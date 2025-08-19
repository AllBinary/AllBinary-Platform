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

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.TextField;

import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.screen.CommandForm;
import org.allbinary.logic.communication.log.LogUtil;

public class SaveGameForm extends CommandForm
{

    private static CommandForm FORM = CommandForm.NULL_COMMAND_FORM;

    public static CommandForm getInstance(CommandListener commandListener, String title,
            BasicColor backgrounBasicColor, BasicColor foregroundBasicColor)
            throws Exception
    {
        FORM = new SaveGameForm(commandListener, title, backgrounBasicColor, foregroundBasicColor);
        return FORM;
    }

    public static CommandForm getInstance()
    {
        return FORM;
    }

    private SaveGameForm(CommandListener commandListener, String title,
            BasicColor backgrounBasicColor, BasicColor foregroundBasicColor)
        throws Exception
    {
        super(commandListener, title, backgrounBasicColor, foregroundBasicColor);

        logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);

        String timeString = Long.toString(System.currentTimeMillis());
        this.append(new TextField("Name: ", timeString, 30, TextField.ANY)); 
        
        this.initCommands(commandListener);
    }

    @Override
    public void initCommands(CommandListener cmdListener)
    {
        GameCommandsFactory gameCommandsFactory = 
            GameCommandsFactory.getInstance();
        
        this.removeAllCommands();
        this.addCommand(gameCommandsFactory.SAVE);
        this.addCommand(gameCommandsFactory.CLOSE_AND_SHOW_GAME_CANVAS);

        this.setCommandListener(cmdListener);
    }
    
}