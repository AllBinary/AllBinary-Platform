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

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.game.configuration.event.GameFeatureEvent;
import allbinary.game.configuration.event.GameFeatureEventHandler;
import org.allbinary.util.BasicArrayList;

public class Features
{
    private static final Features SINGLETON = new Features();

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
    public void addDefault(Feature gameFeature) throws Exception
    {
        //LogUtil.put(LogFactory.getInstance(
          //      CommonStrings.getInstance().START_LABEL + gameFeature.toString(), 
            //    "GameFeature", "addDefault"));

        this.add(gameFeature);

        if (!defaultList.contains(gameFeature))
        {
            defaultList.add(gameFeature);
        }
    }

    public void add(Feature gameFeature) throws Exception
    {
        if (!list.contains(gameFeature))
        {
//            if(gameFeature == SensorFeatureFactory.getInstance().ORIENTATION_SENSORS)
//            {
//                ForcedLogUtil.log("here it is: " + this.getClass().getClassLoader().getClass().getName() + this.getClass().getClassLoader().hashCode() , this);
//            }
            LogUtil.put(LogFactory.getInstance(
                    CommonStrings.getInstance().START_LABEL + gameFeature.toString(), this, CommonStrings.getInstance().ADD));

            list.add(gameFeature);

            GameFeatureEventHandler.getInstance().fireEvent(
                    new GameFeatureEvent(gameFeature, gameFeature.toString()));
        }
    }

  //For default or reload required features
    public void removeDefault(Feature gameFeature) throws Exception
    {
        LogUtil.put(LogFactory.getInstance(
                CommonStrings.getInstance().START_LABEL + gameFeature.toString(), this, "removeDefault"));

        this.remove(gameFeature);
        defaultList.remove(gameFeature);
    }

    public void remove(Feature gameFeature) throws Exception
    {
        if (list.contains(gameFeature))
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().REMOVE));
            list.remove(gameFeature);
            GameFeatureEventHandler.getInstance().fireEvent(
                    new GameFeatureEvent(gameFeature, gameFeature.toString()));
        }
    }

    private void init() // throws Exception
    {
        try
        {            
            GameFeatureFactory gameFeatureFactory = 
                GameFeatureFactory.getInstance();
            
            this.addDefault(gameFeatureFactory.ARTIFICIAL_INTELLEGENCE_PROCESSOR);
            this.addDefault(gameFeatureFactory.COLLIDABLE_INTERFACE_LAYER_PROCESSOR);
            this.addDefault(gameFeatureFactory.GAME_INPUT_LAYER_PROCESSOR);
            this.addDefault(gameFeatureFactory.TICKABLE_LAYER_PROCESSOR);

            this.addDefault(InputFeatureFactory.getInstance().MULTI_KEY_PRESS);
            this.addDefault(InputFeatureFactory.getInstance().REMOVE_DUPLICATE_KEY_PRESSES);

            this.addDefault(gameFeatureFactory.SCREEN_SHAKE);

            this.addDefault(SensorFeatureFactory.getInstance().ORIENTATION_SENSORS);

            //this.addDefault(GameFeature.SIMULATED_ORIENTATION_SENSORS);
            //this.addDefault(GameFeature.NO_ORIENTATION);

            // defaultVector.addAll(vector);
            this.addDefault(
                    TouchFeatureFactory.getInstance().TOUCH_ENABLED);
            this.addDefault(
                    TouchFeatureFactory.getInstance().AUTO_HIDE_SHOW_SCREEN_BUTTONS);

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
            PreLogUtil.put(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().INIT, e);
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
