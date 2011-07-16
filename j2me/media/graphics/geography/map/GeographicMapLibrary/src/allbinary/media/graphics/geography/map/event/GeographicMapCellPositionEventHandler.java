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
package allbinary.media.graphics.geography.map.event;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.EventListenerInterface;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class GeographicMapCellPositionEventHandler extends BasicEventHandler
{
    private static GeographicMapCellPositionEventHandler gameKeyEventHandler = new GeographicMapCellPositionEventHandler();

    private GeographicMapCellPositionEventHandler()
    {
    }

    public static GeographicMapCellPositionEventHandler getInstance()
    {
        return GeographicMapCellPositionEventHandler.gameKeyEventHandler;
    }

    public synchronized void fireRemoveEvent(AllBinaryEventObject eventObject) throws Exception
    {
        BasicArrayList list = this.getEventListenerInterfaceList();
        
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
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "fireEvent", e));
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