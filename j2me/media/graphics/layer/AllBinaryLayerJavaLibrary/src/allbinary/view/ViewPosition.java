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
package allbinary.view;

import allbinary.layer.AllBinaryLayer;

// get the location of this vehicle relative to the top right corner of the
// tileLayer location
public class ViewPosition extends Position
{
    private AllBinaryLayer allbinaryLayer;

    public ViewPosition()
    {
    }

    protected ViewPosition(int x, int y)
    {
        super(x, y);
    }

    protected ViewPosition(int x, int y, int z)
    {
        super(x, y, z);
    }

    public int getX()
    {
        return this.allbinaryLayer.getX();
    }

    public int getY()
    {
        return this.allbinaryLayer.getY();
    }

    public int getZ()
    {
        return this.allbinaryLayer.getZ();
    }

    public int getX2()
    {
        return this.getX() + this.allbinaryLayer.getWidth();
    }

    public int getY2()
    {
        return this.getY() + this.allbinaryLayer.getHeight();
    }

    public int getZ2()
    {
        return this.getZ() + this.allbinaryLayer.getDepth();
    }

    /*
     * private AllBinaryLayer getAllbinaryLayer() { return allbinaryLayer; }
     */

    public void setAllbinaryLayer(AllBinaryLayer allbinaryLayer)
    {
        this.allbinaryLayer = allbinaryLayer;
    }
}
