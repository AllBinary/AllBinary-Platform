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

import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.layer.LayerInterfaceManager;
import allbinary.layer.LayerProcessor;

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
        
        //LogUtil.put(LogFactory.getInstance(collidableInterfaceCompositeInterface.toString(), this, CommonStrings.getInstance().START));
        
        if (collidableBase.isCollidable())
        {

            LayerInterfaceManager layerInterfaceManager = this.getLayerInterfaceManager();

            int size = layerInterfaceManager.getSize();
            //LogUtil.put(LogFactory.getInstance("startIndex: " + startIndex + CommonStrings.getInstance().SPACE + CommonStrings.getInstance().TOTAL_LABEL + size, this, CommonStrings.getInstance().PROCESS));
            for (int index = startIndex + 1; index < size; index++)
            {
                CollidableCompositeLayer collidableInterfaceCompositeInterface2 =
                    (CollidableCompositeLayer) layerInterfaceManager.getLayerAt(index);

                CollidableBaseBehavior collidableBase2 = 
                    collidableInterfaceCompositeInterface2.getCollidableInferface();

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
        if (layerInterface.implmentsCollidableInterface())
        {
            return true;
        } else
        {
            return false;
        }
    }
}
