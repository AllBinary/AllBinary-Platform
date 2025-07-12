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

import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.graphics.form.ScrollSelectionForm;
import org.allbinary.input.motion.gesture.observer.MotionGestureEvent;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.time.TimeDelayHelper;
import org.allbinary.util.BasicArrayList;

public class ImmediateCommandFormInputProcessor
    extends CommandFormInputProcessor
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final TimeDelayHelper timeHelper = new TimeDelayHelper(25);
    
    protected GameCommandCanvas gameCommandCanvas;
    
    public ImmediateCommandFormInputProcessor(
        BasicArrayList gameKeyEventList,
        int playerInputId, 
        GameCommandCanvas gameCommandCanvas,
        ScrollSelectionForm form)
    {
        super(gameKeyEventList, playerInputId, gameCommandCanvas, form);
        
        this.gameCommandCanvas = gameCommandCanvas;
    }

    public synchronized void onPressGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
        try
        {
            //logUtil.put(commonStrings.START, this, "onPressGameKeyEvent: " + gameKeyEvent.getSourceId());
            //PreLogUtil.put(commonStrings.START, this, gameInputStrings.ON_PRESS_GAME_KEY);

            super.onPressGameKeyEvent(gameKeyEvent);

            if (timeHelper.isTime())
            {
                this.processInput();
                this.gameCommandCanvas.repaintBehavior.onChangeRepaint(this.getCanvas());
            }
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, gameInputStrings.ON_PRESS_GAME_KEY, e);
        }
    }
    
    public synchronized void onDownGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
        try
        {
            //logUtil.put(commonStrings.START, this, "onDownGameKeyEvent: " + gameKeyEvent.getSourceId());
            //PreLogUtil.put(commonStrings.START, this, gameInputStrings.ON_DOWN_GAME_KEY);

            super.onDownGameKeyEvent(gameKeyEvent);

            if (this.timeHelper.isTime())
            {
                this.processInput();
                this.gameCommandCanvas.repaintBehavior.onChangeRepaint(this.getCanvas());
            }
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, gameInputStrings.ON_DOWN_GAME_KEY, e);
        }
    }

    public synchronized void onUpGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
        try
        {
            //logUtil.put(commonStrings.START, this, "onUpGameKeyEvent: " + gameKeyEvent.getSourceId());
            //PreLogUtil.put(commonStrings.START, this, gameInputStrings.ON_UP_GAME_KEY);
            
            super.onUpGameKeyEvent(gameKeyEvent);

            if (timeHelper.isTime())
            {
                this.processInput();
                this.gameCommandCanvas.repaintBehavior.onChangeRepaint(this.getCanvas());
            }
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, gameInputStrings.ON_UP_GAME_KEY, e);
        }
    }

    public void onMotionGestureEvent(MotionGestureEvent motionGestureEvent)
    {
        try
        {
            //logUtil.put(commonStrings.START, this, "onMotionGestureEvent: " + motionGestureEvent.getId());
            //PreLogUtil.put(commonStrings.START, this, "onMotionGestureEvent");
            
            super.onMotionGestureEvent(motionGestureEvent);
            if (timeHelper.isTime())
            {
                this.processInput();
                this.gameCommandCanvas.repaintBehavior.onChangeRepaint(this.getCanvas());
            }
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, "onMotionGestureEvent", e);
        }
    }
}
