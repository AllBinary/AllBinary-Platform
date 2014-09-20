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

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.basic.util.event.AllBinaryEventObject;
import org.allbinary.logic.basic.util.event.EventListenerInterface;
import org.allbinary.logic.basic.util.event.handler.BasicEventHandler;

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

    public void addListener(LayerManagerEventListener layerManagerEventListener)
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

    public void removeListener(EventListenerInterface eventListenerInterface)
    {
        this.list.remove(eventListenerInterface);
        super.removeListener(eventListenerInterface);
    }

    public void fireEvent(AllBinaryEventObject eventObject) throws Exception
    {        
        for (int index = this.list.size(); --index >= 0;)
        {
            try
            {
                LayerManagerEventListener layerManagerEventListener = (LayerManagerEventListener) this.list.objectArray[index];
                layerManagerEventListener.onCreateLayerManagerEvent((LayerManagerEvent) eventObject);
            }
            catch (Exception e)
            {
                LogUtil.put(new Log(CommonStrings.getInstance().EXCEPTION, this, "fireEvent", e));
            }
        }

        super.fireEvent(eventObject);
    }
    
    public final String CREATE = "Create";
    public final String DELETE = "Delete";
    
    public synchronized void fireDeleteEvent(AllBinaryEventObject eventObject)
            throws Exception
    {
        for (int index = this.list.size(); --index >= 0;)
        {
            try
            {
                LayerManagerEventListener layerManagerEventListener = (LayerManagerEventListener) this.list.objectArray[index];
                layerManagerEventListener.onDeleteLayerManagerEvent((LayerManagerEvent) eventObject);
            }
            catch (Exception e)
            {
                LogUtil.put(new Log(CommonStrings.getInstance().EXCEPTION, this, "fireEvent", e));
            }
        }
        
        final BasicArrayList eventListenerInterfaceList = this
                .getEventListenerInterfaceList();

        for (int index = 0; index < eventListenerInterfaceList.size(); index++)
        {
            try
            {
                EventListenerInterface eventListenerInterface = (EventListenerInterface) eventListenerInterfaceList
                        .objectArray[index];

                ((LayerManagerEventListenerInterface) eventListenerInterface)
                        .onDeleteLayerManagerEvent((LayerManagerEvent) eventObject);
            }
            catch (Exception e)
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "fireEvent", e));
            }
        }
    }

    protected void process(AllBinaryEventObject eventObject,
            EventListenerInterface eventListenerInterface) throws Exception
    {
        ((LayerManagerEventListenerInterface) eventListenerInterface)
                .onCreateLayerManagerEvent((LayerManagerEvent) eventObject);
    }
}
