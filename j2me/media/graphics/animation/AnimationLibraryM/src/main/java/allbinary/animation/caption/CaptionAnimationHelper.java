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
import allbinary.animation.NullAnimationFactory;
import allbinary.animation.text.TextAnimation;
import allbinary.graphics.color.BasicColor;
import allbinary.time.GameTickTimeDelayHelperFactory;
import allbinary.time.TimeDelayHelper;

public class CaptionAnimationHelper
{
    private final TimeDelayHelper timeHelper = new TimeDelayHelper(620);
    private final TextAnimation textAnimation = new TextAnimation();
    private final Animation captionAnimation;
    
    private Animation animation = NullAnimationFactory.getFactoryInstance().getInstance();

    public CaptionAnimationHelper(Animation captionAnimation,
            int captionDx, int captionDy, int dx, int dy)
    {
        this.captionAnimation = new CaptionAnimation(
            captionAnimation, this.textAnimation, 
            captionDx, captionDy, dx, dy);
    }
    
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
    
    public void tick()
    {
        if(this.timeHelper.isTime(GameTickTimeDelayHelperFactory.getInstance().getStartTime()))
        {
            this.animation = NullAnimationFactory.getFactoryInstance().getInstance();
        }
    }
    
    public void update(String message, BasicColor basicColor)
    {
        this.textAnimation.setBasicColor(basicColor);
        this.textAnimation.setText(message);
        this.animation = this.captionAnimation;
        this.timeHelper.setStartTime();
    }
    
    public void paint(Graphics graphics, int x, int y)
    {
        this.animation.paint(graphics, x, y);
    }
}
