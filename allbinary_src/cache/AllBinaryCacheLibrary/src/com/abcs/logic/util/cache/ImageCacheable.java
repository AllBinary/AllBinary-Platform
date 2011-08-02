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

//import com.abcs.logic.util.cache.CacheableInterface;

public class ImageCacheable //implements CacheableInterface
{
    private String name;
    //private Object object;
    
    public ImageCacheable(String name, Object object)
    {
        //this.object = object;
    }
    
    public Object getKey()
    {
        return name;
    }

    public String toString()
    {
        return null;
    }
}
