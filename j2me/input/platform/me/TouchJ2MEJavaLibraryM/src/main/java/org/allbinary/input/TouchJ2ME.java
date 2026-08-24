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
package org.allbinary.input;

import jsinterop.annotations.JsType;

import org.allbinary.input.motion.AllMotionRecognizer;
import org.allbinary.input.motion.gesture.observer.BasicMotionGesturesHandler;
import org.allbinary.input.motion.gesture.observer.GameMotionGestureListener;
import org.allbinary.input.motion.gesture.observer.MotionGestureReceiveInterfaceFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author user
 */
//TouchJ2ME

@JsType
public class TouchJ2ME
{
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final int DEVICE_ID = 0;
    private AllMotionRecognizer motionRecognizer = new AllMotionRecognizer();

    @JsConstructor
    public TouchJ2ME()
    {
        BasicMotionGesturesHandler motionGesturesHandler =
            this.motionRecognizer.getMotionGestureRecognizer().getMotionGesturesHandler();

        motionGesturesHandler.addListenerInterface(
            new GameMotionGestureListener(
            MotionGestureReceiveInterfaceFactory.getInstance()));
    }

    @JsMethod
    public void pointerDragged(int x, int y)
    {
        try
        {
            this.motionRecognizer.processDraggedMotionEvent(x, y, DEVICE_ID, 0);
        }
        catch(Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this, "pointerDragged", e);
        }
    }

    @JsMethod
    public void pointerPressed(int x, int y)
    {
        try
        {
            this.motionRecognizer.processStartMotionEvent(x, y, DEVICE_ID, 0);
        }
        catch(Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this, "pointerPressed", e);
        }
    }

    @JsMethod
    public void pointerReleased(int x, int y)
    {
        try
        {
            this.motionRecognizer.processEndMotionEvent(x, y, DEVICE_ID, 0);
        }
        catch(Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this, "pointerReleased", e);
        }
    }
}
