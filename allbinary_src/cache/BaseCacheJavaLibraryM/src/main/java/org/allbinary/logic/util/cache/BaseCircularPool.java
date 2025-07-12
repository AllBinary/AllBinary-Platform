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
package org.allbinary.logic.util.cache;

import org.allbinary.logic.NullUtil;
import org.allbinary.util.CircularIndexUtil;

public class BaseCircularPool
{
    protected CircularIndexUtil circularIndexUtil = CircularIndexUtil.NULL_CIRCULAR_INDEX_UTIL;
    protected Object[] OBJECT_ARRAY = NullUtil.getInstance().NULL_OBJECT_ARRAY;

    public synchronized Object getNextInstance() throws Exception
    {
        Object object = OBJECT_ARRAY[this.circularIndexUtil.getIndex()];

        this.circularIndexUtil.next();

        return object;
    }
    
    public void init(
            AllBinaryObjectFactoryInterface allBinaryObjectFactoryInterface)
    {
        this.circularIndexUtil.setIndex(0);
        int size = this.circularIndexUtil.getSize();
        for (int index = 0; index < size ; index++)
        {
            OBJECT_ARRAY[index] = allBinaryObjectFactoryInterface.getInstance();
        }
    }
    
    public Object getInstance(int index) throws Exception
    {
        return OBJECT_ARRAY[index];
    }
}
