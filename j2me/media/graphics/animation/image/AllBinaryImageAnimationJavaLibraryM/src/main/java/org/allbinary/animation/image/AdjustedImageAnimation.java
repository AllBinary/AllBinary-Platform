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
    
    private int dx;
    private int dy;

    public AdjustedImageAnimation(final Image image, final int dx, final int dy, final AnimationBehavior animationBehavior)
    		throws Exception
    {
        super(image, animationBehavior);

        // LogUtil.put(LogFactory.getInstance(, this,));

        this.init(dx, dy);
        //-(imageArray[0].getWidth() >> 2), -(imageArray[0].getHeight() >> 2)
    }

    public void init(final int dx, final int dy) throws Exception
    {
        this.setDx(dx);
        this.setDy(dy);
    }

    public void paint(final Graphics graphics, final int x, final int y)
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
