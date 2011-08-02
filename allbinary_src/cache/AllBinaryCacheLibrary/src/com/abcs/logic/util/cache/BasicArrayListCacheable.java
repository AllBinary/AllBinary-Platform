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

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.StringUtil;

public class BasicArrayListCacheable extends BasicArrayList 
    implements CacheableInterface
{
    public Object getKey()
    {
        return StringUtil.getInstance().EMPTY_STRING;
    }
}
