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

import org.allbinary.logic.util.cache.BaseCircularPool;
import org.allbinary.util.CircularIndexUtil;

public class AllBinaryLayerCircularPool
extends BaseCircularPool
{
    protected AllBinaryLayerCircularPool()
    {
        
    }

    public void init(AllBinaryLayerFactoryInterface allbinaryLayerFactoryInterface,
            int total) throws Exception
    {
        this.circularIndexUtil = CircularIndexUtil.getInstance(total);

        OBJECT_ARRAY = new AllBinaryLayer[total];

        for (int localIndex = 0; localIndex < total; localIndex++)
        {
            OBJECT_ARRAY[localIndex] = allbinaryLayerFactoryInterface.getInstance();
        }
    }
}