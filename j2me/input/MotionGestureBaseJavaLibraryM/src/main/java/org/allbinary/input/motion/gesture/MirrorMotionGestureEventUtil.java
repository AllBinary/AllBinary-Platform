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
package org.allbinary.input.motion.gesture;

import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.input.motion.gesture.observer.MotionEventCircularPool;
import org.allbinary.input.motion.gesture.observer.MotionGestureEvent;

public class MirrorMotionGestureEventUtil
{
    private static final MirrorMotionGestureEventUtil instance =
        new MirrorMotionGestureEventUtil();
    
    private final MotionEventCircularPool motionEventCircularPool;
    
    private MirrorMotionGestureEventUtil()
    {
        this.motionEventCircularPool =
            MotionEventCircularPool.createPool(Integer.MAX_VALUE);
    }
    
    public MotionGestureEvent mirrorVerticle(final MotionGestureEvent motionGestureEvent, final int halfWidth)
    throws Exception
    {
        final MotionGestureEvent event =
            this.motionEventCircularPool.getInstance(
                    motionGestureEvent.getMotionGesture());
        
        final int previousNewX = 
            this.getNewX(motionGestureEvent.getPreviousPoint().getX(), halfWidth);

        final int currentNewX = 
            this.getNewX(motionGestureEvent.getCurrentPoint().getX(), halfWidth);
        
        final PointFactory pointFactory = PointFactory.getInstance();
        
        final GPoint mirroredPreviousPoint = pointFactory.createXY(
                previousNewX, motionGestureEvent.getPreviousPoint().getY());

        final GPoint mirroredCurrentPoint = pointFactory.createXY(
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
