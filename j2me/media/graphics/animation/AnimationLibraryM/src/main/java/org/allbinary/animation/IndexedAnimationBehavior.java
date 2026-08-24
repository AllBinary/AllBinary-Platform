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
package org.allbinary.animation;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author User
 */

@JsType
public class IndexedAnimationBehavior extends AnimationBehavior {
    
    @JsProperty
    public long frameDelayTime;
    @JsProperty
    public int loopTotal;
    
    @JsProperty
    public long elapsedTime;
    @JsProperty
    public int loopIndex;
    
    @JsConstructor
    public IndexedAnimationBehavior(final int loopTotal, final long frameDelayTime) {
        this.loopTotal = loopTotal;
        this.frameDelayTime = frameDelayTime;
    }
    
    @Override
    @JsMethod
    public void reset() {
        this.loopIndex = 0;
    }
    
}
