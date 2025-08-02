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

    void setVelocity(BasicDecimal magnitudeBasicDecimal, Direction direction, Direction otherDirection);

    void addVelocity(BasicDecimal magnitudeBasicDecimal, Direction direction, Direction otherDirection);

    void setVelocity(long magnitude, Direction direction, Direction otherDirection);

    void addVelocity(long magnitude, Direction direction, Direction otherDirection);

    void setVelocity(BasicDecimal magnitudeBasicDecimal, Angle angle, Angle otherAngle);

    void addVelocity(BasicDecimal magnitudeBasicDecimal, Angle angle, Angle otherAngle);

    void setVelocity(long magnitude, Angle angle, Angle otherAngle);

    void addVelocity(long magnitude, Angle angle, Angle otherAngle);

    void addVelocity(long magnitude, int angle, int otherAngle);
}
