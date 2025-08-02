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
package org.allbinary.ai;

import org.allbinary.game.layer.CollidableCompositeLayer;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.layer.LayerProcessor;

public class OptimizedArtificialIntelligenceLayerProcessorForCollidableLayer extends LayerProcessor  {
    
	public OptimizedArtificialIntelligenceLayerProcessorForCollidableLayer() 
	{
            super(new OptimizedArtificialIntelligenceLayerManager());
	}

        @Override
	public void process(AllBinaryLayerManager allBinaryLayerManager,
	        AllBinaryLayer layerInterface, int index) throws Exception {
	    CollidableCompositeLayer artificialIntelligenceCompositeInterface = 
			(CollidableCompositeLayer) layerInterface;

	    artificialIntelligenceCompositeInterface.getArtificialIntelligenceInterface().processAI(
							allBinaryLayerManager);
	}

        @Override
	public boolean isProcessorLayer(AllBinaryLayer layerInterface) 
      throws Exception
   {
      /*
      if(layerInterface instanceof ArtificialIntelligenceInterface)
      {
         throw new Exception("Uh oh you still have not gone to all composites");
      }
      */

		if (layerInterface.implmentsArtificialIntelligenceCompositeInterface()) {
		    
			return true;
		}
		else
		{
			return false;
		}
	}
}
