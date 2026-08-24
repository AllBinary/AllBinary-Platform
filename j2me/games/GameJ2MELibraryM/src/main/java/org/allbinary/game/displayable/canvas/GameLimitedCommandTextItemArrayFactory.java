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
package org.allbinary.game.displayable.canvas;

import jsinterop.annotations.JsType;

import org.allbinary.graphics.form.item.CommandTextItemArrayFactory;
import org.allbinary.graphics.form.item.LimitCommandsVisitor;
import jsinterop.annotations.JsMethod;


@JsType
public class GameLimitedCommandTextItemArrayFactory
{
    private static final GameLimitedCommandTextItemArrayFactory instance = 
        new GameLimitedCommandTextItemArrayFactory();
    
    private final CommandTextItemArrayFactory commandTextItemArrayFactory =
        new CommandTextItemArrayFactory(new LimitCommandsVisitor());

    @JsMethod
    public static GameLimitedCommandTextItemArrayFactory getInstance()
    {
        return GameLimitedCommandTextItemArrayFactory.instance;
    }

    @JsMethod
    public CommandTextItemArrayFactory getCommandTextItemArrayFactory()
    {
        return this.commandTextItemArrayFactory;
    }
}
