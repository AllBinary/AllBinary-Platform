/*
 * AllBinary Open License Version 1
 * Copyright (c) 2007 AllBinary
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

public class PoolType
{
    private CacheType cacheType;
    
    public PoolType(CacheType cacheType)
    {
        this.setCacheType(cacheType);
    }

    public CacheType getCacheType()
    {
        return cacheType;
    }

    public void setCacheType(CacheType cacheType)
    {
        this.cacheType = cacheType;
    }

   @Override
    public String toString()
    {
       final String POOL_TYPE = "PoolType: ";
        return POOL_TYPE + this.getCacheType().toString();
    }
}
