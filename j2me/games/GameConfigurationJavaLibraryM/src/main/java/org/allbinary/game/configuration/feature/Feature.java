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
package org.allbinary.game.configuration.feature;

import java.util.Hashtable;

import org.allbinary.game.configuration.event.ChangedGameFeatureListener;

public class Feature
{
    private static Hashtable hashtable = new Hashtable();

    private final String name;

    public Feature(String name)
    {
        this.name = name;
        add(name, this);
        
        ChangedGameFeatureListener.getInstance().add(this);
    }
    
    public static Feature getInstance(String name)
    {
        return (Feature) hashtable.get(name);
    }

    private static void add(String name, Feature gameFeature)
    {
        hashtable.put(name, gameFeature);
    }
    
    public String toString()
    {
        return getName();
    }

    public String getName()
    {
        return name;
    }
}
