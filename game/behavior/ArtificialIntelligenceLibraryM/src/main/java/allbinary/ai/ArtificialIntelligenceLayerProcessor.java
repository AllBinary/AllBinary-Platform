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
package allbinary.ai;

import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.layer.LayerProcessor;

public class ArtificialIntelligenceLayerProcessor extends LayerProcessor
{
    public ArtificialIntelligenceLayerProcessor()
    {
    }

    public void process(AllBinaryLayerManager allBinaryLayerManager, AllBinaryLayer layerInterface,
            int index) throws Exception
    {

        ArtificialIntelligenceCompositeInterface artificialIntelligenceCompositeInterface = 
            (ArtificialIntelligenceCompositeInterface) layerInterface;

        artificialIntelligenceCompositeInterface.getArtificialIntelligenceInterface().processAI(
                allBinaryLayerManager);
    }

    public boolean isProcessorLayer(AllBinaryLayer layerInterface) 
        throws Exception
    {
        /*
        if (layerInterface instanceof ArtificialIntelligenceInterface)
        {
            throw new Exception("Uh oh you still have not gone to all composites");
        }
        */

        if (layerInterface.implmentsArtificialIntelligenceCompositeInterface())
        {
            return true;
        } else
        {
            return false;
        }
    }
}
