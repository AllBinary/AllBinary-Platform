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
package allbinary.game.input;

import allbinary.game.layer.AllBinaryGameLayer;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.layer.LayerProcessor;

public class OptimizedGameInputLayerProcessorForCollidableLayer extends LayerProcessor {
	public OptimizedGameInputLayerProcessorForCollidableLayer() {
	}

	public void process(AllBinaryLayerManager allBinaryLayerManager,
	        AllBinaryLayer layerInterface, int index) throws Exception {
		AllBinaryGameLayer gameInputInterface = (AllBinaryGameLayer) layerInterface;
		gameInputInterface.processInput(allBinaryLayerManager);
	}

	public boolean isProcessorLayer(AllBinaryLayer layerInterface) {
		if (layerInterface.implmentsGameInputInterface()) {
			return true;
		} else {
			return false;
		}
	}

}
