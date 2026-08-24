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

import jsinterop.annotations.JsType;

import java.util.Vector;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.NullCommandListener;

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
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.graphics.form.CommandCurrentSelectionFormFactory;
import org.allbinary.graphics.form.FormPaintable;
import org.allbinary.graphics.form.FormTypeFactory;
import org.allbinary.graphics.form.PaintableForm;
import org.allbinary.graphics.form.ScrollSelectionForm;
import org.allbinary.graphics.form.ScrollSelectionFormNoneFactory;
import org.allbinary.graphics.form.item.CommandTextItemArrayFactory;
import org.allbinary.graphics.form.item.ABCustomItem;
import org.allbinary.graphics.form.item.validation.AllCommandsVisitor;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.input.motion.gesture.observer.BasicMotionGesturesHandler;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.string.CommonSeps;
import org.allbinary.util.BasicArrayListD;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class GameCommandCanvas
    extends MyCanvas
    implements MenuListener, DisplayChangeEventListener, UpdateMyFontInterface
{

    private final Processor repaintProcessor =
            ScreenRepaintProcessorFactory.getInstance().create(this);
    
    @JsProperty
    protected final GameInputStrings gameInputStrings = GameInputStrings.getInstance();
    
    private static final int id = 0;
    
    @JsProperty
    protected final BasicColor foregroundBasicColor;
    @JsProperty
    protected final BasicColor backgroundBasicColor;

    @JsProperty
    protected final InputToGameKeyMapping inputToGameKeyMapping =
        PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance().getInputMapping();

    @JsProperty
    protected final GameKeyFactory gameKeyFactory = GameKeyFactory.getInstance();

    private final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
    
    private final DownGameKeyEventHandler downGameKeyEventHandler = DownGameKeyEventHandler.getInstance();
    private final UpGameKeyEventHandler upGameKeyEventHandler = UpGameKeyEventHandler.getInstance();

    @JsProperty
    public final RepaintBehavior repaintBehavior;
    
    @JsProperty
    protected final MyFontProcessor updateMyFontProcessor = new UpdateMyFontProcessor(this);
    @JsProperty
    protected MyFontProcessor myFontProcessor = this.updateMyFontProcessor;
    
    @JsProperty
    protected int foregroundColor;
    @JsProperty
    protected int backgroundColor;
    private BasicMenuInputProcessor menuInputProcessor =
        NoMenuInputProcessor.getInstance();
    private Paintable menuPaintable = NullPaintable.getInstance();
    
    private PaintableForm menuForm = PaintableForm.getNullPaintableForm();

    private boolean isSingleKeyRepeatableProcessing =
        Features.getInstance().isFeature(
        InputFeatureFactory.getInstance().SINGLE_KEY_REPEAT_PRESS);
    
    @JsProperty
    protected int fontHeight = 0;
    
    @JsConstructor
    public GameCommandCanvas(final CommandListener cmdListener, final String name,
            final BasicColor backgroundBasicColor, 
            final BasicColor foregroundBasicColor)
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
        this.foregroundColor = this.foregroundBasicColor.intValue();
        this.backgroundColor = this.backgroundBasicColor.intValue();

        this.initCommands(cmdListener);

        if(cmdListener != NullCommandListener.NULL_COMMAND_LISTENER) {
            try {
                this.initMenu();

                this.repaintProcessor.process();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }
 
    @Override
    @JsMethod
    public void updateMeasurement(final Graphics graphics) {

        try
        {
            final Font font = graphics.getFont();
            
            this.logUtil.putF(new StringMaker().append(this.commonStrings.START).append(this.canvasStrings.FD_WIDTH).appendint(MyFontProcessor.defaultCharWidth(font)).append(this.canvasStrings.FD_HEIGHT).appendint(font.getHeight()).toString(), this, this.canvasStrings.UPDATE_MEASUREMENT);
            
            this.fontHeight = font.getHeight();
            final Rectangle rectangle = this.createRectangle(this.menuForm.size());
            this.menuForm.init(rectangle, FormTypeFactory.getInstance().VERTICAL_CENTER_FORM);
            //needed to update Android on orientation change
            //this.repaintBehavior.onChangeRepaint(this);
            this.update();

        }
        catch(Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.canvasStrings.ON_DISPLAY_CHANGE_EVENT, e);
        }
        
        this.myFontProcessor = MyFontProcessor.getInstance();
    }
    
    @Override
    @JsMethod
    public void onEvent(final AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }

    @Override
    @JsMethod
    public void onDisplayChangeEvent(final DisplayChangeEvent displayChangeEvent)
    {
        //this.logUtil.putF(this.commonStrings.START, this, this.canvasStrings.ON_DISPLAY_CHANGE_EVENT);
        this.logUtil.putF(new StringMaker().append(this.commonStrings.START).append(DisplayInfoSingleton.getInstance().toString()).toString(), this, this.canvasStrings.ON_DISPLAY_CHANGE_EVENT);
        
        this.myFontProcessor = this.updateMyFontProcessor;
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

    @JsMethod
    public void initCommands(final CommandListener cmdListener)
    {
        this.removeAllCommands();

        this.addCommand(GameCommandsFactory.getInstance().CLOSE_AND_SHOW_GAME_CANVAS);

        this.setCommandListener(cmdListener);
    }

    @JsMethod
    protected void initMenu() throws Exception
    { 
        final ScrollSelectionForm form = this.createForm();

        this.menuForm = form;
        
        if(form != ScrollSelectionFormNoneFactory.getInstance())
        {
            this.setMenuInputProcessor(
                    new ImmediateCommandFormInputProcessor(
                    new BasicArrayListD(), -1, this, form));

            this.menuPaintable = new FormPaintable(form);
        }
        
        this.repaintBehavior.onChangeRepaint(this);
    }

    @JsMethod
    public ScrollSelectionForm createForm() throws Exception {
        
        final ABCustomItem[] items = new CommandTextItemArrayFactory(
                new AllCommandsVisitor()).getInstance(
                (Vector<Object>) this.getCommandStack(), 
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

    @JsMethod
    public Rectangle createRectangle(final int size) {
        
        final int height = size * this.fontHeight;
        final int startY = (this.displayInfo.getLastHeight() * 2 / 3) - height;

        final PointFactory pointFactory = PointFactory.getInstance();
        
        final Rectangle rectangle = new Rectangle(
            pointFactory.createXY(30, startY),
            this.displayInfo.getLastWidth() - 30,
            startY);

        //this.logUtil.putF(displayInfo.toString(), this, "createRectangle");
        //this.logUtil.putF(rectangle.toString(), this, "createRectangle");
        
        return rectangle;
    }

    @Override
    @JsMethod
    public void open()
    {
        this.logUtil.putF(this.commonStrings.START, this, "open");

        BasicMotionGesturesHandler.getInstance().addListenerInterface(this.getMenuInputProcessor());
        GameKeyEventHandler.getInstance().addListener(this.getMenuInputProcessor());
        DisplayChangeEventHandler.getInstance().addListenerInterface(this);
    }
    
    @Override
    @JsMethod
    public void close() throws Exception
    {
        this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.CLOSE);

        BasicMotionGesturesHandler.getInstance().removeListener(this.getMenuInputProcessor());
        GameKeyEventHandler.getInstance().removeListener(this.getMenuInputProcessor());
        DisplayChangeEventHandler.getInstance().removeListener(this);
    }

    @JsMethod
    public void update() throws Exception {
        this.repaintProcessor.process();
    }
    
    @Override
    @JsMethod
    public int getSourceId()
    {
        return GameCommandCanvas.id;
    }

    @Override
    @JsMethod
    public void keyPressed(final int keyCode)
    {
        this.keyPressedByDevice(keyCode, 0);
    }
    
    @Override
    @JsMethod
    public void keyReleased(final int keyCode)
    {
        this.keyReleasedByDevice(keyCode, 0);
    }

    @Override
    @JsMethod
    public void keyRepeated(final int keyCode)
    {
        this.keyRepeatedByDevice(keyCode, 0);
    }
    
    @Override
    @JsMethod
    public void keyPressedByDevice(final int keyCode, final int deviceId)
    {
        this.logUtil.putF(new StringMaker().append(CommonSeps.getInstance().SPACE).appendint(keyCode).toString(), this, this.gameInputStrings.KEY_PRESSED);
        this.addGameKeyEvent(keyCode, 0, false);
    }

    @Override
    @JsMethod
    public void keyReleasedByDevice(final int keyCode, final int deviceId)
    {
        //this.logUtil.putF(this.commonStrings.START, this, gameInputStrings.KEY_RELEASED);
        this.removeGameKeyEvent(keyCode, deviceId, false);
    }

    @Override
    @JsMethod
    public void keyRepeatedByDevice(final int keyCode, final int deviceId)
    {
        // this.logUtil.putF("Key Repeated: ").append(Integer.toHexString(keyCode), this, gameInputStrings.KEY_REPEATED);
        if (this.isSingleKeyRepeatableProcessing)
        {
            this.addGameKeyEvent(keyCode, deviceId, true);
        }
    }
    
    @JsMethod
    private void addGameKeyEvent(final int keyCode, final int deviceId, final boolean repeated)
    {
        try
        {
            //this.logUtil.putF(new StringMaker().append("Key Code (Hex): ").append(Integer.toHexString(keyCode)).toString(), this, this.gameInputStrings.ADD_KEY_EVENT);

            final GameKey gameKey = this.inputToGameKeyMapping.getInstanceForCanvas(this, keyCode);

            //this.logUtil.putF(new StringMaker().append("GameKey: ").append(gameKey).toString(), this, this.gameInputStrings.ADD_KEY_EVENT);

            if (gameKey != this.gameKeyFactory.NONE)
            {
                final GameKeyEvent gameKeyEvent = this.gameKeyEventFactory.getInstanceForInput(this, gameKey);

                /*
                 * //This is for key input debugging only GameKeyEvent
                 * gameKeyEvent = GameKeyEventFactory.getInstance(this, keyCode, gameActionKeyCode, gameKey.getKey(), repeated);
                 * this.logUtil.putF(gameKeyEvent.toString(), this, this.gameInputStrings.ADD_KEY_EVENT);
                 */

                this.downGameKeyEventHandler.fireEvent(gameKeyEvent);
                this.downGameKeyEventHandler.getInstanceForDevice(deviceId).fireEvent(gameKeyEvent);

                //getPlayerGameInput().onDownGameKeyEvent(gameKeyEvent);

                //this.gameKeyEventProcessor.onDownGameKeyEvent(gameKeyEvent);
            }
            else
            {
                this.logUtil.putF(new StringMaker().append(this.gameInputStrings.NO_KEY).appendint(keyCode).toString(), this, this.gameInputStrings.ADD_KEY_EVENT);
            }
        }
        catch (Exception e)
        {
            this.logUtil.put("Key Event Error", this, this.gameInputStrings.ADD_KEY_EVENT, e);
        }
    }

    @JsMethod
    private void removeGameKeyEvent(final int keyCode, final int deviceId, final boolean repeated)
    {
        try
        {
            //this.logUtil.putF(new StringMaker().append("Key Code: " + Integer.toHexString(keyCode), this, this.gameInputStrings.REMOVE_KEY_EVENT);

            final GameKey gameKey = this.inputToGameKeyMapping.getInstanceForCanvas(this, keyCode);

            //this.logUtil.putF(new StringMaker().append("GameKey: ").append(gameKey, this, this.gameInputStrings.REMOVE_KEY_EVENT);

            if (gameKey != this.gameKeyFactory.NONE)
            {
                final GameKeyEvent gameKeyEvent = this.gameKeyEventFactory.getInstanceForInput(this, gameKey);

                /*
                 * //This is for key input debugging only GameKeyEvent
                 * gameKeyEvent = GameKeyEventFactory.getInstance(this, keyCode, gameActionKeyCode, gameKey.getKey(), repeated);
                 * this.logUtil.putF(gameKeyEvent.toString(), this, this.gameInputStrings.REMOVE_KEY_EVENT);
                 */

                // TODO TWB - Remove or improve key input event handling
                this.upGameKeyEventHandler.fireEvent(gameKeyEvent);
                this.upGameKeyEventHandler.getInstanceForDevice(deviceId).fireEvent(gameKeyEvent);

                //getPlayerGameInput().onUpGameKeyEvent(gameKeyEvent);
            }
            else
            {
                this.logUtil.putF(new StringMaker().append(this.gameInputStrings.NO_KEY).appendint(keyCode).toString(), this, this.gameInputStrings.REMOVE_KEY_EVENT);
            }
        }
        catch (Exception e)
        {
            this.logUtil.put("Key Event Error", this, this.gameInputStrings.REMOVE_KEY_EVENT, e);
        }
    }

    @Override
    @JsMethod
    public void paint(final Graphics graphics)
    {
        this.myFontProcessor.process(graphics);

        this.menuPaintable.paint(graphics);
        this.repaintBehavior.repaint(this);
    }

    @JsMethod
    private void setMenuInputProcessor(final BasicMenuInputProcessor menuInputProcessor)
    {
        this.menuInputProcessor = menuInputProcessor;
    }

    @JsMethod
    protected BasicMenuInputProcessor getMenuInputProcessor()
    {
        return this.menuInputProcessor;
    }

}
