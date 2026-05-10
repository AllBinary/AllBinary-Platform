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
    public static final CustomGPoint NULL_CUSTOM_POINT = new CustomGPoint(0, 0, 0);

    public static CustomGPoint getInstance(final GPoint point) {
        return new CustomGPoint(point.getX(), point.getY(), point.getZ());
    }

    public static CustomGPoint getInstance3(final int x, final int y) {
        return new CustomGPoint(x, y, 3);
    }

    private int cx;
    private int cy;
    private int cz;

    public CustomGPoint(int x, int y, int z)
    {
        super(x, y, z);
    }
    
    public void setX(int x)
    {
        this.cx = x;
    }

    @Override
    public int getX()
    {
        return this.cx;
    }

    public void setY(int y)
    {
        this.cy = y;
    }

    @Override
    public int getY()
    {
        return this.cy;
    }

    public void setZ(int z)
    {
        this.cz = z;
    }

    @Override
    public int getZ()
    {
        return this.cz;
    }
}
