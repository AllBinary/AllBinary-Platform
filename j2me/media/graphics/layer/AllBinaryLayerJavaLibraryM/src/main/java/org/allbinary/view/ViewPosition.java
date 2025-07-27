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
import org.allbinary.layer.AllBinaryLayer;

// get the location of this vehicle relative to the top right corner of the
// tileLayer location
public class ViewPosition extends GPoint
{
    public static final ViewPosition NULL_VIEW_POSITION = new ViewPosition();
    
    private AllBinaryLayer allbinaryLayer = AllBinaryLayer.NULL_ALLBINARY_LAYER;

    public ViewPosition()
    {
        super(0, 0, 0);
    }

    protected ViewPosition(int x, int y)
    {
        super(x, y, 0);
    }

    protected ViewPosition(int x, int y, int z)
    {
        super(x, y, z);
    }

    @Override
    public int getX()
    {
        return this.allbinaryLayer.getXP();
    }

    @Override
    public int getY()
    {
        return this.allbinaryLayer.getYP();
    }

    @Override
    public int getZ()
    {
        return this.allbinaryLayer.getZP();
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
        return (int) (this.getZ() + this.allbinaryLayer.getDepth());
    }

    /*
     * private AllBinaryLayer getAllbinaryLayer() { return allbinaryLayer; }
     */

    public void setAllbinaryLayer(AllBinaryLayer allbinaryLayer)
    {
        this.allbinaryLayer = allbinaryLayer;
    }
}
