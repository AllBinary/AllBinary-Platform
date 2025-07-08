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

import org.allbinary.util.BasicArrayList;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class BasicLayerProcessor
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final BasicArrayList list = new BasicArrayList();

    protected BasicLayerProcessor()
    {
    }

    public void add(AllBinaryLayer layerInterface)
    {
        if(!list.contains(layerInterface))
        {
            //if(layerInterface.getName().indexOf("debrish") >= 0) {
                //logUtil.put("Adding: " + layerInterface, this, "add", new Exception());
            //}
            list.add(layerInterface);
        }
    }

    public void process(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception
    {
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }

    public BasicArrayList getList()
    {
        return list;
    }
}
