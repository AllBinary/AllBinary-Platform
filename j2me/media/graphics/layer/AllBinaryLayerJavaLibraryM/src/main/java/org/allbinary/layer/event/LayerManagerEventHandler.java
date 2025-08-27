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
package org.allbinary.layer.event;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.util.BasicArrayList;

public class LayerManagerEventHandler extends BasicEventHandler
{

    private static LayerManagerEventHandler instance = new LayerManagerEventHandler();

    public static LayerManagerEventHandler getInstance()
    {
        return LayerManagerEventHandler.instance;
    }

    private final BasicArrayList list = new BasicArrayList();
    
    private LayerManagerEventHandler()
    {
    }

    public void addListener(final LayerManagerEventListener layerManagerEventListener)
    {
        if(!list.contains(layerManagerEventListener))
        {
            list.add(layerManagerEventListener);
        }
    }

    @Override
    public void removeAllListeners()
    {
        this.list.clear();
        super.removeAllListeners();
    }

    @Override
    public void removeListener(final EventListenerInterface eventListenerInterface)
    {
        this.list.remove(eventListenerInterface);
        super.removeListener(eventListenerInterface);
    }

    @Override
    public void fireEvent(final AllBinaryEventObject eventObject) throws Exception
    {        
        for (int index = this.list.size(); --index >= 0;)
        {
            try
            {
                final LayerManagerEventListener layerManagerEventListener = (LayerManagerEventListener) this.list.objectArray[index];
                layerManagerEventListener.onCreateLayerManagerEvent((LayerManagerEvent) eventObject);
            }
            catch (Exception e)
            {
                logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
            }
        }

        super.fireEvent(eventObject);
    }
    
    public final String CREATE = "Create";
    public final String DELETE = "Delete";
    
    public synchronized void fireDeleteEvent(final AllBinaryEventObject eventObject)
            throws Exception
    {
        for (int index = this.list.size(); --index >= 0;)
        {
            try
            {
                final LayerManagerEventListener layerManagerEventListener = (LayerManagerEventListener) this.list.objectArray[index];
                layerManagerEventListener.onDeleteLayerManagerEvent((LayerManagerEvent) eventObject);
            }
            catch (Exception e)
            {
                logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
            }
        }
        
        final BasicArrayList eventListenerInterfaceList = this.eventListenerInterfaceList;

        EventListenerInterface eventListenerInterface;
        LayerManagerEventListenerInterface layerManagerEventListenerInterface;
        int index = 0;
        while (index < eventListenerInterfaceList.size())
        {
            try
            {
                eventListenerInterface = (EventListenerInterface) eventListenerInterfaceList.objectArray[index];
                layerManagerEventListenerInterface = ((LayerManagerEventListenerInterface) eventListenerInterface);
                layerManagerEventListenerInterface.onDeleteLayerManagerEvent((LayerManagerEvent) eventObject);
            }
            catch (Exception e)
            {
                logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
            }
            index++;
        }
    }

    @Override
    protected void process(final AllBinaryEventObject eventObject,
            final EventListenerInterface eventListenerInterface) throws Exception
    {
        final LayerManagerEventListenerInterface layerManagerEventListenerInterface = ((LayerManagerEventListenerInterface) eventListenerInterface);
        layerManagerEventListenerInterface.onCreateLayerManagerEvent((LayerManagerEvent) eventObject);
    }
}
