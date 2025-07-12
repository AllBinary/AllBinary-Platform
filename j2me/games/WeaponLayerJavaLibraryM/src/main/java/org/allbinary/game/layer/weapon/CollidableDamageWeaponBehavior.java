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
    
    public CollidableDamageWeaponBehavior(CollidableCompositeLayer ownerLayer, boolean collidable)
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
              //logUtil.put("isCollision: " + this.getGroupInterface().getGroupName() + "==" + collisionLayer.getGroupInterface().getGroupName(), this, damageUtil.IS_COLLISION);
              return super.isCollision(collisionLayer);
          }
       }
       return false;
    }

    // TODO TWB Special Super Efficient Collision Processing
    public void collide(CollidableCompositeLayer collisionLayer)
            throws Exception
    {
       //logUtil.put(this.getName() + " collided with " + collisionLayer.getName(), this, damageUtil.COLLIDE);
       super.collide(collisionLayer);
       this.collided = true;
    }

    private final LayerCollisionUtil layerCollisionUtil = LayerCollisionUtil.getInstance();
    
    public boolean isCollision(CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
    {
       if (this.collisionHelper.isCollidable((CollidableCompositeLayer) collidableInterfaceCompositeInterface))
       {
          final AllBinaryLayer layerInterface = (AllBinaryLayer) collidableInterfaceCompositeInterface;

          //logUtil.put("isCollision: " + this.getGroupInterface().getGroupName() + "==" + layerInterface.getGroupInterface().getGroupName(), this, damageUtil.IS_COLLISION);
          if (this.ownerLayer.getGroupInterface()[0] != layerInterface.getGroupInterface()[0])
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
       //logUtil.put(commonStrings.START, this, damageUtil.COLLIDE);
       // if (collidableInterfaceCompositeInterface was instance of DamageableInterface) {
       damageUtil.process((DamageableInterface) this.ownerLayer, (DamageableInterface) collidableInterfaceCompositeInterface);
       this.collided = true;
    // }
    }

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
     * @param collided the collided to set
     */
    private void setCollided(boolean collided)
    {
        this.collided = collided;
    }
}
