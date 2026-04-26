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
package org.allbinary.game.physics.velocity;

import org.allbinary.direction.Direction;
import org.allbinary.logic.math.BasicDecimal;
import org.allbinary.math.Angle;

public interface BasicVelocityInterface
{

    BasicDecimal getVelocityXBasicDecimalP();

    BasicDecimal getVelocityYBasicDecimalP();

    void zero();

    //void setVelocityXBasicDecimal(BasicDecimal velocityXBasicDecimal);

    //void setVelocityYBasicDecimal(BasicDecimal velocityYBasicDecimal);

    void setVelocityWithBigDecimalAndDirection(BasicDecimal magnitudeBasicDecimal, Direction direction, Direction otherDirection);

    void addVelocityWithBigDecimalAndDirection(BasicDecimal magnitudeBasicDecimal, Direction direction, Direction otherDirection);

    void setVelocityWithDirection(long magnitude, Direction direction, Direction otherDirection);

    void addVelocityWithDirection(long magnitude, Direction direction, Direction otherDirection);

    void setVelocityWithBigDecimal(BasicDecimal magnitudeBasicDecimal, Angle angle, Angle otherAngle);

    void addVelocityWithBigDecimal(BasicDecimal magnitudeBasicDecimal, Angle angle, Angle otherAngle);

    void setVelocity(long magnitude, Angle angle, Angle otherAngle);

    void addVelocity(long magnitude, Angle angle, Angle otherAngle);

    void addVelocityi(long magnitude, int angle, int otherAngle);
}
