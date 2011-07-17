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
package org.allbinary.game.testgamedemo.canvas;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;

import org.allbinary.game.testgamedemo.init.TestGameDemoStaticInitializerFactory;
import org.allbinary.game.testgamedemo.layer.TestGameDemoLayerManager;
import org.allbinary.game.testgamedemo.level.TestGameDemoLevelBuilder;
import org.allbinary.input.accelerometer.AccelerometerSensorFactory;
import org.allbinary.input.gyro.GyroSensorFactory;
import org.allbinary.media.audio.TestGameDemoSoundsFactoryFactory;
import org.allbinary.media.audio.TestSound;
import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.ai.OptimizedArtificialIntelligenceLayerProcessorForCollidableLayer;
import allbinary.game.GameInfo;
import allbinary.game.GameType;
import allbinary.game.IntermissionFactory;
import allbinary.game.collision.OptimizedAllBinaryCollisionLayerProcessorForCollidableLayer;
import allbinary.game.configuration.GameSpeed;
import allbinary.game.configuration.event.ChangedGameFeatureListener;
import allbinary.game.configuration.feature.Features;
import allbinary.game.configuration.feature.GameFeatureFactory;
import allbinary.game.configuration.feature.TouchFeatureFactory;
import allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import allbinary.game.displayable.canvas.GamePerformanceInitUpdatePaintable;
import allbinary.game.displayable.canvas.StartIntermissionPaintable;
import allbinary.game.input.OptimizedGameInputLayerProcessorForCollidableLayer;
import allbinary.game.layer.AllBinaryGameLayerManager;
import allbinary.game.layer.PlayerGameInputGameLayer;
import allbinary.game.layer.identification.GroupLayerManagerListener;
import allbinary.game.score.BasicHighScoresFactory;
import allbinary.game.state.GameState;
import allbinary.game.tick.OptimizedTickableLayerProcessor;
import allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.displayable.DisplayInfoSingleton;
import allbinary.graphics.displayable.command.MyCommandsFactory;
import allbinary.input.motion.button.BaseTouchInput;
import allbinary.input.motion.button.TestGameDemoNeededTouchButtonsBuilder;
import allbinary.input.motion.button.TestGameDemoTouchButtonsBuilder;
import allbinary.media.AllBinaryVibration;
import allbinary.media.audio.AllBinaryMediaManager;
import allbinary.media.audio.PrimaryPlayerQueueFactory;
import allbinary.media.audio.SecondaryPlayerQueueFactory;
import allbinary.time.TimeDelayHelper;

public class TestGameDemoGameCanvas extends AllBinaryGameCanvas
{
    private final int WAIT = GameSpeed.getInstance().getDelay();

    private final int portion = 4;
    
    public TestGameDemoGameCanvas(CommandListener commandListener,
            AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception
    {
        super(commandListener, allBinaryGameLayerManager, 
                new BasicHighScoresFactory(TestGameDemoSoftwareInfo.getInstance()),
                new TestGameDemoStaticInitializerFactory(),
           //new BasicBuildGameInitializerFactory(),
           false);
    }

    public TestGameDemoGameCanvas(AllBinaryGameLayerManager allBinaryGameLayerManager)
    throws Exception
    {
        this(null, allBinaryGameLayerManager);
    }
    
    protected void initSpecialPaint()
    {
        super.initSpecialPaint();

        this.setStartIntermissionPaintable(new StartIntermissionPaintable(
                this, new String[] {StringUtil.getInstance()}, new int[] {0}, BasicColor.RED));
    }

    public void mediaInit() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "mediaInit"));
        AllBinaryMediaManager.init(TestGameDemoSoundsFactoryFactory.getInstance());
    }

    //Don't Auto Hide instead update the list
    protected  void updateTouch()
    throws Exception
    {
        GameInfo gameInfo = this.getLayerManager().getGameInfo();
        
        if(gameInfo.getGameType() != GameType.BOT)
        {
            BaseTouchInput nextTouchInputFactory =
                TestGameDemoTouchButtonsBuilder.getInstance(
                        this.getSensorGameUpdateProcessor());

            if(Features.getInstance().isFeature(
                    TouchFeatureFactory.getInstance().AUTO_HIDE_SHOW_SCREEN_BUTTONS))
            {
                if(gameInfo.getCurrentLevel() - getStartLevel() >= 1)
                {
                    nextTouchInputFactory = 
                        TestGameDemoNeededTouchButtonsBuilder.getInstance(
                                this.getSensorGameUpdateProcessor());
                }
            }
            this.updateCurrentTouchInputFactory(nextTouchInputFactory);
        }
    }

    protected synchronized void initConfigurable()
    {
        try
        {
            if (ChangedGameFeatureListener.getInstance().isChanged())
            {
                super.initConfigurable();

                ProgressCanvasFactory.getInstance().addPortion(portion, "Group Manager");
                GroupLayerManagerListener.getInstance().init(3);

                AllBinaryVibration.init();

                //super.initConfigurable(portion);

                ChangedGameFeatureListener.getInstance().setChanged(false);

                if (!this.isRunning())
                {
                    return;
                }
            } else
            {
                ProgressCanvasFactory.getInstance().addPortion(4, "Skipping Configurable");
            }
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "initConfigurable", e));
        }
    }

    protected void threadInit() throws Exception
    {
        try
        {
            final int portion = 60;
            super.init();

            if (!this.isRunning())
            {
                return;
            }

            if (!this.isInitialized())
            {
                if (!this.isRunning())
                {
                    return;
                }

                ProgressCanvas progressCanvas = 
                    ProgressCanvasFactory.getInstance();
                
                progressCanvas.addPortion(portion, "Main Processors");

                this.setWait(WAIT);
                this.loadState();

                BasicArrayList list = new BasicArrayList();

                Features features = Features.getInstance();
                
                GameFeatureFactory gameFeatureFactory = GameFeatureFactory.getInstance();
                
                if (features.isFeature(gameFeatureFactory.ARTIFICIAL_INTELLEGENCE_PROCESSOR))
                {
                    list.add(new OptimizedArtificialIntelligenceLayerProcessorForCollidableLayer());
                }

                if (features.isFeature(gameFeatureFactory.GAME_INPUT_LAYER_PROCESSOR))
                {
                    list.add(new OptimizedGameInputLayerProcessorForCollidableLayer());
                }

                if (features.isFeature(gameFeatureFactory.COLLIDABLE_INTERFACE_LAYER_PROCESSOR))
                {
                    list.add(new OptimizedAllBinaryCollisionLayerProcessorForCollidableLayer());
                }

                if (features.isFeature(gameFeatureFactory.TICKABLE_LAYER_PROCESSOR))
                {
                    list.add(new OptimizedTickableLayerProcessor());
                }

                this.getLayerManager().setLayerProcessorList(list);

                progressCanvas.addPortion(portion, "Initializing Game");
            }

            this.buildGame(false);

        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "_init", e));
        }
    }

    public void buildGame(boolean isProgress) throws Exception
    {
        this.loadResources(
                this.getLayerManager().getGameInfo().getCurrentLevel());
        
        ProgressCanvas progressCanvas = 
            ProgressCanvasFactory.getInstance();
        
        int portion = 30;
        if (isProgress && this.isMainCanvas())
        {            
            progressCanvas.start();

            this.getCustomCommandListener().commandAction(
                    MyCommandsFactory.getInstance().SET_DISPLAYABLE,
                    (Displayable) progressCanvas);
            //progressCanvas.waitUntilDisplayed();
            portion = 4;
        }

        progressCanvas.addPortion(portion, "Cleaning Up");

        PrimaryPlayerQueueFactory.getInstance().clear();
        SecondaryPlayerQueueFactory.getInstance().clear();

        this.getLayerManager().cleanup();

        if (!this.isRunning())
        {
            return;
        }

        progressCanvas.addPortion(portion, "Building Game Level");

        //this.getLayerManager().append(new PlayerGameInputGameLayer());

        //DestroyedEventHandler.getInstance().removeAllListeners();

        //Some games update intermission here

        progressCanvas.addPortion(portion, "Building Game Level");

        new TestGameDemoLevelBuilder((TestGameDemoLayerManager) this.getLayerManager()).build(
                this.getWidth(), this.getHeight());

        progressCanvas.addPortion(portion, "Set Background");

        //Some games update backgrounds here

        //this.playerLayer = ((TestGameDemoLayerManager) this.getLayerManager()).getPlayerLayer();

        //DestroyedEventHandler.getInstance().addListener((EventListenerInterface) playerLayer);

        if (!this.isRunning())
        {
            return;
        }

        this.getLayerManager().append(new PlayerGameInputGameLayer());

        progressCanvas.addPortion(portion, "Ending Custom Build");

        if (this.getLayerManager().getGameInfo().getGameType() != GameType.BOT)
        {
            //PrimaryPlayerQueueFactory.getInstance().add(
                    //GameSounds.getBegin());
        }

        super.buildGame(portion);

        this.getStartIntermissionInterface().setEnabled(true);
        this.getEndLevelIntermissionInterface().setEnabled(false);
        
        // A canvas not in GameState.PLAYING_GAME_STATE will not appear in
        // democanvas
        this.setGameState(GameState.PLAYING_GAME_STATE);
    }

    public void setGameState(GameState gameState) throws Exception
    {
        super.setGameState(gameState);

        IntermissionFactory intermissionFactory = IntermissionFactory.getInstance();
        
        if (this.getGameState() == GameState.PLAYING_GAME_STATE)
        {
            this.setMainStateProcessor(this.getProcessGameProcessor());
        }
        else if (this.getGameState() == intermissionFactory.WAIT_LEVEL_INTERMISSION_GAME_STATE
                || this.getGameState() == intermissionFactory.SHOW_RESULTS_LEVEL_INTERMISSION_GAME_STATE
                || this.getGameState() == intermissionFactory.SHOW_HIGH_SCORE_LEVEL_INTERMISSION_GAME_STATE)
        {
            //GameKeyEventHandler.getInstance().addListener(this.getIntermissionPlayerGameInput());
            
            //this.setMainStateProcessor(this.processEndIntermissionProcessor);
        }
        else
        {
            // Game plays in non intermission and after death
            this.setMainStateProcessor(this.getProcessGameProcessor());
        }
    }
    
    private final GamePerformanceInitUpdatePaintable gamePerformanceInitUpdatePaintable = 
        new GamePerformanceInitUpdatePaintable();

    private final int halfHeight = DisplayInfoSingleton.getInstance().getLastHalfHeight();

    //private String soundQueue = PrimaryPlayerQueueFactory.getInstance().toString();
    
    public void draw(Graphics graphics)
    {
        this.clear(graphics);

        this.getBasicColorUtil().setBasicColor(
                graphics, this.getLayerManager().getForegroundBasicColor());

        //graphics.drawString(TEXT, 0, halfHeight, 0);

        /*
        graphics.drawString(soundQueue, 0, halfHeight + 15, 0);
        */

        this.gamePerformanceInitUpdatePaintable.paint(graphics);
        
        graphics.drawString(GyroSensorFactory.getInstance().toString(), 
                0, halfHeight + 30 + 60, 0);
        graphics.drawString(AccelerometerSensorFactory.getInstance().toString(), 
                0, halfHeight + 30 + 75, 0);
        
        this.getTouchPaintable().paint(graphics);
    }

    private TimeDelayHelper playerTimeDelayHelper = new TimeDelayHelper(2000);
            //890);

    protected void processGame() throws Exception
    {
        super.processGame();

        if (playerTimeDelayHelper.isTime())
        {
            if(Features.getInstance().isFeature(
                    GameFeatureFactory.getInstance().SOUND))
            {
                PrimaryPlayerQueueFactory.getInstance().add(
                        TestSound.getInstance());
            }
        }
        
        /*
        if (playerTimeDelayHelper.isTime())
        {
            if (!PrimaryPlayerQueueFactory.getInstance().process())
            {
                if (SecondaryPlayerQueueFactory.getInstance().process())
                {
                    playerTimeDelayHelper.setStartTime();
                }
            } else
            {
                playerTimeDelayHelper.setStartTime();
            }
        }
        */
        
        if (!PrimaryPlayerQueueFactory.getInstance().process())
        {
            SecondaryPlayerQueueFactory.getInstance().process();
        }
        
        //soundQueue = PrimaryPlayerQueueFactory.getInstance().toString();
        
        this.gamePerformanceInitUpdatePaintable.update();
    }
}