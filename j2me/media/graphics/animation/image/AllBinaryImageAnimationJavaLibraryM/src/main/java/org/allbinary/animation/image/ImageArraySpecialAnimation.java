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
import org.allbinary.animation.special.SpecialAnimationInterface;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;

public class ImageArraySpecialAnimation 
extends ImageArrayAnimation 
implements SpecialAnimationInterface
{
    private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(270);

    public ImageArraySpecialAnimation(final Image[] imageArray, final AnimationBehavior animationBehavior)
            throws Exception
    {
        super(imageArray, animationBehavior);

        this.reset();
    }

    private int loopCount = 0;
    
    public int getLoopCount()
    {
        return loopCount;
    }
    
    public void setLastFrame()
    {
        this.circularIndexUtil.setIndex(this.circularIndexUtil.getSize() - 1);
    }
    
    public void reset()
    {
        this.setFrame(this.getSize() - 1);
        loopCount = 0;
    }

    public void nextFrame()
    {
            if (this.getFrame() > 0)
            {
                // If Frame is up long enough
                if (timeDelayHelper.isTime(GameTickTimeDelayHelperFactory.getInstance().startTime))
                {
                    this.previousFrame();
                    
                    if(this.getFrame() == 0)
                    {
                        loopCount++;
                    }                    
                }
            }
    }

    private final int loopCountTotal = 1;
    
    public boolean isComplete()
    {
        if (loopCount < loopCountTotal || this.getFrame() != 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void paint(final Graphics graphics, int x, int y)
    {
        x = DisplayInfoSingleton.getInstance().getLastHalfWidth() - (this.getImage(this.circularIndexUtil.getIndex()).getWidth() >> 1);
        y = 5;

        super.paint(graphics, x, y);
    }
}
