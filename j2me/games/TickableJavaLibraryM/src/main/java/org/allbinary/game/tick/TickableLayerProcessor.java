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
package org.allbinary.game.tick;

import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.layer.LayerProcessor;

public class TickableLayerProcessor extends LayerProcessor
{
    public TickableLayerProcessor()
    {
        super(new TickableLayerManager());
    }

    @Override
    public void process(AllBinaryLayerManager allBinaryLayerManager,
            AllBinaryLayer layerInterface, int index) throws Exception
    {
        // no physics here - just destroy them
        TickableInterface tickableInterface = (TickableInterface) layerInterface;
        tickableInterface.processTick(allBinaryLayerManager);
    }

    @Override
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
