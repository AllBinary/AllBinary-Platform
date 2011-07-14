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

import org.allbinary.input.AllBinarySensor;
import org.allbinary.input.NoAllBinarySensor;

public class SensorGameUpdateProcessor
{
    private AllBinarySensor inputSensor = NoAllBinarySensor.getInstance();

    protected void setInputSensor(AllBinarySensor inputSensor)
    {
        this.inputSensor = inputSensor;
    }

    public AllBinarySensor getInputSensor()
    {
        return inputSensor;
    }
    
    //public void process(AllBinaryGameLayerManager layerManager) throws Exception
    public void process(Object object) throws Exception
    {
    }

    //public void sendNotifications(AllBinaryGameLayerManager layerManager) throws Exception
    public void sendNotifications(Object object) throws Exception
    {
    }
    
    public boolean isAnySensor()
    {
        if(this.inputSensor == NoAllBinarySensor.getInstance())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    protected void setNoSensors()
    {
        this.setInputSensor(NoAllBinarySensor.getInstance());
    }
}
