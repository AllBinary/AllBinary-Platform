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
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class TouchFeatureFactory
{
    private static final TouchFeatureFactory instance = 
        new TouchFeatureFactory();

    @JsMethod
    public static TouchFeatureFactory getInstance()
    {
        return TouchFeatureFactory.instance;
    }
    
    @JsConstructor
    private TouchFeatureFactory()
    {
        
    }

    @JsProperty
    public final TouchFeature TOUCH_ENABLED = new TouchFeature("Touch Enabled");
    
    @JsProperty
    public final TouchFeature SHOW_SCREEN_BUTTONS = 
        new TouchFeature("Show");
    @JsProperty
    public final TouchFeature AUTO_HIDE_SHOW_SCREEN_BUTTONS = 
        new TouchFeature("Auto Hide (One Level)");
    @JsProperty
    public final TouchFeature HIDE_SCREEN_BUTTONS = 
        new TouchFeature("Hide");
}
