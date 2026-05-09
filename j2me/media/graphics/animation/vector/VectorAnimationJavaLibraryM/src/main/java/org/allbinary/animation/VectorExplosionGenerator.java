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
import org.allbinary.util.BasicArrayListD;
import org.allbinary.util.BasicArrayListS;

public class VectorExplosionGenerator
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final VectorExplosionGenerator instance = new VectorExplosionGenerator();

    public static VectorExplosionGenerator getInstance()
    {
        return VectorExplosionGenerator.instance;
    }
    
    public final VectorExplosionType RANDOM = new VectorExplosionType();
    public final VectorExplosionType ROTATION = new VectorExplosionType();

    private final VectorAnimationUtil vectorAnimationUtil = VectorAnimationUtil.getInstance();
    
    private VectorExplosionGenerator()
    {
    }

    public BasicArrayList createList(final BasicArrayList list, final int howMuch, final VectorExplosionType type) throws Exception
    {
        GPoint point;
        final int size = list.size();
        final int[][] points = new int[size][2];
        for (int index = 0; index < size; index++)
        {
            point = (GPoint) list.objectArray[index];
            points[index][0] = point.getX();
            points[index][1] = point.getY();
        }
        return createListFromPoints(list, points, howMuch, type);
    }

    public int[][][] getInstance(final int[][] points, final int howMuch, final VectorExplosionType type) throws Exception
    {

        final BasicArrayList pointsBasicArrayList = this.getInstanceStartFrame(points, howMuch, type, true);

        final BasicArrayList tempBasicArrayList = (BasicArrayList) pointsBasicArrayList.objectArray[0];

        final int[][][] newPoints = this.vectorAnimationUtil
                .toAnimationArrayFromListOfPointListWithPointsPerFrame(
                        pointsBasicArrayList, tempBasicArrayList.size());

        return newPoints;
    }

    public BasicArrayList getInstanceStartFrame(final int[][] points, final int howMuch, final VectorExplosionType type, final boolean startFrame) throws Exception
    {
        try
        {
            final BasicArrayList pointsBasicArrayList = new BasicArrayListS(howMuch);

            pointsBasicArrayList.add(createPointsBasicArrayList(points));

            int frameIndex = 0;

            BasicArrayList tempBasicArrayList;
            BasicArrayList pointBasicArrayList;
            while (frameIndex < howMuch)
            {
                tempBasicArrayList = (BasicArrayList) pointsBasicArrayList.objectArray[frameIndex];

                pointBasicArrayList = this.createListFromPoints(tempBasicArrayList, points, howMuch, type);
                // this.logUtil.putF("Adding Point BasicArrayList commonStrings.TOTAL_LABEL + pointBasicArrayList.size() + " should be == " + firstPointBasicArrayList.size(), this, this.commonStrings.GET_INSTANCE);
                // this.logUtil.putF("Point BasicArrayList: " + pointBasicArrayList.toString(), this, this.commonStrings.GET_INSTANCE);
                pointsBasicArrayList.add(pointBasicArrayList);

                frameIndex++;
            }

            if (!startFrame)
            {
                pointsBasicArrayList.removeAt(0);
            }

            // this.logUtil.putF(IntArrayUtil.toString(newPoints), this, this.commonStrings.GET_INSTANCE);
            return pointsBasicArrayList;

        }
        catch (Exception e)
        {
            //this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.GET_INSTANCE, e);
            throw e;
        }
    }

    private final RandomRotationFactory randomRotationFactory = RandomRotationFactory.getInstance();

    private BasicArrayList createListFromPoints(final BasicArrayList tempBasicArrayList, final int[][] points, final int howMuch, final VectorExplosionType type)
            throws Exception
    {
        int index = 0;
        final BasicArrayList pointBasicArrayList = new BasicArrayListD();

        BasicArrayList sectionBasicArrayList;
        // && index < tempBasicArrayList.size()
        while (index < points.length)
        {
            sectionBasicArrayList = new BasicArrayListD();

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

            sectionBasicArrayList = RandomTranslation.getInstance(sectionBasicArrayList, howMuch);

            if (type == this.ROTATION)
            {
                sectionBasicArrayList = randomRotationFactory.getInstanceList(sectionBasicArrayList, howMuch);
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

    private BasicArrayList createPointsBasicArrayList(final int[][] points)
            throws Exception
    {
        if (points.length == 0)
        {
            throw new Exception("Not Points Provided");
        }

        final BasicArrayList firstPointBasicArrayList = new BasicArrayListS(points.length);

        final PointFactory pointFactory = PointFactory.getInstance();
        for (int index = 0; index < points.length; index++)
        {
            firstPointBasicArrayList.add(pointFactory.createXY(points[index][0], points[index][1]));
        }
        return firstPointBasicArrayList;
    }
}