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

import org.allbinary.logic.util.event.AllBinaryEventObject;

public class ScrollMapEvent extends AllBinaryEventObject
{
    private int dx;
    private int dy;
    
    public ScrollMapEvent(Object object)
    {
        super(object);
    }

    public void setDx(int dx)
    {
        this.dx = dx;
    }

    public int getDx()
    {
        return dx;
    }

    public void setDy(int dy)
    {
        this.dy = dy;
    }

    public int getDy()
    {
        return dy;
    }
    
    public void setDxDy(int dx, int dy)
    {
        this.dx = dx;
        this.dy = dy;
    }
}
