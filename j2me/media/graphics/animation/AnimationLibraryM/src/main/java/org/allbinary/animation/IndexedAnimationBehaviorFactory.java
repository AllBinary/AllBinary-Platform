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

/**
 *
 * @author User
 */
public class IndexedAnimationBehaviorFactory extends AnimationBehaviorFactory {

    public long frameDelayTime;
    public int loopTotal;    
    
    public IndexedAnimationBehaviorFactory(final int loopTotal, final long frameDelayTime) {
        this.loopTotal = loopTotal;
        this.frameDelayTime = frameDelayTime;
    }
    
    public AnimationBehavior getOrCreateInstance() {
        return new IndexedAnimationBehavior(this.loopTotal, this.frameDelayTime);
    }

}
