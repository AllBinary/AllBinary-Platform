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
import javax.microedition.lcdui.NullCanvas;
import org.allbinary.game.input.PlayerGameInput;
import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.input.motion.gesture.observer.BaseMotionGestureEventListener;
import org.allbinary.input.motion.gesture.observer.MotionGestureEvent;
import org.allbinary.util.BasicArrayList;

public class BasicMenuInputProcessor extends PlayerGameInput
    implements BaseMotionGestureEventListener
{

    public final BasicArrayList motionGestureEventList = new BasicArrayList();
    private Canvas canvas = NullCanvas.NULL_CANVAS;
    
    protected BasicMenuInputProcessor(BasicArrayList gameKeyEventList, int playerInputId, Canvas gameCanvas)
    {
        super(gameKeyEventList, playerInputId);
        
        this.canvas = gameCanvas;
    }
    
    @Override
    public void onMotionGestureEvent(MotionGestureEvent motionGestureEvent)
    {
        //PreLogUtil.put(commonStrings.START, this, 
                //new StringBuilder().append("onMotionGestureEvent - id: ").append(motionGestureEvent.getId())
                        //.append(" point: ").append(motionGestureEvent.getCurrentPoint()).toString());
       
        motionGestureEventList.add(motionGestureEvent);
    }

    private void setCanvas(MyCanvas canvas)
    {
        this.canvas = canvas;
    }

    protected MyCanvas getCanvas()
    {
        return (MyCanvas) canvas;
    }

    public int processInput()
        throws Exception
    {
        return -1;
    }
}
