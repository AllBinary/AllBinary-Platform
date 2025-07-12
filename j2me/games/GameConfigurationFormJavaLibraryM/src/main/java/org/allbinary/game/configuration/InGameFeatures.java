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

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureChoiceGroups;
import org.allbinary.game.configuration.feature.InGameFeatureChoiceGroups;
import org.allbinary.game.configuration.feature.SensorFeatureFactory;
import org.allbinary.game.configuration.feature.TouchFeatureFactory;
import org.allbinary.init.Init;
import org.allbinary.input.gyro.OrientationData;
import org.allbinary.util.BasicArrayList;

public class InGameFeatures extends Init
{
    public void init()
    {
        final String LABEL = "Screen Buttons";

        OrientationData orientationData = OrientationData.getInstance();
        
        BasicArrayList exclusiveOrientationSensorVector = (BasicArrayList) 
            GameFeatureChoiceGroups.getExclusiveInstance().get().get(
                    orientationData.ORIENTATION_SENSOR_INPUT);

        if (exclusiveOrientationSensorVector != null)
        {
            BasicArrayList inGameExclusiveOrientationSensorVector = (BasicArrayList)
                InGameFeatureChoiceGroups.getExclusiveInstance().get().get(
                    orientationData.ORIENTATION_SENSOR_INPUT);
            
            if(inGameExclusiveOrientationSensorVector == null ||
                    inGameExclusiveOrientationSensorVector.size() == 0)
            {
                inGameExclusiveOrientationSensorVector = new BasicArrayList();
                
                inGameExclusiveOrientationSensorVector.add(
                        SensorFeatureFactory.getInstance().ORIENTATION_SENSORS);
                inGameExclusiveOrientationSensorVector.add(
                        SensorFeatureFactory.getInstance().NO_ORIENTATION);

                InGameFeatureChoiceGroups.getExclusiveInstance().add(
                        orientationData.ORIENTATION_SENSOR_INPUT,
                        inGameExclusiveOrientationSensorVector);
            }
        }

        Features features = Features.getInstance();

        if (features.isFeature(TouchFeatureFactory.getInstance().SHOW_SCREEN_BUTTONS)
            || features.isFeature(TouchFeatureFactory.getInstance().AUTO_HIDE_SHOW_SCREEN_BUTTONS)
            || features.isFeature(TouchFeatureFactory.getInstance().HIDE_SCREEN_BUTTONS))
        {
            BasicArrayList exclusiveScreenButtonsVector = new BasicArrayList();

            TouchFeatureFactory touchFeatureFactory =
                TouchFeatureFactory.getInstance();

            exclusiveScreenButtonsVector.add(
                touchFeatureFactory.AUTO_HIDE_SHOW_SCREEN_BUTTONS);
            exclusiveScreenButtonsVector.add(
                touchFeatureFactory.SHOW_SCREEN_BUTTONS);
            exclusiveScreenButtonsVector.add(
                touchFeatureFactory.HIDE_SCREEN_BUTTONS);

            InGameFeatureChoiceGroups.getExclusiveInstance().add(LABEL,
                exclusiveScreenButtonsVector);
        }
    }

    public boolean isAny()
    {
            Features features = Features.getInstance();

            TouchFeatureFactory touchFeatureFactory =
                TouchFeatureFactory.getInstance();

        if (features.isFeature(touchFeatureFactory.SHOW_SCREEN_BUTTONS)
            || features.isFeature(touchFeatureFactory.AUTO_HIDE_SHOW_SCREEN_BUTTONS)
            || features.isFeature(touchFeatureFactory.HIDE_SCREEN_BUTTONS))
        {
            return true;
        }

        BasicArrayList exclusiveOrientationSensorVector = (BasicArrayList)
            GameFeatureChoiceGroups.getExclusiveInstance().get().get(
            OrientationData.getInstance().ORIENTATION_SENSOR_INPUT);

        if (exclusiveOrientationSensorVector != null)
        {
            return true;
        }

        return false;
    }
}
