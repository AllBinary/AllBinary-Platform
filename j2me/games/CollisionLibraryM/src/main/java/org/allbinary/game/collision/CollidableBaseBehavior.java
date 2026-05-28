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
package org.allbinary.game.collision;

import javax.microedition.lcdui.Graphics;

import org.allbinary.game.layer.CollidableCompositeLayer;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;

public class CollidableBaseBehavior 
implements CollidableInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    //TWB - move to CollidableCompositeLayer
    private boolean collidable = true;
    
    public CollidableBaseBehavior(final boolean collidable)
    {
        this.collidable = collidable;
    }
    
    public void update() {
        
    }

    @Override
    public String getName()
    {
        return this.getClass().getName();
    }
    
    public void setCollidable(boolean collidable)
    {
        this.collidable = collidable;
    }

    // If visible and a collidable object then
    @Override
    public boolean isCollidable(final CollidableCompositeLayer ownerLayer)
    {
        return this.collidable;
    }

    // TODO TWB Special Super Efficient Collision Processing
    @Override
    public void collide(final CollidableCompositeLayer ownerLayer, final CollidableCompositeLayer allbinaryCollidableLayer)
            throws Exception
    {
        throw new Exception(new StringMaker().append(this.commonStrings.NOT_IMPLEMENTED).append(CommonLabels.getInstance().COLON_SEP).append(this.getClass().getName()).toString());
    }

    private final LayerCollisionUtil layerCollisionUtil = LayerCollisionUtil.getInstance();
    
    // TODO TWB Special Super Efficient Collision Processing
    @Override
    public boolean isCollision(final CollidableCompositeLayer ownerLayer, final CollidableCompositeLayer collisionLayer)
    {
        return this.layerCollisionUtil.isCollision(ownerLayer, collisionLayer);
        /*
        if ()
        {
            return false;
        }
        else
        {
            //this.logUtil.putF("y: " + this.y + " " + this.getY2() + " other y: " + collisionLayer.getYP() + " " + collisionLayer.getY2(), this, damageUtil.IS_COLLISION);
            //this.logUtil.putF("viewy: " + this.getViewPosition().getY() + " " + this.getViewPosition().getY2() + " other viewy: " + ((AllBinaryLayer) collisionLayer).getViewPosition().getY() + " " + ((AllBinaryLayer) collisionLayer).getViewPosition().getY2(), this, damageUtil.IS_COLLISION);
            
            return true;
        }
        */
    }

    public boolean isCollisionInterface(final CollidableCompositeLayer ownerLayer, final CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
    {
        return this.layerCollisionUtil.isCollision(ownerLayer, (AllBinaryLayer) collidableInterfaceCompositeInterface);
        /*
        if ()
        {
            return true;
        }
        return false;
        */
    }

    public void collideInterface(final CollidableCompositeLayer ownerLayer, final CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
            throws Exception
    {
        throw new Exception(this.commonStrings.NOT_IMPLEMENTED);
    }

    public void paint(final CollidableCompositeLayer ownerLayer, final Graphics graphics) {
    
    }
    
    @Override
    public CollisionType getCollisionTypeWith(AllBinaryLayer layerInterface)
    {
        return CollisionTypeFactory.getInstance().NONE;
    }
}
