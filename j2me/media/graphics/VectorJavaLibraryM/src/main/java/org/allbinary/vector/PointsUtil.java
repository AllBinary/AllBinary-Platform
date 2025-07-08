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

package org.allbinary.vector;

public class PointsUtil
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final PointsUtil instance = new PointsUtil();

    public static PointsUtil getInstance()
    {
        return instance;
    }
    
    public int[][][] adjust(int[][][] points, int x, int y)
    {
        // StringMaker stringBuffer = new StringMaker();
        int[][][] newPoints = new int[points.length][0][0];
        // Frame
        for (int index = 0; index < points.length; index++)
        {
            newPoints[index] = new int[points[index].length][2];

            // Point
            for (int index2 = 0; index2 < points[index].length; index2++)
            {
                if (points[index][index2][0] != 1000)
                {
                    newPoints[index][index2][0] = points[index][index2][0] + x;
                    newPoints[index][index2][1] = points[index][index2][1] + y;
                    /*
                    stringBuffer.append("{");
                    stringBuffer.append(newPoints[index][index2][0]);
                    stringBuffer.append(commonStrings.COMMA_SEP);
                    stringBuffer.append(newPoints[index][index2][1]);
                    stringBuffer.append("}, ");
                    */
                } else
                {
                    newPoints[index][index2][0] = 1000;
                    newPoints[index][index2][1] = 1000;
                }
            }
        }

        // logUtil.put("New Points: " + stringBuffer, this, commonStrings.GET_INSTANCE);
        return newPoints;
    }

    public int[][] adjust(int[][] points, int both)
    {
        return adjust(points, both, both);
    }

    public int[][] adjust(int[][] points, int x, int y)
    {
        int[][] newPoints = new int[points.length][2];

        for (int index2 = 0; index2 < points.length; index2++)
        {
            if (points[index2][0] != 1000)
            {
                newPoints[index2][0] = points[index2][0] + x;
                newPoints[index2][1] = points[index2][1] + y;
            } else
            {
                newPoints[index2][0] = 1000;
                newPoints[index2][1] = 1000;
            }
        }

        return newPoints;
    }
}
