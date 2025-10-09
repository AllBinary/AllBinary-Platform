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
import org.allbinary.math.Angle;
import org.allbinary.math.AngleFactory;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;
import org.allbinary.vector.VectorInfo;

public class VectorRotationGenerator
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final VectorRotationGenerator instance = new VectorRotationGenerator();

    public static VectorRotationGenerator getInstance()
    {
        return instance;
    }
    
    private final VectorAnimationUtil vectorAnimationUtil = VectorAnimationUtil.getInstance();
    
    private VectorRotationGenerator()
    {
    }

    public int[][][] getInstance(VectorInfo vectorRotationInfo)
    // throws Exception
    {
        return getInstance(vectorRotationInfo.getWidth(), vectorRotationInfo.getHeight(),
                vectorRotationInfo.getPoints(), vectorRotationInfo.getTotalFrames());
    }

    private final AngleFactory angleFactory = AngleFactory.getInstance();
    
    public int[][][] getInstance(int width, int height, int[][] points, int frames)
    // throws Exception
    {
        try
        {
            short totalAngle = this.angleFactory.TOTAL_ANGLE;
            
            int angleIncrement = totalAngle / frames;

            BasicArrayList pointsBasicArrayList = new BasicArrayList(
                    totalAngle / angleIncrement);

            BasicArrayList pointBasicArrayList = new BasicArrayList(points.length);

            PointFactory pointFactory = PointFactory.getInstance();
            
            int size = points.length;
            for (int index = 0; index < size; index++)
            {
                pointBasicArrayList.add(
                        pointFactory.getInstance(
                                points[index][0], points[index][1]));
            }

            int index = 0;
            while (index < totalAngle)
            {
                pointsBasicArrayList.add(getInstance(width, height, pointBasicArrayList, this.angleFactory.getInstance(index)));
                index += angleIncrement;
            }

            int[][][] newPoints = vectorAnimationUtil
                    .toAnimationArrayFromBasicArrayListOfPointBasicArrayList(pointsBasicArrayList,
                            pointBasicArrayList.size());
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

    public BasicArrayList getInstance(int width, int height, BasicArrayList pointBasicArrayList,
            Angle angle) throws Exception
    {
        BasicGraphicsPipeline graphicsPipe = new BasicGraphicsPipeline(pointBasicArrayList);
        graphicsPipe.createMatrix();

        graphicsPipe.translate(-(width >> 1), -(height >> 1));
        //graphicsPipe.rotate(Angle.getInstance(angle.getValue()));
        graphicsPipe.rotate(angle);
        graphicsPipe.translate((width >> 1), (height >> 1));

        return graphicsPipe.getMatrix();
    }
}