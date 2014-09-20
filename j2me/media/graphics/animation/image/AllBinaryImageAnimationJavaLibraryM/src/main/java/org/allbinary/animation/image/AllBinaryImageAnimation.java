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

import org.allbinary.animation.IndexedAnimation;
import org.allbinary.graphics.Anchor;
import org.allbinary.logic.math.PrimitiveIntUtil;

public class AllBinaryImageAnimation extends IndexedAnimation
{
    private final Image image;

    //private static final int currentFrame = 0;
    //private static final int totalFrames = 1;
    public AllBinaryImageAnimation(Image image)
        throws Exception
    {
        this.image = image;
    }

    public int getAnimationSize() throws Exception
    {
        return this.getSize();
    }
    
    public void nextFrame()
    {
    }

    public void previousFrame()
    {
    }

    public void setFrame(int index)
    {
    }

    public int getFrame()
    {
        //return this.currentFrame;
        return 0;
    }

    public int getSize()
    {
        //return this.totalFrames;
        return 1;
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
