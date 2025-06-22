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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.game.input.Input;
import org.allbinary.game.input.InputPersistance;
import org.allbinary.game.input.mapping.event.InputMappingEvent;
import org.allbinary.game.input.mapping.event.InputMappingEventListenerInterface;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.util.BasicArrayList;

public class PersistentInputMapping
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    //_Default_Input_Mapping
    public static final String DEFAULT_RECORD_ID = "_DIM";
    //_Saved_Input_Configuration
    public static final String RECORD_ID = "_SIC";

    private final InputToGameKeyMapping inputMapping = new InputToGameKeyMapping();
    
    private InputMappingEventListenerInterface inputMappingEventListenerInterface;
    
    private final InputPersistance inputPersistance;

    protected PersistentInputMapping()
    {
        inputPersistance = new InputPersistance(PersistentInputMapping.RECORD_ID);
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
        return null;
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
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.INIT));
        //Write out the default mappings and reload if something went wrong
        //This could happen if file is not deleted between versions and something changed
        try
        {
            inputPersistance.loadAll(abeClientInformation);
        }
        catch(Exception e)
        {
            //LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.INIT, e));
            PreLogUtil.put(commonStrings.EXCEPTION, this, commonStrings.INIT, e);
            inputPersistance.deleteRecoreStore(abeClientInformation);
            //inputPersistance.deleteAll(abeClientInformation);
            this.setDefault(abeClientInformation);
            //this.setDefault((InputToGameKeyMapping) this);
            inputPersistance.loadAll(abeClientInformation);
        }

        final BasicArrayList list = inputPersistance.getList();

        final int size = list.size();
        //LogUtil.put(LogFactory.getInstance("size: " + size, this, commonStrings.INIT));
        
        int totalMappedTo = 0;
        //TWB - Use selected profile/id for future imp
        for(int index = 0; index < size; index++)
        {
            final Hashtable hashtable = (Hashtable) list.objectArray[index];
            //LogUtil.put(LogFactory.getInstance("hashtable.keySet().size(): " + hashtable.keySet().size(), this, commonStrings.INIT));
            final Enumeration enumeration = hashtable.keys();
            
            while(enumeration.hasMoreElements())
            {
                final Input mappedToInput = (Input) enumeration.nextElement();
                final Input gameActionInput = (Input) hashtable.get(mappedToInput);

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
        
        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, commonStrings.INIT));
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
