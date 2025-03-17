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
package org.allbinary.math;

import org.allbinary.graphics.GPoint;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.math.MathUtil;

public class LayerDistanceUtil
{
    private static final LayerDistanceUtil instance = new LayerDistanceUtil();

    /**
     * @return the instance
     */
    public static LayerDistanceUtil getInstance() {
        return instance;
    }
    
    private final MathUtil mathUtil = MathUtil.getInstance();

    public int getDistance(final AllBinaryLayer layerInterface, final AllBinaryLayer layerInterface2)
    {
        final int dx = (layerInterface.getX() + layerInterface.getHalfWidth()) -
            (layerInterface2.getX() + layerInterface2.getHalfWidth());
        final int dy = (layerInterface.getY() + layerInterface.getHalfHeight()) -
            (layerInterface2.getY() + layerInterface2.getHalfHeight());

        return (int) mathUtil.sqrt((dx * dx) + (dy * dy));
    }

    public int getDistance3D(final AllBinaryLayer layerInterface, final AllBinaryLayer layerInterface2)
    {
        final int dx = (layerInterface.getX() + layerInterface.getHalfWidth()) -
            (layerInterface2.getX() + layerInterface2.getHalfWidth());
        final int dy = (layerInterface.getY() + layerInterface.getHalfHeight()) -
            (layerInterface2.getY() + layerInterface2.getHalfHeight());
        final int dz = (layerInterface.getZ() + layerInterface.getHalfDepth()) -
            (layerInterface2.getZ() + layerInterface2.getHalfDepth());

        return (int) mathUtil.sqrt((dx * dx) + (dy * dy) + (dz * dz));
    }
    
    public int getDistance(final AllBinaryLayer layerInterface, final GPoint point)
    {
        final int dx = layerInterface.getX() - point.getX();
        final int dy = layerInterface.getY() - point.getY();

        return (int) mathUtil.sqrt((dx * dx) + (dy * dy));
    }
}
