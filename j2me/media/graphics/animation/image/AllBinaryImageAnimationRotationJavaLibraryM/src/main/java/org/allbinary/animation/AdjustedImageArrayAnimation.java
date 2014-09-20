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

import org.allbinary.animation.image.AllBinaryImageArrayAnimation;

public class AdjustedImageArrayAnimation 
extends AllBinaryImageArrayAnimation
{
    private int dx;
    private int dy;

    public AdjustedImageArrayAnimation(Image[] imageArray, int dx, int dy)
    		throws Exception
    {
        super(imageArray);

        // LogUtil.put(LogFactory.getInstance(, this,));

        this.init(dx, dy);
        //-(imageArray[0].getWidth() >> 2), -(imageArray[0].getHeight() >> 2)
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
