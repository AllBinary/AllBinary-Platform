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
package org.allbinary.animation.image.sprite;

import javax.microedition.lcdui.game.Sprite;
import org.allbinary.animation.AnimationBehavior;

import org.allbinary.direction.Direction;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.math.Angle;
import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;
import org.allbinary.math.FrameUtil;

public class AllBinarySpriteRotationAnimation extends HackRotationSpriteIndexedAnimation
{
    public AllBinarySpriteRotationAnimation(final Sprite sprite, final AnimationBehavior animationBehavior)
    {
        super(sprite,
                AngleInfo.getInstance(
                (short) (AngleFactory.getInstance().TOTAL_ANGLE / sprite.getRawFrameCount())),
                animationBehavior);

        //(sprite.getWidth() / sprite.getHeight()) % 
        LogUtil.put(LogFactory.getInstance(new StringMaker().append("sprite.getRawFrameCount(): ").append(sprite.getRawFrameCount()).toString(), this, CommonStrings.getInstance().CONSTRUCTOR));

        this.angleInfo.adjustAngle(this.sprite.getFrame());
    }

    /*
    public AllBinarySpriteRotationAnimation(MESprite sprite, int x, int y, final AnimationBehavior animationBehavior)
    {
    super(sprite, x, y);

    int angleIncrement = Angle.THREE_SIXTY/this.getSprite().getRawFrameCount();

    this.setAngleInfo(AngleInfo.getInstance(angleIncrement));
    this.getAngleInfo().adjustAngle(this.getSprite().getFrame());
    }
     */
    
    public void nextRotation()
    {
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "nextFrame"));
        this.sprite.nextFrame();
        this.angleInfo.adjustAngle(this.sprite.getFrame());
    }

    public void previousRotation()
    {
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "previousFrame"));
        this.sprite.prevFrame();
        this.angleInfo.adjustAngle(this.sprite.getFrame());
    }

    public void setFrame(Direction direction)
    {
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "setFrame"));
        Angle angle = directionUtil.getFrameAngle(direction);
        this.adjustFrame(angle);
    }

    public void setFrame(Angle angle)
    {
        this.adjustFrame(angle);
    }

    public void setFrame(int index)
    {
        this.sprite.setFrame(index);
        this.angleInfo.adjustAngle(this.getFrame());
    }

    public void adjustFrame(Angle angle)
    {
        this.adjustFrame(angle.getValue());
    }

    private final FrameUtil frameUtil = FrameUtil.getInstance();
    
    public void adjustFrame(short angle)
    {
        this.setFrame(frameUtil.getFrameForAngle(angle,
                this.angleInfo.getAngleIncrementInfo().getAngleIncrement()));
    }
}
