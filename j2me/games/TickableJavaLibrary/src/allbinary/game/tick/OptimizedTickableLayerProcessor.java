/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: August 27, 2006
 *
 *
 * Modified By         When       ?
 *
 */
package allbinary.game.tick;

import allbinary.game.layer.AllBinaryGameLayer;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.layer.LayerProcessor;

public class OptimizedTickableLayerProcessor extends LayerProcessor
{

    public OptimizedTickableLayerProcessor()
    {
    }

    public void process(AllBinaryLayerManager allBinaryLayerManager,
            AllBinaryLayer layerInterface, int index) throws Exception
    {
        // no physics here - just destroy them
        AllBinaryGameLayer tickableInterface = (AllBinaryGameLayer) layerInterface;
        tickableInterface.processTick(allBinaryLayerManager);
    }
    
    public boolean isProcessorLayer(AllBinaryLayer layerInterface)
    {
        if (layerInterface.implmentsTickableInterface())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
