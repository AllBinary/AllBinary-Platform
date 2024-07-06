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

public class CachePolicyFactory
{
    private static final CachePolicyFactory instance = new CachePolicyFactory();
    
    public static CachePolicyFactory getInstance()
    {
        return instance;
    }
    
    public final String NAME = "lru";
    
    public CachePolicy MAX_TIME_THOUSAND_MAX = 
        new CachePolicy(NAME, Long.MAX_VALUE, 1000);

    public CachePolicy THIRTY_MINUTES_TEN_THOUSAND_MAX = 
        new CachePolicy(NAME, 1000 * 60 * 30, 10000);

    public CachePolicy THIRTY_MINUTES_FIFTY_THOUSAND_MAX = 
        new CachePolicy(NAME, 1000 * 60 * 30, 100000);
    
    public CachePolicy ONE_MINUTE_FIVE_MAX = 
        new CachePolicy(NAME, 1000 * 60 * 1, 5);

    public CachePolicy ONE_MINUTE_TEN_MAX = 
        new CachePolicy(NAME, 1000 * 60 * 1, 10);

    public CachePolicy ONE_MINUTE_ONE_HUNDRED_MAX = 
        new CachePolicy(NAME, 1000 * 60 * 1, 100);
    
}
