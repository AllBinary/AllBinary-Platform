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
package org.allbinary.animation;

import org.allbinary.graphics.GPoint;
import org.allbinary.util.BasicArrayList;

public class VectorAnimationUtil
{
    private static final VectorAnimationUtil instance = new VectorAnimationUtil();

    public static VectorAnimationUtil getInstance()
    {
        return instance;
    }

    private VectorAnimationUtil()
    {
    }
    
    public int[][][] toAnimationArrayFromBasicArrayListOfPointBasicArrayList(
            BasicArrayList vector, int pointsPerFrame)
    {
        int size = vector.size();
        int[][][] points = new int[size][pointsPerFrame][2];

        for (int index = 0; index < size; index++)
        {
            BasicArrayList nextBasicArrayList = (BasicArrayList) vector.objectArray[index];
            int[][] frame = toFrameArrayFromPointBasicArrayList(nextBasicArrayList);

            for (int pointIndex = 0; pointIndex < frame.length; pointIndex++)
            {
                points[index][pointIndex][0] = frame[pointIndex][0];
                points[index][pointIndex][1] = frame[pointIndex][1];
            }
        }
        return points;
    }

    public int[][][] toAnimationArrayFromBasicArrayListOfPointBasicArrayList(
            BasicArrayList vector)
    {
        int size = vector.size();
        int[][][] points = new int[size][0][0];

        for (int index = 0; index < size; index++)
        {
            BasicArrayList nextBasicArrayList = (BasicArrayList) vector.objectArray[index];
            int[][] framePoints = toFrameArrayFromPointBasicArrayList(nextBasicArrayList);
            points[index] = new int[framePoints.length][2];
            for (int pointIndex = 0; pointIndex < framePoints.length; pointIndex++)
            {
                // points[index][pointIndex] = new int[2];
                points[index][pointIndex][0] = framePoints[pointIndex][0];
                points[index][pointIndex][1] = framePoints[pointIndex][1];
            }
        }
        return points;
    }

    /*
    public float[][][] toFloatFromIntAnimationArray(
            int[][][] pointIntArray)
    {
        int size = pointIntArray.length;
        float[][][] points = new float[size][0][0];

        for (int index = 0; index < size; index++)
        {
            int[][] framePoints = pointIntArray[index];
            points[index] = new float[framePoints.length][2];
            for (int pointIndex = 0; pointIndex < framePoints.length; pointIndex++)
            {
                // points[index][pointIndex] = new int[2];
                points[index][pointIndex][0] = framePoints[pointIndex][0];
                points[index][pointIndex][1] = framePoints[pointIndex][1];
            }
        }
        return points;
    }

    public float[][][] toFloatFromIntAnimationArray(int[][] pointIntArray)
    {
        float[][][] points = new float[1][0][0];

        points[0] = new float[pointIntArray.length][2];
        for (int pointIndex = 0; pointIndex < pointIntArray.length; pointIndex++)
        {
            // points[index][pointIndex] = new int[2];
            points[0][pointIndex][0] = pointIntArray[pointIndex][0];
            points[0][pointIndex][1] = pointIntArray[pointIndex][1];
        }

        return points;
    }
    */

    public int[][] toFrameArrayFromPointBasicArrayList(
            BasicArrayList list)
    {
        int[][] points = new int[list.size()][2];

        int size = list.size();
        for (int index = 0; index < size; index++)
        {
            GPoint nextPoint = (GPoint) list.objectArray[index];
            points[index][0] = nextPoint.getX();
            points[index][1] = nextPoint.getY();
        }
        return points;
    }
}
