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

import org.allbinary.android.NullParcelable;
import javax.microedition.lcdui.Command;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Parcelable;

/**
 *
 * @author user
 */
public class NotificationBuilder
{
    public static final NotificationBuilder NULL_NOTIFICATION_BUILDER = new NotificationBuilder();
    
    public Parcelable build(Context context, Command command, String message, Integer integer, PendingIntent pendingIntent)
    {
        return NullParcelable.NULL_PARCELABLE;
    }

}
