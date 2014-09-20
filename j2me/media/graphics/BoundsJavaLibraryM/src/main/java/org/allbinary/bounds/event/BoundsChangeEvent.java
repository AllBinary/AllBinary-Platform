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
package org.allbinary.bounds.event;

import org.allbinary.graphics.Rectangle;
import org.allbinary.logic.basic.util.event.AllBinaryEventObject;

public class BoundsChangeEvent extends AllBinaryEventObject
{
    public BoundsChangeEvent(Rectangle rectangle)
    {
        super(rectangle);
    }
    
    public Rectangle getRectangle()
    {
        return (Rectangle) this.getSource();
    }
}
