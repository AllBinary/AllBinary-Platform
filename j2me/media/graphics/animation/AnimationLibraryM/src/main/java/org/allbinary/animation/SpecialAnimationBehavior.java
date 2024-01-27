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
public class SpecialAnimationBehavior extends IndexedAnimationBehavior {
    
    public int loopIndex;
    
    public SpecialAnimationBehavior(final int loopTotal, final long frameDelayTime) {
        super(loopTotal, frameDelayTime);
    }

    public void reset() {
        this.loopIndex = 0;
    }
    
}
