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

import javax.microedition.lcdui.Command;

import org.allbinary.data.resource.ResourceUtil;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import org.allbinary.android.AndroidInfoFactory;

import org.allbinary.string.CommonStrings;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class NotificationUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final NotificationUtil SINGLETON = new NotificationUtil();
    
    private NotificationManager notificationManager = (NotificationManager) 
        ResourceUtil.getInstance().getContext().getSystemService(Context.NOTIFICATION_SERVICE); 
    
    public static NotificationUtil getInstance()
    {
        return SINGLETON;
    }
    
    private final NotificationBuilder notificationBuilder;
    
    private NotificationUtil()
    {
        final int SDK_VERSION = AndroidInfoFactory.getInstance().getVersion();
        
        if(SDK_VERSION > 22)
        {
            notificationBuilder = new NotificationBuilderAPI23();
        }
        else
        {
            notificationBuilder = null;
            //notificationBuilder = new NotificationBuilderThroughAPI22();
        }
    }
    
    public void notify(final Command command, final String resource, final String message)
    {
        final Context context = ResourceUtil.getInstance().getContext();
        
        final Intent intent = CommandUriAction.getInstance().getIntent(command);

        //Load a new activity Intent
        //Intent intent = new Intent(Intent.ACTION_MAIN);
        //intent.setClass(context, context.getClass());
        //intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        /*
            intent.putExtra( "data", "test data");
       */

        final Integer integer = ResourceUtil.getInstance().getResourceId(resource);

        final int FLAG_IMMUTABLE = 1<<26;
        final int SDK_VERSION = AndroidInfoFactory.getInstance().getVersion();
        final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, SDK_VERSION > 22 ? FLAG_IMMUTABLE : 0);

        if(notificationBuilder == null) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.NOT_IMPLEMENTED);
            return;
        }
        
        final Notification notification = notificationBuilder.build(context, command, message, integer, pendingIntent);
        
        //Android 13 requires a permission for this
        //notificationManager.notify(command.hashCode(), notification);

        //context.startActivity(intent);
    }
}
