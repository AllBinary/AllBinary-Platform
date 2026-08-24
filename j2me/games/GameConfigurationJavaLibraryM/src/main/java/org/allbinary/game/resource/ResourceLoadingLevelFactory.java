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
import jsinterop.annotations.JsProperty;


@JsType
public class ResourceLoadingLevelFactory
{
    private static final ResourceLoadingLevelFactory instance = new ResourceLoadingLevelFactory();

    @JsMethod
    public static ResourceLoadingLevelFactory getInstance()
    {
        return ResourceLoadingLevelFactory.instance;
    }

    @JsProperty
    public final ResourceLoadingLevel LOAD_EARLY = new ResourceLoadingLevel("Load Early", Integer.MAX_VALUE);
    @JsProperty
    public final ResourceLoadingLevel LOAD_TOUCH = new ResourceLoadingLevel("Load Touch", Integer.MAX_VALUE - 1);
    @JsProperty
    public final ResourceLoadingLevel LOAD_GAME = new ResourceLoadingLevel("Load Game", Integer.MAX_VALUE - 2);
    @JsProperty
    public final ResourceLoadingLevel LEVEL = new ResourceLoadingLevel("Level", Integer.MAX_VALUE - 99);
    @JsProperty
    public final ResourceLoadingLevel MAX_LEVEL = new ResourceLoadingLevel("Max Level", Integer.MAX_VALUE - 100);

    //private final int LOAD_ = Integer.MAX_VALUE;
    @JsProperty
    public final ResourceLoadingLevel LOAD_ALL = new ResourceLoadingLevel("Load All", -1);
    
    private final ResourceLoadingLevel[] RESOURCE_LOADING_LEVEL_ARRAY = 
    {
        this.LOAD_ALL,
        this.MAX_LEVEL,
        this.LEVEL,
        this.LOAD_GAME,
        this.LOAD_TOUCH,
        this.LOAD_EARLY
    };
    
    @JsMethod
    public String getLevelString(int level)
    {
        for(int index = 0; index < this.RESOURCE_LOADING_LEVEL_ARRAY.length; index++)
        {
            if(level == this.RESOURCE_LOADING_LEVEL_ARRAY[index].getLevel())
            {
                return this.RESOURCE_LOADING_LEVEL_ARRAY[index].getName();
            }
        }
        return Integer.toString(level);
    }
}
