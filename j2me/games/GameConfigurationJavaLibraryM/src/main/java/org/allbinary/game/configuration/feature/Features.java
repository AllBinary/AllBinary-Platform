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

import org.allbinary.game.configuration.event.GameFeatureEvent;
import org.allbinary.game.configuration.event.GameFeatureEventHandler;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.os.GenericOperatingSystem;
import org.allbinary.logic.system.os.OperatingSystemFactory;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class Features
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final Features SINGLETON = new Features();

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final BasicArrayList list;
    private final BasicArrayList defaultList;

    private Features()
    {
        list = new BasicArrayList();
        defaultList = new BasicArrayList();
        
        this.init();
    }

    public static Features getInstance()
    {
        return SINGLETON;
    }

    //For default or reload required features
    public void addDefault(final Feature gameFeature) throws Exception
    {
        //logUtil.put(
          //      commonStrings.START_LABEL).append(gameFeature.toString(), 
            //    "GameFeature", "addDefault");

        this.add(gameFeature);

        if (!defaultList.contains(gameFeature))
        {
            defaultList.add(gameFeature);
        }
    }

    public void add(final Feature gameFeature) throws Exception
    {
        if (!list.contains(gameFeature))
        {
//            if(gameFeature == SensorFeatureFactory.getInstance().ORIENTATION_SENSORS)
//            {
//                ForcedLogUtil.log("here it is: ").append(this.getClass().getClassLoader().getClass().getName()).append(this.getClass().getClassLoader().hashCode() , this);
//            }
            logUtil.put(
                    new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(gameFeature.toString()).toString(), this, commonStrings.ADD);

            list.add(gameFeature);

            GameFeatureEventHandler.getInstance().fireEvent(
                    new GameFeatureEvent(gameFeature, gameFeature.toString()));
        }
    }

  //For default or reload required features
    public void removeDefault(final Feature gameFeature) throws Exception
    {
        logUtil.put(
                new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(gameFeature.toString()).toString(), this, "removeDefault");

        this.remove(gameFeature);
        defaultList.remove(gameFeature);
    }

    public void remove(final Feature gameFeature) throws Exception
    {
        if (list.contains(gameFeature))
        {
            logUtil.put(commonStrings.START, this, commonStrings.REMOVE);
            list.remove(gameFeature);
            GameFeatureEventHandler.getInstance().fireEvent(
                    new GameFeatureEvent(gameFeature, gameFeature.toString()));
        }
    }

    private void init() // throws Exception
    {
        try
        {            
            final GameFeatureFactory gameFeatureFactory = GameFeatureFactory.getInstance();
            final InputFeatureFactory inputFeatureFactory = InputFeatureFactory.getInstance();
            final SensorFeatureFactory sensorFeatureFactory = SensorFeatureFactory.getInstance();
            final TouchFeatureFactory touchFeatureFactory = TouchFeatureFactory.getInstance();
            
            this.addDefault(gameFeatureFactory.ARTIFICIAL_INTELLEGENCE_PROCESSOR);
            this.addDefault(gameFeatureFactory.COLLIDABLE_INTERFACE_LAYER_PROCESSOR);
            this.addDefault(gameFeatureFactory.GAME_INPUT_LAYER_PROCESSOR);
            this.addDefault(gameFeatureFactory.TICKABLE_LAYER_PROCESSOR);
            
            this.addDefault(inputFeatureFactory.MULTI_KEY_PRESS);
            this.addDefault(inputFeatureFactory.REMOVE_DUPLICATE_KEY_PRESSES);

            this.addDefault(gameFeatureFactory.SCREEN_SHAKE);
            this.addDefault(gameFeatureFactory.POST_IMAGE_LOADING_MODIFICATION);
            
            final GenericOperatingSystem operatingSystemInterface
                    = OperatingSystemFactory.getInstance().getOperatingSystemInstance();

            if (operatingSystemInterface.isOverScan()) {
                this.addDefault(sensorFeatureFactory.NO_ORIENTATION);
                this.addDefault(touchFeatureFactory.HIDE_SCREEN_BUTTONS);
            } else {
                this.addDefault(sensorFeatureFactory.ORIENTATION_SENSORS);
                this.addDefault(touchFeatureFactory.AUTO_HIDE_SHOW_SCREEN_BUTTONS);
            }
                
              //this.addDefault(GameFeature.SIMULATED_ORIENTATION_SENSORS);

                // defaultVector.addAll(vector);
            
            this.addDefault(touchFeatureFactory.TOUCH_ENABLED);

            this.addDefault(MainFeatureFactory.getInstance().FULL_SCREEN);

            //this.addDefault(MainFeatureFactory.getInstance().LOAD_ALL);
            this.addDefault(MainFeatureFactory.getInstance().LOAD_ONDEMAND);

            //Currently creates dependency issue
            //if(DebugFactory.getInstance() != NoDebug.getInstance())
            //{
                //this.addDefault(OpenGLFeatureFactory.getInstance().OPENGL_OPTIONS);
            //}
        }
        catch (Exception e)
        {
            PreLogUtil.put(commonStrings.EXCEPTION, this, commonStrings.INIT, e);
        }
    }

  //For default or reload required features
    public boolean isDefault(Feature gameFeature)
    {
        return defaultList.contains(gameFeature);
    }

    public boolean isFeature(Feature gameFeature)
    {
        return this.list.contains(gameFeature);
    }

    public void toggle(Feature gameFeature) throws Exception
    {
        if (this.isFeature(gameFeature))
        {
            this.remove(gameFeature);
        }
        else
        {
            this.add(gameFeature);
        }
    }

}
