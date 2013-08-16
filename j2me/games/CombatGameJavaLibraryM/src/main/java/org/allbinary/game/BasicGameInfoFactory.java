package org.allbinary.game;

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
public class BasicGameInfoFactory
{
    private final static BasicGameInfoFactory SINGLETON = new BasicGameInfoFactory();

    public static BasicGameInfoFactory getInstance()
    {
        return SINGLETON;
    }

    public int currentReleaseIndex;
    public int currentDestroyIndex;
    public int total;
    
    private BasicGameInfoFactory()
    {
    }

    public void init(int total)
    {
        currentReleaseIndex = 0;
        currentDestroyIndex = 0;
        this.total = total;
    }

    public boolean isPeaked()
    {
        if(this.currentDestroyIndex >= this.total)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public int howManyLeft()
    {
        return this.total - this.currentDestroyIndex;
    }
}
