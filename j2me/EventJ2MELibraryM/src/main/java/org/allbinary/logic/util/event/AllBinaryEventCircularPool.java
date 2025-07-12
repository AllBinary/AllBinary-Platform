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
package org.allbinary.logic.util.event;

import org.allbinary.logic.util.cache.BaseCircularPool;
import org.allbinary.util.CircularIndexUtil;

public class AllBinaryEventCircularPool
extends BaseCircularPool
{
    public AllBinaryEventCircularPool(int max)
    {
        this.OBJECT_ARRAY = new Object[max];
        this.circularIndexUtil = CircularIndexUtil.getInstance(max);
    }
    
    public void init(
            AllBinaryEventObjectFactoryInterface allBinaryEventObjectFactoryInterface)
    {
        this.circularIndexUtil.setIndex(0);
        int size = this.circularIndexUtil.getSize();
        for (int index = 0; index < size ; index++)
        {
            OBJECT_ARRAY[index] = allBinaryEventObjectFactoryInterface.getInstance();
        }
    }
}
