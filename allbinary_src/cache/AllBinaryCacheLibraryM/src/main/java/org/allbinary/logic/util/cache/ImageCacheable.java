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

//import com.abcs.logic.util.cache.CacheableInterface;

import org.allbinary.logic.string.StringUtil;


public class ImageCacheable //implements CacheableInterface
{
    private String name = StringUtil.getInstance().EMPTY_STRING;
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
        return name;
    }
}
