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

import org.allbinary.game.input.GameInputStrings;
import org.allbinary.game.input.GameKey;
import org.allbinary.game.input.GameKeyEventSourceInterface;
import org.allbinary.game.input.GameKeyFactory;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.game.input.Input;
import org.allbinary.game.input.InputFactory;
import org.allbinary.game.input.InputProcessor;
import org.allbinary.game.input.PlatformKeyFactory;
import org.allbinary.game.input.event.DownKeyEventHandler;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.game.input.event.UpGameKeyEventHandler;
import org.allbinary.string.CommonStrings;

public class FormInputProcessor
extends InputProcessor
{   
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    private final GameInputStrings gameInputStrings = GameInputStrings.getInstance();
    
    private final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
    
    //private final DownGameKeyEventHandler downGameKeyEventHandler = DownGameKeyEventHandler.getInstance();
    private final DownKeyEventHandler downKeyEventHandler = DownKeyEventHandler.getInstance();
    private final UpGameKeyEventHandler upGameKeyEventHandler = UpGameKeyEventHandler.getInstance();
     
    private final InputFactory inputFactory = InputFactory.getInstance();
    private final PlatformKeyFactory platformKeyFactory = PlatformKeyFactory.getInstance();
 
    private final AllBinaryGameCanvas allBinaryGameCanvas;
    
    public FormInputProcessor(AllBinaryGameCanvas allBinaryGameCanvas)
    {
        this.allBinaryGameCanvas = allBinaryGameCanvas;
    }
        
    public void keyPressed(final int keyCode, final int deviceId)
    {
        try
        {
            PreLogUtil.put(
                    new StringMaker()
                            .append(inputFactory.KEY_CODE_LABEL)
                            .append(keyCode)
                            .append(CommonSeps.getInstance().SPACE)
                            .append(inputFactory.DEVICE_ID_LABEL)
                            .append(deviceId)
                            .toString()
                    , this, this.gameInputStrings.KEY_PRESSED);

            final Input input = inputFactory.getInstance(keyCode);

            final GameKeyEvent gameKeyEvent = gameKeyEventFactory.getInstance(this.allBinaryGameCanvas, input);
            downKeyEventHandler.fireEvent(gameKeyEvent);
            //downKeyEventHandler.getInstance(deviceId).fireEvent(gameKeyEvent);

        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, this.gameInputStrings.ADD_KEY_EVENT, e));
        }
    }
    
    //Handle the enter case as a GameKey
    public void keyReleased(final Canvas canvas, final int keyCode, final int deviceId) {
        try
        {
//                PreLogUtil.put(
//                    new StringMaker()
//                        .append(inputFactory.KEY_CODE_LABEL)
//                        .append(keyCode)
//                        .append(CommonSeps.getInstance().SPACE)
//                        .append(inputFactory.DEVICE_ID_LABEL)
//                        .append(deviceId)
//                        .toString(),
//                     this, gameInputStrings.KEY_RELEASED);

            final Input input = this.inputFactory.getInstance(keyCode);
            if (platformKeyFactory.isEnter(input)) {
                
                PreLogUtil.put(
                    new StringMaker()
                        .append(inputFactory.KEY_CODE_LABEL)
                        .append(keyCode)
                        .append(CommonSeps.getInstance().SPACE)
                        .append(inputFactory.DEVICE_ID_LABEL)
                        .append(deviceId)
                        .toString(),
                     this, gameInputStrings.KEY_RELEASED);
          
                final GameKey gameKey = GameKeyFactory.getInstance().KEY_NUM0;
                final GameKeyEvent gameKeyEvent = gameKeyEventFactory.getInstance((GameKeyEventSourceInterface) canvas, gameKey);
                upGameKeyEventHandler.fireEvent(gameKeyEvent);
                upGameKeyEventHandler.getInstance(deviceId).fireEvent(gameKeyEvent);
            }

        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, this.gameInputStrings.ADD_KEY_EVENT, e));
        }
        
    }
    
}