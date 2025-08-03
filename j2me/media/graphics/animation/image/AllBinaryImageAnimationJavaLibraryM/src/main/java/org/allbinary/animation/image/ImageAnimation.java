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

import org.allbinary.DisposalUtil;
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.graphics.Anchor;
import org.allbinary.logic.math.PrimitiveIntUtil;

public class ImageAnimation extends IndexedAnimation //implements AutoCloseable
{
    private final Image image;

    public ImageAnimation(final Image image, final AnimationBehavior animationBehavior)
        throws Exception
    {
        super(animationBehavior);

        this.image = image;
    }
    
    @Override
    public int getAnimationSize() throws Exception
    {
        return this.getSize();
    }
    
    @Override
    public void nextFrame()
    {
    }

    @Override
    public void previousFrame()
    {
    }

    @Override
    public void setFrame(int index)
    {
    }

    @Override
    public int getFrame()
    {
        return 0;
    }

    @Override
    public int getSize()
    {
        return 1;
    }

    @Override
    public void setSequence(int[] sequence)
    {
    }

    @Override
    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    private int anchor = Anchor.TOP_LEFT;
    
    @Override
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

//    public void paint(Graphics graphics) {
//        graphics.drawImage(this.image, 0, 0, Anchor.TOP_LEFT);
//    }
 
    //java.lang.ref.Cleaner
    //java.lang.ref.PhantomReference} //extends PhantomReference<Object>
    //AutoCloseable} to enable use of the {@code try}-with-resources
    //java.lang.ref.WeakReference
    //java.lang.ref.PhantomReference
    public void close() throws Exception {
        DisposalUtil.getInstance().dispose(this.image);
    }
    
    @Override
    protected void finalize() throws Throwable {
        DisposalUtil.getInstance().dispose(this.image);
    }
    
}
