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
package org.allbinary.game.midlet;

/**
 *Detailed description This class is the main MIDlet for all GameMidlets
 *it sets the main canvas and starts a thread for the specified canvas.
 *
 *@author Travis Berthelot
 *Date: 11/19/02
 *
 */

import java.util.Enumeration;
import java.util.Hashtable;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.NullCanvas;

import org.allbinary.J2MEUtil;
import org.allbinary.TsUtil;
import org.allbinary.business.advertisement.GameAdStateFactory;
import org.allbinary.canvas.FullScreenUtil;
import org.allbinary.canvas.GameStatisticsFactory;
import org.allbinary.debug.DebugFactory;
import org.allbinary.debug.DebugInterface;
import org.allbinary.game.GameAdState;
import org.allbinary.game.GameInfo;
import org.allbinary.game.GameMode;
import org.allbinary.game.GameStrings;
import org.allbinary.game.GameTypeFactory;
import org.allbinary.game.PlayerTypesFactory;
import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.configuration.GameOptionsForm;
import org.allbinary.game.configuration.GameOptionsFormFactory;
import org.allbinary.game.configuration.InGameFeatures;
import org.allbinary.game.configuration.InGameOptionsForm;
import org.allbinary.game.configuration.InGameOptionsFormFactory;
import org.allbinary.game.configuration.LoadGameForm;
import org.allbinary.game.configuration.event.ChangedGameFeatureListener;
import org.allbinary.game.configuration.event.GameFeatureEventHandler;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureFormUtil;
import org.allbinary.game.configuration.feature.MainFeatureFactory;
import org.allbinary.game.configuration.persistance.GamePersistanceSingleton;
import org.allbinary.game.configuration.persistance.KeyValuePersistance;
import org.allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import org.allbinary.game.displayable.canvas.BasicPaintablesCanvas;
import org.allbinary.game.displayable.canvas.DemoCanvas;
import org.allbinary.game.displayable.canvas.GameCanvasRunnableInterface;
import org.allbinary.game.displayable.canvas.GameEventHandlerUtil;
import org.allbinary.game.displayable.canvas.GameInputMappingCanvas;
import org.allbinary.game.displayable.canvas.GameInputMappingInstructionsCanvas;
import org.allbinary.game.displayable.canvas.MenuListener;
import org.allbinary.game.displayable.canvas.NullGameCanvasRunnable;
import org.allbinary.game.input.TextNotificationUtil;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.hud.event.GameNotificationEventHandler;
import org.allbinary.game.paint.help.HelpPaintable;
import org.allbinary.game.score.HighScoreCommands;
import org.allbinary.game.score.HighScoreCommandsFactory;
import org.allbinary.game.score.displayable.HighScoreTextBox;
import org.allbinary.game.score.displayable.HighScoreUtil;
import org.allbinary.game.score.displayable.HighScoresCanvas;
import org.allbinary.game.state.GameStateFactory;
import org.allbinary.graphics.ResizableListenerHandler;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.ColorChangeEventHandler;
import org.allbinary.graphics.displayable.CanvasStrings;
import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.graphics.displayable.command.MyCommandsFactory;
import org.allbinary.graphics.displayable.screen.AboutCommandProcessor;
import org.allbinary.graphics.displayable.screen.AboutPaintableFactory;
import org.allbinary.graphics.displayable.screen.CommandForm;
import org.allbinary.graphics.displayable.screen.WebCommandProcessor;
import org.allbinary.input.AllBinarySensorManager;
import org.allbinary.input.event.VirtualKeyboardEventHandler;
import org.allbinary.input.motion.gesture.observer.BasicMotionGesturesHandler;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.ClientInformationFactory;
import org.allbinary.logic.system.security.licensing.InApplicationPurchaseFactory;
import org.allbinary.logic.system.security.licensing.LockableFeature;
import org.allbinary.logic.system.security.licensing.LockableFeatureFactory;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.media.audio.AllBinaryMediaManager;
import org.allbinary.midlet.MidletStrings;
import org.allbinary.midlet.ProgressMidlet;
import org.allbinary.thread.NullThread;
import org.allbinary.thread.ThreadFactoryUtil;
import org.allbinary.thread.ThreadUtil;
import org.allbinary.time.TimeDelayHelper;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.EnumerationUtil;

public class GameMidlet extends ProgressMidlet
    implements CommandListener //, GameMidletEventListener
{
    private final EnumerationUtil enumerationUtil = EnumerationUtil.getInstance();
    
    protected final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();
    protected final Features features = Features.getInstance();
    protected final MidletStrings midletStrings = MidletStrings.getInstance();
    protected final MyCommandsFactory myCommandsFactory = MyCommandsFactory.getInstance();
    protected final GameStrings gameStrings = GameStrings.getInstance();
    protected final GameAdStateFactory gameAdStateFactory = GameAdStateFactory.getInstance();
    protected final GameStateFactory gameStateFactory = GameStateFactory.getInstance();
    protected final TsUtil tsUtil = TsUtil.getInstance();

    private final String DISPLAYABLE = " Displayable: ";
    private final String COMMAND_NAME = "command Name/Label: ";
    private final String NO_COMMAND = "No Command";
    private final String NO_DISPLAYABLE = "No Displayable";
    private final String COMMAND_ACTION = new StringMaker().append("GameMidlet::").append(this.midletStrings.COMMAND_ACTION).toString();
    private final String PAUSE_APP_BACKGROUND = "pauseAppBackground";
    private final String UN_PAUSE_APP_BACKGROUND = "unPauseAppBackground";

    private final AboutCommandProcessor aboutCommandProcessor = AboutCommandProcessor.getInstance();
    private final WebCommandProcessor webCommandProcessor = WebCommandProcessor.getInstance();
    private final GameMidletStateFactory gameMidletStateFactory = GameMidletStateFactory.getInstance();
    protected final TimeDelayHelper gameStartTimeHelper = new TimeDelayHelper(240);
    private final FullScreenUtil fullScreenUtil = FullScreenUtil.getInstance();

    private final DebugInterface debugInterface;
    
    private GameCanvasRunnableInterface allbinaryGameCanvasRunnableInterface = NullGameCanvasRunnable.NULL_GAME_CANVAS_RUNNABLE;
    private Thread thread = NullThread.NULL_THREAD;

    // Screens/Canvas
    //private CommandForm saveGameForm;
    private CommandForm loadGameForm;
    private boolean isFullScreen;
    private boolean resized;
    
    //private GameOptionsForm gameOptionsForm;

    public GameMidlet(final ClientInformationFactory clientInformationFactory)
    {
        super(clientInformationFactory);
        //this.logUtil.putF("GameMidlet::GameMidlet", this, this.commonStrings.CONSTRUCTOR);
        
        //For BB can be used for J2ME as well
        SmallIntegerSingletonFactory.getInstance().initWithRange(0x291, 6);
        //This can be used for J2ME but not BB
        //SmallIntegerSingletonFactory.getInstance().init(0x101, 6);
        //This must load after SmallIntegerSingletonFactory init for InputFactory
        this.loadGameForm = CommandForm.getNullCommandForm();

        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
        progressCanvas.init(this);

        GameFeatureEventHandler.getInstance().addListenerInterface(
                ChangedGameFeatureListener.getInstance());

        GamePersistanceSingleton.getInstance().clear();

        this.debugInterface = DebugFactory.getInstance();
        
        this.init();        
    }
    
    protected void init()
    {
    }

    protected void setDemo() throws Exception
    {
        throw new Exception(this.commonStrings.NOT_IMPLEMENTED);
    }

    protected void createGame() throws Exception
    {
        throw new Exception(this.commonStrings.NOT_IMPLEMENTED);
    }

    protected void mediaShutdown() throws Exception
    {
        throw new Exception(this.commonStrings.NOT_IMPLEMENTED);
    }

    public void stopAll()
    {
    }

    @Override
    protected void pauseApp()
    {
        this.pauseAppBackground(true);
        
        final GameAdState gameAdState = this.gameAdStateFactory.getCurrentInstance();
        
        gameAdState.getAdvertisements().stopAll();
    }

    protected void pauseAppBackground(final boolean background)
    {
        this.logUtil.putF(this.commonStrings.START, this, this.PAUSE_APP_BACKGROUND);
        //PreLogUtil.put(commonStrings.START, this, PAUSE_APP_BACKGROUND);
        
        if (this.allbinaryGameCanvasRunnableInterface != NullGameCanvasRunnable.NULL_GAME_CANVAS_RUNNABLE)
        {
            this.allbinaryGameCanvasRunnableInterface.pause();
            /*
            if (!this.allbinaryGameCanvasRunnableInterface.isPaused())
            {
                this.allbinaryGameCanvasRunnableInterface.pause();
            }
             */
        }
        else
        {
            this.logUtil.putF("<<<<<< Null", this, this.PAUSE_APP_BACKGROUND);
        }
        
        AllBinarySensorManager.getInstance().shutdown();
    }

    protected void unPauseApp()
    {
        this.unPauseAppBackground(true);
        
        final GameAdState gameAdState = this.gameAdStateFactory.getCurrentInstance();
        
        gameAdState.getAdvertisements().startAll();
    }
    
    protected void unPauseAppBackground(boolean background)
    {
        this.logUtil.putF(this.commonStrings.START, this, this.UN_PAUSE_APP_BACKGROUND);
        //PreLogUtil.put(commonStrings.START, this, UN_PAUSE_APP_BACKGROUND);

        AllBinarySensorManager.getInstance().init();

        final GameCanvasRunnableInterface gameCanvasRunnableInterface =
            this.allbinaryGameCanvasRunnableInterface;

        if (gameCanvasRunnableInterface != NullGameCanvasRunnable.NULL_GAME_CANVAS_RUNNABLE)
        {
            gameCanvasRunnableInterface.unPause();
            /*
            if (gameCanvasRunnableInterface.isPaused())
            {
                gameCanvasRunnableInterface.unPause();
            }
            */
        }
        else
        {
            this.logUtil.putF("<<<<<< Null", this, this.UN_PAUSE_APP_BACKGROUND);
        }
    }
    
    @Override
    protected void destroyAppInRunnable(boolean unconditional, boolean isProgress)
    {
        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();

        if (isProgress)
        {
            progressCanvas.start();

            this.commandAction(this.myCommandsFactory.SET_DISPLAYABLE, progressCanvas);

            // progressCanvas.waitUntilDisplayed();
        }
        
        this.destroyApp(unconditional);

        if (isProgress)
        {
            progressCanvas.end();
        }
    }
    
    @Override
    protected void destroyApp(boolean unconditional)
    {
        final String METHOD_NAME = "GameMidlet::destroyApp";
        try
        {
            PreLogUtil.put(GameStatisticsFactory.getInstance().toString(), this, METHOD_NAME);
            //this.logUtil.putF(this.commonStrings.START, this, METHOD_NAME);

            final GameAdState gameAdState = this.gameAdStateFactory.getCurrentInstance();
            
            gameAdState.getAdvertisements().stopAll();
            
            if (!this.isDestroyed())
            {
                this.stopGameCanvasRunnableInterface();
                this.setGameCanvasRunnableInterface(NullGameCanvasRunnable.NULL_GAME_CANVAS_RUNNABLE);
                //if(!AppletUtil.isAppletLoader(this))
                //{
                this.mediaShutdown();
                //}
            }
            else
            {
                this.logUtil.putF(
                    "Midlet Managment Error: Midlet Should Only Be Destroyed Once", this, METHOD_NAME);
            }

            super.destroyApp(true);

            //this.logUtil.putF(this.commonStrings.END, this, METHOD_NAME);
            PreLogUtil.put(this.commonStrings.END, this, METHOD_NAME);
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, METHOD_NAME, e);
        }
        this.logUtil.putF(this.commonStrings.END, this, METHOD_NAME);
    }
 
    @Override
    protected void startApp()
    {
        try
        {
            final GameAdState gameAdState = this.gameAdStateFactory.getCurrentInstance();
            
            gameAdState.getAdvertisements().startAll();
            
            final String START_APP = "startApp";
            
            this.logUtil.putF(this.commonStrings.START, this, START_APP);
            //PreLogUtil.put(commonStrings.START, this, START_APP);

            final Object gameCanvasRunnableInterface = this.allbinaryGameCanvasRunnableInterface;

            //If the first time/null then start demo or unpausing
            //thus start up only occurs one time ever otherwise this is just unpause
            if (gameCanvasRunnableInterface == NullGameCanvasRunnable.NULL_GAME_CANVAS_RUNNABLE)
            {
            	this.gameMidletStateFactory.setCurrentGameState(this.gameStateFactory.NO_GAME_STATE);
                this.setDemo();
            }
            else
            {
                this.logUtil.putF("GameCanvasRunnableInterface is available", this, START_APP);

                //Starting midlet after a previous start means that we only unpause the
                //GameCanvasRunnableInterface if it is currently displayed
                if (gameCanvasRunnableInterface == this.getCurrentDisplayable())
                {
                    this.unPauseAppBackground(false);
                }
                else
                {
                    this.logUtil.putF("GameCanvasRunnableInterface is not current displayable", this, START_APP);
                }
            }
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, "startApp", e);
            this.destroyApp(false);
            //TWB - Only remove from context when multiple midlets share the same emulator
            this.notifyDestroyed();
        }
    }
    
    @Override
    public synchronized void commandAction(final Command command, final Displayable displayable)
    {
        try
        {
            String displayableAsString = this.NO_DISPLAYABLE;
            if(displayable != null)
            {
                 displayableAsString = displayable.toString();
            }

            String label = this.NO_COMMAND;
            if(command != null)
            {
                label = command.getLabel();
            }

            PreLogUtil.put(new StringMaker().append(this.COMMAND_NAME).append(label).append(this.DISPLAYABLE).append(displayableAsString).toString(), this, this.COMMAND_ACTION);

            final GameCommandsFactory gameCommandsFactory = GameCommandsFactory.getInstance();

            final GameCanvasRunnableInterface allbinaryGameCanvasRunnableInterface = this.allbinaryGameCanvasRunnableInterface;
            final Object gameCanvasRunnableInterface = allbinaryGameCanvasRunnableInterface;
            
            if (command == gameCommandsFactory.SHOW_GAME_CANVAS)
            {
                //TWB - If progress concurrency is a problem then this is probably the cause
                if(this.getDisplay().getCurrent() != gameCanvasRunnableInterface && allbinaryGameCanvasRunnableInterface.getType() != NullGameCanvasRunnable.NULL_GAME_CANVAS_RUNNABLE.getType())
                {
                    this.setDisplay((Displayable) gameCanvasRunnableInterface);
                }

                this.unPauseAppBackground(false);
            }
            else if (command == gameCommandsFactory.CLOSE_AND_SHOW_GAME_CANVAS)
            {
                //this.closeMenuListener();
                final MenuListener menuListener = (MenuListener) /*TS as unknown*/ displayable;
                menuListener.close();

                this.setDisplay((Displayable) gameCanvasRunnableInterface);
                this.unPauseAppBackground(false);
            }
            else if (command == gameCommandsFactory.EXIT_COMMAND)
            {
                //TWB - Handle exit for android differently
                if(GameMidletEventHandler.getInstance().getEventListenerInterfaceListP().size() == 0)
                {
                    this.exitProgress(true);
                }
                
                GameMidletEventHandler.getInstance().fireEvent(new DemoGameMidletEvent(this, DemoGameMidletStateFactory.getInstance().NONE));
            }
            else if (command == gameCommandsFactory.EXIT_WITHOUT_PROGRESS_COMMAND)
            {
                this.exitProgress(false);
            }
            else if (command == gameCommandsFactory.START_COMMAND ||
                command == gameCommandsFactory.RESTART_COMMAND ||
                command == gameCommandsFactory.CONTINUE_COMMAND)
            {
                // GameState gameState = GameStateFactory.getInstance(command);
                if (this.gameMidletStateFactory.getCurrentGameState() != this.gameStateFactory.PLAYING_GAME_STATE ||
                		command == gameCommandsFactory.RESTART_COMMAND)
                {
                    if (this.gameStartTimeHelper.isTimeTNT())
                    {
                        if(command == gameCommandsFactory.START_COMMAND && this.isDemoLoading()) {
                            this.logUtil.putF("Trying to Start Game Before Loading Complete", this, this.midletStrings.COMMAND_ACTION);
                        } else {
                            this.startedBefore = true;
                            this.createGame();
                            this.gameMidletStateFactory.setCurrentGameState(this.gameStateFactory.PLAYING_GAME_STATE);
                        }
                    }
                    else
                    {
                        this.logUtil.putF("Starting Game Too Often", this, this.midletStrings.COMMAND_ACTION);
                    }
                }
                else
                {
                    this.logUtil.putF("Already in playing state", this, this.COMMAND_ACTION);
                }

            }
            else if (command == gameCommandsFactory.BUY_COMMAND)
            {                
                InApplicationPurchaseFactory inApplicationPurchaseFactory =
                    InApplicationPurchaseFactory.getInstance();

                final BasicArrayList list = LockableFeatureFactory.getInstance().getList();
                final LockableFeature lockableFeature = (LockableFeature) list.get(0);

                if (list.size() > 0 && !inApplicationPurchaseFactory.isPurchased(lockableFeature)) {
                    inApplicationPurchaseFactory.purchase(lockableFeature);
                    //TextNotificationUtil.getInstance().fireSuccess("In Application Purchase Request");
                }
                else
                {
                    TextNotificationUtil.getInstance().fireSuccess("Already Purchased");
                }
            }
            else if (command == gameCommandsFactory.QUIT_COMMAND)
            {
                if (this.gameStartTimeHelper.isTimeTNT())
                {
                    //Close on quit for HighScoresTextBox.
                    if (displayable instanceof HighScoreTextBox) {
                        final MenuListener menuListener = (MenuListener) /*TS as unknown*/ displayable;
                        menuListener.close();
                    }

                    this.stopGameCanvasRunnableInterface();
                    this.gameMidletStateFactory.setCurrentGameState(this.gameStateFactory.NO_GAME_STATE);
                    this.setDemo();
                }
            }
            else if (command == this.myCommandsFactory.RESUME_COMMAND)
            {
                this.unPauseAppBackground(false);
            }
            else if (command == this.myCommandsFactory.PAUSE_COMMAND)
            {
                this.pauseAppBackground(false);
            }
            else if (command == this.myCommandsFactory.SET_DISPLAYABLE)
            {
                this.pauseAppBackground(false);

                this.setDisplay(displayable);
            }
            else if (command == gameCommandsFactory.SET_MENU_DISPLAYABLE)
            {
                this.pauseAppBackground(false);

                final MenuListener menuListener = (MenuListener) /*TS as unknown*/ displayable;
                menuListener.open();

                this.setDisplay(displayable);
            }
            else if (command == gameCommandsFactory.START_TRACE)
            {
                this.debugInterface.start();
                final AllBinaryGameCanvas gameCanvas = (AllBinaryGameCanvas) allbinaryGameCanvasRunnableInterface;
                gameCanvas.addCommand(gameCommandsFactory.STOP_TRACE);
                gameCanvas.removeCommand(gameCommandsFactory.START_TRACE);
            }
            else if (command == gameCommandsFactory.STOP_TRACE)
            {
                this.pauseAppBackground(false);
                this.debugInterface.stop();
                this.unPauseAppBackground(false);
                final AllBinaryGameCanvas gameCanvas = (AllBinaryGameCanvas) allbinaryGameCanvasRunnableInterface;
                gameCanvas.addCommand(gameCommandsFactory.START_TRACE);
                gameCanvas.removeCommand(gameCommandsFactory.STOP_TRACE);
            }
            else if (command == gameCommandsFactory.DEFAULT_OPTIONS)
            {
                final Displayable tempDisplayable = displayable;
                if (tempDisplayable instanceof GameOptionsForm)
                {
                    GameFeatureFormUtil.getInstance().setDefault((CommandForm) tempDisplayable);
                }
            }
            else if (command == GameInputMappingInstructionsCanvas.DISPLAY)
            {
                final Displayable tempDisplayable = displayable;
                if (tempDisplayable instanceof GameInputMappingCanvas)
                {
                    GameInputMappingCanvas gameInputMappingCanvas = (GameInputMappingCanvas) tempDisplayable;
                    gameInputMappingCanvas.update();
                }

                this.commandAction(
                    gameCommandsFactory.SET_MENU_DISPLAYABLE, this.getInputMappingInstructionsCanvas());

                //this.setDisplay((Displayable) this.getInputMappingInstructionsCanvas());
            }
            else if (HighScoreCommandsFactory.getInstance().isHighScoreCommand(command))
            {
                final Displayable tempDisplayable = displayable;
                if (tempDisplayable instanceof HighScoresCanvas)
                {
                    final HighScoresCanvas highScoresCanvas =
                        (HighScoresCanvas) tempDisplayable;

                    highScoresCanvas.updateCommand(command);
                }
            }
            else if (command == HighScoreCommands.getInstance().DISPLAY)
            {
                this.commandAction(
                    gameCommandsFactory.SET_MENU_DISPLAYABLE,
                    this.createHighScoresCanvas());
            }
            else if (command == GameInputMappingInstructionsCanvas.CLOSE)
            {
                final MenuListener menuListener = (MenuListener) /*TS as unknown*/ displayable;
                menuListener.close();

                this.commandAction(GameInputMappingCanvas.DISPLAY, NullCanvas.NULL_CANVAS);
            }
            else if (command == GameInputMappingCanvas.DISPLAY)
            {
                //TWB - Called for HTML5 but not others?

                if (J2MEUtil.isHTML())
                {
                    this.pauseAppBackground(false);
                }

                this.commandAction(gameCommandsFactory.SET_MENU_DISPLAYABLE, 
                		this.getInputMappingCanvas()
                		);

                //this.setDisplay((Displayable) this.getInputMappingCanvas());
            }
            else if (command == GameInputMappingCanvas.DEFAULT)
            {
                final Displayable tempDisplayable = displayable;
                if (tempDisplayable instanceof GameInputMappingCanvas)
                {
                    GameInputMappingCanvas gameInputMappingCanvas =
                        (GameInputMappingCanvas) tempDisplayable;
                    gameInputMappingCanvas.setDefault();
                }
            }
            else if (command == InGameOptionsForm.DISPLAY)
            {
                this.pauseAppBackground(false);

                final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();

                progressCanvas.addNormalPortion(50, "In Game Options");

                final AllBinaryGameLayerManager layerManager = this.createGameLayerManager();
                
                final InGameOptionsFormFactory inGameOptionsFormFactory = 
                    InGameOptionsFormFactory.getInstance();
                
                inGameOptionsFormFactory.init(this,
                    new InGameFeatures(), "Options In Game",
                    layerManager.getBackgroundBasicColor(),
                    layerManager.getForegroundBasicColor()
                    );

                final CommandForm inGameOptionsForm = inGameOptionsFormFactory.get();

                inGameOptionsForm.setItemStateListener(
                        allbinaryGameCanvasRunnableInterface);

                this.commandAction(
                    gameCommandsFactory.SET_MENU_DISPLAYABLE,
                    inGameOptionsForm);
            }
            else if (command == InGameOptionsForm.DEFAULT)
            {
                final InGameOptionsForm inGameOptionsForm = (InGameOptionsForm) 
                    InGameOptionsFormFactory.getInstance().get();
                GameFeatureFormUtil.getInstance().setDefault(inGameOptionsForm);
            }
            else if (command == gameCommandsFactory.DISPLAY_OPTIONS)
            {
                AllBinaryMediaManager.setMuted(true);
                this.stopAll();

                final MainFeatureFactory mainFeatureFactory =
                    MainFeatureFactory.getInstance();

                this.isFullScreen = this.features.isFeature(
                    mainFeatureFactory.FULL_SCREEN);
                
                //PreLogUtil.put("Open isFullScreen: ").append(isFullScreen, this, midletStrings.COMMAND_ACTION);

                ResizableListenerHandler.getInstance().fireEvent(true);

                this.setResized(false);

                this.commandAction(
                    gameCommandsFactory.SET_MENU_DISPLAYABLE,
                    this.getGameOptionsForm());
            }
            else if (command == gameCommandsFactory.CLOSE_OPTIONS)
            {
                //PreLogUtil.put("Close", this, midletStrings.COMMAND_ACTION);
                ResizableListenerHandler.getInstance().fireEvent(false);

                this.commandAction(
                    gameCommandsFactory.CLOSE_AND_SHOW_GAME_CANVAS,
                    displayable);

                final StringMaker stringBuffer = new StringMaker();
                stringBuffer.append("Close isFullScreen/change: ");
                stringBuffer.appendboolean(this.isFullScreen);
                stringBuffer.appendboolean(this.fullScreenUtil.isScreenChange(this.isFullScreen));
                stringBuffer.append(" isResized: ");
                stringBuffer.appendboolean(this.isResized());

                PreLogUtil.put(stringBuffer.toString(), this, this.COMMAND_ACTION);

                //Restart canvas if screen change
                if(this.isResized() ||
                   this.fullScreenUtil.isScreenChange(this.isFullScreen))
                {
                    //PreLogUtil.put("Resized/Changed", this, midletStrings.COMMAND_ACTION);

                    this.updateFullScreen();
                }

                AllBinaryMediaManager.setMuted(false);
            }
            else if (command == gameCommandsFactory.DISPLAY_LOAD_FORM)
            {
                this.pauseAppBackground(false);

                final KeyValuePersistance keyValuePersistance = 
                    GamePersistanceSingleton.getInstance();
                
                keyValuePersistance.clear();
                keyValuePersistance.loadAll(this.abeClientInformation);

                if(this.getLoadGameForm() == CommandForm.getNullCommandForm())
                {
                    final AllBinaryGameLayerManager layerManager = this.createGameLayerManager();
                    
                    this.setLoadGameForm(new LoadGameForm(this, "Load Game",
                            layerManager.getBackgroundBasicColor(),
                            layerManager.getForegroundBasicColor()));
                }
                else
                {
                    this.getLoadGameForm().update();
                }

                this.commandAction(
                    gameCommandsFactory.SET_MENU_DISPLAYABLE, this.getLoadGameForm());

                //this.setDisplay((Displayable) this.getLoadGameForm());
            }
            else if (command == gameCommandsFactory.LOAD_FILE)
            {
                final LoadGameForm loadGameForm = (LoadGameForm) this.getLoadGameForm();
                final int index = loadGameForm.getSelectedId();

                if (index != -1)
                {
                    final KeyValuePersistance keyValuePersistance = 
                        GamePersistanceSingleton.getInstance();
                    
                    this.setStartStateHashtable(keyValuePersistance.get(index));

                    final MenuListener menuListener = this.getLoadGameForm();
                    menuListener.close();

                    PreLogUtil.put(
                            BasicMotionGesturesHandler.getInstance().toString(), this, this.COMMAND_ACTION);
                    
                    this.commandAction(gameCommandsFactory.START_COMMAND, NullCanvas.NULL_CANVAS);

                    PreLogUtil.put(
                            BasicMotionGesturesHandler.getInstance().toString(), this, this.COMMAND_ACTION);
                }
            }
            else if (command == gameCommandsFactory.DELETE_FILE)
            {
                final LoadGameForm loadGameForm = (LoadGameForm) this.getLoadGameForm();
                final int index = loadGameForm.getSelectedText();

                if (index != -1)
                {
                    final KeyValuePersistance keyValuePersistance = 
                        GamePersistanceSingleton.getInstance();
                    
                    keyValuePersistance.delete(this.abeClientInformation, index);
                    keyValuePersistance.clear();
                    keyValuePersistance.loadAllSize(this.abeClientInformation, 1);
                    this.getLoadGameForm().update();
                }
            }
            /*
            else if (command == GameCommands.DISPLAY_SAVE_FORM)
            {
            this.pauseApp();
            this.setDisplay((Displayable) this.getSaveGameForm());
            }*/
            else if (command == gameCommandsFactory.SAVE)
            {
                final KeyValuePersistance keyValuePersistance = 
                    GamePersistanceSingleton.getInstance();
                
                this.pauseAppBackground(false);
                //String name = ((SaveGameForm) this.getSaveGameForm()).getText();
                keyValuePersistance.save(this.abeClientInformation, this.getCurrentStateHashtable());

                //this.setDisplay((Displayable) allbinaryGameCanvasRunnableInterface);

                this.unPauseAppBackground(false);
            }
            else if (command == HighScoreUtil.SUBMIT_TEXTBOX_COMMAND)
            {
                this.logUtil.putF("Submitted Score", this, this.COMMAND_ACTION);

                if (displayable instanceof HighScoreTextBox) {
                    final HighScoreTextBox highScoreTextBox = (HighScoreTextBox) /*TS as unknown*/ displayable;
                    highScoreTextBox.submitted = true;
                }

                allbinaryGameCanvasRunnableInterface.setHighScoreSubmitted(true);

                this.commandAction(gameCommandsFactory.CLOSE_AND_SHOW_GAME_CANVAS, displayable);
            }
            else if (command == gameCommandsFactory.DISPLAY_ABOUT)
            {
                this.aboutCommandProcessor.process(this, gameCommandsFactory.SET_MENU_DISPLAYABLE, this.getAboutCanvas());
            }
            else if (command == gameCommandsFactory.OPEN_WEB_URL)
            {
                this.webCommandProcessor.process(this, gameCommandsFactory.OPEN_WEB_URL, NullCanvas.NULL_CANVAS);
            }
            else if (command == gameCommandsFactory.TOGGLE_KEYBOARD)
            {
                final VirtualKeyboardEventHandler virtualKeyboardEventHandler = 
                    VirtualKeyboardEventHandler.getInstance();
                
                virtualKeyboardEventHandler.fireEvent(virtualKeyboardEventHandler.SHOW_EVENT);
            }
            else if (this.tsUtil.compareTo(command.getLabel(), gameCommandsFactory.TOGGLE_FULLSCREEN.getLabel()) == 0)
            {
                final MainFeatureFactory mainFeatureFactory = MainFeatureFactory.getInstance();

                final boolean isFullScreen = this.features.isFeature(mainFeatureFactory.FULL_SCREEN);
        
                if(isFullScreen)
                {
                    this.features.removeDefault(mainFeatureFactory.FULL_SCREEN);
                }
                else
                {
                    this.features.addDefault(mainFeatureFactory.FULL_SCREEN);
                }
                
                this.updateFullScreen();
            }

            //no else
            //if (command != gameCommandsFactory.START_COMMAND && command != gameCommandsFactory.RESTART_COMMAND)
            //{
            	//gameMidletStateFactory.setCurrentGameState(GameState.NO_GAME_STATE);
            //}

        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.midletStrings.COMMAND_ACTION, e);
            if (command != GameCommandsFactory.getInstance().EXIT_COMMAND)
            {
                this.exitProgress(false);
            }
        }
    }

    private void updateFullScreen()
    {
        final MainFeatureFactory mainFeatureFactory = MainFeatureFactory.getInstance();

        final boolean isFullScreen = this.features.isFeature(mainFeatureFactory.FULL_SCREEN);
        
        //fire should be called in Canvas setFullScreenMode
        //ScreenListenerHandler.getInstance().fire(isFullScreen);
        
        final Displayable displayable = this.getDisplay().getCurrent();
        
        if(displayable instanceof Canvas)
        {
            final Canvas canvas = (Canvas) /*TS as unknown*/ displayable;
            canvas.setFullScreenMode(isFullScreen);
        }

        //this.commandAction(
          //      gameCommandsFactory.QUIT_COMMAND, displayable);
    }

    /*
    private void closeMenuListener() throws Exception
    {
    Display display = this.getDisplay();
    if(display != null)
    {
    Displayable displayable = display.getCurrent();
    if(displayable != null)
    {
    if(displayable instanceof GameCommandCanvas)
    {
    MenuListener menuListener = (MenuListener) displayable;
    menuListener.close();
    }
    }
    }
    }
     */
    
    public void onEvent(final AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }

    protected MyCanvas getInputMappingInstructionsCanvas()
        throws Exception
    {
        return new GameInputMappingInstructionsCanvas(this, this.createGameLayerManager());
    }

    protected MyCanvas getInputMappingCanvas()
        throws Exception
    {
        //Paintable[] paintableArray = {this.getHelpPaintable()}; 
        //return new BasicPaintablesCanvas(this, this.createGameLayerManager(), paintableArray);
        return new GameInputMappingCanvas(this.abeClientInformation, this, this.createGameLayerManager(), this.getHelpPaintable());
    }

    //You can override this with your own Canvas
    protected MyCanvas getAboutCanvas() throws Exception
    {
        return new BasicPaintablesCanvas(this, CanvasStrings.getInstance().ABOUT, this.createGameLayerManager(),
                AboutPaintableFactory.getInstance().paintableArray);
    }

    protected HelpPaintable getHelpPaintable()
        throws Exception
    {
        throw new Exception(this.commonStrings.NOT_IMPLEMENTED);
    }

    protected HighScoresCanvas createHighScoresCanvas() throws Exception
    {
        throw new Exception(this.commonStrings.NOT_IMPLEMENTED);
    }

    public void startGameCanvasRunnableInterface() throws Exception
    {
        //this.logUtil.putF(this.commonStrings.START, this, "startGameCanvasRunnableInterface");
        
        final ThreadFactoryUtil threadFactoryUtil = ThreadFactoryUtil.getInstance();

        //thread = 
            this.thread = threadFactoryUtil.getInstanceGameCanvasRunnable(this.allbinaryGameCanvasRunnableInterface);

        this.logUtil.putF(new StringMaker().append("Thread Priority: ").appendint(this.thread.getPriority()).toString(), this, "startGameCanvasRunnableInterface");

        // canvasThread.setPriority(Thread.NORM_PRIORITY).append(2);
        //thread.setPriority(Thread.MAX_PRIORITY);

        this.allbinaryGameCanvasRunnableInterface.setThread(this.thread);

        threadFactoryUtil.start(this.thread);
    }

    protected void stopGameCanvasRunnableInterface() throws Exception
    {
        this.logUtil.putF(this.commonStrings.START, this, this.gameStrings.STOP_GAME_CANVAS_RUNNABLE_INTERFACE);

        GameNotificationEventHandler.getInstance().removeAllListeners();
        ColorChangeEventHandler.getInstance().removeAllListeners();
        GameEventHandlerUtil.removeAllListeners();
        
        // TWB- Depends on the phone - emulators like it without but i think my
        // sprint phone needed a null canvas
        // this.setDisplay((Displayable) new ProgressCanvas(this));
        
        final GameCanvasRunnableInterface gameCanvasRunnableInterface = 
            this.allbinaryGameCanvasRunnableInterface;
        if (gameCanvasRunnableInterface != NullGameCanvasRunnable.NULL_GAME_CANVAS_RUNNABLE)
        {
            this.logUtil.putF("Set Running False", this, this.gameStrings.STOP_GAME_CANVAS_RUNNABLE_INTERFACE);
            gameCanvasRunnableInterface.setRunning(false);
        }
        else
        {
            this.logUtil.putF("StopGame - Could Not Stop", this, this.gameStrings.STOP_GAME_CANVAS_RUNNABLE_INTERFACE);
        }

        // Wait for the thread to end then continue
        ThreadUtil.getInstance().join(this.thread);
        
        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
        if(this.features.isFeature(MainFeatureFactory.getInstance().LOAD_ALL))
        {
            progressCanvas.addNormalPortion(50, "Stopped Game Runnable");
        }
        else
        {
            progressCanvas.addNormalPortion(50, "Stopped Main Runnable");
        }
        

        this.logUtil.putF(this.commonStrings.END, this, this.gameStrings.STOP_GAME_CANVAS_RUNNABLE_INTERFACE);
    }

    public GameCanvasRunnableInterface getGameCanvasRunnableInterface()
    {
        return this.allbinaryGameCanvasRunnableInterface;
    }

    public void setGameCanvasRunnableInterface(
        final GameCanvasRunnableInterface gameCanvasRunnableInterface)
    {
        this.allbinaryGameCanvasRunnableInterface = gameCanvasRunnableInterface;
        
//        if (allbinaryGameCanvasRunnableInterface == NullGameCanvasRunnable.NULL_GAME_CANVAS_RUNNABLE)
//            
//            this.logUtil.put(new StringMaker().append("allbinaryGameCanvasRunnableInterface: ").append(this.allbinaryGameCanvasRunnableInterface).toString(), this, "setGameCanvasRunnableInterface", new Exception());
//        }
        
    }

    protected AllBinaryGameLayerManager createGameLayerManager()
    {
        final GameInfo gameInfo = new GameInfo(GameTypeFactory.getInstance().SINGLE_PLAYER,
            GameMode.SERVER, PlayerTypesFactory.getInstance().PLAYER_TYPE_ONE, 1, 1);

        return new AllBinaryGameLayerManager(
                this.basicColorFactory.BLACK,
                this.basicColorFactory.WHITE, 
                gameInfo);
    }

    //protected void setGameOptionsForm(GameOptionsForm gameOptionsForm)
    //{
        //this.gameOptionsForm = gameOptionsForm;
    //}
    
    protected CommandForm getGameOptionsForm()
    {
        final AllBinaryGameLayerManager layerManager = this.createGameLayerManager();

        return GameOptionsFormFactory.getInstance().init(this, "Game Options",
                layerManager.getBackgroundBasicColor(),
                layerManager.getForegroundBasicColor());
        //return GameOptionsForm.getInstance();
        //return gameOptionsForm;
    }

    public void save() throws Exception
    {
        this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.SAVE);

        final Hashtable hashtable = this.getCurrentStateHashtable();
        GamePersistanceSingleton.getInstance().save(this.abeClientInformation, hashtable);
    }

    @Override
    public Hashtable getCurrentStateHashtable() throws Exception
    {
        this.logUtil.putF(this.commonStrings.START, this, "getCurrentStateHashtable");
        final Hashtable hashtable = new Hashtable();

        if (this.allbinaryGameCanvasRunnableInterface != NullGameCanvasRunnable.NULL_GAME_CANVAS_RUNNABLE)
        {
            final Hashtable currentHashtable = this.allbinaryGameCanvasRunnableInterface.getCurrentStateHashtable();
            final Enumeration enumeration = currentHashtable.keys();
            Object key;
            while (this.enumerationUtil.hasMoreElements(enumeration))
            {
                key = this.enumerationUtil.nextElement(enumeration);
                hashtable.put(key, currentHashtable.get(key));
            }
        }

        return hashtable;
    }

    public void setLoadGameForm(final LoadGameForm loadGameForm)
    {
        this.loadGameForm = loadGameForm;
    }

    public CommandForm getLoadGameForm()
    {
        return this.loadGameForm;
    }
    
    public void setResized(final boolean resized)
    {
        this.resized = resized;
    }

    public boolean isResized()
    {
        return this.resized;
    }

    private boolean startedBefore = false;
    public boolean isDemoLoading() {
        
        if(this.startedBefore) {
            return false;
        }
        
        final Displayable displayable = this.getDisplay().getCurrent();
        
        if(displayable instanceof DemoCanvas) {
            final DemoCanvas demoCanvas = (DemoCanvas) /*TS as unknown*/ displayable;

            if(demoCanvas.isDemoLoading()) {
                return true;
            }
        }

        return false;
    }    
}
