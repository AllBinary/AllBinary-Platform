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
package org.allbinary.game.layer.hud.basic.notification;

import org.allbinary.game.layer.hud.event.GameNotificationEvent;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.util.BasicArrayList;

public class GameNotification
{
    public static final GameNotificationEvent NULL_GAME_NOTIFICATION_EVENT = new GameNotificationEvent(
        NullUtil.getInstance().NULL_OBJECT, StringUtil.getInstance().EMPTY_STRING, new Integer(0), BasicColorFactory.getInstance().RED, BooleanFactory.getInstance().FALSE);
    
    public final BasicArrayList stringList = new BasicArrayList();
    public final BasicArrayList timeList = new BasicArrayList();
    public final BasicArrayList colorList = new BasicArrayList();

    public void add(String string, Integer seconds, BasicColor basicColor)
    {
        if (!this.stringList.contains(string))
        {
            this.stringList.add(string);
            this.timeList.add(seconds);
            this.colorList.add(basicColor);
        }
    }

    public void clear()
    {
        this.stringList.clear();
        this.timeList.clear();
        this.colorList.clear();
    }

    public int getSize()
    {
        return this.stringList.size();
    }
}
