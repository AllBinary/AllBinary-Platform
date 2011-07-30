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
package allbinary.animation.caption;

import javax.microedition.lcdui.Graphics;

import allbinary.animation.Animation;

/**
 * 
 * @author user
 */
public class CaptionAnimation extends Animation
{
    private Animation animationInterface;
    private Animation overlayAnimationInterface;

    private int captionDx;
    private int captionDy;

    private int dx;
    private int dy;

    public CaptionAnimation(Animation animationInterface,
            Animation overlayAnimationInterface,
            int captionDx, int captionDy, int dx, int dy)
    {
        this.animationInterface = animationInterface;
        this.overlayAnimationInterface = overlayAnimationInterface;

        this.captionDx = captionDx;
        this.captionDy = captionDy;

        this.dx = dx;
        this.dy = dy;
    }

    public void nextFrame() throws Exception
    {
        //this.animationInterface.nextFrame();
        //this.overlayAnimationInterface.nextFrame();
    }

    public void paint(Graphics graphics, int x, int y)
    {
        this.animationInterface.paint(graphics, x + this.captionDx, y
                + this.captionDy);
        this.overlayAnimationInterface.paint(graphics, x + this.captionDx
                + dx, y + this.captionDy + dy);
    }

    public void paintThreed(Graphics graphics, int x, int y, int z)
    {
        this.animationInterface.paintThreed(graphics, x + this.captionDx, y + this.captionDy, z);
        this.overlayAnimationInterface.paintThreed(graphics, 
                x + this.captionDx + dx, y + this.captionDy + dy, z);
    }
    
}
