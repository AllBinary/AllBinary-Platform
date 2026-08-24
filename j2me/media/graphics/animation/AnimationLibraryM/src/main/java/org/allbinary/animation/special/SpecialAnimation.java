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
package org.allbinary.animation.special;

import jsinterop.annotations.JsType;

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.animation.IndexedAnimationBehavior;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class SpecialAnimation extends IndexedAnimation implements SpecialAnimationInterface
{
    private static final SpecialAnimation NULL_SPECIAL_ANIMATION = new SpecialAnimation(new IndexedAnimationBehavior(1, 250));

    @JsMethod
    public static SpecialAnimation getInstance()
    {
        return SpecialAnimation.NULL_SPECIAL_ANIMATION;
    }
    
    @JsConstructor
    public SpecialAnimation(final AnimationBehavior animationBehavior) {
        super(animationBehavior);
    }
    
    @Override
    @JsMethod
    public void nextFrame()
    {
        final IndexedAnimationBehavior indexedAnimationBehavior = ((IndexedAnimationBehavior) this.animationBehaviorP);
        indexedAnimationBehavior.loopIndex++;
    }
    
    @Override
    @JsMethod
    public void setLastFrame()
    {
        
    }
    
    @Override
    @JsMethod
    public boolean isComplete()
    {
        return false;
    }
    
    @JsMethod
    public void open() {
        
    }

    @JsMethod
    public void close() {
        
    }
    
    @JsMethod
    public void process() {
        
    }
}
