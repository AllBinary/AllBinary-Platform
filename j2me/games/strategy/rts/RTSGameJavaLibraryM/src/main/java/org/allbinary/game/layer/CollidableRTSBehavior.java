
/*
 * AllBinary Open License Version 1
 * Copyright (c) 2003 AllBinary
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
package org.allbinary.game.layer;

import org.allbinary.game.collision.CollisionType;
import org.allbinary.game.collision.CollisionTypeFactory;
import org.allbinary.game.layer.special.CollidableDestroyableDamageableBehavior;

public class CollidableRTSBehavior
extends CollidableDestroyableDamageableBehavior 
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    public CollidableRTSBehavior(final CollidableCompositeLayer ownerLayer, final boolean collidable)
    {
        super(ownerLayer, collidable);
    }

    //public void collide(CollidableCompositeLayer collidableInterfaceCompositeInterface)
    //public void collide(CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
    public boolean isCollision(final CollidableCompositeLayer collidableInterfaceCompositeInterface)
        {
            final CollisionTypeFactory collisionTypeFactory = CollisionTypeFactory.getInstance();
            final CollisionType collisionType = 
                collidableInterfaceCompositeInterface.getCollidableInferface().getCollisionTypeWith(this.ownerLayer);

            // logUtil.put("collisionType: ").append(
            // collisionType.toString(), this, damageUtil.COLLIDE);

            if (collisionType == collisionTypeFactory.PICKUP)
            {
            }
            else if (collisionType == collisionTypeFactory.COLLISION)
            {
                return super.isCollision(collidableInterfaceCompositeInterface);
            }
            else
            {
                if (collidableInterfaceCompositeInterface.getX2() <= this.ownerLayer.getXP()
                        || collidableInterfaceCompositeInterface.getY2() <= this.ownerLayer.getYP()
                        || collidableInterfaceCompositeInterface.getYP() >= this.ownerLayer.getY2()
                        || collidableInterfaceCompositeInterface.getXP() >= this.ownerLayer.getX2())
                {
                    return false;
                } else
                {
                    /*
                     * logUtil.put( "y: ").append(this.y).append(" ").append(
                     * this.getY2()).append(" other y: ").append(collisionLayer.getYP()).append(" ").append(
                     * collisionLayer.getY2(), this, damageUtil.IS_COLLISION);
                     * logUtil.put( "x: ").append(this.x).append(" ").append(
                     * this.getX2()).append(" other x: ").append(collisionLayer.getXP()).append(" ").append(
                     * collisionLayer.getX2(), this, damageUtil.IS_COLLISION);
                     */
                    // logUtil.put("viewy: ").append(
                    // this.getViewPosition().getY()).append(" ").append(
                    // this.getViewPosition().getY2()).append(" other viewy: ").append(
                    // ((AllBinaryLayer) collisionLayer).getViewPosition().getY()).append(
                    // " ").append(((AllBinaryLayer)
                    // collisionLayer).getViewPosition().getY2(), this,
                    // damageUtil.IS_COLLISION);
                    return true;
                }
            }
            return false;
        }

        public void collide(final CollidableCompositeLayer collidableInterfaceCompositeInterface)
            throws Exception
        {
            final CollisionTypeFactory collisionTypeFactory = CollisionTypeFactory.getInstance();
            final CollisionType collisionType = 
                collidableInterfaceCompositeInterface.getCollidableInferface().getCollisionTypeWith(this.ownerLayer);

            //logUtil.put("collisionType: ").append(collisionType.toString(), this, damageUtil.COLLIDE);

            if (collisionType == collisionTypeFactory.PICKUP)
            {
            }
            else if (collisionType == collisionTypeFactory.COLLISION)
            {
                //Enemy weapons cause damage on collision
                //logUtil.put(this.getName()).append(" collided with ").append(((CollidableDestroyableDamageableLayer) collidableInterface).getName(), this, damageUtil.IS_COLLISION);
                //logUtil.put("isCollision: ").append(this.getGroupInterface().getGroupName()).append("==").append(((CollidableDestroyableDamageableLayer) collidableInterface).getGroupInterface().getGroupName(), this, damageUtil.IS_COLLISION);
                super.collide(collidableInterfaceCompositeInterface);
            }
            else
            {
                this.collideNone(collidableInterfaceCompositeInterface);
            }
        }

        protected void collideNone(final CollidableCompositeLayer collidableInterface)
            throws Exception
        {
        }
}
