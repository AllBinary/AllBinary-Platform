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
package org.allbinary.game.layer.weapon;

import org.allbinary.physics.movement.Movement;

import org.allbinary.animation.Animation;
import org.allbinary.graphics.Rectangle;
import org.allbinary.view.ViewPosition;
import org.allbinary.game.multiplayer.layer.RemoteInfo;

public class DamageWeaponLayer extends WeaponLayer
{
    public DamageWeaponLayer(Movement movement,
            Animation animationInterface,
            Rectangle rectangle, ViewPosition viewPosition)
            throws Exception
    {
        super(movement, animationInterface, rectangle, viewPosition);
        
        this.setCollidableInferface(new CollidableDamageWeaponBehavior(this, true));
    }

    public DamageWeaponLayer(Movement movement,
            Animation animationInterface,
            Animation destroyedAnimationInterface,
            Rectangle rectangle, ViewPosition viewPosition)
            throws Exception
    {
        super(movement, animationInterface, destroyedAnimationInterface, rectangle, viewPosition);
        
        this.setCollidableInferface(new CollidableDamageWeaponBehavior(this, true));
    }

    public DamageWeaponLayer(RemoteInfo remoteInfo, Movement movement,
            Animation animationInterface,
            Animation destroyedAnimationInterface,
            Rectangle rectangle, ViewPosition viewPosition, String username, int actorSessionId, int id, int multiPlayerType)
            throws Exception
    {
        super(remoteInfo, multiPlayerType, movement, animationInterface, destroyedAnimationInterface, rectangle, viewPosition);
        
        this.setCollidableInferface(new CollidableDamageWeaponBehavior(this, true));
    }
}
