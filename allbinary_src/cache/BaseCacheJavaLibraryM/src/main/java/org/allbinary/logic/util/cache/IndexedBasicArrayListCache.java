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

import org.allbinary.string.CommonStrings;


/**
 *
 * @author user
 */
public class IndexedBasicArrayListCache
    extends BaseBasicArrayListCache
    implements CacheInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private int size = 0;
    protected int index = 0;

    public IndexedBasicArrayListCache()
    {
        
    }

    public IndexedBasicArrayListCache(int size)
    throws Exception
    {
        this.init(size);
    }

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
                this.add();
            }
            this.size = size;
        }
    }

    public Object get(int index)
    {
        return this.list.objectArray[index];
    }

    public void add() throws Exception
    {

    }

    public void add(Object object) throws Exception
    {
        this.list.add(object);
    }

    public void add(CacheableInterface cacheableInterface) throws Exception
    {
        this.list.add(cacheableInterface);
    }

    public void add(CacheableInterface[] cacheableInterfaces) throws Exception
    {
        //this.list.add(this);
        throw new Exception("No Imple");
    }

    public void clear()
    {
        //logUtil.put("List: " + this.log(), this, "clear");
        
        //logUtil.put("PathWrapper count: " + this.index, this, "release");
        //PreLogUtil.put("abstractPathElementList: " + BasicArrayListUtil.log(this.abstractPathElementList), this, "release");

        index = 0;
    }
}
