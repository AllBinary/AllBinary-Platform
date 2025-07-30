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

import javax.microedition.lcdui.NullCanvas;
import org.allbinary.canvas.RunnableCanvas;
import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.input.GameInputStrings;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.util.BasicArrayList;

public class DemoCanvasBasicStartInputProcessor
    extends BasicMenuInputProcessor
{

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

    @Override
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
                GameCommandsFactory.getInstance().START_COMMAND, NullCanvas.NULL_CANVAS);
        return size;
    }
}
