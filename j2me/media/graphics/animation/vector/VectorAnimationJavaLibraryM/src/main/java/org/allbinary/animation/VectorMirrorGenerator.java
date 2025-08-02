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

import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.pipeline.BasicGraphicsPipeline;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class VectorMirrorGenerator
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final VectorMirrorGenerator instance = new VectorMirrorGenerator();

    public static VectorMirrorGenerator getInstance()
    {
        return instance;
    }

    private final VectorAnimationUtil vectorAnimationUtil = VectorAnimationUtil.getInstance();
    
    private VectorMirrorGenerator()
    {
    }

    public int[][][] getInstance(int[][][] points, int width) // throws
                                                                     // Exception
    {
        try
        {
            final PointFactory pointFactory = PointFactory.getInstance();
            
            BasicArrayList pointBasicArrayList;
            int[][] nextPoints;
            int size2;
            final int size = points.length;
            final BasicArrayList pointsBasicArrayList = new BasicArrayList(size);
            
            for (int frame = 0; frame < size; frame++)
            {
                nextPoints = points[frame];
                size2 = nextPoints.length;
                pointBasicArrayList = new BasicArrayList(size2);

                for (int index = 0; index < size2; index++)
                {
                    pointBasicArrayList.add(
                            pointFactory.getInstance(
                            nextPoints[index][0], nextPoints[index][1]));
                }

                pointsBasicArrayList.add(pointBasicArrayList);
            }

            for (int index = 0; index < points.length; index++)
            {
                pointsBasicArrayList.add(getInstance(
                        (BasicArrayList) pointsBasicArrayList.objectArray[index], width));
            }

            int[][][] newPoints = vectorAnimationUtil.toAnimationArrayFromBasicArrayListOfPointBasicArrayList(
                    pointsBasicArrayList);

            // logUtil.put(IntArrayUtil.toString(newPoints), this, commonStrings.GET_INSTANCE);
            return newPoints;

        } catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE, e);
            // throw e;
            return NullUtil.getInstance().NULL_INT_ARRAY_ARRAY_ARRAY;
        }
    }

    public BasicArrayList getInstance(BasicArrayList pointBasicArrayList, int width)
            throws Exception
    {
        BasicGraphicsPipeline graphicsPipe = new BasicGraphicsPipeline(pointBasicArrayList);

        graphicsPipe.createMatrix();
        graphicsPipe.mirror(width);

        return graphicsPipe.getMatrix();
    }
}
