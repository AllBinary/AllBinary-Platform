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
package allbinary.animation;

import javax.microedition.lcdui.Image;

import allbinary.math.AngleFactory;
import allbinary.math.AngleInfo;

public class AllBinaryAdjustedImageArrayRotationAnimationFactory 
    extends AllBinaryImageArrayRotationAnimationFactory
{
    private int dx;
    private int dy;

    public AllBinaryAdjustedImageArrayRotationAnimationFactory(Image image, int dx, int dy) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy);
    }

    public AllBinaryAdjustedImageArrayRotationAnimationFactory(Image image, int dx, int dy, int angleIncrement) 
    throws Exception
    {
        this(image, image.getWidth(), image.getHeight(), dx, dy, angleIncrement);
    }
    
    public AllBinaryAdjustedImageArrayRotationAnimationFactory(Image image,
            int width, int height, int dx, int dy, int angleIncrement) throws Exception
    {

        super(image, width, height, angleIncrement);

        this.dx = dx;
        this.dy = dy;
    }
    
    public AllBinaryAdjustedImageArrayRotationAnimationFactory(Image image,
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

        return new AllBinaryAdjustedImageArrayRotationAnimation(
                this.getImageArray(), 
                AngleInfo.getInstance(this.getAngleIncrement()), 
                AngleFactory.getInstance().TOTAL_ANGLE, dx, dy);
    }

}
