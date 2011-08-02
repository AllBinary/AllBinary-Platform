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
package com.abcs.logic.util.cache;

import org.allbinary.util.CircularIndexUtil;

public class BaseCircularPool
{
    protected CircularIndexUtil circularIndexUtil;
    protected Object[] OBJECT_ARRAY;

    public synchronized Object getNextInstance() throws Exception
    {
        Object object = OBJECT_ARRAY[this.circularIndexUtil.getIndex()];

        this.circularIndexUtil.next();

        return object;
    }
    
    public Object getInstance(int index) throws Exception
    {
        return OBJECT_ARRAY[index];
    }
}
