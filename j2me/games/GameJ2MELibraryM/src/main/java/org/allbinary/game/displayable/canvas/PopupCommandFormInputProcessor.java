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

import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.graphics.form.ScrollSelectionForm;
import org.allbinary.input.motion.gesture.observer.MotionGestureEvent;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.util.BasicArrayList;

public class PopupCommandFormInputProcessor 
extends CommandFormInputProcessor 
{

    //private PopupMenuInputProcessor popupMenuInputProcessor;

    public PopupCommandFormInputProcessor(
        BasicArrayList gameKeyEventList,
        int playerInputId, 
        MyCanvas gameCanvas,
        ScrollSelectionForm form,
        PopupMenuInputProcessor popupMenuInputProcessor)
    {
        super(gameKeyEventList, playerInputId, gameCanvas, form);

        //logUtil.put(commonStrings.CONSTRUCTOR, this, commonStrings.CONSTRUCTOR);
        
        //this.popupMenuInputProcessor = popupMenuInputProcessor;
    }

    @Override
    public int processInput(int key) throws Exception
    {
        //logUtil.put(commonStrings.START_LABEL).append(" Canvas.").append(CanvasUtil.getKeyName(key), this, GameInputStrings.getInstance());
        //this.popupMenuInputProcessor.processInput(key);
        return super.processInput(key);
    }

    @Override
    protected void processMotionInput(MotionGestureEvent motionGestureEvent)
        throws Exception
    {
        //this.popupMenuInputProcessor.processMotionInput(motionGestureEvent);
        super.processMotionInput(motionGestureEvent);
    }
}
