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
package allbinary.input.motion.gesture;

import allbinary.graphics.GPoint;
import allbinary.graphics.PointFactory;
import allbinary.input.motion.gesture.observer.MotionEventCircularPool;
import allbinary.input.motion.gesture.observer.MotionGestureEvent;

public class MirrorMotionGestureEventUtil
{
    private static final MirrorMotionGestureEventUtil instance =
        new MirrorMotionGestureEventUtil();
    
    private final MotionEventCircularPool motionEventCircularPool;
    
    private MirrorMotionGestureEventUtil()
    {
        this.motionEventCircularPool =
            MotionEventCircularPool.getInstance(Integer.MAX_VALUE);
    }
    
    public MotionGestureEvent mirrorVerticle(MotionGestureEvent motionGestureEvent, int halfWidth)
    throws Exception
    {
        MotionGestureEvent event =
            this.motionEventCircularPool.getInstance(
                    motionGestureEvent.getMotionGesture());
        
        final int previousNewX = 
            this.getNewX(motionGestureEvent.getPreviousPoint().getX(), halfWidth);

        final int currentNewX = 
            this.getNewX(motionGestureEvent.getCurrentPoint().getX(), halfWidth);
        
        PointFactory pointFactory = PointFactory.getInstance();
        
        GPoint mirroredPreviousPoint = pointFactory.getInstance(
                previousNewX, motionGestureEvent.getPreviousPoint().getY());

        GPoint mirroredCurrentPoint = pointFactory.getInstance(
                currentNewX, motionGestureEvent.getCurrentPoint().getY());
        
        event.setPreviousPoint(mirroredPreviousPoint);
        event.setCurrentPoint(mirroredCurrentPoint);
        
        return event;
    }

    public int getNewX(int x, int halfWidth)
    {
        int newX;
        
        if(x > halfWidth)
        {
           newX = halfWidth - (x - halfWidth);
        }
        else
        {
           newX = halfWidth + (halfWidth - x);
        }
        
        return newX;
    }

    public static MirrorMotionGestureEventUtil getInstance()
    {
        return instance;
    }
}
