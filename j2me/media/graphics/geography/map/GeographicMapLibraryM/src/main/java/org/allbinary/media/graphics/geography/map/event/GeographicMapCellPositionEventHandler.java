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
import org.allbinary.game.layer.geographic.map.MiniMapLayer;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.logic.util.event.handler.BasicEventHandler;

public class GeographicMapCellPositionEventHandler 
extends BasicEventHandler
{

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
        BasicArrayList list = this.eventListenerInterfaceList;
        
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
        
        EventListenerInterface eventListenerInterface;
        GeographicMapCellPositionEventListenerInterface geographicMapCellPositionEventListenerInterface;
        final int size = list.size();
        for (int index = 0; index < size; index++)
        {
            try
            {
                eventListenerInterface = (EventListenerInterface) list.get(index);
                geographicMapCellPositionEventListenerInterface = ((GeographicMapCellPositionEventListenerInterface) eventListenerInterface);
                geographicMapCellPositionEventListenerInterface.onRemoveGeographicMapCellPositionEvent((GeographicMapCellPositionEvent) eventObject);

            } catch (Exception e)
            {
                logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
            }
        }
    }

    @Override
    protected void process(AllBinaryEventObject eventObject,
            EventListenerInterface eventListenerInterface) throws Exception
    {
        GeographicMapCellPositionEventListenerInterface geographicMapCellPositionEventListenerInterface = ((GeographicMapCellPositionEventListenerInterface) eventListenerInterface);
        geographicMapCellPositionEventListenerInterface.onGeographicMapCellPositionEvent((GeographicMapCellPositionEvent) eventObject);
    }
}