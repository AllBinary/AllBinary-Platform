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
package org.allbinary.animation;

import org.allbinary.game.configuration.GameConfigurationCentral;
import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;
import org.allbinary.media.ScaleProperties;

public class RotationAnimationFactory implements
        AnimationInterfaceFactoryInterface,
        ProceduralAnimationInterfaceFactoryInterface
{
    public static RotationAnimationFactory create()
    {
        final short totalAngle = AngleFactory.getInstance().TOTAL_ANGLE;
        return new RotationAnimationFactory(totalAngle, (short) (totalAngle / GameConfigurationCentral.getInstance().getGameControlFidelity()));
    }

    private short totalAngle;
    private short angleIncrement;

    public RotationAnimationFactory(short totalAngle, short angleIncrement)
    {
        this.totalAngle = totalAngle;
        this.angleIncrement = angleIncrement;
    }
    
    @Override
    public Animation getInstance(final int instanceId) throws Exception
    {
        return NullRotationAnimation.createTotalAngle(
                AngleInfo.getInstance(this.angleIncrement), this.totalAngle, AnimationBehavior.getInstance());
    }

    @Override
    public Animation getInstance(Animation animationInterface) throws Exception
    {
        return NullRotationAnimation.createTotalAngle(
                AngleInfo.getInstance(this.angleIncrement), this.totalAngle, AnimationBehavior.getInstance());
    }
     
    @Override
    public void setInitialScale(final ScaleProperties scaleProperties) {
       
    }

}