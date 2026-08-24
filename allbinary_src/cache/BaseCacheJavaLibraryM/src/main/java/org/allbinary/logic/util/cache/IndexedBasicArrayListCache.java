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

import jsinterop.annotations.JsType;

import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


/**
 *
 * @author user
 */

@JsType
public class IndexedBasicArrayListCache
    extends BaseBasicArrayListCache
    implements CacheInterface
{
    @JsMethod
    public static IndexedBasicArrayListCache createCache(int size) throws Exception
    {
        final IndexedBasicArrayListCache indexedBasicArrayListCache = new IndexedBasicArrayListCache();
        indexedBasicArrayListCache.init(size);
        return indexedBasicArrayListCache;
    }

    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private int size = 0;
    @JsProperty
    protected int index = 0;

    @JsConstructor
    public IndexedBasicArrayListCache()
    {

    }

    @JsMethod
    public void init(int size)
    throws Exception
    {
        //TWB - Size Optimization
        //size = 0;

        if(this.size < size)
        {
            this.list.ensureCapacity(size);
            for(int index = size - 1; index >= this.size; index--)
            {
                this.addDefault();
            }
            this.size = size;
        }
    }

    @JsMethod
    public Object getAt(int index)
    {
        return this.list.objectArray[index];
    }

    @JsMethod
    public void addDefault() throws Exception
    {

    }

    @JsMethod
    public void addObject(Object object) throws Exception
    {
        this.list.add(object);
    }

    @Override
    @JsMethod
    public void add(CacheableInterface cacheableInterface) throws Exception
    {
        this.list.add(cacheableInterface);
    }

    @Override
    @JsMethod
    public void addArray(CacheableInterface[] cacheableInterfaces) throws Exception
    {
        //this.list.add(this);
        throw new Exception("No Imple");
    }

    @Override
    @JsMethod
    public void clear()
    {
        //this.logUtil.putF("List: " + this.log(), this, "clear");
        
        //this.logUtil.putF("PathWrapper count: " + this.index, this, "clear");
        //PreLogUtil.put("abstractPathElementList: " + BasicArrayListUtil.log(this.abstractPathElementList), this, "clear");

        this.index = 0;
    }
}
