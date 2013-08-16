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
package allbinary.game.input;

import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.layer.LayerProcessor;

public class GameInputLayerProcessor extends LayerProcessor {
	public GameInputLayerProcessor() {
	}

	public void process(AllBinaryLayerManager allBinaryLayerManager,
	        AllBinaryLayer layerInterface, int index) throws Exception {
		GameInputInterface gameInputInterface = (GameInputInterface) layerInterface;
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
