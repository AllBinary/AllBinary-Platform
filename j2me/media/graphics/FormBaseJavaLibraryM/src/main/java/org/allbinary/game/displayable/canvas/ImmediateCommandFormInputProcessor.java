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

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.graphics.form.ScrollSelectionForm;
import org.allbinary.input.motion.gesture.observer.MotionGestureEvent;
import org.allbinary.time.TimeDelayHelper;

public class ImmediateCommandFormInputProcessor
    extends CommandFormInputProcessor
{
    private final TimeDelayHelper timeHelper = new TimeDelayHelper(25);
    
    public ImmediateCommandFormInputProcessor(
        BasicArrayList gameKeyEventList,
        int playerInputId, 
        MyCanvas gameCanvas,
        ScrollSelectionForm form)
    {
        super(gameKeyEventList, playerInputId, gameCanvas, form);
    }

    public synchronized void onPressGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
        try
        {
            //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "onPressGameKeyEvent: " + gameKeyEvent.getSourceId()));
            //PreLogUtil.put(commonStrings.START, this, "onPressGameKeyEvent");

            super.onPressGameKeyEvent(gameKeyEvent);

            if (timeHelper.isTime())
            {
                this.processInput();
                this.getCanvas().repaint();
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "onPressGameKeyEvent", e));
        }
    }
    
    public synchronized void onDownGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
        try
        {
            //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "onDownGameKeyEvent: " + gameKeyEvent.getSourceId()));
            //PreLogUtil.put(commonStrings.START, this, "onDownGameKeyEvent");

            super.onDownGameKeyEvent(gameKeyEvent);

            if (this.timeHelper.isTime())
            {
                this.processInput();
                this.getCanvas().repaint();
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "onDownGameKeyEvent", e));
        }
    }

    public synchronized void onUpGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
        try
        {
            //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "onUpGameKeyEvent: " + gameKeyEvent.getSourceId()));
            //PreLogUtil.put(commonStrings.START, this, "onUpGameKeyEvent");
            
            super.onUpGameKeyEvent(gameKeyEvent);

            if (timeHelper.isTime())
            {
                this.processInput();
                this.getCanvas().repaint();
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "onUpGameKeyEvent", e));
        }
    }

    public void onMotionGestureEvent(MotionGestureEvent motionGestureEvent)
    {
        try
        {
            //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "onMotionGestureEvent: " + motionGestureEvent.getId()));
            //PreLogUtil.put(commonStrings.START, this, "onMotionGestureEvent");
            
            super.onMotionGestureEvent(motionGestureEvent);
            if (timeHelper.isTime())
            {
                this.processInput();
                this.getCanvas().repaint();
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "onMotionGestureEvent", e));
        }
    }
}
