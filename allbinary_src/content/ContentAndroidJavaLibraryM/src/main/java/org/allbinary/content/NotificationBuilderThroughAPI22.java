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
package org.allbinary.content;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import javax.microedition.lcdui.Command;

/**
 *
 * @author user
 */
public class NotificationBuilderThroughAPI22 extends NotificationBuilder
{

    public Notification build(Context context, Command command, String message, Integer integer, PendingIntent pendingIntent)
    {
        Notification notification = new Notification(
                integer.intValue(), message, System.currentTimeMillis());

        notification.setLatestEventInfo(
                context, command.getLabel(), message, pendingIntent);

        return notification;
    }
}
