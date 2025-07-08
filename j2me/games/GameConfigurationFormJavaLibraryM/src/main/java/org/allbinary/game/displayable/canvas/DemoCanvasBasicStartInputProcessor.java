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

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.canvas.RunnableCanvas;
import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.input.GameInputStrings;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;

public class DemoCanvasBasicStartInputProcessor
    extends BasicMenuInputProcessor
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    public DemoCanvasBasicStartInputProcessor(
        BasicArrayList gameKeyEventList, RunnableCanvas gameCanvas)
    {
        super(gameKeyEventList, -1, gameCanvas);
    }

    /*
    public int processInput(int key) throws Exception
    {
        return -1;
    }
     */

    public int processInput()
        throws Exception
    {
        BasicArrayList list = this.getGameKeyEventList();
        
        int size = list.size();
        
        GameKeyEvent gameKeyEvent;
        
        for (int index = 0; index < size; index++)
        {
            gameKeyEvent = (GameKeyEvent) list.objectArray[index];

            logUtil.put(new StringMaker().append("Start GameKey: ").append(StringUtil.getInstance().toString(gameKeyEvent)).toString(), this, GameInputStrings.getInstance().PROCESS_INPUT);
        }
        this.getCanvas().getCustomCommandListener().commandAction(
                GameCommandsFactory.getInstance().START_COMMAND, null);
        return size;
    }
}
