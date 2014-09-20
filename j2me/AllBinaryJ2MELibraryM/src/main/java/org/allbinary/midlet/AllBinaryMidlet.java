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
package org.allbinary.midlet;

/**
 *Detailed description This class is the main MIDlet for all MIDlets and
 *it sets the main canvas and starts a thread for the specified canvas.
 *
 *@author Travis Berthelot
 *Date: 11/19/02
 *
 */
import java.util.Hashtable;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.basic.string.StringUtil;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.basic.util.event.handler.BasicEventHandler;
import org.allbinary.system.Memory;

// MIDlet methods not overridden are final
public class AllBinaryMidlet extends MIDlet 
implements CommandListener
{
    private Hashtable hashtable = new Hashtable();
    private boolean destroyed;

    public AllBinaryMidlet()
    {
        LogUtil.put(LogFactory.getInstance(
                CommonStrings.getInstance().CONSTRUCTOR, this, "AllBinaryMidlet::AllBinaryMidlet"));
    }

    protected void setDisplay(Displayable newDisplay)
    {
        String title = StringUtil.getInstance().EMPTY_STRING;
        if (newDisplay != null)
        {
            title = newDisplay.getTitle();
            
            if (title != null)
            {
                LogUtil.put(LogFactory.getInstance("Setting: " + title + " Display: " + newDisplay, this, "setDisplay"));
            }
            else
            {
                LogUtil.put(LogFactory.getInstance("Setting: No Title, Display: " + newDisplay, this, "setDisplay"));
            }            
        }
        Display display = getDisplay();
        display.setCurrent(newDisplay);
    }

    public Display getDisplay()
    {
        return Display.getDisplay(this);
    }

    protected Displayable getCurrentDisplayable()
    {
        return Display.getDisplay(this).getCurrent();
    }

    public void setDestroyed(boolean destroyed)
    {
        this.destroyed = destroyed;
    }

    public boolean isDestroyed()
    {
        return destroyed;
    }

    protected void startApp() throws MIDletStateChangeException
    {
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
    }
    
    protected void pauseApp()
    {
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
    }
    
    protected void destroyApp(boolean unconditional, boolean isProgress)
    {
        
    }

    protected void destroyApp(boolean unconditional)
    {
        try
        {
            final String METHOD_NAME = "AllBinaryMidlet::destroyApp";
            
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, METHOD_NAME));
            
            PreLogUtil.put(Memory.getInfo(), this, METHOD_NAME);
            
            this.setDestroyed(true);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "AllBinaryMidlet::destroyApp", e));
        }
    }
    
    public void setStartStateHashtable(Hashtable hashtable) throws Exception
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_LABEL + hashtable, this, "setStartStateHashtable"));
        this.hashtable = hashtable;
    }

    public Hashtable getStartStateHashtable() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_LABEL + hashtable, this, "getStartStateHashtable"));
        return this.hashtable;
    }

    public Hashtable getCurrentStateHashtable() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "getStateHashtable"));
        return new Hashtable();
    }

    public void commandAction(Command command, Displayable displayable)
    {
    }
}