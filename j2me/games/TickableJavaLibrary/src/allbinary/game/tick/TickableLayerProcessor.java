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

import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.layer.LayerProcessor;

public class TickableLayerProcessor extends LayerProcessor
{
    public TickableLayerProcessor()
    {
    }

    public void process(AllBinaryLayerManager allBinaryLayerManager,
            AllBinaryLayer layerInterface, int index) throws Exception
    {
        // no physics here - just destroy them
        TickableInterface tickableInterface = (TickableInterface) layerInterface;
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
