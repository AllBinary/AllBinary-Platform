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
import org.allbinary.graphics.Line;
import org.allbinary.graphics.PointFactory;
import org.allbinary.input.motion.gesture.configuration.MotionGestureConfiguration;
import org.allbinary.input.motion.gesture.configuration.MotionGestureConfigurationFactory;
import org.allbinary.input.motion.gesture.observer.BasicMotionGesturesHandler;
import org.allbinary.input.motion.gesture.observer.MotionEventCircularPool;
import org.allbinary.input.motion.gesture.observer.MotionGestureEvent;
import org.allbinary.input.motion.gesture.observer.MovedMotionGesturesHandler;
import org.allbinary.input.motion.gesture.observer.ScrolledMotionGesturesHandler;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.math.J2SEMath;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.string.CommonStrings;

public class MotionGestureRecognizer
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final J2SEMath j2seMath = J2SEMath.getInstance();

    private final GPoint origin = PointFactory.getInstance().ZERO_ZERO;

    private GPoint previous = this.origin;
    private GPoint intermediate = this.origin;

    private final Line line = new Line(this.origin, this.origin);

    //TWB - should be treated like the KeyEventHandlers per player for deviceId
    private final BasicMotionGesturesHandler motionGesturesHandler;
    private final BasicEventHandler movedMotionGesturesHandler;
    private final BasicEventHandler scrolledMotionGesturesHandler;

    //private final int id;
    
    private final MotionEventCircularPool motionEventCircularPool;
    
    public MotionGestureRecognizer(final int id)
    {
        //this.id = id;

        this.motionEventCircularPool =
            MotionEventCircularPool.createPool(id);
        
        
        BasicEventHandler motionGesturesHandler = new BasicEventHandler();
        BasicEventHandler movedMotionGesturesHandler = motionGesturesHandler;
        BasicEventHandler scrolledMotionGesturesHandler = motionGesturesHandler;
        try
        {
            motionGesturesHandler = BasicMotionGesturesHandler.getInstance();
            movedMotionGesturesHandler = MovedMotionGesturesHandler.getInstance();
            scrolledMotionGesturesHandler = ScrolledMotionGesturesHandler.getInstance();
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
        }
        
        this.motionGesturesHandler = (BasicMotionGesturesHandler) motionGesturesHandler;
        this.movedMotionGesturesHandler = movedMotionGesturesHandler;
        this.scrolledMotionGesturesHandler = scrolledMotionGesturesHandler;
    }

    public boolean processPressedMotionEvent(final GPoint current, final int deviceId, final int button)
            throws Exception
    {
        //this.logUtil.putF(this.commonStrings.START_LABEL + current.toString(), this, "processPressedMotionEvent");
        //PreLogUtil.put(commonStrings.START, this, "processPressedMotionEvent");

        this.intermediate = this.origin;
        this.previous = this.origin;

        final MotionGestureEvent event =
            this.motionEventCircularPool.getInstance(
                    TouchMotionGestureFactory.getInstance().PRESSED);

        event.setPreviousPoint(this.previous);
        event.setCurrentPoint(current);

        //this.logUtil.putF("Firing Event: " + event, this, "processReleasedMotionEvent");
        
        this.motionGesturesHandler.fireEvent(event);

        return true;
    }

    public boolean processReleasedMotionEvent(final GPoint current, final int deviceId, final int button)
            throws Exception
    {
        //this.logUtil.putF(this.commonStrings.START_LABEL + current.toString(), this, "processReleasedMotionEvent");

        /*
         * if(this.touchButtonRecognizer.processTouchButtonInput(UpGameKeyEventHandler
         * .getInstance(), current)) { touchButtonProcessing = false; return
         * true; }
         */

        final MotionGestureEvent event =
            this.motionEventCircularPool.getInstance(
                    TouchMotionGestureFactory.getInstance().RELEASED);

        event.setPreviousPoint(this.previous);
        event.setCurrentPoint(current);

        //this.logUtil.putF("Firing Event: " + event, this, "processReleasedMotionEvent");
        
        this.motionGesturesHandler.fireEvent(event);

        return true;
    }

    public void processDraggedMotionEvent(final GPoint current, final int deviceId, final int buttonMask)
    // public Line processDraggedMotionEvent(GPoint current, int buttonMask)
            throws Exception
    {
        //PreLogUtil.put(commonStrings.START, this, "processDraggedMotionEvent");
        
        /*
         * if (buttonMask !=
         * MotionGestureConfigurationFactory.getInstance().getMouseButtonMask())
         * { //return null; return; }
         */

        //TWB - GPoints are not cached so what is the deal here?
        if (this.previous == this.origin || this.intermediate == this.origin)
        {
            this.previous = current;
            this.intermediate = current;
            // return null;
            return;
        }

        this.line.setP1(this.previous);
        this.line.setP2(current);
        // Line line = new Line(previous, current);
        // Line returnLine = new Line(intermediate, current);

        int minimumMotionGesture = MotionGestureConfigurationFactory
                .getInstance().getMinimumMotionGesture();
        if (this.j2seMath.abs((float) this.line.getDeltaX()) < minimumMotionGesture
                && this.j2seMath.abs((float) this.line.getDeltaY()) < minimumMotionGesture)
        {
            this.intermediate = current;
            return;
            // return returnLine;
        }

        double gradient = this.line.getGradient();
        double absGradient = (double) this.j2seMath.abs((float) gradient);

        final MotionGestureConfiguration conf = MotionGestureConfigurationFactory.getInstance();

        final TouchMotionGestureFactory touchMotionGestureFactory = TouchMotionGestureFactory.getInstance();
        MotionGestureInput newMotionGesture = touchMotionGestureFactory.NO_MOTION;

        double diagonalToleranceHigher = 12.0;
        double diagonalToleranceLower = 12.0;

        if (conf.isDiagonalMotionGestureAllowed())
        {
            diagonalToleranceHigher = (double) (90 - conf.getDiagonalTolerance());
            diagonalToleranceLower = (double) conf.getDiagonalTolerance();
        }

        if (absGradient > Math.tan(Math.toRadians(diagonalToleranceHigher)))
        {

            if (this.line.getDeltaY() > 0)
            {
                newMotionGesture = touchMotionGestureFactory.UP;
            }
            else
            {
                newMotionGesture = touchMotionGestureFactory.DOWN;
            }

        }
        else
        {

            if (absGradient < Math.tan(Math.toRadians(diagonalToleranceLower)))
            {

                // this.logUtil.putF("Left Or Right: " +
                // line.getDeltaX(), this, "processDraggedMotionEvent");

                if (this.line.getDeltaX() > 0)
                {
                    newMotionGesture = touchMotionGestureFactory.LEFT;
                }
                else
                {
                    newMotionGesture = touchMotionGestureFactory.RIGHT;
                }

            }
            else
            {

                //this.logUtil.putF("Diagonal: " + gradient + CommonStrings + PositionStrings + line.getDeltaX() + PositionStrings + line.getDeltaY(), this, "processDraggedMotionEvent");
                
                if (gradient > 0)
                {
                    if (this.line.getDeltaX() > 0)
                    {
                        //this.logUtil.putF("Diagonal Up Left", this, "processDraggedMotionEvent");
                        
                        newMotionGesture = touchMotionGestureFactory.DIAGONAL_UP_LEFT;
                    }
                    else
                    {
                        //this.logUtil.putF("Diagonal Down Right", this, "processDraggedMotionEvent");
                        
                        newMotionGesture = touchMotionGestureFactory.DIAGONAL_DOWN_RIGHT;
                    }
                }
                else
                {
                    if (this.line.getDeltaX() > 0)
                    {
                        //this.logUtil.putF("Diagonal Down Left", this, "processDraggedMotionEvent");
                        
                        newMotionGesture = touchMotionGestureFactory.DIAGONAL_DOWN_LEFT;
                    }
                    else
                    {
                        //this.logUtil.putF("Diagonal Up Right", this, "processDraggedMotionEvent");
                        
                        newMotionGesture = touchMotionGestureFactory.DIAGONAL_UP_RIGHT;
                    }
                }

            }

        }

        this.previous = current;
        this.intermediate = current;

        final MotionGestureEvent event =
            this.motionEventCircularPool.getInstance(newMotionGesture);

        event.setPreviousPoint(this.previous);
        event.setCurrentPoint(current);

        this.motionGesturesHandler.fireEvent(event);

        // return returnLine;
        return;

    }

    public boolean processMovedMotionEvent(final GPoint current, final int deviceId, final int button)
            throws Exception
    {
        //this.logUtil.putF(this.commonStrings.START_LABEL + current.toString(), this, "processReleasedMotionEvent");

        final MotionGestureEvent event =
            this.motionEventCircularPool.getInstance(
                    TouchMotionGestureFactory.getInstance().NO_MOTION);

        event.setPreviousPoint(this.previous);
        event.setCurrentPoint(current);

        //this.logUtil.putF("Firing Event: " + event, this, "processReleasedMotionEvent");
        
        this.movedMotionGesturesHandler.fireEvent(event);

        return true;
    }

    public boolean processScrolledMotionEvent(final GPoint current, final int deviceId, final int button)
            throws Exception
    {
        //this.logUtil.putF(this.commonStrings.START_LABEL + current.toString(), this, "processReleasedMotionEvent");

        final MotionGestureEvent event =
            this.motionEventCircularPool.getInstance(
                    TouchMotionGestureFactory.getInstance().NO_MOTION);

        event.setPreviousPoint(this.previous);
        event.setCurrentPoint(current);

        //this.logUtil.putF("Firing Event: " + event, this, "processReleasedMotionEvent");
        
        this.scrolledMotionGesturesHandler.fireEvent(event);

        return true;
    }
    
    public BasicMotionGesturesHandler getMotionGesturesHandler()
    {
        return this.motionGesturesHandler;
    }

}
