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

public class ResourceLoadingLevelFactory
{
    private static final ResourceLoadingLevelFactory instance = new ResourceLoadingLevelFactory();

    public static ResourceLoadingLevelFactory getInstance()
    {
        return instance;
    }

    public final ResourceLoadingLevel LOAD_EARLY = new ResourceLoadingLevel("Load Early", Integer.MAX_VALUE);
    public final ResourceLoadingLevel LOAD_TOUCH = new ResourceLoadingLevel("Load Touch", Integer.MAX_VALUE - 1);
    public final ResourceLoadingLevel LOAD_GAME = new ResourceLoadingLevel("Load Game", Integer.MAX_VALUE - 2);
    public final ResourceLoadingLevel LEVEL = new ResourceLoadingLevel("Level", Integer.MAX_VALUE - 99);
    public final ResourceLoadingLevel MAX_LEVEL = new ResourceLoadingLevel("Max Level", Integer.MAX_VALUE - 100);

    //private final int LOAD_ = Integer.MAX_VALUE;
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
    
    public String getLevelString(int level)
    {
        for(int index = 0; index < RESOURCE_LOADING_LEVEL_ARRAY.length; index++)
        {
            if(level == RESOURCE_LOADING_LEVEL_ARRAY[index].getLevel())
            {
                return RESOURCE_LOADING_LEVEL_ARRAY[index].getName();
            }
        }
        return Integer.toString(level);
    }
}
