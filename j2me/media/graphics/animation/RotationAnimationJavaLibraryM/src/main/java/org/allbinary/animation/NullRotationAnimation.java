package org.allbinary.animation;

import org.allbinary.math.AngleInfo;

public class NullRotationAnimation
extends RotationAnimation
{
    NullRotationAnimation(final AngleInfo angleInfo, final short totalAngle, final AnimationBehavior animationBehavior)
    {
        super(angleInfo, totalAngle, animationBehavior);
    }

    NullRotationAnimation(final AnimationBehavior animationBehavior)
    {
        super(animationBehavior);
    }
    
    @Override
    public int getAnimationSize() throws Exception
    {
        return 0;
    }
}
