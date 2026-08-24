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
package org.allbinary.game;

import jsinterop.annotations.JsType;
import org.allbinary.logic.string.StringUtil;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;

@JsType
public class GameMode
{
    @JsProperty
    public static GameMode NONE = new GameMode(StringUtil.getInstance().NULL_STRING);
    @JsProperty
    public static GameMode SERVER = new GameMode("Server");
    @JsProperty
    public static GameMode CLIENT = new GameMode("Client");

    private String name = StringUtil.getInstance().EMPTY_STRING;

    @JsConstructor
    private GameMode(String name)
    {
        this.setName(name);
    }

    @JsMethod
    private void setName(String name)
    {
        this.name = name;
    }

    @JsMethod
    public String getName()
    {
        return this.name;
    }

    @JsMethod
    public String toString()
    {
        return this.getName();
    }
}
