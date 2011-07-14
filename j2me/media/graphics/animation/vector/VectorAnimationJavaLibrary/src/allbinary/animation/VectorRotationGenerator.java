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
package allbinary.animation;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.graphics.PointFactory;
import allbinary.graphics.pipeline.BasicGraphicsPipeline;
import allbinary.math.Angle;
import allbinary.math.AngleFactory;
import allbinary.vector.VectorInfo;

public class VectorRotationGenerator
{
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
            
            for (int index = 0; index < points.length; index++)
            {
                pointBasicArrayList.add(
                        pointFactory.getInstance(
                                points[index][0], points[index][1]));
            }

            for (int index = 0; index < totalAngle; index += angleIncrement)
            {
                pointsBasicArrayList.add(getInstance(width, height, pointBasicArrayList, this.angleFactory.getInstance(index)));
            }

            int[][][] newPoints = vectorAnimationUtil
                    .toAnimationArrayFromBasicArrayListOfPointBasicArrayList(pointsBasicArrayList,
                            pointBasicArrayList.size());
            // LogUtil.put(LogFactory.getInstance(IntArrayUtil.toString(newPoints),
            // "BabyFighterBasicArrayListData", "static"));
            return newPoints;
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, instance, CommonStrings.getInstance().GET_INSTANCE, e));
            // throw e;
            return null;
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