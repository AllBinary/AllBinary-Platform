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

import javax.microedition.lcdui.Canvas;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.input.GameKey;
import org.allbinary.game.input.GameKeyEventSourceInterface;
import org.allbinary.game.input.GameKeyFactory;
import org.allbinary.game.input.InputProcessor;
import org.allbinary.game.input.PlatformInputMappingFactory;
import org.allbinary.game.input.event.DownGameKeyEventHandler;
import org.allbinary.game.input.event.DownKeyEventHandler;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.game.input.event.UpGameKeyEventHandler;
import org.allbinary.game.input.mapping.InputToGameKeyMapping;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;

public class GameCanvasInputProcessor
extends InputProcessor
{
    private final AllBinaryGameCanvas allBinaryGameCanvas;
    private final DownGameKeyEventHandler downGameKeyEventHandler =
        DownGameKeyEventHandler.getInstance();
    private final UpGameKeyEventHandler upGameKeyEventHandler = UpGameKeyEventHandler.getInstance();
    private final DownKeyEventHandler downKeyEventHandler =
        DownKeyEventHandler.getInstance();
    private final SmallIntegerSingletonFactory smallIntegerSingletonFactory = 
                    SmallIntegerSingletonFactory.getInstance();
    
    private final InputToGameKeyMapping inputToGameKeyMapping =
        PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance().getInputMapping();
    
    public GameCanvasInputProcessor(final AllBinaryGameCanvas allBinaryGameCanvas)
    {
        this.allBinaryGameCanvas = allBinaryGameCanvas;

        this.inputToGameKeyMapping.init(this.allBinaryGameCanvas);
    }
    
    private final GameKey NONE = GameKeyFactory.getInstance().NONE;

    private final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
        
    //TWB - This is raw input from Canvas that does not include TouchButton Input
    public void keyPressed(final int keyCode, final int deviceId)
    {
        try
        {
            //LogUtil.put(LogFactory.getInstance(new StringMaker().append("Key Code: ").append(Integer.toHexString(keyCode)).toString(), this, this.gameInputStrings.ADD_KEY_EVENT));
            //PreLogUtil.put(new StringMaker().append(new StringMaker().append("Key Code: ").append(Integer.toHexString(keyCode)).toString(), this, this.gameInputStrings.ADD_KEY_EVENT);
            
            final GameKey gameKey = this.inputToGameKeyMapping.getInstance(
                    this.allBinaryGameCanvas, keyCode);

            if (gameKey != NONE)
            {
                final GameKeyEvent gameKeyEvent = gameKeyEventFactory.getInstance(
                            this.allBinaryGameCanvas, gameKey);

                /*
                 * //This is for key input debugging only GameKeyEvent
                 * gameKeyEvent = GameKeyEventFactory.getInstance(this,
                 * keyCode, gameActionKeyCode, gameKey.getKey(), repeated);
                 * LogUtil
                 * .put(LogFactory.getInstance(gameKeyEvent.toString(), this, this.gameInputStrings.ADD_KEY_EVENT));
                 */

                downGameKeyEventHandler.fireEvent(gameKeyEvent);
                downGameKeyEventHandler.getInstance(deviceId).fireEvent(gameKeyEvent);
                // DownGameKeyEventHandler.getInstance().fireEvent(gameKey);

            }
            else
            {
                LogUtil.put(LogFactory.getInstance(new StringMaker().append(this.gameInputStrings.NO_KEY).append(keyCode).toString(), this, this.gameInputStrings.ADD_KEY_EVENT));
            }

            final Integer keyCodeAsInteger = smallIntegerSingletonFactory.getInstanceNoThrow(keyCode);
            downKeyEventHandler.fireEvent(keyCodeAsInteger);
            downKeyEventHandler.getInstance(deviceId).fireEvent(keyCodeAsInteger);

        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Key Event Error", this, this.gameInputStrings.ADD_KEY_EVENT, e));
        }
    }
    
    public void keyReleased(final Canvas canvas, final int keyCode, final int deviceId) {
        this.removeGameKeyEvent(canvas, keyCode, deviceId, false);
    }
    
    private void removeGameKeyEvent(final Canvas canvas, final int keyCode, final int deviceId, final boolean repeated)
    {
        try
        {
            //LogUtil.put(LogFactory.getInstance(new StringMaker().append("Key Code: " + Integer.toHexString(keyCode), this, REMOVE_KEY_EVENT));

            final GameKey gameKey = this.inputToGameKeyMapping.getInstance(canvas, keyCode);

            if (gameKey != NONE)
            {
                final GameKeyEvent gameKeyEvent = gameKeyEventFactory.getInstance((GameKeyEventSourceInterface) canvas, gameKey);

                /*
                 * //This is for key input debugging only GameKeyEvent
                 * gameKeyEvent = GameKeyEventFactory.getInstance(this, keyCode, gameActionKeyCode, gameKey.getKey(), repeated);
                 */
                //LogUtil.put(LogFactory.getInstance(gameKeyEvent.toString(), this, REMOVE_KEY_EVENT));

                // TODO TWB - Remove or improve key input event handling
                upGameKeyEventHandler.fireEvent(gameKeyEvent);
                upGameKeyEventHandler.getInstance(deviceId).fireEvent(gameKeyEvent);
                // UpGameKeyEventHandler.getInstance().fireEvent(gameKey);
                // getPlayerGameInput().onUpGameKeyEvent(gameKeyEvent);
            }
            else
            {
                LogUtil.put(LogFactory.getInstance(new StringMaker().append(this.gameInputStrings.NO_KEY).append(keyCode).toString(), this, this.gameInputStrings.REMOVE_KEY_EVENT));
            }

            //This is for key released events if needed
            //this.handleRawKey(keyCode, deviceId, repeated);
            
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Key Event Error", this, this.gameInputStrings.REMOVE_KEY_EVENT, e));
        }
    }
    
}
