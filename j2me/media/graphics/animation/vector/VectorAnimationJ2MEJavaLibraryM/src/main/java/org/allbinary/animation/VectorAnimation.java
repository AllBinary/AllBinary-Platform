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

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.NullImage;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.util.CircularIndexUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

@JsType
public class VectorAnimation extends IndexedAnimation
    implements VectorAnimationInterface
{
    @JsMethod
    public static VectorAnimation createVectorAnimation(final int[][] currentPoints2, final BasicColor basicColor, final AnimationBehavior animationBehavior)
    {
        final int[][][] currentPoints = new int[1][currentPoints2.length][2];

        int size = currentPoints2.length;
        for (int index = 0; index < size; index++)
        {
            currentPoints[0][index][0] = currentPoints2[index][0];
            currentPoints[0][index][1] = currentPoints2[index][1];
        }

        return new VectorAnimation(currentPoints, basicColor, animationBehavior);
    }

    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    private int[][][] currentPoints = NullUtil.getInstance().NULL_INT_ARRAY_ARRAY_ARRAY;
    
    private CircularIndexUtil circularIndexUtil = CircularIndexUtil.createInstance(0);
    
    @JsConstructor
    public VectorAnimation(final int[][][] currentPoints, final BasicColor basicColor, final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);
        
        this.setPoints(currentPoints);

        this.setBasicColorP(basicColor);
    }

    @Override
    @JsMethod
    public void setBasicColorP(final BasicColor basicColor) {
        
        boolean changed = false;
        if(this.getBasicColorP() == null || this.getBasicColorP().intValue() != basicColor.intValue()) {
            changed = true;
        }
        
        super.setBasicColorP(basicColor);
        
        if(changed) {
//            this.setColorProcessor = SetColorProcessor.getInstance();
            this.updateModifiers();
        }
    }

    @Override
    @JsMethod
    public void changeBasicColor(final BasicColor basicColor) {
        
        boolean changed = false;
        //if(this.getBasicColorP() == null || this.getBasicColorP().intValue() != basicColor.intValue()) {
        if(this.getChangeBasicColor() == null || this.getChangeBasicColor().intValue() != basicColor.intValue()) {
            changed = true;
        }
        
        super.changeBasicColor(basicColor);
        
        if(changed) {
//            this.changeColorProcessor = ChangeColorProcessor.getInstance();
            this.updateModifiers();
        }
    }
    
    @Override
    @JsMethod
    public void setAlpha(final int alpha) {
        
        boolean changed = false;
        if(this.alphaP != alpha) {
            changed = true;
        }
        
        super.setAlpha(alpha);

        if(changed) {
//            this.alphaProcessor = AlphaProcessor.getInstance();
            this.updateModifiers();
        }
    }

    @Override
    @JsMethod
    public void setScale(final float scaleX, final float scaleY) {
        //this.logUtil.putF(new StringMaker().append("scaleX: ").append(scaleX).append("scaleY: ").append(scaleY).toString(), this, "setScale");
    }

    @Override
    @JsMethod
    public void setMaxScale(final float maxScaleX, final float maxScaleY) {
    }
    
    @JsMethod
    private void updateModifiers() {
    }
    
    @Override
    @JsMethod
    public int getAnimationSize() throws Exception
    {
        return this.getSize();
    }

    @Override
    @JsMethod
    public int getSize()
    {
        return this.currentPoints.length;
    }
    
    @Override
    @JsMethod
    public void setSequence(final int[] sequence)
    {

    }

    @Override
    @JsMethod
    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    @Override
    @JsMethod
    public void nextFrame()
    {
        this.circularIndexUtil.next();
    }

    @Override
    @JsMethod
    public void previousFrame()
    {
        this.circularIndexUtil.previous();
    }

    @JsMethod
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

            int index = size - 2;
            while (--index >= 0)
            {
                nextPoint = currentPointsFrame[index];
                point = currentPointsFrame[index + 1];
                
                nextPointX = nextPoint[0];
                nextPointY = nextPoint[1];
                
                if (nextPointX != 1000)
                {
                    // this.logUtil.putF("Next Line: x1: " +
                    // this.currentPoints[this.currentFrame][index][0] + x +
                    // " y1: "
                    // + this.currentPoints[this.currentFrame][index][1] + y, // this, // canvasStrings.PAINT);

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
            this.logUtil.put(this.commonStrings.EXCEPTION, this, "paintVectors", e);
        }

    }

    @Override
    @JsMethod
    public void paintXY(Graphics graphics, int x, int y)
    {
        this.basicSetColorUtil.setBasicColorP(graphics, this.basicColor);

        this.paintVectors(graphics, x, y);
    }

    @Override
    @JsMethod
    public int getFrame()
    {
        return this.circularIndexUtil.getIndex();
    }

    @Override
    @JsMethod
    public void setFrame(int index)
    {
        this.circularIndexUtil.setIndex(index);
    }

    @Override
    @JsMethod
    public int[][] getPoints(int frame)
    {
        return this.currentPoints[frame];
    }

    @JsMethod
    public void setPoints(int[][][] currentPoints)
    {
        this.currentPoints = currentPoints;
        this.circularIndexUtil = CircularIndexUtil.createInstance(currentPoints.length);
    }
}
