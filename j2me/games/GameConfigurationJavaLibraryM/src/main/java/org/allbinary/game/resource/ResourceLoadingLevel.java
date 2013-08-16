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

public class ResourceLoadingLevel
{
    private final String name;
    private final int level;
    
    protected ResourceLoadingLevel(String name, int level)
    {
        this.name = name;
        this.level = level;
    }

    public int getLevel()
    {
        return level;
    }

    public String getName()
    {
        return name;
    }
}
