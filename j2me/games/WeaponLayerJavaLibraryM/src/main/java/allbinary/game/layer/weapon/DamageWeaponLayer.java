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
package allbinary.game.layer.weapon;

import org.allbinary.physics.movement.Movement;

import allbinary.animation.Animation;
import allbinary.graphics.Rectangle;
import allbinary.view.ViewPosition;

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

    public DamageWeaponLayer(Movement movement,
            Animation animationInterface,
            Animation destroyedAnimationInterface,
            Rectangle rectangle, ViewPosition viewPosition, String username, int actorSessionId, int id, int multiPlayerType)
            throws Exception
    {
        super(movement, animationInterface, destroyedAnimationInterface, rectangle, viewPosition, username, actorSessionId, id, multiPlayerType);
        
        this.setCollidableInferface(new CollidableDamageWeaponBehavior(this, true));
    }
}
