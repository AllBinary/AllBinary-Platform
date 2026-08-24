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
public class SensorFeatureFactory
{
    private static final SensorFeatureFactory instance = new SensorFeatureFactory();
    
    @JsMethod
    public static SensorFeatureFactory getInstance()
    {
        return SensorFeatureFactory.instance;
    }
    
    @JsConstructor
    private SensorFeatureFactory()
    {
    }

    @JsProperty
    public final SensorFeature NO_ORIENTATION = 
        new SensorFeature("No Orientation");
    @JsProperty
    public final SensorFeature ORIENTATION_SENSORS = 
        new SensorFeature("Orientation Sensors");
    @JsProperty
    public final SensorFeature SIMULATED_ORIENTATION_SENSORS = 
        new SensorFeature("Simulated Orientation Sensors");
    
    @JsProperty
    public final SensorFeature YAW = new SensorFeature("Yaw");
    @JsProperty
    public final SensorFeature YAW_MINUS_RIGHT_PLUS_LEFT = 
        new SensorFeature("Yaw -Right +Left");
    @JsProperty
    public final SensorFeature YAW_MINUS_LEFT_PLUS_RIGHT = 
        new SensorFeature("Yaw -Left +Right ");
    
    @JsProperty
    public final SensorFeature PITCH = new SensorFeature("Pitch");
    @JsProperty
    public final SensorFeature ROLL = new SensorFeature("Roll");
}
