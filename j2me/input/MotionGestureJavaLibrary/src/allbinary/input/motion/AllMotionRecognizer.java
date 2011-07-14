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
package allbinary.input.motion;

import allbinary.graphics.CustomGPoint;
import allbinary.graphics.GPointCircularPool;
import allbinary.input.motion.button.TouchButtonRecognizer;
import allbinary.input.motion.gesture.MotionGestureRecognizer;

/**
 * 
 * @author user
 */
public class AllMotionRecognizer extends MotionRecognizer
{
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

    public void processStartMotionEvent(int x, int y, int modifiers)
            throws Exception
    {
        // TWB - should now actually occur for more than one button
        // Only one touch button can be pressed by a pointer
        // !touchButtonProcessing &&
        if (this.touchButtonRecognizer.pressTouchButtonInput(x, y))
        {
            touchButtonProcessing = true;
        }

        // Only if touch buttons are not in processing can a gesture start
        if (!touchButtonProcessing)
        {
            CustomGPoint point = (CustomGPoint) pointCircularPool.getNextInstance();

            point.setX(x);
            point.setY(y);

            this.motionGestureRecognizer.processPressedMotionEvent(point, modifiers);
        }
    }

    public void processEndMotionEvent(int x, int y, int modifiers)
            throws Exception
    {
        if (this.touchButtonRecognizer.releaseTouchButtonInput(x, y))
        {
            // this.touchButtonRecognizer.releaseAll();
            //return;
        }
        
        if (!touchButtonProcessing)
        {
            CustomGPoint point = (CustomGPoint) pointCircularPool.getNextInstance();

            point.setX(x);
            point.setY(y);

            this.motionGestureRecognizer.processReleasedMotionEvent(point, modifiers);
        }
        
        if (touchButtonProcessing)
        {
            touchButtonProcessing = false;

            // else should never occur
        }
    }

    public void processDraggedMotionEvent(int x, int y, int modifiers)
            throws Exception
    {
        // Allows sliding from one button to the next without releasing but only
        // if a button press was already processing
        if (touchButtonProcessing)
        {
            this.touchButtonRecognizer.pressTouchButtonInput(x, y);
        }

        if (!touchButtonProcessing)
        {
            CustomGPoint point = (CustomGPoint) pointCircularPool
                    .getNextInstance();

            point.setX(x);
            point.setY(y);

            this.motionGestureRecognizer.processDraggedMotionEvent(point, modifiers);
        }
    }

    public final MotionGestureRecognizer getMotionGestureRecognizer()
    {
        return motionGestureRecognizer;
    }
}
