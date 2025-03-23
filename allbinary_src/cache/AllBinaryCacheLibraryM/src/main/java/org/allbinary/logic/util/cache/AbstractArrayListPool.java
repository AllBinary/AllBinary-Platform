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

import org.allbinary.util.BasicArrayList;

import org.allbinary.string.CommonStrings;

public class AbstractArrayListPool implements PoolInterface {

    protected BasicArrayList buffers = new BasicArrayList();
    //protected int pos = -1;
    protected CacheableInterfaceFactoryInterface cacheableInterfaceFactoryInterface;

    public AbstractArrayListPool(CacheableInterfaceFactoryInterface cacheableInterfaceFactoryInterface) {
        this.cacheableInterfaceFactoryInterface = cacheableInterfaceFactoryInterface;
    }

    public void clear()
            throws Exception {
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }

    public CacheableInterface remove(Object key)
            throws Exception {
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }

    public void add(CacheableInterface cacheableInterface) throws Exception {
        //buffers.set(++pos, cacheableInterface);
        buffers.add(cacheableInterface);
    }

    public void add(CacheableInterface[] cacheableInterfaces) throws Exception {
        for (int index = 0; index < cacheableInterfaces.length; index++) {
            //buffers.set(++pos, cacheableInterfaces[index]);
            buffers.add(cacheableInterfaces[index]);
        }
    }

    /*
    public Set keySet()
    {
    return null;
    }
     */
}
