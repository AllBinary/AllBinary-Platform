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

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.direction.Direction;
import org.allbinary.math.Angle;
import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class AllBinarySpriteRotationAnimation extends HackRotationSpriteIndexedAnimation
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    @JsConstructor
    public AllBinarySpriteRotationAnimation(final Sprite sprite, final Image image, final AnimationBehavior animationBehavior)
    {
        super(sprite, image,
                AngleInfo.getInstance(
                (short) (AngleFactory.getInstance().TOTAL_ANGLE / sprite.getRawFrameCount())),
                animationBehavior);

        //(sprite.getWidth() / sprite.getHeight()) % 
        //this.logUtil.putF(new StringMaker().append("sprite.getRawFrameCount(): ").append(sprite.getRawFrameCount()).toString(), this, this.commonStrings.CONSTRUCTOR);

        this.angleInfo.adjustAngle(this.sprite.getFrame());
    }

    /*
    public AllBinarySpriteRotationAnimation(MESprite sprite, int x, int y, final AnimationBehavior animationBehavior)
    {
    super(sprite, x, y);

    int angleIncrement = Angle.THREE_SIXTY/this.getSprite().getRawFrameCount();

    this.setAngleInfo(AngleInfo.getInstance(angleIncrement));
    this.angleInfo.adjustAngle(this.getSprite().getFrame());
    }
     */
  
    @Override
    @JsMethod
    public void nextRotation()
    {
        //this.logUtil.putF(this.commonStrings.START, this, "nextFrame");
        this.sprite.nextFrame();
        this.angleInfo.adjustAngle(this.sprite.getFrame());
    }

    @Override
    @JsMethod
    public void previousRotation()
    {
        //this.logUtil.putF(this.commonStrings.START, this, "previousFrame");
        this.sprite.prevFrame();
        this.angleInfo.adjustAngle(this.sprite.getFrame());
    }

    @Override
    @JsMethod
    public void setFrameByDirection(Direction direction)
    {
        //this.logUtil.putF(this.commonStrings.START, this, "setFrame");
        Angle angle = this.directionUtil.getFrameAngle(direction);
        this.adjustFrameToAngle(angle);
    }

    @Override
    @JsMethod
    public void setFrameToAngle(Angle angle)
    {
        this.adjustFrameToAngle(angle);
    }

    @Override
    @JsMethod
    public void setFrame(int index)
    {
        this.sprite.setFrame(index);
        this.angleInfo.adjustAngle(this.getFrame());
    }

    @Override
    @JsMethod
    public void adjustFrameToAngle(Angle angle)
    {
        this.adjustFrame(angle.getValue());
    }

    @Override
    @JsMethod
    public void adjustFrame(short angle)
    {
        this.setFrame(this.frameUtil.getFrameForAngle(angle,
                (int) this.angleInfo.getAngleIncrementInfo().getAngleIncrement()));
    }
}
