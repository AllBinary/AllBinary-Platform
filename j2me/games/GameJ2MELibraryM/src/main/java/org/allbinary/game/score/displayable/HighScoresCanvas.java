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
import org.allbinary.AvianUtil;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.GameInfo;
import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.displayable.canvas.GameCommandCanvas;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.paint.ColorFillBasePaintable;
import org.allbinary.game.paint.ColorFillPaintableFactory;
import org.allbinary.game.score.HighScoreCommandsFactory;
import org.allbinary.game.score.HighScores;
import org.allbinary.game.score.HighScoresCanvasInputProcessor;
import org.allbinary.game.score.HighScoresCanvasInputProcessorFactoryInterface;
import org.allbinary.game.score.HighScoresCanvasNoInputProcessorFactory;
import org.allbinary.game.score.HighScoresFactoryInterface;
import org.allbinary.game.score.HighScoresHelperBase;
import org.allbinary.game.score.HighScoresPaintable;
import org.allbinary.game.score.HighScoresResultsListener;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.graphics.paint.SimpleTextPaintable;
import org.allbinary.logic.string.StringMaker;

public class HighScoresCanvas extends GameCommandCanvas
        implements HighScoresResultsListener
{
    private Paintable paintable;

    private final SimpleTextPaintable waitPaintable = new SimpleTextPaintable(
            commonStrings.PLEASE_WAIT_FOR_SERVER, 
            BasicColorFactory.getInstance().WHITE);

    private final HighScoresPaintable highScoresPaintable;
    private final HighScoresFactoryInterface highScoresFactoryInterface;

    protected ColorFillBasePaintable colorFillPaintable;

    private final HighScoresHelperBase highScoresHelper = new HighScoresHelperBase();

    private final GameInfo gameInfo;

    private final HighScoresCanvasInputProcessor highScoresCanvasInputProcessor;

    private Command currentCommand = HighScoreCommandsFactory.getInstance().HIGH_SCORE_COMMANDS[0];

    public HighScoresCanvas(final CommandListener commandListener,
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

        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.CONSTRUCTOR));

        this.highScoresPaintable = paintable;

        this.highScoresFactoryInterface = highScoresFactoryInterface;

        this.highScoresCanvasInputProcessor = highScoresCanvasInputProcessorFactoryInterface
                .getInstance(this);

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
                allBinaryGameLayerManager.getBackgroundBasicColor(), false);

        if(!AvianUtil.isAvian()) {        
            this.executeUpdate();
        } else {
            this.setPaintable(this.waitPaintable);
        }
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
    
    private int paintIndex = 0;
    public void paint(Graphics graphics)
    {
        this.colorFillPaintable.paint(graphics);

        this.paintable.paint(graphics);

        if(this.waitPaintable != this.paintable)
        {
            this.highScoresCanvasInputProcessor.paint(graphics);
        }

        super.paint(graphics);

        //TWB - This is a temp hack until I can find out why Threads are blocking the UI for Native builds.
        if(AvianUtil.isAvian()) {
            if (paintIndex < 3) {
                if (paintIndex == 2) {
                    this.executeUpdate();
                }
                paintIndex++;
                this.repaint();
            }
        }
        
    }

    public void executeUpdate()
    {
        try
        {
            this.setPaintable(this.waitPaintable);
            
            this.highScoresFactoryInterface.fetchHighScores(this.getGameInfo(), this);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.UPDATE, e));
        }
    }

    private HighScoresPaintable getHighScoresPaintable()
    {
        return highScoresPaintable;
    }

    public void setHighScoresArray(final HighScores[] highScoresArray)
    {
        try {
            if (highScoresArray != null) {
                LogUtil.put(LogFactory.getInstance(new StringMaker().append(commonStrings.START).append(highScoresArray.length).toString(), this, "setHighScoresArray"));
            } else {
                LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "setHighScoresArray"));
            }

            this.highScoresHelper.setHighScoresArray(highScoresArray);

            this.updateCommand(this.currentCommand);
            this.setPaintable(this.getHighScoresPaintable());
            
        } catch(Exception e) {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.UPDATE, e));
        }
    }

    public void updateCommand(Command command) throws Exception
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(commonStrings.START).append(command).toString(), this, commonStrings.UPDATE));

        final GameCommandsFactory gameCommandsFactory = 
            GameCommandsFactory.getInstance();
        
        final HighScoreCommandsFactory highScoreCommandsFactory = 
            HighScoreCommandsFactory.getInstance();
        
        if (highScoreCommandsFactory.isHighScoreCommand(command))
        {
            int index = highScoreCommandsFactory.getIndex(command);
            
            //LogUtil.put(LogFactory.getInstance(commonStrings.START).append(index, this, commonStrings.UPDATE));
            
            int nextIndex = index + 1;

            if(nextIndex >= highScoreCommandsFactory.HIGH_SCORE_COMMANDS.length)
            {
                nextIndex = 0;
            }
            
            this.getHighScoresPaintable().setHighScores(
                    this.highScoresHelper.getHighScoresArray()[index]);

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

}
