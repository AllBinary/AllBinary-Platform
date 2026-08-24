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
package org.allbinary.game.resource;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class ResourceLoadingLevel
{
    private final String name;
    private final int level;
    
    @JsConstructor
    ResourceLoadingLevel(String name, int level)
    {
        this.name = name;
        this.level = level;
    }

    @JsMethod
    public int getLevel()
    {
        return this.level;
    }

    @JsMethod
    public String getName()
    {
        return this.name;
    }
}
