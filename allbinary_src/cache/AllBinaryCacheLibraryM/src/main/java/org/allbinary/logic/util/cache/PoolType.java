/*
 *Copyright (c) 2007 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *@author Travis Berthelot
 *Date: April 27, 2007, 2:32 AM
 *
 *Modified By         When       ?
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
