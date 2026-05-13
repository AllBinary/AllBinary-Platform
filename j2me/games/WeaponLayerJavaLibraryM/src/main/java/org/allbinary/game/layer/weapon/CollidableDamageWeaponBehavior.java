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

import org.allbinary.game.collision.CollidableHelperFactory;
import org.allbinary.game.collision.CollidableInterfaceCompositeInterface;
import org.allbinary.game.collision.CollisionHelper;
import org.allbinary.game.collision.CollisionType;
import org.allbinary.game.collision.CollisionTypeFactory;
import org.allbinary.game.collision.LayerCollisionUtil;
import org.allbinary.game.combat.damage.DamageableInterface;
import org.allbinary.game.layer.CollidableCompositeLayer;
import org.allbinary.game.layer.special.CollidableDestroyableDamageableBehavior;
import org.allbinary.layer.AllBinaryLayer;

public class CollidableDamageWeaponBehavior 
extends CollidableDestroyableDamageableBehavior
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    
    private boolean collided;
    protected CollisionHelper collisionHelper;
    
    public CollidableDamageWeaponBehavior(boolean collidable)
    {
        super(collidable);
        
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
    @Override
    public boolean isCollision(final CollidableCompositeLayer ownerLayer, CollidableCompositeLayer collisionLayer)
    {
       if (this.collisionHelper.isCollidable(collisionLayer))
       {
          if (ownerLayer.getGroupInterface()[0] != collisionLayer.getGroupInterface()[0])
          {
              //this.logUtil.putF("isCollision: " + this.getGroupInterface().getGroupName() + "==" + collisionLayer.getGroupInterface().getGroupName(), this, damageUtil.IS_COLLISION);
              return super.isCollision(ownerLayer, collisionLayer);
          }
       }
       return false;
    }

    // TODO TWB Special Super Efficient Collision Processing
    @Override
    public void collide(final CollidableCompositeLayer ownerLayer, CollidableCompositeLayer collisionLayer)
            throws Exception
    {
       //this.logUtil.putF(this.getName() + " collided with " + collisionLayer.getName(), this, damageUtil.COLLIDE);
       super.collide(ownerLayer, collisionLayer);
       this.collided = true;
    }

    private final LayerCollisionUtil layerCollisionUtil = LayerCollisionUtil.getInstance();
    
    @Override
    public boolean isCollisionInterface(final CollidableCompositeLayer ownerLayer, CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
    {
       if (this.collisionHelper.isCollidable((CollidableCompositeLayer) collidableInterfaceCompositeInterface))
       {
          final AllBinaryLayer layerInterface = (AllBinaryLayer) collidableInterfaceCompositeInterface;

          //this.logUtil.putF("isCollision: " + this.getGroupInterface().getGroupName() + "==" + layerInterface.getGroupInterface().getGroupName(), this, damageUtil.IS_COLLISION);
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
    public void collideInterface(final CollidableCompositeLayer ownerLayer, CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
            throws Exception
    {
       //this.logUtil.putF(this.commonStrings.START, this, damageUtil.COLLIDE);
       // if (collidableInterfaceCompositeInterface was instance of DamageableInterface) {
       this.damageUtil.process((DamageableInterface) ownerLayer, (DamageableInterface) collidableInterfaceCompositeInterface);
       this.collided = true;
    // }
    }

    @Override
    public CollisionType getCollisionTypeWith(AllBinaryLayer layerInterface)
    {
       return CollisionTypeFactory.getInstance().COLLISION;
    }
    
    /**
     * @return the collided
     */
    public boolean isCollided()
    {
        return this.collided;
    }

    /**
     * @param collided the collided to set
     */
    private void setCollided(boolean collided)
    {
        this.collided = collided;
    }
}
