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

import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.configuration.feature.GameFeatureFormUtil;
import org.allbinary.game.configuration.feature.InGameFeatureChoiceGroups;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.screen.CommandForm;
import org.allbinary.logic.string.StringUtil;

public class InGameOptionsForm extends CommandForm
{

    public static final Command DISPLAY  = new Command("Options In Game", StringUtil.getInstance().EMPTY_STRING,Command.SCREEN, 1);
    public static final Command SAVE  = new Command("Save", StringUtil.getInstance().EMPTY_STRING,Command.SCREEN, 1);
    public static final Command DEFAULT  = new Command("Default", StringUtil.getInstance().EMPTY_STRING,Command.SCREEN, 1);
    
    InGameOptionsForm(CommandListener commandListener, String title,
            BasicColor backgrounBasicColor, BasicColor foregroundBasicColor)
        throws Exception
    {
        super(commandListener, title, backgrounBasicColor, foregroundBasicColor);

        this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.CONSTRUCTOR);

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
    
    @Override
    public void initCommands(CommandListener cmdListener)
    {
        this.removeAllCommands();

        this.addCommand(GameCommandsFactory.getInstance().CLOSE_AND_SHOW_GAME_CANVAS);
        this.addCommand(InGameOptionsForm.DEFAULT);

        this.setCommandListener(cmdListener);
    }

}
