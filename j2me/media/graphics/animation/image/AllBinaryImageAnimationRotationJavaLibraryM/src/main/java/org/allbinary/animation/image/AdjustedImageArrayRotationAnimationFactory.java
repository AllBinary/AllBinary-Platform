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

import javax.microedition.lcdui.Image;
import org.allbinary.animation.Animation;

import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;

public class AdjustedImageArrayRotationAnimationFactory 
    extends ImageArrayRotationAnimationFactory
{
    private int dx;
    private int dy;

    public AdjustedImageArrayRotationAnimationFactory(Image image, int dx, int dy) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy);
    }

    public AdjustedImageArrayRotationAnimationFactory(Image image, int dx, int dy, int angleIncrement) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, angleIncrement);
    }
    
    public AdjustedImageArrayRotationAnimationFactory(Image image,
            int width, int height, int dx, int dy, int angleIncrement) throws Exception
    {

        super(image, width, height, angleIncrement);

        this.dx = dx;
        this.dy = dy;
    }
    
    public AdjustedImageArrayRotationAnimationFactory(Image image,
            int width, int height, int dx, int dy) throws Exception
    {

        super(image, width, height);

        this.dx = dx;
        this.dy = dy;
    }

    public Animation getInstance() throws Exception
    {
        // return new AllBinarySpriteRotationAnimation(new MESprite(image,
        // width, height), dx, dy);

        return new AdjustedImageArrayRotationAnimation(
                this.getImageArray(), 
                AngleInfo.getInstance((short) this.getAngleIncrement()), 
                AngleFactory.getInstance().TOTAL_ANGLE, dx, dy);
    }

}