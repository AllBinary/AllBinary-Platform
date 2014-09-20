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

import org.allbinary.animation.RotationAnimation;
import org.allbinary.graphics.Anchor;
import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.math.AngleInfo;

public class AllBinaryImageBaseRotationAnimation extends RotationAnimation
{
    private final Image image;

    //private static final int currentFrame = 0;
    //private static final int totalFrames = 1;
    public AllBinaryImageBaseRotationAnimation(Image image, AngleInfo angleInfo, short totalAngle)
        throws Exception
    {
        super(angleInfo, totalAngle);
        
        this.image = image;
    }

    public int getAnimationSize() throws Exception
    {
        return this.getSize();
    }
    
    public void setSequence(int[] sequence)
    {
    }

    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics, int x, int y)
    {
        graphics.drawImage(this.image, x, y, anchor);
    }

    /**
     * @return the image
     */
    protected Image getImage()
    {
        return image;
    }

    /*
    public void paint(Graphics graphics) {
    graphics.drawImage(this.image, 0, 0, Anchor.TOP_LEFT);
    }
     */
}
