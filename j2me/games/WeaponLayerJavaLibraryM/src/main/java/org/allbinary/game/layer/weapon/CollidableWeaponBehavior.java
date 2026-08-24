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

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

import org.allbinary.game.collision.CollidableHelperFactory;
import org.allbinary.game.collision.CollidableInterfaceCompositeInterface;
import org.allbinary.game.collision.CollisionHelper;
import org.allbinary.game.collision.CollisionType;
import org.allbinary.game.collision.CollisionTypeFactory;
import org.allbinary.game.combat.damage.DamageableInterface;
import org.allbinary.game.layer.CollidableCompositeLayer;
import org.allbinary.game.layer.special.CollidableDestroyableDamageableBehavior;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.NullUtil;


@JsType
public class CollidableWeaponBehavior extends CollidableDestroyableDamageableBehavior
{
    
    private static Object NULL_COLLIDABLE_WEAPON_BEHAVIOR = NullUtil.getInstance().NULL_OBJECT;
    
    @JsMethod
    public static CollidableWeaponBehavior getNullInstance() {
        if(CollidableWeaponBehavior.NULL_COLLIDABLE_WEAPON_BEHAVIOR == NullUtil.getInstance().NULL_OBJECT) {
            CollidableWeaponBehavior.NULL_COLLIDABLE_WEAPON_BEHAVIOR = new CollidableWeaponBehavior(false);
        }
        return (CollidableWeaponBehavior) CollidableWeaponBehavior.NULL_COLLIDABLE_WEAPON_BEHAVIOR;
    }
    
    
    private boolean collided;
    @JsProperty
    protected CollisionHelper collisionHelper;
    
    @JsConstructor
    public CollidableWeaponBehavior(final boolean collidable)
    {
        super(collidable);

        this.setCollided(false);
        this.setCollidable(true);

        this.collisionHelper = CollidableHelperFactory.getInstance();
    }

    @JsMethod
    public void init(final AllBinaryLayer sourceLayerInterface)
    {
        this.setCollided(false);

        this.collisionHelper.setOwnerLayerInterface(sourceLayerInterface);
    }

    // TODO TWB Special Super Efficient Collision Processing
    @Override
    @JsMethod
    public boolean isCollision(final CollidableCompositeLayer ownerLayer, final CollidableCompositeLayer collisionLayer)
    {
        if (this.collisionHelper.isCollidable(collisionLayer))
        {
            if (ownerLayer.getGroupInterface()[0] != collisionLayer.getGroupInterface()[0])
            {
                // this.logUtil.putF("isCollision: " +
                // this.getGroupInterface().getGroupName() + "==" +
                // collisionLayer.getGroupInterface().getGroupName(), this, // damageUtil.IS_COLLISION);
                return super.isCollision(ownerLayer, collisionLayer);
            }
        }
        return false;
    }

    // TODO TWB Special Super Efficient Collision Processing
    @Override
    @JsMethod
    public void collide(final CollidableCompositeLayer ownerLayer, final CollidableCompositeLayer collisionLayer) throws Exception
    {
        // this.logUtil.putF(this.getName() + " collided with "
        // + collisionLayer.getName(), this, damageUtil.COLLIDE);
        //this.slow();

        super.collide(ownerLayer, collisionLayer);
        this.collided = true;
    }
    
    @Override
    @JsMethod
    public boolean isCollisionInterface(
            final CollidableCompositeLayer ownerLayer, final CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
    {
        if (this.collisionHelper.isCollidable((CollidableCompositeLayer) collidableInterfaceCompositeInterface))
        {
            final AllBinaryLayer layerInterface = (AllBinaryLayer) /*TS as unknown*/ collidableInterfaceCompositeInterface;

            // this.logUtil.putF("isCollision: " +
            // this.getGroupInterface().getGroupName() + "==" +
            // layerInterface.getGroupInterface().getGroupName(), // this, damageUtil.IS_COLLISION);
            if (ownerLayer.getGroupInterface()[0] != layerInterface.getGroupInterface()[0])
            {
                if (this.layerCollisionUtil.isCollision(ownerLayer, layerInterface))
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    @JsMethod
    public void collideInterface(final CollidableCompositeLayer ownerLayer, final CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
            throws Exception
    {
        // this.logUtil.putF(this.commonStrings.START, this, damageUtil.COLLIDE);
        
        //this.slow();
        
        // if (collidableInterfaceCompositeInterface was instance of
        // DamageableInterface) {
        final DamageableInterface ownerDamageableInterface = (DamageableInterface) /*TS as unknown*/ ownerLayer;
        final DamageableInterface damageableInterface = (DamageableInterface) /*TS as unknown*/ collidableInterfaceCompositeInterface;
        this.damageUtil.process(ownerDamageableInterface, damageableInterface);
        this.collided = true;
        // }
    }

    /*
    private void slow()
    {
        Movement movement = ((WeaponLayer) this.ownerLayer).getMovement();

        BasicVelocityProperties velocityProperties = 
            ((VelocityInterfaceCompositeInterface) movement).getVelocityProperties();

        BasicDecimal xBasicDecimal = velocityProperties.getVelocityXBasicDecimalP();
        BasicDecimal yBasicDecimal = velocityProperties.getVelocityYBasicDecimalP(); 

        if(xBasicDecimal.getUnscaled() > 1000 || yBasicDecimal.getUnscaled() > 1000)
        {
            xBasicDecimal.divide(2);
            yBasicDecimal.divide(2);
        }
    }
    */

    @Override
    @JsMethod
    public CollisionType getCollisionTypeWith(final AllBinaryLayer layerInterface)
    {
        return CollisionTypeFactory.getInstance().COLLISION;
    }

    /**
     * @return the collided
     */
    @JsMethod
    public boolean isCollided()
    {
        return this.collided;
    }

    /**
     * @param collided
     *            the collided to set
     */
    @JsMethod
    private void setCollided(final boolean collided)
    {
        this.collided = collided;
    }
}
