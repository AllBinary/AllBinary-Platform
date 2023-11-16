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

import org.allbinary.game.input.PlayerGameInput;
import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.input.motion.gesture.observer.BaseMotionGestureEventListener;
import org.allbinary.input.motion.gesture.observer.MotionGestureEvent;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;

public class BasicMenuInputProcessor extends PlayerGameInput
    implements BaseMotionGestureEventListener
{
    public final BasicArrayList motionGestureEventList = new BasicArrayList();
    private MyCanvas canvas;
    
    protected BasicMenuInputProcessor(BasicArrayList gameKeyEventList, int playerInputId, MyCanvas gameCanvas)
    {
        super(gameKeyEventList, playerInputId);
        
        this.setCanvas(gameCanvas);
    }
    
    public void onMotionGestureEvent(MotionGestureEvent motionGestureEvent)
    {
        //PreLogUtil.put(CommonStrings.getInstance().START, this, 
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
        return canvas;
    }

    public int processInput()
        throws Exception
    {
        return -1;
    }
}
