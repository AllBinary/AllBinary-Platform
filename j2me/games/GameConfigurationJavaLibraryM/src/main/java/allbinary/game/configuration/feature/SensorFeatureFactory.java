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
package allbinary.game.configuration.feature;

public class SensorFeatureFactory
{
    private static final SensorFeatureFactory instance = new SensorFeatureFactory();
    
    public static SensorFeatureFactory getInstance()
    {
        return instance;
    }
    
    private SensorFeatureFactory()
    {
    }

    public final SensorFeature NO_ORIENTATION = 
        new SensorFeature("No Orientation");
    public final SensorFeature ORIENTATION_SENSORS = 
        new SensorFeature("Orientation Sensors");
    public final SensorFeature SIMULATED_ORIENTATION_SENSORS = 
        new SensorFeature("Simulated Orientation Sensors");
    
    public final SensorFeature YAW = new SensorFeature("Yaw");
    public final SensorFeature YAW_MINUS_RIGHT_PLUS_LEFT = 
        new SensorFeature("Yaw -Right +Left");
    public final SensorFeature YAW_MINUS_LEFT_PLUS_RIGHT = 
        new SensorFeature("Yaw -Left +Right ");
    
    public final SensorFeature PITCH = new SensorFeature("Pitch");
    public final SensorFeature ROLL = new SensorFeature("Roll");
}
