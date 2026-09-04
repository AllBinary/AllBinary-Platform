/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package org.allbinary.game.input.mapping;

import jsinterop.annotations.JsType;

import java.util.Enumeration;

import org.allbinary.game.configuration.persistance.GamePersistanceStrings;
import org.allbinary.game.input.Input;
import org.allbinary.game.input.InputPersistance;
import org.allbinary.game.input.mapping.event.InputMappingEvent;
import org.allbinary.game.input.mapping.event.InputMappingEventListenerInterface;
import org.allbinary.game.input.mapping.event.NullInputMappingEventListener;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.EnumerationUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;
import org.allbinary.util.ABHashtable;

//GameInputNoPersistanceLibrary

@JsType
public class PersistentInputMapping
{
    @JsProperty
    public static Object instance = NullUtil.getInstance().NULL_OBJECT;
    
    @JsMethod
    public static PersistentInputMapping getNullInstance() {
        
        if(PersistentInputMapping.instance == NullUtil.getInstance().NULL_OBJECT) {
            PersistentInputMapping.instance = new PersistentInputMapping(GamePersistanceStrings.getInstance().SAVED_INPUT_CONFIGURATION_RECORD_ID);
        }

        return (PersistentInputMapping) PersistentInputMapping.instance;
    }
    
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    private final EnumerationUtil enumerationUtil = EnumerationUtil.getInstance();
    
    private final InputToGameKeyMapping inputMapping = new InputToGameKeyMapping();
    
    private InputMappingEventListenerInterface inputMappingEventListenerInterface = 
        NullInputMappingEventListener.NULL_INPUT_MAPPING_EVENT_LISTENER;
    
    private final InputPersistance inputPersistance;

    @JsConstructor
    protected PersistentInputMapping(String name)
    {
        this.inputPersistance = new InputPersistance(name);
    }
    
    @JsMethod
    public int getTotalMapped()
    {
        return this.getInputMapping().getHashtable().size();
    }
    
    @JsMethod
    protected boolean isDefaultNew()
    {
        return false;
    }
    
    @JsMethod
    protected InputToGameKeyMapping getDefault()
    {
        return InputToGameKeyMapping.getNullInstance();
    }
        
    @JsMethod
    public void setDefault(final AbeClientInformationInterface abeClientInformation)
    //InputToGameKeyMapping inputToGameKeyMapping
    throws Exception
    {
        this.getInputMapping().removeAll();
        this.getInputMapping().addMapping(this.getDefault());
        //this.save(inputToGameKeyMapping);
        this.save(abeClientInformation);
    }

    @JsMethod
    public void update(final AbeClientInformationInterface abeClientInformation) throws Exception
    {
        this.inputPersistance.deleteAll(abeClientInformation);
        this.save(abeClientInformation);
    }
    
    private InputMappingEvent inputMappingEvent = new InputMappingEvent(this); 
    @JsMethod
    public void save(final AbeClientInformationInterface abeClientInformation)
    //InputToGameKeyMapping inputToGameKeyMapping
    throws Exception
    {
        this.inputPersistance.save(abeClientInformation, this.getInputMapping().getHashtable());
        //inputMappingEvent.setInputToGameKeyMapping(inputToGameKeyMapping);
        this.inputMappingEvent.setInputToGameKeyMapping(this.getInputMapping());
        if(this.getInputMappingEventListenerInterface() != null)
        {
            this.getInputMappingEventListenerInterface().onInputMappingEvent(this.inputMappingEvent);
        }
    }
    
    @JsMethod
    public void init(final AbeClientInformationInterface abeClientInformation) 
    throws Exception
    {
        this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.INIT);
        //Write out the default mappings and reload if something went wrong
        //This could happen if file is not deleted between versions and something changed
        try
        {
            this.inputPersistance.loadAll(abeClientInformation);
        }
        catch(Exception e)
        {
            //this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.INIT, e);
            PreLogUtil.putOE(this.commonStrings.EXCEPTION, this, this.commonStrings.INIT, e);
            //inputPersistance.deleteAll(abeClientInformation);
            this.inputPersistance.deleteRecoreStore(abeClientInformation);
            this.setDefault(abeClientInformation);
            //this.setDefault((InputToGameKeyMapping) this);
            this.inputPersistance.loadAll(abeClientInformation);
        }

        final BasicArrayList list = this.inputPersistance.getList();

        int totalMappedTo = 0;
        //TWB - Use selected profile/id for future imp
        final int size = list.size();
        ABHashtable hashtable;
        Enumeration enumeration;
        Input mappedToInput;
        Input gameActionInput;
        for(int index = 0; index < size; index++)
        {
            hashtable = (ABHashtable) list.objectArray[index];
            enumeration = hashtable.keys();

            while(this.enumerationUtil.hasMoreElements(enumeration))
            {
                mappedToInput = (Input) this.enumerationUtil.nextElement(enumeration);
                gameActionInput = (Input) hashtable.get(mappedToInput);

                //AndroidGameKey mappedToKey
                //MotionGestureInput mappedToKey

                totalMappedTo++;
                this.getInputMapping().add(gameActionInput, mappedToInput);
            }
        }

        final StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("End - Total Loaded Keys Mapped: ");
        stringBuffer.appendint(this.getTotalMapped());
        stringBuffer.append(" to: ");
        stringBuffer.appendint(totalMappedTo);
        
        this.logUtil.putF(stringBuffer.toString(), this, this.commonStrings.INIT);
    }

    @JsMethod
    public void setInputMappingEventListenerInterface(
            InputMappingEventListenerInterface inputMappingEventListenerInterface)
    {
        this.inputMappingEventListenerInterface = inputMappingEventListenerInterface;
    }

    @JsMethod
    private InputMappingEventListenerInterface getInputMappingEventListenerInterface()
    {
        return this.inputMappingEventListenerInterface;
    }

    @JsMethod
    public InputToGameKeyMapping getInputMapping()
    {
        return this.inputMapping;
    }
    
    //TWB - Hack Method for Platform Independence - Maybe a better place for this
    @JsMethod
    public boolean isDelete(Input input) throws Exception
    {
        throw new Exception(this.commonStrings.NOT_IMPLEMENTED);
    }

    @JsMethod
    public boolean isSystemInput(Input input) throws Exception
    {
        throw new Exception(this.commonStrings.NOT_IMPLEMENTED);
    }
    
}
