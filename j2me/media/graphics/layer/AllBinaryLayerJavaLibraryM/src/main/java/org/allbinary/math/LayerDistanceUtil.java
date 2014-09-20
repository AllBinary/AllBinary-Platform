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
    public static int getDistance(AllBinaryLayer layerInterface, AllBinaryLayer layerInterface2)
    {
        int dx = (layerInterface.getX() + layerInterface.getHalfWidth()) -
            (layerInterface2.getX() + layerInterface2.getHalfWidth());
        int dy = (layerInterface.getY() + layerInterface.getHalfHeight()) -
            (layerInterface2.getY() + layerInterface2.getHalfHeight());

        return (int) MathUtil.getInstance().sqrt((dx * dx) + (dy * dy));
    }

    public static int getDistance(AllBinaryLayer layerInterface, GPoint point)
    {
        int dx = layerInterface.getX() - point.getX();
        int dy = layerInterface.getY() - point.getY();

        return (int) MathUtil.getInstance().sqrt((dx * dx) + (dy * dy));
    }
}
