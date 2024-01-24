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

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.game.input.Input;
import org.allbinary.game.input.InputPersistance;
import org.allbinary.game.input.mapping.event.InputMappingEvent;
import org.allbinary.game.input.mapping.event.InputMappingEventListenerInterface;
import java.util.Enumeration;
import java.util.Hashtable;
import org.allbinary.util.BasicArrayList;

public class PersistentInputMapping
{
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public static final String DEFAULT_SAVE_NAME = "_Default_Input_Mapping";
    public static final String SAVE_NAME = "_Saved_Input_Configuration";

    private final InputToGameKeyMapping inputMapping = new InputToGameKeyMapping();
    
    private InputMappingEventListenerInterface inputMappingEventListenerInterface;
    
    private final InputPersistance inputPersistance;

    protected PersistentInputMapping()
    {
        inputPersistance = new InputPersistance(SAVE_NAME);
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
        
    public void setDefault()
    //InputToGameKeyMapping inputToGameKeyMapping
    throws Exception
    {
        this.getInputMapping().removeAll();
        this.getInputMapping().add(this.getDefault());
        //this.save(inputToGameKeyMapping);
        this.save();
    }

    public void update() throws Exception
    {
        this.inputPersistance.deleteAll();
        this.save();
    }
    
    private InputMappingEvent inputMappingEvent = new InputMappingEvent(this); 
    public void save()
    //InputToGameKeyMapping inputToGameKeyMapping
    throws Exception
    {
        inputPersistance.save(this.getInputMapping().getHashtable());
        //inputMappingEvent.setInputToGameKeyMapping(inputToGameKeyMapping);
        inputMappingEvent.setInputToGameKeyMapping(this.getInputMapping());
        if(this.getInputMappingEventListenerInterface() != null)
        {
            this.getInputMappingEventListenerInterface().onInputMappingEvent(inputMappingEvent);
        }
    }
    
    public void init() 
    throws Exception
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.INIT));
        //Write out the default mappings and reload if something went wrong
        //This could happen if file is not deleted between versions and something changed
        try
        {
            inputPersistance.loadAll();
        }
        catch(Exception e)
        {
            //LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.INIT, e));
            PreLogUtil.put(commonStrings.EXCEPTION, this, commonStrings.INIT, e);
            inputPersistance.deleteAll();
            this.setDefault();
            //this.setDefault((InputToGameKeyMapping) this);
            inputPersistance.loadAll();
        }

        BasicArrayList list = inputPersistance.getList();

        int totalMappedTo = 0;
        //TWB - Use selected profile/id for future imp
        for(int index = 0; index < list.size(); index++)
        {
            Hashtable hashtable = (Hashtable) list.objectArray[index];
            Enumeration enumeration = hashtable.keys();

            while(enumeration.hasMoreElements())
            {
                Input mappedToInput = (Input) enumeration.nextElement();
                Input gameActionInput = (Input) hashtable.get(mappedToInput);

                //AndroidGameKey mappedToKey
                //MotionGestureInput mappedToKey

                totalMappedTo++;
                this.getInputMapping().add(gameActionInput, mappedToInput);
            }
        }

        StringMaker stringBuffer = new StringMaker();
        
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
}
