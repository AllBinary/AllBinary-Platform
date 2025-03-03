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

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;

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
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("CellPositionEvent: ");
        stringBuffer.append("\nGeographicMapCellPosition: ");
        stringBuffer.append(StringUtil.getInstance().toString(this.getGeographicMapCellPosition()));

        return stringBuffer.toString();
    }
}
