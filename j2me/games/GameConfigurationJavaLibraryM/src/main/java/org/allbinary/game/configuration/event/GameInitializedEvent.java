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
package org.allbinary.game.configuration.event;

import org.allbinary.game.resource.ResourceLoadingLevel;
import org.allbinary.game.resource.ResourceLoadingLevelFactory;
import org.allbinary.logic.util.event.AllBinaryEventObject;

public class GameInitializedEvent extends AllBinaryEventObject
{
    private ResourceLoadingLevel resourceLoadingLevel = ResourceLoadingLevelFactory.getInstance().LEVEL;
    private int level;
    
    public GameInitializedEvent(Object object)
    {
        super(object);
    }

    public void setLevel(int level)
    {
        this.level = level;
        this.resourceLoadingLevel = ResourceLoadingLevelFactory.getInstance().LEVEL;
    }

    public int getLevel()
    {
        return level;
    }

    public void setResourceLoadingLevel(ResourceLoadingLevel resourceLoadingLevel)
    {
        this.resourceLoadingLevel = resourceLoadingLevel;
        level = this.resourceLoadingLevel.getLevel();
    }

    public ResourceLoadingLevel getResourceLoadingLevel()
    {
        return resourceLoadingLevel;
    }
}
