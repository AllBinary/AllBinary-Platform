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

import java.util.Hashtable;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Item;

import org.allbinary.business.advertisement.GameAdStateFactory;
import org.allbinary.game.GameAdState;
import org.allbinary.game.resource.ResourceLoadingLevel;
import org.allbinary.game.resource.ResourceLoadingLevelFactory;
import org.allbinary.graphics.opengles.CurrentDisplayableFactory;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.graphics.opengles.OpenGLFeatureUtil;
import org.allbinary.input.gyro.SensorGameUpdateProcessor;
import org.allbinary.input.gyro.SingleSensorGameUpdateProcessor;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.CircularIndexUtil;

import abcs.logic.basic.NotImplemented;
import abcs.logic.basic.string.CommonSeps;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.ForcedLogUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.canvas.AllGameStatisticsFactory;
import allbinary.canvas.BaseGameStatistics;
import allbinary.canvas.GameStatisticsFactory;
import allbinary.canvas.Processor;
import allbinary.canvas.RunnableCanvas;
import allbinary.debug.DebugFactory;
import allbinary.debug.NoDebug;
import allbinary.game.GameInfo;
import allbinary.game.GameTypeFactory;
import allbinary.game.Intermission;
import allbinary.game.IntermissionCompositeInterface;
import allbinary.game.IntermissionEnableListenerInterface;
import allbinary.game.IntermissionInterface;
import allbinary.game.commands.GameCommandsFactory;
import allbinary.game.configuration.InGameFeatures;
import allbinary.game.configuration.InGameOptionsForm;
import allbinary.game.configuration.event.ChangedGameFeatureListener;
import allbinary.game.configuration.event.GameInitializedEvent;
import allbinary.game.configuration.event.GameInitializedEventHandler;
import allbinary.game.configuration.feature.Features;
import allbinary.game.configuration.feature.GameFeatureFactory;
import allbinary.game.configuration.feature.GameFeatureUtil;
import allbinary.game.configuration.feature.InputFeatureFactory;
import allbinary.game.configuration.feature.MainFeatureFactory;
import allbinary.game.configuration.feature.SensorFeatureFactory;
import allbinary.game.configuration.feature.TouchFeatureFactory;
import allbinary.game.displayable.GameLevelDisplayChangeEventListenersFactory;
import allbinary.game.init.BasicBuildGameInitializerFactory;
import allbinary.game.init.GameInitializationUtil;
import allbinary.game.input.GameKey;
import allbinary.game.input.GameKeyFactory;
import allbinary.game.input.InputProcessor;
import allbinary.game.input.NoPlayerGameInput;
import allbinary.game.input.PlatformInputMappingFactory;
import allbinary.game.input.PlayerGameInput;
import allbinary.game.input.event.GameKeyEvent;
import allbinary.game.input.event.GameKeyEventFactory;
import allbinary.game.input.event.GameKeyEventHandler;
import allbinary.game.input.event.UpGameKeyEventHandler;
import allbinary.game.input.mapping.InputToGameKeyMapping;
import allbinary.game.layer.AllBinaryGameLayerManager;
import allbinary.game.paint.ColorFillPaintable;
import allbinary.game.paint.ColorFillPaintableFactory;
import allbinary.game.score.HighScore;
import allbinary.game.score.HighScores;
import allbinary.game.score.HighScoresCompositeInterface;
import allbinary.game.score.HighScoresFactoryInterface;
import allbinary.game.score.HighScoresPaintable;
import allbinary.game.score.HighScoresUpdateRunnable;
import allbinary.game.score.displayable.HighScoreTextBox;
import allbinary.game.state.GameState;
import allbinary.game.state.GameStateFactory;
import allbinary.graphics.Rectangle;
import allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import allbinary.graphics.color.BasicColorFactory;
import allbinary.graphics.color.BasicColorSetUtil;
import allbinary.graphics.displayable.command.MyCommandsFactory;
import allbinary.graphics.displayable.event.DisplayChangeEvent;
import allbinary.graphics.displayable.event.DisplayChangeEventHandler;
import allbinary.graphics.displayable.event.DisplayChangeEventListener;
import allbinary.graphics.form.CommandCurrentSelectionFormFactory;
import allbinary.graphics.form.FormPaintable;
import allbinary.graphics.form.FormType;
import allbinary.graphics.form.FormTypeFactory;
import allbinary.graphics.form.ScrollSelectionForm;
import allbinary.graphics.form.ScrollSelectionFormNoneFactory;
import allbinary.graphics.form.item.CommandTextItemArrayFactory;
import allbinary.graphics.form.item.CustomItem;
import allbinary.graphics.paint.InitUpdatePaintable;
import allbinary.graphics.paint.NullPaintable;
import allbinary.graphics.paint.Paintable;
import allbinary.input.motion.button.BaseTouchInput;
import allbinary.input.motion.button.TouchButtonFactory;
import allbinary.input.motion.button.TouchButtonsPaintableFactory;
import allbinary.input.motion.button.TouchScreenFactory;
import allbinary.input.motion.gesture.observer.BasicMotionGesturesHandler;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;
import allbinary.media.audio.AllBinaryMediaManager;
import allbinary.media.audio.PlayerQueue;
import allbinary.media.audio.PrimaryPlayerQueueFactory;
import allbinary.media.audio.SecondaryPlayerQueueFactory;
import allbinary.media.audio.SelectSound;
import allbinary.thread.SecondaryThreadPool;
import allbinary.time.GameTickTimeDelayHelperFactory;
import allbinary.time.TimeDelayHelper;

public class AllBinaryGameCanvas 
extends RunnableCanvas 
implements AllBinaryGameCanvasInterface, GameCanvasRunnableInterface,
        MenuListener, IntermissionCompositeInterface,
        IntermissionEnableListenerInterface, PopupMenuInterface,
        HighScoresCompositeInterface, DisplayChangeEventListener
{
    protected Paintable gameSpecificPaintable = NullPaintable.getInstance();

    private final BasicColorSetUtil basicColorUtil = BasicColorSetUtil.getInstance();

    private final SensorGameUpdateProcessor sensorGameUpdateProcessor = new SingleSensorGameUpdateProcessor();
    private EndGameInfo endGameInfo = new EndGameInfo();
    private final IntermissionInterface startIntermissionInterface = new Intermission();
    private final IntermissionInterface endLevelIntermissionInterface = new Intermission();
    private static final int id = 0;
    private AllBinaryGameLayerManager gameLayerManager;
    private GameState gameState;
    private boolean gameOver;
    public static final GameState SHOW_END_RESULT_GAME_STATE = 
        GameStateFactory.getInstance("SHOW_END_RESULT_GAME_STATE");
    public static final GameState SHOW_HIGH_SCORE_GAME_STATE = 
        GameStateFactory.getInstance("SHOW_HIGH_SCORE_GAME_STATE");
    private boolean initialized;
    // graphic constants
    // private final int BORDER = 3;
    // Time Delayed repsonse times
    private final TimeDelayHelper gameStateTimeHelper = new TimeDelayHelper(0);

    // high score vars
    private HighScoreTextBox textBox;
    private boolean highScoreSubmitted;
    private final CircularIndexUtil circularIndexUtil = CircularIndexUtil.getInstance(0, 0);
    private HighScores selectedHighScores;
    private HighScores[] highScoresArray;
    private final HighScoresPaintable realHighScoresPaintable = new HighScoresPaintable();
    private Paintable highScoresPaintable = NullPaintable.getInstance();
    // TODO TWB hack around key event handling performance
    private PlayerGameInput playerGameInput = NoPlayerGameInput.getInstance();
    private boolean isCheating;
    private Hashtable hashtable;
    private boolean isSingleKeyRepeatableProcessing;
    private BasicBuildGameInitializerFactory gameInitializationInterfaceFactoryInterface;
    private Paintable touchButtonsPaintable = NullPaintable.getInstance();
    private Paintable touchPaintable;
    private PlayerGameInput cheatProcessor;
    private Processor gameInputProcessor;
    private Processor endGameProcessor;
    private Processor realEndGameProcessor;
    private Processor startIntermissionProcessor;
    private Processor realStartIntermissionProcessor;
    private Paintable endGamePaintable;
    private Paintable endGameStatePaintable;
    private Paintable nonBotPaintable;
    private Paintable intermissionPaintable;
    // protected Paintable startIntermissionPaintable;
    private InitUpdatePaintable startIntermissionPaintable;
    private Processor mainStateProcessor = Processor.getInstance();
    private Processor processGameProcessor = Processor.getInstance();
    private final HighScoresFactoryInterface highScoresFactoryInterface;
    private int startLevel;
    // Menu
    private BasicMenuInputProcessor mainMenuInputProcessor = 
        NoMenuInputProcessor.getInstance();
    private BasicMenuInputProcessor popupMenuInputProcessor = 
        NoMenuInputProcessor.getInstance();
    
    private BasicMenuInputProcessor menuInputProcessor = 
        NoMenuInputProcessor.getInstance();

    private ScrollSelectionForm menuForm;
    private Paintable formPaintable = NullPaintable.getInstance();
    private Paintable openMenuPaintable = NullPaintable.getInstance();
    private Paintable menuPaintable = NullPaintable.getInstance();

    private BaseTouchInput currentTouchInputFactory;

    protected ColorFillPaintable colorFillPaintable = 
        ColorFillPaintableFactory.getInstance(
                BasicColorFactory.getInstance().BLACK);

    private final BaseGameStatistics baseGameStatistics = 
            GameStatisticsFactory.getInstance();
    
    private final PlayerQueue primaryPlayerQueue = 
        PrimaryPlayerQueueFactory.getInstance();

    private final PlayerQueue secondaryPlayerQueue = 
        SecondaryPlayerQueueFactory.getInstance();
    
    protected final String BUILD_GAME = "buildGame";
    
    protected final GameTypeFactory gameTypeFactory = GameTypeFactory.getInstance();
    
    public AllBinaryGameCanvas(
            CommandListener commandListener,
            AllBinaryGameLayerManager gameLayerManager,
            HighScoresFactoryInterface highScoresFactoryInterface,
            BasicBuildGameInitializerFactory gameInitializationInterfaceFactoryInterface,
            boolean buffered) throws Exception
    {
        super(commandListener);
        
        this.gameInitializationInterfaceFactoryInterface = gameInitializationInterfaceFactoryInterface;
        this.init(gameLayerManager, buffered);

        this.highScoresFactoryInterface = highScoresFactoryInterface;

        this.initSpecialPaint();

        this.initPopupMenu();

        this.initMenu();
        
        DisplayChangeEventHandler.getInstance().addListener(this);
        
        if (this.getLayerManager().getGameInfo().getGameType() != gameTypeFactory.BOT)
        {
            GameAdState gameAdState = 
                GameAdStateFactory.getInstance().getCurrentInstance();

            gameAdState.playingAdState();
        }
    }
    
    //Null GameCanvas
    public AllBinaryGameCanvas(
            AllBinaryGameLayerManager gameLayerManager)
    {
        this.gameLayerManager = gameLayerManager;
        
        this.highScoresFactoryInterface = null;
    }

    public AllBinaryGameCanvas()
    {
        this.highScoresFactoryInterface = null;
    }

    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
    }

    public void onDisplayChangeEvent(DisplayChangeEvent displayChangeEvent)
    {
        try
        {
            //LogUtil.put(LogFactory.getInstance(commonStrings.START_LABEL + DisplayInfoSingleton.getInstance().toString(), this, "onDisplayChangeEvent"));
            //PreLogUtil.put(commonStrings.START_LABEL + DisplayInfoSingleton.getInstance().toString(), this, "onDisplayChangeEvent");

            if (this.gameLayerManager.getGameInfo().getGameType() != gameTypeFactory.BOT)
            {
                Rectangle popupMenuRectangle = FormUtil.getInstance().createPopupMenuRectangle();
                ((BasicPopupMenuPaintable) this.getOpenMenuPaintable()).init(popupMenuRectangle);

                if (this.getPopupMenuInputProcessor() != NoMenuInputProcessor.getInstance())
                {
                    ((PopupMenuInputProcessor) this.getPopupMenuInputProcessor()).init(popupMenuRectangle);
                }

                FormType formType = FormTypeFactory.getInstance().getFormType();
                Rectangle rectangle = FormUtil.getInstance().createFormRectangle();
                this.menuForm.init(rectangle, formType);

                //PreLogUtil.put(this.currentTouchInputFactory.toString(), this, "onDisplayChangeEvent");

                // TouchButtonFactory.getInstance().toggle(this.isPaused(),
                TouchButtonFactory.getInstance().setList(this.currentTouchInputFactory.getList());

                // PreLogUtil.put(TouchButtonLocationHelper.getInstance().toString(), this, "onDisplayChangeEvent");
            }
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "onDisplayChangeEvent", e));
        }
    }

    /*
     * public void setRunning(boolean running) { if (!this.isRunning()) {
     * this.menuInputProcessor = NoMenuInputProcessor.getInstance(); }
     * 
     * super.setRunning(running); }
     */
    public void processSleep() throws Exception
    {
        // LogUtil.put(LogFactory.getInstance(commonStrings.START,
        // this, "processSleep"));

        super.processSleep();

        if (AllBinaryMediaManager.update())
        {
            if (!primaryPlayerQueue.process())
            {
                secondaryPlayerQueue.process();
            }
        }

        if (this.getMenuInputProcessor().processInput() != -1)
        {
            // LogUtil.put(LogFactory.getInstance("Menu Processing While Sleeping",
            // this, "processSleep"));

            // this.processPaintable.process();
            this.runnableCanvasRefreshHelper.process();
        }
    }

    protected void initPopupMenu() throws Exception
    {
        // Popup Menu Tab Init
        Rectangle popupMenuRectangle = FormUtil.getInstance().createPopupMenuRectangle();

        if (Features.getInstance().isFeature(TouchFeatureFactory.getInstance().TOUCH_ENABLED))
        {
            this.setOpenMenuPaintable(new BasicPopupMenuPaintable(
                    popupMenuRectangle, 
                    this.getLayerManager().getBackgroundBasicColor(), 
                    this.getLayerManager().getForegroundBasicColor()));

            this.setPopupMenuInputProcessor(new PopupMenuInputProcessor(
                    new BasicArrayList(), this, popupMenuRectangle));
        }
    }
    
    protected void initMenu()
    {
        try
        {
            if (this.gameLayerManager.getGameInfo().getGameType() != gameTypeFactory.BOT)
            {
                this.closeMenu();

                FormUtil formUtil = FormUtil.getInstance();
                FormType formType = FormTypeFactory.getInstance().getFormType();

                GameLimitedCommandTextItemArrayFactory gameLimitedCommandTextItemArrayFactory = 
                    GameLimitedCommandTextItemArrayFactory.getInstance();

                CommandTextItemArrayFactory commandTextItemArrayFactory = 
                    gameLimitedCommandTextItemArrayFactory.getCommandTextItemArrayFactory();

                CustomItem[] items = commandTextItemArrayFactory.getInstance(
                        this.getCommandStack(), this.getLayerManager()
                                .getBackgroundBasicColor(), this
                                .getLayerManager().getForegroundBasicColor());

                Rectangle rectangle = formUtil.createFormRectangle();

                this.setMenuForm(CommandCurrentSelectionFormFactory.getInstance(
                        StringUtil.getInstance().EMPTY_STRING, 
                        items,
                        rectangle, formType, 25, false, 
                        this.getLayerManager().getBackgroundBasicColor(), 
                        this.getLayerManager().getForegroundBasicColor()));

                /*
                 * this.setMenuInputProcessor( new
                 * DemoCanvasBasicStartInputProcessor( new BasicArrayList(),
                 * this));
                 */

                ScrollSelectionForm scrollSelectionForm = this.getMenuForm();
                
                if (Features.getInstance().isFeature(TouchFeatureFactory.getInstance().TOUCH_ENABLED))
                {
                    this.mainMenuInputProcessor = new PopupCommandFormInputProcessor(
                            new BasicArrayList(), this, scrollSelectionForm,
                            (PopupMenuInputProcessor) this.getPopupMenuInputProcessor());
                }

                this.setMenuInputProcessor(this.getPopupMenuInputProcessor());

                if (scrollSelectionForm != ScrollSelectionFormNoneFactory.getInstance())
                {
                    this.setFormPaintable(new FormPaintable(scrollSelectionForm));
                }

                this.closeMenu();
            }

        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(
                    commonStrings.EXCEPTION, this, "initMenu", e));
        }
    }

    public void updateMenu()
    {
        try
        {
            if (this.getLayerManager().getGameInfo().getGameType() != gameTypeFactory.BOT)
            {
                ScrollSelectionForm scrollSelectionForm = this.getMenuForm();
                
                scrollSelectionForm.deleteAll();

                GameLimitedCommandTextItemArrayFactory gameLimitedCommandTextItemArrayFactory = 
                    GameLimitedCommandTextItemArrayFactory.getInstance();

                CommandTextItemArrayFactory commandTextItemArrayFactory = gameLimitedCommandTextItemArrayFactory
                        .getCommandTextItemArrayFactory();

                CustomItem[] items = commandTextItemArrayFactory.getInstance(
                        this.getCommandStack(), this.getLayerManager()
                                .getBackgroundBasicColor(), this
                                .getLayerManager().getForegroundBasicColor());

                int size = items.length;
                for (int index = 0; index < size; index++)
                {
                    scrollSelectionForm.append(items[index]);
                }

                FormUtil formUtil = FormUtil.getInstance();
                FormType formType = FormTypeFactory.getInstance().getFormType();                
                Rectangle rectangle = formUtil.createFormRectangle();
                scrollSelectionForm.init(rectangle, formType);
            }

        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(
                    commonStrings.EXCEPTION, this, "initMenu", e));
        }

    }

    public synchronized void pause()
    {
    	final String METHOD_NAME = "pause";
    	
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, METHOD_NAME));
        // PreLogUtil.put(commonStrings.START, this, METHOD_NAME);

        if (this.getLayerManager().getGameInfo().getGameType() != gameTypeFactory.BOT)
        {
            if (Features.getInstance().isDefault(
                    OpenGLFeatureFactory.getInstance().OPENGL_AS_GAME_THREAD))
            {
                GameCanvasPauseRunnable gameRunnable = new GameCanvasPauseRunnable(this);

                final CurrentDisplayableFactory currentDisplayableFactory = 
                    CurrentDisplayableFactory.getInstance();

                currentDisplayableFactory.setRunnable(gameRunnable);
            }
        }

        this.closeMenu();

        super.pause();

        TouchButtonFactory.getInstance().toggle(this.isPaused(), null);

        System.gc();

        //LogUtil.put(LogFactory.getInstance(commonStrings.END, this, METHOD_NAME));
    }

    public synchronized void unPause()
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "unPause"));
        // PreLogUtil.put(commonStrings.START, this, "unPause");
        
        this.closeMenu();
        System.gc();
        super.unPause();
        TouchButtonFactory.getInstance().toggle(this.isPaused(), null);
        
        if (this.getLayerManager().getGameInfo().getGameType() != gameTypeFactory.BOT)
        {
        if (Features.getInstance().isDefault(
                OpenGLFeatureFactory.getInstance().OPENGL_AS_GAME_THREAD))
        {
            GameCanvasRunnable gameRunnable = new GameCanvasRunnable(this);

            final CurrentDisplayableFactory currentDisplayableFactory = 
                CurrentDisplayableFactory.getInstance();

            currentDisplayableFactory.setRunnable(gameRunnable);
        }
        }
    }

    public void popupMenu() throws Exception
    {
        if (this.getLayerManager().getGameInfo().getGameType() != gameTypeFactory.BOT)
        {
            primaryPlayerQueue.add(SelectSound.getInstance());

            this.setMenuPaintable(this.getFormPaintable());
            this.setMenuInputProcessor(this.mainMenuInputProcessor);
            BasicMotionGesturesHandler.getInstance().addListener(
                    this.mainMenuInputProcessor);

            GameKeyEventHandler.getInstance().addListener(
            		this.mainMenuInputProcessor);
        }
    }

    public void toggleMenu() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "toggleMenu"));

        if (this.getMenuPaintable() == this.getOpenMenuPaintable())
        {
            this.pause();
            this.updateMenu();
            this.popupMenu();
        }
        else 
            if (this.getMenuPaintable() == this.getFormPaintable())
        {
                primaryPlayerQueue.add(SelectSound.getInstance());

                this.unPause();
        }
    }

    public void closeMenu()
    {
        if (this.gameLayerManager.getGameInfo().getGameType() != gameTypeFactory.BOT)
        {
            this.setMenuPaintable(this.getOpenMenuPaintable());

            BasicMotionGesturesHandler.getInstance().removeListener(
                    this.mainMenuInputProcessor);
        
            GameKeyEventHandler.getInstance().removeListener(
            		this.mainMenuInputProcessor);

            this.setMenuInputProcessor(this.getPopupMenuInputProcessor());
        }
    }

    public void open()
    {
        BasicMotionGesturesHandler.getInstance().addListener(
                this.getMenuInputProcessor());

        GameKeyEventHandler.getInstance().addListener(
        		this.getMenuInputProcessor());
    }

    public void close()
    {
        BasicMotionGesturesHandler.getInstance().removeListener(
                this.getMenuInputProcessor());

        GameKeyEventHandler.getInstance().removeListener(
        		this.getMenuInputProcessor());

        primaryPlayerQueue.clear();
        secondaryPlayerQueue.clear();
    }

    protected void processorInit() throws Exception
    {
        this.setMainStateProcessor(Processor.getInstance());
        this.setProcessGameProcessor(new GameProcessor(this));
    }

    protected void initSpecialPaint()
    {
        if (this.gameLayerManager.getGameInfo().getGameType() != gameTypeFactory.BOT)
        {
            this.setNonBotPaintable(new GameCanvasNonBotPaintable(this));
        }
        else
        {
            this.setNonBotPaintable(NullPaintable.getInstance());
        }
    }

    private void init(AllBinaryGameLayerManager gameLayerManager,
            boolean buffered) throws Exception
    {        
        this.gameStateTimeHelper.setStartTime();

        this.setHighScoreSubmitted(false);

        this.setLayerManager(gameLayerManager);

        // TWB - Custom image release was here

        if (!buffered)
        {
            final String BUFF_MESSAGE = "XXX Not Buffering Causes Concurrency Issues XXX";
            LogUtil.put(LogFactory.getInstance(BUFF_MESSAGE, this, commonStrings.CONSTRUCTOR));

            // this.processPaintable = new GameCanvasPaintHelper(this);
        }
        else
        {
            // this.processPaintable = new BufferedGameCanvasPaintHelper(this);
            throw new Exception("Buffering is disabled");
        }

        // LogUtil.put(LogFactory.getInstance("isDoubleBuffered: " +
        // this.isBuffered() +
        // "isDoubleBuffered: " + this.isDoubleBuffered(), this,
        // commonStrings.CONSTRUCTOR));

        this.setGameInputProcessor(Processor.getInstance());

        if (Features.getInstance().isFeature(
                GameFeatureFactory.getInstance().CHEATING))
        {
            this.isCheating = true;
            this.cheatProcessor = new CheatGameInputProcessor(this);

            GameKeyEventHandler.getInstance().addListener(this.cheatProcessor);

        }
        else
        {
            this.isCheating = false;
            this.cheatProcessor = NoPlayerGameInput.getInstance();
        }

        this.realEndGameProcessor = new EndGameProcessor(this);
        this.setEndGameProcessor(Processor.getInstance());

        this.realStartIntermissionProcessor = new StartIntermissionProcessor(
                this);
        this.startIntermissionProcessor = Processor.getInstance();

        this.setEndGameStatePaintable(new EndGamePaintable(this));
        this.setEndGamePaintable(NullPaintable.getInstance());

        this.setIntermissionPaintable(NullPaintable.getInstance());

        this.getStartIntermissionInterface().setListener(this);
    }

    public void notify(boolean enable)
    {
        if (enable)
        {
            this.startIntermissionProcessor = this.realStartIntermissionProcessor;
        }
        else
        {
            this.startIntermissionProcessor = Processor.getInstance();
        }

        if (enable)
        {
            this.setIntermissionPaintable(this.getStartIntermissionPaintable());
        }
        else
        {
            this.setIntermissionPaintable(NullPaintable.getInstance());
        }
    }

    public void mediaInit() throws Exception
    {
        ForcedLogUtil.log(NotImplemented.NAME, this);
    }

    protected synchronized void initConfigurable() throws Exception
    {
        ProgressCanvasFactory.getInstance().addPortion(50,
                "Setting Configurables");

        GameInitializationUtil.getInstance().initGame(this,
                gameInitializationInterfaceFactoryInterface);

        GameFeatureFactory gameFeatureFactory = GameFeatureFactory.getInstance();

        ChangedGameFeatureListener changedGameFeatureListener = 
            ChangedGameFeatureListener.getInstance();

        if (changedGameFeatureListener.isChanged(gameFeatureFactory.SOUND))
        {
            LogUtil.put(LogFactory.getInstance("Sound Changing To: " + 
                    Features.getInstance().isFeature(gameFeatureFactory.SOUND), this, "initConfigurable"));

            this.mediaInit();
            changedGameFeatureListener.remove(gameFeatureFactory.SOUND);
        }

        SensorFeatureFactory sensorFeatureFactory = SensorFeatureFactory
                .getInstance();

        if (changedGameFeatureListener
                .isChanged(sensorFeatureFactory.ORIENTATION_SENSORS)
                || changedGameFeatureListener
                        .isChanged(sensorFeatureFactory.NO_ORIENTATION)
                || changedGameFeatureListener
                        .isChanged(sensorFeatureFactory.SIMULATED_ORIENTATION_SENSORS))
        {
            // private final AllBinarySensor[] allBinarySensorArray = new
            // AllBinarySensor[1];

            changedGameFeatureListener
                    .remove(sensorFeatureFactory.NO_ORIENTATION);
            changedGameFeatureListener
                    .remove(sensorFeatureFactory.ORIENTATION_SENSORS);
            changedGameFeatureListener
                    .remove(sensorFeatureFactory.SIMULATED_ORIENTATION_SENSORS);
        }

        ResourceLoadingLevelFactory resourceLoadingLevelFactory = 
            ResourceLoadingLevelFactory.getInstance();
        
        this.loadResources(resourceLoadingLevelFactory.LOAD_GAME);        
    }

    protected void init() throws Exception
    {
        this.initConfigurable();
        
        this.processorInit();

        // Since touch button selection is based on sensors this must come first
        this.sensorGameUpdateProcessor.process(this.gameLayerManager);
        this.sensorGameUpdateProcessor.sendNotifications(this.gameLayerManager);

        this.initTouch();
    }

    // TWB - Hack?
    protected void initTouch() throws Exception
    {
        GameInitializedEvent gameInitializedEvent = GameInitializationUtil.getInstance().EVENT;

        ResourceLoadingLevelFactory resourceLoadingLevelFactory = 
            ResourceLoadingLevelFactory.getInstance();

        gameInitializedEvent.setResourceLoadingLevel(resourceLoadingLevelFactory.LOAD_TOUCH);

        GameInitializedEventHandler.getInstance().fireEvent(gameInitializedEvent);

        TouchButtonFactory.getInstance().defaultList();

        this.updateTouch();

        this.postInitTouch();
    }

    public void updateCurrentTouchInputFactory(
            BaseTouchInput nextTouchInput) throws Exception
    {
        if (nextTouchInput != this.currentTouchInputFactory)
        {
            this.currentTouchInputFactory = nextTouchInput;

            // LogUtil.put(LogFactory.getInstance(this.currentTouchInputFactory,
            // this, "updateCurrentTouchInputFactory"));
            PreLogUtil.put(this.currentTouchInputFactory.toString(), this, "updateCurrentTouchInputFactory");

            TouchButtonFactory.getInstance().toggle(this.isPaused(),
                    this.currentTouchInputFactory.getList());
        }
    }

    protected void updateTouch() throws Exception
    {
        if (this.gameLayerManager.getGameInfo().getGameType() != gameTypeFactory.BOT)
        {
            if (Features.getInstance().isFeature(TouchFeatureFactory.getInstance().AUTO_HIDE_SHOW_SCREEN_BUTTONS))
            {
                if (this.gameLayerManager.getGameInfo().getCurrentLevel() - getStartLevel() == 1)
                {
                    this.setTouchPaintable(NullPaintable.getInstance());
                }
            }
        }
    }

    protected void postInitTouch() throws Exception
    {
        this.setTouchButtonsPaintable(TouchButtonsPaintableFactory
                .getInstance(this.gameLayerManager.getForegroundBasicColor()));
        this.updateScreenButtonPaintable();
    }

    public void initCommands(CommandListener cmdListener)
    {
        this.removeAllCommands();

        this.addCommands();

        this.setCommandListener(cmdListener);
    }

    public void addCommands()
    {
        GameCommandsFactory gameCommandsFactory = GameCommandsFactory
                .getInstance();

        if (DebugFactory.getInstance() != NoDebug.getInstance())
        {
            this.addCommand(gameCommandsFactory.START_TRACE);
        }

        this.addCommand(gameCommandsFactory.RESTART_COMMAND);

        this.addCommand(MyCommandsFactory.getInstance().PAUSE_COMMAND);

        this.addCommand(gameCommandsFactory.QUIT_COMMAND);

        if (TouchScreenFactory.getInstance().isTouch()
                && new InGameFeatures().isAny())
        {
            // System.out.println("InGameOptions");
            this.addCommand(InGameOptionsForm.DISPLAY);
        }

        // this.addCommand(GameCommands.DISPLAY_SAVE_FORM);
        this.addCommand(gameCommandsFactory.SAVE);
        this.addCommand(gameCommandsFactory.DISPLAY_LOAD_FORM);
    }

    public void itemStateChanged(Item item)
    {
        try
        {
            String itemLabel = item.getLabel();

            LogUtil.put(LogFactory.getInstance("Item: " + itemLabel, this,
                    "itemStateChanged"));

            if (item instanceof ChoiceGroup)
            {
                GameFeatureUtil gameFeatureUtil = GameFeatureUtil.getInstance();

                if (gameFeatureUtil.isExclusive(itemLabel))
                {
                    gameFeatureUtil.updateExclusive((ChoiceGroup) item);
                }
                else
                {
                    gameFeatureUtil.updateMultiple((ChoiceGroup) item);
                }
            }

            // Since touch button selection is based on sensors this must come
            // first
            this.getSensorGameUpdateProcessor().process(this.gameLayerManager);

            // Swap between different touch button setups
            this.updateTouch();

            // Update whether they should be displayed or not
            this.updateScreenButtonPaintable();

            this.getSensorGameUpdateProcessor().sendNotifications(
                    this.gameLayerManager);

        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(
                    commonStrings.EXCEPTION, this,
                    "itemStateChanged", e));
        }
    }

    public void updateScreenButtonPaintable() throws Exception
    {
        Features features = Features.getInstance();

        TouchFeatureFactory touchFeatureFactory = TouchFeatureFactory.getInstance();
        
        // Show/Hide the screen buttons
        if (this.gameLayerManager.getGameInfo().getGameType() == gameTypeFactory.BOT)
        {
            this.setTouchPaintable(NullPaintable.getInstance());
        }
        else if (features.isFeature(touchFeatureFactory.AUTO_HIDE_SHOW_SCREEN_BUTTONS))
        {
            // If touch buttons are visible
            this.setTouchPaintable(this.getTouchButtonsPaintable());
            this.setStartLevel(this.gameLayerManager.getGameInfo()
                    .getCurrentLevel());
        }
        else if (features.isFeature(touchFeatureFactory.SHOW_SCREEN_BUTTONS))
        {
            // PreLogUtil.put(TouchFeatureFactory.getInstance().SHOW_SCREEN_BUTTONS.getName(),
            // this, "updateScreenButtonPaintable");

            // If touch buttons are visible
            this.setTouchPaintable(this.getTouchButtonsPaintable());
        }
        else if (features.isFeature(touchFeatureFactory.HIDE_SCREEN_BUTTONS))
        {
            this.setTouchPaintable(NullPaintable.getInstance());
        }
        else
        {
            this.setTouchPaintable(NullPaintable.getInstance());
            // throw new Exception("Screen Button Feature Not Set");
        }
    }

    public AllBinaryGameLayerManager getLayerManager()
    {
        return gameLayerManager;
    }

    public void setLayerManager(AllBinaryGameLayerManager layerManager)
    {
        this.gameLayerManager = layerManager;
    }

    public synchronized boolean isGameOver()
    {
        return this.gameOver;
    }

    public synchronized void setGameOver(boolean gameOver)
    {
        this.gameOver = gameOver;
    }

    public void setGameOver() throws Exception
    {
        PreLogUtil.put(commonStrings.START, this, "setGameOver");
        // LogUtil.put(LogFactory.getInstance(commonStrings.START,
        // this, "setGameOver"));

        this.setGameOver(true);

        removePauseCommand();
        this.setGameState(SHOW_END_RESULT_GAME_STATE);

        this.setEndGamePaintable(getEndGameStatePaintable());
        /*
         * if (gameOver) { this.setEndGamePaintable(endGameStatePaintable); }
         * else { this.setEndGamePaintable(NullPaintable.getInstance()); }
         */
    }

    /*
     * protected boolean isRepeated() { return repeated; } protected void
     * setRepeated(boolean repeated) { this.repeated = repeated; }
     */
    public boolean isHighScoreSubmitted()
    {
        return highScoreSubmitted;
    }

    public void setHighScoreSubmitted(boolean highScoreSubmitted)
            throws Exception
    {
        this.highScoreSubmitted = highScoreSubmitted;

        LogUtil.put(LogFactory.getInstance("isHighScoreSubmitted: " + this.isHighScoreSubmitted(), this, "setHighScoreSubmitted"));
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public void setGameState(GameState gameState) throws Exception
    {
        LogUtil.put(LogFactory.getInstance("Game State: " + gameState, this, "setGameState"));
        // PreLogUtil.put("Game State: " + gameState, this, "setGameState");

        this.gameState = gameState;
        this.gameStateTimeHelper.setStartTime();

        this.updateEndGameProcessor();
        this.updateGameKeyEventProcessor();
        
        if (this.getLayerManager().getGameInfo().getGameType() != gameTypeFactory.BOT)
        {
            GameAdState gameAdState = 
                GameAdStateFactory.getInstance().getCurrentInstance();

            gameAdState.processAdState(
                    this.gameState, this.getLayerManager().getGameInfo().getGameType());
            
            if (this.gameState != GameState.PLAYING_GAME_STATE)
            {
                gameAdState.processPageAdState();
            }
        }
    }

    private void updateGameKeyEventProcessor()
    {
        if (this.getGameState() != GameState.PLAYING_GAME_STATE || this.isCheating)
        {
            // for cheating
            // this.open();
        }
        else
        {
            // this.removeGameKeyInputListeners();
        }
    }

    protected void removeAllGameKeyInputListeners()
    {
        if (this.gameLayerManager.getGameInfo().getGameType() != gameTypeFactory.BOT)
        {

            // System.out.println("Clearing Keys From Last Level");
            LogUtil.put(LogFactory.getInstance("Remove PlayerInput Listeners",
                    this, "removeAllGameKeyInputListeners"));

            if (this.playerGameInput != null)
            {
                GameKeyEventHandler.getInstance().removeListener(this.playerGameInput);
                this.playerGameInput.removeNonAIInputGameKeyEvents();
            }
        }
    }

    /*
     * private void removeGameKeyInputListeners() { //for cheating
     * UpGameKeyEventHandler.getInstance().removeListener(this);
     * DownGameKeyEventHandler.getInstance().removeListener(this); }
     */
    protected void updateEndGameProcessor() throws Exception
    {
        if (this.gameLayerManager.getGameInfo().getGameType() != gameTypeFactory.BOT)
        {
            if (this.getGameState() == SHOW_END_RESULT_GAME_STATE
                    || this.getGameState() == SHOW_HIGH_SCORE_GAME_STATE)
            {
                if (highScoresPaintable != NullPaintable.getInstance())
                {
                    this.selectHighScores();
                }

                this.setEndGameProcessor(this.realEndGameProcessor);
            }
            else
            {
                this.setEndGameProcessor(Processor.getInstance());
            }
        }
    }

    public void buildGame(boolean isPortion) throws Exception
    {
    }

    protected void cleanupGame() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "cleanupGame"));
        
        ProgressCanvasFactory.getInstance().addPortion(10, "Cleaning Up");

        primaryPlayerQueue.clear();
        secondaryPlayerQueue.clear();

        this.getLayerManager().cleanup();
        
        GameLevelDisplayChangeEventListenersFactory.getInstance().clear();
    }

    public void loadResources(ResourceLoadingLevel resourceLoadingLevel) throws Exception
    {
        GameInitializedEvent gameInitializedEvent = GameInitializationUtil.getInstance().EVENT;

        gameInitializedEvent.setResourceLoadingLevel(resourceLoadingLevel);

        GameInitializedEventHandler.getInstance().fireEvent(gameInitializedEvent);
    }

    public void loadResources(int level) throws Exception
    {
        GameInitializedEvent gameInitializedEvent = GameInitializationUtil.getInstance().EVENT;

        gameInitializedEvent.setLevel(level);

        GameInitializedEventHandler.getInstance().fireEvent(gameInitializedEvent);
    }
    
    public void updateColor() throws Exception
    {
    }

    public void buildGame(int portion) throws Exception
    {
        ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();

        progressCanvas.addPortion(portion, "Generic Build");

        this.removeAllGameKeyInputListeners();

        this.updateTouch();

        progressCanvas.addPortion(portion, "High Scores");

        SecondaryThreadPool.getInstance().runTask(
                new HighScoresUpdateRunnable(this));

        this.setHighScoresPaintable(NullPaintable.getInstance());

        progressCanvas.addPortion(portion, "Finishing.");

        this.getEndGameInfo().update();

        Features features = Features.getInstance();
        
        this.isSingleKeyRepeatableProcessing = 
            features.isFeature(InputFeatureFactory.getInstance().SINGLE_KEY_REPEAT_PRESS);

        this.setGameOver(false);

        progressCanvas.addPortion(portion, "Finishing..");

        // LogUtil.put(LogFactory.getInstance("Game Color Change", this, BUILD_GAME));

        this.updateColor();

        this.getStartIntermissionPaintable().update();

        if (!this.isRunning())
        {
            return;
            // If this is not the root window then it does not change
            // the screen
        }

        progressCanvas.addPortion(portion, "Finishing...");

        if (this.isMainCanvas() || !this.isInitialized())
        {
            // Don't try to end progress if demogame and not in resource
            // debugging mode
            if (features.isFeature(
                    MainFeatureFactory.getInstance().LOAD_ONDEMAND))
            {
                if (this.getCustomCommandListener() != null)
                {
                    progressCanvas.end();
                }
            }
            else
            {
                progressCanvas.end();
            }

            this.setInitialized(true);
        }

        if (this.gameCanvasStartListener != null)
        {
            this.gameCanvasStartListener.showGamePaintable();
        }

        this.colorFillPaintable.setBasicColor(this.gameLayerManager.getBackgroundBasicColor());

        if (this.gameLayerManager.getGameInfo().getGameType() != gameTypeFactory.BOT)
        {
            //LogUtil.put(LogFactory.getInstance("Clearing Keys From Last Level", this, BUILD_GAME));

            PreLogUtil.put("Enabling PlayerGameInput: " + this.playerGameInput, this, BUILD_GAME);
            
            if (this.playerGameInput != null)
            {
                this.playerGameInput.removeNonAIInputGameKeyEvents();
                GameKeyEventHandler.getInstance().addListener(
                        this.playerGameInput);

                // ForcedLogUtil.log("DownGameKeyEventHandler: " +
                // DownGameKeyEventHandler.getInstance().getEventListenerInterfaceList(),
                // this);
            }
        }
    }

    private DemoCanvas gameCanvasStartListener;

    public void setGameCanvasStartListener(DemoCanvas gameCanvasStartListener)
    {
        this.gameCanvasStartListener = gameCanvasStartListener;
    }

    public void loadState() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START,
                this, "loadState"));
        Hashtable hashtable = getLoadStateHashtable();

        if (hashtable != null && hashtable.size() > 0)
        {
            Integer levelInteger = Integer.valueOf((String) hashtable
                    .get(GameInfo.LEVEL_NAME));
            GameInfo gameInfo = this.gameLayerManager.getGameInfo();
            gameInfo.setCurrentLevel(levelInteger.intValue());
        }
    }

    public Hashtable getLoadStateHashtable() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(
                commonStrings.START_LABEL + this.hashtable, this,
                "getLoadStateHashtable"));
        return this.hashtable;
    }

    public void setLoadStateHashtable(Hashtable hashtable)
    {
        LogUtil.put(LogFactory.getInstance(
                commonStrings.START_LABEL + hashtable, this,
                "setLoadStateHashtable"));
        this.hashtable = hashtable;
    }

    public Hashtable getCurrentStateHashtable()
    {
        Hashtable hashtable = new Hashtable();

        int level = this.gameLayerManager.getGameInfo().getCurrentLevel();

        hashtable.put(GameInfo.LEVEL_NAME.toString(), Integer.toString(level));

        LogUtil.put(LogFactory.getInstance("End: " + hashtable, this,
                "getCurrentStateHashtable"));

        return hashtable;
    }

    public void paintGameOver(Graphics graphics)
    {
        ForcedLogUtil.log(NotImplemented.NAME, this);
    }

    public void draw(Graphics graphics)
    {
        this.colorFillPaintable.paint(graphics);

        this.getBasicColorUtil().setBasicColor(graphics,
                this.gameLayerManager.getForegroundBasicColor());

        this.gameSpecificPaintable.paint(graphics);
    }

    public void clear(Graphics graphics)
    {
        this.colorFillPaintable.paint(graphics);
    }

    public void paint(Graphics graphics)
    {
        //TWB - was in MyCanvas paint -- super.paint(graphics);
        baseGameStatistics.nextRefresh();

        //// Disabled buffering
        //// this.processPaintable.paint(graphics);
        //// This is what is called without buffering
        this.draw(graphics);
        ////this.setDisplayed(true);
        //// End - This is what is called without buffering

        menuPaintable.paint(graphics);
    }

    public void paintThreed(Graphics graphics)
    {
    }

    // TWB - This hack method should be removed once I figure out how it should
    // be removed
    public void processEndLevelIntermissionGameState() throws Exception
    {
    }

    // TWB - This hack method should be removed once I figure out how it should
    // be removed
    public void paintIntermission(Graphics graphics)
    {
    }

    private final InputToGameKeyMapping inputToGameKeyMapping =
        PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance()
        .getInputMapping();

    private final InputProcessor rawGameInputProcessor = new GameInputProcessor(this);
    private final InputProcessor rawInputProcessor = new FormInputProcessor(this);
    private InputProcessor inputProcessor = getRawGameInputProcessor();

    public void keyPressed(int keyCode)
    {
        // LogUtil.put(LogFactory.getInstance(commonStrings.START_LABEL
        // + this.inputProcessor.toString() + commonStrings.SPACE
        // + keyCode, this, "keyPressed"));
        // ForcedLogUtil.log(commonStrings.START_LABEL +
        // this.inputProcessor.toString() + commonStrings.SPACE +
        // keyCode, this);

        // this.addGameKeyEvent(keyCode, false);
        this.inputProcessor.keyPressed(keyCode);
    }

    public void keyReleased(int keyCode)
    {
        // LogUtil.put(LogFactory.getInstance(commonStrings.START,
        // this, "keyReleased"));
        this.removeGameKeyEvent(keyCode, false);
    }

    public void keyRepeated(int keyCode)
    {
        // LogUtil.put(LogFactory.getInstance("Key Repeated: " +
        // Integer.toHexString(keyCode),
        // this, "keyRepeated"));
        if (this.isSingleKeyRepeatableProcessing)
        {
            // this.addGameKeyEvent(keyCode, true);
            this.inputProcessor.keyPressed(keyCode);
        }
    }

    public final String NO_KEY = "Key Code Not Mapped For Game: ";
    public final String ADD_KEY_EVENT = "addGameKeyEvent";
    private final String REMOVE_KEY_EVENT = "removeGameKeyEvent";

    // private void addGameKeyEvent(int keyCode, boolean repeated)
    // {
    // }

    private final GameKey NONE = GameKeyFactory.getInstance().NONE;
    
    private final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
    
    private void removeGameKeyEvent(int keyCode, boolean repeated)
    {
        try
        {
            // LogUtil.put(LogFactory.getInstance("Key Code: " +
            // Integer.toHexString(keyCode),
            // this, "removeGameKeyEvent"));

            GameKey gameKey = this.inputToGameKeyMapping.getInstance(this, keyCode);

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
                // UpGameKeyEventHandler.getInstance().fireEvent(gameKey);
                // getPlayerGameInput().onUpGameKeyEvent(gameKeyEvent);
            }
            else
            {
                LogUtil.put(LogFactory.getInstance(NO_KEY + keyCode, this, REMOVE_KEY_EVENT));
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Key Event Error", this, REMOVE_KEY_EVENT, e));
        }
    }

    protected int endProgress(boolean isProgress)
    {
        int portion = 30;
        if (isProgress && this.isMainCanvas())
        {
            ProgressCanvasFactory.getInstance().start();
            this.getCustomCommandListener().commandAction(
                    MyCommandsFactory.getInstance().SET_DISPLAYABLE,
                    ProgressCanvasFactory.getInstance());
            // ProgressCanvasFactory.getInstance().waitUntilDisplayed();
            portion = 4;
        }
        return portion;
    }

    protected void processPlayingGame() throws Exception
    {
        /*
         * if(this.hasRepeatEvents()) { this.setRepeated(true); }
         */

        cheatProcessor.update();

        sensorGameUpdateProcessor.getInputSensor().update();

        gameInputProcessor.process();

        // layerProcessor.track(layer);

        gameLayerManager.process();

        startIntermissionProcessor.process();

        /*
         * if (DebugFactory.getInstance() != NoDebug.getInstance()) {
         * DebugInterface debugInterface = DebugFactory.getInstance(); if
         * (debugInterface.isRunning()) { LogUtil.put(LogFactory.getInstance(
         * "Debug Trace Running", this, "processPlayingGame"));
         * 
         * long totalTimeElapsed = currentTime) - debugInterface.getStartTime();
         * 
         * LogUtil.put(LogFactory.getInstance( "Elapsed Time: " +
         * totalTimeElapsed, this, "processGame"));
         * 
         * if (totalTimeElapsed > debugInterface.getMaxTime()) { PreLogUtil.put(
         * //LogFactory.getInstance( "Elapsed Time: " + totalTimeElapsed, this,
         * "processPlayingGame"); //));
         * 
         * debugInterface.stop(); } } }
         */
    }

    protected void threadInit() throws Exception
    {
    }

    protected void processGame() throws Exception
    {
        if (AllBinaryMediaManager.update())
        {
            if (!primaryPlayerQueue.process())
            {
                secondaryPlayerQueue.process();
            }
        }

        mainStateProcessor.process();
        menuInputProcessor.processInput();
        endGameProcessor.process();

        baseGameStatistics.nextFrame();

        // soundQueue = PrimaryPlayerQueueFactory.getInstance().toString();

        // TWB - Game Stats
        // baseRefreshHelperCharArray =
        // GameStatisticsFactory.getInstance().toCharArray();
    }

    public void notifyDonePainting()
    {
        synchronized (this)
        {
            this.notify();
        }
    }

    private final int YIELD_SLEEP = 100;

    public void run()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(
                    commonStrings.START_RUNNABLE, this,
                    commonStrings.RUN));

            ProgressCanvasFactory.getInstance().addPortion(50, "Game Thread");

            this.setCurrentThread();

            this.setRunning(true);

            if (this.getCustomCommandListener() == null)
            {
                Thread.sleep(YIELD_SLEEP);
            }

            this.threadInit();

            if (this.getCustomCommandListener() == null)
            {
                Thread.sleep(YIELD_SLEEP);
            }

            this.open();

            GameAdState gameAdState = GameAdStateFactory.getInstance().getCurrentInstance();

            gameAdState.init();
            gameAdState.setGameIsReady(true);

            //Don't keep running thread if in bot/demo mode            
            if (this.gameLayerManager.getGameInfo().getGameType() == gameTypeFactory.BOT)
            {
                
            }
            else
                if (Features.getInstance().isDefault(
                        OpenGLFeatureFactory.getInstance().OPENGL_AS_GAME_THREAD))
                {
                    GameCanvasRunnable gameRunnable = new GameCanvasRunnable(this);

                    final CurrentDisplayableFactory currentDisplayableFactory = 
                        CurrentDisplayableFactory.getInstance();

                    currentDisplayableFactory.setRunnable(gameRunnable);
                    
                }
                else
            if (Features.getInstance().isDefault(
            		OpenGLFeatureFactory.getInstance().OPENGL_AND_GAME_HAVE_DIFFERENT_THREADS))
            {
                final GameTickTimeDelayHelperFactory gameTickTimeDelayHelperFactory = 
                        GameTickTimeDelayHelperFactory.getInstance();

                while (this.isRunning())
                {
                	this.getLoopTimeHelper().setStartTime(
                			gameTickTimeDelayHelperFactory.setStartTime());

                	this.processGame();

                	this.processLoopSleep();
                }

                this.end();

                /*
                this.runnableCanvasRefreshHelper = Processor.getInstance();

                GameFrameRunnable gameFrameRunnable = new GameFrameRunnable(this);

                while (this.isRunning())
                {
                    currentDisplayableFactory.setRunnable(gameFrameRunnable);

                    synchronized (this)
                    {
                        // PreLogUtil.put("Wait", this, commonStrings.RUN);
                        this.wait();
                        // PreLogUtil.put("Notified", this, commonStrings.RUN);
                    }

                    currentDisplayableFactory.clearRunnable();

                    if (!this.isRunning())
                    {
                        break;
                    }

                    this.processLoopSleep();
                } 
                */
            }
            else
            {
                final GameTickTimeDelayHelperFactory gameTickTimeDelayHelperFactory = 
                    GameTickTimeDelayHelperFactory.getInstance();
                
                while (this.isRunning())
                {
                    this.getLoopTimeHelper().setStartTime(
                            gameTickTimeDelayHelperFactory.setStartTime());

                    this.processGame();

                    this.processLoopSleep();
                }
                
                this.end();
            }

            LogUtil.put(LogFactory.getInstance(commonStrings.END_RUNNABLE, this, commonStrings.RUN));
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.RUN, e));
        }
    }

    public void setRunning(boolean running) 
    {
        super.setRunning(running);
        
        try
        {
            //If game thread is not actually running
            if (Features.getInstance().isDefault(OpenGLFeatureFactory.getInstance().OPENGL) && !running)
            {
                final CurrentDisplayableFactory currentDisplayableFactory = CurrentDisplayableFactory.getInstance();
                currentDisplayableFactory.clearRunnable();
                this.end();
            }
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, SET_RUNNING, e));
        }
    }
    
    public void end() throws Exception
    {
        AllGameStatisticsFactory allGameStatisticsFactory = AllGameStatisticsFactory.getInstance();
        allGameStatisticsFactory.add(baseGameStatistics.toString() + CommonSeps.getInstance().NEW_LINE);
        baseGameStatistics.init();
        
        GameKeyEventHandler.getInstance().removeListener(this.cheatProcessor);
        this.close();
        this.removeAllGameKeyInputListeners();
        this.endGameThread();
    }

    public void endGameThread() throws Exception
    {
        DisplayChangeEventHandler.getInstance().removeListener(this);
        GameLevelDisplayChangeEventListenersFactory.getInstance().clear();
        
        GameAdState gameAdState = 
            GameAdStateFactory.getInstance().getCurrentInstance();
        
        gameAdState.notPlayingAdState();

    }

    // Since HighScores for a default game are not level Specific we do not
    // specify level info
    public HighScore createHighScore(long score)
    {
        GameInfo gameInfo = this.gameLayerManager.getGameInfo();

        return new HighScore(0, "NONE", new GameInfo(gameInfo.getGameType(),
                gameInfo.getGameMode(), 0, 0), score);
    }

    // Game states
    /*
     * public boolean isLevelComplete() { return this.isLevelComplete; } public
     * void levelComplete() { this.isLevelComplete = true; }
     */
    public void setHighScore(String name, long score) throws Exception
    {
        if (this.gameLayerManager.getGameInfo().getGameType() != gameTypeFactory.BOT)
        {
            HighScore highScore = this.createHighScore(score);
            // TWB - Technically this means that if it is not a best local score
            // then it will not become a remote high score
            if (this.getHighScoresArray()[0].isBestScore(highScore))
            {
                this.textBox = new HighScoreTextBox(
                        this.getCustomCommandListener(), name,
                        this.getHighScoresArray(), highScore, this
                                .getLayerManager().getBackgroundBasicColor(),
                        this.gameLayerManager.getForegroundBasicColor());

                this.getCustomCommandListener().commandAction(
                        GameCommandsFactory.getInstance().SET_MENU_DISPLAYABLE,
                        this.textBox);
            }
            else
            {
                // if score is not a high score ignore it
                this.setHighScoreSubmitted(true);
            }
        }
    }

    protected TimeDelayHelper getGameStateTimeHelper()
    {
        return gameStateTimeHelper;
    }

    protected void setInitialized(boolean initialized)
    {
        this.initialized = initialized;
    }

    public boolean isInitialized()
    {
        return initialized;
    }

    public int getSourceId()
    {
        return id;
    }

    public IntermissionInterface getStartIntermissionInterface()
    {
        return this.startIntermissionInterface;
    }

    public IntermissionInterface getEndLevelIntermissionInterface()
    {
        return endLevelIntermissionInterface;
    }

    protected void setTouchPaintable(Paintable paintable)
    {
        //ForcedLogUtil.log("Touch Paintable: " + paintable, this);
        this.touchPaintable = paintable;
    }

    public Paintable getTouchPaintable()
    {
        return touchPaintable;
    }

    protected void setEndGamePaintable(Paintable endGamePaintable)
    {
        this.endGamePaintable = endGamePaintable;
    }

    public Paintable getEndGamePaintable()
    {
        return endGamePaintable;
    }

    protected void setIntermissionPaintable(Paintable intermissionPaintable)
    {
        this.intermissionPaintable = intermissionPaintable;
    }

    public Paintable getIntermissionPaintable()
    {
        return intermissionPaintable;
    }

    /**
     * @return the endGameInfo
     */
    protected EndGameInfo getEndGameInfo()
    {
        return endGameInfo;
    }

    /**
     * @param endGameInfo
     *            the endGameInfo to set
     */
    public void setEndGameInfo(EndGameInfo endGameInfo)
    {
        this.endGameInfo = endGameInfo;
    }

    public void setHighScores() throws Exception
    {
        HighScores[] highScoresArray = this.highScoresFactoryInterface
                .createHighScores(this.gameLayerManager.getGameInfo());

        this.setHighScoresArray(highScoresArray);
    }

    private HighScores[] getHighScoresArray()
    {
        return highScoresArray;
    }

    protected void setHighScoresArray(HighScores[] highScores)
    {
        this.highScoresArray = highScores;

        this.circularIndexUtil.setSize(this.highScoresArray.length);

        this.selectHighScores();
    }

    public void selectHighScores()
    {
        this.circularIndexUtil.next();

        HighScores highScores = this.highScoresArray[this.circularIndexUtil.getIndex()];

        int index = 0;
        while (highScores.getTotal() < 1 && index < this.highScoresArray.length)
        {
            highScores = this.highScoresArray[this.circularIndexUtil.next()];
            index++;
        }

        this.setSelectedHighScores(highScores);
        this.getRealHighScoresPaintable().setHighScores(highScores);
    }

    public void setHighScoresPaintable(Paintable highScoresPaintable)
    {
        this.highScoresPaintable = highScoresPaintable;
    }

    public Paintable getHighScoresPaintable()
    {
        return highScoresPaintable;
    }

    protected HighScoresPaintable getRealHighScoresPaintable()
    {
        return realHighScoresPaintable;
    }

    private void setSelectedHighScores(HighScores selectedHighScores)
    {
        this.selectedHighScores = selectedHighScores;
    }

    protected HighScores getSelectedHighScores()
    {
        return selectedHighScores;
    }

    protected void setPlayerGameInput(PlayerGameInput playerGameInput)
    {
        //PreLogUtil.put("Setting Player Input: " + playerGameInput, this, "setPlayerGameInput");
        
        if (this.playerGameInput != null)
        {
            GameKeyEventHandler.getInstance().removeListener(this.playerGameInput);
        }

        this.playerGameInput = playerGameInput;
    }

    protected void setMenuInputProcessor(
            BasicMenuInputProcessor menuInputProcessor)
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
     * @param menuForm
     *            the menuForm to set
     */
    public void setMenuForm(ScrollSelectionForm menuForm)
    {
        this.menuForm = menuForm;
    }

    /**
     * @return the startLevel
     */
    public int getStartLevel()
    {
        return startLevel;
    }

    /**
     * @param startLevel
     *            the startLevel to set
     */
    public void setStartLevel(int startLevel)
    {
        this.startLevel = startLevel;
    }

    protected BasicColorSetUtil getBasicColorUtil()
    {
        return basicColorUtil;
    }

    protected void setTouchButtonsPaintable(Paintable touchButtonsPaintable)
    {
        this.touchButtonsPaintable = touchButtonsPaintable;
    }

    protected Paintable getTouchButtonsPaintable()
    {
        return touchButtonsPaintable;
    }

  //TWB - multiplayer needed it to be public
    public void setGameInputProcessor(Processor gameInputProcessor)
    {
        this.gameInputProcessor = gameInputProcessor;
    }

    protected Processor getGameInputProcessor()
    {
        return gameInputProcessor;
    }

    protected void setEndGameProcessor(Processor endGameProcessor)
    {
        this.endGameProcessor = endGameProcessor;
    }

    protected Processor getEndGameProcessor()
    {
        return endGameProcessor;
    }

    protected void setEndGameStatePaintable(Paintable endGameStatePaintable)
    {
        this.endGameStatePaintable = endGameStatePaintable;
    }

    protected Paintable getEndGameStatePaintable()
    {
        return endGameStatePaintable;
    }

    protected void setNonBotPaintable(Paintable nonBotPaintable)
    {
        this.nonBotPaintable = nonBotPaintable;
    }

    protected Paintable getNonBotPaintable()
    {
        return nonBotPaintable;
    }

    protected void setStartIntermissionPaintable(
            InitUpdatePaintable startIntermissionPaintable)
    {
        this.startIntermissionPaintable = startIntermissionPaintable;
    }

    protected InitUpdatePaintable getStartIntermissionPaintable()
    {
        return startIntermissionPaintable;
    }

  //TWB - multiplayer needed it to be public
    public void setMainStateProcessor(Processor mainStateProcessor)
    {
        this.mainStateProcessor = mainStateProcessor;
    }

    protected Processor getMainStateProcessor()
    {
        return mainStateProcessor;
    }

    protected void setProcessGameProcessor(Processor processGameProcessor)
    {
        this.processGameProcessor = processGameProcessor;
    }

  //TWB - multiplayer needed it to be public
    public Processor getProcessGameProcessor()
    {
        return processGameProcessor;
    }

    protected void setOpenMenuPaintable(Paintable openMenuPaintable)
    {
        this.openMenuPaintable = openMenuPaintable;
    }

    protected Paintable getOpenMenuPaintable()
    {
        return openMenuPaintable;
    }

    protected void setPopupMenuInputProcessor(
            BasicMenuInputProcessor popupMenuInputProcessor)
    {
        this.popupMenuInputProcessor = popupMenuInputProcessor;
    }

    protected BasicMenuInputProcessor getPopupMenuInputProcessor()
    {
        return popupMenuInputProcessor;
    }

    public SensorGameUpdateProcessor getSensorGameUpdateProcessor()
    {
        return sensorGameUpdateProcessor;
    }

  //TWB - multiplayer needed it to be public
    public InputProcessor getRawGameInputProcessor()
    {
        return rawGameInputProcessor;
    }

  //TWB - multiplayer needed it to be public
    public InputProcessor getRawInputProcessor()
    {
        return rawInputProcessor;
    }

  //TWB - multiplayer needed it to be public
    public void setInputProcessor(InputProcessor inputProcessor)
    {
        // LogUtil.put(LogFactory.getInstance("New: " +
        // inputProcessor.toString(), this, "setInputProcessor"));
        this.inputProcessor = inputProcessor;
    }

    protected InputProcessor getInputProcessor()
    {
        return inputProcessor;
    }

    protected void setMenuPaintable(Paintable menuPaintable)
    {
        this.menuPaintable = menuPaintable;
    }

    protected Paintable getMenuPaintable()
    {
        return menuPaintable;
    }

    private void setFormPaintable(Paintable formPaintable)
    {
        this.formPaintable = formPaintable;
    }

    protected Paintable getFormPaintable()
    {
        return formPaintable;
    }
    
  //TWB - multiplayer needed it to be public
    public void setGameSpecificPaintable(Paintable gameSpecificPaintable)
    {
        this.gameSpecificPaintable = gameSpecificPaintable;
    }

    protected Paintable getGameSpecificPaintable()
    {
        return gameSpecificPaintable;
    }
    
    public boolean isSingleThread()
    {
        return OpenGLFeatureUtil.getInstance().isAnyThreed();
    }
}
