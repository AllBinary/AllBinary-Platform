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

import org.allbinary.math.RectangleCollisionUtil;
import org.allbinary.util.BasicArrayList;

import org.allbinary.graphics.GPoint;

public class RectangleCollisionPointUtil
{
    
    //private static final RectangleCollisionPointUtil instance = new RectangleCollisionPointUtil();

    private RectangleCollisionPointUtil()
    {
    }

    public static boolean allPointsInside(int rectX1, int rectY1, int rectX2, int rectY2, BasicArrayList list)
    {
        final RectangleCollisionUtil rectangleCollisionUtil = RectangleCollisionUtil.getInstance();
        
        GPoint point;

        for (int index = list.size(); --index >= 0;)
        {
            point = (GPoint) list.objectArray[index];

            if (!rectangleCollisionUtil.isInside(rectX1, rectY1, rectX2, rectY2, point.getX(), point.getY()))
            {
                return false;
            }
        }

        return true;
    }

    public static boolean allPointsInside(int rectX1, int rectY1, int rectX2, int rectY2, BasicArrayList list, int xCellSize, int yCellSize)
    {
        final RectangleCollisionUtil rectangleCollisionUtil = RectangleCollisionUtil.getInstance();
        
        GPoint point;

        for (int index = list.size(); --index >= 0;)
        {
            point = (GPoint) list.objectArray[index];

            if (!rectangleCollisionUtil.isInside(rectX1, rectY1, rectX2, rectY2, point.getX() * xCellSize, point.getY() * yCellSize))
            {
                return false;
            }
        }

        return true;
    }
}
