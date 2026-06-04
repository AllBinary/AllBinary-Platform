/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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

import org.allbinary.layer.AllBinaryLayer;

/**
 *
 * @author User
 */
public class ViewPosition extends ViewPositionBase {
 
    public static ViewPosition getInstanceD() {
        return new ViewPosition(0, 0, 0);
    }
        
    private AllBinaryLayer allbinaryLayer = AllBinaryLayer.NULL_ALLBINARY_LAYER;

//    protected ViewPosition(int x, int y)
//    {
//        super(x, y, 0);
//    }

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

    @Override
    public int getX2()
    {
        return this.getX() + this.allbinaryLayer.getWidth();
    }

    @Override
    public int getY2()
    {
        return this.getY() + this.allbinaryLayer.getHeight();
    }

    @Override
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
