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

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.NullCanvas;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import org.allbinary.logic.StdUtil;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;
import org.allbinary.system.Memory;
import org.allbinary.logic.NullUtil;
import org.allbinary.util.ABHashtable;

/**
 *Detailed description: This class is the main MIDlet for all MIDlets and
 *it sets the main canvas and starts a thread for the specified canvas.
 *
 *@author Travis Berthelot
 *Date: 11/19/02
 *
 */
// MIDlet methods not overridden are final
/*//JSNI Expose so JSNI can access this class *** &#10;
globalThis.org = globalThis.org || {}; &#10;
globalThis.org.allbinary = globalThis.org.allbinary || {}; &#10;
globalThis.org.allbinary.midlet = globalThis.org.allbinary.midlet || {}; &#10;
globalThis.org.allbinary.midlet.AllBinaryMidlet = AllBinaryMidlet; &#10;
console.log('Exported AllBinaryMidlet as globalThis');
*/
public class AllBinaryMidlet extends MIDlet 
implements CommandListener
{
    private static Object NULL_ALLBINARY_MIDLET = NullUtil.getInstance().NULL_OBJECT;
    
    public static AllBinaryMidlet getNullInstance() {
        
        if(AllBinaryMidlet.NULL_ALLBINARY_MIDLET == NullUtil.getInstance().NULL_OBJECT) {
            AllBinaryMidlet.NULL_ALLBINARY_MIDLET = new AllBinaryMidlet();
        }

        return (AllBinaryMidlet) AllBinaryMidlet.NULL_ALLBINARY_MIDLET;
    }
    
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final String SET_DISPLAY = "setDisplay";
    
    private final String SETTING_ = "Setting: ";
    private final String _DISPLAY_ = " Display: ";
    private final String SETTING_NO_TITLE = "Setting: No Title, Display: ";
    
    private ABHashtable<Object, Object> hashtable = StdUtil.getInstance().createHashtable();
    private boolean midletDestroyed;

    public AllBinaryMidlet()
    {
        this.logUtil.putF(this.commonStrings.CONSTRUCTOR, this, "AllBinaryMidlet::AllBinaryMidlet");
    }

    protected void setDisplay(final Displayable newDisplay)
    {
        String title = StringUtil.getInstance().EMPTY_STRING;
        if (newDisplay != NullCanvas.NULL_CANVAS)
        {
            title = newDisplay.getTitle();
            
            if (title != null)
            {
                this.logUtil.putF(new StringMaker().append(this.SETTING_).append(title).append(this._DISPLAY_).append(StringUtil.getInstance().toString(newDisplay)).toString(), this, this.SET_DISPLAY);
            }
            else
            {
                this.logUtil.putF(new StringMaker().append(this.SETTING_NO_TITLE).append(StringUtil.getInstance().toString(newDisplay)).toString(), this, this.SET_DISPLAY);
            }            
        }
        final Display display = this.getDisplay();
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
        this.midletDestroyed = destroyed;
    }

    public boolean isDestroyed()
    {
        return this.midletDestroyed;
    }

    @Override
    protected void startApp() throws MIDletStateChangeException
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }
    
    @Override
    protected void pauseApp()
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }
    
    protected void destroyAppInRunnable(final boolean unconditional, final boolean isProgress)
    {
        
    }

    @Override
    protected void destroyApp(final boolean unconditional)
    {
        final String METHOD_NAME = "AllBinaryMidlet::destroyApp";
        try
        {   
            this.logUtil.putF(this.commonStrings.START, this, METHOD_NAME);
            
            PreLogUtil.put(Memory.getInfo(), this, METHOD_NAME);
            
            this.setDestroyed(true);
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, METHOD_NAME, e);
        }
    }
    
    public void setStartStateHashtable(final ABHashtable<Object, Object> hashtable) throws Exception
    {
        this.logUtil.putF(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(StringUtil.getInstance().toString(hashtable)).toString(), this, "setStartStateHashtable");
        this.hashtable = hashtable;
    }

    public ABHashtable<Object, Object> getStartStateHashtable() throws Exception
    {
        this.logUtil.putF(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(StringUtil.getInstance().toString(this.hashtable)).toString(), this, "getStartStateHashtable");
        return this.hashtable;
    }

    public ABHashtable<Object, Object> getCurrentStateHashtable() throws Exception
    {
        this.logUtil.putF(this.commonStrings.START, this, "getStateHashtable");
        return StdUtil.getInstance().NULL_TABLE;
    }

    @Override
    public void commandAction(final Command command, final Displayable displayable)
    {
    }
}