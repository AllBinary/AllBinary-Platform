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

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.configuration.GameConfigurationGauge;
import org.allbinary.game.configuration.GameConfigurationUtil;
import org.allbinary.game.configuration.GameOptionsForm;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.midlet.MidletStrings;

public class GameFeatureItemCommandListener implements ItemCommandListener
{
    /*private static final GameFeatureItemCommandListener gameFeatureItemStateListener = new GameFeatureItemCommandListener();

    public static GameFeatureItemCommandListener getInstance()
    {
        return gameFeatureItemStateListener;
    }
    */
    
    private GameOptionsForm gameOptionsForm;
    
    public GameFeatureItemCommandListener(GameOptionsForm gameOptionsForm)
    {
        this.gameOptionsForm = gameOptionsForm;
    }
    
    public void commandAction(Command command, Item item)
    {
        try
        {
            String itemLabel = item.getLabel();
            LogUtil.put(LogFactory.getInstance(new StringMaker().append("Item: ").append(itemLabel).toString(), this, MidletStrings.getInstance().COMMAND_ACTION));

            if (item instanceof GameConfigurationGauge)
            {
                GameConfigurationUtil.getInstance().change(
                        this.gameOptionsForm,
                        (GameConfigurationGauge) item);
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, MidletStrings.getInstance().COMMAND_ACTION, e));
        }
    }
}
