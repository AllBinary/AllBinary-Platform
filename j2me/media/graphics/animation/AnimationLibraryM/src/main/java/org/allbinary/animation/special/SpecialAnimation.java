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

import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.IndexedAnimation;
import org.allbinary.animation.IndexedAnimationBehavior;

public class SpecialAnimation extends IndexedAnimation implements SpecialAnimationInterface
{
    private static final SpecialAnimation NULL_SPECIAL_ANIMATION = new SpecialAnimation(new IndexedAnimationBehavior(1, 250));

    public static SpecialAnimation getInstance()
    {
        return NULL_SPECIAL_ANIMATION;
    }
    
    public SpecialAnimation(final AnimationBehavior animationBehavior) {
        super(animationBehavior);
    }
    
    @Override
    public void nextFrame()
    {
        final IndexedAnimationBehavior indexedAnimationBehavior = ((IndexedAnimationBehavior) this.animationBehaviorP);
        indexedAnimationBehavior.loopIndex++;
    }
    
    @Override
    public void setLastFrame()
    {
        
    }
    
    @Override
    public boolean isComplete()
    {
        return false;
    }
    
    public void open() {
        
    }

    public void close() {
        
    }
    
    public void process() {
        
    }
}
