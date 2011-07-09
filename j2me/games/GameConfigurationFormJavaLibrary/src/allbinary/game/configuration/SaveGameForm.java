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
package allbinary.game.configuration;

/*
 *Copyright (c) 2002 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: 11/19/02
 *
 *
 *Modified By         When       ?
 *
 */

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.TextField;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.commands.GameCommandsFactory;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.displayable.screen.CommandForm;

public class SaveGameForm extends CommandForm
{
    private static SaveGameForm FORM;

    public static SaveGameForm getInstance(CommandListener commandListener, String title,
            BasicColor backgrounBasicColor, BasicColor foregroundBasicColor)
            throws Exception
    {
        FORM = new SaveGameForm(commandListener, title, backgrounBasicColor, foregroundBasicColor);
        return FORM;
    }

    public static SaveGameForm getInstance()
    {
        return FORM;
    }

    private SaveGameForm(CommandListener commandListener, String title,
            BasicColor backgrounBasicColor, BasicColor foregroundBasicColor)
        throws Exception
    {
        super(commandListener, title, backgrounBasicColor, foregroundBasicColor);

        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().CONSTRUCTOR));

        String timeString = Long.toString(System.currentTimeMillis());
        this.append(new TextField("Name: ", timeString, 30, TextField.ANY)); 
        
        this.initCommands(commandListener);
    }

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