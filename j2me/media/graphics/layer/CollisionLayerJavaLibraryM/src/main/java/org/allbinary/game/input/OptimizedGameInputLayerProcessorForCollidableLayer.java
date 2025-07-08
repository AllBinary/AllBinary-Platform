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
    //protected final LogUtil logUtil = LogUtil.getInstance();

    
	public OptimizedGameInputLayerProcessorForCollidableLayer() {
            super(new OptimizedGameInputLayerManager());
	}

	public void process(final AllBinaryLayerManager allBinaryLayerManager,
	        final AllBinaryLayer layerInterface, final int index) throws Exception {
		final AllBinaryGameLayer gameInputInterface = (AllBinaryGameLayer) layerInterface;
                //logUtil.put(new StringMaker().append("processInput: ").append(layerInterface).toString(), this, gameInputStrings.PROCESS_INPUT);
		gameInputInterface.processInput(allBinaryLayerManager);
	}

        //private final String IS_PROCESSING_LAYER = "isProcessorLayer";
	public boolean isProcessorLayer(final AllBinaryLayer layerInterface) {
                //logUtil.put(new StringMaker().append("isProcessorLayer: ").append(layerInterface).toString(), this, IS_PROCESSING_LAYER);
		if (layerInterface.implmentsGameInputInterface()) {
			return true;
		} else {
			return false;
		}
	}

}
