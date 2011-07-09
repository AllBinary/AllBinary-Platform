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

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.configuration.GameConfigurationGauge;
import allbinary.game.configuration.GameConfigurationUtil;
import allbinary.game.configuration.GameOptionsForm;

public class GameFeatureItemStateListener implements ItemStateListener
{
    //private static final GameFeatureItemStateListener gameFeatureItemStateListener = new GameFeatureItemStateListener();

    private static BasicArrayList toggleList = new BasicArrayList();

    private GameOptionsForm gameOptionsForm;
    
    public GameFeatureItemStateListener(GameOptionsForm gameOptionsForm)
    {
        this.gameOptionsForm = gameOptionsForm;
        
        // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().CONSTRUCTOR));

        GameFeatureFactory gameFeatureFactory = 
            GameFeatureFactory.getInstance();
        
        this.add(gameFeatureFactory.ARTIFICIAL_INTELLEGENCE_PROCESSOR);
        this.add(gameFeatureFactory.COLLIDABLE_INTERFACE_LAYER_PROCESSOR);

        this.add(gameFeatureFactory.DAMAGE_FLOATERS);
        this.add(gameFeatureFactory.DROPPED_ITEMS);

        this.add(gameFeatureFactory.GAME_INPUT_LAYER_PROCESSOR);
        
        this.add(gameFeatureFactory.HEALTH_BARS);
        this.add(gameFeatureFactory.SOUND);
        //toggleList.add(GameFeature.OPENGL);

        this.add(gameFeatureFactory.TICKABLE_LAYER_PROCESSOR);
    }

    public static void add(GameFeature gameFeature)
    {
        if(!toggleList.contains(gameFeature))
        {
            toggleList.add(gameFeature);
        }
    }
    /*
    public static GameFeatureItemStateListener getInstance()
    {
        return gameFeatureItemStateListener;
    }
    */

    public void itemStateChanged(Item item)
    {
        try
        {
            String itemLabel = item.getLabel();

            LogUtil.put(LogFactory.getInstance("Item: " + itemLabel, this, "itemStateChanged"));

            if (item instanceof GameConfigurationGauge)
            {
                GameConfigurationUtil.getInstance().change(
                        this.gameOptionsForm, (GameConfigurationGauge) item);
            }
            else if (item instanceof ChoiceGroup)
            {
                GameFeatureUtil gameFeatureUtil = 
                    GameFeatureUtil.getInstance();
                
                if (gameFeatureUtil.isExclusive(itemLabel))
                {
                    gameFeatureUtil.updateExclusive((ChoiceGroup) item);
                }
                else
                {
                    gameFeatureUtil.updateMultiple((ChoiceGroup) item);
                }
            }

        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "itemStateChanged", e));
        }
    }
}
