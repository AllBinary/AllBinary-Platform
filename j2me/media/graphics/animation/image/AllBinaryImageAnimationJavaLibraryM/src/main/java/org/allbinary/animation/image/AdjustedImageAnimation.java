/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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

/**
 *
 * @author User
 */
public class AdjustedImageAnimation extends ImageAnimation {
    //protected final LogUtil logUtil = LogUtil.getInstance();

    
    private int dx;
    private int dy;

    public AdjustedImageAnimation(final Image image, final int dx, final int dy, final AnimationBehavior animationBehavior)
    		throws Exception
    {
        super(image, animationBehavior);

        // logUtil.put(, this,);

        this.init(dx, dy);
        //-(imageArray[0].getWidth() >> 2), -(imageArray[0].getHeight() >> 2)
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
    public void setDx(int dx)
    {
        this.dx = dx;
    }

    @Override
    public int getDx()
    {
        return dx;
    }

    @Override
    public void setDy(int dy)
    {
        this.dy = dy;
    }

    @Override
    public int getDy()
    {
        return dy;
    }

}
