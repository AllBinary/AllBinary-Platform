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
package allbinary.layer;

public class SingleLayerLayerProcessor 
extends LayerProcessor
{
    public SingleLayerLayerProcessor()
    {
    }

    public void process(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception
    {
        LayerInterfaceManager layerInterfaceManager = 
            this.getLayerInterfaceManager();

        this.process(allBinaryLayerManager, 
                (AllBinaryLayer) layerInterfaceManager.getLayerAt(0), 0);
    }
}
