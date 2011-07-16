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

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.input.GameKey;
import allbinary.game.input.GameKeyFactory;
import allbinary.game.input.InputProcessor;
import allbinary.game.input.PlatformInputMappingFactory;
import allbinary.game.input.event.DownGameKeyEventHandler;
import allbinary.game.input.event.GameKeyEvent;
import allbinary.game.input.event.GameKeyEventFactory;
import allbinary.game.input.mapping.InputToGameKeyMapping;

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
    
    public void keyPressed(int keyCode)
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
