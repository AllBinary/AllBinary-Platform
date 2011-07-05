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
package allbinary.ai;

import org.allbinary.game.layer.CollidableCompositeLayer;

import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.layer.LayerProcessor;

public class OptimizedArtificialIntelligenceLayerProcessorForCollidableLayer extends LayerProcessor  {
	public OptimizedArtificialIntelligenceLayerProcessorForCollidableLayer() 
	{
	}

	public void process(AllBinaryLayerManager allBinaryLayerManager,
	        AllBinaryLayer layerInterface, int index) throws Exception {
	    CollidableCompositeLayer artificialIntelligenceCompositeInterface = 
			(CollidableCompositeLayer) layerInterface;

	    artificialIntelligenceCompositeInterface.getArtificialIntelligenceInterface().processAI(
							allBinaryLayerManager);
	}

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
