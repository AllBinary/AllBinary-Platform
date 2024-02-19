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

import org.allbinary.util.CircularIndexUtil;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorSetUtil;
import org.allbinary.logic.math.PrimitiveIntUtil;

public class VectorAnimation extends IndexedAnimation
    implements VectorAnimationInterface
{
    private int currentPoints[][][];

    private BasicColor basicColor;

    //private int color;
    
    private CircularIndexUtil circularIndexUtil;

    protected final BasicColorSetUtil basicColorUtil = 
        BasicColorSetUtil.getInstance();
    
    public VectorAnimation(final int currentPoints[][][], final BasicColor basicColor, final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);
        
        this.setPoints(currentPoints);

        this.setBasicColor(basicColor);
    }

    public VectorAnimation(final int currentPoints[][], final BasicColor basicColor, final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);
        
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

    public void nextFrame()
    {
        this.circularIndexUtil.next();
    }

    public void previousFrame()
    {
        this.circularIndexUtil.previous();
    }

    protected void paintVectors(final Graphics graphics, final int x, final int y)
    {
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

            for (int index = size - 2; --index >= 0;)
            {
                nextPoint = currentPointsFrame[index];
                point = currentPointsFrame[index + 1];
                
                nextPointX = nextPoint[0];
                nextPointY = nextPoint[1];
                
                if (nextPointX != 1000)
                {
                    // LogUtil.put(LogFactory.getInstance("Next Line: x1: " +
                    // this.currentPoints[this.currentFrame][index][0] + x +
                    // " y1: "
                    // + this.currentPoints[this.currentFrame][index][1] + y,
                    // this,
                    // "paint"));

                    graphics.drawLine(point[0] + x, point[1] + y,
                            nextPointX + x, nextPointY + y);
                }
                else
                {
                    index--;
                }
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "paintVectors", e));
        }

    }

    public void paint(Graphics graphics, int x, int y)
    {
        this.basicSetColorUtil.setBasicColor(graphics, basicColor);

        this.paintVectors(graphics, x, y);
    }

    public int getFrame()
    {
        return this.circularIndexUtil.getIndex();
    }

    public void setFrame(int index)
    {
        this.circularIndexUtil.setIndex(index);
    }

    public int[][] getPoints(int frame)
    {
        return currentPoints[frame];
    }

    public void setPoints(int[][][] currentPoints)
    {
        this.currentPoints = currentPoints;
        this.circularIndexUtil = CircularIndexUtil.getInstance(currentPoints.length);
    }
}
