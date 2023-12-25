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

import java.util.Hashtable;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Item;

import org.allbinary.business.advertisement.GameAdStateFactory;
import org.allbinary.game.GameAdState;
import org.allbinary.graphics.ResizableListenerHandler;
import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.graphics.opengles.CurrentDisplayableFactory;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.graphics.opengles.OpenGLThreadUtil;
import org.allbinary.util.BasicArrayList;


import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.system.os.OperatingSystemFactory;
import org.allbinary.logic.system.security.licensing.InApplicationPurchaseFactory;
import org.allbinary.logic.system.security.licensing.LockableFeature;
import org.allbinary.logic.system.security.licensing.LockableFeatureFactory;
import org.allbinary.animation.Animation;
import org.allbinary.animation.special.SpecialAnimation;
import org.allbinary.canvas.AllGameStatisticsFactory;
import org.allbinary.canvas.BaseGameStatistics;
import org.allbinary.canvas.CustomGameMenuUtil;
import org.allbinary.canvas.FullScreenUtil;
import org.allbinary.canvas.GameStatisticsFactory;
import org.allbinary.canvas.RunnableCanvas;
import org.allbinary.game.GameInfo;
import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.configuration.event.ChangedGameFeatureListener;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureFactory;
import org.allbinary.game.configuration.feature.HTMLFeatureFactory;
import org.allbinary.game.configuration.feature.InputFeatureFactory;
import org.allbinary.game.configuration.feature.MainFeatureFactory;
import org.allbinary.game.init.BasicBuildGameInitializerFactory;
import org.allbinary.game.init.GameInitializationUtil;
import org.allbinary.game.input.GameKey;
import org.allbinary.game.input.GameKeyFactory;
import org.allbinary.game.input.PlatformInputMappingFactory;
import org.allbinary.game.input.event.DownGameKeyEventHandler;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.game.input.event.GameKeyEventHandler;
import org.allbinary.game.input.event.UpGameKeyEventHandler;
import org.allbinary.game.input.mapping.InputToGameKeyMapping;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.midlet.DemoGameMidlet;
import org.allbinary.game.score.HighScoreCommands;
import org.allbinary.game.score.HighScores;
import org.allbinary.game.score.HighScoresCompositeInterface;
import org.allbinary.game.score.HighScoresFactoryInterface;
import org.allbinary.game.score.HighScoresHelper;
import org.allbinary.game.score.HighScoresPaintable;
import org.allbinary.game.score.HighScoresUpdateRunnable;
import org.allbinary.game.state.GameState;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.displayable.event.DisplayChangeEvent;
import org.allbinary.graphics.displayable.event.DisplayChangeEventHandler;
import org.allbinary.graphics.displayable.event.DisplayChangeEventListener;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.graphics.form.CommandCurrentSelectionFormFactory;
import org.allbinary.graphics.form.FormType;
import org.allbinary.graphics.form.FormTypeFactory;
import org.allbinary.graphics.form.ScrollSelectionForm;
import org.allbinary.graphics.form.ScrollSelectionFormNoneFactory;
import org.allbinary.graphics.form.item.CommandTextItemArrayFactory;
import org.allbinary.graphics.paint.InitUpdatePaintable;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.graphics.paint.PaintableInterface;
import org.allbinary.graphics.paint.StatePaintable;
import org.allbinary.graphics.paint.StatePaintableFactory;
import org.allbinary.input.motion.gesture.observer.BasicMotionGesturesHandler;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.media.audio.AllBinaryMediaManager;
import org.allbinary.media.audio.EarlySoundsFactory;
import org.allbinary.media.audio.PrimaryPlayerQueueFactory;
import org.allbinary.media.audio.SecondaryPlayerQueueFactory;
import org.allbinary.thread.SecondaryThreadPool;
import org.allbinary.thread.ThreadFactoryUtil;
import org.allbinary.thread.ThreadUtil;
import org.allbinary.time.TimeDelayHelper;

public class DemoCanvas extends RunnableCanvas 
        implements GameCanvasRunnableInterface,
        MenuListener, HighScoresCompositeInterface, 
        DisplayChangeEventListener
{    
    private StatePaintable basicGameDemoPaintable =
        //new StateNotifyPaintable(this);
        StatePaintableFactory.getInstance();
    private SpecialAnimation specialAnimationInterface = SpecialAnimation.getInstance();
    private Animation paintedSpecialAnimationInterface = SpecialAnimation.getInstance();
    private final FullScreenUtil fullScreenUtil = FullScreenUtil.getInstance();
    private AllBinaryGameCanvas gameCanvas = NullGameCanvas.getInstance();
    
    private final HighScoresHelper highScoresHelper = new HighScoresHelper();

    private final HighScoresPaintable realHighScoresPaintable = new HighScoresPaintable();
    private Paintable highScoresPaintable = NullPaintable.getInstance();
    private int state = 0;
    private Thread canvasThread;
    private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(6000);
    
    private BasicColor basicColor = BasicColorFactory.getInstance().RED;

    private final HighScoresFactoryInterface highScoresFactoryInterface;
    //Menu
    private BasicMenuInputProcessor menuInputProcessor =
        NoMenuInputProcessor.getInstance();
    private ScrollSelectionForm menuForm;

    private final BasicBuildGameInitializerFactory gameInitializationInterfaceFactoryInterface;
    private boolean initialized;
    private final DemoGameStartupRunnable demoGameRunnable;
    private Paintable defaultPaintableInterface;
    private PaintableInterface paintableInterface;
    private final InitUpdatePaintable overlayPaintable;

    private final int LOAD_WAIT = 172;
    private int tempWait = LOAD_WAIT;
    
    private final InputToGameKeyMapping inputToGameKeyMapping = 
        PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance().getInputMapping();
    
    private GameRunnable gameRunnable = NullGameRunnable.getInstance();
    
    public DemoCanvas(CommandListener commandListener,
        HighScoresFactoryInterface highScoresFactoryInterface,
        Paintable paintable,
        InitUpdatePaintable overlayPaintable,
        BasicBuildGameInitializerFactory gameInitializationInterfaceFactoryInterface,
        boolean isContinue)
        throws Exception
    {
        super(commandListener);

        //Give time for initialization of demogame by default
        this.setWait(LOAD_WAIT);
        
        this.gameInitializationInterfaceFactoryInterface =
            gameInitializationInterfaceFactoryInterface;

        GameInitializationUtil.getInstance().initDemo(
            this, gameInitializationInterfaceFactoryInterface);

        ResizableListenerHandler.getInstance().fireEvent(false);

        this.overlayPaintable = overlayPaintable;

        //PreLogUtil.put("New Demo Canvas", this, CommonStrings.getInstance().CONSTRUCTOR);

        this.highScoresFactoryInterface = highScoresFactoryInterface;

        this.setDefaultPaintableInterface(paintable);
        this.setPaintableInterface(this.getDefaultPaintableInterface());

        if (isContinue)
        {
            this.addCommand(GameCommandsFactory.getInstance().CONTINUE_COMMAND);
        }

        if (ChangedGameFeatureListener.getInstance().isChanged(
            GameFeatureFactory.getInstance().SOUND))
        {
            this.mediaInit();
        }

        this.demoGameRunnable = new DemoGameStartupRunnable(this);
        
        DisplayChangeEventHandler.getInstance().addListener(this);
    }

    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
    }
    
    public void onDisplayChangeEvent(DisplayChangeEvent displayChangeEvent)
    {
        try
        {
            //MyFont.getInstance().update();

            LogUtil.put(LogFactory.getInstance(new StringMaker().append(commonLabels.START_LABEL).append(DisplayInfoSingleton.getInstance().toString()).append(MyFont.getInstance().toString()).toString(), this, "onDisplayChangeEvent"));

            final ScrollSelectionForm scrollSelectionForm = this.getMenuForm();
            
            if(scrollSelectionForm != null)
            {
                final FormType formType = FormTypeFactory.getInstance().getFormType();
                final Rectangle rectangle = FormUtil.getInstance().createFormRectangle();
                scrollSelectionForm.init(rectangle, formType);
            }
            
            this.overlayPaintable.init();
        }
        catch(Exception e) 
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "onDisplayChangeEvent", e));
        }
    }
    
    protected Command[] getCustomCommands()
    {
        final GameCommandsFactory gameCommandsFactory = GameCommandsFactory.getInstance();

        final Features features = Features.getInstance();

        if (features.isDefault(HTMLFeatureFactory.getInstance().HTML))
        {
            //TWB - Removed Options that are not HTML5 capable yet
            final Command[] commandArray =
            {
                gameCommandsFactory.START_COMMAND,
                HighScoreCommands.getInstance().DISPLAY,
                GameInputMappingCanvas.DISPLAY,
                gameCommandsFactory.DISPLAY_ABOUT
            };

            return commandArray;
        }
        else
        {
            final BasicArrayList commandList = new BasicArrayList();
            
            commandList.add(gameCommandsFactory.START_COMMAND);
            
            final InApplicationPurchaseFactory inApplicationPurchaseFactory =
                    InApplicationPurchaseFactory.getInstance();
            
            if (inApplicationPurchaseFactory.isEnabled()) {
                final BasicArrayList list = LockableFeatureFactory.getInstance().getList();

                if (list.size() > 0 && !inApplicationPurchaseFactory.isPurchased((LockableFeature) list.get(0))) {
                    commandList.add(gameCommandsFactory.BUY_COMMAND);
                }
            }

            commandList.add(HighScoreCommands.getInstance().DISPLAY);

            //Has nothing to do with overscan/ouya just hiding issues with the UI
            try
            {
                if (!OperatingSystemFactory.getInstance().getOperatingSystemInstance().isOverScan()) {
                    commandList.add(gameCommandsFactory.DISPLAY_OPTIONS);
                    commandList.add(gameCommandsFactory.DISPLAY_LOAD_FORM);
                    commandList.add(GameInputMappingCanvas.DISPLAY);
                }
            }catch(Exception e)
            {
                
            }
            
            commandList.add(gameCommandsFactory.DISPLAY_ABOUT);
            
            final Command[] commandArray = (Command[])
                    commandList.toArray(new Command[commandList.size()]);

            return commandArray;
        }
    }

    public void initCommands(CommandListener cmdListener)
    {
        this.removeAllCommands();

        Command[] commandArray = getCustomCommands();

        int size = commandArray.length;
        for (int index = 0; index < size; index++)
        {
            this.addCommand(commandArray[index]);
        }

        // this.addCommand(GameCommands.SELECTCOMMAND);

        CustomGameMenuUtil.add(this);

        this.setCommandListener(cmdListener);
    }

    public void initPostPaint()
        throws Exception
    {
    }

    public void mediaInit() throws Exception
    {
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "mediaInit"));
        AllBinaryMediaManager.init(EarlySoundsFactory.getInstance());
    }

    public void itemStateChanged(Item item)
    {
        ForcedLogUtil.log(CommonStrings.getInstance().NOT_IMPLEMENTED, this);
    }
    
    protected void initMenu()
        throws Exception
    {
        this.close();
        
        final CommandTextItemArrayFactory commandTextItemArrayFactory = 
            DemoLimitedCommandTextItemArrayFactory.getInstance().getCommandTextItemArrayFactory();
        
        final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();
        
        final CustomItem[] items = commandTextItemArrayFactory.getInstance(
            this.getCommandStack(), 
            basicColorFactory.BLACK, basicColorFactory.WHITE);

        final FormType formType = FormTypeFactory.getInstance().getFormType();
        
        final Rectangle rectangle = FormUtil.getInstance().createFormRectangle();

        PreLogUtil.put(new StringMaker().append(commonLabels.START_LABEL).append(DisplayInfoSingleton.getInstance().toString()).toString(), this, "initMenu");
        
        final ScrollSelectionForm scrollSelectionForm = CommandCurrentSelectionFormFactory.getInstance(
                    StringUtil.getInstance().EMPTY_STRING, 
                    items, rectangle, formType, 15, true,
                    basicColorFactory.BLACK, basicColorFactory.WHITE);
        this.setMenuForm(scrollSelectionForm);
        
        final FormType formType2 = FormTypeFactory.getInstance().getFormType();
        final Rectangle rectangle2 = FormUtil.getInstance().createFormRectangle();
        scrollSelectionForm.init(rectangle2, formType2);

        //this.setMenuInputProcessor(new DemoCanvasBasicStartInputProcessor(new BasicArrayList(), this));

        if(this.getMenuForm() != ScrollSelectionFormNoneFactory.getInstance())
        {
            this.setMenuInputProcessor(
                    new CommandFormInputProcessor(
                    new BasicArrayList(), -1, this, this.getMenuForm()));
        }

        this.open();
    }
    
    public void open()
    {
        BasicMotionGesturesHandler.getInstance().addListener(this.getMenuInputProcessor());
        GameKeyEventHandler.getInstance().addListener(this.getMenuInputProcessor());
    }

    public void close()
    {
        BasicMotionGesturesHandler.getInstance().removeListener(this.getMenuInputProcessor());
        GameKeyEventHandler.getInstance().removeListener(this.getMenuInputProcessor());
    }
    
    private static final int id = 0;

    public int getSourceId()
    {
        return id;
    }

    public void keyPressed(int keyCode)
    {
        this.keyPressed(keyCode, 0);
    }
    
    public void keyReleased(int keyCode)
    {
        this.keyReleased(keyCode, 0);
    }

    public void keyRepeated(int keyCode)
    {
        this.keyRepeated(keyCode, 0);
    }
    
    public void keyPressed(int keyCode, int deviceId)
    {
        // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "keyPressed"));
        this.addGameKeyEvent(keyCode, false);
    }

    public void keyReleased(int keyCode, int deviceId)
    {
        // LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "keyReleased"));
        this.removeGameKeyEvent(keyCode, false);
    }
    private boolean isSingleKeyRepeatableProcessing =
        Features.getInstance().isFeature(InputFeatureFactory.getInstance().SINGLE_KEY_REPEAT_PRESS);

    public void keyRepeated(int keyCode, int deviceId)
    {
        // LogUtil.put(LogFactory.getInstance("Key Repeated: " +
        // Integer.toHexString(keyCode),
        // this, "keyRepeated"));
        if (this.isSingleKeyRepeatableProcessing)
        {
            this.addGameKeyEvent(keyCode, true);
        }
    }
    private final String NO_KEY = "Key Code Not Mapped For Game: ";
    private final String ADD_KEY_EVENT = "addGameKeyEvent";
    private final String REMOVE_KEY_EVENT = "removeGameKeyEvent";

    private final GameKey NONE = GameKeyFactory.getInstance().NONE;
    
    private final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
    
    private void addGameKeyEvent(int keyCode, boolean repeated)
    {
        try
        {
        	//LogUtil.put(LogFactory.getInstance("Key Code (Hex): ").append(Integer.toHexString(keyCode), this, "addGameKeyEvent"));

            GameKey gameKey = this.inputToGameKeyMapping.getInstance(this, keyCode);

            //LogUtil.put(LogFactory.getInstance("GameKey: ").append(gameKey, this, "addGameKeyEvent"));

            if (gameKey != NONE)
            {
                GameKeyEvent gameKeyEvent = gameKeyEventFactory.getInstance(this, gameKey);
                /*
                 * //This is for key input debugging only GameKeyEvent
                 * gameKeyEvent = GameKeyEventFactory.getInstance(this, keyCode,
                 * gameActionKeyCode, gameKey.getKey(), repeated);
                 * LogUtil.put(LogFactory.getInstance(gameKeyEvent.toString(),
                 * this, "GameKeyEvent"));
                 */

                DownGameKeyEventHandler.getInstance().fireEvent(gameKeyEvent);
            }
            else
            {
                LogUtil.put(LogFactory.getInstance(new StringMaker().append(NO_KEY).append(keyCode).toString(), this, ADD_KEY_EVENT));
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Key Event Error", this, ADD_KEY_EVENT, e));
        }
    }

    private void removeGameKeyEvent(int keyCode, boolean repeated)
    {
        try
        {
            // LogUtil.put(LogFactory.getInstance("Key Code: " +
            // Integer.toHexString(keyCode),
            // this, "removeGameKeyEvent"));

            GameKey gameKey = this.inputToGameKeyMapping.getInstance(this, keyCode);

            //LogUtil.put(LogFactory.getInstance("GameKey: ").append(gameKey, this, "removeGameKeyEvent"));

            if (gameKey != NONE)
            {
                GameKeyEvent gameKeyEvent = gameKeyEventFactory.getInstance(this, gameKey);

                /*
                 * //This is for key input debugging only GameKeyEvent
                 * gameKeyEvent = GameKeyEventFactory.getInstance(this, keyCode,
                 * gameActionKeyCode, gameKey.getKey(), repeated);
                 * LogUtil.put(LogFactory.getInstance(gameKeyEvent.toString(),
                 * this, "GameKeyEvent"));
                 */

                // TODO TWB - Remove or improve key input event handling
                UpGameKeyEventHandler.getInstance().fireEvent(gameKeyEvent);
                
                //getPlayerGameInput().onUpGameKeyEvent(gameKeyEvent);
            }
            else
            {
                LogUtil.put(LogFactory.getInstance(new StringMaker().append(NO_KEY).append(keyCode).toString(), this, REMOVE_KEY_EVENT));
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Key Event Error", this, REMOVE_KEY_EVENT, e));
        }
    }

    public synchronized void pause()
    {
        this.close();

        this.setPaused(true);
        this.gameCanvas.pause();
    }

    public synchronized void unPause()
    {
        this.open();

        this.setPaused(false);
        this.gameCanvas.unPause();
    }

    public boolean isPausable()
    {
        //TWB - Game is paused but UsedRunnable was set after the old runnable was called
        if (CurrentDisplayableFactory.getInstance().getUsedRunnable() == NullGameRunnable.getInstance()) {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean isGameOver()
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(CommonStrings.getInstance().NOT_IMPLEMENTED).append(" since not a game").toString(), this, "isGameOver"));
        return false;
    }

    public void setLoadStateHashtable(Hashtable hashtable) throws Exception
    {
        LogUtil.put(LogFactory.getInstance(
            "Trying to continue a demo lol - only continue a game canvas not the demo",
            this, "setLoadStateHashtable"));
    }

    public Hashtable getLoadStateHashtable() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(
            "Trying to continue a demo lol - only continue a game canvas not the demo",
            this, "getLoadStateHashtable"));
        return new Hashtable();
    }

    public Hashtable getCurrentStateHashtable() throws Exception
    {
        LogUtil.put(LogFactory.getInstance("Trying to save the AI lol", this, "getCurrentStateHashtable"));
        return new Hashtable();
    }

    public void setHighScoreSubmitted(boolean isNotUsed)
    {
    }

    public void paint(Graphics graphics)
    {
        //PreLogUtil.put("DemoCanvas", this, "paint");
        
        // Draw Game
        this.paintableInterface.paint(graphics);
        
        // Draw title animation
        this.paintedSpecialAnimationInterface.paint(graphics, 0, 0);
        
        //paints the high scores when in that state
        this.highScoresPaintable.paint(graphics);

        //Draw help or menu/ownership or empty
        //usually the game menu

        this.getBasicGameDemoPaintable().paint(graphics);

        this.overlayPaintable.paint(graphics);
    }

    public void paintThreed(Graphics graphics)
    {
        this.paintableInterface.paintThreed(graphics);
        
        // Draw title animation
        this.paintedSpecialAnimationInterface.paintThreed(graphics, 0, 0, 0);
        
        //TWB - More 3d
    }
    
    public synchronized void setGameOver()
    {
        LogUtil.put(LogFactory.getInstance("Not Implemented since not a game", this, "setGameOver"));
    }
    
    protected void demoStateChange()
    {
        int newState = this.state + 1;

        if (newState > 2)
        {
            newState = 0;
        }
        else
        if (newState == 2)
        {
            //skip HighScores State it none exists
            if(!this.highScoresHelper.isAnyHighScores())
            {
                newState = 0;
            }
            else
            {
                this.getRealHighScoresPaintable().setHighScores(
                        this.highScoresHelper.getSelectHighScores());
            }
        }

        this.setState(newState);
        
        this.setState();
    }

    private final String SET_STATE = "setState";
    
    protected void setState()
    {
        PreLogUtil.put(SmallIntegerSingletonFactory.getInstance().createInstance(this.state).toString(), this, SET_STATE);
        //LogUtil.put(LogFactory.getInstance("Current Demo State: ").append(this.getState(), this, SET_STATE));

        this.getBasicGameDemoPaintable().setState(this.state);

        if (this.state == 0)
        {
            // Title and menu
            this.highScoresPaintable = NullPaintable.getInstance();

            this.paintedSpecialAnimationInterface = this.getSpecialAnimationInterface();

            if (!this.demoGameRunnable.isRunning() && gameCanvas.isInitialized())
            {
                //PreLogUtil.put("Reset", this, CommonStrings.getInstance().RUN);
                this.getSpecialAnimationInterface().reset();
            }
            
            //PreLogUtil.put("isComplete: ").append(this.getSpecialAnimationInterface().isComplete(), this, CommonStrings.getInstance().RUN);
        }
        else if (this.state == 1)
        {
            // Help
            this.paintedSpecialAnimationInterface = SpecialAnimation.getInstance();
        }
        else if (this.state == 2)
        {
            this.highScoresPaintable = this.getRealHighScoresPaintable();
        }
        
        GameAdState gameAdState = GameAdStateFactory.getInstance().getCurrentInstance();
        gameAdState.processPageAdState();
    }

    protected int getNextRandom() throws Exception
    {
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }

    protected AllBinaryGameLayerManager createGameLayerManager(int randomValue)
        throws Exception
    {
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }

    protected GameCanvasRunnableInterface createRunnable(int randomLevel)
        throws Exception
    {
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }

    protected void create() throws Exception
    {
        //PreLogUtil.put(CommonStrings.getInstance().START, this, "create");

        this.highScoresPaintable = NullPaintable.getInstance();

        int randomLevel = this.getNextRandom();

        this.gameCanvas = (AllBinaryGameCanvas) this.createRunnable(randomLevel);

        this.basicColor =
            this.gameCanvas.getLayerManager().getForegroundBasicColor();
        this.getRealHighScoresPaintable().setBasicColor(this.basicColor);
        
        this.gameCanvas.setGameCanvasStartListener(this);
        
        SecondaryThreadPool.getInstance().runTask(new HighScoresUpdateRunnable(this));
    }

    public void setHighScores() throws Exception
    {
       GameInfo gameInfo = this.gameCanvas.getLayerManager().getGameInfo();

       HighScores[] highScoresArray =
               this.getHighScoresFactoryInterface().createHighScores(gameInfo);

       this.highScoresHelper.setHighScoresArray(highScoresArray);
    }

    protected void start() throws Exception
    {
        PreLogUtil.put("Game Thread in DemoCanvas", this, CommonStrings.getInstance().START);

        this.canvasThread = ThreadFactoryUtil.getInstance().getInstance(this.gameCanvas);
        this.gameCanvas.setThread(canvasThread);

        //PreLogUtil.put("Game Thread Priority: ").append(
        //      canvasThread.getPriority(), this, CommonStrings.getInstance());
        //LogUtil.put(LogFactory.getInstance(
        //      "Game Thread Priority: ").append(canvasThread.getPriority(), this, CommonStrings.getInstance()));

        this.canvasThread.start();

        if(this.getWait() == LOAD_WAIT)
        {
            this.setWait(this.getTempWait());
        }

        //PreLogUtil.put(CommonStrings.getInstance().END, this, CommonStrings.getInstance());
    }

    public void preDemoProcess()
    {
        if (!gameCanvas.isInitialized() ||
                gameCanvas.getTitle() == NullGameCanvas.NO_GAME)
        {
            if (AllBinaryMediaManager.update())
            {
                if (!PrimaryPlayerQueueFactory.getInstance().process())
                {
                    SecondaryPlayerQueueFactory.getInstance().process();
                }
            }
        }

        this.overlayPaintable.update();
    }

    public void process() throws Exception
    {
        this.getMenuInputProcessor().processInput();

        this.preDemoProcess();

        if (this.state == 0)
        {
            //Don't allow the time of the animation to count towards the state time.
            if (this.getSpecialAnimationInterface().getLoopCount() < 1)
            {
                timeDelayHelper.setStartTime();
            }

            DemoGameMidlet demoGameMidlet =
                    (DemoGameMidlet) this.getCustomCommandListener();

            if (this.gameCanvas != NullGameCanvas.getInstance()
                && this.gameCanvas.isGameOver())
            {
                //PreLogUtil.put("Restarting Game Demo", this, CommonStrings.getInstance().PROCESS);

                this.stopGameDemo();

                int randomLevel = this.getNextRandom();
                
                //PreLogUtil.put("Restarting Game Demo at Level: ").append(randomLevel, this, CommonStrings.getInstance().PROCESS);
                
                GameInfo gameInfo =
                    this.gameCanvas.getLayerManager().getGameInfo();

                gameInfo.setCurrentLevel(randomLevel);

                this.gameCanvas.setGameOver(false);

                this.start();
            }
            else if (this.gameCanvas == NullGameCanvas.getInstance()
                && demoGameMidlet.isReady())
            {
                if (!demoGameRunnable.isRunning())
                {
                    //LogUtil.put(LogFactory.getInstance("Starting Game Demo", this, CommonStrings.getInstance().PROCESS));
                    //PreLogUtil.put("Starting Game Demo", this, CommonStrings.getInstance().PROCESS);

                    this.startDemoGame();
                    
                    demoGameRunnable.setRunning(true);
                    Thread thread = ThreadFactoryUtil.getInstance().getInstance(demoGameRunnable);
                    demoGameRunnable.setThread(thread);
                    //I guess that setting a thread priority sets threads created by a thread to the same priority
                    //Don't un-remark thread.setPriority(Thread.MIN_PRIORITY);
                    thread.start();
                }
            }
        }
    }

    //Override to Show Background progress when demogame is loading
    protected final void startDemoGame() throws Exception
    {
        //Show Background progress when demogame is loading
        DemoCanvasProgressUtil.showProgress(this);
    }
    
    protected void stopGameDemo() throws Exception
    {
        if (this.gameCanvas != NullGameCanvas.getInstance())
        {
            //PreLogUtil.put("Set Running False", this, "stopGameDemo");
            LogUtil.put(LogFactory.getInstance("Set Running False", this, "stopGameDemo"));
            this.gameCanvas.setRunning(false);
        }

        ThreadUtil.getInstance().join(this.canvasThread);
    }

    protected void showGamePaintable()
    {
        final String METHOD_NAME = "showGamePaintable";
        
        PreLogUtil.put(CommonStrings.getInstance().START, this, METHOD_NAME);
        
        Features features = Features.getInstance();
        
        if (this.gameCanvas != NullGameCanvas.getInstance() && 
                (this.gameCanvas.isRunning() || 
                features.isDefault(HTMLFeatureFactory.getInstance().HTML))
                && !(this.gameCanvas instanceof NullGameCanvas)
                )
        {
            this.gameRunnable = new GameCanvasRunnable(this.gameCanvas);
            PreLogUtil.put("Showing Game", this, METHOD_NAME);
            this.setPaintableInterface(this.gameCanvas);
        }
        else
        {
            this.gameRunnable = NullGameRunnable.getInstance();
            PreLogUtil.put("Not Showing Game", this, METHOD_NAME);
            this.setPaintableInterface(this.getDefaultPaintableInterface());
        }
    }

    protected boolean isReadyForStateChange()
    {
        return !this.demoGameRunnable.isRunning() && gameCanvas.isInitialized();
    }
    
    protected void processGame() throws Exception
    {
        //PreLogUtil.put(CommonStrings.getInstance().START, this, "processGame");
        
        this.gameRunnable.run();

        //if(runningTimeDelayHelper.isTime())
        //{
            //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().RUNNING, this, CommonStrings.getInstance().RUN));
        //}

        //Viewer Game is initialized and and
        //&& gameCanvasRunnableInterface.isInitialized()
        //If animation is not completed
        
        if (!this.specialAnimationInterface.isComplete() && this.isReadyForStateChange())
        {
            this.specialAnimationInterface.nextFrame();
        }

        // || this.circularIndexUtil.getSize() > 0
        
        if (timeDelayHelper.isTime() && this.isReadyForStateChange())
        {
            this.demoStateChange();
        }
        else
        {
            // allow title animation to finish before counting down for
            // next screen and starting a new demo game
            this.process();
        }
    }
    
    public void run()
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_RUNNABLE, this, CommonStrings.getInstance().RUN));

        try
        {
            ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();

            progressCanvas.addPortion(50, "Demo Thread");

            this.setCurrentThread();
            this.setRunning(true);

            //this.process();

            Features features = Features.getInstance();
            
            if (features.isFeature(MainFeatureFactory.getInstance().LOAD_ONDEMAND))
            {
                progressCanvas.end();
            }
            else
            {
                progressCanvas.addPortion(50, "Demo Thread Running");
            }

            fullScreenUtil.init(this, this.getCustomCommandListener());

            this.initMenu();
            this.initPostPaint();
            this.setState();

            //final TimeDelayHelper runningTimeDelayHelper = new TimeDelayHelper(12000);
            
            if (features.isDefault(OpenGLFeatureFactory.getInstance().OPENGL_AS_GAME_THREAD))
            {
                //PreLogUtil.put(CommonStrings.getInstance().START, this, "OPENGL_AS_GAME_THREAD");
                
                //Process as 2 threads until initialized - allows progress to update
                while (gameCanvas == NullGameCanvas.getInstance() || !gameCanvas.isInitialized())
                {
                    this.getLoopTimeHelper().setStartTime();

                    this.processGame();

                    this.processLoopSleep();
                }
                
                final DemoGameRunnable gameRunnable = new DemoGameRunnable(this);
                
                final CurrentDisplayableFactory currentDisplayableFactory = CurrentDisplayableFactory.getInstance();
                
                currentDisplayableFactory.setRunnable(gameRunnable);
                //Only needed is not really using a real gamecanvas
                OpenGLThreadUtil.getInstance().onResume();
            }

            if (features.isDefault(OpenGLFeatureFactory.getInstance().OPENGL_AS_GAME_THREAD) ||
                    features.isDefault(HTMLFeatureFactory.getInstance().HTML))
            {
                //PreLogUtil.put(CommonStrings.getInstance().START, this, "OPENGL_AS_GAME_THREAD 2");

                final DemoGameRunnable demoGameRunnable = new DemoGameRunnable(this);
                
                final CurrentDisplayableFactory currentDisplayableFactory = 
                        CurrentDisplayableFactory.getInstance();
                
                currentDisplayableFactory.setRunnable(demoGameRunnable);
            }
            else
            {
                //PreLogUtil.put(CommonStrings.getInstance().START, this, "Starting Run Loop");
                
                while (this.isRunning())
                {
                    this.getLoopTimeHelper().setStartTime();
                    
                    this.processGame();

                    this.processLoopSleep();
                }
                
                this.end();
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().RUN, e));
        }

        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().END_RUNNABLE, this, CommonStrings.getInstance().RUN));
    }

    public void setRunning(boolean running) 
    {
        super.setRunning(running);

        try
        {
            Features features = Features.getInstance();
            
            //If game thread is not actually running
            if ((features.isDefault(OpenGLFeatureFactory.getInstance().OPENGL) ||
                    features.isDefault(HTMLFeatureFactory.getInstance().HTML))
                    && !running)
            {
                final CurrentDisplayableFactory currentDisplayableFactory = CurrentDisplayableFactory.getInstance();
                currentDisplayableFactory.clearRunnable();
                this.end();
            }
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, SET_RUNNING, e));
        }        
    }
    
    private static final String BOT_GAME_STATS = "Bot Game Statistics: ";
    private final BaseGameStatistics baseGameStatistics = 
            GameStatisticsFactory.getInstance();

    public void end() throws Exception
    {
        ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
        Features features = Features.getInstance();
        
        AllGameStatisticsFactory allGameStatisticsFactory = AllGameStatisticsFactory.getInstance();
        allGameStatisticsFactory.add(new StringMaker().append(BOT_GAME_STATS).append(baseGameStatistics.toString()).append(CommonSeps.getInstance().NEW_LINE).toString());
        baseGameStatistics.init();
        
        if (features.isFeature(MainFeatureFactory.getInstance().LOAD_ONDEMAND))
        {
            progressCanvas.start();
        }

        LogUtil.put(LogFactory.getInstance("Demo End", this, CommonStrings.getInstance().RUN));

        this.close();
        DisplayChangeEventHandler.getInstance().removeListener(this);

        this.stopGameDemo();
    }
    
    public void setGameState(GameState gameState)
    {
        
    }
    
    public GameState getGameState()
    {
        return GameState.PLAYING_GAME_STATE;
    }

    public AllBinaryGameCanvas getGameCanvasRunnableInterface()
    {
        return gameCanvas;
    }

    protected int getState()
    {
        return state;
    }

    protected void setState(int state)
    {
        this.state = state;
    }

    public boolean isHighScoreSubmitted()
    {
        // Don't Submit AI Score Since That Is Stupidy
        LogUtil.put(LogFactory.getInstance("Wow the AI got a high score!", this, "isHighScoreSubmitted"));
        return false;
    }

    /*
    public void setRealHighScoresPaintable(HighScoresPaintable realHighScoresPaintable)
    {
    this.realHighScoresPaintable = realHighScoresPaintable;
    }
     */
    public HighScoresPaintable getRealHighScoresPaintable()
    {
        return realHighScoresPaintable;
    }

    protected void setSpecialAnimationInterface(
        SpecialAnimation specialAnimationInterface)
    {
        specialAnimationInterface.setFrame(0);
        this.specialAnimationInterface = specialAnimationInterface;
    }

    protected SpecialAnimation getSpecialAnimationInterface()
    {
        return specialAnimationInterface;
    }

    protected void setPaintableInterface(PaintableInterface paintableInterface)
    {
        this.paintableInterface = paintableInterface;
    }

    protected PaintableInterface getPaintableInterface()
    {
        return paintableInterface;
    }

    protected void setDefaultPaintableInterface(
        Paintable defaultPaintableInterface)
    {
        this.defaultPaintableInterface = defaultPaintableInterface;
    }

    protected Paintable getDefaultPaintableInterface()
    {
        return defaultPaintableInterface;
    }

    public HighScoresFactoryInterface getHighScoresFactoryInterface()
    {
        return highScoresFactoryInterface;
    }

    protected void setMenuInputProcessor(BasicMenuInputProcessor menuInputProcessor)
    {
        this.menuInputProcessor = menuInputProcessor;
    }

    protected BasicMenuInputProcessor getMenuInputProcessor()
    {
        return menuInputProcessor;
    }

    /**
     * @return the menuForm
     */
    public ScrollSelectionForm getMenuForm()
    {
        return menuForm;
    }

    /**
     * @param menuForm the menuForm to set
     */
    public void setMenuForm(ScrollSelectionForm menuForm)
    {
        this.menuForm = menuForm;
    }

    /*
    private void setInitialized(boolean initialized)
    {
    this.initialized = initialized;
    }
     */
    public boolean isInitialized()
    {
        return initialized;
    }

    protected Paintable getOverlayPaintable()
    {
        return overlayPaintable;
    }

    protected void setBasicGameDemoPaintable(StatePaintable basicGameDemoPaintable)
    {
        this.basicGameDemoPaintable = basicGameDemoPaintable;
    }

    protected StatePaintable getBasicGameDemoPaintable()
    {
        return basicGameDemoPaintable;
    }

    public void setTempWait(int tempWait)
    {
        this.tempWait = tempWait;
    }

    public int getTempWait()
    {
        return tempWait;
    }

   /**
    * @return the gameInitializationInterfaceFactoryInterface
    */
   public BasicBuildGameInitializerFactory getGameInitializationInterfaceFactoryInterface()
   {
      return gameInitializationInterfaceFactoryInterface;
   }
}
