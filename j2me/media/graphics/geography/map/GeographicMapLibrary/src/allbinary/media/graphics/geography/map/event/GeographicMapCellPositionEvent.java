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

import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.media.graphics.geography.map.GeographicMapCellPosition;

public class GeographicMapCellPositionEvent extends AllBinaryEventObject
{
    private GeographicMapCellPosition geographicMapCellPosition;

    public GeographicMapCellPositionEvent(Object object,
            GeographicMapCellPosition geographicMapCellPosition)
    {
        super(object);

        this.setGeographicMapCellPosition(geographicMapCellPosition);
    }

    public GeographicMapCellPositionEvent(Object object, int key)
    {
        super(object);
    }

    public void init(Object object)
    {
        this.setSource(object);
    }

    public void setGeographicMapCellPosition(
            GeographicMapCellPosition geographicMapCellPosition)
    {
        this.geographicMapCellPosition = geographicMapCellPosition;
    }

    public GeographicMapCellPosition getGeographicMapCellPosition()
    {
        return geographicMapCellPosition;
    }

    public String toString()
    {
        StringBuilder stringBuffer = new StringBuilder();

        stringBuffer.append("CellPositionEvent: ");
        stringBuffer.append("\nGeographicMapCellPosition: ");
        stringBuffer.append(this.getGeographicMapCellPosition());

        return stringBuffer.toString();
    }
}
