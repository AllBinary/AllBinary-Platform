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

import jsinterop.annotations.JsType;

import javax.microedition.lcdui.Command;
import org.allbinary.logic.string.StringUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class MyCommandsFactory
{
    private static final MyCommandsFactory instance = new MyCommandsFactory();

    @JsMethod
    public static MyCommandsFactory getInstance()
    {
        return MyCommandsFactory.instance;
    }

    @JsProperty
    public final Command NO_COMMAND = new Command(StringUtil.getInstance().EMPTY_STRING, StringUtil.getInstance().EMPTY_STRING, -1, -1);
    
    @JsProperty
    public final Command GAUGE_CHANGE = new Command("GAUGE_CHANGE", StringUtil.getInstance().EMPTY_STRING, Command.ITEM, 1);

    // general commands
    @JsProperty
    public final Command SET_DISPLAYABLE = new Command("Display", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 2);

    @JsProperty
    public final Command PAUSE_COMMAND = new Command("Pause", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 0);
    @JsProperty
    public final Command RESUME_COMMAND = new Command("Resume", StringUtil.getInstance().EMPTY_STRING, Command.SCREEN, 0);

    @JsConstructor
    protected MyCommandsFactory()
    {
    }
}