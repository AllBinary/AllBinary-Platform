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
package allbinary.animation.special;

import allbinary.animation.IndexedAnimation;

public class SpecialAnimation extends IndexedAnimation implements SpecialAnimationInterface
{
    private static final SpecialAnimation NULL_ANIMATION = new SpecialAnimation();

    public static SpecialAnimation getInstance()
    {
        return NULL_ANIMATION;
    }

    private int loopCount = 0;
    
    public void reset()
    {
        this.loopCount = 0;
    }
    
    public void setLoopCount(int loopCount)
    {
        this.loopCount = loopCount;
    }

    public int getLoopCount()
    {
        return loopCount;
    }
    
    public void nextFrame()
    {
        loopCount++;
    }
    
    public void setLastFrame()
    {
        
    }
    
    public boolean isComplete()
    {
        return false;
    }
}
