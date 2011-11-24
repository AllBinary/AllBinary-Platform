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
package allbinary.animation.image.sprite;

import javax.microedition.lcdui.game.Sprite;

import allbinary.direction.Direction;
import allbinary.math.Angle;
import allbinary.math.AngleFactory;
import allbinary.math.AngleInfo;
import allbinary.math.FrameUtil;

public class AllBinarySpriteRotationAnimation extends HackRotationSpriteIndexedAnimation
{
    private AngleInfo angleInfo;

    public AllBinarySpriteRotationAnimation(Sprite sprite)
    {
        super(sprite);

        int angleIncrement = AngleFactory.getInstance().TOTAL_ANGLE / this.getSprite().getRawFrameCount();

        this.setAngleInfo(AngleInfo.getInstance(angleIncrement));
        this.getAngleInfo().adjustAngle(this.getSprite().getFrame());
    }

    /*
    public AllBinarySpriteRotationAnimation(MESprite sprite, int x, int y)
    {
    super(sprite, x, y);

    int angleIncrement = Angle.THREE_SIXTY/this.getSprite().getRawFrameCount();

    this.setAngleInfo(AngleInfo.getInstance(angleIncrement));
    this.getAngleInfo().adjustAngle(this.getSprite().getFrame());
    }
     */
    public void nextFrame()
    {
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "nextFrame"));
        Sprite sprite = this.getSprite();
        sprite.nextFrame();
        this.getAngleInfo().adjustAngle(sprite.getFrame());
    }

    public void previousFrame()
    {
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "previousFrame"));
        Sprite sprite = this.getSprite();
        sprite.prevFrame();
        this.getAngleInfo().adjustAngle(sprite.getFrame());
    }

    public AngleInfo getAngleInfo()
    {
        return angleInfo;
    }

    protected void setAngleInfo(AngleInfo angleInfo)
    {
        this.angleInfo = angleInfo;
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
        this.getSprite().setFrame(index);
        this.getAngleInfo().adjustAngle(this.getFrame());
    }

    public void adjustFrame(Angle angle)
    {
        this.adjustFrame(angle.getValue());
    }

    private final FrameUtil frameUtil = FrameUtil.getInstance();
    
    public void adjustFrame(short angle)
    {
        this.setFrame(frameUtil.getFrameForAngle(angle,
            this.getAngleInfo().getAngleIncrementInfo().getAngleIncrement()));
    }
}
