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

public class NotificationUtil
{
    private static final NotificationUtil SINGLETON = new NotificationUtil();
        
    public static NotificationUtil getInstance()
    {
        return SINGLETON;
    }
    
    public void notify(Command command, String resource, String message)
    {
    }
}
