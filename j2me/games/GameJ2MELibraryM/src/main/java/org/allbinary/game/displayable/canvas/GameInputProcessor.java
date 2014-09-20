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
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.game.input.mapping.InputToGameKeyMapping;

public class GameInputProcessor
extends InputProcessor
{
    private final AllBinaryGameCanvas allBinaryGameCanvas;
    private final DownGameKeyEventHandler downGameKeyEventHandler =
        DownGameKeyEventHandler.getInstance();
    
    private final InputToGameKeyMapping inputToGameKeyMapping =
        PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance().getInputMapping();
    
    public GameInputProcessor(AllBinaryGameCanvas allBinaryGameCanvas)
    {
        this.allBinaryGameCanvas = allBinaryGameCanvas;

        this.inputToGameKeyMapping.init(this.allBinaryGameCanvas);
    }
    
    private final GameKey NONE = GameKeyFactory.getInstance().NONE;

    private final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
    
    //TWB - This is raw input from Canvas that does not include TouchButton Input
    public void keyPressed(int keyCode, int deviceId)
    {
        try
        {
            //LogUtil.put(LogFactory.getInstance("Key Code: " + Integer.toHexString(keyCode), this, this.allBinaryGameCanvas.ADD_KEY_EVENT));
            //PreLogUtil.put("Key Code: " + Integer.toHexString(keyCode), this, this.allBinaryGameCanvas.ADD_KEY_EVENT);
            
            GameKey gameKey = this.inputToGameKeyMapping.getInstance(
                    this.allBinaryGameCanvas, keyCode);

            if (gameKey != NONE)
            {
                GameKeyEvent gameKeyEvent = gameKeyEventFactory.getInstance(
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
                LogUtil.put(LogFactory.getInstance(this.allBinaryGameCanvas.NO_KEY + keyCode, this, 
                        this.allBinaryGameCanvas.ADD_KEY_EVENT));
            }

        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Key Event Error", this, 
                    this.allBinaryGameCanvas.ADD_KEY_EVENT, e));
        }
    }
}
