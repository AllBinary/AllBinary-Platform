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

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.configuration.GameConfigurationGauge;
import allbinary.game.configuration.GameConfigurationUtil;
import allbinary.game.configuration.GameOptionsForm;
import allbinary.midlet.MidletStrings;

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
            LogUtil.put(LogFactory.getInstance("Item: " + itemLabel, this, MidletStrings.getInstance().COMMAND_ACTION));

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
