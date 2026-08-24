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

import jsinterop.annotations.JsType;

import org.allbinary.logic.util.cache.BaseCircularPool;
import org.allbinary.util.CircularIndexUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class AllBinaryLayerCircularPool
extends BaseCircularPool
{
    @JsConstructor
    protected AllBinaryLayerCircularPool()
    {
        
    }

    @JsMethod
    public void initAllBinaryLayerCircularPool(final AllBinaryLayerFactoryInterface allbinaryLayerFactoryInterface, final int total) throws Exception
    {
        this.circularIndexUtil = CircularIndexUtil.createInstance(total);

        this.OBJECT_ARRAY = new AllBinaryLayer[total];

        for (int localIndex = 0; localIndex < total; localIndex++)
        {
            this.OBJECT_ARRAY[localIndex] = allbinaryLayerFactoryInterface.getInstance();
        }
    }
}