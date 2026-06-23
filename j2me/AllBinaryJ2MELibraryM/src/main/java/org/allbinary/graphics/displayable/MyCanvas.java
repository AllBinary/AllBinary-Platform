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
import javax.microedition.lcdui.NullCommandListener;

import org.allbinary.graphics.displayable.command.MyCommandInterface;
import org.allbinary.graphics.displayable.command.MyCommandsFactory;
import org.allbinary.input.TouchJ2ME;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.media.audio.Sound;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class MyCanvas extends Canvas 
    implements DisplayableInterface, MyCommandInterface
{

    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final CanvasStrings canvasStrings = CanvasStrings.getInstance();
    protected final StringUtil stringUtil = StringUtil.getInstance();
    protected final DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
    
    private final String name;
    private final BasicArrayList childNameList;
    
    private final Stack<Object> commandStack;
    
    private CommandListener commandListener = NullCommandListener.NULL_COMMAND_LISTENER;
    
    //private boolean displayed;
    private boolean paused;

//    public MyCanvas()
//    {
//        this( CommonStrings.getInstance().UNKNOWN, new BasicArrayListD());
//    }
    
    public MyCanvas(final String name, final BasicArrayList childNameList)
    {
        this.logUtil.putF(this.commonStrings.CONSTRUCTOR, this, this.commonStrings.CONSTRUCTOR);

        //This should update display info for J2ME Emulator. 
        //It could also be set with basically an event.
        this.displayInfo.update(this, this.canvasStrings.CONSTRUCTOR);

        this.name = name;
        this.childNameList = childNameList;

        this.commandStack = new Stack();
    }

    @Override
    public void setFullScreenMode(boolean mode)
    {
        //PreLogUtil.put("Old W: " + this.getWidth() + " H: " + this.getHeight() +  " m: " + mode + " fs: " + this.isFullScreenMode(), this, "setFullScreenMode");

        super.setFullScreenMode(mode);

        //PreLogUtil.put("New W: " + this.getWidth() + " H: " + this.getHeight() +  " m: " + mode + " fs: " + this.isFullScreenMode(), this, "setFullScreenMode");

        this.displayInfo.update(this, this.canvasStrings.SET_FULL_SCREEN_MODE);
    }

    @Override
    protected void sizeChanged(int w, int h)
    {
        this.displayInfo.update(this, this.canvasStrings.SIZE_CHANGED);
    }
    
    public Stack<Object> getCommandStack()
    {
        return this.commandStack;
    }

    public synchronized boolean isCommand(Command command)
    {
        return this.commandStack.contains(command);
    }

    @Override
    public synchronized void addCommand(Command command)
    {
        if(!this.commandStack.contains(command))
        {
            this.commandStack.push(command);
            super.addCommand(command);
        }
    }

    @Override
    public synchronized void removeCommand(Command command)
    {
        //commandStack.remove(command);
        this.commandStack.removeElement(command);
        super.removeCommand(command);
    }

    @Override
    public synchronized void removeAllCommands()
    {
        int size = this.commandStack.size();
        for (int index = 0; index < size; index++)
        {
            super.removeCommand((Command) this.commandStack.pop());
        }
    }

    @Override
    public void setCommandListener(CommandListener l)
    {
        super.setCommandListener(l);
        this.commandListener = l;
    }

    public CommandListener getCustomCommandListener()
    {
        return this.commandListener;
    }

    public synchronized boolean isPaused()
    {
        return this.paused;
    }

    public void removePauseCommand()
    {
        this.removeCommand(MyCommandsFactory.getInstance().PAUSE_COMMAND);
    }

    public synchronized void pause()
    {
        this.logUtil.putF(this.commonStrings.START, this, this.canvasStrings.PAUSE);
        this.removePauseCommand();
        this.addCommand(MyCommandsFactory.getInstance().RESUME_COMMAND);
        this.setPaused(true);
    }

    public synchronized void unPause()
    {
        this.logUtil.putF(this.commonStrings.START, this, this.canvasStrings.UN_PAUSE);

        this.removeCommand(MyCommandsFactory.getInstance().RESUME_COMMAND);
        this.addCommand(MyCommandsFactory.getInstance().PAUSE_COMMAND);
        this.setPaused(false);
    }

    protected void process() throws Exception
    {
        this.displayInfo.process();
    }
    
    //public void draw(gl)
    //{
        //You could request a repaint here
        //The Android version calls for a view repaint from repaint in Canvas
    //}
    
    //private final BaseGameStatistics baseGameStatistics = 
        //GameStatisticsFactory.getInstance();

    @Override
    protected void paint(Graphics graphics)
    {
        //baseGameStatistics.nextRefresh();
        //displayInfoSingleton.update(this);
    }

    public void draw(Graphics graphics)
    {
    }

    public boolean hasChild(MyCanvas displayable) {
        return this.childNameList.contains(displayable.name);
    }
    
    public void destroy()
    {
        this.logUtil.putF("Destroyed MyCanvas", this, "destroy");
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
        this.paused = isPaused;
    }

    public void keyPressedByDevice(int keyCode, int deviceId)
    {
    }

    public void keyRepeatedByDevice(int keyCode, int deviceId)
    {
    }

    public void keyReleasedByDevice(int keyCode, int deviceId)
    {
    }
    
    private final TouchJ2ME touchME = new TouchJ2ME();
    
    @Override
    protected void pointerDragged(int x, int y)
    {
        //this.logUtil.putF(this.commonStrings.START, this, "pointerDragged");
        //PreLogUtil.put(commonStrings.START, this, "pointerDragged");

        this.touchME.pointerDragged(x, y);
    }

    @Override
    protected void pointerPressed(int x, int y)
    {
        //this.logUtil.putF(this.commonStrings.START, this, "pointerPressed");
        //PreLogUtil.put(commonStrings.START, this, "pointerPressed");

        this.touchME.pointerPressed(x, y);
    }

    @Override
    protected void pointerReleased(int x, int y)
    {
        //this.logUtil.putF(this.commonStrings.START, this, "pointerReleased");
        //PreLogUtil.put(commonStrings.START, this, "pointerReleased");

        this.touchME.pointerReleased(x, y);
    }
    
    public void nextSong(final Sound nextSongSound, final int leftVolume, final int rightVolume) {
        
    }
}
