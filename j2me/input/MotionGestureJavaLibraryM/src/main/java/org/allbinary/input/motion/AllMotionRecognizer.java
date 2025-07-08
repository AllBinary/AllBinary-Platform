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
package org.allbinary.input.motion;

import org.allbinary.graphics.CustomGPoint;
import org.allbinary.graphics.GPointCircularPool;
import org.allbinary.input.motion.button.TouchButtonRecognizer;
import org.allbinary.input.motion.gesture.MotionGestureRecognizer;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

/**
 * 
 * @author user
 */
public class AllMotionRecognizer extends MotionRecognizer
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final MotionGestureRecognizer motionGestureRecognizer;
    private boolean touchButtonProcessing = false;
    private final TouchButtonRecognizer touchButtonRecognizer;

    private final int id;

    private static int index = 0;

    private static final GPointCircularPool pointCircularPool = 
        new GPointCircularPool(40);

    public AllMotionRecognizer()
    {
        this.id = index++;

        this.motionGestureRecognizer = new MotionGestureRecognizer(id);
        this.touchButtonRecognizer = new TouchButtonRecognizer();
    }

    public void processStartMotionEvent(final int x, final int y, final int deviceId, final int modifiers)
            throws Exception
    {
        // TWB - should now actually occur for more than one button
        // Only one touch button can be pressed by a pointer
        // !touchButtonProcessing &&
        if (this.touchButtonRecognizer.pressTouchButtonInput(x, y, deviceId))
        {
            touchButtonProcessing = true;
        }

        // Only if touch buttons are not in processing can a gesture start
        if (!touchButtonProcessing)
        {
            final CustomGPoint point = (CustomGPoint) pointCircularPool.getNextInstance();

            point.setX(x);
            point.setY(y);

//            logUtil.put(
//                    new StringBuilder().append(commonStrings.START_LABEL)
//                            .append("point: ").append(point.toString()).toString(),
//                    this, "processStartMotionEvent");
            
            this.motionGestureRecognizer.processPressedMotionEvent(point, deviceId, modifiers);
        }
    }

    public void processEndMotionEvent(final int x, final int y, final int deviceId, final int modifiers)
            throws Exception
    {
        if (this.touchButtonRecognizer.releaseTouchButtonInput(x, y, deviceId))
        {
            // this.touchButtonRecognizer.releaseAll();
            //return;
        }
        
        if (!touchButtonProcessing)
        {
            final CustomGPoint point = (CustomGPoint) pointCircularPool.getNextInstance();

            point.setX(x);
            point.setY(y);

//            logUtil.put(
//                    new StringBuilder().append(commonStrings.START_LABEL)
//                            .append("point: ").append(point.toString()).toString(),
//                    this, "processEndMotionEvent");
            
            this.motionGestureRecognizer.processReleasedMotionEvent(point, deviceId, modifiers);
        }
        
        if (touchButtonProcessing)
        {
            touchButtonProcessing = false;

            // else should never occur
        }
    }

    public void processDraggedMotionEvent(final int x, final int y, final int deviceId, final int modifiers)
            throws Exception
    {
        // Allows sliding from one button to the next without releasing but only
        // if a button press was already processing
        if (touchButtonProcessing)
        {
            this.touchButtonRecognizer.pressTouchButtonInput(x, y, deviceId);
        }

        if (!touchButtonProcessing)
        {
            final CustomGPoint point = (CustomGPoint) pointCircularPool.getNextInstance();

            point.setX(x);
            point.setY(y);

            this.motionGestureRecognizer.processDraggedMotionEvent(point, deviceId, modifiers);
        }
    }

    private int lastX;
    private int lastY;
    public void processMovedMotionEvent(final int x, final int y, final int deviceId, final int modifiers)
            throws Exception
    {
        if(x != lastX || y != lastY) {

            this.lastX = x;
            this.lastY = y;
            
            final CustomGPoint point = (CustomGPoint) pointCircularPool.getNextInstance();

            point.setX(x);
            point.setY(y);

            this.motionGestureRecognizer.processMovedMotionEvent(point, deviceId, modifiers);
        }
    }
    
    public final MotionGestureRecognizer getMotionGestureRecognizer()
    {
        return motionGestureRecognizer;
    }
}
