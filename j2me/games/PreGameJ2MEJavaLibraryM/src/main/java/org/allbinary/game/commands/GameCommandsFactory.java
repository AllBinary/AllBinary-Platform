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

import org.allbinary.string.CommonStrings;

public class GameCommandsFactory
{
    private static final GameCommandsFactory instance = new GameCommandsFactory();
    
    public static GameCommandsFactory getInstance()
    {
        return instance;
    }
        
    public final Command TOGGLE_FULLSCREEN  = new Command("Toggle FullScreen", Command.SCREEN, 1);
    public final Command TOGGLE_KEYBOARD  = new Command("Keyboard", Command.SCREEN, 1);
    
    public final Command SET_MENU_DISPLAYABLE  = new Command("Set Menu Displayable", Command.SCREEN, 1);

    public final Command APPLICATION_UPDATE  = new Command(CommonStrings.getInstance().UPDATE, Command.SCREEN, 1);

    public final Command DISPLAY_ABOUT  = new Command(CanvasStrings.getInstance().ABOUT, Command.SCREEN, 2);
    public final Command OPEN_WEB_URL  = new Command("Open Web URL", Command.SCREEN, 2);

    //general commands
    public final Command CLOSE_OPTIONS  = new Command("Back", Command.SCREEN, 1);
    public final Command DISPLAY_OPTIONS  = new Command(CanvasStrings.getInstance().OPTIONS, Command.SCREEN, 2);

    public final Command START_TRACE  = new Command("Start Trace", Command.SCREEN, 1);
    public final Command STOP_TRACE  = new Command("Stop Trace", Command.SCREEN, 1);

    public final Command DEFAULT_OPTIONS  = new Command("Default", Command.SCREEN, 1);
   
    public final Command SHOW_GAME_CANVAS  = new Command("Back to Game", Command.SCREEN, 1);
    public final Command CLOSE_AND_SHOW_GAME_CANVAS  = new Command("Back", Command.SCREEN, 1);

    public final Command QUIT_COMMAND  = new Command("Quit", Command.STOP, 1);
    public final Command RESTART_COMMAND  = new Command("Restart", Command.SCREEN, 0);

    public final Command DISPLAY_SAVE_FORM = new Command("Save To", Command.SCREEN, 3);
    public final Command SAVE = new Command("Save", Command.SCREEN, 3);
    public final Command DISPLAY_LOAD_FORM = new Command("Load", Command.SCREEN, 3);
    public final Command LOAD_FILE = new Command("Load File", Command.SCREEN, 3);
    public final Command DELETE_FILE = new Command("Delete File", Command.SCREEN, 1);
   
    public final Command EXIT_COMMAND  = new Command("Exit", Command.EXIT, 2);
    public final Command EXIT_WITHOUT_PROGRESS_COMMAND  = new Command("Exit Without Progress", Command.EXIT, 2);
    public final Command START_COMMAND  = new Command(CommonStrings.getInstance().START, Command.SCREEN, 1);
    public final Command CONTINUE_COMMAND  = new Command("Continue", Command.SCREEN, 1);
    public final Command SELECT_COMMAND  = new Command("Select", Command.SCREEN, 1);
   
    public final Command FEATURE_UPDATE  = new Command("Feature Update", Command.SCREEN, 1);

    public final Command BUY_COMMAND  = new Command("BUY", Command.SCREEN, 1);
    
    protected GameCommandsFactory()
    {
    }
}
