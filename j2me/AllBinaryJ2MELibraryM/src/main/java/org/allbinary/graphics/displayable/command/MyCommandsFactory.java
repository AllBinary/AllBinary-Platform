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
package org.allbinary.graphics.displayable.command;

import javax.microedition.lcdui.Command;
import org.allbinary.logic.string.StringUtil;

public class MyCommandsFactory
{
    private static final MyCommandsFactory instance = new MyCommandsFactory();

    public static MyCommandsFactory getInstance()
    {
        return instance;
    }

    public final Command NO_COMMAND = new Command(StringUtil.getInstance().EMPTY_STRING, -1, -1);
    
    public final Command GAUGE_CHANGE = new Command("GAUGE_CHANGE", Command.ITEM, 1);

    // general commands
    public final Command SET_DISPLAYABLE = new Command("Display", Command.SCREEN, 2);

    public final Command PAUSE_COMMAND = new Command("Pause", Command.SCREEN, 0);
    public final Command RESUME_COMMAND = new Command("Resume", Command.SCREEN, 0);

    protected MyCommandsFactory()
    {
    }
}