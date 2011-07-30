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
package abcs.logic.system.security.licensing;


import abcs.logic.java.bool.BooleanFactory;
import allbinary.game.layer.hud.basic.event.GameNotificationEvent;
import allbinary.game.layer.hud.basic.event.GameNotificationEventHandler;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.color.BasicColorFactory;
import allbinary.logic.math.SmallIntegerSingletonFactory;
import allbinary.media.audio.ErrorSound;
import allbinary.media.audio.PrimaryPlayerQueueFactory;

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

        gameNotificationEvent.setBasicColor(basicColor);
        
        GameNotificationEventHandler.getInstance().fireEvent(
                gameNotificationEvent);
    }
}
