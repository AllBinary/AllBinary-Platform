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
package allbinary.game.collision;

import org.allbinary.game.layer.CollidableCompositeLayer;

import abcs.logic.basic.NotImplemented;
import allbinary.layer.AllBinaryLayer;

public class CollidableBaseBehavior 
implements CollidableInterface
{
    private boolean collidable = true;

    protected final CollidableCompositeLayer ownerLayer;
    
    public CollidableBaseBehavior(CollidableCompositeLayer ownerLayer, boolean collidable)
    {
        this.ownerLayer = ownerLayer;
        this.collidable = collidable;
    }

    public String getName()
    {
        return this.getClass().getName();
    }
    
    public void setCollidable(boolean collidable)
    {
        this.collidable = collidable;
    }

    // If visible and a collidable object then
    public boolean isCollidable()
    {
        return this.collidable;
    }

    // TODO TWB Special Super Efficient Collision Processing
    public void collide(CollidableCompositeLayer allbinaryCollidableLayer)
            throws Exception
    {
        throw new Exception(NotImplemented.NAME + ": " + this.getClass().getName());
    }

    private final LayerCollisionUtil layerCollisionUtil = LayerCollisionUtil.getInstance();
    
    // TODO TWB Special Super Efficient Collision Processing
    public boolean isCollision(CollidableCompositeLayer collisionLayer)
    {
        return layerCollisionUtil.isCollision(this.ownerLayer, collisionLayer);
        /*
        if ()
        {
            return false;
        }
        else
        {
            //LogUtil.put(LogFactory.getInstance("y: " + this.y + " " + this.getY2() + " other y: " + collisionLayer.getY() + " " + collisionLayer.getY2(), this, "isCollision"));
            //LogUtil.put(LogFactory.getInstance("viewy: " + this.getViewPosition().getY() + " " + this.getViewPosition().getY2() + " other viewy: " + ((AllBinaryLayer) collisionLayer).getViewPosition().getY() + " " + ((AllBinaryLayer) collisionLayer).getViewPosition().getY2(), this, "isCollision"));
            
            return true;
        }
        */
    }

    public boolean isCollision(CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
    {
        return layerCollisionUtil.isCollision(ownerLayer, (AllBinaryLayer) collidableInterfaceCompositeInterface);
        /*
        if ()
        {
            return true;
        }
        return false;
        */
    }

    public void collide(CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
            throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }

    public CollisionType getCollisionTypeWith(AllBinaryLayer layerInterface)
    {
        return CollisionTypeFactory.getInstance().NONE;
    }
}
