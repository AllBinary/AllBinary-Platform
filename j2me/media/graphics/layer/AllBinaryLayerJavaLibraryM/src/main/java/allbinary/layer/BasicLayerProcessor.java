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

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.NotImplemented;

public class BasicLayerProcessor
{
    private final BasicArrayList list = new BasicArrayList();

    protected BasicLayerProcessor()
    {
    }

    public void add(AllBinaryLayer layerInterface)
    {
        if(!list.contains(layerInterface))
        {
            list.add(layerInterface);
        }
    }

    public void process(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }

    protected BasicArrayList getList()
    {
        return list;
    }
}
