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
package org.allbinary.game.collision.layer;

import org.allbinary.game.collision.CollidableInterfaceCompositeInterface;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.layer.LayerProcessor;

public class CollidableInterfaceLayerProcessor extends LayerProcessor {

    public CollidableInterfaceLayerProcessor() {
        super(new CollidableInterfaceLayerManager());
    }

    @Override
    public void process(AllBinaryLayerManager allBinaryLayerManager,
        AllBinaryLayer layerInterface, int index)
        throws Exception {
        //no physics here - just destroy them
        CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface =
            (CollidableInterfaceCompositeInterface) layerInterface;
        if (collidableInterfaceCompositeInterface.getCollidableInferface().isCollidable()) {
            AllBinaryCollisionManager.getInstance().process(
                this.getLayerManager(), collidableInterfaceCompositeInterface, index);
        }
    }

    @Override
    public boolean isProcessorLayer(AllBinaryLayer layerInterface) {
        if (layerInterface.implmentsCollidableInterface()) {
            return true;
        } else {
            return false;
        }
    }
}
