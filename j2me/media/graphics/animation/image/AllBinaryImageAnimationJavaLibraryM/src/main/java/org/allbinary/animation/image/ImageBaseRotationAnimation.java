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
import org.allbinary.DisposalUtil;
import org.allbinary.animation.AnimationBehavior;

import org.allbinary.animation.RotationAnimation;
import org.allbinary.graphics.Anchor;
import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.math.AngleInfo;

public class ImageBaseRotationAnimation extends RotationAnimation //implements AutoCloseable
{
    private final Image image;

    //private static final int currentFrame = 0;
    //private static final int totalFrames = 1;
    public ImageBaseRotationAnimation(final Image image, final AngleInfo angleInfo, final short totalAngle, final AnimationBehavior animationBehavior)
        throws Exception
    {
        super(angleInfo, totalAngle, animationBehavior);
        
        this.image = image;
    }

    public int getAnimationSize() throws Exception
    {
        return this.getSize();
    }
    
    public void setSequence(int[] sequence)
    {
    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    protected int anchor = Anchor.TOP_LEFT;
    
    public void paint(final Graphics graphics, final int x, final int y)
    {
        graphics.drawImage(this.image, x, y, anchor);
    }

    /**
     * @return the image
     */
    protected Image getImage()
    {
        return image;
    }

    public int getWidth() {
        return this.image.getWidth();
    }

    /*
    public void paint(Graphics graphics) {
    graphics.drawImage(this.image, 0, 0, Anchor.TOP_LEFT);
    }
     */
    
    public void close() throws Exception {
        DisposalUtil.getInstance().dispose(this.image);
    }
    
    protected void finalize() throws Throwable {
        DisposalUtil.getInstance().dispose(this.image);
    }
    
}
