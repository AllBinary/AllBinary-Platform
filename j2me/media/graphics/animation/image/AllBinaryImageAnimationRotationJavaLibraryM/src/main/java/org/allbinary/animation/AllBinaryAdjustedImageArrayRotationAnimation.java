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
package org.allbinary.animation;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;

//TWB - Adjustments should be done in the resource creation and not at the animatoin level
public class AllBinaryAdjustedImageArrayRotationAnimation extends
        AllBinaryImageArrayRotationAnimation
{
    private int dx;
    private int dy;

    protected AllBinaryAdjustedImageArrayRotationAnimation(Object object)
            throws Exception
    {
        super(object);

        AllBinaryImageArrayRotationAnimationInfo allBinaryImageRotationAnimationInfo = (AllBinaryImageArrayRotationAnimationInfo) object;

        this.init(allBinaryImageRotationAnimationInfo.getDx(),
                allBinaryImageRotationAnimationInfo.getDy());
    }

    public AllBinaryAdjustedImageArrayRotationAnimation(Image[] imageArray)
            throws Exception
    {
        this(imageArray, AngleInfo.getInstance(10), AngleFactory.getInstance().TOTAL_ANGLE, 0, 0);
    }

    public AllBinaryAdjustedImageArrayRotationAnimation(Image[] imageArray,
            int dx, int dy) throws Exception
    {
        this(imageArray, AngleInfo.getInstance(10), AngleFactory.getInstance().TOTAL_ANGLE, dx, dy);
    }

    public AllBinaryAdjustedImageArrayRotationAnimation(Image[] imageArray,
            AngleInfo angleInfo, int dx, int dy) throws Exception
    {
        this(imageArray, angleInfo, AngleFactory.getInstance().TOTAL_ANGLE, dx, dy);
    }

    public AllBinaryAdjustedImageArrayRotationAnimation(Image[] imageArray,
            AngleInfo angleInfo, int totalAngle, int dx, int dy)
            throws Exception
    {
        super(imageArray, angleInfo, totalAngle);

        // LogUtil.put(LogFactory.getInstance("Constructing", this,
        // "AllBinaryImageRotationAnimation"));

        this.init(dx, dy);
    }

    public AllBinaryAdjustedImageArrayRotationAnimation(Image[] imageArray,
            AngleInfo angleInfo, int totalAngle) throws Exception
    {
        super(imageArray, angleInfo, totalAngle);

        // LogUtil.put(LogFactory.getInstance("Constructing", this,
        // "AllBinaryImageRotationAnimation"));

        this.init(-(imageArray[0].getWidth() >> 2), -(imageArray[0].getHeight() >> 2));
    }

    public void init(int dx, int dy) throws Exception
    {
        this.setDx(dx);
        this.setDy(dy);
    }

    public void paint(Graphics graphics, int x, int y)
    {
        super.paint(graphics, x + this.dx, y + this.dy);
    }

    public void setDx(int dx)
    {
        this.dx = dx;
    }

    public int getDx()
    {
        return dx;
    }

    public void setDy(int dy)
    {
        this.dy = dy;
    }

    public int getDy()
    {
        return dy;
    }
}
