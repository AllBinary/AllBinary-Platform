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
package allbinary.game.configuration.event;

import allbinary.animation.resource.ResourceLoadingLevel;
import allbinary.animation.resource.ResourceLoadingLevelFactory;
import allbinary.logic.basic.util.event.AllBinaryEventObject;

public class GameInitializedEvent extends AllBinaryEventObject
{
    private ResourceLoadingLevel resourceLoadingLevel;
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
