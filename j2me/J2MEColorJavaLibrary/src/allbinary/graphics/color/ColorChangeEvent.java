/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: 11/19/02
 *
 *
 * Modified By         When       ?
 *
 */
package allbinary.graphics.color;

import allbinary.logic.basic.util.event.AllBinaryEventObject;

public class ColorChangeEvent extends AllBinaryEventObject
{
    private BasicColor basicColor;
    
    public ColorChangeEvent(Object object)
    {
        super(object);
    }

    public void setBasicColor(BasicColor basicColor)
    {
        this.basicColor = basicColor;
    }

    public BasicColor getBasicColor()
    {
        return basicColor;
    }
}
