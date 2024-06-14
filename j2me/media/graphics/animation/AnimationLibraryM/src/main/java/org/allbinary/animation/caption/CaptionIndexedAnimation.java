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

//import org.allbinary.graphics.displayable.PaintableInterface;
import javax.microedition.lcdui.Graphics;
import javax.microedition.media.Player;

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.AnimationEventHandler;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;

/**
 * 
 * @author user
 */
public class CaptionIndexedAnimation extends IndexedAnimation
{
    private Animation animationInterface;
    // private PaintableInterface paintableInterface;
    private IndexedAnimation movieIndexedAnimationInterface;

    private int captionDx;
    private int captionDy;

    private int dx;
    private int dy;

    private int time;

    private AllBinaryEventObject END_EVENT;

    private TimeDelayHelper timeDelayHelper;

    private Player player;

    public CaptionIndexedAnimation(final Animation animationInterface,
            final IndexedAnimation movieIndexedAnimationInterface,
            final Player player, final int captionDx, final int captionDy, 
            final int dx, final int dy, final int time,
            final AnimationBehavior animationBehavior) throws Exception
    {
        super(animationBehavior);
        
        this.animationInterface = animationInterface;
        this.movieIndexedAnimationInterface = movieIndexedAnimationInterface;

        this.captionDx = captionDx;
        this.captionDy = captionDy;

        this.dx = dx;
        this.dy = dy;

        this.time = time;

        this.END_EVENT = new AllBinaryEventObject(this);

        int timePerFrame = this.time / this.getSize();
        this.timeDelayHelper = new TimeDelayHelper(timePerFrame);

        this.player = player;

        this.player.start();
    }

    public void nextFrame() throws Exception
    {
        if (this.timeDelayHelper.isTime(GameTickTimeDelayHelperFactory.getInstance().startTime))
        {
            this.movieIndexedAnimationInterface.nextFrame();
        }

        if (this.isLastFrame())
        {
            AnimationEventHandler.getInstance().fireEvent(this.END_EVENT);
        }
    }

    public boolean isLastFrame()
    {
        if (this.movieIndexedAnimationInterface.getFrame() == this.getSize() - 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void previousFrame()
    {
        this.movieIndexedAnimationInterface.previousFrame();
    }

    public void setFrame(int index)
    {
        this.movieIndexedAnimationInterface.setFrame(index);
    }

    public int getFrame()
    {
        return this.movieIndexedAnimationInterface.getFrame();
    }

    public int getSize()
    {
        return this.movieIndexedAnimationInterface.getSize();
    }

    public void setSequence(int[] sequence)
    {

    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    public void paint(Graphics graphics, int x, int y)
    {
        this.animationInterface.paint(graphics, 
                x + this.captionDx, y + this.captionDy);
        this.movieIndexedAnimationInterface.paint(graphics, 
                x + this.captionDx + dx, y + this.captionDy + dy);
    }

    public void paintThreed(Graphics graphics, int x, int y, int z)
    {
        this.animationInterface.paintThreed(graphics, 
                x + this.captionDx, y + this.captionDy, z);
        this.movieIndexedAnimationInterface.paintThreed(graphics, 
                x + this.captionDx + dx, y + this.captionDy + dy, z);
    }    
}
