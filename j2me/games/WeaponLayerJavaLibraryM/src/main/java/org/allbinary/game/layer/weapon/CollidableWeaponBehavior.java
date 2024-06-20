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

import org.allbinary.game.layer.CollidableCompositeLayer;

import org.allbinary.game.collision.CollidableHelperFactory;
import org.allbinary.game.collision.CollidableInterfaceCompositeInterface;
import org.allbinary.game.collision.CollisionHelper;
import org.allbinary.game.collision.CollisionType;
import org.allbinary.game.collision.CollisionTypeFactory;
import org.allbinary.game.collision.LayerCollisionUtil;
import org.allbinary.game.combat.damage.DamageUtil;
import org.allbinary.game.combat.damage.DamageableInterface;
import org.allbinary.game.layer.special.CollidableDestroyableDamageableBehavior;
import org.allbinary.layer.AllBinaryLayer;

public class CollidableWeaponBehavior extends CollidableDestroyableDamageableBehavior
{
    private boolean collided;
    protected CollisionHelper collisionHelper;

    public CollidableWeaponBehavior(CollidableCompositeLayer ownerLayer, boolean collidable)
    {
        super(ownerLayer, collidable);

        this.setCollided(false);
        this.setCollidable(true);

        this.collisionHelper = CollidableHelperFactory.getInstance();
    }

    public void init(AllBinaryLayer sourceLayerInterface)
    {
        this.setCollided(false);

        this.collisionHelper.setOwnerLayerInterface(sourceLayerInterface);
    }

    // TODO TWB Special Super Efficient Collision Processing
    public boolean isCollision(CollidableCompositeLayer collisionLayer)
    {
        if (this.collisionHelper.isCollidable(collisionLayer))
        {
            if (this.ownerLayer.getGroupInterface()[0] != collisionLayer.getGroupInterface()[0])
            {
                // LogUtil.put(LogFactory.getInstance("isCollision: " +
                // this.getGroupInterface().getGroupName() + "==" +
                // collisionLayer.getGroupInterface().getGroupName(), this,
                // "isCollision"));
                return super.isCollision(collisionLayer);
            }
        }
        return false;
    }

    // TODO TWB Special Super Efficient Collision Processing
    public void collide(CollidableCompositeLayer collisionLayer) throws Exception
    {
        // LogUtil.put(LogFactory.getInstance(this.getName() + " collided with "
        // + collisionLayer.getName(), this, "collide"));
        //this.slow();

        super.collide(collisionLayer);
        this.collided = true;
    }

    private final LayerCollisionUtil layerCollisionUtil = LayerCollisionUtil.getInstance();
    
    public boolean isCollision(
            CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
    {
        if (this.collisionHelper.isCollidable((CollidableCompositeLayer) collidableInterfaceCompositeInterface))
        {
            final AllBinaryLayer layerInterface = (AllBinaryLayer) collidableInterfaceCompositeInterface;

            // LogUtil.put(LogFactory.getInstance("isCollision: " +
            // this.getGroupInterface().getGroupName() + "==" +
            // layerInterface.getGroupInterface().getGroupName(),
            // this, "isCollision"));
            if (this.ownerLayer.getGroupInterface() != layerInterface.getGroupInterface())
            {
                if (layerCollisionUtil.isCollision(this.ownerLayer, layerInterface))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void collide(CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
            throws Exception
    {
        // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "collide"));
        
        //this.slow();
        
        // if (collidableInterfaceCompositeInterface was instance of
        // DamageableInterface) {
        DamageUtil.process((DamageableInterface) this.ownerLayer,
                (DamageableInterface) collidableInterfaceCompositeInterface);
        this.collided = true;
        // }
    }

    /*
    private void slow()
    {
        Movement movement = ((WeaponLayer) this.ownerLayer).getMovement();

        BasicVelocityProperties velocityProperties = 
            ((VelocityInterfaceCompositeInterface) movement).getVelocityProperties();

        BasicDecimal xBasicDecimal = velocityProperties.getVelocityXBasicDecimal();
        BasicDecimal yBasicDecimal = velocityProperties.getVelocityYBasicDecimal(); 

        if(xBasicDecimal.getUnscaled() > 1000 || yBasicDecimal.getUnscaled() > 1000)
        {
            xBasicDecimal.divide(2);
            yBasicDecimal.divide(2);
        }
    }
    */

    public CollisionType getCollisionTypeWith(AllBinaryLayer layerInterface)
    {
        return CollisionTypeFactory.getInstance().COLLISION;
    }

    /**
     * @return the collided
     */
    public boolean isCollided()
    {
        return collided;
    }

    /**
     * @param collided
     *            the collided to set
     */
    private void setCollided(boolean collided)
    {
        this.collided = collided;
    }
}
