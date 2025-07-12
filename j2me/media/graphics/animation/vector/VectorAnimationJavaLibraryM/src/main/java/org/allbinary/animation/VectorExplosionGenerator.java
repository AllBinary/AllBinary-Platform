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
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.pipeline.RandomRotationFactory;
import org.allbinary.graphics.pipeline.RandomTranslation;
import org.allbinary.util.BasicArrayList;

public class VectorExplosionGenerator
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final VectorExplosionGenerator instance = new VectorExplosionGenerator();

    public static VectorExplosionGenerator getInstance()
    {
        return instance;
    }
    
    public final VectorExplosionType RANDOM = new VectorExplosionType();
    public final VectorExplosionType ROTATION = new VectorExplosionType();

    private final VectorAnimationUtil vectorAnimationUtil = VectorAnimationUtil.getInstance();
    
    private VectorExplosionGenerator()
    {
    }

    public BasicArrayList getInstance(BasicArrayList list, int howMuch,
            VectorExplosionType type) throws Exception
    {
        int size = list.size();
        int[][] points = new int[size][2];
        for (int index = 0; index < size; index++)
        {
            GPoint point = (GPoint) list.objectArray[index];
            points[index][0] = point.getX();
            points[index][1] = point.getY();
        }
        return getInstance(list, points, howMuch, type);
    }

    public int[][][] getInstance(int[][] points, int howMuch,
            VectorExplosionType type) throws Exception
    {

        BasicArrayList pointsBasicArrayList = getInstance(points, howMuch, type, true);

        BasicArrayList tempBasicArrayList = (BasicArrayList) pointsBasicArrayList.objectArray[0];

        int[][][] newPoints = vectorAnimationUtil
                .toAnimationArrayFromBasicArrayListOfPointBasicArrayList(
                        pointsBasicArrayList, tempBasicArrayList.size());

        return newPoints;
    }

    public BasicArrayList getInstance(int[][] points, int howMuch,
            VectorExplosionType type, boolean startFrame) throws Exception
    {
        try
        {
            BasicArrayList pointsBasicArrayList = new BasicArrayList(howMuch);

            pointsBasicArrayList.add(createPointsBasicArrayList(points));

            int frameIndex = 0;

            while (frameIndex < howMuch)
            {
                BasicArrayList tempBasicArrayList = (BasicArrayList) pointsBasicArrayList.objectArray[frameIndex];

                BasicArrayList pointBasicArrayList = getInstance(
                        tempBasicArrayList, points, howMuch, type);
                // logUtil.put("Adding Point BasicArrayList commonStrings.TOTAL_LABEL + pointBasicArrayList.size() + " should be == " + firstPointBasicArrayList.size(), this, commonStrings.GET_INSTANCE);
                // logUtil.put("Point BasicArrayList: " + pointBasicArrayList.toString(), this, commonStrings.GET_INSTANCE);
                pointsBasicArrayList.add(pointBasicArrayList);

                frameIndex++;
            }

            if (!startFrame)
            {
                pointsBasicArrayList.remove(0);
            }

            // logUtil.put(IntArrayUtil.toString(newPoints), this, commonStrings.GET_INSTANCE);
            return pointsBasicArrayList;

        }
        catch (Exception e)
        {
            //logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE, e);
            throw e;
        }
    }

    private final RandomRotationFactory randomRotationFactory = RandomRotationFactory
            .getInstance();

    private BasicArrayList getInstance(BasicArrayList tempBasicArrayList,
            int[][] points, int howMuch, VectorExplosionType type)
            throws Exception
    {
        int index = 0;
        BasicArrayList pointBasicArrayList = new BasicArrayList();

        // && index < tempBasicArrayList.size()
        while (index < points.length)
        {
            BasicArrayList sectionBasicArrayList = new BasicArrayList();

            // && index < tempBasicArrayList.size()
            while (points[index][0] != 1000)
            {
                sectionBasicArrayList.add(tempBasicArrayList.objectArray[index]);
                index++;

                if (index >= points.length)
                {
                    break;
                }
            }

            /*
             * if(index < points.length && index < tempBasicArrayList.size()) {
             * break; }
             */

            sectionBasicArrayList = RandomTranslation.getInstance(
                    sectionBasicArrayList, howMuch);

            if (type == ROTATION)
            {
                sectionBasicArrayList = randomRotationFactory.getInstance(
                        sectionBasicArrayList, howMuch);
            }

            int size = sectionBasicArrayList.size();
            for (int index2 = 0; index2 < size; index2++)
            {
                pointBasicArrayList.add(sectionBasicArrayList.objectArray[index2]);
            }

            if (index >= points.length)
            {
                break;
            }
            else if (points[index][0] == 1000)
            {
                pointBasicArrayList.add(tempBasicArrayList.objectArray[index]);
                index++;
            }
        }
        return pointBasicArrayList;
    }

    private BasicArrayList createPointsBasicArrayList(int[][] points)
            throws Exception
    {
        if (points.length == 0)
        {
            throw new Exception("Not Points Provided");
        }

        BasicArrayList firstPointBasicArrayList = new BasicArrayList(
                points.length);

        for (int index = 0; index < points.length; index++)
        {
            firstPointBasicArrayList.add(PointFactory.getInstance()
                    .getInstance(points[index][0], points[index][1]));
        }
        return firstPointBasicArrayList;
    }
}