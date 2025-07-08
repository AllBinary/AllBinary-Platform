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

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.configuration.feature.GameFeatureFormUtil;
import org.allbinary.game.configuration.feature.InGameFeatureChoiceGroups;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.screen.CommandForm;

public class InGameOptionsForm extends CommandForm
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    public static final Command DISPLAY  = new Command("Options In Game", Command.SCREEN, 1);
    public static final Command SAVE  = new Command("Save", Command.SCREEN, 1);
    public static final Command DEFAULT  = new Command("Default", Command.SCREEN, 1);
    
    protected InGameOptionsForm(CommandListener commandListener, String title,
            BasicColor backgrounBasicColor, BasicColor foregroundBasicColor)
        throws Exception
    {
        super(commandListener, title, backgrounBasicColor, foregroundBasicColor);

        logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);

        GameFeatureFormUtil gameFeatureFormUtil = 
            GameFeatureFormUtil.getInstance();
        
        gameFeatureFormUtil.addChoiceGroup(this, 
                InGameFeatureChoiceGroups.getExclusiveInstance().get(),
                Choice.EXCLUSIVE);
        
        gameFeatureFormUtil.addChoiceGroup(this, 
                InGameFeatureChoiceGroups.getMultipleInstance().get(),
                Choice.MULTIPLE);

        this.initCommands(commandListener);
    }
    
    public void initCommands(CommandListener cmdListener)
    {
        this.removeAllCommands();

        this.addCommand(GameCommandsFactory.getInstance().CLOSE_AND_SHOW_GAME_CANVAS);
        this.addCommand(this.DEFAULT);

        this.setCommandListener(cmdListener);
    }

}
