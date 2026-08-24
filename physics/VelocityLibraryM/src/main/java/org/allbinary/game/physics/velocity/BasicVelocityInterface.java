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

import jsinterop.annotations.JsType;

import org.allbinary.direction.Direction;
import org.allbinary.logic.math.BasicDecimal;
import org.allbinary.math.Angle;
import jsinterop.annotations.JsMethod;


@JsType
public interface BasicVelocityInterface
{

    @JsMethod
    BasicDecimal getVelocityXBasicDecimalP();

    @JsMethod
    BasicDecimal getVelocityYBasicDecimalP();

    @JsMethod
    void zero();

    //void setVelocityXBasicDecimal(BasicDecimal velocityXBasicDecimal);

    //void setVelocityYBasicDecimal(BasicDecimal velocityYBasicDecimal);

    @JsMethod
    void setVelocityWithBigDecimalAndDirection(BasicDecimal magnitudeBasicDecimal, Direction direction, Direction otherDirection);

    @JsMethod
    void addVelocityWithBigDecimalAndDirection(BasicDecimal magnitudeBasicDecimal, Direction direction, Direction otherDirection);

    @JsMethod
    void setVelocityWithDirection(long magnitude, Direction direction, Direction otherDirection);

    @JsMethod
    void addVelocityWithDirection(long magnitude, Direction direction, Direction otherDirection);

    @JsMethod
    void setVelocityWithBigDecimal(BasicDecimal magnitudeBasicDecimal, Angle angle, Angle otherAngle);

    @JsMethod
    void addVelocityWithBigDecimal(BasicDecimal magnitudeBasicDecimal, Angle angle, Angle otherAngle);

    @JsMethod
    void setVelocity(long magnitude, Angle angle, Angle otherAngle);

    @JsMethod
    void addVelocity(long magnitude, Angle angle, Angle otherAngle);

    @JsMethod
    void addVelocityi(long magnitude, int angle, int otherAngle);
}
