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

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;

import org.allbinary.game.configuration.GameConfigurationGauge;
import org.allbinary.game.configuration.GameConfigurationUtil;
import org.allbinary.game.configuration.GameOptionsForm;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class GameFeatureItemStateListener implements ItemStateListener
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    //private static final GameFeatureItemStateListener gameFeatureItemStateListener = new GameFeatureItemStateListener();

    private static BasicArrayList toggleList = new BasicArrayList();

    private GameOptionsForm gameOptionsForm;
    
    public GameFeatureItemStateListener(GameOptionsForm gameOptionsForm)
    {
        this.gameOptionsForm = gameOptionsForm;
        
        // logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);

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

            logUtil.put(new StringMaker().append(CommonLabels.getInstance().ITEM_LABEL).append(itemLabel).toString(), this, "itemStateChanged");

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
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, "itemStateChanged", e);
        }
    }
}
