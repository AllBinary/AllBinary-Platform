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
package org.allbinary.game.layer.hud.event;

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;

public class GameNotificationEvent extends AllBinaryEventObject
{
    private String string = StringUtil.getInstance().EMPTY_STRING;
    private final Integer seconds;
    private BasicColor basicColor = BasicColorFactory.getInstance().BLACK;
    private final Boolean removeable;

    public GameNotificationEvent(
            Object object, 
            String string,
            Integer seconds,
            BasicColor basicColor,
            Boolean permanent)
    {
        super(object);
        
        this.setString(string);
        this.seconds = seconds;
        this.setBasicColor(basicColor);
        this.removeable = permanent;
    }

    public String getString()
    {
        return string;
    }

    public Integer getSeconds()
    {
        return seconds;
    }

    public BasicColor getBasicColor()
    {
        return basicColor;
    }

    public Boolean getPermanent()
    {
        return removeable;
    }

    public void setString(String string)
    {
        this.string = string;
    }

    public void setBasicColor(BasicColor basicColor)
    {
        this.basicColor = basicColor;
    }
}
