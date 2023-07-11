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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.input.GameKey;
import org.allbinary.game.input.GameKeyFactory;
import org.allbinary.game.input.InputProcessor;
import org.allbinary.game.input.PlatformInputMappingFactory;
import org.allbinary.game.input.event.DownGameKeyEventHandler;
import org.allbinary.game.input.event.DownKeyEventHandler;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.game.input.mapping.InputToGameKeyMapping;
import org.allbinary.logic.basic.string.StringMaker;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;

public class GameInputProcessor
extends InputProcessor
{
    private final AllBinaryGameCanvas allBinaryGameCanvas;
    private final DownGameKeyEventHandler downGameKeyEventHandler =
        DownGameKeyEventHandler.getInstance();
    private final DownKeyEventHandler downKeyEventHandler =
        DownKeyEventHandler.getInstance();
    private final SmallIntegerSingletonFactory smallIntegerSingletonFactory = 
                    SmallIntegerSingletonFactory.getInstance();
    
    private final InputToGameKeyMapping inputToGameKeyMapping =
        PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance().getInputMapping();
    
    public GameInputProcessor(final AllBinaryGameCanvas allBinaryGameCanvas)
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
            //LogUtil.put(LogFactory.getInstance("Key Code: ").append(Integer.toHexString(keyCode), this, this.allBinaryGameCanvas.ADD_KEY_EVENT));
            //PreLogUtil.put("Key Code: ").append(Integer.toHexString(keyCode), this, this.allBinaryGameCanvas.ADD_KEY_EVENT);
            
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
                 * .put(LogFactory.getInstance(gameKeyEvent.toString(),
                 * this, "GameKeyEvent"));
                 */

                downGameKeyEventHandler.fireEvent(gameKeyEvent);
                downGameKeyEventHandler.getInstance(deviceId).fireEvent(gameKeyEvent);
                // DownGameKeyEventHandler.getInstance().fireEvent(gameKey);

            }
            else
            {
                LogUtil.put(LogFactory.getInstance(new StringMaker().append(this.allBinaryGameCanvas.NO_KEY).append(keyCode).toString(), this, this.allBinaryGameCanvas.ADD_KEY_EVENT));
            }

            final Integer keyCodeAsInteger = smallIntegerSingletonFactory.getInstance(keyCode);
            downKeyEventHandler.fireEvent(keyCodeAsInteger);
            downKeyEventHandler.getInstance(deviceId).fireEvent(keyCodeAsInteger);

        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Key Event Error", this, this.allBinaryGameCanvas.ADD_KEY_EVENT, e));
        }
    }
}
