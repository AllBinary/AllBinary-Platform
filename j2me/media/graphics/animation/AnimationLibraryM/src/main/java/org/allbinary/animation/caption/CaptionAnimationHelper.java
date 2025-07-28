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
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.animation.text.TextAnimation;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;

public class CaptionAnimationHelper extends CaptionAnimationHelperBase
{
    private final TimeDelayHelper timeHelper = new TimeDelayHelper(620);
    private final TextAnimation textAnimation = new TextAnimation(AnimationBehavior.getInstance());
    private final Animation captionAnimation;
    
    private Animation animation = NullAnimationFactory.getFactoryInstance().getInstance(0);

    public CaptionAnimationHelper(final Animation captionAnimation,
            final int captionDx, final int captionDy, final int dx, final int dy)
    {
        this.captionAnimation = new CaptionAnimation(
            captionAnimation, this.textAnimation, 
            captionDx, captionDy, dx, dy);
    }
    
    @Override
    public boolean isShowing()
    {
        if(this.animation == this.captionAnimation)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public void tick()
    {
        if(this.timeHelper.isTime(GameTickTimeDelayHelperFactory.getInstance().startTime))
        {
            this.animation = NullAnimationFactory.getFactoryInstance().getInstance(0);
        }
    }
    
    @Override
    public void update(String message, BasicColor basicColor)
    {
        this.textAnimation.setBasicColor(basicColor);
        this.textAnimation.setText(message);
        this.animation = this.captionAnimation;
        this.timeHelper.setStartTime();
    }
    
    @Override
    public void paint(Graphics graphics, int x, int y)
    {
        this.animation.paint(graphics, x, y);
    }
}
