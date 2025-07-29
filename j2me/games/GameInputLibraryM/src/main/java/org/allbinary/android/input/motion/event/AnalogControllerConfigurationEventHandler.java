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
package org.allbinary.android.input.motion.event;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.util.BasicArrayList;

public class AnalogControllerConfigurationEventHandler extends BasicEventHandler
{

    private static final AnalogControllerConfigurationEventHandler instance = new AnalogControllerConfigurationEventHandler();

    public static AnalogControllerConfigurationEventHandler getInstance()
    {
        return instance;
    }
    
    private final BasicArrayList list = new BasicArrayList();
    
    private AnalogControllerConfigurationEventHandler()
    {
    }

    public void addListener(AnalogControllerConfigurationEventListenerInterface analogControllerConfigurationEventListenerInterface)
    {
        if(!list.contains(analogControllerConfigurationEventListenerInterface))
        {
            list.add(analogControllerConfigurationEventListenerInterface);
        }
    }

    @Override
    public void removeAllListeners()
    {
        this.list.clear();
        super.removeAllListeners();
    }

    @Override
    public void removeListener(EventListenerInterface eventListenerInterface)
    {
        this.list.remove(eventListenerInterface);
        super.removeListener(eventListenerInterface);
    }

    @Override
    public void fireEvent(AllBinaryEventObject eventObject) throws Exception
    {        
        for (int index = this.list.size(); --index >= 0;)
        {
            try
            {
            	//Add deviceId
            	AnalogControllerConfigurationEventListenerInterface playerGameInput = 
            			(AnalogControllerConfigurationEventListenerInterface) this.list.objectArray[index];
                playerGameInput.onAnalogControllerConfigurationEvent(
                		(AnalogControllerConfigurationEvent) eventObject);
            }
            catch (Exception e)
            {
                logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
            }
        }

        super.fireEvent(eventObject);
    }
    
    @Override
    protected void process(final AllBinaryEventObject eventObject, final EventListenerInterface eventListenerInterface) throws Exception
    {
        final AnalogControllerConfigurationEventListenerInterface analogControllerConfigurationEventListenerInterface = 
            ((AnalogControllerConfigurationEventListenerInterface) eventListenerInterface);
        analogControllerConfigurationEventListenerInterface.onAnalogControllerConfigurationEvent((AnalogControllerConfigurationEvent) eventObject);
    }
}
