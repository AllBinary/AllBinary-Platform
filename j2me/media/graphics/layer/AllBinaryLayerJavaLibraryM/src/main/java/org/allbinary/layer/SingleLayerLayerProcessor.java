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
package org.allbinary.layer;

public class SingleLayerLayerProcessor 
extends LayerProcessor
{
    public SingleLayerLayerProcessor()
    {
        super(new SingleLayerLayerManager());
    }

    @Override
    public void process(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception
    {
        LayerManager layerManager = 
            this.getLayerManager();

        this.process(allBinaryLayerManager, 
                (AllBinaryLayer) layerManager.getLayerAt(0), 0);
    }
}
