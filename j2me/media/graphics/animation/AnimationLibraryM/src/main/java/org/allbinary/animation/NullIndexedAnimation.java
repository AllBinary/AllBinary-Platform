package org.allbinary.animation;

public class NullIndexedAnimation
extends IndexedAnimation
{
    public NullIndexedAnimation(final AnimationBehavior animationBehavior) {
        super(animationBehavior);
    }
    
    public int getAnimationSize() throws Exception
    {
        return 0;
        //return this.getSize();
    }
}
