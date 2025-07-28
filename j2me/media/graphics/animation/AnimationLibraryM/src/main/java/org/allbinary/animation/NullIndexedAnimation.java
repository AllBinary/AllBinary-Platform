package org.allbinary.animation;

public class NullIndexedAnimation
extends IndexedAnimation
{
    public NullIndexedAnimation(final AnimationBehavior animationBehavior) {
        super(animationBehavior);
    }
    
    @Override
    public int getAnimationSize() throws Exception
    {
        return 0;
        //return this.getSize();
    }
}
