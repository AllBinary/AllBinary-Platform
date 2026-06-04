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
package org.allbinary.view;

import org.allbinary.graphics.GPoint;

public class ViewPositionBase extends GPoint
{
    public static final ViewPositionBase NULL_VIEW_POSITION = new ViewPositionBase(0, 0, 0);
    
    protected ViewPositionBase(int x, int y, int z)
    {
        super(x, y, z);
    }
    
    public int getX2()
    {
        return this.getX();
    }

    public int getY2()
    {
        return this.getY();
    }

    public int getZ2()
    {
        return this.getZ();
    }
    
}
