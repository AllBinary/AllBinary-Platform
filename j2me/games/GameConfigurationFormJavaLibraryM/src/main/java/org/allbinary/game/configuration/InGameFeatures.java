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
    @Override
    public void init()
    {
        final String LABEL = "Screen Buttons";

        final OrientationData orientationData = OrientationData.getInstance();
        
        final BasicArrayList exclusiveOrientationSensorVector = (BasicArrayList) 
            GameFeatureChoiceGroups.getExclusiveInstance().get().get(
                    (Object) orientationData.ORIENTATION_SENSOR_INPUT);

        final InGameFeatureChoiceGroups inGameFeatureChoiceGroups = 
            InGameFeatureChoiceGroups.getExclusiveInstance();
        
        if (exclusiveOrientationSensorVector != null)
        {
            final Object inGameExclusiveOrientationSensorVectorCanBeNull = 
                inGameFeatureChoiceGroups.get().get((Object) orientationData.ORIENTATION_SENSOR_INPUT);
            
            if(inGameExclusiveOrientationSensorVectorCanBeNull == null)
            {
                this.addToInGameMenu();
            } else {
                final BasicArrayList inGameExclusiveOrientationSensorVector = 
                    (BasicArrayList) inGameExclusiveOrientationSensorVectorCanBeNull;
                if(inGameExclusiveOrientationSensorVector.size() == 0) {
                    this.addToInGameMenu();
                }
            }
            
        }

        final Features features = Features.getInstance();

        if (features.isFeature(TouchFeatureFactory.getInstance().SHOW_SCREEN_BUTTONS)
            || features.isFeature(TouchFeatureFactory.getInstance().AUTO_HIDE_SHOW_SCREEN_BUTTONS)
            || features.isFeature(TouchFeatureFactory.getInstance().HIDE_SCREEN_BUTTONS))
        {
            final BasicArrayList exclusiveScreenButtonsVector = new BasicArrayList();

            final TouchFeatureFactory touchFeatureFactory =
                TouchFeatureFactory.getInstance();

            exclusiveScreenButtonsVector.add(
                touchFeatureFactory.AUTO_HIDE_SHOW_SCREEN_BUTTONS);
            exclusiveScreenButtonsVector.add(
                touchFeatureFactory.SHOW_SCREEN_BUTTONS);
            exclusiveScreenButtonsVector.add(
                touchFeatureFactory.HIDE_SCREEN_BUTTONS);

            inGameFeatureChoiceGroups.add(LABEL,
                exclusiveScreenButtonsVector);
        }
    }

    private void addToInGameMenu() {
        
        final OrientationData orientationData = OrientationData.getInstance();
        
        final BasicArrayList inGameExclusiveOrientationSensorVector = new BasicArrayList();

        inGameExclusiveOrientationSensorVector.add(
            SensorFeatureFactory.getInstance().ORIENTATION_SENSORS);
        inGameExclusiveOrientationSensorVector.add(
            SensorFeatureFactory.getInstance().NO_ORIENTATION);

        InGameFeatureChoiceGroups.getExclusiveInstance().add(orientationData.ORIENTATION_SENSOR_INPUT,
            inGameExclusiveOrientationSensorVector);
    }

    public boolean isAny()
    {
        final Features features = Features.getInstance();

        final TouchFeatureFactory touchFeatureFactory =
                TouchFeatureFactory.getInstance();

        if (features.isFeature(touchFeatureFactory.SHOW_SCREEN_BUTTONS)
            || features.isFeature(touchFeatureFactory.AUTO_HIDE_SHOW_SCREEN_BUTTONS)
            || features.isFeature(touchFeatureFactory.HIDE_SCREEN_BUTTONS))
        {
            return true;
        }

        final BasicArrayList exclusiveOrientationSensorVector = (BasicArrayList)
            GameFeatureChoiceGroups.getExclusiveInstance().get().get(
            (Object) OrientationData.getInstance().ORIENTATION_SENSOR_INPUT);

        if (exclusiveOrientationSensorVector != null)
        {
            return true;
        }

        return false;
    }
}
