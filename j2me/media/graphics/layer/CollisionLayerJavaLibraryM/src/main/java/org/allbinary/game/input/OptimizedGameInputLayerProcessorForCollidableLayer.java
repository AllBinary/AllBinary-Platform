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

import org.allbinary.game.layer.AllBinaryGameLayer;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.layer.LayerProcessor;

public class OptimizedGameInputLayerProcessorForCollidableLayer extends LayerProcessor {
    
	public OptimizedGameInputLayerProcessorForCollidableLayer() {
            super(new OptimizedGameInputLayerManager());
	}

	public void process(final AllBinaryLayerManager allBinaryLayerManager,
	        final AllBinaryLayer layerInterface, final int index) throws Exception {
		final AllBinaryGameLayer gameInputInterface = (AllBinaryGameLayer) layerInterface;
                //LogUtil.put(LogFactory.getInstance(new StringMaker().append("processInput: ").append(layerInterface).toString(), this, "processInput"));
		gameInputInterface.processInput(allBinaryLayerManager);
	}

	public boolean isProcessorLayer(final AllBinaryLayer layerInterface) {
                //LogUtil.put(LogFactory.getInstance(new StringMaker().append("isProcessorLayer: ").append(layerInterface).toString(), this, "isProcessorLayer"));
		if (layerInterface.implmentsGameInputInterface()) {
			return true;
		} else {
			return false;
		}
	}

}
