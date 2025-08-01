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
    
    private boolean collidable = true;

    protected final CollidableCompositeLayer ownerLayer;
    
    public CollidableBaseBehavior(CollidableCompositeLayer ownerLayer, boolean collidable)
    {
        this.ownerLayer = ownerLayer;
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
    public boolean isCollidable()
    {
        return this.collidable;
    }

    // TODO TWB Special Super Efficient Collision Processing
    @Override
    public void collide(CollidableCompositeLayer allbinaryCollidableLayer)
            throws Exception
    {
        throw new Exception(new StringMaker().append(commonStrings.NOT_IMPLEMENTED).append(CommonLabels.getInstance().COLON_SEP).append(this.getClass().getName()).toString());
    }

    private final LayerCollisionUtil layerCollisionUtil = LayerCollisionUtil.getInstance();
    
    // TODO TWB Special Super Efficient Collision Processing
    @Override
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
            //logUtil.put("y: " + this.y + " " + this.getY2() + " other y: " + collisionLayer.getYP() + " " + collisionLayer.getY2(), this, damageUtil.IS_COLLISION);
            //logUtil.put("viewy: " + this.getViewPosition().getY() + " " + this.getViewPosition().getY2() + " other viewy: " + ((AllBinaryLayer) collisionLayer).getViewPosition().getY() + " " + ((AllBinaryLayer) collisionLayer).getViewPosition().getY2(), this, damageUtil.IS_COLLISION);
            
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
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }

    public void paint(final Graphics graphics) {
    
    }
    
    @Override
    public CollisionType getCollisionTypeWith(AllBinaryLayer layerInterface)
    {
        return CollisionTypeFactory.getInstance().NONE;
    }
}
