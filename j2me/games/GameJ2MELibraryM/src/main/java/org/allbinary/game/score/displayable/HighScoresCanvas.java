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
package org.allbinary.game.score.displayable;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.GameInfo;
import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.displayable.canvas.GameCommandCanvas;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.paint.ColorFillPaintable;
import org.allbinary.game.paint.ColorFillPaintableFactory;
import org.allbinary.game.score.HighScoreCommandsFactory;
import org.allbinary.game.score.HighScores;
import org.allbinary.game.score.HighScoresCanvasInputProcessor;
import org.allbinary.game.score.HighScoresCanvasInputProcessorFactoryInterface;
import org.allbinary.game.score.HighScoresCanvasNoInputProcessorFactory;
import org.allbinary.game.score.HighScoresCompositeInterface;
import org.allbinary.game.score.HighScoresFactoryInterface;
import org.allbinary.game.score.HighScoresPaintable;
import org.allbinary.game.score.HighScoresUpdateRunnable;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.graphics.paint.SimpleTextPaintable;
import org.allbinary.logic.basic.string.StringMaker;
import org.allbinary.thread.SecondaryThreadPool;

public class HighScoresCanvas extends GameCommandCanvas
        implements HighScoresCompositeInterface
{
    private Paintable paintable;

    private final SimpleTextPaintable waitPaintable = new SimpleTextPaintable(
            CommonStrings.getInstance().PLEASE_WAIT_FOR_SERVER, 
            BasicColorFactory.getInstance().WHITE);

    private final HighScoresPaintable highScoresPaintable;
    private final HighScoresFactoryInterface highScoresFactoryInterface;

    protected ColorFillPaintable colorFillPaintable;

    private HighScores[] highScoresArray;

    private final GameInfo gameInfo;

    private final HighScoresCanvasInputProcessor highScoresCanvasInputProcessor;

    private Command currentCommand = HighScoreCommandsFactory.getInstance().HIGH_SCORE_COMMANDS[0];

    private final HighScoresUpdateRunnable highScoresUpdateRunnable;

    public HighScoresCanvas(CommandListener commandListener,
            final AllBinaryGameLayerManager allBinaryGameLayerManager,
            final HighScoresPaintable paintable,
            final HighScoresFactoryInterface highScoresFactoryInterface)
            throws Exception
    {
        this(commandListener, allBinaryGameLayerManager,
                allBinaryGameLayerManager.getGameInfo(), paintable,
                highScoresFactoryInterface,
                new HighScoresCanvasNoInputProcessorFactory());
    }

    public HighScoresCanvas(
            final CommandListener commandListener,
            final AllBinaryGameLayerManager allBinaryGameLayerManager,
            final GameInfo gameInfo,
            final HighScoresPaintable paintable,
            final HighScoresFactoryInterface highScoresFactoryInterface,
            final HighScoresCanvasInputProcessorFactoryInterface highScoresCanvasInputProcessorFactoryInterface)
            throws Exception
    {
        super(commandListener,
                allBinaryGameLayerManager.getBackgroundBasicColor(),
                allBinaryGameLayerManager.getForegroundBasicColor());

        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().CONSTRUCTOR));

        this.highScoresPaintable = paintable;

        this.highScoresFactoryInterface = highScoresFactoryInterface;

        this.highScoresCanvasInputProcessor = highScoresCanvasInputProcessorFactoryInterface
                .getInstance(this);

        this.highScoresUpdateRunnable = new HighScoresUpdateRunnable(this);

        this.gameInfo = gameInfo;

        // DemoGameMidletEventHandler.getInstance().fireEvent(
        // new DemoGameMidletEvent(this, DemoGameMidletStateFactory
        // .getInstance().START_INPUT_MAPPING));

        this.waitPaintable.setBasicColor(allBinaryGameLayerManager
                .getForegroundBasicColor());

        this.getHighScoresPaintable().setBasicColor(
                allBinaryGameLayerManager.getForegroundBasicColor());
        this.colorFillPaintable = 
            ColorFillPaintableFactory.getInstance(
                allBinaryGameLayerManager.getBackgroundBasicColor());

        this.executeUpdate();
    }

    public void initCommands(CommandListener cmdListener)
    {
        this.removeAllCommands();
        this.addCommand(GameCommandsFactory.getInstance().CLOSE_AND_SHOW_GAME_CANVAS);
        // this.addCommand(HighScoresCanvas.WORLD);

        this.setCommandListener(cmdListener);
    }

    public void open()
    {
        super.open();
        
        this.highScoresCanvasInputProcessor.open();
    }
    
    public void close() throws Exception
    {
        super.close();
        
        this.highScoresCanvasInputProcessor.close();
    } 
    
    public void paint(Graphics graphics)
    {
        this.colorFillPaintable.paint(graphics);

        this.getPaintable().paint(graphics);

        if(this.waitPaintable != this.getPaintable())
        {
            this.highScoresCanvasInputProcessor.paint(graphics);
        }

        super.paint(graphics);
    }

    public void executeUpdate()
    {
        try
        {
            this.setPaintable(this.waitPaintable);
            
            SecondaryThreadPool.getInstance().runTask(this.highScoresUpdateRunnable);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().UPDATE, e));
        }
    }

    public void setHighScores() throws Exception
    {
       final HighScores[] highScoresArray =
               this.highScoresFactoryInterface.createHighScores(
               this.getGameInfo()
               );

       this.setHighScoresArray(highScoresArray);
    }

    private HighScoresPaintable getHighScoresPaintable()
    {
        return highScoresPaintable;
    }

    private void setHighScoresArray(HighScores[] highScoresArray)
            throws Exception
    {
//        if(highScoresArray != null) {
//            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START).append(highScoresArray.length, this, "setHighScoresArray"));
//        } else {
//            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "setHighScoresArray"));
//        }
        
        this.highScoresArray = highScoresArray;

        this.updateCommand(this.currentCommand);
        this.setPaintable(this.getHighScoresPaintable());
    }

    private HighScores[] getHighScoresArray()
    {
        return highScoresArray;
    }

    public void updateCommand(Command command) throws Exception
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(CommonStrings.getInstance().START).append(command).toString(), this, CommonStrings.getInstance().UPDATE));

        final GameCommandsFactory gameCommandsFactory = 
            GameCommandsFactory.getInstance();
        
        final HighScoreCommandsFactory highScoreCommandsFactory = 
            HighScoreCommandsFactory.getInstance();
        
        if (highScoreCommandsFactory.isHighScoreCommand(command))
        {
            int index = highScoreCommandsFactory.getIndex(command);
            
            //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START).append(index, this, CommonStrings.getInstance().UPDATE));
            
            int nextIndex = index + 1;

            if(nextIndex >= highScoreCommandsFactory.HIGH_SCORE_COMMANDS.length)
            {
                nextIndex = 0;
            }
            
            this.getHighScoresPaintable().setHighScores(
                    this.getHighScoresArray()[index]);

            if(index != nextIndex)
            {
                this.removeAllCommands();
                this.addCommand(gameCommandsFactory.CLOSE_AND_SHOW_GAME_CANVAS);
                this.addCommand(highScoreCommandsFactory.HIGH_SCORE_COMMANDS[nextIndex]);
            }
        }

        this.currentCommand = command;

        this.close();
        super.initMenu();
        this.open();

        this.repaint();
    }

    public GameInfo getGameInfo()
    {
        return gameInfo;
    }

    private void setPaintable(Paintable paintable)
    {
        this.paintable = paintable;
        this.repaint();
    }

    private Paintable getPaintable()
    {
        return paintable;
    }
}
