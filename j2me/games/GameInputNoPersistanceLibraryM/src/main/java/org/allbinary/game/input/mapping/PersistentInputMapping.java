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
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class PersistentInputMapping
{
    public static final PersistentInputMapping NULL_PERSISTENT_INPUT_MAPPING = new PersistentInputMapping();
    
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final InputToGameKeyMapping inputMapping = new InputToGameKeyMapping();
    
    private InputMappingEventListenerInterface inputMappingEventListenerInterface = 
        NullInputMappingEventListener.NULL_INPUT_MAPPING_EVENT_LISTENER;
    
    private final InputPersistance inputPersistance;

    protected PersistentInputMapping()
    {
        inputPersistance = new InputPersistance(GamePersistanceStrings.getInstance().SAVED_INPUT_CONFIGURATION_RECORD_ID);
    }

    protected PersistentInputMapping(String name)
    {
        inputPersistance = new InputPersistance(name);
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
        return InputToGameKeyMapping.NULL_INPUT_TO_GAME_KEY_MAPPING;
    }
        
    public void setDefault(final AbeClientInformationInterface abeClientInformation)
    //InputToGameKeyMapping inputToGameKeyMapping
    throws Exception
    {
        this.getInputMapping().removeAll();
        this.getInputMapping().add(this.getDefault());
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
        inputPersistance.save(abeClientInformation, this.getInputMapping().getHashtable());
        //inputMappingEvent.setInputToGameKeyMapping(inputToGameKeyMapping);
        inputMappingEvent.setInputToGameKeyMapping(this.getInputMapping());
        if(this.getInputMappingEventListenerInterface() != null)
        {
            this.getInputMappingEventListenerInterface().onInputMappingEvent(inputMappingEvent);
        }
    }
    
    public void init(final AbeClientInformationInterface abeClientInformation) 
    throws Exception
    {
        logUtil.put(commonStrings.START, this, commonStrings.INIT);
        //Write out the default mappings and reload if something went wrong
        //This could happen if file is not deleted between versions and something changed
        try
        {
            inputPersistance.loadAll(abeClientInformation);
        }
        catch(Exception e)
        {
            //logUtil.put(commonStrings.EXCEPTION, this, commonStrings.INIT, e);
            PreLogUtil.put(commonStrings.EXCEPTION, this, commonStrings.INIT, e);
            //inputPersistance.deleteAll(abeClientInformation);
            inputPersistance.deleteRecoreStore(abeClientInformation);
            this.setDefault(abeClientInformation);
            //this.setDefault((InputToGameKeyMapping) this);
            inputPersistance.loadAll(abeClientInformation);
        }

        BasicArrayList list = inputPersistance.getList();

        int totalMappedTo = 0;
        //TWB - Use selected profile/id for future imp
        final int size = list.size();
        Hashtable hashtable;
        Enumeration enumeration;
        Input mappedToInput;
        Input gameActionInput;
        for(int index = 0; index < size; index++)
        {
            hashtable = (Hashtable) list.objectArray[index];
            enumeration = hashtable.keys();

            while(enumeration.hasMoreElements())
            {
                mappedToInput = (Input) enumeration.nextElement();
                gameActionInput = (Input) hashtable.get(mappedToInput);

                //AndroidGameKey mappedToKey
                //MotionGestureInput mappedToKey

                totalMappedTo++;
                this.getInputMapping().add(gameActionInput, mappedToInput);
            }
        }

        final StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("End - Total Loaded Keys Mapped: ");
        stringBuffer.append(this.getTotalMapped());
        stringBuffer.append(" to: ");
        stringBuffer.append(totalMappedTo);
        
        logUtil.put(stringBuffer.toString(), this, commonStrings.INIT);
    }

    public void setInputMappingEventListenerInterface(
            InputMappingEventListenerInterface inputMappingEventListenerInterface)
    {
        this.inputMappingEventListenerInterface = inputMappingEventListenerInterface;
    }

    private InputMappingEventListenerInterface getInputMappingEventListenerInterface()
    {
        return inputMappingEventListenerInterface;
    }

    public InputToGameKeyMapping getInputMapping()
    {
        return inputMapping;
    }
    
    //TWB - Hack Method for Plaform Independence - Maybe a better place for this
    public boolean isDelete(Input input) throws Exception
    {
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }

    public boolean isSystemInput(Input input) throws Exception
    {
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }
    
}
