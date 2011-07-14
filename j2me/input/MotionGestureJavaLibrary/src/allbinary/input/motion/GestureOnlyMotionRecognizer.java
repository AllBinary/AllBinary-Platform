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
import allbinary.input.motion.gesture.MotionGestureRecognizer;

/**
 * 
 * @author user
 */
public class GestureOnlyMotionRecognizer extends MotionRecognizer
{
    private final MotionGestureRecognizer motionGestureRecognizer;
    
    private final int id;

    private static int index = 0;
    
    private static final GPointCircularPool pointCircularPool = new GPointCircularPool(40);

    public GestureOnlyMotionRecognizer()
    {
        this.id = index++;
        
        this.motionGestureRecognizer = new MotionGestureRecognizer(id);
    }
    
    public void processStartMotionEvent(int x, int y, int modifiers)
            throws Exception
    {
            CustomGPoint point = (CustomGPoint) pointCircularPool.getNextInstance();

            point.setX(x);
            point.setY(y);

            this.getMotionGestureRecognizer().processPressedMotionEvent(
                    point, modifiers);
    }

    public void processEndMotionEvent(int x, int y, int modifiers)
            throws Exception
    {
        CustomGPoint point = (CustomGPoint) pointCircularPool.getNextInstance();

        point.setX(x);
        point.setY(y);
        
        this.getMotionGestureRecognizer().processReleasedMotionEvent(
                point, modifiers);
    }

    public void processDraggedMotionEvent(int x, int y, int modifiers)
            throws Exception
    {
        CustomGPoint point = (CustomGPoint) pointCircularPool.getNextInstance();

        point.setX(x);
        point.setY(y);

        this.getMotionGestureRecognizer()
                .processDraggedMotionEvent(point, modifiers);
    }

    private final MotionGestureRecognizer getMotionGestureRecognizer()
    {
        return motionGestureRecognizer;
    }
}
