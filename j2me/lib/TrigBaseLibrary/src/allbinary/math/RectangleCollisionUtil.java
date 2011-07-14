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
package allbinary.math;

public class RectangleCollisionUtil
{

    private RectangleCollisionUtil()
    {
    }

    public static boolean isCollision(int rectX1, int rectY1, int rectX2, int rectY2,
            int rect2X1, int rect2Y1, int rect2X2, int rect2Y2)
    {
        if (rect2X1 >= rectX2
                || rect2Y1 >= rectY2
                || rect2X2 <= rectX1
                || rect2Y2 <= rectY1)
        {
            return false;
        } else
        {
            return true;
        }
        /*
        if(rect2X1 > rectX1 && rect2X1 <= rectX2)
        {
        if(rect2Y1 >= rectY1 && rect2Y1 <= rectY2)
        {
        return true;
        }
        }

        if(rect2X2 > rectX1 && rect2X2 <= rectX2)
        {
        if(rect2Y1 >= rectY1 && rect2Y1 <= rectY2)
        {
        return true;
        }
        }

        if(rect2X1 > rectX1 && rect2X1 <= rectX2)
        {
        if(rect2Y2 >= rectY1 && rect2Y2 <= rectY2)
        {
        return true;
        }
        }

        if(rect2X2 > rectX1 && rect2X2 <= rectX2)
        {
        if(rect2Y2 >= rectY1 && rect2Y2 <= rectY2)
        {
        return true;
        }
        }

        if(rectX1 > rect2X1 && rectX1 <= rect2X2)
        {
        if(rectY1 >= rect2Y1 && rectY1 <= rect2Y2)
        {
        return true;
        }
        }

        if(rectX2 > rect2X1 && rectX2 <= rect2X2)
        {
        if(rectY1 >= rect2Y1 && rectY1 <= rect2Y2)
        {
        return true;
        }
        }

        if(rectX1 > rect2X1 && rectX1 <= rect2X2)
        {
        if(rectY2 >= rect2Y1 && rectY2 <= rect2Y2)
        {
        return true;
        }
        }

        if(rectX2 > rect2X1 && rectX2 <= rect2X2)
        {
        if(rectY2 >= rect2Y1 && rectY2 <= rect2Y2)
        {
        return true;
        }
        }

        return false;
         **/

        /*
        return (Math.abs(a.getXPos() - b.getXPos()) +
        Math.abs(a.getYPos() - b.getYPos())) <
        ((a.getSize() >> 1) + (b.getSize() >> 1));
         */
    }

    public static boolean isInside(int rectX1, int rectY1, int rectX2, int rectY2,
            int x, int y)
    {
        if (x >= rectX2 || y >= rectY2 || x <= rectX1 || y <= rectY1)
        {
            return false;
        } else
        {
            return true;
        }
    }

}
