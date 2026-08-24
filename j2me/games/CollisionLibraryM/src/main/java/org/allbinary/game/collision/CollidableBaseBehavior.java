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

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Graphics;

import org.allbinary.game.layer.CollidableCompositeLayer;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class CollidableBaseBehavior 
implements CollidableInterface
{
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    protected final LayerCollisionUtil layerCollisionUtil = LayerCollisionUtil.getInstance();
    
    //TWB - move to CollidableCompositeLayer
    private boolean collidable = true;
    
    @JsConstructor
    public CollidableBaseBehavior(final boolean collidable)
    {
        this.collidable = collidable;
    }
    
    @JsMethod
    public void update() {
        
    }

    @Override
    @JsMethod
    public String getName()
    {
        return this.getClass().getName();
    }
    
    @JsMethod
    public void setCollidable(boolean collidable)
    {
        this.collidable = collidable;
    }

    // If visible and a collidable object then
    @Override
    @JsMethod
    public boolean isCollidable(final CollidableCompositeLayer ownerLayer)
    {
        return this.collidable;
    }

    // TODO TWB Special Super Efficient Collision Processing
    @Override
    @JsMethod
    public void collide(final CollidableCompositeLayer ownerLayer, final CollidableCompositeLayer allbinaryCollidableLayer)
            throws Exception
    {
        throw new Exception(new StringMaker().append(this.commonStrings.NOT_IMPLEMENTED).append(CommonLabels.getInstance().COLON_SEP).append(this.getClass().getName()).toString());
    }
    
    // TODO TWB Special Super Efficient Collision Processing
    @Override
    @JsMethod
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

    @JsMethod
    public boolean isCollisionInterface(final CollidableCompositeLayer ownerLayer, final CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
    {
        return this.layerCollisionUtil.isCollision(ownerLayer, (AllBinaryLayer) /*TS as unknown*/ collidableInterfaceCompositeInterface);
        /*
        if ()
        {
            return true;
        }
        return false;
        */
    }

    @JsMethod
    public void collideInterface(final CollidableCompositeLayer ownerLayer, final CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
            throws Exception
    {
        throw new Exception(this.commonStrings.NOT_IMPLEMENTED);
    }

    @JsMethod
    public void paint(final CollidableCompositeLayer ownerLayer, final Graphics graphics) {
    
    }
    
    @Override
    @JsMethod
    public CollisionType getCollisionTypeWith(AllBinaryLayer layerInterface)
    {
        return CollisionTypeFactory.getInstance().NONE;
    }
}
