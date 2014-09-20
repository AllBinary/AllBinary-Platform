package org.allbinary.animation;

import org.allbinary.math.AngleInfo;

public class NullRotationAnimation
extends RotationAnimation
{
    protected NullRotationAnimation(AngleInfo angleInfo, short totalAngle)
    {
        super(angleInfo, totalAngle);
    }

    protected NullRotationAnimation()
    {
    }
    
    public int getAnimationSize() throws Exception
    {
        return 0;
    }
}
