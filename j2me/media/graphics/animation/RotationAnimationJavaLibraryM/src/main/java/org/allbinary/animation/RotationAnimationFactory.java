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

public class RotationAnimationFactory implements
        AnimationInterfaceFactoryInterface,
        ProceduralAnimationInterfaceFactoryInterface
{
    private short totalAngle;
    private short angleIncrement;

    public RotationAnimationFactory()
    {
        this.totalAngle = AngleFactory.getInstance().TOTAL_ANGLE;

        this.angleIncrement = (short) (totalAngle / GameConfigurationCentral.getInstance().getGameControlFidelity());
    }

    public RotationAnimationFactory(short totalAngle, short angleIncrement)
    {
        this.totalAngle = totalAngle;
        this.angleIncrement = angleIncrement;
    }
    
    public Animation getInstance() throws Exception
    {
        return new NullRotationAnimation(
                AngleInfo.getInstance(this.angleIncrement), this.totalAngle, AnimationBehavior.getInstance());
    }

    public Animation getInstance(Animation animationInterface) throws Exception
    {
        return new NullRotationAnimation(
                AngleInfo.getInstance(this.angleIncrement), this.totalAngle, AnimationBehavior.getInstance());
    }
      
   public void setInitialSize(final int width, final int height) {
       
   }
    
}