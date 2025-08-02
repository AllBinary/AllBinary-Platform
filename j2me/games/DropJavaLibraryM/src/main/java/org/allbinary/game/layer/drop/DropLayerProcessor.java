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
package org.allbinary.game.layer.drop;

import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.layer.BasicLayerProcessor;
import org.allbinary.util.BasicArrayList;

public class DropLayerProcessor extends BasicLayerProcessor
{
    private static final BasicLayerProcessor LAYER_PROCESSOR = new DropLayerProcessor();

    private DropLayerProcessor()
    {
    }

    public static BasicLayerProcessor getInstance()
    {
        return LAYER_PROCESSOR;
    }

    @Override
    public void process(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception
    {
        BasicArrayList list = this.getList();
        int size = list.size();
        for (int index = 0; index < size; index++)
        {
            AllBinaryLayer layerInterface = (AllBinaryLayer) list.objectArray[index];

            DropLayerInterface dropLayerInterface = (DropLayerInterface) layerInterface;
            allBinaryLayerManager.append(
                    (AllBinaryLayer) dropLayerInterface.getDroppedLayer());
        }
        list.clear();
    }
}
