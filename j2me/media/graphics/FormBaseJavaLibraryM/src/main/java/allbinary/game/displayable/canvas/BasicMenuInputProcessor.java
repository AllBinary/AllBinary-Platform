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

import org.allbinary.util.BasicArrayList;

import allbinary.game.input.PlayerGameInput;
import allbinary.graphics.displayable.MyCanvas;
import allbinary.input.motion.gesture.observer.BaseMotionGestureEventListener;
import allbinary.input.motion.gesture.observer.MotionGestureEvent;

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
        //PreLogUtil.put(CommonStrings.getInstance().START, this, "onMotionGestureEvent");
       
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
