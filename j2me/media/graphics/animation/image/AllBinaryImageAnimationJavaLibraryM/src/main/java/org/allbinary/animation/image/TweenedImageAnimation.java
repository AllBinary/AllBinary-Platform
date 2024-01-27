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
package org.allbinary.animation.image;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import org.allbinary.animation.AnimationBehavior;

import org.allbinary.util.CircularIndexUtil;

import org.allbinary.animation.IndexedAnimation;
import org.allbinary.graphics.Anchor;
import org.allbinary.logic.math.PrimitiveIntUtil;

public class TweenedImageAnimation extends IndexedAnimation
{
    private final Image image;

    private final int dx;
    private final int dy;
    
    private int totalFrames;

    protected CircularIndexUtil circularIndexUtil;
            
    public TweenedImageAnimation(final Image image, final int dx, final int dy, final int totalFrames, final AnimationBehavior animationBehavior)
        throws Exception
    {
        super(animationBehavior);

        this.image = image;
        
        this.dx = dx;
        this.dy = dy;
        
        this.totalFrames = totalFrames;
        
        this.circularIndexUtil = CircularIndexUtil.getInstance(this.totalFrames);
        
        // LogUtil.put(LogFactory.getInstance("Constructing", this,
        // "AllBinaryImageRotationAnimation"));
    }

    public int getAnimationSize() throws Exception
    {
        return this.getSize();
    }
    
    public void nextFrame()
    {
        this.circularIndexUtil.next();
    }

    public void previousFrame()
    {
        this.circularIndexUtil.previous();
    }

    public void setFrame(int index)
    {
        this.circularIndexUtil.setIndex(index);
    }

    public int getFrame()
    {
        return this.circularIndexUtil.getIndex();
    }

    public int getSize()
    {
        return this.totalFrames;
    }

    public void setSequence(int[] sequence)
    {

    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics, int x, int y)
    {
        int frame = this.getFrame();
        int currentX = dx * frame; 
        int currentY = dy * frame;

        graphics.drawImage(this.image, x + currentX, y + currentY, anchor);
    }
        
}
