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

import jsinterop.annotations.JsType;

import org.allbinary.logic.math.PrimitiveIntUtil;
import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

/**
 * 
 * @author user
 */

@JsType
public class IndexedAnimation extends Animation 
    implements IndexedAnimationInterface
{
    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    @JsProperty
    protected final AnimationBehavior animationBehaviorP;
    
    @JsConstructor
    protected IndexedAnimation(final AnimationBehavior animationBehavior)
    {
        this.animationBehaviorP = animationBehavior;
    }
 
    /**
     * @return the animationBehavior
     */
    @JsMethod
    public AnimationBehavior getAnimationBehavior() {
        return this.animationBehaviorP;
    }
   
    @Override
    @JsMethod
    public void reset()
    {
        this.animationBehaviorP.reset();
        this.setFrame(0);
    }
    
    @Override
    @JsMethod
    public void setFrame(final int index)
    {

    }

    @Override
    @JsMethod
    public int getFrame()
    {
        return 0;
    }

    @JsMethod
    public int getAnimationSize() throws Exception
    {
        throw new Exception(this.commonStrings.NOT_IMPLEMENTED);
        //return this.getSize();
    }
    
    @Override
    @JsMethod
    public int getSize()
    {
        return 0;
    }

    @Override
    @JsMethod
    public void previousFrame()
    {

    }

    @JsMethod
    public boolean isLastFrame()
    {
        if(this.getFrame() == this.getSize() - 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    @Override
    @JsMethod
    public void setSequence(final int[] sequence)
    {

    }

    @Override
    @JsMethod
    public int[] getSequence()
    {
        return PrimitiveIntUtil.getArrayInstance();
    }
    
    @JsMethod
    public void setState(final IndexedAnimation indexedAnimation) {
        this.setFrame(indexedAnimation.getFrame());
        this.setDx(indexedAnimation.getDx());
        this.setDy(indexedAnimation.getDy());
    }
    
    //TWB - This is really only for ImageBaseRotationAnimation
    @JsMethod
    public int getWidth() {
        return 0;
    }
    @JsMethod
    public int getHeight() {
        return 0;
    }

    
}
