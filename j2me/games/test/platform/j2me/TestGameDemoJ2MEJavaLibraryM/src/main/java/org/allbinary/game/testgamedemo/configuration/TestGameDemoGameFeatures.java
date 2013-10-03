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
package org.allbinary.game.testgamedemo.configuration;

import org.allbinary.util.BasicArrayList;

import allbinary.game.configuration.GameConfigurationCentral;
import allbinary.game.configuration.GameConfigurationSingleton;
import allbinary.game.configuration.feature.GameFeatureChoiceGroups;
import allbinary.game.configuration.feature.GameFeatureFactory;
import allbinary.game.configuration.feature.SensorFeatureFactory;
import allbinary.init.InitInterface;

public class TestGameDemoGameFeatures implements InitInterface
{    
    private final String GRAPHICS_OPTIONS = "Graphics Options";
    
    public TestGameDemoGameFeatures()
    {
        
    }
    
    public void init()
    {
        //BasicArrayList exclusiveOrientationSensorList = new BasicArrayList();

        SensorFeatureFactory sensorFeatureFactory =
                SensorFeatureFactory.getInstance();

        GameFeatureFactory gameFeatureFactory =
                GameFeatureFactory.getInstance();

        //exclusiveOrientationSensorList.add(sensorFeatureFactory.ORIENTATION_SENSORS);
        //exclusiveOrientationSensorList.add(sensorFeatureFactory.NO_ORIENTATION);

        /*
        BasicArrayList exclusiveOrientationTypeList = new BasicArrayList();
        exclusiveOrientationTypeList.add(GameFeature.YAW_AND_PITCH);
        exclusiveOrientationTypeList.add(GameFeature.PITCH_AND_ROLL);
        */
        
        GameConfigurationSingleton gameConfigurationSingleton = 
            GameConfigurationSingleton.getInstance();
        
        GameConfigurationCentral gameConfigurationCentral = 
            GameConfigurationCentral.getInstance();

        gameConfigurationSingleton.add(gameConfigurationCentral.VIBRATION);
        gameConfigurationSingleton.add(gameConfigurationCentral.ORIENTATION);

        // gameConfigurationSingleton.add(gameConfigurationCentral.CONTROL_LEVEL);
        gameConfigurationSingleton.add(gameConfigurationCentral.SPEED);

        BasicArrayList multipleList = new BasicArrayList();

        multipleList.add(gameFeatureFactory.SOUND);
        multipleList.add(gameFeatureFactory.SCREEN_SHAKE);

        //multipleBasicArrayList.add(GameFeature.TRANSISTION_ANIMATIONS);

        // graphicsOptionsBasicArrayList.add(GameFeature.);

        GameFeatureChoiceGroups.getMultipleInstance().add(
                GRAPHICS_OPTIONS, multipleList);

        //GameFeatureChoiceGroups.getExclusiveInstance().add(
          //      AllBinarySensorManager.getInstance().ORIENTATION_SENSOR_INPUT, 
            //    exclusiveOrientationSensorList);

        //GameFeatureChoiceGroups.getExclusiveInstance().add(ORIENTATION_TYPE,
          //      exclusiveOrientationTypeBasicArrayList); 
    }
}
