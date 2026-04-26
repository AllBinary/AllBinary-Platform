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

import org.allbinary.animation.Animation;
import org.allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import org.allbinary.animation.NullIndexedAnimationFactory;
import org.allbinary.game.combat.damage.ExplosionResources;
import org.allbinary.game.multiplayer.layer.RemoteInfo;
import org.allbinary.graphics.Rectangle;
import org.allbinary.physics.movement.Movement;
import org.allbinary.view.ViewPosition;

public class SimpleWeaponLayer extends WeaponLayer
{
    public static Animation createDestroyed() throws Exception {
        return FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance()
                .getProcedural(ExplosionResources.getInstance().THIRD_EXPLOSION_RESOURCE)
                .getInstanceAnimation(NullIndexedAnimationFactory.getFactoryInstance().getInstance(0));
    }

    public SimpleWeaponLayer(final String name, final RemoteInfo remoteInfo, final int multiPlayerType, 
            final Movement movement,
            final Animation animationInterface,
            final Animation destroyedAnimationInterface,
            final Rectangle rectangle, final ViewPosition viewPosition)
            throws Exception
    {
        super(name, remoteInfo, multiPlayerType, movement, animationInterface, destroyedAnimationInterface, rectangle, viewPosition);
        
        this.setCollidableInferface(new CollidableWeaponBehavior(this, true));
    }
}
