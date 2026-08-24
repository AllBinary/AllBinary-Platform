package org.allbinary.animation;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class NullIndexedAnimation
extends IndexedAnimation
{
    @JsConstructor
    public NullIndexedAnimation(final AnimationBehavior animationBehavior) {
        super(animationBehavior);
    }
    
    @Override
    @JsMethod
    public int getAnimationSize() throws Exception
    {
        return 0;
        //return this.getSize();
    }
}
