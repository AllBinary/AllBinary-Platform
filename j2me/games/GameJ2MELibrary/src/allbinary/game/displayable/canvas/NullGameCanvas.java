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
package allbinary.game.displayable.canvas;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.layer.AllBinaryGameLayerManager;

public class NullGameCanvas extends AllBinaryGameCanvas
{
    private static final NullGameCanvas SINGLETON = new NullGameCanvas();
    
    public static NullGameCanvas getInstance()
    {
        return SINGLETON;
    }

    public static NullGameCanvas getInstance(
            AllBinaryGameLayerManager gameLayerManager)
    {
        NullGameCanvas nullGameCanvas = new NullGameCanvas(gameLayerManager);
        
        nullGameCanvas.setInitialized(true);
        nullGameCanvas.setTitle(NO_GAME);

        return nullGameCanvas;
    }
    
    public static final String NO_GAME = "No Background Game";
    
    protected NullGameCanvas(AllBinaryGameLayerManager gameLayerManager)
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

    public void initCommands(CommandListener cmdListener)
    {
        this.removeAllCommands();
        
        // this.addCommand(GameCommands.QUIT_COMMAND);
        // this.addCommand(GameCommands.RESTART_COMMAND);

        this.setCommandListener(cmdListener);
    }

    public void buildGame(boolean isProgress) throws Exception
    {
        //this.getLayerManager().cleanup();

        //this.getLayerManager().append(new PlayerGameInputGameLayer());
    }

    public void draw(Graphics graphics)
    {
        //this.clear(graphics);

        //this.paintableInterface.paint(graphics);
        
    }

    public void run()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_RUNNABLE, this, CommonStrings.getInstance().RUN));

            //this.setRunning(false);

            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().END_RUNNABLE, this, CommonStrings.getInstance().RUN));
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().RUN, e));
        }
    }
}