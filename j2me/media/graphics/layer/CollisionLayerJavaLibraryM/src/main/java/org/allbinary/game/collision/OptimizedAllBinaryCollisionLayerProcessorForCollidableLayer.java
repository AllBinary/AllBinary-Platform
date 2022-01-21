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
import org.allbinary.layer.LayerInterfaceManager;
import org.allbinary.layer.LayerProcessor;

public class OptimizedAllBinaryCollisionLayerProcessorForCollidableLayer
    extends LayerProcessor
{

    public OptimizedAllBinaryCollisionLayerProcessorForCollidableLayer()
    {
    }

    public void process(AllBinaryLayerManager allBinaryLayerManager,
            AllBinaryLayer layerInterface, int startIndex) throws Exception
    {
        // no physics here - just destroy them
        CollidableCompositeLayer collidableInterfaceCompositeInterface = (CollidableCompositeLayer) layerInterface;
        CollidableBaseBehavior collidableBase = collidableInterfaceCompositeInterface.getCollidableInferface();
        
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START + layerInterface.toString(), this, CommonStrings.getInstance().PROCESS));
        //LogUtil.put(LogFactory.getInstance(collidableInterfaceCompositeInterface.toString(), this, CommonStrings.getInstance().START));
        
        if (collidableBase.isCollidable())
        {

            final LayerInterfaceManager layerInterfaceManager = this.getLayerInterfaceManager();

            final int size = layerInterfaceManager.getSize();
            CollidableCompositeLayer collidableInterfaceCompositeInterface2;
            CollidableBaseBehavior collidableBase2;
            //LogUtil.put(LogFactory.getInstance("startIndex: " + startIndex + CommonSeps.getInstance().SPACE + CommonLabels.getInstance().TOTAL_LABEL + size, this, CommonStrings.getInstance().PROCESS));
            for (int index = startIndex + 1; index < size; index++)
            {
                collidableInterfaceCompositeInterface2 =
                    (CollidableCompositeLayer) layerInterfaceManager.getLayerAt(index);

                collidableBase2 = 
                    collidableInterfaceCompositeInterface2.getCollidableInferface();
                
                //LogUtil.put(LogFactory.getInstance(layerInterface.toString(), this, CommonStrings.getInstance().START));
                //LogUtil.put(LogFactory.getInstance(collidableInterfaceCompositeInterface2.toString(), this, CommonStrings.getInstance().PROCESS));

                if (collidableBase2.isCollidable() && collidableBase.isCollision(collidableInterfaceCompositeInterface2))
                {
                    collidableBase.collide(collidableInterfaceCompositeInterface2);
                    collidableBase2.collide(collidableInterfaceCompositeInterface);
                }
            }

        }
    }

    public boolean isProcessorLayer(AllBinaryLayer layerInterface)
    {
        //LogUtil.put(LogFactory.getInstance(layerInterface.toString(), this, "isProcessorLayer"));
        if (layerInterface.implmentsCollidableInterface())
        {
            //LogUtil.put(LogFactory.getInstance("implmentsCollidableInterface - true: " + layerInterface.toString(), this, "isProcessorLayer"));
            return true;
        } else
        {
            //LogUtil.put(LogFactory.getInstance("implmentsCollidableInterface - false: " + layerInterface.toString(), this, "isProcessorLayer"));
            return false;
        }
    }
}
