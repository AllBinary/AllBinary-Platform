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

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

import org.allbinary.business.advertisement.GameAdStateFactory;
import org.allbinary.game.GameAdState;
import org.allbinary.graphics.ResizableListenerHandler;
import org.allbinary.input.AllBinarySensorManager;


import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.system.security.licensing.InApplicationPurchaseFactory;
import org.allbinary.logic.system.security.licensing.LockableFeature;
import org.allbinary.logic.system.security.licensing.LockableFeatureFactory;
import org.allbinary.canvas.GameStatisticsFactory;
import org.allbinary.canvas.FullScreenUtil;
import org.allbinary.debug.DebugFactory;
import org.allbinary.debug.DebugInterface;
import org.allbinary.game.GameInfo;
import org.allbinary.game.GameMode;
import org.allbinary.game.GameTypeFactory;
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
import org.allbinary.game.configuration.feature.HTMLFeatureFactory;
import org.allbinary.game.configuration.feature.MainFeatureFactory;
import org.allbinary.game.configuration.persistance.GamePersistanceSingleton;
import org.allbinary.game.configuration.persistance.KeyValuePersistance;
import org.allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import org.allbinary.game.displayable.canvas.BasicPaintablesCanvas;
import org.allbinary.game.displayable.canvas.GameCanvasRunnableInterface;
import org.allbinary.game.displayable.canvas.GameEventHandlerUtil;
import org.allbinary.game.displayable.canvas.GameInputMappingCanvas;
import org.allbinary.game.displayable.canvas.GameInputMappingInstructionsCanvas;
import org.allbinary.game.displayable.canvas.MenuListener;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.hud.event.GameNotificationEventHandler;
import org.allbinary.game.paint.help.HelpPaintable;
import org.allbinary.game.score.HighScoreCommands;
import org.allbinary.game.score.HighScoreCommandsFactory;
import org.allbinary.game.score.displayable.HighScoresCanvas;
import org.allbinary.game.state.GameState;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.ColorChangeEventHandler;
import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.graphics.displayable.command.MyCommandsFactory;
import org.allbinary.graphics.displayable.screen.CommandForm;
import org.allbinary.input.event.VirtualKeyboardEventHandler;
import org.allbinary.input.motion.gesture.observer.BasicMotionGesturesHandler;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.media.audio.AllBinaryMediaManager;
import org.allbinary.midlet.MidletStrings;
import org.allbinary.midlet.ProgressMidlet;
import org.allbinary.thread.ThreadFactoryUtil;
import org.allbinary.thread.ThreadUtil;
import org.allbinary.time.TimeDelayHelper;
import javax.microedition.lcdui.Canvas;
import org.allbinary.game.input.TextNotificationUtil;
import org.allbinary.game.score.displayable.HighScoreTextBox;
import org.allbinary.game.score.displayable.HighScoreUtil;
import org.allbinary.graphics.displayable.CanvasStrings;
import org.allbinary.graphics.displayable.screen.AboutCommandProcessor;
import org.allbinary.graphics.displayable.screen.AboutPaintableFactory;
import org.allbinary.graphics.displayable.screen.WebCommandProcessor;
import org.allbinary.logic.system.security.licensing.ClientInformationFactory;
import org.allbinary.util.BasicArrayList;

public class GameMidlet extends ProgressMidlet
    implements CommandListener //, GameMidletEventListener
{
    protected final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();
    protected final Features features = Features.getInstance();
    protected final HTMLFeatureFactory htmlFeatureFactory = HTMLFeatureFactory.getInstance();
    protected final MidletStrings midletStrings = MidletStrings.getInstance();
    protected final MyCommandsFactory myCommandsFactory = MyCommandsFactory.getInstance();
    protected final GameAdStateFactory gameAdStateFactory = GameAdStateFactory.getInstance();

    private final String DISPLAYABLE = " Displayable: ";
    private final String COMMAND_NAME = "command Name/Label: ";
    private final String NO_COMMAND = "No Command";
    private final String NO_DISPLAYABLE = "No Displayable";
    private final String COMMAND_ACTION = new StringMaker().append("GameMidlet::").append(midletStrings.COMMAND_ACTION).toString();

    private final AboutCommandProcessor aboutCommandProcessor = AboutCommandProcessor.getInstance();
    private final WebCommandProcessor webCommandProcessor = WebCommandProcessor.getInstance();
    private final GameMidletStateFactory gameMidletStateFactory = GameMidletStateFactory.getInstance();
    protected final TimeDelayHelper gameStartTimeHelper = new TimeDelayHelper(240);
    private final FullScreenUtil fullScreenUtil = FullScreenUtil.getInstance();

    private final DebugInterface debugInterface;
    
    private GameCanvasRunnableInterface allbinaryGameCanvasRunnableInterface;
    private Thread thread;

    // Screens/Canvas
    //private CommandForm saveGameForm;
    private LoadGameForm loadGameForm;
    private boolean isFullScreen;
    private boolean resized;
    
    //private GameOptionsForm gameOptionsForm;

    public GameMidlet(final ClientInformationFactory clientInformationFactory)
    {
        super(clientInformationFactory);
        //LogUtil.put(LogFactory.getInstance("GameMidlet::GameMidlet", this, commonStrings.CONSTRUCTOR));
        
        //For BB can be used for J2ME as well
        SmallIntegerSingletonFactory.getInstance().init(0x291, 6);
        //This can be used for J2ME but not BB
        //SmallIntegerSingletonFactory.getInstance().init(0x101, 6);

        ProgressCanvasFactory.getInstance().init(this);

        GameFeatureEventHandler.getInstance().addListener(
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
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }

    protected void createGame() throws Exception
    {
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }

    protected void mediaShutdown() throws Exception
    {
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }

    public void stopAll()
    {
    }

    protected void pauseApp()
    {
        this.pauseAppBackground(true);
        
        final GameAdState gameAdState = gameAdStateFactory.getCurrentInstance();
        
        gameAdState.getAdvertisements().stopAll();
    }

    protected void pauseAppBackground(final boolean background)
    {
        final String METHOD_NAME = "pauseAppBackground";
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, METHOD_NAME));
        //PreLogUtil.put(commonStrings.START, this, METHOD_NAME);
        
        if (allbinaryGameCanvasRunnableInterface != null)
        {
            allbinaryGameCanvasRunnableInterface.pause();
            /*
            if (!allbinaryGameCanvasRunnableInterface.isPaused())
            {
                allbinaryGameCanvasRunnableInterface.pause();
            }
             */
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("<<<<<< Null", this, METHOD_NAME));
        }
        
        AllBinarySensorManager.getInstance().shutdown();
    }

    protected void unPauseApp()
    {
        this.unPauseAppBackground(true);
        
        final GameAdState gameAdState = gameAdStateFactory.getCurrentInstance();
        
        gameAdState.getAdvertisements().startAll();
    }
    
    protected void unPauseAppBackground(boolean background)
    {
        final String METHOD_NAME = "unPauseAppBackground";
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, METHOD_NAME));
        //PreLogUtil.put(commonStrings.START, this, METHOD_NAME);
        
        AllBinarySensorManager.getInstance().init();

        final GameCanvasRunnableInterface gameCanvasRunnableInterface =
            this.allbinaryGameCanvasRunnableInterface;

        if (gameCanvasRunnableInterface != null)
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
            LogUtil.put(LogFactory.getInstance("<<<<<< Null", this, METHOD_NAME));
        }
    }
    
    protected void destroyApp(boolean unconditional, boolean isProgress)
    {
        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();

        if (isProgress)
        {
            progressCanvas.start();

            this.commandAction(myCommandsFactory.SET_DISPLAYABLE, progressCanvas);

            // progressCanvas.waitUntilDisplayed();
        }
        
        this.destroyApp(unconditional);

        if (isProgress)
        {
            progressCanvas.end();
        }
    }
    
    protected void destroyApp(boolean unconditional)
    {
        final String METHOD_NAME = "GameMidlet::destroyApp";
        try
        {
            PreLogUtil.put(GameStatisticsFactory.getInstance().toString(), this, METHOD_NAME);
            //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, METHOD_NAME));

            final GameAdState gameAdState = gameAdStateFactory.getCurrentInstance();
            
            gameAdState.getAdvertisements().stopAll();
            
            if (!this.isDestroyed())
            {
                this.stopGameCanvasRunnableInterface();
                this.setGameCanvasRunnableInterface(null);
                //if(!AppletUtil.isAppletLoader(this))
                //{
                this.mediaShutdown();
                //}
            }
            else
            {
                LogUtil.put(LogFactory.getInstance(
                    "Midlet Managment Error: Midlet Should Only Be Destroyed Once", this, METHOD_NAME));
            }

            super.destroyApp(true);

            //LogUtil.put(LogFactory.getInstance(commonStrings.END, this, METHOD_NAME));
            PreLogUtil.put(commonStrings.END, this, METHOD_NAME);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, METHOD_NAME, e));
        }
        LogUtil.put(LogFactory.getInstance(commonStrings.END, this, METHOD_NAME));
    }
 
    protected void startApp()
    {
        try
        {
            final GameAdState gameAdState = gameAdStateFactory.getCurrentInstance();
            
            gameAdState.getAdvertisements().startAll();
            
            final String START_APP = "startApp";
            
            LogUtil.put(LogFactory.getInstance(commonStrings.START, this, START_APP));
            //PreLogUtil.put(commonStrings.START, this, START_APP);

            final GameCanvasRunnableInterface gameCanvasRunnableInterface =
                this.allbinaryGameCanvasRunnableInterface;

            //If the first time/null then start demo or unpausing
            //thus start up only occurs one time ever otherwise this is just unpause
            if (gameCanvasRunnableInterface == null)
            {
            	gameMidletStateFactory.setCurrentGameState(GameState.NO_GAME_STATE);
                this.setDemo();
            }
            else
            {
                LogUtil.put(LogFactory.getInstance("GameCanvasRunnableInterface is available", this, START_APP));

                //Starting midlet after a previous start means that we only unpause the
                //GameCanvasRunnableInterface if it is currently displayed
                if (gameCanvasRunnableInterface == this.getCurrentDisplayable())
                {
                    this.unPauseAppBackground(false);
                }
                else
                {
                    LogUtil.put(LogFactory.getInstance("GameCanvasRunnableInterface is not current displayable", this, START_APP));
                }
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "startApp", e));
            destroyApp(false);
            //TWB - Only remove from context when multiple midlets share the same emulator
            notifyDestroyed();
        }
    }
    
    public synchronized void commandAction(final Command command, final Displayable displayable)
    {
        try
        {
            String displayableAsString = NO_DISPLAYABLE;
            if(displayable != null)
            {
                 displayableAsString = displayable.toString();
            }

            String label = NO_COMMAND;
            if(command != null)
            {
                label = command.getLabel();
            }

            PreLogUtil.put(new StringMaker().append(COMMAND_NAME).append(label).append(DISPLAYABLE).append(displayableAsString).toString(), this, this.COMMAND_ACTION);

            final GameCommandsFactory gameCommandsFactory = 
                GameCommandsFactory.getInstance();

            if (command == gameCommandsFactory.SHOW_GAME_CANVAS)
            {
                //TWB - If progress concurrency is a problem then this is probably the cause
                if(this.getDisplay().getCurrent() != this.allbinaryGameCanvasRunnableInterface)
                {
                    this.setDisplay((Displayable) this.allbinaryGameCanvasRunnableInterface);
                }

                this.unPauseAppBackground(false);
            }
            else if (command == gameCommandsFactory.CLOSE_AND_SHOW_GAME_CANVAS)
            {
                //this.closeMenuListener();
                final MenuListener menuListener = (MenuListener) displayable;
                menuListener.close();

                this.setDisplay((Displayable) this.allbinaryGameCanvasRunnableInterface);
                this.unPauseAppBackground(false);
            }
            else if (command == gameCommandsFactory.EXIT_COMMAND)
            {
                //TWB - Handle exit for android differently
                if(GameMidletEventHandler.getInstance().getEventListenerInterfaceList().size() == 0)
                {
                    this.exit(true);
                }
                
                GameMidletEventHandler.getInstance().fireEvent(null);
            }
            else if (command == gameCommandsFactory.EXIT_WITHOUT_PROGRESS_COMMAND)
            {
                this.exit(false);
            }
            else if (command == gameCommandsFactory.START_COMMAND ||
                command == gameCommandsFactory.RESTART_COMMAND ||
                command == gameCommandsFactory.CONTINUE_COMMAND)
            {
                // GameState gameState = GameStateFactory.getInstance(command);
                if (gameMidletStateFactory.getCurrentGameState() != GameState.PLAYING_GAME_STATE || 
                		command == gameCommandsFactory.RESTART_COMMAND)
                {
                    if (this.gameStartTimeHelper.isTime())
                    {
                        this.createGame();
                        gameMidletStateFactory.setCurrentGameState(GameState.PLAYING_GAME_STATE);
                    }
                    else
                    {
                        LogUtil.put(LogFactory.getInstance("Starting Game Too Often", this, midletStrings.COMMAND_ACTION));
                    }
                }
                else
                {
                    LogUtil.put(LogFactory.getInstance("Already in playing state", this, COMMAND_ACTION));
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
                if (this.gameStartTimeHelper.isTime())
                {
                    //Close on quit for HighScoresTextBox.
                    if (displayable instanceof HighScoreTextBox) {
                        final MenuListener menuListener = (MenuListener) displayable;
                        menuListener.close();
                    }

                    this.stopGameCanvasRunnableInterface();
                    gameMidletStateFactory.setCurrentGameState(GameState.NO_GAME_STATE);
                    this.setDemo();
                }
            }
            else if (command == myCommandsFactory.RESUME_COMMAND)
            {
                this.unPauseAppBackground(false);
            }
            else if (command == myCommandsFactory.PAUSE_COMMAND)
            {
                this.pauseAppBackground(false);
            }
            else if (command == myCommandsFactory.SET_DISPLAYABLE)
            {
                this.pauseAppBackground(false);

                this.setDisplay(displayable);
            }
            else if (command == gameCommandsFactory.SET_MENU_DISPLAYABLE)
            {
                this.pauseAppBackground(false);

                final MenuListener menuListener = (MenuListener) displayable;
                menuListener.open();

                this.setDisplay(displayable);
            }
            else if (command == gameCommandsFactory.START_TRACE)
            {
                this.debugInterface.start();
                ((AllBinaryGameCanvas) this.allbinaryGameCanvasRunnableInterface).addCommand(
                    gameCommandsFactory.STOP_TRACE);
                ((AllBinaryGameCanvas) this.allbinaryGameCanvasRunnableInterface).removeCommand(
                    gameCommandsFactory.START_TRACE);
            }
            else if (command == gameCommandsFactory.STOP_TRACE)
            {
                this.pauseAppBackground(false);
                this.debugInterface.stop();
                this.unPauseAppBackground(false);
                ((AllBinaryGameCanvas) this.allbinaryGameCanvasRunnableInterface).addCommand(
                    gameCommandsFactory.START_TRACE);
                ((AllBinaryGameCanvas) this.allbinaryGameCanvasRunnableInterface).removeCommand(
                    gameCommandsFactory.STOP_TRACE);
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
                final MenuListener menuListener = (MenuListener) displayable;
                menuListener.close();

                this.commandAction(GameInputMappingCanvas.DISPLAY, null);
            }
            else if (command == GameInputMappingCanvas.DISPLAY)
            {
                //TWB - Called for HTML5 but not others?

                if (features.isDefault(htmlFeatureFactory.HTML))
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

                final ProgressCanvas progressCanvas =
                    ProgressCanvasFactory.getInstance();

                progressCanvas.addPortion(50, "In Game Options");

                final AllBinaryGameLayerManager layerManager = this.createGameLayerManager();
                
                InGameOptionsFormFactory.init(this,
                    new InGameFeatures(), "Options In Game",
                    layerManager.getBackgroundBasicColor(),
                    layerManager.getForegroundBasicColor()
                    );
                
                final InGameOptionsForm inGameOptionsForm = 
                    InGameOptionsFormFactory.getInstance();

                inGameOptionsForm.setItemStateListener(
                        this.allbinaryGameCanvasRunnableInterface);

                this.commandAction(
                    gameCommandsFactory.SET_MENU_DISPLAYABLE,
                    inGameOptionsForm);
            }
            else if (command == InGameOptionsForm.DEFAULT)
            {
                GameFeatureFormUtil.getInstance().setDefault(
                        InGameOptionsFormFactory.getInstance());
            }
            else if (command == gameCommandsFactory.DISPLAY_OPTIONS)
            {
                AllBinaryMediaManager.setMuted(true);
                this.stopAll();

                final MainFeatureFactory mainFeatureFactory =
                    MainFeatureFactory.getInstance();

                isFullScreen = features.isFeature(
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
                stringBuffer.append(isFullScreen);
                stringBuffer.append(fullScreenUtil.isScreenChange(isFullScreen));
                stringBuffer.append(" isResized: ");
                stringBuffer.append(this.isResized());

                PreLogUtil.put(stringBuffer.toString(), this, COMMAND_ACTION);

                //Restart canvas if screen change
                if(this.isResized() ||
                   fullScreenUtil.isScreenChange(isFullScreen))
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
                keyValuePersistance.loadAll(abeClientInformation);

                if(this.getLoadGameForm() == null)
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
                final int index = this.getLoadGameForm().getSelectedId();

                if (index != -1)
                {
                    final KeyValuePersistance keyValuePersistance = 
                        GamePersistanceSingleton.getInstance();
                    
                    this.setStartStateHashtable(keyValuePersistance.get(index));

                    final MenuListener menuListener = this.getLoadGameForm();
                    menuListener.close();

                    PreLogUtil.put(
                            BasicMotionGesturesHandler.getInstance().toString(), this, COMMAND_ACTION);
                    
                    this.commandAction(gameCommandsFactory.START_COMMAND, null);

                    PreLogUtil.put(
                            BasicMotionGesturesHandler.getInstance().toString(), this, COMMAND_ACTION);
                }
            }
            else if (command == gameCommandsFactory.DELETE_FILE)
            {
                final int index = this.getLoadGameForm().getSelectedText();

                if (index != -1)
                {
                    final KeyValuePersistance keyValuePersistance = 
                        GamePersistanceSingleton.getInstance();
                    
                    keyValuePersistance.delete(abeClientInformation, index);
                    keyValuePersistance.clear();
                    keyValuePersistance.loadAll(abeClientInformation, 1);
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
                keyValuePersistance.save(abeClientInformation, this.getCurrentStateHashtable());

                //this.setDisplay((Displayable) allbinaryGameCanvasRunnableInterface);

                this.unPauseAppBackground(false);
            }
            else if (command == HighScoreUtil.SUBMIT_TEXTBOX_COMMAND)
            {
                LogUtil.put(LogFactory.getInstance("Submitted Score", this, COMMAND_ACTION));

                if (displayable instanceof HighScoreTextBox) {
                    final HighScoreTextBox menuListener = (HighScoreTextBox) displayable;
                    menuListener.submitted = true;
                }

                this.allbinaryGameCanvasRunnableInterface.setHighScoreSubmitted(true);

                this.commandAction(gameCommandsFactory.CLOSE_AND_SHOW_GAME_CANVAS, displayable);
            }
            else if (command == gameCommandsFactory.DISPLAY_ABOUT)
            {
                this.aboutCommandProcessor.process(this, gameCommandsFactory.SET_MENU_DISPLAYABLE, this.getAboutCanvas());
            }
            else if (command == gameCommandsFactory.OPEN_WEB_URL)
            {
                this.webCommandProcessor.process(this, gameCommandsFactory.OPEN_WEB_URL, null);
            }
            else if (command == gameCommandsFactory.TOGGLE_KEYBOARD)
            {
                final VirtualKeyboardEventHandler virtualKeyboardEventHandler = 
                    VirtualKeyboardEventHandler.getInstance();
                
                virtualKeyboardEventHandler.fireEvent(virtualKeyboardEventHandler.SHOW_EVENT);
            }
            else if (command.getLabel().compareTo(gameCommandsFactory.TOGGLE_FULLSCREEN.getLabel()) == 0)
            {
                final MainFeatureFactory mainFeatureFactory
                        = MainFeatureFactory.getInstance();

                final boolean isFullScreen = features.isFeature(mainFeatureFactory.FULL_SCREEN);
        
                if(isFullScreen)
                {
                    features.removeDefault(mainFeatureFactory.FULL_SCREEN);
                }
                else
                {
                    features.addDefault(mainFeatureFactory.FULL_SCREEN);
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
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, midletStrings.COMMAND_ACTION, e));
            if (command != GameCommandsFactory.getInstance().EXIT_COMMAND)
            {
                this.exit(false);
            }
        }
    }

    private void updateFullScreen()
    {
        final MainFeatureFactory mainFeatureFactory = MainFeatureFactory.getInstance();

        final boolean isFullScreen = features.isFeature(mainFeatureFactory.FULL_SCREEN);
        
        //fire should be called in Canvas setFullScreenMode
        //ScreenListenerHandler.getInstance().fire(isFullScreen);
        
        final Displayable displayable = this.getDisplay().getCurrent();
        
        if(displayable instanceof Canvas)
        {
            ((Canvas) displayable).setFullScreenMode(isFullScreen);
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
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
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
        return new GameInputMappingCanvas(abeClientInformation, this, this.createGameLayerManager(), this.getHelpPaintable());
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
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }

    protected HighScoresCanvas createHighScoresCanvas() throws Exception
    {
        throw new Exception(commonStrings.NOT_IMPLEMENTED);
    }

    public void startGameCanvasRunnableInterface() throws Exception
    {
        //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "startGameCanvasRunnableInterface"));
        
        final ThreadFactoryUtil threadFactoryUtil = ThreadFactoryUtil.getInstance();

        thread = thread = threadFactoryUtil.getInstance(this.allbinaryGameCanvasRunnableInterface);

        LogUtil.put(LogFactory.getInstance(new StringMaker().append("Thread Priority: ").append(thread.getPriority()).toString(), this, "startGameCanvasRunnableInterface"));

        // canvasThread.setPriority(Thread.NORM_PRIORITY).append(2);
        //thread.setPriority(Thread.MAX_PRIORITY);

        this.allbinaryGameCanvasRunnableInterface.setThread(thread);

        threadFactoryUtil.start(thread);
    }

    protected void stopGameCanvasRunnableInterface() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "stopGameCanvasRunnableInterface"));

        GameNotificationEventHandler.getInstance().removeAllListeners();
        ColorChangeEventHandler.getInstance().removeAllListeners();
        GameEventHandlerUtil.removeAllListeners();
        
        // TWB- Depends on the phone - emulators like it without but i think my
        // sprint phone needed a null canvas
        // this.setDisplay((Displayable) new ProgressCanvas(this));
        
        final GameCanvasRunnableInterface gameCanvasRunnableInterface = 
            this.allbinaryGameCanvasRunnableInterface;
        if (gameCanvasRunnableInterface != null)
        {
            LogUtil.put(LogFactory.getInstance("Set Running False", this,
                "stopGameCanvasRunnableInterface"));
            gameCanvasRunnableInterface.setRunning(false);
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("StopGame - Could Not Stop", this,
                "stopGameCanvasRunnableInterface"));
        }

        // Wait for the thread to end then continue
        ThreadUtil.getInstance().join(this.thread);
        
        if(features.isFeature(MainFeatureFactory.getInstance().LOAD_ALL))
        {
            ProgressCanvasFactory.getInstance().addPortion(50, "Stopped Game Runnable");
        }
        else
        {
            ProgressCanvasFactory.getInstance().addPortion(50, "Stopped Main Runnable");
        }
        

        LogUtil.put(LogFactory.getInstance(commonStrings.END, this, "stopGameCanvasRunnableInterface"));
    }

    public GameCanvasRunnableInterface getGameCanvasRunnableInterface()
    {
        return allbinaryGameCanvasRunnableInterface;
    }

    public void setGameCanvasRunnableInterface(
        final GameCanvasRunnableInterface myGameCanvasInterface)
    {
        this.allbinaryGameCanvasRunnableInterface = myGameCanvasInterface;
        
//        if(this.allbinaryGameCanvasRunnableInterface == null) {
//            
//            LogUtil.put(LogFactory.getInstance(new StringMaker().append("allbinaryGameCanvasRunnableInterface: ").append(this.allbinaryGameCanvasRunnableInterface).toString(), this, "setGameCanvasRunnableInterface", new Exception()));
//        }
        
    }

    protected AllBinaryGameLayerManager createGameLayerManager()
    {
        final GameInfo gameInfo = new GameInfo(GameTypeFactory.getInstance().SINGLE_PLAYER,
            GameMode.SERVER, 1, 1);

        return new AllBinaryGameLayerManager(
                basicColorFactory.BLACK,
                basicColorFactory.WHITE, 
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
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "save"));

        final Hashtable hashtable = this.getCurrentStateHashtable();
        GamePersistanceSingleton.getInstance().save(abeClientInformation, hashtable);
    }

    public Hashtable getCurrentStateHashtable() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "getCurrentStateHashtable"));
        final Hashtable hashtable = new Hashtable();

        if (this.allbinaryGameCanvasRunnableInterface != null)
        {
            final Hashtable currentHashtable = this.allbinaryGameCanvasRunnableInterface.getCurrentStateHashtable();
            final Enumeration enumeration = currentHashtable.keys();
            Object key;
            while (enumeration.hasMoreElements())
            {
                key = enumeration.nextElement();
                hashtable.put(key, currentHashtable.get(key));
            }
        }

        return hashtable;
    }

    public void setLoadGameForm(final LoadGameForm loadGameForm)
    {
        this.loadGameForm = loadGameForm;
    }

    public LoadGameForm getLoadGameForm()
    {
        return loadGameForm;
    }
    
    public void setResized(final boolean resized)
    {
        this.resized = resized;
    }

    public boolean isResized()
    {
        return resized;
    }
    
}
