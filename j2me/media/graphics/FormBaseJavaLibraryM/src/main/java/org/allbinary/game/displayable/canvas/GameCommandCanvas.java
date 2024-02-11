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

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.InputFeatureFactory;
import org.allbinary.game.input.GameKey;
import org.allbinary.game.input.GameKeyFactory;
import org.allbinary.game.input.PlatformInputMappingFactory;
import org.allbinary.game.input.event.DownGameKeyEventHandler;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.game.input.event.GameKeyEventHandler;
import org.allbinary.game.input.event.UpGameKeyEventHandler;
import org.allbinary.game.input.mapping.InputToGameKeyMapping;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.graphics.displayable.event.DisplayChangeEvent;
import org.allbinary.graphics.displayable.event.DisplayChangeEventHandler;
import org.allbinary.graphics.displayable.event.DisplayChangeEventListener;
import org.allbinary.graphics.form.CommandCurrentSelectionFormFactory;
import org.allbinary.graphics.form.FormPaintable;
import org.allbinary.graphics.form.FormType;
import org.allbinary.graphics.form.FormTypeFactory;
import org.allbinary.graphics.form.PaintableForm;
import org.allbinary.graphics.form.ScrollSelectionForm;
import org.allbinary.graphics.form.ScrollSelectionFormNoneFactory;
import org.allbinary.graphics.form.item.CommandTextItemArrayFactory;
import org.allbinary.graphics.form.item.validation.AllCommandsVisitor;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.input.motion.gesture.observer.BasicMotionGesturesHandler;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.handler.BasicEventHandler;

public class GameCommandCanvas
    extends MyCanvas
    implements MenuListener, DisplayChangeEventListener
{
    private static final int id = 0;
    
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

    private boolean isSingleKeyRepeatableProcessing =
        Features.getInstance().isFeature(
        InputFeatureFactory.getInstance().SINGLE_KEY_REPEAT_PRESS);

    private final String NO_KEY = "Key Code Not Mapped For Game: ";
    private final String ADD_KEY_EVENT = "addGameKeyEvent";
    private final String REMOVE_KEY_EVENT = "removeGameKeyEvent";

    private final GameKeyFactory gameKeyFactory = GameKeyFactory.getInstance();

    private final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
    
    private final DownGameKeyEventHandler downGameKeyEventHandler = DownGameKeyEventHandler.getInstance();
    private final UpGameKeyEventHandler upGameKeyEventHandler = UpGameKeyEventHandler.getInstance();
    
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
            LogUtil.put(LogFactory.getInstance(commonStrings.START, this, 
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
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "onResize", e));
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
        final ScrollSelectionForm form = this.createForm();

        this.setMenuForm(form);
        
        if(form != ScrollSelectionFormNoneFactory.getInstance())
        {
            this.setMenuInputProcessor(
                    new ImmediateCommandFormInputProcessor(
                    new BasicArrayList(), -1, this, form));

            this.menuPaintable = new FormPaintable(form);            
        }
    }

    public ScrollSelectionForm createForm() throws Exception {
        
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
        
            return CommandCurrentSelectionFormFactory.getInstance(
            //"Menu",
            StringUtil.getInstance().EMPTY_STRING,
            items,
            rectangle,
            FormTypeFactory.getInstance().VERTICAL_CENTER_FORM,
            15, false,
            this.backgroundBasicColor, this.foregroundBasicColor
            );
    }
    
    public void open()
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "open"));

        BasicMotionGesturesHandler.getInstance().addListener(this.getMenuInputProcessor());
        GameKeyEventHandler.getInstance().addListener(this.getMenuInputProcessor());
        DisplayChangeEventHandler.getInstance().addListener(this);
    }
    
    public void close() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "close"));

        BasicMotionGesturesHandler.getInstance().removeListener(this.getMenuInputProcessor());
        GameKeyEventHandler.getInstance().removeListener(this.getMenuInputProcessor());
        DisplayChangeEventHandler.getInstance().removeListener(this);
    }

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
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "keyPressed"));
        this.addGameKeyEvent(keyCode, 0, false);
    }

    public void keyReleased(int keyCode, int deviceId)
    {
        //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "keyReleased"));
        this.removeGameKeyEvent(keyCode, deviceId, false);
    }
    
    public void keyRepeated(int keyCode, int deviceId)
    {
        // LogUtil.put(LogFactory.getInstance("Key Repeated: ").append(Integer.toHexString(keyCode), this, "keyRepeated"));
        if (this.isSingleKeyRepeatableProcessing)
        {
            this.addGameKeyEvent(keyCode, deviceId, true);
        }
    }
    
    private void addGameKeyEvent(int keyCode, int deviceId, boolean repeated)
    {
        try
        {
            // LogUtil.put(LogFactory.getInstance("Key Code: ").append(Integer.toHexString(keyCode), this, "addGameKeyEvent"));

            GameKey gameKey = this.inputToGameKeyMapping.getInstance(this, keyCode);

            //LogUtil.put(LogFactory.getInstance("GameKey: ").append(gameKey, this, "addGameKeyEvent"));

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

                downGameKeyEventHandler.fireEvent(gameKeyEvent);
                downGameKeyEventHandler.getInstance(deviceId).fireEvent(gameKeyEvent);

                //getPlayerGameInput().onDownGameKeyEvent(gameKeyEvent);

                //this.gameKeyEventProcessor.onDownGameKeyEvent(gameKeyEvent);
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

    private void removeGameKeyEvent(int keyCode, int deviceId, boolean repeated)
    {
        try
        {
            // LogUtil.put(LogFactory.getInstance("Key Code: " +
            // Integer.toHexString(keyCode),
            // this, "removeGameKeyEvent"));

            GameKey gameKey = this.inputToGameKeyMapping.getInstance(this, keyCode);

            //LogUtil.put(LogFactory.getInstance("GameKey: ").append(gameKey, this, "removeGameKeyEvent"));

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
                upGameKeyEventHandler.fireEvent(gameKeyEvent);
                upGameKeyEventHandler.getInstance(deviceId).fireEvent(gameKeyEvent);

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
