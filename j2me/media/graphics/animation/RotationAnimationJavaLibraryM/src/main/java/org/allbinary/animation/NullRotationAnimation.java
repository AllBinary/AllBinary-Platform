package org.allbinary.animation;

import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;
import org.allbinary.util.CircularIndexUtil;

public class NullRotationAnimation
extends RotationAnimation
{
    public static NullRotationAnimation createTotalAngle(final AngleInfo angleInfo, final short totalAngle, final AnimationBehavior animationBehavior)
    {
        return new NullRotationAnimation(angleInfo, CircularIndexUtil.create(totalAngle / angleInfo.getAngleIncrementInfo().getAngleIncrement()), animationBehavior);
    }

    public static NullRotationAnimation createQuarter(final AnimationBehavior animationBehavior)
    {
        return new NullRotationAnimation(AngleInfo.getInstance(AngleFactory.getInstance().QUARTER_TOTAL_ANGLE), CircularIndexUtil.create(4), animationBehavior);
    }

    protected NullRotationAnimation(final AngleInfo angleInfo, final CircularIndexUtil circularIndexUtil, final AnimationBehavior animationBehavior) {
        super(angleInfo, circularIndexUtil, animationBehavior);
    }

    @Override
    public int getAnimationSize() throws Exception
    {
        return 0;
    }
}
