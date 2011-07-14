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
package allbinary.animation.special;

import javax.microedition.lcdui.Graphics;

import allbinary.animation.IndexedAnimation;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.color.BasicColorFactory;
import allbinary.graphics.color.BasicColorSetUtil;
import allbinary.graphics.color.BasicColorUtilFactory;
import allbinary.logic.math.PrimitiveIntUtil;

public class TitleAnimation extends SpecialAnimation 
{
    private final int loopCountTotal;
    
    private final BasicColor[] basicColorArray;
    private final int[] dxArray;
    private final int[] dyArray;
    private final int width;
    // private final int HEIGHT = 45;

    private final IndexedAnimation[] animationInterfaceArray;
    
    private final int timePerFrame;

    private final int size;
    private final int y;

    private int loopCount;
    private long lastFrameStartTime;
    
    protected final BasicColorSetUtil basicColorUtil = 
        BasicColorUtilFactory.getInstance();
    
    public TitleAnimation(IndexedAnimation[] animationInterfaceArray,
            BasicColor[] basicColorArray, int[] dxArray, int[] dyArray)
    {
        this(animationInterfaceArray, basicColorArray, dxArray, dyArray, 0, Integer.MIN_VALUE, 250, 1);
    }

    public TitleAnimation(IndexedAnimation[] animationInterfaceArray,
            BasicColor[] basicColorArray, int[] dxArray, int[] dyArray, int y, int width)
    {
        this(animationInterfaceArray, basicColorArray, dxArray, dyArray, y, width, 250, 1);
    }
    
    public TitleAnimation(IndexedAnimation[] animationInterfaceArray,
            BasicColor[] basicColorArray, int[] dxArray, int[] dyArray, int y, int width,
            int timePerFrame, int loopCountTotal)
    {
        this.lastFrameStartTime = System.currentTimeMillis();

        this.animationInterfaceArray = animationInterfaceArray;

        this.size = this.animationInterfaceArray.length;

        this.basicColorArray = basicColorArray;
        this.dxArray = dxArray;
        this.dyArray = dyArray;

        this.y = y;

        this.width = width;

        this.timePerFrame = timePerFrame;

        this.loopCountTotal = loopCountTotal;
        
        this.reset();
    }

    public void nextFrame()
    {
        long currentTime = System.currentTimeMillis();
        long totalTimeElapsed = currentTime - lastFrameStartTime;

            // If Frame is up long enough
            if (totalTimeElapsed > timePerFrame)
            {
                this.previousFrame();
                lastFrameStartTime = currentTime;
            }
            
            if(this.animationInterfaceArray[0].getFrame() == 0)
            {
                loopCount++;
            }

        /*
         * if (this.isPainted) { if (this.animationInterfaceArray[0].getFrame()
         * > 0) { // If Frame is up long enough if (totalTimeElapsed >
         * timePerFrame) { this.previousFrame(); lastFrameStartTime =
         * currentTime; } } else { this.isPainted = false; } }
         */
    }

    public boolean isComplete()
    {
        if(loopCountTotal == -1 || loopCount < loopCountTotal || this.getFrame() != 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void setSequence(int[] sequence)
    {

    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    public int getSize()
    {
        return this.animationInterfaceArray[0].getSize();
    }

    public int getFrame()
    {
        return this.animationInterfaceArray[0].getFrame();
    }

    public void setFrame(int frame)
    {
        for (int index = 0; index < size; index++)
        {
            this.animationInterfaceArray[index].setFrame(frame);
        }
    }

    public void setLastFrame()
    {
        this.setFrame(this.getSize() - 1);
    }
    
    public void setLoopCount(int loopCount)
    {
        this.loopCount = loopCount;
    }

    public int getLoopCount()
    {
        return loopCount;
    }
    
    public void reset()
    {
        // this.setFrame(0);
        this.setLastFrame();
        loopCount = 0;
    }

    //this is called from nextFrame. Logical? probably not.
    public void previousFrame()
    {
        //ForcedLogUtil.log(NotImplemented.NAME, this);
        for (int index = 0; index < size; index++)
        {
            this.animationInterfaceArray[index].previousFrame();
        }
    }

    public void paint(Graphics graphics, int frame, int x, int y)
    {
        this.setFrame(frame);
        this.paint(graphics, x, y);
    }

    private final BasicColor CLEAR_COLOR = BasicColorFactory.getInstance().CLEAR_COLOR;
    
    public void paint(Graphics graphics, int ax, int ay)
    {
        int x = 0;
        
        if(this.width != Integer.MIN_VALUE)
        {
            x = ((graphics.getClipWidth() - this.width) / 2);
        }
        
        int deltaX;
        int deltaY;

        for (int index = 0; index < size; index++)
        {
            deltaX = this.dxArray[index] + x;
            deltaY = this.dyArray[index] + y;
            
            if (this.basicColorArray[index] != CLEAR_COLOR)
            {
                this.basicColorUtil.setBasicColor(graphics, this.basicColorArray[index]);
            }
            //LogUtil.put(LogFactory.getInstance("deltaX: " + deltaX + " " + x, this, "paint"));
            this.animationInterfaceArray[index].paint(graphics, deltaX, deltaY);
        }
    }
}