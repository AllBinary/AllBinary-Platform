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
package org.allbinary.game.layer;

import abcs.logic.basic.NotImplemented;
import allbinary.ai.ArtificialIntelligenceInterface;
import allbinary.game.collision.CollidableBaseBehavior;
import allbinary.game.collision.CollidableInterfaceCompositeInterface;
import allbinary.game.layer.AllBinaryGameLayer;
import allbinary.graphics.Rectangle;
import allbinary.view.ViewPosition;

public class CollidableCompositeLayer 
extends AllBinaryGameLayer
implements CollidableInterfaceCompositeInterface
{
   private CollidableBaseBehavior collidableInferface;
    
    public CollidableCompositeLayer(
            Rectangle layerInfo, ViewPosition viewPosition, 
            CollidableBaseBehavior collidableInferface)
    {
        super(layerInfo, viewPosition);
        
        this.setCollidableInferface(collidableInferface);
    }

    public CollidableCompositeLayer(
            Rectangle layerInfo, ViewPosition viewPosition)
    {
        super(layerInfo, viewPosition);
    }

    public CollidableCompositeLayer(
            Rectangle layerInfo)
    {
        super(layerInfo);
    }

    public ArtificialIntelligenceInterface getArtificialIntelligenceInterface()
    throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }
    
    public CollidableBaseBehavior getCollidableInferface()
    {
        return collidableInferface;
    }

    public void setCollidableInferface(CollidableBaseBehavior collidableInferface)
    {
        this.collidableInferface = collidableInferface;
    }
    
    public boolean implmentsCollidableInterface()
    {
        return true;
    }
}
