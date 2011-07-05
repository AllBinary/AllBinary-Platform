/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: 11/19/02
 *
 *
 * Modified By         When       ?
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
    
    protected BasicMenuInputProcessor(BasicArrayList gameKeyEventList, MyCanvas gameCanvas)
    {
        super(gameKeyEventList);
        
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
