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

import org.allbinary.graphics.opengles.OpenGLFeatureUtil;

import allbinary.layer.AllBinaryLayer;

public class LayerCollisionUtil
{
    private static final LayerCollisionUtil instance = new LayerCollisionUtil();
    
    public static LayerCollisionUtil getInstance()
    {
        return instance;
    }
    
    private final CollisionProcessor collisionProcessor;
    
    private LayerCollisionUtil()
    {
        if (OpenGLFeatureUtil.getInstance().isAnyThreed())
        {
            this.collisionProcessor = new CollisionThreedProcessor();
        }
        else
        {
            this.collisionProcessor = new Collision2DProcessor();
        }
    }

    public boolean isCollision(AllBinaryLayer myLayer, AllBinaryLayer myLayer2)
    {
        return this.collisionProcessor.isCollision(myLayer, myLayer2);
    }
    //collisionLayer.getX2() <= this.ownerLayer.getX() || collisionLayer.getY2() <= this.ownerLayer.getY() || collisionLayer.getY() >= this.ownerLayer.getY2() || collisionLayer.getX() >= this.ownerLayer.getX2()
    
    //return RectangleCollisionUtil.isCollision( myLayer.getX(),
    //myLayer.getY(), myLayer.getX2(), myLayer.getY2(), myLayer2.getX(),
    //myLayer2.getY(), myLayer2.getX2(), myLayer2.getY2() );
}
