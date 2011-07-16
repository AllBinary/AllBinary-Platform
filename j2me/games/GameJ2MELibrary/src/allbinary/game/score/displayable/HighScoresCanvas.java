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
package allbinary.game.score.displayable;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.GameInfo;
import allbinary.game.commands.GameCommandsFactory;
import allbinary.game.displayable.canvas.GameCommandCanvas;
import allbinary.game.layer.AllBinaryGameLayerManager;
import allbinary.game.paint.ColorFillPaintable;
import allbinary.game.paint.ColorFillPaintableFactory;
import allbinary.game.score.HighScoreCommandsFactory;
import allbinary.game.score.HighScores;
import allbinary.game.score.HighScoresCanvasInputProcessor;
import allbinary.game.score.HighScoresCanvasInputProcessorFactoryInterface;
import allbinary.game.score.HighScoresCanvasNoInputProcessorFactory;
import allbinary.game.score.HighScoresCompositeInterface;
import allbinary.game.score.HighScoresFactoryInterface;
import allbinary.game.score.HighScoresPaintable;
import allbinary.game.score.HighScoresUpdateRunnable;
import allbinary.graphics.color.BasicColorFactory;
import allbinary.graphics.paint.Paintable;
import allbinary.graphics.paint.SimpleTextPaintable;
import allbinary.thread.SecondaryThreadPool;

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
            AllBinaryGameLayerManager allBinaryGameLayerManager,
            HighScoresPaintable paintable,
            HighScoresFactoryInterface highScoresFactoryInterface)
            throws Exception
    {
        this(commandListener, allBinaryGameLayerManager,
                allBinaryGameLayerManager.getGameInfo(), paintable,
                highScoresFactoryInterface,
                new HighScoresCanvasNoInputProcessorFactory());
    }

    public HighScoresCanvas(
            CommandListener commandListener,
            AllBinaryGameLayerManager allBinaryGameLayerManager,
            GameInfo gameInfo,
            HighScoresPaintable paintable,
            HighScoresFactoryInterface highScoresFactoryInterface,
            HighScoresCanvasInputProcessorFactoryInterface highScoresCanvasInputProcessorFactoryInterface)
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
       HighScores[] highScoresArray =
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
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().UPDATE));

        GameCommandsFactory gameCommandsFactory = 
            GameCommandsFactory.getInstance();
        
        HighScoreCommandsFactory highScoreCommandsFactory = 
            HighScoreCommandsFactory.getInstance();
        
        if (highScoreCommandsFactory.isHighScoreCommand(command))
        {
            int index = highScoreCommandsFactory.getIndex(command);
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
