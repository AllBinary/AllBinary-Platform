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
import org.allbinary.util.BasicArrayListD;

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
        this.list = new BasicArrayListD();
        this.hashtable = new Hashtable();
    }
    
    public GameConfiguration getInstance(String name)
    {
        return (GameConfiguration) this.hashtable.get(name);
    }

    public BasicArrayList getOptionsBasicArrayList()
    {
        return list;
    }

    public void add(GameConfiguration gameConfiguration)
    {
        this.list.add(gameConfiguration);
    }
    
    public Hashtable getHashtable()
    {
        return this.hashtable;
    }
    
}
