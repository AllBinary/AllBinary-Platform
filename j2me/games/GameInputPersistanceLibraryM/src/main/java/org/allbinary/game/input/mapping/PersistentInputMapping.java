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

import java.util.Enumeration;
import java.util.Hashtable;

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

//GameInputPersistanceLibrary
public class PersistentInputMapping
{
    public static Object instance = NullUtil.getInstance().NULL_OBJECT;
    
    public static PersistentInputMapping getNullInstance() {
        
        if(PersistentInputMapping.instance == NullUtil.getInstance().NULL_OBJECT) {
            PersistentInputMapping.instance = new PersistentInputMapping(GamePersistanceStrings.getInstance().SAVED_INPUT_CONFIGURATION_RECORD_ID);
        }

        return (PersistentInputMapping) PersistentInputMapping.instance;
    }
    
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    private final EnumerationUtil enumerationUtil = EnumerationUtil.getInstance();
    
    private final InputToGameKeyMapping inputMapping = new InputToGameKeyMapping();
    
    private InputMappingEventListenerInterface inputMappingEventListenerInterface = 
        NullInputMappingEventListener.NULL_INPUT_MAPPING_EVENT_LISTENER;
    
    private final InputPersistance inputPersistance;

    protected PersistentInputMapping(String name)
    {
        this.inputPersistance = new InputPersistance(name);
    }
    
    public int getTotalMapped()
    {
        return this.getInputMapping().getHashtable().size();
    }
    
    protected boolean isDefaultNew()
    {
        return false;
    }
    
    protected InputToGameKeyMapping getDefault()
    {
        return InputToGameKeyMapping.getNullInstance();
    }
        
    public void setDefault(final AbeClientInformationInterface abeClientInformation)
    //InputToGameKeyMapping inputToGameKeyMapping
    throws Exception
    {
        this.getInputMapping().removeAll();
        this.getInputMapping().addMapping(this.getDefault());
        //this.save(inputToGameKeyMapping);
        this.save(abeClientInformation);
    }

    public void update(final AbeClientInformationInterface abeClientInformation) throws Exception
    {
        this.inputPersistance.deleteAll(abeClientInformation);
        this.save(abeClientInformation);
    }
    
    private InputMappingEvent inputMappingEvent = new InputMappingEvent(this); 
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
            this.inputPersistance.deleteRecoreStore(abeClientInformation);
            //inputPersistance.deleteAll(abeClientInformation);
            this.setDefault(abeClientInformation);
            //this.setDefault((InputToGameKeyMapping) this);
            this.inputPersistance.loadAll(abeClientInformation);
        }

        final BasicArrayList list = this.inputPersistance.getList();
        
        int totalMappedTo = 0;
        //TWB - Use selected profile/id for future imp
        final int size = list.size();
        //this.logUtil.putF("size: " + size, this, this.commonStrings.INIT);
        Hashtable hashtable;
        Enumeration enumeration;
        Input mappedToInput;
        Input gameActionInput;
        for(int index = 0; index < size; index++)
        {
            hashtable = (Hashtable) list.objectArray[index];
            //this.logUtil.putF("hashtable.keySet().size(): " + hashtable.keySet().size(), this, this.commonStrings.INIT);
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

    public void setInputMappingEventListenerInterface(
            InputMappingEventListenerInterface inputMappingEventListenerInterface)
    {
        this.inputMappingEventListenerInterface = inputMappingEventListenerInterface;
    }

    private InputMappingEventListenerInterface getInputMappingEventListenerInterface()
    {
        return this.inputMappingEventListenerInterface;
    }

    public InputToGameKeyMapping getInputMapping()
    {
        return this.inputMapping;
    }
    
    //TWB - Hack Method for Platform Independence - Maybe a better place for this
    public boolean isDelete(Input input) throws Exception
    {
        throw new Exception(this.commonStrings.NOT_IMPLEMENTED);
    }

    public boolean isSystemInput(Input input) throws Exception
    {
        throw new Exception(this.commonStrings.NOT_IMPLEMENTED);
    }
    
}
