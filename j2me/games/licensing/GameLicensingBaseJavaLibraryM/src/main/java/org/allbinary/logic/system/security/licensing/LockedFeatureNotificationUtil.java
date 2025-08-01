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
package org.allbinary.logic.system.security.licensing;

import org.allbinary.game.layer.hud.event.GameNotificationEvent;
import org.allbinary.game.layer.hud.event.GameNotificationEventHandler;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.media.audio.ErrorSound;
import org.allbinary.media.audio.PrimaryPlayerQueueFactory;

public class LockedFeatureNotificationUtil
{
    private static final LockedFeatureNotificationUtil instance = 
        new LockedFeatureNotificationUtil();

    public static LockedFeatureNotificationUtil getInstance()
    {
        return instance;
    }

    private final GameNotificationEvent gameNotificationEvent = 
        new GameNotificationEvent(
                this, 
                LicenseStrings.getInstance().LOCKED,
                SmallIntegerSingletonFactory.getInstance().getInstance(3),
                BasicColorFactory.getInstance().RED,
                BooleanFactory.getInstance().FALSE);
    
    public void fire() throws Exception
    {
        PrimaryPlayerQueueFactory.getInstance().add(
                ErrorSound.getInstance());

        GameNotificationEventHandler.getInstance().fireEvent(
                gameNotificationEvent);
    }

    public void fire(BasicColor basicColor) throws Exception
    {
        PrimaryPlayerQueueFactory.getInstance().add(
                ErrorSound.getInstance());

        gameNotificationEvent.setBasicColorP(basicColor);
        
        GameNotificationEventHandler.getInstance().fireEvent(
                gameNotificationEvent);
    }
}
