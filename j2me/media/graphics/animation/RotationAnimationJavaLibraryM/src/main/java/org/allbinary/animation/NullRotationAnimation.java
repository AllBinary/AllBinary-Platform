package org.allbinary.animation;

import org.allbinary.math.AngleInfo;

public class NullRotationAnimation
extends RotationAnimation
{
    protected NullRotationAnimation(final AngleInfo angleInfo, final short totalAngle, final AnimationBehavior animationBehavior)
    {
        super(angleInfo, totalAngle, animationBehavior);
    }

    protected NullRotationAnimation(final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);
    }
    
    public int getAnimationSize() throws Exception
    {
        return 0;
    }
}
