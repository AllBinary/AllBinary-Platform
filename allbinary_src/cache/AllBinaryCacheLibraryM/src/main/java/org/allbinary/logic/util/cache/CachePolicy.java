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

public class CachePolicy
{
    private final String name;
    private long maxTime;
    private long maxSize;

    public CachePolicy(String name, long maxTime, long maxSize)
    {
        this.name = name; 
        this.setMaxTime(maxTime);
        this.setMaxSize(maxSize);
    }
    
    public long getMaxTime()
    {
        return maxTime;
    }

    public void setMaxTime(long maxTime)
    {
        this.maxTime = maxTime;
    }

    public long getMaxSize()
    {
        return maxSize;
    }

    public void setMaxSize(long maxSize)
    {
        this.maxSize = maxSize;
    }    

    public String getName()
    {
        return name;
    }
    
}
