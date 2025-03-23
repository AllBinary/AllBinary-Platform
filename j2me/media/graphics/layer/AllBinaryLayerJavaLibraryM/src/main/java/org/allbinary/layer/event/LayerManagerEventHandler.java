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

import org.allbinary.util.BasicArrayList;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.logic.util.event.handler.BasicEventHandler;

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

    public void removeAllListeners()
    {
        this.list.clear();
        super.removeAllListeners();
    }

    public void removeListener(final EventListenerInterface eventListenerInterface)
    {
        this.list.remove(eventListenerInterface);
        super.removeListener(eventListenerInterface);
    }

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
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e));
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
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e));
            }
        }
        
        final BasicArrayList eventListenerInterfaceList = this
                .getEventListenerInterfaceList();

        for (int index = 0; index < eventListenerInterfaceList.size(); index++)
        {
            try
            {
                final EventListenerInterface eventListenerInterface = (EventListenerInterface) eventListenerInterfaceList
                        .objectArray[index];

                ((LayerManagerEventListenerInterface) eventListenerInterface)
                        .onDeleteLayerManagerEvent((LayerManagerEvent) eventObject);
            }
            catch (Exception e)
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e));
            }
        }
    }

    protected void process(final AllBinaryEventObject eventObject,
            final EventListenerInterface eventListenerInterface) throws Exception
    {
        ((LayerManagerEventListenerInterface) eventListenerInterface)
                .onCreateLayerManagerEvent((LayerManagerEvent) eventObject);
    }
}
