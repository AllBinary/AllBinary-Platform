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
package org.allbinary.graphics.displayable;

import java.util.Stack;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;

import org.allbinary.input.TouchJ2ME;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.displayable.command.MyCommandInterface;
import org.allbinary.graphics.displayable.command.MyCommandsFactory;
import org.allbinary.media.audio.Sound;

public class MyCanvas extends Canvas 
    implements DisplayableInterface, MyCommandInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final CanvasStrings canvasStrings = CanvasStrings.getInstance();
    
    //private boolean displayed;
    private boolean isPaused;
    private final Stack commandStack;
    
    public MyCanvas()
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.CONSTRUCTOR, this, commonStrings.CONSTRUCTOR));

        //This should update display info for J2ME Emulator. 
        //It could also be set with basically an event.
        DisplayInfoSingleton.getInstance().update(this, canvasStrings.CONSTRUCTOR);

        this.commandStack = new Stack();
    }

    public void setFullScreenMode(boolean mode)
    {
        //PreLogUtil.put("Old W: " + this.getWidth() + " H: " + this.getHeight() +  " m: " + mode + " fs: " + this.isFullScreenMode(), this, "setFullScreenMode");

        super.setFullScreenMode(mode);

        //PreLogUtil.put("New W: " + this.getWidth() + " H: " + this.getHeight() +  " m: " + mode + " fs: " + this.isFullScreenMode(), this, "setFullScreenMode");

        final DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();

        displayInfo.update(this, canvasStrings.SET_FULL_SCREEN_MODE);
    }

    protected void sizeChanged(int w, int h)
    {
        final DisplayInfoSingleton displayInfo =
            DisplayInfoSingleton.getInstance();

        displayInfo.update(this, canvasStrings.SIZE_CHANGED);
    }
    
    public Stack getCommandStack()
    {
        return this.commandStack;
    }

    public synchronized boolean isCommand(Command command)
    {
        return this.commandStack.contains(command);
    }

    public synchronized void addCommand(Command command)
    {
        if(!this.commandStack.contains(command))
        {
            commandStack.push(command);
            super.addCommand(command);
        }
    }

    public synchronized void removeCommand(Command command)
    {
        //commandStack.remove(command);
        commandStack.removeElement(command);
        super.removeCommand(command);
    }

    public synchronized void removeAllCommands()
    {
        int size = commandStack.size();
        for (int index = 0; index < size; index++)
        {
            super.removeCommand((Command) commandStack.pop());
        }
    }
    
    private CommandListener listener = null;

    public void setCommandListener(CommandListener l)
    {
        super.setCommandListener(l);
        listener = l;
    }

    public CommandListener getCustomCommandListener()
    {
        return this.listener;
    }

    public synchronized boolean isPaused()
    {
        return this.isPaused;
    }

    public void removePauseCommand()
    {
        this.removeCommand(MyCommandsFactory.getInstance().PAUSE_COMMAND);
    }

    public synchronized void pause()
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, canvasStrings.PAUSE));
        this.removePauseCommand();
        this.addCommand(MyCommandsFactory.getInstance().RESUME_COMMAND);
        this.setPaused(true);
    }

    public synchronized void unPause()
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, canvasStrings.UN_PAUSE));

        this.removeCommand(MyCommandsFactory.getInstance().RESUME_COMMAND);
        this.addCommand(MyCommandsFactory.getInstance().PAUSE_COMMAND);
        this.setPaused(false);
    }

    protected void process() throws Exception
    {
        DisplayInfoSingleton.getInstance().process();
    }
    
    //public void draw(gl)
    //{
        //You could request a repaint here
        //The Android version calls for a view repaint from repaint in Canvas
    //}
    
    //private final BaseGameStatistics baseGameStatistics = 
        //GameStatisticsFactory.getInstance();
        
    protected void paint(Graphics graphics)
    {
        //baseGameStatistics.nextRefresh();
        //DisplayInfoSingleton.getInstance().update(this);
    }

    public void destroy()
    {
        LogUtil.put(LogFactory.getInstance("Destroyed MyCanvas", this, "MyCanvas::destroy"));
    }

    /*
    public void setDisplayed(boolean displayed)
    {
        this.displayed = displayed;
    }

    protected boolean isDisplayed()
    {
        return displayed;
    }
    */

    protected void setPaused(boolean isPaused)
    {
        this.isPaused = isPaused;
    }

    private final TouchJ2ME touchME = new TouchJ2ME();
    
    protected void pointerDragged(int x, int y)
    {
        //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "pointerDragged"));
        //PreLogUtil.put(commonStrings.START, this, "pointerDragged");

        touchME.pointerDragged(x, y);
    }

    protected void pointerPressed(int x, int y)
    {
        //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "pointerPressed"));
        //PreLogUtil.put(commonStrings.START, this, "pointerPressed");

        touchME.pointerPressed(x, y);
    }

    protected void pointerReleased(int x, int y)
    {
        //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "pointerReleased"));
        //PreLogUtil.put(commonStrings.START, this, "pointerReleased");

        touchME.pointerReleased(x, y);
    }
    
    public void nextSong(final Sound nextSongSound) {
        
    }
}
