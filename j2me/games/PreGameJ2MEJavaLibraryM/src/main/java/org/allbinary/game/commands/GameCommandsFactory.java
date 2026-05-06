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
package org.allbinary.game.commands;

import javax.microedition.lcdui.Command;

import org.allbinary.graphics.displayable.CanvasStrings;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;

public class GameCommandsFactory
{
    private static final GameCommandsFactory instance = new GameCommandsFactory();
    
    public static GameCommandsFactory getInstance()
    {
        return GameCommandsFactory.instance;
    }
        
    public final Command TOGGLE_FULLSCREEN  = new Command("Toggle FullScreen", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 1);
    public final Command TOGGLE_KEYBOARD  = new Command("Keyboard", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 1);
    
    public final Command SET_MENU_DISPLAYABLE  = new Command("Set Menu Displayable", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 1);

    public final Command APPLICATION_UPDATE  = new Command(CommonStrings.getInstance().UPDATE, StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 1);

    public final Command DISPLAY_ABOUT  = new Command(CanvasStrings.getInstance().ABOUT, StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 2);
    public final Command OPEN_WEB_URL  = new Command("Open Web URL", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 2);

    //general commands
    public final Command CLOSE_OPTIONS  = new Command("Back", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 1);
    public final Command DISPLAY_OPTIONS  = new Command(CanvasStrings.getInstance().OPTIONS, StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 2);

    public final Command START_TRACE  = new Command("Start Trace", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 1);
    public final Command STOP_TRACE  = new Command("Stop Trace", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 1);

    public final Command DEFAULT_OPTIONS  = new Command("Default", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 1);
   
    public final Command SHOW_GAME_CANVAS  = new Command("Back to Game", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 1);
    public final Command CLOSE_AND_SHOW_GAME_CANVAS  = new Command("Back", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 1);

    public final Command QUIT_COMMAND  = new Command("Quit", StringUtil.getInstance().EMPTY_STRING,Command.STOP, 1);
    public final Command RESTART_COMMAND  = new Command("Restart", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 0);

    public final Command DISPLAY_SAVE_FORM = new Command("Save To", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 3);
    public final Command SAVE = new Command("Save", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 3);
    public final Command DISPLAY_LOAD_FORM = new Command("Load", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 3);
    public final Command LOAD_FILE = new Command("Load File", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 3);
    public final Command DELETE_FILE = new Command("Delete File", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 1);
   
    public final Command EXIT_COMMAND  = new Command("Exit", StringUtil.getInstance().EMPTY_STRING,Command.EXIT, 2);
    public final Command EXIT_WITHOUT_PROGRESS_COMMAND  = new Command("Exit Without Progress", StringUtil.getInstance().EMPTY_STRING,Command.EXIT, 2);
    public final Command START_COMMAND  = new Command(CommonStrings.getInstance().START, StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 1);
    public final Command CONTINUE_COMMAND  = new Command("Continue", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 1);
    public final Command SELECT_COMMAND  = new Command("Select", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 1);
   
    public final Command FEATURE_UPDATE  = new Command("Feature Update", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 1);

    public final Command BUY_COMMAND  = new Command("BUY", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 1);
    
    protected GameCommandsFactory()
    {
    }
}
