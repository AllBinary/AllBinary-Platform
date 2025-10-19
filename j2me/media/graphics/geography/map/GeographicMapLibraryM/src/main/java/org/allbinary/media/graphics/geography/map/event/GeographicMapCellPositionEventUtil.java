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

import org.allbinary.graphics.CellPositionFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPositionTracking;

public class GeographicMapCellPositionEventUtil
{
    private final GeographicMapCellPositionEvent geographicMapCellPositionEvent;
    private final GeographicMapCellPositionEventHandler geographicMapCellPositionEventHandler = 
        GeographicMapCellPositionEventHandler.getInstance();

    public GeographicMapCellPositionEventUtil(Object object)
    {
        geographicMapCellPositionEvent = new GeographicMapCellPositionEvent(object, CellPositionFactory.getInstance().NONE);
    }

    public void update(GeographicMapCellPositionTracking geographicMapCellPositionTracking)
    throws Exception
    {
        geographicMapCellPositionEvent.setGeographicMapCellPosition(
                geographicMapCellPositionTracking.getCurrentGeographicMapCellPosition());

        geographicMapCellPositionEventHandler.fireEvent(
                geographicMapCellPositionEvent);
    }

    public void remove(GeographicMapCellPositionTracking geographicMapCellPositionTracking)
    throws Exception
    {
        geographicMapCellPositionEvent.setGeographicMapCellPosition(
                geographicMapCellPositionTracking.getCurrentGeographicMapCellPosition());

        geographicMapCellPositionEventHandler.fireRemoveEvent(
                geographicMapCellPositionEvent);
    }
}
