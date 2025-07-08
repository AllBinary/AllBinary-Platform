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
package org.allbinary.media.graphics.geography.map.event;

import org.allbinary.util.BasicArrayList;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.layer.geographic.map.MiniMapLayer;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.logic.util.event.handler.BasicEventHandler;

public class GeographicMapCellPositionEventHandler 
extends BasicEventHandler
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static GeographicMapCellPositionEventHandler instance = new GeographicMapCellPositionEventHandler();

    public static GeographicMapCellPositionEventHandler getInstance()
    {
        return GeographicMapCellPositionEventHandler.instance;
    }

    private final BasicArrayList list = new BasicArrayList();
    
    private GeographicMapCellPositionEventHandler()
    {
    }

    public void addListener(MiniMapLayer miniMapLayer)
    {
        if(!list.contains(miniMapLayer))
        {
            list.add(miniMapLayer);
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
                MiniMapLayer miniMapLayer = (MiniMapLayer) this.list.get(index);
                miniMapLayer.onGeographicMapCellPositionEvent(
                        (GeographicMapCellPositionEvent) eventObject);
            }
            catch (Exception e)
            {
                logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
            }
        }

        super.fireEvent(eventObject);
    }

    public synchronized void fireRemoveEvent(AllBinaryEventObject eventObject) throws Exception
    {
        BasicArrayList list = this.getEventListenerInterfaceList();
        
        for (int index = this.list.size(); --index >= 0;)
        {
            try
            {
                MiniMapLayer miniMapLayer = (MiniMapLayer) this.list.get(index);
                miniMapLayer.onRemoveGeographicMapCellPositionEvent(
                        (GeographicMapCellPositionEvent) eventObject);
            }
            catch (Exception e)
            {
                logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
            }
        }
        
        for (int index = 0; index < list.size(); index++)
        {
            try
            {
                EventListenerInterface eventListenerInterface = 
                    (EventListenerInterface) list.get(index);

                ((GeographicMapCellPositionEventListenerInterface) eventListenerInterface)
                .onRemoveGeographicMapCellPositionEvent((GeographicMapCellPositionEvent) eventObject);

            } catch (Exception e)
            {
                logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
            }
        }
    }

    protected void process(AllBinaryEventObject eventObject,
            EventListenerInterface eventListenerInterface) throws Exception
    {
        ((GeographicMapCellPositionEventListenerInterface) 
                eventListenerInterface).onGeographicMapCellPositionEvent(
                (GeographicMapCellPositionEvent) eventObject);
    }
}