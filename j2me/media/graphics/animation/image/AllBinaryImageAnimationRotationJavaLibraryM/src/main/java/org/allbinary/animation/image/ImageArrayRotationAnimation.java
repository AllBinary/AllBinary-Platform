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

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;

public class ImageArrayRotationAnimation extends
        ImageArrayBaseRotationAnimation
{
    protected static ImageArrayRotationAnimation create(final Object object, final AnimationBehavior animationBehavior)
            throws Exception
    {
        final ImageArrayRotationAnimationInfo imageRotationAnimationInfo = (ImageArrayRotationAnimationInfo) object;
        return new ImageArrayRotationAnimation(imageRotationAnimationInfo.getImageArray(),
                imageRotationAnimationInfo.getAngleInfoP(), imageRotationAnimationInfo.getTotalAngle(), animationBehavior);
    }

    //protected final LogUtil logUtil = LogUtil.getInstance();

    // , 10, AngleIncrementInfo.TOTAL_ANGLE, angleIncrement, AngleIncrementInfo.TOTAL_ANGLE

    private int expectedTotalFrames;

    public ImageArrayRotationAnimation(final Image[] imageArray,
            final AngleInfo angleInfo, final int totalAngle, final AnimationBehavior animationBehavior) throws Exception
    {
        super(imageArray, angleInfo, animationBehavior);

        // this.logUtil.putF(commonStrings.START, this, commonStrings.CONSTRUCTOR);

        this.init(imageArray, angleInfo, totalAngle);
    }
    
//    public ImageArrayRotationAnimation(final Image[] imageArray, final AnimationBehavior animationBehavior)
//            throws Exception
//    {
//        this(imageArray, AngleInfo.getInstance((short) 10), (int) AngleFactory.getInstance().TOTAL_ANGLE, animationBehavior);
//    }

//    public ImageArrayRotationAnimation(final Image[] imageArray, final AngleInfo angleInfo,
//        final AnimationBehavior animationBehavior) throws Exception
//    {
//        this(imageArray, angleInfo, (int) AngleFactory.getInstance().TOTAL_ANGLE, animationBehavior);
//    }

    private void init(final Image[] imageArray, final AngleInfo angleInfo, final int totalAngle)
            throws Exception
    {

        // this.logUtil.putF(commonStrings.START, // this, "AllBinaryImageRotationAnimation");

        this.expectedTotalFrames = totalAngle
                / this.angleInfo.getAngleIncrementInfo().getAngleIncrement();

        this.angleInfo.adjustAngle(0);

        if (this.expectedTotalFrames != this.getSize())
        {
            throw new Exception("Wrong Number of Frames");
        }
    }

    @Override
    public void setImageArray(final Image[] imageArray)
    {
        super.setImageArray(imageArray);

        this.angleInfo.adjustAngle(0);
    }
}
