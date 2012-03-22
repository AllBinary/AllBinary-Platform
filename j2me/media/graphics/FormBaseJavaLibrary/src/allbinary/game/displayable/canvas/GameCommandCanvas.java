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

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.ForcedLogUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.commands.GameCommandsFactory;
import allbinary.game.configuration.feature.Features;
import allbinary.game.configuration.feature.InputFeatureFactory;
import allbinary.game.input.GameKey;
import allbinary.game.input.GameKeyFactory;
import allbinary.game.input.PlatformInputMappingFactory;
import allbinary.game.input.event.DownGameKeyEventHandler;
import allbinary.game.input.event.GameKeyEvent;
import allbinary.game.input.event.GameKeyEventFactory;
import allbinary.game.input.event.GameKeyEventHandler;
import allbinary.game.input.event.UpGameKeyEventHandler;
import allbinary.game.input.mapping.InputToGameKeyMapping;
import allbinary.graphics.PointFactory;
import allbinary.graphics.Rectangle;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.displayable.DisplayInfoSingleton;
import allbinary.graphics.displayable.MyCanvas;
import allbinary.graphics.displayable.event.DisplayChangeEvent;
import allbinary.graphics.displayable.event.DisplayChangeEventHandler;
import allbinary.graphics.displayable.event.DisplayChangeEventListener;
import allbinary.graphics.form.CommandCurrentSelectionFormFactory;
import allbinary.graphics.form.FormPaintable;
import allbinary.graphics.form.FormType;
import allbinary.graphics.form.FormTypeFactory;
import allbinary.graphics.form.PaintableForm;
import allbinary.graphics.form.ScrollSelectionForm;
import allbinary.graphics.form.ScrollSelectionFormNoneFactory;
import allbinary.graphics.form.item.CommandTextItemArrayFactory;
import allbinary.graphics.form.item.CustomItem;
import allbinary.graphics.form.item.validation.AllCommandsVisitor;
import allbinary.graphics.paint.NullPaintable;
import allbinary.graphics.paint.Paintable;
import allbinary.input.motion.gesture.observer.BasicMotionGesturesHandler;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class GameCommandCanvas
    extends MyCanvas
    implements MenuListener, DisplayChangeEventListener
{
    protected final BasicColor foregroundBasicColor;
    protected final BasicColor backgroundBasicColor;

    protected int foregroundColor;
    protected int backgroundColor;
    private BasicMenuInputProcessor menuInputProcessor =
        NoMenuInputProcessor.getInstance();
    private Paintable menuPaintable = NullPaintable.getInstance();
    
    private final InputToGameKeyMapping inputToGameKeyMapping = 
        PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance().getInputMapping();

    private PaintableForm menuForm;
    
    public GameCommandCanvas(CommandListener cmdListener, 
            BasicColor backgroundBasicColor, 
            BasicColor foregroundBasicColor)
        throws Exception
    {
        this.foregroundBasicColor = foregroundBasicColor;
        this.backgroundBasicColor = backgroundBasicColor;
        this.foregroundColor = foregroundBasicColor.intValue();
        this.backgroundColor = backgroundBasicColor.intValue();

        this.initCommands(cmdListener);

        this.initMenu();

        //AndroidUtil.isAndroid();
    }
    
    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
    }

    public void onDisplayChangeEvent(DisplayChangeEvent displayChangeEvent)
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, 
                    DisplayChangeEventHandler.getInstance().ON_DISPLAY_CHANGE_EVENT));
            
            FormType formType = FormTypeFactory.getInstance().getFormType();
            
            Rectangle rectangle = FormUtil.getInstance().createFormRectangle();

            this.menuForm.init(rectangle, formType);
            
            this.repaint();

            //This breaks the menu when coming back from the registration screen
            //this.initMenu();
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "onResize", e));
        }
    }
    
    /*
    public synchronized void pause()
    {
        this.setPaused(true);
    }

    public synchronized void unPause()
    {
        this.setPaused(false);
    }
    */

    public void initCommands(CommandListener cmdListener)
    {
        this.removeAllCommands();

        this.addCommand(GameCommandsFactory.getInstance().CLOSE_AND_SHOW_GAME_CANVAS);

        this.setCommandListener(cmdListener);
    }

    protected void initMenu() throws Exception
    {
        final CustomItem[] items = new CommandTextItemArrayFactory(
                new AllCommandsVisitor()).getInstance(
                this.getCommandStack(), 
                this.backgroundBasicColor, this.foregroundBasicColor
                );

        final DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();

        final Rectangle rectangle = new Rectangle(
            PointFactory.getInstance().getInstance(30, 30 + displayInfo.getLastHalfHeight()),
            displayInfo.getLastWidth() - 30,
            displayInfo.getLastHalfHeight() - 30);
        
        final ScrollSelectionForm form =
            CommandCurrentSelectionFormFactory.getInstance(
            //"Menu",
            StringUtil.getInstance().EMPTY_STRING,
            items,
            rectangle,
            FormTypeFactory.getInstance().VERTICAL_CENTER_FORM,
            25, false,
            this.backgroundBasicColor, this.foregroundBasicColor
            );

        this.setMenuForm(form);
        
        if(form != ScrollSelectionFormNoneFactory.getInstance())
        {
            this.setMenuInputProcessor(
                    new ImmediateCommandFormInputProcessor(
                    new BasicArrayList(), this, form));

            this.menuPaintable = new FormPaintable(form);            
        }
    }

    public void open()
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "open"));

        BasicMotionGesturesHandler.getInstance().addListener(this.getMenuInputProcessor());
        GameKeyEventHandler.getInstance().addListener(this.getMenuInputProcessor());
        DisplayChangeEventHandler.getInstance().addListener(this);
    }
    
    public void close() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "close"));

        BasicMotionGesturesHandler.getInstance().removeListener(this.getMenuInputProcessor());
        GameKeyEventHandler.getInstance().removeListener(this.getMenuInputProcessor());
        DisplayChangeEventHandler.getInstance().removeListener(this);
    }

    private static final int id = 0;

    public int getSourceId()
    {
        return id;
    }

    public void keyPressed(int keyCode)
    {
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "keyPressed"));
        this.addGameKeyEvent(keyCode, false);
    }

    public void keyReleased(int keyCode)
    {
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "keyReleased"));
        this.removeGameKeyEvent(keyCode, false);
    }

    private boolean isSingleKeyRepeatableProcessing =
        Features.getInstance().isFeature(
        InputFeatureFactory.getInstance().SINGLE_KEY_REPEAT_PRESS);

    public void keyRepeated(int keyCode)
    {
        // LogUtil.put(LogFactory.getInstance("Key Repeated: " + Integer.toHexString(keyCode), this, "keyRepeated"));
        if (this.isSingleKeyRepeatableProcessing)
        {
            this.addGameKeyEvent(keyCode, true);
        }
    }
    private final String NO_KEY = "Key Code Not Mapped For Game: ";
    private final String ADD_KEY_EVENT = "addGameKeyEvent";
    private final String REMOVE_KEY_EVENT = "removeGameKeyEvent";

    private final GameKeyFactory gameKeyFactory = GameKeyFactory.getInstance();

    private final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
    
    private void addGameKeyEvent(int keyCode, boolean repeated)
    {
        try
        {
            // LogUtil.put(LogFactory.getInstance("Key Code: " + Integer.toHexString(keyCode), this, "addGameKeyEvent"));

            GameKey gameKey = this.inputToGameKeyMapping.getInstance(this, keyCode);

            //LogUtil.put(LogFactory.getInstance("GameKey: " + gameKey, this, "addGameKeyEvent"));

            if (gameKey != this.gameKeyFactory.NONE)
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

                //getPlayerGameInput().onDownGameKeyEvent(gameKeyEvent);

                //this.gameKeyEventProcessor.onDownGameKeyEvent(gameKeyEvent);
            }
            else
            {
                LogUtil.put(LogFactory.getInstance(NO_KEY + keyCode, this, ADD_KEY_EVENT));
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

            //LogUtil.put(LogFactory.getInstance("GameKey: " + gameKey, this, "removeGameKeyEvent"));

            if (gameKey != this.gameKeyFactory.NONE)
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
                LogUtil.put(LogFactory.getInstance(NO_KEY + keyCode, this, REMOVE_KEY_EVENT));
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Key Event Error", this, REMOVE_KEY_EVENT, e));
        }
    }

    public void paint(Graphics graphics)
    {
        this.menuPaintable.paint(graphics);
    }

    private void setMenuInputProcessor(BasicMenuInputProcessor menuInputProcessor)
    {
        this.menuInputProcessor = menuInputProcessor;
    }

    protected BasicMenuInputProcessor getMenuInputProcessor()
    {
        return menuInputProcessor;
    }

    protected void setMenuForm(PaintableForm menuForm)
    {
        this.menuForm = menuForm;
    }

    protected PaintableForm getMenuForm()
    {
        return menuForm;
    }
}
