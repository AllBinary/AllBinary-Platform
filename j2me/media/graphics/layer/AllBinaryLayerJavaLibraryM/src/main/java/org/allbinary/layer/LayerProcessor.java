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

import org.allbinary.string.CommonStrings;

public class LayerProcessor implements LayerProcessorInterface
{
    private final LayerManager layerManager;

    public LayerProcessor(final LayerManager layerManager)
    {
        this.layerManager = layerManager;
    }

    /*
    public LayerProcessor(LayerManager layerManager)
    {
        this.setLayerManager(layerManager);
    }
    */
    
    public void process(AllBinaryLayerManager allBinaryLayerManager,
            AllBinaryLayer layerInterface, int index) throws Exception
    {
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }

    public boolean isProcessorLayer(AllBinaryLayer layerInterface)
            throws Exception
    {
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }

    public LayerManager getLayerManager()
    {
        return layerManager;
    }

    public void process(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception
    {
        LayerManager layerManager = this
                .getLayerManager();

        int size = layerManager.getSize();
        for (int index = 0; index < size; index++)
        {
            //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().TOTAL_LABEL + layerManager.getSize(), this, CommonStrings.getInstance().PROCESS));
            this.process(allBinaryLayerManager,
               (AllBinaryLayer) layerManager.getLayerAt(index), index);
        }
    }
}
