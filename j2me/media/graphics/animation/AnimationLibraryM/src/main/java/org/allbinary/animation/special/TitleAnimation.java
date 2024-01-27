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
package org.allbinary.animation.special;

import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.animation.IndexedAnimationBehavior;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.logic.math.PrimitiveIntUtil;

public class TitleAnimation extends SpecialAnimation 
{
    
    protected final BasicColor[] basicColorArray;
    protected final int[] dxArray;
    protected final int[] dyArray;
    protected final int width;
    // private final int HEIGHT = 45;

    protected final IndexedAnimation[] animationInterfaceArray;
    
    protected final int size;
    protected final int y;

    private long lastFrameStartTime;

    private final DisplayInfoSingleton displayInfoSingleton = 
            DisplayInfoSingleton.getInstance();
    
    public TitleAnimation(final IndexedAnimation[] animationInterfaceArray,
            final BasicColor[] basicColorArray, final int[] dxArray, final int[] dyArray)
    {
        this(animationInterfaceArray, basicColorArray, dxArray, dyArray, 0, Integer.MIN_VALUE, new IndexedAnimationBehavior(1, 250));
    }

    public TitleAnimation(final IndexedAnimation[] animationInterfaceArray,
            final BasicColor[] basicColorArray, final int[] dxArray, final int[] dyArray, final int y, final int width)
    {
        this(animationInterfaceArray, basicColorArray, dxArray, dyArray, y, width, new IndexedAnimationBehavior(1, 250));
    }
    
    private TitleAnimation(final IndexedAnimation[] animationInterfaceArray,
            final BasicColor[] basicColorArray, final int[] dxArray, final int[] dyArray, final int y, final int width,
            final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);
        
        this.lastFrameStartTime = System.currentTimeMillis();

        this.animationInterfaceArray = animationInterfaceArray;

        this.size = this.animationInterfaceArray.length;

        this.basicColorArray = basicColorArray;
        this.dxArray = dxArray;
        this.dyArray = dyArray;

        this.y = y;

        this.width = width;

        this.reset();
    }

    public void nextFrame()
    {
        final long currentTime = System.currentTimeMillis();
        final long totalTimeElapsed = currentTime - lastFrameStartTime;

        final IndexedAnimationBehavior indexedAnimationBehavior = (IndexedAnimationBehavior) this.animationBehavior;

            // If Frame is up long enough
            if (totalTimeElapsed > indexedAnimationBehavior.frameDelayTime)
            {
                this.previousFrame();
                this.lastFrameStartTime = currentTime;
            }
            
            if(this.animationInterfaceArray[0].getFrame() == 0)
            {
                indexedAnimationBehavior.loopIndex++;
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
        final IndexedAnimationBehavior indexedAnimationBehavior = (IndexedAnimationBehavior) this.animationBehavior;
        if(indexedAnimationBehavior.loopTotal == -1 || 
            indexedAnimationBehavior.loopIndex < indexedAnimationBehavior.loopTotal || 
            this.getFrame() != 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void setSequence(final int[] sequence)
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

    public void setFrame(final int frame)
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
        
    public void reset()
    {
        // this.setFrame(0);
        this.setLastFrame();
        ((IndexedAnimationBehavior) this.animationBehavior).reset();
    }

    //this is called from nextFrame. Logical? probably not.
    public void previousFrame()
    {
        //ForcedLogUtil.log(CommonStrings.getInstance().NOT_IMPLEMENTED, this);
        for (int index = 0; index < size; index++)
        {
            this.animationInterfaceArray[index].previousFrame();
        }
    }

    public void paint(final Graphics graphics, final int frame, final int x, final int y)
    {
        this.setFrame(frame);
        this.paint(graphics, x, y);
    }

    protected final BasicColor CLEAR_COLOR = BasicColorFactory.getInstance().CLEAR_COLOR;
    
    public void paint(final Graphics graphics, final int ax, final int ay)
    {
        int x = 0;
        
        if(this.width != Integer.MIN_VALUE)
        {
            //x = ((graphics.getClipWidth() - this.width) / 2);
            x = ((displayInfoSingleton.getLastWidth() - this.width) / 2);
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

    /*
    private final ViewPosition viewPosition = new CenterViewPositionFactory().getInstance();
    
    public void paintThreed(final Graphics graphics, final int x, final int y, final int z)
    {
        int dx = 0;
        
        if(this.width != Integer.MIN_VALUE)
        {
            //dx = ((graphics.getClipWidth() - this.width) / 2);
            dx = ((displayInfoSingleton.getLastWidth() - this.width) / 2);
        }
        
        int deltaX;
        int deltaY;

        for (int index = 0; index < size; index++)
        {
            deltaX = this.dxArray[index] + dx;
            deltaY = this.dyArray[index] + y;

            //if (this.basicColorArray[index] != CLEAR_COLOR)
            //{
              //  this.basicColorUtil.setBasicColor(graphics, this.basicColorArray[index]);
            //}
            //LogUtil.put(LogFactory.getInstance("deltaX: " + deltaX + " " + x, this, "paint"));

            //this.animationInterfaceArray[index].paintThreed(graphics, deltaX, deltaY, 30);
            this.animationInterfaceArray[index].paintThreed(graphics, viewPosition.getX() + deltaX, viewPosition.getY() + deltaY, 30);
        }
    }
    */
}