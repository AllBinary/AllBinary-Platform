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

import javax.microedition.lcdui.Graphics;

import org.allbinary.util.CircularIndexUtil;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.color.BasicColorSetUtil;
import allbinary.logic.math.PrimitiveIntUtil;

public class VectorAnimation extends IndexedAnimation
    implements VectorAnimationInterface
{
    private int currentPoints[][][];

    private BasicColor basicColor;

    //private int color;
    
    private CircularIndexUtil circularIndexUtil;

    protected final BasicColorSetUtil basicColorUtil = 
        BasicColorSetUtil.getInstance();
    
    public VectorAnimation(int currentPoints[][][], BasicColor basicColor)
    {
        this.setPoints(currentPoints);

        this.setBasicColor(basicColor);
    }

    public VectorAnimation(int currentPoints[][], BasicColor basicColor)
    {
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

    public void setBasicColor(BasicColor basicColor)
    {
        this.basicColor = basicColor;
        //this.color = this.basicColor.intValue();
    }

    public int getSize()
    {
        return this.currentPoints.length;
    }

    public void setSequence(int[] sequence)
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

    protected void paintVectors(Graphics graphics, int x, int y)
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
            
            int[][] currentPointsFrame = this.currentPoints[this.circularIndexUtil.getIndex()];
            int size = currentPointsFrame.length;

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
        this.basicColorUtil.setBasicColor(graphics, basicColor);

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
