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
package org.allbinary.game.input;

import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.layer.LayerProcessor;

public class GameInputLayerProcessor extends LayerProcessor {
    
	public GameInputLayerProcessor() {
            super(new GameInputLayerManager());
	}

        @Override
	public void process(final AllBinaryLayerManager allBinaryLayerManager,
	        final AllBinaryLayer layerInterface, final int index) throws Exception {
		final GameInputInterface gameInputInterface = (GameInputInterface) layerInterface;
		gameInputInterface.processInput(allBinaryLayerManager);
	}

        @Override
	public boolean isProcessorLayer(final AllBinaryLayer layerInterface) {
		if (layerInterface.implmentsGameInputInterface()) {
			return true;
		} else {
			return false;
		}
	}

}
