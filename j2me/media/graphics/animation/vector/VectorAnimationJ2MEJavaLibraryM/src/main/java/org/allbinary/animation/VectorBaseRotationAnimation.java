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

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorSetUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.math.AngleInfo;
import org.allbinary.util.CircularIndexUtil;

public class VectorBaseRotationAnimation 
    extends RotationAnimation
    implements VectorAnimationInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private int[][][] currentPoints;

    private BasicColor basicColor;

    protected final BasicColorSetUtil basicColorUtil = BasicColorSetUtil.getInstance();
    
    public VectorBaseRotationAnimation(final AngleInfo angleInfo, final int[][][] currentPoints, final BasicColor basicColor, final AnimationBehavior animationBehavior)
    {
        super(angleInfo, animationBehavior);
        
        this.setPoints(currentPoints);
        this.setBasicColor(basicColor);
    }

    public VectorBaseRotationAnimation(final AngleInfo angleInfo, final int[][] currentPoints, final BasicColor basicColor, final AnimationBehavior animationBehavior)
    {
        super(angleInfo, animationBehavior);
        
        this.setPoints(new int[1][currentPoints.length][2]);

        int size = currentPoints.length;
        for (int index = 0; index < size; index++)
        {
            this.currentPoints[0][index][0] = currentPoints[index][0];
            this.currentPoints[0][index][1] = currentPoints[index][1];
        }
        
        this.setBasicColor(basicColor);
    }

    public int getAnimationSize() throws Exception
    {
        return this.getSize();
    }
    
    public BasicColor getBasicColor()
    {
        return this.basicColor;
    }

    public void setBasicColor(final BasicColor basicColor)
    {
        this.basicColor = basicColor;
        //this.color = this.basicColor.intValue();
    }

    public int getFrame()
    {
        return this.circularIndexUtil.getIndex();
    }

    public void setFrame(int index)
    {
        this.circularIndexUtil.setIndex(index);
    }

    public void nextFrame()
    {
        this.circularIndexUtil.next();
    }

    public void previousFrame()
    {
        this.circularIndexUtil.previous();
    }

    public int getSize()
    {
        return this.currentPoints.length;
    }

    public void setSequence(final int[] sequence)
    {

    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    public void paint(final Graphics graphics, final int x, final int y)
    {
        this.basicSetColorUtil.setBasicColor(graphics, basicColor);

        try
        {
            /*
             * if (this.currentFrame >= this.currentPoints.length) {
             * this.currentFrame = 0; }
             */

            int nextPointX = 0;
            int nextPointY = 0;
            int[] nextPoint;
            int[] point;
            
            final int[][] currentPointsFrame = this.currentPoints[this.circularIndexUtil.getIndex()];
            final int size = currentPointsFrame.length;

            for (int index = size - 3; index >= 0; index--)
            {
                nextPoint = currentPointsFrame[index];
                point = currentPointsFrame[index + 1];
                
                nextPointX = nextPoint[0];
                nextPointY = nextPoint[1];
                
                if (nextPointX != 1000)
                {
                    // logUtil.put("Next Line: x1: " +
                    // this.currentPoints[this.currentFrame][index][0] + x +
                    // " y1: "
                    // + this.currentPoints[this.currentFrame][index][1] + y,
                    // this,
                    // canvasStrings.PAINT);

                    graphics.drawLine(point[0] + x, point[1] + y, nextPointX + x, nextPointY + y);
                }
                else
                {
                    index--;
                }
            }
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, "paintVectors", e);
        }
    }
    
    public int[][] getPoints(final int frame)
    {
        return currentPoints[frame];
    }

    public void setPoints(final int[][][] currentPoints)
    {
        this.currentPoints = currentPoints;
        this.circularIndexUtil = CircularIndexUtil.getInstance(this.currentPoints.length);
    }
    
}
