package org.allbinary.animation;

import jsinterop.annotations.JsType;

import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;
import org.allbinary.util.CircularIndexUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class NullRotationAnimation
extends RotationAnimation
{
    @JsMethod
    public static NullRotationAnimation createTotalAngle(final AngleInfo angleInfo, final short totalAngle, final AnimationBehavior animationBehavior)
    {
        return new NullRotationAnimation(angleInfo, CircularIndexUtil.createInstance(totalAngle / angleInfo.getAngleIncrementInfo().getAngleIncrement()), animationBehavior);
    }

    @JsMethod
    public static NullRotationAnimation createQuarter(final AnimationBehavior animationBehavior)
    {
        return new NullRotationAnimation(AngleInfo.getInstance(AngleFactory.getInstance().QUARTER_TOTAL_ANGLE), CircularIndexUtil.createInstance(4), animationBehavior);
    }

    @JsConstructor
    protected NullRotationAnimation(final AngleInfo angleInfo, final CircularIndexUtil circularIndexUtil, final AnimationBehavior animationBehavior) {
        super(angleInfo, circularIndexUtil, animationBehavior);
    }

    @Override
    @JsMethod
    public int getAnimationSize() throws Exception
    {
        return 0;
    }
}
