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

public class PointFactory
{
    private static Point[][] pointArray = new Point[3000][3000];
    
    private PointFactory()
    {
    }
    
    public static Point getInstance(int x, int y)
    {
        Point point = pointArray[x][y];

        if(point == null)
        {
            point = pointArray[x][y] = new Point(x, y);
        }
        
        return point;
    }
}
