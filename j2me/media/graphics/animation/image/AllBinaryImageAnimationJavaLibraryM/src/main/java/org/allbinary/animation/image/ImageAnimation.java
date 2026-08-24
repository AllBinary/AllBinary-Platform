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

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.DisposalUtil;
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.graphics.Anchor;
import org.allbinary.logic.math.PrimitiveIntUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class ImageAnimation extends IndexedAnimation //implements AutoCloseable
{
    private final Image image;

    @JsConstructor
    public ImageAnimation(final Image image, final AnimationBehavior animationBehavior)
        throws Exception
    {
        super(animationBehavior);

        this.image = image;
    }
    
    @Override
    @JsMethod
    public int getAnimationSize() throws Exception
    {
        return this.getSize();
    }
    
    @Override
    @JsMethod
    public void nextFrame()
    {
    }

    @Override
    @JsMethod
    public void previousFrame()
    {
    }

    @Override
    @JsMethod
    public void setFrame(int index)
    {
    }

    @Override
    @JsMethod
    public int getFrame()
    {
        return 0;
    }

    @Override
    @JsMethod
    public int getSize()
    {
        return 1;
    }

    @Override
    @JsMethod
    public void setSequence(int[] sequence)
    {
    }

    @Override
    @JsMethod
    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }

    private int anchor = Anchor.TOP_LEFT;
    
    @Override
    @JsMethod
    public void paintXY(Graphics graphics, int x, int y)
    {
        graphics.drawImage(this.image, x, y, this.anchor);
    }
    
    /**
     * @return the image
     */
    @JsMethod
    protected Image getImage()
    {
        return this.image;
    }

//    public void paint(Graphics graphics) {
//        graphics.drawImage(this.image, 0, 0, Anchor.TOP_LEFT);
//    }
 
    //java.lang.ref.Cleaner
    //java.lang.ref.PhantomReference} //extends PhantomReference<Object>
    //AutoCloseable} to enable use of the {@code try}-with-resources
    //java.lang.ref.WeakReference
    //java.lang.ref.PhantomReference
    @JsMethod
    public void close() throws Exception {
        DisposalUtil.getInstance().disposeImage(this.image);
    }
    
    @Override
    @JsMethod
    protected void finalize() throws Throwable {
        DisposalUtil.getInstance().disposeImage(this.image);
    }
    
}
