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

import org.allbinary.canvas.Processor;
import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.InputFeatureFactory;
import org.allbinary.game.displayable.RepaintBehavior;
import org.allbinary.game.input.GameInputStrings;
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
import org.allbinary.graphics.displayable.CanvasStrings;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.graphics.displayable.event.DisplayChangeEvent;
import org.allbinary.graphics.displayable.event.DisplayChangeEventHandler;
import org.allbinary.graphics.displayable.event.DisplayChangeEventListener;
import org.allbinary.graphics.displayable.screen.ScreenRepaintProcessorFactory;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.graphics.form.CommandCurrentSelectionFormFactory;
import org.allbinary.graphics.form.FormPaintable;
import org.allbinary.graphics.form.FormTypeFactory;
import org.allbinary.graphics.form.PaintableForm;
import org.allbinary.graphics.form.ScrollSelectionForm;
import org.allbinary.graphics.form.ScrollSelectionFormNoneFactory;
import org.allbinary.graphics.form.item.CommandTextItemArrayFactory;
import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.graphics.form.item.validation.AllCommandsVisitor;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.input.motion.gesture.observer.BasicMotionGesturesHandler;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.string.CommonSeps;
import org.allbinary.util.BasicArrayList;

public class GameCommandCanvas
    extends MyCanvas
    implements MenuListener, DisplayChangeEventListener
{

    private final Processor repaintProcessor =
            ScreenRepaintProcessorFactory.getInstance().getInstance(this);
    
    protected final GameInputStrings gameInputStrings = GameInputStrings.getInstance();
    
    private static final int id = 0;
    
    protected final BasicColor foregroundBasicColor;
    protected final BasicColor backgroundBasicColor;

    private final InputToGameKeyMapping inputToGameKeyMapping = 
        PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance().getInputMapping();

    private final GameKeyFactory gameKeyFactory = GameKeyFactory.getInstance();

    private final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
    
    private final DownGameKeyEventHandler downGameKeyEventHandler = DownGameKeyEventHandler.getInstance();
    private final UpGameKeyEventHandler upGameKeyEventHandler = UpGameKeyEventHandler.getInstance();

    public final RepaintBehavior repaintBehavior;
    
    protected int foregroundColor;
    protected int backgroundColor;
    private BasicMenuInputProcessor menuInputProcessor =
        NoMenuInputProcessor.getInstance();
    private Paintable menuPaintable = NullPaintable.getInstance();
    
    private PaintableForm menuForm = PaintableForm.NULL_PAINTABLE_FORM;

    private boolean isSingleKeyRepeatableProcessing =
        Features.getInstance().isFeature(
        InputFeatureFactory.getInstance().SINGLE_KEY_REPEAT_PRESS);
    
    public GameCommandCanvas(final CommandListener cmdListener, final String name,
            final BasicColor backgroundBasicColor, 
            final BasicColor foregroundBasicColor)
        throws Exception
    {        
        super(name, CanvasStrings.getInstance().EMPTY_CHILD_NAME_LIST);

        //final GenericOperatingSystem operatingSystem = OperatingSystemFactory.getInstance().getOperatingSystemInstance();
//        if(operatingSystem.isScalable()) {
//            this.repaintBehavior = AlwaysRepaintBehavior.getInstance();
//        } else {
            this.repaintBehavior = RepaintBehavior.getInstance();
//        }

        this.foregroundBasicColor = foregroundBasicColor;
        this.backgroundBasicColor = backgroundBasicColor;
        this.foregroundColor = foregroundBasicColor.intValue();
        this.backgroundColor = backgroundBasicColor.intValue();

        this.initCommands(cmdListener);

        this.initMenu();
        
        repaintProcessor.process();
    }
 
    @Override
    public void onEvent(final AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }

    @Override
    public void onDisplayChangeEvent(final DisplayChangeEvent displayChangeEvent)
    {
        try
        {
            logUtil.put(commonStrings.START, this, canvasStrings.ON_DISPLAY_CHANGE_EVENT);
            
            final Rectangle rectangle = this.createRectangle(this.menuForm.size());

            this.menuForm.init(rectangle, FormTypeFactory.getInstance().VERTICAL_CENTER_FORM);

            //needed to update Android on orientation change
            //this.repaintBehavior.onChangeRepaint(this);
            this.update();

        }
        catch(Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, "onResize", e);
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

    public void initCommands(final CommandListener cmdListener)
    {
        this.removeAllCommands();

        this.addCommand(GameCommandsFactory.getInstance().CLOSE_AND_SHOW_GAME_CANVAS);

        this.setCommandListener(cmdListener);
    }

    protected void initMenu() throws Exception
    { 
        final ScrollSelectionForm form = this.createForm();

        this.menuForm = form;
        
        if(form != ScrollSelectionFormNoneFactory.getInstance())
        {
            this.setMenuInputProcessor(
                    new ImmediateCommandFormInputProcessor(
                    new BasicArrayList(), -1, this, form));

            this.menuPaintable = new FormPaintable(form);
        }
        
        this.repaintBehavior.onChangeRepaint(this);
    }

    public ScrollSelectionForm createForm() throws Exception {
        
        final CustomItem[] items = new CommandTextItemArrayFactory(
                new AllCommandsVisitor()).getInstance(
                this.getCommandStack(), 
                this.backgroundBasicColor, this.foregroundBasicColor
                );

        final Rectangle rectangle = this.createRectangle(items.length);

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

    public Rectangle createRectangle(final int size) {
        
        final DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
        
        final int height = size * MyFont.getInstance().DEFAULT_CHAR_HEIGHT;
        final int startY = (displayInfo.getLastHeight() * 2 / 3) - height;

        final Rectangle rectangle = new Rectangle(
            PointFactory.getInstance().getInstance(30, startY),
            displayInfo.getLastWidth() - 30,
            startY);

        //logUtil.put(displayInfo.toString(), this, "createRectangle");
        //logUtil.put(rectangle.toString(), this, "createRectangle");
        
        return rectangle;
    }

    @Override
    public void open()
    {
        logUtil.put(commonStrings.START, this, "open");

        BasicMotionGesturesHandler.getInstance().addListener(this.getMenuInputProcessor());
        GameKeyEventHandler.getInstance().addListener(this.getMenuInputProcessor());
        DisplayChangeEventHandler.getInstance().addListener(this);
    }
    
    @Override
    public void close() throws Exception
    {
        logUtil.put(commonStrings.START, this, commonStrings.CLOSE);

        BasicMotionGesturesHandler.getInstance().removeListener(this.getMenuInputProcessor());
        GameKeyEventHandler.getInstance().removeListener(this.getMenuInputProcessor());
        DisplayChangeEventHandler.getInstance().removeListener(this);
    }

    public void update() throws Exception {
        this.repaintProcessor.process();
    }
    
    @Override
    public int getSourceId()
    {
        return id;
    }

    @Override
    public void keyPressed(final int keyCode)
    {
        this.keyPressed(keyCode, 0);
    }
    
    @Override
    public void keyReleased(final int keyCode)
    {
        this.keyReleased(keyCode, 0);
    }

    @Override
    public void keyRepeated(final int keyCode)
    {
        this.keyRepeated(keyCode, 0);
    }
    
    public void keyPressed(final int keyCode, final int deviceId)
    {
        logUtil.put(new StringMaker().append(CommonSeps.getInstance().SPACE).append(keyCode).toString(), this, gameInputStrings.KEY_PRESSED);
        this.addGameKeyEvent(keyCode, 0, false);
    }

    public void keyReleased(final int keyCode, final int deviceId)
    {
        //logUtil.put(commonStrings.START, this, gameInputStrings.KEY_RELEASED);
        this.removeGameKeyEvent(keyCode, deviceId, false);
    }

    public void keyRepeated(final int keyCode, final int deviceId)
    {
        // logUtil.put("Key Repeated: ").append(Integer.toHexString(keyCode), this, gameInputStrings.KEY_REPEATED);
        if (this.isSingleKeyRepeatableProcessing)
        {
            this.addGameKeyEvent(keyCode, deviceId, true);
        }
    }
    
    private void addGameKeyEvent(final int keyCode, final int deviceId, final boolean repeated)
    {
        try
        {
            //logUtil.put(new StringMaker().append("Key Code (Hex): ").append(Integer.toHexString(keyCode)).toString(), this, this.gameInputStrings.ADD_KEY_EVENT);

            final GameKey gameKey = this.inputToGameKeyMapping.getInstance(this, keyCode);

            //logUtil.put(new StringMaker().append("GameKey: ").append(gameKey).toString(), this, this.gameInputStrings.ADD_KEY_EVENT);

            if (gameKey != this.gameKeyFactory.NONE)
            {
                final GameKeyEvent gameKeyEvent = gameKeyEventFactory.getInstance(this, gameKey);

                /*
                 * //This is for key input debugging only GameKeyEvent
                 * gameKeyEvent = GameKeyEventFactory.getInstance(this, keyCode, gameActionKeyCode, gameKey.getKey(), repeated);
                 * logUtil.put(gameKeyEvent.toString(), this, this.gameInputStrings.ADD_KEY_EVENT);
                 */

                downGameKeyEventHandler.fireEvent(gameKeyEvent);
                downGameKeyEventHandler.getInstance(deviceId).fireEvent(gameKeyEvent);

                //getPlayerGameInput().onDownGameKeyEvent(gameKeyEvent);

                //this.gameKeyEventProcessor.onDownGameKeyEvent(gameKeyEvent);
            }
            else
            {
                logUtil.put(new StringMaker().append(this.gameInputStrings.NO_KEY).append(keyCode).toString(), this, this.gameInputStrings.ADD_KEY_EVENT);
            }
        }
        catch (Exception e)
        {
            logUtil.put("Key Event Error", this, this.gameInputStrings.ADD_KEY_EVENT, e);
        }
    }

    private void removeGameKeyEvent(final int keyCode, final int deviceId, final boolean repeated)
    {
        try
        {
            //logUtil.put(new StringMaker().append("Key Code: " + Integer.toHexString(keyCode), this, this.gameInputStrings.REMOVE_KEY_EVENT);

            final GameKey gameKey = this.inputToGameKeyMapping.getInstance(this, keyCode);

            //logUtil.put(new StringMaker().append("GameKey: ").append(gameKey, this, this.gameInputStrings.REMOVE_KEY_EVENT);

            if (gameKey != this.gameKeyFactory.NONE)
            {
                final GameKeyEvent gameKeyEvent = gameKeyEventFactory.getInstance(this, gameKey);

                /*
                 * //This is for key input debugging only GameKeyEvent
                 * gameKeyEvent = GameKeyEventFactory.getInstance(this, keyCode, gameActionKeyCode, gameKey.getKey(), repeated);
                 * logUtil.put(gameKeyEvent.toString(), this, this.gameInputStrings.REMOVE_KEY_EVENT);
                 */

                // TODO TWB - Remove or improve key input event handling
                upGameKeyEventHandler.fireEvent(gameKeyEvent);
                upGameKeyEventHandler.getInstance(deviceId).fireEvent(gameKeyEvent);

                //getPlayerGameInput().onUpGameKeyEvent(gameKeyEvent);
            }
            else
            {
                logUtil.put(new StringMaker().append(this.gameInputStrings.NO_KEY).append(keyCode).toString(), this, this.gameInputStrings.REMOVE_KEY_EVENT);
            }
        }
        catch (Exception e)
        {
            logUtil.put("Key Event Error", this, this.gameInputStrings.REMOVE_KEY_EVENT, e);
        }
    }

    @Override
    public void paint(final Graphics graphics)
    {
        this.menuPaintable.paint(graphics);
        this.repaintBehavior.repaint(this);
    }

    private void setMenuInputProcessor(final BasicMenuInputProcessor menuInputProcessor)
    {
        this.menuInputProcessor = menuInputProcessor;
    }

    protected BasicMenuInputProcessor getMenuInputProcessor()
    {
        return menuInputProcessor;
    }

}
