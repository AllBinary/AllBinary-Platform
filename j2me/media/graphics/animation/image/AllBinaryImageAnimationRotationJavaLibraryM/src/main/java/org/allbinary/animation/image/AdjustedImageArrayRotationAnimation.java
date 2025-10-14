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
import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;

//TWB - Adjustments should be done in the resource creation and not at the animation level
public class AdjustedImageArrayRotationAnimation extends
        ImageArrayRotationAnimation
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private int dx;
    private int dy;

    //protected
    public AdjustedImageArrayRotationAnimation(final Object object, final AnimationBehavior animationBehavior)
            throws Exception
    {
        super(object, animationBehavior);

        ImageArrayRotationAnimationInfo allBinaryImageRotationAnimationInfo = (ImageArrayRotationAnimationInfo) object;

        this.init(allBinaryImageRotationAnimationInfo.getDx(),
                allBinaryImageRotationAnimationInfo.getDy());
    }

    public AdjustedImageArrayRotationAnimation(final Image[] imageArray, final AnimationBehavior animationBehavior)
            throws Exception
    {
        this(imageArray, AngleInfo.getInstance((short) 10), (int) AngleFactory.getInstance().TOTAL_ANGLE, 0, 0, animationBehavior);
    }

    public AdjustedImageArrayRotationAnimation(final Image[] imageArray,
            final int dx, final int dy, final AnimationBehavior animationBehavior) throws Exception
    {
        this(imageArray, AngleInfo.getInstance((short) 10), (int) AngleFactory.getInstance().TOTAL_ANGLE, dx, dy, animationBehavior);
    }

    public AdjustedImageArrayRotationAnimation(final Image[] imageArray,
            final AngleInfo angleInfo, final int dx, final int dy, final AnimationBehavior animationBehavior) throws Exception
    {
        this(imageArray, angleInfo, (int) AngleFactory.getInstance().TOTAL_ANGLE, dx, dy, animationBehavior);
    }

    public AdjustedImageArrayRotationAnimation(final Image[] imageArray,
            final AngleInfo angleInfo, final int totalAngle, final int dx, final int dy,
            final AnimationBehavior animationBehavior)
            throws Exception
    {
        super(imageArray, angleInfo, totalAngle, animationBehavior);

        // logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);

        this.init(dx, dy);
    }

    public AdjustedImageArrayRotationAnimation(final Image[] imageArray,
            final AngleInfo angleInfo, final int totalAngle, final AnimationBehavior animationBehavior) throws Exception
    {
        super(imageArray, angleInfo, totalAngle, animationBehavior);

        // logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);

        this.init(-(imageArray[0].getWidth() >> 2), -(imageArray[0].getHeight() >> 2));
    }

    public void init(final int dx, final int dy) throws Exception
    {
        this.setDx(dx);
        this.setDy(dy);
    }

    @Override
    public void paint(final Graphics graphics, final int x, final int y)
    {
        super.paint(graphics, x + this.dx, y + this.dy);
    }

    @Override
    public void setDx(final int dx)
    {
        this.dx = dx;
    }

    @Override
    public int getDx()
    {
        return dx;
    }

    @Override
    public void setDy(final int dy)
    {
        this.dy = dy;
    }

    @Override
    public int getDy()
    {
        return dy;
    }
}
