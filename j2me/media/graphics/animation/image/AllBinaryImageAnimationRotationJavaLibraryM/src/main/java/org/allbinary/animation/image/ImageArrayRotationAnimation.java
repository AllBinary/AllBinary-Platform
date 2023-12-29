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

import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;

public class ImageArrayRotationAnimation extends
        ImageArrayBaseRotationAnimation
{
    // , 10, AngleIncrementInfo.TOTAL_ANGLE
    // , angleIncrement, AngleIncrementInfo.TOTAL_ANGLE

    private int expectedTotalFrames;

    protected ImageArrayRotationAnimation(Object object)
            throws Exception
    {
        super(((ImageArrayRotationAnimationInfo) object).getImageArray(),
                ((ImageArrayRotationAnimationInfo) object).getAngleInfo());

        // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().CONSTRUCTOR,
        // this, "AllBinaryImageRotationAnimation"));
        ImageArrayRotationAnimationInfo allBinaryImageRotationAnimationInfo = (ImageArrayRotationAnimationInfo) object;

        this.init(allBinaryImageRotationAnimationInfo.getImageArray(),
                allBinaryImageRotationAnimationInfo.getAngleInfo(),
                allBinaryImageRotationAnimationInfo.getTotalAngle());
    }

    public ImageArrayRotationAnimation(Image[] imageArray,
            AngleInfo angleInfo, int totalAngle) throws Exception
    {
        super(imageArray, angleInfo);

        // LogUtil.put(LogFactory.getInstance("Constructing", this,
        // "AllBinaryImageRotationAnimation"));

        this.init(imageArray, angleInfo, totalAngle);
    }
    
    public ImageArrayRotationAnimation(Image[] imageArray)
            throws Exception
    {
        this(imageArray, AngleInfo.getInstance((short) 10), AngleFactory.getInstance().TOTAL_ANGLE);
    }

    public ImageArrayRotationAnimation(Image[] imageArray,
            AngleInfo angleInfo) throws Exception
    {
        this(imageArray, angleInfo, AngleFactory.getInstance().TOTAL_ANGLE);
    }

    private void init(Image[] imageArray, AngleInfo angleInfo, int totalAngle)
            throws Exception
    {

        // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START,
        // this, "AllBinaryImageRotationAnimation"));

        this.expectedTotalFrames = totalAngle
                / this.angleInfo.getAngleIncrementInfo().getAngleIncrement();

        this.angleInfo.adjustAngle(0);

        if (expectedTotalFrames != this.getSize())
        {
            throw new Exception("Wrong Number of Frames");
        }
    }

    public void setImageArray(Image[] imageArray)
    {
        super.setImageArray(imageArray);

        this.angleInfo.adjustAngle(0);
    }
}
