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
package org.allbinary.game.physics.acceleration;

import org.allbinary.game.physics.velocity.BasicVelocityProperties;

/**
 * 
 * @author user
 */
public class GravityUtil
{
    private static final GravityUtil instance = new GravityUtil();

    public static GravityUtil getInstance()
    {
        return instance;
    }

    public final int GAME_GRAVITY_VELOCITY = 1800; //6900; // 800;
    public final int MAX_GAME_VELOCITY = 18000; //9600;

    public final void process(BasicVelocityProperties velocityProperties)
    {
        velocityProperties.getVelocityYBasicDecimalP().add(this.GAME_GRAVITY_VELOCITY);
    }

    public final void process(BasicVelocityProperties velocityProperties, int customGravity)
    {
        velocityProperties.getVelocityYBasicDecimalP().add(customGravity);
    }
}
