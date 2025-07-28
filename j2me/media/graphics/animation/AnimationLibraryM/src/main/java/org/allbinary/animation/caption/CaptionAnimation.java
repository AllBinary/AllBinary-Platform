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
package org.allbinary.animation.caption;

import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.Animation;

/**
 * 
 * @author user
 */
public class CaptionAnimation extends Animation
{
    private final Animation animationInterface;
    private final Animation overlayAnimationInterface;

    private final int captionDx;
    private final int captionDy;

    private final int dx;
    private final int dy;

    public CaptionAnimation(final Animation animationInterface,
            final Animation overlayAnimationInterface,
            final int captionDx, final int captionDy, final int dx, final int dy)
    {
        this.animationInterface = animationInterface;
        this.overlayAnimationInterface = overlayAnimationInterface;

        this.captionDx = captionDx;
        this.captionDy = captionDy;

        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void nextFrame() throws Exception
    {
        //this.animationInterface.nextFrame();
        //this.overlayAnimationInterface.nextFrame();
    }

    @Override
    public void paint(final Graphics graphics, final int x, final int y)
    {
        this.animationInterface.paint(graphics, x + this.captionDx, y
                + this.captionDy);
        this.overlayAnimationInterface.paint(graphics, x + this.captionDx
                + dx, y + this.captionDy + dy);
    }

    @Override
    public void paintThreed(final Graphics graphics, final int x, final int y, final int z)
    {
        this.animationInterface.paintThreed(graphics, x + this.captionDx, y + this.captionDy, z);
        this.overlayAnimationInterface.paintThreed(graphics, 
                x + this.captionDx + dx, y + this.captionDy + dy, z);
    }
    
}
