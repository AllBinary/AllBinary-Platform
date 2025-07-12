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
package org.allbinary.input.automation;

import java.awt.*;

public class PointHelper
{
    private final static double two = 2;
    
    public PointHelper()
    {
    }

    public static Point getCenterPoint(Rectangle rectangle)
    {
        double x = rectangle.getX() + rectangle.getWidth()/two;
        double y = rectangle.getY() + rectangle.getHeight()/two;

        return PointFactory.getInstance((int) x, (int) y);
    }
}
