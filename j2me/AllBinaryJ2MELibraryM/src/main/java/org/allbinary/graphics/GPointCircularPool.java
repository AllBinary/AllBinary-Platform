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
package org.allbinary.graphics;

import org.allbinary.logic.util.cache.BaseCircularPool;
import org.allbinary.util.CircularIndexUtil;

public class GPointCircularPool extends BaseCircularPool
{
    public GPointCircularPool(int size)
    {
        this.circularIndexUtil = CircularIndexUtil.getInstance(size);
        this.OBJECT_ARRAY = new Object[size];
        
        for(int index = size - 1; index >= 0; index--)
        {
            this.OBJECT_ARRAY[index] = new CustomGPoint(0, 0);
        }
    }
}
