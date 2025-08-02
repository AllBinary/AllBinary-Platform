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

import org.allbinary.game.layer.CollidableCompositeLayer;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.layer.LayerManager;
import org.allbinary.layer.LayerProcessor;

public class OptimizedAllBinaryCollisionLayerProcessorForCollidableLayer
    extends LayerProcessor
{
    //protected final LogUtil logUtil = LogUtil.getInstance();


    public OptimizedAllBinaryCollisionLayerProcessorForCollidableLayer()
    {
        super(new OptimizedAllBinaryCollisionLayerManager());
    }

    @Override
    public void process(AllBinaryLayerManager allBinaryLayerManager,
            AllBinaryLayer layerInterface, int startIndex) throws Exception
    {
        // no physics here - just destroy them
        CollidableCompositeLayer collidableInterfaceCompositeInterface = (CollidableCompositeLayer) layerInterface;
        CollidableBaseBehavior collidableBase = collidableInterfaceCompositeInterface.getCollidableInferface();
        
        //logUtil.put(commonStrings.START + layerInterface.toString(), this, commonStrings.PROCESS);
        //logUtil.put(collidableInterfaceCompositeInterface.toString(), this, commonStrings.START);
        
        if (collidableBase.isCollidable())
        {

            final LayerManager layerManager = this.getLayerManager();

            final int size = layerManager.getSize();
            CollidableCompositeLayer collidableInterfaceCompositeInterface2;
            CollidableBaseBehavior collidableBase2;
            //logUtil.put("startIndex: " + startIndex + CommonSeps.getInstance().SPACE + CommonLabels.getInstance().TOTAL_LABEL + size, this, commonStrings.PROCESS);
            for (int index = startIndex + 1; index < size; index++)
            {
                collidableInterfaceCompositeInterface2 =
                    (CollidableCompositeLayer) layerManager.getLayerAt(index);

                collidableBase2 = 
                    collidableInterfaceCompositeInterface2.getCollidableInferface();
                
                //logUtil.put(layerInterface.toString(), this, commonStrings.START);
                //logUtil.put(collidableInterfaceCompositeInterface2.toString(), this, commonStrings.PROCESS);

                if (collidableBase2.isCollidable() && collidableBase.isCollision(collidableInterfaceCompositeInterface2))
                {
                    collidableBase.collide(collidableInterfaceCompositeInterface2);
                    collidableBase2.collide(collidableInterfaceCompositeInterface);
                }
            }

        }
    }

    //private final String IS_PROCESSING_LAYER = "isProcessorLayer";
    @Override
    public boolean isProcessorLayer(AllBinaryLayer layerInterface)
    {
        //logUtil.put(layerInterface.toString(), this, IS_PROCESSING_LAYER);
        if (layerInterface.implmentsCollidableInterface())
        {
            //logUtil.put("implmentsCollidableInterface - true: " + layerInterface.toString(), this, IS_PROCESSING_LAYER);
            return true;
        } else
        {
            //logUtil.put("implmentsCollidableInterface - false: " + layerInterface.toString(), this, IS_PROCESSING_LAYER);
            return false;
        }
    }
}
