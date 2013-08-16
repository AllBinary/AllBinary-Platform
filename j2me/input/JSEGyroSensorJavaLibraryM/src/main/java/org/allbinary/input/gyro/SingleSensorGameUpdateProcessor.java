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


import allbinary.canvas.Processor;

public class SingleSensorGameUpdateProcessor 
    extends SensorGameUpdateProcessor
{
    private final Processor accelerometerSensorUpdateProcessor = 
        new AccelerometerSensorUpdateProcessor();
    private final Processor gyroSensorUpdateProcessor = 
        new GyroSensorUpdateProcessor();

    public void process(Object object)
        throws Exception
    {
/*
        GameType gameType = layerManager.getGameInfo().getGameType();

        AllBinarySensorManager sensorManager = 
            AllBinarySensorManager.getInstance();

        SensorFeatureFactory sensorFeatureFactory = 
            SensorFeatureFactory.getInstance();

        sensorManager.shutdown();

        //At some point I will probably want sensor input for the main menu and or demo screen
        if (gameType == GameType.BOT)
        {
            this.setNoSensors();
        }
        else 
            if (Features.getInstance().isFeature(sensorFeatureFactory.NO_ORIENTATION))
            {
                this.setNoSensors();
            }
            else 
        {
                AllBinarySensorManager.getInstance().init();

                /*
                BasicArrayList list = 
                    sensorManager.getRegisteredSensorNamesList();

                if (list.contains(sensorManager.SENSOR_ACCELEROMETER))
                {
                    this.accelerometerSensorUpdateProcessor.process();

                    GyroSensorFactory.getInstance().setListener(
                            NoCompleteMotionGestureInputEventListener.getInstance());

                    this.setInputSensor(AccelerometerSensorFactory.getInstance());
                }
                else if (list.contains(sensorManager.SENSOR_ORIENTATION))
                {
                this.gyroSensorUpdateProcessor.process();

                AccelerometerSensorFactory.getInstance().setListener(
                        NoCompleteMotionGestureInputEventListener.getInstance());

                this.setInputSensor(GyroSensorFactory.getInstance());
            }
            else
            {
                // Could not find appropriate sensor
                this.setNoSensors();
            }
                 */
                this.setNoSensors();
        //}
    }
    
    public void sendNotifications(Object object) throws Exception
    {
        /*
        GameType gameType = layerManager.getGameInfo().getGameType();
        
        GameNotificationEvent gameNotificationEvent = 
            new GameNotificationEvent(
                    this, 
                    StringUtil.getInstance(),
                    SmallIntegerSingletonFactory.getInstance(3),
                    BasicColor.RED,
                    Boolean.FALSE);

        if (gameType == GameType.BOT)
        {
            AllBinarySensorManager sensorManager = 
                AllBinarySensorManager.getInstance();

            AllBinarySensorManager.getInstance().init();
            
            boolean isAnySensor = false;
            */
            /*
            BasicArrayList list = 
                sensorManager.getRegisteredSensorNamesList();
            
            if (list.contains(sensorManager.SENSOR_ACCELEROMETER))
            {
                gameNotificationEvent.setString("Accelerometer Sensor Found");
                GameNotificationEventHandler.getInstance().fireEvent(
                        gameNotificationEvent);
                
                isAnySensor = true;
            }
            
            if (list.contains(sensorManager.SENSOR_ORIENTATION))
            {
                gameNotificationEvent.setString("Orientation Sensor Found");
                GameNotificationEventHandler.getInstance().fireEvent(
                        gameNotificationEvent);
                
                isAnySensor = true;
            }
             */
/*
            BasicArrayList exclusiveOrientationSensorVector = (BasicArrayList) 
            GameFeatureChoiceGroups.getExclusiveInstance().get().get(
                    OrientationData.getInstance().ORIENTATION_SENSOR_INPUT);

            if(!isAnySensor && exclusiveOrientationSensorVector != null)
            {
                gameNotificationEvent.setString("No Sensor Found");
                GameNotificationEventHandler.getInstance().fireEvent(
                        gameNotificationEvent);
            }
            
            sensorManager.shutdown();
        }
        else
        {
            if (this.isAnySensor())
            {
                gameNotificationEvent.setString("Game Orientation Sensor Enabled");
                GameNotificationEventHandler.getInstance().fireEvent(
                        gameNotificationEvent);
            }
            else if (TouchScreenFactory.getInstance().isMultiTouch())
            {
                gameNotificationEvent.setString("Game Orientation Sensor Disabled");
                GameNotificationEventHandler.getInstance().fireEvent(
                        gameNotificationEvent);

                GameNotificationEvent gameNotificationEvent2 = new GameNotificationEvent(
                        this, "Multi-Touch Buttons Enabled",
                        SmallIntegerSingletonFactory.getInstance(3),
                        BasicColor.RED, Boolean.FALSE);

                GameNotificationEventHandler.getInstance().fireEvent(
                        gameNotificationEvent2);
            }
            else
            // It would be better to find out if a keyboard is available to turn
            // off the buttons.
            {
                gameNotificationEvent.setString("Game Orientation Sensor Disabled");
                GameNotificationEventHandler.getInstance().fireEvent(
                        gameNotificationEvent);

                GameNotificationEvent gameNotificationEvent2 = new GameNotificationEvent(
                        this, "Single-Touch Buttons Enabled",
                        SmallIntegerSingletonFactory.getInstance(3),
                        BasicColor.RED, Boolean.FALSE);

                GameNotificationEventHandler.getInstance().fireEvent(
                        gameNotificationEvent2);
            }
        }

 */
    }
}
