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

import jsinterop.annotations.JsType;

import java.util.Hashtable;

import org.allbinary.game.configuration.event.ChangedGameFeatureListener;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import org.allbinary.logic.StdUtil;


@JsType
public class Feature
{
    private static Hashtable hashtable = StdUtil.getInstance().createHashtable();

    private final String name;

    @JsConstructor
    public Feature(String name)
    {
        this.name = name;
        Feature.add(name, this);
        
        ChangedGameFeatureListener.getInstance().add(this);
    }
    
    @JsMethod
    public static Feature getInstance(String name)
    {
        return (Feature) Feature.hashtable.get(name);
    }

    @JsMethod
    private static void add(String name, Feature gameFeature)
    {
        Feature.hashtable.put(name, gameFeature);
    }
    
    @JsMethod
    public String toString()
    {
        return this.getName();
    }

    @JsMethod
    public String getName()
    {
        return this.name;
    }
}
