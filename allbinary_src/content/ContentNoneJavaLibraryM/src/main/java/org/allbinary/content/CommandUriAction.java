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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.resource.ResourceUtil;

public class CommandUriAction
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final CommandUriAction instance = new CommandUriAction();
 
    public static CommandUriAction getInstance()
    {
        return instance;
    }
    
    //private Hashtable hashtable = new Hashtable();

    public void add(Command command, String url)
    {
        //hashtable.put(command, url);
    }

    public void process(Command command)
    {
        /*
        try
        {
        }
        catch (Exception e)
        {
            logUtil.put(this.commonStrings.EXCEPTION, this, commonStrings.PROCESS, e);
        }
        */
    }
}
