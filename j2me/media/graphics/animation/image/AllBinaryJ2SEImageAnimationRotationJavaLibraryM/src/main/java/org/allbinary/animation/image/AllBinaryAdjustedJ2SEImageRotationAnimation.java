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
import org.allbinary.math.AngleInfo;

public class AllBinaryAdjustedJ2SEImageRotationAnimation 
extends AllBinaryJ2SEImageRotationAnimation
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private int dx;
    private int dy;

    public AllBinaryAdjustedJ2SEImageRotationAnimation(final Image originalImage, final Image image, final AngleInfo angleInfo, final short totalAngle, 
        final int dx, final int dy, final AnimationBehavior animationBehavior)
    		throws Exception
    {
        super(originalImage, image, angleInfo, totalAngle, animationBehavior);

        // logUtil.put(, this,);

        this.init(dx, dy);
        //-(imageArray[0].getWidth() >> 2), -(imageArray[0].getHeight() >> 2)
    }

    public void init(final int dx, final int dy) throws Exception
    {
        this.dx = dx;
        this.dy = dy;
    }

    
    public void setDx(final int dx)
    {
        this.dx = dx;
    }

    public int getDx() {
        return this.dx;
    }    

    public void setDy(final int dy)
    {
        this.dy = dy;
    }

    public int getDy() {
        return this.dy;
    }    

    public void paint(final Graphics graphics, final int x, final int y)
    {
        super.paint(graphics, x + this.dx, y + this.dy);
    }

}
