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

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.game.input.Input;
import org.allbinary.game.input.InputFactory;
import org.allbinary.game.input.InputProcessor;
import org.allbinary.game.input.event.DownGameKeyEventHandler;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.GameKeyEventFactory;

public class FormInputProcessor
extends InputProcessor
{
    private final AllBinaryGameCanvas allBinaryGameCanvas;
    
    private final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
    
    private final DownGameKeyEventHandler downGameKeyEventHandler = DownGameKeyEventHandler.getInstance();
    
    public FormInputProcessor(AllBinaryGameCanvas allBinaryGameCanvas)
    {
        this.allBinaryGameCanvas = allBinaryGameCanvas;
    }
    
    private final InputFactory inputFactory = InputFactory.getInstance();
    
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
                    , this, "keyPressed");

            final Input input = inputFactory.getInstance(keyCode);

            final GameKeyEvent gameKeyEvent = gameKeyEventFactory.getInstance(this.allBinaryGameCanvas, input);
            
            downGameKeyEventHandler.fireEvent(gameKeyEvent);
            if(deviceId >= 0) {
                downGameKeyEventHandler.getInstance(deviceId).fireEvent(gameKeyEvent);
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Key Event Error", this, this.allBinaryGameCanvas.ADD_KEY_EVENT, e));
        }
    }
}