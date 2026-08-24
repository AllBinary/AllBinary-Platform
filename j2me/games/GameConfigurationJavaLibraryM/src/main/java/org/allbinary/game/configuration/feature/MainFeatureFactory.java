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
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;


@JsType
public class MainFeatureFactory
{
    private static final MainFeatureFactory instance = new MainFeatureFactory();
    
    @JsMethod
    public static MainFeatureFactory getInstance()
    {
        return MainFeatureFactory.instance;
    }
    
    @JsProperty
    public final Feature FULL_SCREEN = new Feature("Full Screen");
    @JsProperty
    public final Feature TITLE_BAR = new Feature("Title Bar");

    @JsProperty
    public final Feature STATIC = new Feature("Static Dependent Feature");

    @JsProperty
    public final Feature LOAD_ONDEMAND = new Feature("Load OnDemand");

    @JsProperty
    public final Feature LOAD_ALL = new Feature("Load All");
    
}
