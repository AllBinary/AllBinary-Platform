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
package org.allbinary.game.displayable.canvas;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;

import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.logic.communication.log.LogUtil;

public class NullGameCanvas extends AllBinaryGameCanvas
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final NullGameCanvas SINGLETON = new NullGameCanvas();
    
    public static NullGameCanvas getInstance()
    {
        return SINGLETON;
    }

    public static NullGameCanvas getInstance(
            final AllBinaryGameLayerManager gameLayerManager)
    {
        final NullGameCanvas nullGameCanvas = new NullGameCanvas(gameLayerManager);
        
        nullGameCanvas.setInitialized(true);
        nullGameCanvas.setTitle(NO_GAME);

        return nullGameCanvas;
    }
    
    public static final String NO_GAME = "No Background Game";
    
    protected NullGameCanvas(final AllBinaryGameLayerManager gameLayerManager)
    {
        super(gameLayerManager);

        super.setWait(1200);
    }
    
    private NullGameCanvas()
    {
        super.setWait(1200);
    }

    public synchronized boolean isGameOver()
    {
        return false;
    }
    
    private boolean running = true;
    public void setRunning(boolean running)
    {
        this.running = running;
    }

    public synchronized boolean isRunning()
    {
        return running;
    }    
    public synchronized void pause()
    {
        this.setPaused(true);
    }

    public synchronized void unPause()
    {
        this.setPaused(false);
    }

    public void initCommands(final CommandListener cmdListener)
    {
        this.removeAllCommands();
        
        // this.addCommand(GameCommands.QUIT_COMMAND);
        // this.addCommand(GameCommands.RESTART_COMMAND);

        this.setCommandListener(cmdListener);
    }

    public void buildGame(final boolean isProgress) throws Exception
    {
        //this.getLayerManager().cleanup();

        //this.getLayerManager().append(new PlayerGameInputGameLayer());
    }

    public void draw(final Graphics graphics)
    {
        //this.clear(graphics);

        //this.paintableInterface.paint(graphics);
        
    }

    public void run()
    {
        try
        {
            logUtil.put(commonStrings.START_RUNNABLE, this, commonStrings.RUN);

            //this.setRunning(false);

            logUtil.put(commonStrings.END_RUNNABLE, this, commonStrings.RUN);
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
        }
    }
    
    public static final int TYPE = 1;
    public int getType() {
        return TYPE;
    }
    
}