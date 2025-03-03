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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.GameInfo;
import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.HTMLFeatureFactory;
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
import org.allbinary.game.score.NoHighScoresFactory;
import org.allbinary.game.score.NullHighScoresSingletonFactory;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.graphics.paint.SimpleTextPaintable;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.thread.SecondaryThreadPool;

public class HighScoresCanvas extends GameCommandCanvas
        implements HighScoresResultsListener
{
    public static final String NAME = "HighScoresCanvas";
    
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
        super(commandListener, HighScoresCanvas.NAME,
                allBinaryGameLayerManager.getBackgroundBasicColor(),
                allBinaryGameLayerManager.getForegroundBasicColor());

        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.CONSTRUCTOR));

        this.highScoresPaintable = paintable;

        this.highScoresFactoryInterface = highScoresFactoryInterface;

        this.highScoresCanvasInputProcessor = 
            highScoresCanvasInputProcessorFactoryInterface.getInstance(this);

        this.gameInfo = gameInfo;

        // DemoGameMidletEventHandler.getInstance().fireEvent(
        // new DemoGameMidletEvent(this, DemoGameMidletStateFactory
        // .getInstance().START_INPUT_MAPPING));

        this.waitPaintable.setBasicColor(allBinaryGameLayerManager.getForegroundBasicColor());

        this.getHighScoresPaintable().setBasicColor(
                allBinaryGameLayerManager.getForegroundBasicColor());
        this.colorFillPaintable = 
            ColorFillPaintableFactory.getInstance(
                allBinaryGameLayerManager.getBackgroundBasicColor(), false);

        if(this.highScoresHelper.getHighScoresArray() == NoHighScoresFactory.getInstance().NO_HIGH_SCORES) {
            this.setPaintable(this.waitPaintable);
        } else {
            LogUtil.put(LogFactory.getInstance("Show HighScores that are already loaded", this, commonStrings.CONSTRUCTOR));
            this.updateCommand(this.currentCommand);
            this.setPaintable(this.getHighScoresPaintable());
        }

        final Features features = Features.getInstance();
        final boolean isHTML = features.isDefault(HTMLFeatureFactory.getInstance().HTML);
        
        SecondaryThreadPool.getInstance().runTask(new Runnable() {
            public void run() {
                
                try {
                    if (!isHTML) {
                        while (!hasPainted) {
                        }
                        hasPainted = false;
                    }
                    final StringMaker stringMaker = new StringMaker();
                    LogUtil.put(LogFactory.getInstance(stringMaker.append("HighScoresCanvas - Request repaint to be sure: ").append(System.currentTimeMillis()).toString(), this, commonStrings.RUN));
                    repaintBehavior.onChangeRepaint(HighScoresCanvas.this);
                    if (!isHTML) {
                        while (!hasPainted) {
                        }
                    }
                    stringMaker.delete(0, stringMaker.length());
                    LogUtil.put(LogFactory.getInstance(stringMaker.append("HighScoresCanvas - Now that the canvas has completed repaint go ahead and fetch the scores: ").append(System.currentTimeMillis()).toString(), this, commonStrings.RUN));
                    executeUpdate();
                } catch (Exception e) {
                    LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().RUN, e));
                }
                
            }
        });
        
        //LogUtil.put(LogFactory.getInstance(commonStrings.END, this, commonStrings.CONSTRUCTOR));
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
    
    boolean hasPainted = false;
    public void paint(Graphics graphics)
    {
        this.colorFillPaintable.paint(graphics);

        this.paintable.paint(graphics);

        if(this.waitPaintable != this.paintable)
        {
            this.highScoresCanvasInputProcessor.paint(graphics);
        }

        super.paint(graphics);
        
        hasPainted = true;
    }

    public void executeUpdate()
    {
        try
        {
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
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(commonStrings.START).append(this.stringUtil.toString(command)).toString(), this, commonStrings.UPDATE));

        final GameCommandsFactory gameCommandsFactory = 
            GameCommandsFactory.getInstance();
        
        final HighScoreCommandsFactory highScoreCommandsFactory = 
            HighScoreCommandsFactory.getInstance();
        
        if (highScoreCommandsFactory.isHighScoreCommand(command))
        {
            final int index = highScoreCommandsFactory.getIndex(command);
            
            //LogUtil.put(LogFactory.getInstance(commonStrings.START).append(index, this, commonStrings.UPDATE));
            
            int nextIndex = index + 1;

            final HighScores[] highScoresArray = this.highScoresHelper.getHighScoresArray();
            
            if(nextIndex >= highScoresArray.length)
            {
                nextIndex = 0;
            }
            
            if(highScoresArray.length > 0) {
                this.getHighScoresPaintable().setHighScores(highScoresArray[index]);
            } else {
                this.getHighScoresPaintable().setHighScores(NullHighScoresSingletonFactory.getInstance());
            }

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

        this.repaintBehavior.onChangeRepaint(this);
    }

    public GameInfo getGameInfo()
    {
        return gameInfo;
    }

    private void setPaintable(Paintable paintable)
    {
        this.paintable = paintable;
        this.repaintBehavior.onChangeRepaint(this);
    }

}
