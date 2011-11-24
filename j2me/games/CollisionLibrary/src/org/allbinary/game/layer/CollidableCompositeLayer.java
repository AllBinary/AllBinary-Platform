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
import abcs.logic.basic.string.CommonSeps;
import abcs.logic.communication.log.ForcedLogUtil;
import allbinary.ai.ArtificialIntelligenceInterface;
import allbinary.game.collision.CollidableBaseBehavior;
import allbinary.game.collision.CollidableInterfaceCompositeInterface;
import allbinary.game.layer.AllBinaryGameLayer;
import allbinary.graphics.Rectangle;
import allbinary.view.ViewPosition;

/*
 * The Game Layers/Objects requirements change a bunch from game to game.
 * As such Game Layers/Objects are very complex in nature.
 * As a result it is best to create a game specific composite game layer for a specific game.
 * Although, I have 3 classes that break this rule because I have not thought of composites that fit yet.
 * The 3 classes are MultiPlayerGameLayer -> CollidableDestroyableDamageableLayer -> CollidableCompositeLayer.
 * In the future these 3 classes should probably go away and be pushed into game Layer/Object composites/properties.
 * I will probably always have one magical game class for new features that don't fit into a composite yet regardless.
 * For now just think of the 3 classes (MultiPlayerGameLayer -> CollidableDestroyableDamageableLayer -> CollidableCompositeLayer)
 * as SpecialGameLayer or MagicalGameLayer.
 */
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

    public CollidableCompositeLayer(Rectangle layerInfo)
    {
        super(layerInfo);
    }

    public ArtificialIntelligenceInterface getArtificialIntelligenceInterface()
    {
        ForcedLogUtil.log(NotImplemented.NAME, this);
        return null;
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
    
    public String toString()
    {
        StringBuilder stringBuffer = new StringBuilder();
        
        CommonSeps commonSeps = CommonSeps.getInstance();
        
        stringBuffer.append(super.toString());
        stringBuffer.append(commonSeps.NEW_LINE);
        stringBuffer.append(this.getCollidableInferface().toString());
        //stringBuffer.append(commonSeps.NEW_LINE);
        //stringBuffer.append(this.getArtificialIntelligenceInterface().toString());
        
        return stringBuffer.toString();
    }    
}
