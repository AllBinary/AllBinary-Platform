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
package allbinary.game.displayable.canvas;

import org.allbinary.util.BasicArrayList;

import allbinary.graphics.displayable.MyCanvas;
import allbinary.graphics.form.ScrollSelectionForm;
import allbinary.input.motion.gesture.observer.MotionGestureEvent;

public class PopupCommandFormInputProcessor 
extends CommandFormInputProcessor 
{
    private PopupMenuInputProcessor popupMenuInputProcessor;

    public PopupCommandFormInputProcessor(
        BasicArrayList gameKeyEventList,
        MyCanvas gameCanvas,
        ScrollSelectionForm form,
        PopupMenuInputProcessor popupMenuInputProcessor)
    {
        super(gameKeyEventList, gameCanvas, form);

        this.popupMenuInputProcessor = popupMenuInputProcessor;
    }

    public int processInput(int key) throws Exception
    {
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_LABEL + " Canvas." + CanvasUtil.getKeyName(key), this, GameInputStrings.getInstance()));
        this.popupMenuInputProcessor.processInput(key);
        return super.processInput(key);
    }

    protected void processMotionInput(MotionGestureEvent motionGestureEvent)
        throws Exception
    {
        this.popupMenuInputProcessor.processMotionInput(motionGestureEvent);
        super.processMotionInput(motionGestureEvent);
    }
}
