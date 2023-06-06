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
 *Date: April 20, 2007, 9:14 PM
 *
 *Modified By         When       ?
 *
 */

package org.allbinary.logic.util.cache;

public class PoolInterfaceFactory
{
    
    private PoolInterfaceFactory()
    {
    }
    
    public static PoolInterface getInstance(
        CacheableInterfaceFactoryInterface cacheableInterfaceFactoryInterface,
        PoolType poolType, CachePolicy cachePolicy)
        throws Exception
    {   
        throw new Exception("No Such PoolType: " + poolType.toString());
    }
}
