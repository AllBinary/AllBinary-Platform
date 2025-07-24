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
package org.allbinary.graphics;

public class CustomGPoint extends GPoint
{
    private int x;
    private int y;
    private int z;
    
    private CustomGPoint(GPoint point)
    {
        super(point);
    }
    
    public CustomGPoint(int x, int y)
    {
        super(x, y);        
    }

    public CustomGPoint(int x, int y, int z)
    {
        super(x, y, z);
    }
    
    public void setX(int x)
    {
        this.x = x;
    }

    @Override
    public int getX()
    {
        return x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    @Override
    public int getY()
    {
        return y;
    }

    public void setZ(int z)
    {
        this.z = z;
    }

    @Override
    public int getZ()
    {
        return z;
    }
}
