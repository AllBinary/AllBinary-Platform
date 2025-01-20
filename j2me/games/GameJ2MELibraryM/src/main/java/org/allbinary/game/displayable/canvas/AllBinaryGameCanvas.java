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

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Item;

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.system.os.OperatingSystemFactory;
import org.allbinary.canvas.BaseGameStatistics;
import org.allbinary.canvas.GameStatisticsFactory;
import org.allbinary.canvas.Processor;
import org.allbinary.canvas.RunnableCanvas;
import org.allbinary.debug.DebugFactory;
import org.allbinary.debug.NoDebug;
import org.allbinary.game.GameInfo;
import org.allbinary.game.GameTypeFactory;
import org.allbinary.game.Intermission;
import org.allbinary.game.IntermissionCompositeInterface;
import org.allbinary.game.IntermissionEnableListenerInterface;
import org.allbinary.game.IntermissionInterface;
import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.configuration.InGameFeatures;
import org.allbinary.game.configuration.InGameOptionsForm;
import org.allbinary.game.configuration.event.ChangedGameFeatureListener;
import org.allbinary.game.configuration.event.GameInitializedEvent;
import org.allbinary.game.configuration.event.GameInitializedEventHandler;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureFactory;
import org.allbinary.game.configuration.feature.GameFeatureUtil;
import org.allbinary.game.configuration.feature.HTMLFeatureFactory;
import org.allbinary.game.configuration.feature.InputFeatureFactory;
import org.allbinary.game.configuration.feature.MainFeatureFactory;
import org.allbinary.game.configuration.feature.SensorFeatureFactory;
import org.allbinary.game.configuration.feature.TouchFeatureFactory;
import org.allbinary.game.displayable.GameLevelDisplayChangeEventListenersFactory;
import org.allbinary.game.init.BasicBuildGameInitializerFactory;
import org.allbinary.game.init.GameInitializationUtil;
import org.allbinary.game.input.InputProcessor;
import org.allbinary.game.input.NoPlayerGameInput;
import org.allbinary.game.input.PlayerGameInput;
import org.allbinary.game.input.event.GameKeyEventHandler;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.paint.ColorFillBasePaintable;
import org.allbinary.game.paint.ColorFillPaintableFactory;
import org.allbinary.game.score.HighScore;
import org.allbinary.game.score.HighScoresFactoryInterface;
import org.allbinary.game.score.HighScoresPaintable;
import org.allbinary.game.score.displayable.HighScoreTextBox;
import org.allbinary.game.state.GameState;
import org.allbinary.game.state.GameStateFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.ScreenCapture;
import org.allbinary.graphics.ScreenCaptureFactory;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.BasicColorSetUtil;
import org.allbinary.graphics.displayable.command.MyCommandsFactory;
import org.allbinary.graphics.displayable.event.DisplayChangeEvent;
import org.allbinary.graphics.displayable.event.DisplayChangeEventHandler;
import org.allbinary.graphics.displayable.event.DisplayChangeEventListener;
import org.allbinary.graphics.form.CommandCurrentSelectionFormFactory;
import org.allbinary.graphics.form.FormPaintable;
import org.allbinary.graphics.form.FormType;
import org.allbinary.graphics.form.FormTypeFactory;
import org.allbinary.graphics.form.ScrollSelectionForm;
import org.allbinary.graphics.form.ScrollSelectionFormNoneFactory;
import org.allbinary.graphics.form.item.CommandTextItemArrayFactory;
import org.allbinary.graphics.paint.InitUpdatePaintable;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.input.motion.button.BaseTouchInput;
import org.allbinary.input.motion.button.TouchButtonFactory;
import org.allbinary.input.motion.button.TouchButtonsPaintableFactory;
import org.allbinary.input.motion.button.TouchScreenFactory;
import org.allbinary.input.motion.gesture.observer.BasicMotionGesturesHandler;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.media.audio.AllBinaryMediaManager;
import org.allbinary.media.audio.PlayerQueue;
import org.allbinary.media.audio.PrimaryPlayerQueueFactory;
import org.allbinary.media.audio.SecondaryPlayerQueueFactory;
import org.allbinary.media.audio.SelectSound;
import org.allbinary.thread.SecondaryThreadPool;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;
import org.allbinary.business.advertisement.GameAdStateFactory;
import org.allbinary.game.GameAdState;
import org.allbinary.game.input.GameInputStrings;
import org.allbinary.game.layer.SWTUtil;
import org.allbinary.game.resource.ResourceLoadingLevel;
import org.allbinary.game.resource.ResourceLoadingLevelFactory;
import org.allbinary.game.score.HighScoresHelperBase;
import org.allbinary.game.score.NoHighScoresFactory;
import org.allbinary.game.score.NullHighScoresSingletonFactory;
import org.allbinary.graphics.displayable.CanvasStrings;
import org.allbinary.graphics.displayable.GameTickDisplayInfoSingleton;
import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.graphics.opengles.CurrentDisplayableFactory;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.graphics.opengles.OpenGLFeatureUtil;
import org.allbinary.graphics.opengles.OpenGLThreadUtil;
import org.allbinary.graphics.paint.PaintableInterface;
import org.allbinary.input.gyro.SensorGameUpdateProcessor;
import org.allbinary.input.gyro.SingleSensorGameUpdateProcessor;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.time.GameTickTimeDelayHelper;
import org.allbinary.util.BasicArrayList;

public class AllBinaryGameCanvas
extends RunnableCanvas
implements AllBinaryGameCanvasInterface, GameCanvasRunnableInterface,
        MenuListener, IntermissionCompositeInterface,
        IntermissionEnableListenerInterface, PopupMenuInterface,
        DisplayChangeEventListener
{
    protected final BasicColorFactory basicColorFactory = BasicColorFactory.getInstance();
    protected final BasicColorSetUtil basicSetColorUtil = BasicColorSetUtil.getInstance();
    protected final TouchFeatureFactory touchFeatureFactory = TouchFeatureFactory.getInstance();
    protected final OpenGLFeatureFactory openGLFeatureFactory = OpenGLFeatureFactory.getInstance();
    protected final TouchButtonFactory touchButtonFactory = TouchButtonFactory.getInstance();
    protected final GameAdStateFactory gameAdStateFactory = GameAdStateFactory.getInstance();
    protected final GameInputStrings gameInputStrings = GameInputStrings.getInstance();

    private final GameTickTimeDelayHelper gameTickTimeDelayHelper = GameTickTimeDelayHelperFactory.getInstance();
    private final GameTickDisplayInfoSingleton gameTickDisplayInfoSingleton = GameTickDisplayInfoSingleton.getInstance();

    public final GameCanvasRunnable gameRunnable = new GameCanvasRunnable(this);
    public final GameCanvasPauseRunnable gamePauseRunnable = new GameCanvasPauseRunnable(this);
    
    protected Paintable gameSpecificPaintable = NullPaintable.getInstance();

    private final SensorGameUpdateProcessor sensorGameUpdateProcessor = new SingleSensorGameUpdateProcessor();
    protected EndGameInfo endGameInfo = new EndGameInfo();
    private final IntermissionInterface startIntermissionInterface = new Intermission();
    private final IntermissionInterface endLevelIntermissionInterface = new Intermission();
    private static final int id = 0;
    protected AllBinaryGameLayerManager gameLayerManager;
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
    protected final HighScoresHelperBase highScoresHelper;
    private boolean highScoreSubmitted;
    private final HighScoresPaintable realHighScoresPaintable = new HighScoresPaintable();
    private Paintable highScoresPaintable = NullPaintable.getInstance();

    // TODO TWB hack around key event handling performance
    private BasicArrayList localPlayerGameInputList = new BasicArrayList();
            //NoPlayerGameInput.getInstance();
    private boolean isCheating;
    private Hashtable hashtable;
    private boolean isSingleKeyRepeatableProcessing;
    private BasicBuildGameInitializerFactory gameInitializationInterfaceFactoryInterface;
    private Paintable touchButtonsPaintable = NullPaintable.getInstance();
    protected Paintable touchPaintable;
    private PlayerGameInput cheatProcessor = NoPlayerGameInput.getInstance();
    private Processor gameInputProcessor = Processor.getInstance();
    private Processor endGameProcessor = Processor.getInstance();
    private Processor realEndGameProcessor = Processor.getInstance();
    private Processor startIntermissionProcessor = Processor.getInstance();
    private Processor realStartIntermissionProcessor = Processor.getInstance();
    private Paintable endGamePaintable;
    private Paintable endGameStatePaintable;
    protected Paintable nonBotPaintable;
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

    protected ColorFillBasePaintable colorFillPaintable =
        ColorFillPaintableFactory.getInstance(
                basicColorFactory.BLACK, true);

    private final BaseGameStatistics baseGameStatistics =
            GameStatisticsFactory.getInstance();

    private final PlayerQueue primaryPlayerQueue =
        PrimaryPlayerQueueFactory.getInstance();

    private final PlayerQueue secondaryPlayerQueue =
        SecondaryPlayerQueueFactory.getInstance();

    protected final String BUILD_GAME = "buildGame";

    protected final GameTypeFactory gameTypeFactory = GameTypeFactory.getInstance();

    protected final ScreenCapture screenCapture = ScreenCaptureFactory.getInstance();

    private final BasicMotionGesturesHandler basicMotionGesturesHandler =
            BasicMotionGesturesHandler.getInstance();
    private final GameKeyEventHandler gameKeyEventHandler = GameKeyEventHandler.getInstance();

    private final DemoGameBehavior gameBehavior;
    private final BaseMenuBehavior menuBehavior;

    private PaintableInterface progressPaintable = ProgressCanvasFactory.getLazyInstance();
    
    public AllBinaryGameCanvas(
            final CommandListener commandListener,
            final AllBinaryGameLayerManager gameLayerManager,
            final HighScoresFactoryInterface highScoresFactoryInterface,
            final BasicBuildGameInitializerFactory gameInitializationInterfaceFactoryInterface,
            final boolean buffered) throws Exception
    {
        super(commandListener, CanvasStrings.getInstance().EMPTY_CHILD_NAME_LIST);

        this.highScoresHelper = highScoresFactoryInterface.createHighScoresHelper();
        this.gameInitializationInterfaceFactoryInterface = gameInitializationInterfaceFactoryInterface;
        this.init(gameLayerManager, buffered);

        this.highScoresFactoryInterface = highScoresFactoryInterface;

        if (this.gameLayerManager.getGameInfo().getGameType() == gameTypeFactory.BOT) {
            this.gameBehavior = DemoGameBehavior.getInstance();
            this.menuBehavior = BaseMenuBehavior.getInstance();
        } else {
            this.gameBehavior = BaseGameBehavior.getInstance();
            this.menuBehavior = this.getInGameMenuBehavior();
        }

        this.initSpecialPaint();

        this.initPopupMenu();

        this.initMenu();

        DisplayChangeEventHandler.getInstance().addListener(this);

    }

    //Null GameCanvas
    public AllBinaryGameCanvas(final AllBinaryGameLayerManager gameLayerManager)
    {
        this.highScoresHelper = NoHighScoresFactory.getInstance().createHighScoresHelper();

        if (this.gameLayerManager.getGameInfo().getGameType() == gameTypeFactory.BOT) {
            this.gameBehavior = DemoGameBehavior.getInstance();
            this.menuBehavior = BaseMenuBehavior.getInstance();
        } else {
            this.gameBehavior = BaseGameBehavior.getInstance();
            this.menuBehavior = this.getInGameMenuBehavior();
        }

        this.gameLayerManager = gameLayerManager;

        this.highScoresFactoryInterface = null;
    }

    public AllBinaryGameCanvas()
    {

        this.highScoresHelper = NoHighScoresFactory.getInstance().createHighScoresHelper();

//        if (this.gameLayerManager.getGameInfo().getGameType() == gameTypeFactory.BOT) {
            this.gameBehavior = DemoGameBehavior.getInstance();
            this.menuBehavior = BaseMenuBehavior.getInstance();
//        } else {
//            this.gameBehavior = BaseGameBehavior.getInstance();
//            this.menuBehavior = this.getInGameMenuBehavior();
//        }

        this.highScoresFactoryInterface = null;
    }

    public BaseMenuBehavior getInGameMenuBehavior() {
        return InGameMenuBehavior.getInstance();
    }

    public void setCurrentThread()
    {
        final Features features = Features.getInstance();
        final HTMLFeatureFactory htmlFeatureFactory = HTMLFeatureFactory.getInstance();

        if(features.isDefault(htmlFeatureFactory.HTML))
        {
            super.setCurrentThreadFake();
        }
        else
        {
            super.setCurrentThread();
        }
    }

    public void onEvent(final AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }

    public void onDisplayChangeEvent(final DisplayChangeEvent displayChangeEvent)
    {
        try
        {
            this.menuBehavior.onDisplayChangeEvent(this, displayChangeEvent);
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, this.canvasStrings.ON_DISPLAY_CHANGE_EVENT, e));
        }
    }

    public void updateMenu(final DisplayChangeEvent displayChangeEvent) throws Exception {

        //MyFont.getInstance().update();

        //LogUtil.put(LogFactory.getInstance(new StringMaker().append(commonLabels.START_LABEL).append(displayInfoSingleton.toString()).append(MyFont.getInstance().toString()).toString(), this, this.canvasStrings.ON_DISPLAY_CHANGE_EVENT));

        final Rectangle popupMenuRectangle = FormUtil.getInstance().createPopupMenuRectangle();
        ((BasicPopupMenuPaintable) this.getOpenMenuPaintable()).init(popupMenuRectangle);

        if (this.getPopupMenuInputProcessor() != NoMenuInputProcessor.getInstance()) {
            ((PopupMenuInputProcessor) this.getPopupMenuInputProcessor()).init(popupMenuRectangle);
        }

        final FormUtil formUtil = FormUtil.getInstance();
        final FormType formType = FormTypeFactory.getInstance().getFormType();
        final Rectangle rectangle = formUtil.createFormRectangle();
        this.menuForm.init(rectangle, formType);

        //PreLogUtil.put(this.currentTouchInputFactory.toString(), this, this.canvasStrings.ON_DISPLAY_CHANGE_EVENT);
        // touchButtonFactory.toggle(this.isPaused(),
        if (this.currentTouchInputFactory != null) {
            touchButtonFactory.toggle(this.isPaused(), this.currentTouchInputFactory.getList());
        }

        // PreLogUtil.put(TouchButtonLocationHelper.getInstance().toString(), this, this.canvasStrings.ON_DISPLAY_CHANGE_EVENT);

    }

    /*
     * public void setRunning(boolean running) { if (!this.isRunning()) {
     * this.menuInputProcessor = NoMenuInputProcessor.getInstance(); }
     *
     * super.setRunning(running); }
     */
    public void processSleep() throws Exception
    {
        //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "processSleep"));

        super.processSleep();

        if (AllBinaryMediaManager.update())
        {
            if (!primaryPlayerQueue.process())
            {
                secondaryPlayerQueue.process();
            }
        }

        if (this.menuInputProcessor.processInput() != -1)
        {
            // LogUtil.put(LogFactory.getInstance("Menu Processing While Sleeping", this, "processSleep"));

            // this.processPaintable.process();
            this.runnableCanvasRefreshHelper.process();
        }
    }

    protected void initPopupMenu() throws Exception
    {
        //LogUtil.put(LogFactory.getInstance("initPopupMenu", this, commonStrings.PROCESS));

        final Features features = Features.getInstance();

        // Popup Menu Tab Init
        final Rectangle popupMenuRectangle = FormUtil.getInstance().createPopupMenuRectangle();

        if (features.isFeature(touchFeatureFactory.TOUCH_ENABLED))
        {
            //LogUtil.put(LogFactory.getInstance("initPopupMenu - touch", this, commonStrings.PROCESS));

            this.setOpenMenuPaintable(new BasicPopupMenuPaintable(
                    popupMenuRectangle,
                    this.gameLayerManager.getBackgroundBasicColor(),
                    this.gameLayerManager.getForegroundBasicColor()));

            this.setPopupMenuInputProcessor(new PopupMenuInputProcessor(
                    new BasicArrayList(), -1, this, popupMenuRectangle));
        }
    }

    protected void initMenu()
    {
        try
        {
            //LogUtil.put(LogFactory.getInstance("initMenu", this, commonStrings.PROCESS));
            this.menuBehavior.initMenu(this);

        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "initMenu", e));
        }
    }

    protected void initMenu2() throws Exception
    {
        //LogUtil.put(LogFactory.getInstance("initMenu - not bot", this, commonStrings.PROCESS));

        this.closeMenu();

        final FormUtil formUtil = FormUtil.getInstance();
        final FormType formType = FormTypeFactory.getInstance().getFormType();

        final GameLimitedCommandTextItemArrayFactory gameLimitedCommandTextItemArrayFactory
                = GameLimitedCommandTextItemArrayFactory.getInstance();

        final CommandTextItemArrayFactory commandTextItemArrayFactory
                = gameLimitedCommandTextItemArrayFactory.getCommandTextItemArrayFactory();

        final CustomItem[] items = commandTextItemArrayFactory.getInstance(
                this.getCommandStack(), this.gameLayerManager
                .getBackgroundBasicColor(), this.gameLayerManager.getForegroundBasicColor());

        final Rectangle rectangle = formUtil.createFormRectangle();

        this.setMenuForm(CommandCurrentSelectionFormFactory.getInstance(
                StringUtil.getInstance().EMPTY_STRING,
                items,
                rectangle, formType, 25, false,
                this.gameLayerManager.getBackgroundBasicColor(),
                this.gameLayerManager.getForegroundBasicColor()));

        //this.setMenuInputProcessor( new DemoCanvasBasicStartInputProcessor( new BasicArrayList(), this));
        final ScrollSelectionForm scrollSelectionForm = this.getMenuForm();

        final Features features = Features.getInstance();

        if (features.isFeature(touchFeatureFactory.TOUCH_ENABLED)) {
            //LogUtil.put(LogFactory.getInstance("initMenu - touch", this, commonStrings.PROCESS));
            this.mainMenuInputProcessor = new PopupCommandFormInputProcessor(
                    new BasicArrayList(), -1, this, scrollSelectionForm,
                    (PopupMenuInputProcessor) this.getPopupMenuInputProcessor());
        } else {
            //LogUtil.put(LogFactory.getInstance("initMenu - no touch", this, commonStrings.PROCESS));
        }

        this.setMenuInputProcessor(this.getPopupMenuInputProcessor());

        if (scrollSelectionForm != ScrollSelectionFormNoneFactory.getInstance()) {
            //LogUtil.put(LogFactory.getInstance("initMenu - scroll", this, commonStrings.PROCESS));
            this.setFormPaintable(new FormPaintable(scrollSelectionForm));
        } else {
            //LogUtil.put(LogFactory.getInstance("initMenu - no scroll", this, commonStrings.PROCESS));
        }

        this.closeMenu();
    }

    public void updateMenu()
    {
        try
        {
            this.menuBehavior.updateMenu(this);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "initMenu", e));
        }

    }

    public void updateMenu2() throws Exception
    {
        final ScrollSelectionForm scrollSelectionForm = this.getMenuForm();

        scrollSelectionForm.deleteAll();

        final GameLimitedCommandTextItemArrayFactory gameLimitedCommandTextItemArrayFactory
                = GameLimitedCommandTextItemArrayFactory.getInstance();

        final CommandTextItemArrayFactory commandTextItemArrayFactory = gameLimitedCommandTextItemArrayFactory
                .getCommandTextItemArrayFactory();

        final CustomItem[] items = commandTextItemArrayFactory.getInstance(
                this.getCommandStack(), this.gameLayerManager
                .getBackgroundBasicColor(), this.gameLayerManager.getForegroundBasicColor());

        final int size = items.length;
        for (int index = 0; index < size; index++) {
            scrollSelectionForm.append(items[index]);
        }

        final FormUtil formUtil = FormUtil.getInstance();
        final FormType formType = FormTypeFactory.getInstance().getFormType();
        final Rectangle rectangle = formUtil.createFormRectangle();
        scrollSelectionForm.init(rectangle, formType);

    }

    public synchronized void pause()
    {
    	//final String METHOD_NAME = "pause";
        //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, METHOD_NAME));
        // PreLogUtil.put(commonStrings.START, this, METHOD_NAME);

        this.gameBehavior.pause(this);

        this.closeMenu();

        super.pause();

        touchButtonFactory.toggle(this.isPaused(), null);

        System.gc();

        //LogUtil.put(LogFactory.getInstance(commonStrings.END, this, METHOD_NAME));
    }

    public synchronized void unPause()
    {
        //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "unPause"));
        // PreLogUtil.put(commonStrings.START, this, "unPause");

        this.closeMenu();
        System.gc();
        super.unPause();
        touchButtonFactory.toggle(this.isPaused(), null);

        this.gameBehavior.unPause(this);

    }

    public boolean isPausable()
    {
        //TWB - Game is paused but UsedRunnable was set after the old runnable was called
        if (CurrentDisplayableFactory.getInstance().getUsedRunnable() == NullWaitGameRunnable.getInstance()) {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void popupMenu() throws Exception
    {
        this.menuBehavior.popupMenu(this);
    }

    public void popupMenu2() throws Exception
    {
        //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "popupMenu"));

        primaryPlayerQueue.add(SelectSound.getInstance());

        this.setMenuPaintable(this.getFormPaintable());
        this.setMenuInputProcessor(this.mainMenuInputProcessor);

        this.basicMotionGesturesHandler.addListener(this.mainMenuInputProcessor);
        this.gameKeyEventHandler.addListener(this.mainMenuInputProcessor);
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
        this.menuBehavior.closeMenu(this);
    }

    public void closeMenu2()
    {
        //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "closeMenu"));

        this.setMenuPaintable(this.getOpenMenuPaintable());

        this.basicMotionGesturesHandler.removeListener(this.mainMenuInputProcessor);
        this.gameKeyEventHandler.removeListener(this.mainMenuInputProcessor);

        this.setMenuInputProcessor(this.getPopupMenuInputProcessor());
    }

    public void open()
    {
        this.basicMotionGesturesHandler.addListener(this.menuInputProcessor);
        this.gameKeyEventHandler.addListener(this.menuInputProcessor);
    }

    public void close()
    {
        this.basicMotionGesturesHandler.removeListener(this.menuInputProcessor);
        this.gameKeyEventHandler.removeListener(this.menuInputProcessor);

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
        this.menuBehavior.initSpecialPaint(this);
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

        //LogUtil.put(LogFactory.getInstance(
        //"isDoubleBuffered: ").append(this.isBuffered()).append("isDoubleBuffered: ").append(this.isDoubleBuffered(),
        //this, commonStrings.CONSTRUCTOR));

        this.setGameInputProcessor(Processor.getInstance());

        final Features features = Features.getInstance();

        if (features.isFeature(GameFeatureFactory.getInstance().CHEATING))
        {
            this.isCheating = true;
            this.cheatProcessor = new CheatGameInputProcessor(this);

            this.gameKeyEventHandler.addListener(this.cheatProcessor);

        }
        else
        {
            this.isCheating = false;
            this.cheatProcessor = NoPlayerGameInput.getInstance();
        }

        this.realEndGameProcessor = new EndGameProcessor(this);
        this.setEndGameProcessor(Processor.getInstance());

        this.realStartIntermissionProcessor = new StartIntermissionProcessor(this);
        this.startIntermissionProcessor = Processor.getInstance();

        this.setEndGameStatePaintable(new EndGamePaintable(this));
        this.setEndGamePaintable(NullPaintable.getInstance());

        this.setIntermissionPaintable(NullPaintable.getInstance());

        this.getStartIntermissionInterface().setListener(this);
    }

    public void notifyIntermission(boolean enable)
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
        ForcedLogUtil.log(commonStrings.NOT_IMPLEMENTED, this);
    }

    protected synchronized void initConfigurable(final AbeClientInformationInterface abeClientInformation) throws Exception
    {
        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
        progressCanvas.addPortion(50, "Setting Configurables");

        GameInitializationUtil.getInstance().initGame(abeClientInformation, this,
                gameInitializationInterfaceFactoryInterface);

        final GameFeatureFactory gameFeatureFactory = GameFeatureFactory.getInstance();

        final ChangedGameFeatureListener changedGameFeatureListener =
            ChangedGameFeatureListener.getInstance();

        if (changedGameFeatureListener.isChanged(gameFeatureFactory.SOUND))
        {
            final Features features = Features.getInstance();

            LogUtil.put(LogFactory.getInstance(new StringMaker().append("Sound Changing To: ").append(
                    features.isFeature(gameFeatureFactory.SOUND)).toString(), this, "initConfigurable"));

            this.mediaInit();
            changedGameFeatureListener.remove(gameFeatureFactory.SOUND);
        }

        final SensorFeatureFactory sensorFeatureFactory = SensorFeatureFactory.getInstance();

        if (changedGameFeatureListener.isChanged(sensorFeatureFactory.ORIENTATION_SENSORS)
                || changedGameFeatureListener.isChanged(sensorFeatureFactory.NO_ORIENTATION)
                || changedGameFeatureListener.isChanged(sensorFeatureFactory.SIMULATED_ORIENTATION_SENSORS))
        {
            // private final AllBinarySensor[] allBinarySensorArray = new
            // AllBinarySensor[1];

            changedGameFeatureListener.remove(
                    sensorFeatureFactory.NO_ORIENTATION);
            changedGameFeatureListener.remove(
                    sensorFeatureFactory.ORIENTATION_SENSORS);
            changedGameFeatureListener.remove(
                    sensorFeatureFactory.SIMULATED_ORIENTATION_SENSORS);
        }

        final ResourceLoadingLevelFactory resourceLoadingLevelFactory =
            ResourceLoadingLevelFactory.getInstance();

        this.loadResources(resourceLoadingLevelFactory.LOAD_GAME);
    }

    protected void init(final AbeClientInformationInterface abeClientInformation) throws Exception
    {
        this.initConfigurable(abeClientInformation);

        this.processorInit();

        // Since touch button selection is based on sensors this must come first
        this.sensorGameUpdateProcessor.process(this.gameLayerManager);
        this.sensorGameUpdateProcessor.sendNotifications(this.gameLayerManager);

        this.initTouch();
    }

    protected void initTouch() throws Exception
    {
        final GameInitializedEvent gameInitializedEvent = GameInitializationUtil.getInstance().EVENT;

        final ResourceLoadingLevelFactory resourceLoadingLevelFactory =
            ResourceLoadingLevelFactory.getInstance();

        gameInitializedEvent.setResourceLoadingLevel(resourceLoadingLevelFactory.LOAD_TOUCH);

        GameInitializedEventHandler.getInstance().fireEvent(gameInitializedEvent);

        touchButtonFactory.defaultList();

        this.updateTouch();

        this.postInitTouch();
    }

    public void updateCurrentTouchInputFactory(
            final BaseTouchInput nextTouchInput) throws Exception
    {
        if (nextTouchInput != this.currentTouchInputFactory)
        {
            this.currentTouchInputFactory = nextTouchInput;

            // LogUtil.put(LogFactory.getInstance(this.currentTouchInputFactory,
            // this, "updateCurrentTouchInputFactory"));
            PreLogUtil.put(this.currentTouchInputFactory.toString(), this, "updateCurrentTouchInputFactory");

            touchButtonFactory.toggle(this.isPaused(),
                    this.currentTouchInputFactory.getList());
        }
    }

    protected void updateTouch() throws Exception
    {
        this.gameBehavior.updateTouch(this);
    }

    protected void updateTouch2() throws Exception
    {
        final Features features = Features.getInstance();
        if (features.isFeature(touchFeatureFactory.AUTO_HIDE_SHOW_SCREEN_BUTTONS)) {
            if (this.gameLayerManager.getGameInfo().getCurrentLevel() - getStartLevel() == 1) {
                this.setTouchPaintable(NullPaintable.getInstance());
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
        final GameCommandsFactory gameCommandsFactory = GameCommandsFactory.getInstance();
        final MyCommandsFactory myCommandsFactory = MyCommandsFactory.getInstance();
        final HTMLFeatureFactory htmlFeatureFactory = HTMLFeatureFactory.getInstance();

        if (DebugFactory.getInstance() != NoDebug.getInstance())
        {
            this.addCommand(gameCommandsFactory.START_TRACE);
        }

        this.addCommand(gameCommandsFactory.RESTART_COMMAND);

        this.addCommand(myCommandsFactory.PAUSE_COMMAND);

        this.addCommand(gameCommandsFactory.QUIT_COMMAND);

        final boolean isOverScan = OperatingSystemFactory.getInstance().getOperatingSystemInstance().isOverScan();

        final Features features = Features.getInstance();

        if(features.isDefault(htmlFeatureFactory.HTML)) {
        } else if(SWTUtil.isSWT) {
        } else if(!isOverScan) {

            if (TouchScreenFactory.getInstance().isTouch() && new InGameFeatures().isAny())
            {
                // System.out.println("InGameOptions");
                this.addCommand(InGameOptionsForm.DISPLAY);
            }

            // this.addCommand(GameCommands.DISPLAY_SAVE_FORM);
            this.addCommand(gameCommandsFactory.SAVE);
            this.addCommand(gameCommandsFactory.DISPLAY_LOAD_FORM);

        }
    }

    public void itemStateChanged(Item item)
    {
        try
        {
            final String itemLabel = item.getLabel();

            LogUtil.put(LogFactory.getInstance(new StringMaker().append("Item: ").append(itemLabel).toString(), this, "itemStateChanged"));

            if (item instanceof ChoiceGroup)
            {
                final GameFeatureUtil gameFeatureUtil = GameFeatureUtil.getInstance();

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
            this.sensorGameUpdateProcessor.process(this.gameLayerManager);

            // Swap between different touch button setups
            this.updateTouch();

            // Update whether they should be displayed or not
            this.updateScreenButtonPaintable();

            this.sensorGameUpdateProcessor.sendNotifications(this.gameLayerManager);

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
        this.gameBehavior.updateScreenButtonPaintable(this);
    }

    public void updateScreenButtonPaintable2() {

        final Features features = Features.getInstance();

        if (features.isFeature(touchFeatureFactory.AUTO_HIDE_SHOW_SCREEN_BUTTONS))
        {
            // If touch buttons are visible
            this.setTouchPaintable(this.getTouchButtonsPaintable());
            this.setStartLevel(this.gameLayerManager.getGameInfo()
                    .getCurrentLevel());
        }
        else if (features.isFeature(touchFeatureFactory.SHOW_SCREEN_BUTTONS))
        {
            // PreLogUtil.put(touchFeatureFactory.SHOW_SCREEN_BUTTONS.getName(),
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

        //this.cleanupGame();
        //this.closeMenu();
        //this.close();
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
        if(highScoreSubmitted) {
            this.highScoresHelper.setSelectedHighScores(NullHighScoresSingletonFactory.getInstance());
        }

        LogUtil.put(LogFactory.getInstance(new StringMaker().append("isHighScoreSubmitted: ").append(highScoreSubmitted).toString(), this, "setHighScoreSubmitted"));
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public void setGameState(final GameState gameState) throws Exception
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append("Game State: ").append(gameState).toString(), this, "setGameState"));
        // PreLogUtil.put("Game State: ").append(gameState, this, "setGameState");

        this.gameState = gameState;
        this.gameStateTimeHelper.setStartTime();

        this.updateEndGameProcessor();
        this.updateGameKeyEventProcessor();

        this.gameBehavior.setGameState(this);
    }

    public void setGameState() throws Exception {
        final GameAdState gameAdState = gameAdStateFactory.getCurrentInstance();

        gameAdState.processAdState(this.gameState, this.gameLayerManager.getGameInfo().getGameType());

        if (this.gameState != GameState.PLAYING_GAME_STATE) {
            gameAdState.processPageAdState();
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

    protected void removeAllGameKeyInputListenersOnBuild() {
        this.removeAllGameKeyInputListeners();
    }

    protected void removeAllGameKeyInputListeners()
    {
        this.gameBehavior.removeAllGameKeyInputListeners(this);
    }

    protected void removeAllGameKeyInputListeners2() {

        // System.out.println("Clearing Keys From Last Level");
        LogUtil.put(LogFactory.getInstance("Remove PlayerInput Listeners", this, "removeAllGameKeyInputListeners"));

        for (int index = this.localPlayerGameInputList.size() - 1; index >= 0; index--) {
            PlayerGameInput playerGameInput = (PlayerGameInput) this.localPlayerGameInputList.get(index);
            this.removeKeyInputListener(playerGameInput);
            playerGameInput.removeNonAIInputGameKeyEvents();
        }
    }

    public void removeKeyInputListener(final PlayerGameInput playerGameInput) {
        this.gameKeyEventHandler.removeListener(playerGameInput);
    }

    /*
     * private void removeGameKeyInputListeners() { //for cheating
     * UpGameKeyEventHandler.getInstance().removeListener(this);
     * DownGameKeyEventHandler.getInstance().removeListener(this); }
     */
    protected void updateEndGameProcessor() throws Exception
    {
        this.gameBehavior.updateEndGameProcessor(this);
    }

    protected void updateEndGameProcessor2() throws Exception
    {
        if (this.getGameState() == SHOW_END_RESULT_GAME_STATE
                || this.getGameState() == SHOW_HIGH_SCORE_GAME_STATE) {
            this.setEndGameProcessor(this.realEndGameProcessor);
        } else {
            this.setEndGameProcessor(Processor.getInstance());
        }
    }

    public void buildGame(final boolean isPortion) throws Exception
    {
    }

    protected void cleanupGame() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "cleanupGame"));

        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
        progressCanvas.addPortion(10, "Cleaning Up");

        primaryPlayerQueue.clear();
        secondaryPlayerQueue.clear();

        GameLevelDisplayChangeEventListenersFactory.getInstance().clear();
    }

    public void loadResources(final ResourceLoadingLevel resourceLoadingLevel) throws Exception
    {
        final GameInitializedEvent gameInitializedEvent = GameInitializationUtil.getInstance().EVENT;

        gameInitializedEvent.setResourceLoadingLevel(resourceLoadingLevel);

        GameInitializedEventHandler.getInstance().fireEvent(gameInitializedEvent);
    }

    public void loadResources(final int level) throws Exception
    {
        final GameInitializedEvent gameInitializedEvent = GameInitializationUtil.getInstance().EVENT;

        gameInitializedEvent.setLevel(level);

        GameInitializedEventHandler.getInstance().fireEvent(gameInitializedEvent);
    }

    public void updateColor() throws Exception
    {
    }

    public void buildGame(final int portion) throws Exception
    {
        screenCapture.endRecording();
        screenCapture.startRecording();

        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
        final Features features = Features.getInstance();

        progressCanvas.addPortion(portion, "Generic Build");

        this.removeAllGameKeyInputListenersOnBuild();

        this.updateTouch();

        progressCanvas.addPortion(portion, "High Scores");

        this.highScoresFactoryInterface.fetchHighScores(this.gameLayerManager.getGameInfo(), this.highScoresHelper);

        this.setHighScoresPaintable(NullPaintable.getInstance());

        progressCanvas.addPortion(portion, "Finishing.");

        this.getEndGameInfo().update(this.getLayerManager().getGameInfo(), this);

        this.isSingleKeyRepeatableProcessing =
            features.isFeature(InputFeatureFactory.getInstance().SINGLE_KEY_REPEAT_PRESS);

        this.setGameOver(false);

        progressCanvas.addPortion(portion, "Finishing..");

        // LogUtil.put(LogFactory.getInstance("Game Color Change", this, BUILD_GAME));

        this.updateColor();

        this.getStartIntermissionPaintable().update();

        if (!this.isRunningInAnotherThread())
        {
            return;
            // If this is not the root window then it does not change the screen
        }

        progressCanvas.addPortion(portion, "Finishing...");

        if (this.isMainCanvas() || !this.isInitialized())
        {
            // Don't try to end progress if demogame and not in resource
            // debugging mode
            if (features.isFeature(MainFeatureFactory.getInstance().LOAD_ONDEMAND))
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

        //HTML5 change
        //if (this.gameCanvasStartListener != null)
        if (this.getCustomCommandListener() == null)
        {
            LogUtil.put(LogFactory.getInstance("Show Game Paintable in DemoCanvas Thread", this, BUILD_GAME));

            this.gameCanvasStartListener.showGamePaintable();
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("No GameCanvasStartListener", this, BUILD_GAME));
        }

        this.colorFillPaintable.setBasicColor(this.gameLayerManager.getBackgroundBasicColor());

        this.gameBehavior.buildGame(this);
    }

    public void buildGame2() {
        //LogUtil.put(LogFactory.getInstance("Clearing Keys From Last Level", this, BUILD_GAME));
        PreLogUtil.put(new StringMaker().append("Enabling PlayerGameInputs: ").append(this.localPlayerGameInputList.size()).toString(), this, BUILD_GAME);

        PlayerGameInput playerGameInput;
        for (int index = this.localPlayerGameInputList.size() - 1; index >= 0; index--) {
            playerGameInput = (PlayerGameInput) this.localPlayerGameInputList.get(index);

            PreLogUtil.put(new StringMaker().append("Enabling PlayerGameInput: ").append(playerGameInput.toString()).toString(), this, BUILD_GAME);

            playerGameInput.removeNonAIInputGameKeyEvents();
            this.addKeyInputListener(playerGameInput);

            // ForcedLogUtil.log("DownGameKeyEventHandler: " + DownGameKeyEventHandler.getInstance().getEventListenerInterfaceList(), this);
        }
    }

    public void addKeyInputListener(final PlayerGameInput playerGameInput) {
        GameKeyEventHandler.getInstance().addListener(playerGameInput, playerGameInput.getPlayerInputId());
    }

    private DemoCanvas gameCanvasStartListener;

    public void setGameCanvasStartListener(DemoCanvas gameCanvasStartListener)
    {
        this.gameCanvasStartListener = gameCanvasStartListener;
    }

    public void loadState() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "loadState"));
        Hashtable hashtable = getLoadStateHashtable();

        if (hashtable != null && hashtable.size() > 0)
        {
            final Integer levelInteger = Integer.valueOf((String) hashtable
                    .get(GameInfo.LEVEL_NAME));
            final GameInfo gameInfo = this.gameLayerManager.getGameInfo();
            gameInfo.setCurrentLevel(levelInteger.intValue());
        }
    }

    public Hashtable getLoadStateHashtable() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(commonLabels.START_LABEL).append(this.hashtable).toString(), this, "getLoadStateHashtable"));
        return this.hashtable;
    }

    public void setLoadStateHashtable(final Hashtable hashtable)
    {
        LogUtil.put(LogFactory.getInstance(
                new StringMaker().append(commonLabels.START_LABEL).append(hashtable).toString(), this,
                "setLoadStateHashtable"));
        this.hashtable = hashtable;
    }

    public Hashtable getCurrentStateHashtable()
    {
        final Hashtable hashtable = new Hashtable();

        final int level = this.gameLayerManager.getGameInfo().getCurrentLevel();

        hashtable.put(GameInfo.LEVEL_NAME.toString(), Integer.toString(level));

        LogUtil.put(LogFactory.getInstance(new StringMaker().append("End: ").append(hashtable).toString(), this, "getCurrentStateHashtable"));

        return hashtable;
    }

    public void paintGameOver(Graphics graphics)
    {
        ForcedLogUtil.log(commonStrings.NOT_IMPLEMENTED, this);
    }

    public void draw(Graphics graphics)
    {
        this.colorFillPaintable.paint(graphics);

        this.basicSetColorUtil.setBasicColor(graphics,
                this.gameLayerManager.getForegroundBasicColor());

        this.gameSpecificPaintable.paint(graphics);
    }

    public void clear(final Graphics graphics)
    {
        this.colorFillPaintable.paint(graphics);
    }

    public void paint(final Graphics graphics)
    {
        //PreLogUtil.put("AllBinaryGameCanvas", this, "paint");

        //TWB - was in MyCanvas paint -- super.paint(graphics);
        baseGameStatistics.nextRefresh();

        //// Disabled buffering
        //// this.processPaintable.paint(graphics);
        //// This is what is called without buffering
        this.draw(graphics);
        ////this.setDisplayed(true);
        //// End - This is what is called without buffering

        menuPaintable.paint(graphics);
        
        this.progressPaintable.paint(graphics);
    }

    public void paintThreed(final Graphics graphics)
    {

    }

    // TWB - This hack method should be removed once I figure out how it should
    // be removed
    public void processEndLevelIntermissionGameState() throws Exception
    {
    }

    public void nonBotPaint(Graphics graphics)
    {
        this.endGamePaintable.paint(graphics);
        this.intermissionPaintable.paint(graphics);
        this.paintIntermission(graphics);
        this.touchPaintable.paint(graphics);
    }

    // TWB - This hack method should be removed once I figure out how it should
    // be removed
    public void paintIntermission(Graphics graphics)
    {
    }

    private final InputProcessor rawGameInputProcessor = new GameCanvasInputProcessor(this);
    private final InputProcessor rawInputProcessor = new FormInputProcessor(this);
    private InputProcessor inputProcessor = getRawGameInputProcessor();

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
        //LogUtil.put(LogFactory.getInstance(new StringMaker().append(this.commonLabels.START_LABEL).append(this.inputProcessor.toString()).append(CommonSeps.getInstance().SPACE).append(keyCode).toString(), this, gameInputStrings.KEY_PRESSED));
        // ForcedLogUtil.log(commonStrings.START_LABEL +
        // this.inputProcessor.toString()).append(commonStrings.SPACE +
        // keyCode, this);

        // this.addGameKeyEvent(keyCode, false);
        this.inputProcessor.keyPressed(keyCode, deviceId);
    }

    public void keyRepeated(int keyCode, int deviceId)
    {
        // LogUtil.put(LogFactory.getInstance("Key Repeated: " +
        // Integer.toHexString(keyCode),
        // this, gameInputStrings.KEY_REPEATED));
        if (this.isSingleKeyRepeatableProcessing)
        {
            // this.addGameKeyEvent(keyCode, true);
            this.inputProcessor.keyPressed(keyCode, deviceId);
        }
    }

    public void keyReleased(final int keyCode, final int deviceId)
    {
        //LogUtil.put(LogFactory.getInstance(new StringMaker().append(this.commonLabels.START_LABEL).append(this.inputProcessor.toString()).append(CommonSeps.getInstance().SPACE).append(keyCode).toString(), this, gameInputStrings.KEY_RELEASED));
        this.inputProcessor.keyReleased(this, keyCode, deviceId);
    }

    // private void addGameKeyEvent(int keyCode, boolean repeated)
    // {
    // }

    public void handleRawKey(final int keyCode, final int deviceId, final boolean repeated) throws Exception {
    }

    protected int endProgress(boolean isProgress)
    {
        int portion = 30;
        if (isProgress && this.isMainCanvas())
        {
            final MyCommandsFactory myCommandsFactory = MyCommandsFactory.getInstance();

            final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
            progressCanvas.start();
            this.getCustomCommandListener().commandAction(
                    myCommandsFactory.SET_DISPLAYABLE,progressCanvas);
            // progressCanvas.waitUntilDisplayed();
            portion = 4;
        }
        return portion;
    }

    protected void processPlayingGame() throws Exception
    {
        //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "processPlayingGame"));

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
         * //LogFactory.getInstance( "Elapsed Time: ").append(totalTimeElapsed, this,
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
        super.process();

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
            //TWB - Playn Testing - was remarked for GameFrameRunnable but I guess I don't need that runnable?
            this.threadObjectUtil.notifyObject(this);
        }
    }

    private final int YIELD_SLEEP = 100;

    public void run()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.START_RUNNABLE, this, commonStrings.RUN));

            final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
            progressCanvas.addPortion(50, "Game Thread");

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

            final GameAdState gameAdState = gameAdStateFactory.getCurrentInstance();

            gameAdState.init();
            gameAdState.setGameIsReady(true);

            this.gameBehavior.run(this);

            LogUtil.put(LogFactory.getInstance(commonStrings.END_RUNNABLE, this, commonStrings.RUN));
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.RUN, e));
        }
    }

    public void run2() throws Exception {

        final Features features = Features.getInstance();
        final HTMLFeatureFactory htmlFeatureFactory = HTMLFeatureFactory.getInstance();

//        if (SWTUtil.isSWT) {
//
//            final Runnable runnable = new Runnable() {
//                public void run() {
//                    try {
//                        run3();
//                    } catch (Exception e) {
//                        LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.RUN, e));
//                    }
//                }
//            };
//            LogUtil.put(LogFactory.getInstance("Set SWT Thread and assign runnable: " + runnable, this, commonStrings.RUN));
//
//            final SWTProcessorUtil swtProcessorUtil = SWTProcessorUtil.getInstance();
//            final SWTRunnableProcessor swtRunnableProcessor = SWTRunnableProcessor.getInstance();
//            swtRunnableProcessor.runnable = runnable;
//            swtProcessorUtil.swtProcessor = swtRunnableProcessor;
//
//            final CurrentDisplayableFactory currentDisplayableFactory
//                    = CurrentDisplayableFactory.getInstance();
//
//            currentDisplayableFactory.setRunnable(gameRunnable);
//
//        } else 
            if (features.isDefault(openGLFeatureFactory.OPENGL_AS_GAME_THREAD)
                || features.isDefault(htmlFeatureFactory.HTML)) {
            
            if (features.isDefault(openGLFeatureFactory.OPENGL_AS_GAME_THREAD)) {
                LogUtil.put(LogFactory.getInstance(openGLFeatureFactory.OPENGL_AS_GAME_THREAD.getName(), this, commonStrings.RUN));
            }

            if (features.isDefault(htmlFeatureFactory.HTML)) {
                LogUtil.put(LogFactory.getInstance(htmlFeatureFactory.HTML.getName(), this, commonStrings.RUN));
            }

            final CurrentDisplayableFactory currentDisplayableFactory = CurrentDisplayableFactory.getInstance();

            currentDisplayableFactory.setRunnable(gameRunnable);
            currentDisplayableFactory.setDisplayable(this);

            OpenGLThreadUtil.getInstance().onResume();
        } else if (features.isDefault(openGLFeatureFactory.OPENGL_AND_GAME_HAVE_DIFFERENT_THREADS)) {

            LogUtil.put(LogFactory.getInstance(openGLFeatureFactory.OPENGL_AND_GAME_HAVE_DIFFERENT_THREADS.getName(), this, commonStrings.RUN));

            OpenGLThreadUtil.getInstance().onResume();

            while (this.isRunning()) {
                this.run3();
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
                        this.threadObjectUtil.waitObject(this);
                        // PreLogUtil.put("Notified", this, commonStrings.RUN);
                    }

                    currentDisplayableFactory.clearRunnable();

                    if (!this.isRunningInAnotherThread())
                    {
                        break;
                    }

                    this.processLoopSleep();
                }
             */
        } else {
            LogUtil.put(LogFactory.getInstance("this thread", this, commonStrings.RUN));

            while (this.isRunning()) {
                this.run3();
            }

            this.end();
        }
    }

    public void run3() throws Exception {

        this.loopTimeHelper.setStartTime(gameTickTimeDelayHelper.setStartTime());

        gameTickDisplayInfoSingleton.update();

        this.processGame();

        this.processLoopSleep();

    }

    public void setRunning(boolean running)
    {
        super.setRunning(running);

        try
        {
            final Features features = Features.getInstance();

            if (running) {
            } else {
                //If game thread is not actually running                
                if (features.isDefault(openGLFeatureFactory.OPENGL) ||
                    //features.isDefault(htmlFeatureFactory.HTML)) ||
                    SWTUtil.isSWT) {
                    
                    if (this.gameLayerManager.getGameInfo().getGameType() != gameTypeFactory.BOT) {
                        
//                        if (SWTUtil.isSWT) {
//                            LogUtil.put(LogFactory.getInstance("Set SWT Thread and assign runnable: " + NullRunnable.getInstance(), this, SET_RUNNING));
//                            final SWTRunnableProcessor swtRunnableProcessor = SWTRunnableProcessor.getInstance();
//                            swtRunnableProcessor.runnable = NullRunnable.getInstance();
//                        }
                        
                        final CurrentDisplayableFactory currentDisplayableFactory = CurrentDisplayableFactory.getInstance();
                        currentDisplayableFactory.clearRunnable();
                    }
                    this.end();
                }
            }
            
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, SET_RUNNING, e));
        }
    }

    public void end() throws Exception
    {
        screenCapture.endRecording();

        baseGameStatistics.add(new StringMaker().append(baseGameStatistics.toString()).append(CommonSeps.getInstance().NEW_LINE).toString());
        baseGameStatistics.init();

        this.gameKeyEventHandler.removeListener(this.cheatProcessor);
        this.close();
        this.removeAllGameKeyInputListeners();
        this.endGameThread();
    }

    public void endGameThread() throws Exception
    {
        DisplayChangeEventHandler.getInstance().removeListener(this);
        GameLevelDisplayChangeEventListenersFactory.getInstance().clear();

        GameAdState gameAdState =
            gameAdStateFactory.getCurrentInstance();

        gameAdState.notPlayingAdState();

    }

    // Since HighScores for a default game are not level Specific we do not
    // specify level info
    public HighScore createHighScore(long score)
    {
        final GameInfo gameInfo = this.gameLayerManager.getGameInfo();

        return new HighScore(0, "NONE", new GameInfo(gameInfo.getGameType(),
                gameInfo.getGameMode(), 0, 0), score);
    }

    // Game states
    /*
     * public boolean isLevelComplete() { return this.isLevelComplete; } public
     * void levelComplete() { this.isLevelComplete = true; }
     */
    public void setHighScore(final AbeClientInformationInterface abeClientInformation, final String name, final long score, final boolean autoSubmit, final boolean isLast) throws Exception
    {
        this.gameBehavior.setHighScore(abeClientInformation, this, name, score, autoSubmit, isLast);
    }

    public void setHighScore2(final AbeClientInformationInterface abeClientInformation, final String name, final long score, final boolean autoSubmit, final boolean isLast) throws Exception {

        final HighScore highScore = this.createHighScore(score);
        // TWB - Technically this means that if it is not a best local score
        // then it will not become a remote high score
        //if (this.getHighScoresArray()[0].isBestScore(highScore))
        //{
        final HighScoreTextBox textBox = new HighScoreTextBox(
                this.highScoresFactoryInterface,
                highScoresHelper,
                abeClientInformation,
                this.gameLayerManager.getGameInfo(),
                this.getCustomCommandListener(), name, highScore,
                this.gameLayerManager.getBackgroundBasicColor(),
                this.gameLayerManager.getForegroundBasicColor());

        if (isLast) {
            this.getCustomCommandListener().commandAction(
                    GameCommandsFactory.getInstance().SET_MENU_DISPLAYABLE,
                    textBox);
        }

        if (autoSubmit) {

            class SaveHighScoreRunnable implements Runnable {

                private final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();
                
                public void run() {
                    try {
                        //Let the endgame sound play first
//                                Thread.sleep(100);
//                                progressCanvas.addPortion(6, "Saving High Score");

                        if (isLast) {
                            textBox.submit();
                        } else {
                            textBox.saveHighScore();
                        }
                    } catch (Exception e) {
                        LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "run", e));
                        progressCanvas.end();
                    }
                }
            }

            SecondaryThreadPool.getInstance().runTask(new SaveHighScoreRunnable());
        }
        //}
        //else
        //{
        // if score is not a high score ignore it
        //this.setHighScoreSubmitted(true);
        //}
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
        //ForcedLogUtil.log("Touch Paintable: ").append(paintable, this);
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
    public EndGameInfo getEndGameInfo()
    {
        return endGameInfo;
    }

    public void setHighScoresPaintable(final Paintable highScoresPaintable)
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

    protected void clearPlayerGameInputList()
    {
        PlayerGameInput playerGameInput;
        for (int index = this.localPlayerGameInputList.size() - 1; index >= 0; index--)
        {
             playerGameInput = (PlayerGameInput) this.localPlayerGameInputList.get(index);
            this.gameKeyEventHandler.removeListener(playerGameInput);
        }

        this.localPlayerGameInputList.clear();
    }

    protected void addPlayerGameInput(final PlayerGameInput playerGameInput)
    {
        //PreLogUtil.put("Setting Player Input: ").append(playerGameInput, this, "setPlayerGameInput");

        this.localPlayerGameInputList.add(playerGameInput);
    }

    protected void setMenuInputProcessor(final BasicMenuInputProcessor menuInputProcessor)
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
    public void setMenuForm(final ScrollSelectionForm menuForm)
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
    public void setStartLevel(final int startLevel)
    {
        this.startLevel = startLevel;
    }

    protected void setTouchButtonsPaintable(final Paintable touchButtonsPaintable)
    {
        this.touchButtonsPaintable = touchButtonsPaintable;
    }

    protected Paintable getTouchButtonsPaintable()
    {
        return touchButtonsPaintable;
    }

  //TWB - multiplayer needed it to be public
    public void setGameInputProcessor(final Processor gameInputProcessor)
    {
        this.gameInputProcessor = gameInputProcessor;
    }

    protected Processor getGameInputProcessor()
    {
        return gameInputProcessor;
    }

    protected void setEndGameProcessor(final Processor endGameProcessor)
    {
        this.endGameProcessor = endGameProcessor;
    }

    protected Processor getEndGameProcessor()
    {
        return endGameProcessor;
    }

    protected void setEndGameStatePaintable(final Paintable endGameStatePaintable)
    {
        this.endGameStatePaintable = endGameStatePaintable;
    }

    protected Paintable getEndGameStatePaintable()
    {
        return endGameStatePaintable;
    }

    protected void setNonBotPaintable(final Paintable nonBotPaintable)
    {
        this.nonBotPaintable = nonBotPaintable;
    }

    protected Paintable getNonBotPaintable()
    {
        return nonBotPaintable;
    }

    protected void setStartIntermissionPaintable(final InitUpdatePaintable startIntermissionPaintable)
    {
        this.startIntermissionPaintable = startIntermissionPaintable;
    }

    protected InitUpdatePaintable getStartIntermissionPaintable()
    {
        return startIntermissionPaintable;
    }

  //TWB - multiplayer needed it to be public
    public void setMainStateProcessor(final Processor mainStateProcessor)
    {
        this.mainStateProcessor = mainStateProcessor;
    }

    protected Processor getMainStateProcessor()
    {
        return mainStateProcessor;
    }

    protected void setProcessGameProcessor(final Processor processGameProcessor)
    {
        this.processGameProcessor = processGameProcessor;
    }

  //TWB - multiplayer needed it to be public
    public Processor getProcessGameProcessor()
    {
        return processGameProcessor;
    }

    protected void setOpenMenuPaintable(final Paintable openMenuPaintable)
    {
        this.openMenuPaintable = openMenuPaintable;
    }

    protected Paintable getOpenMenuPaintable()
    {
        return openMenuPaintable;
    }

    protected void setPopupMenuInputProcessor(
            final BasicMenuInputProcessor popupMenuInputProcessor)
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
    public void setInputProcessor(final InputProcessor inputProcessor)
    {
        // LogUtil.put(LogFactory.getInstance("New: " +
        // inputProcessor.toString(), this, "setInputProcessor"));
        this.inputProcessor = inputProcessor;
    }

    protected InputProcessor getInputProcessor()
    {
        return inputProcessor;
    }

    protected void setMenuPaintable(final Paintable menuPaintable)
    {
        this.menuPaintable = menuPaintable;
    }

    protected Paintable getMenuPaintable()
    {
        return menuPaintable;
    }

    private void setFormPaintable(final Paintable formPaintable)
    {
        this.formPaintable = formPaintable;
    }

    protected Paintable getFormPaintable()
    {
        return formPaintable;
    }

  //TWB - multiplayer needed it to be public
    public void setGameSpecificPaintable(final Paintable gameSpecificPaintable)
    {
        this.gameSpecificPaintable = gameSpecificPaintable;
    }

    protected Paintable getGameSpecificPaintable()
    {
        return gameSpecificPaintable;
    }

    public boolean isSingleThread()
    {
        return OpenGLFeatureUtil.getInstance().isAnyThreed() || SWTUtil.isSWT;
    }

    public boolean isRunningInAnotherThread() {
        final Features features = Features.getInstance();
        final OpenGLFeatureFactory openGLFeatureFactory = OpenGLFeatureFactory.getInstance();
        if(features.isDefault(openGLFeatureFactory.OPENGL_AS_GAME_THREAD)) {
            return true;
        } else {
            return this.isRunning();
        }
    }
    
    public static final int TYPE = 2;
    public int getType() {
        return TYPE;
    }

}
