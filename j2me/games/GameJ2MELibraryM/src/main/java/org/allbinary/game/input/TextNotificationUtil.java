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
package org.allbinary.game.input;

import org.allbinary.game.layer.hud.event.GameNotificationEvent;
import org.allbinary.game.layer.hud.event.GameNotificationEventHandler;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.media.audio.ErrorSound;
import org.allbinary.media.audio.PrimaryPlayerQueueFactory;
import org.allbinary.media.audio.SelectSound;

public class TextNotificationUtil
{
    private static final TextNotificationUtil instance = 
        new TextNotificationUtil();

    public static TextNotificationUtil getInstance()
    {
        return instance;
    }
    
    public void fireError(String message) throws Exception
    {
        PrimaryPlayerQueueFactory.getInstance().add(
                ErrorSound.getInstance());
        this.fire(message);
    }

    public void fireNew(String message) throws Exception
    {
        PrimaryPlayerQueueFactory.getInstance().add(
                SelectSound.getInstance());
        this.fire(message);
    }

    public void fireSuccess(String message) throws Exception
    {
        PrimaryPlayerQueueFactory.getInstance().add(
                SelectSound.getInstance());
        this.fire(message);
    }

    private final Integer TWO = SmallIntegerSingletonFactory.getInstance().getInstance(2);
    private final Boolean FALSE = BooleanFactory.getInstance().FALSE;
    private final GameNotificationEventHandler gameNotificationEventHandler = 
        GameNotificationEventHandler.getInstance();
    
    public void fire(String message) throws Exception
    {
        GameNotificationEvent gameNotificationEvent = new GameNotificationEvent(
                    this, message, TWO, BasicColorFactory.getInstance().RED, FALSE);

        gameNotificationEventHandler.fireEvent(gameNotificationEvent);
    }
}
