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
package org.allbinary.game.configuration;

import java.util.Hashtable;

import org.allbinary.util.BasicArrayList;

public class GameConfigurationSingleton
{
    private static GameConfigurationSingleton SINGLETON = new GameConfigurationSingleton();
    
    private BasicArrayList list;
    private Hashtable hashtable;
        
    public static GameConfigurationSingleton getInstance()
    {
        return SINGLETON;
    }
        
    private GameConfigurationSingleton()
    {
        list = new BasicArrayList();
        hashtable = new Hashtable();
    }
    
    public GameConfiguration getInstance(String name)
    {
        return (GameConfiguration) hashtable.get(name);
    }

    public BasicArrayList getOptionsBasicArrayList()
    {
        return list;
    }

    public void add(GameConfiguration gameConfiguration)
    {
        list.add(gameConfiguration);
    }
    
    public Hashtable getHashtable()
    {
        return this.hashtable;
    }
    
}
