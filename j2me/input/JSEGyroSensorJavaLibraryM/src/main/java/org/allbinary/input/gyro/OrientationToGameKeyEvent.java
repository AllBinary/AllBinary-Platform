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
package org.allbinary.input.gyro;

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.SensorFeatureFactory;
import org.allbinary.logic.math.Orientation;

public class OrientationToGameKeyEvent
{
    private AllBinaryOrientationSensor allBinaryGyroSensor;
    
    private final int MIN = 15; 
    
    public OrientationToGameKeyEvent() throws Exception
    {
        
            if(Features.getInstance().isFeature(
                SensorFeatureFactory.getInstance().NO_ORIENTATION))
            {
                allBinaryGyroSensor = new NoGyroSensor();
            }
                else
                {
                    throw new Exception("Not Such Feature");
                }
        
        //allBinaryGyroSensor.init();
    }

    public void setAllBinaryGyroSensor(AllBinaryOrientationSensor allBinaryGyroSensor)
    {
        this.allBinaryGyroSensor = allBinaryGyroSensor;
    }

    public AllBinaryOrientationSensor getAllBinaryGyroSensor()
    {
        return allBinaryGyroSensor;
    }
        
    public void process() throws Exception
    {
    }
    
    private void updateGameKeyEvents(Orientation orientation, 
            int yaw, int pitch, int roll)
    throws Exception
    {
    }
}
