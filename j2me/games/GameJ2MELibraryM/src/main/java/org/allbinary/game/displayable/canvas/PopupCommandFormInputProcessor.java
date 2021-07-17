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

import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.graphics.form.ScrollSelectionForm;
import org.allbinary.input.motion.gesture.observer.MotionGestureEvent;
import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

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

        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().CONSTRUCTOR, this, CommonStrings.getInstance().CONSTRUCTOR));
        
        //this.popupMenuInputProcessor = popupMenuInputProcessor;
    }

    public int processInput(int key) throws Exception
    {
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_LABEL + " Canvas." + CanvasUtil.getKeyName(key), this, GameInputStrings.getInstance()));
        //this.popupMenuInputProcessor.processInput(key);
        return super.processInput(key);
    }

    protected void processMotionInput(MotionGestureEvent motionGestureEvent)
        throws Exception
    {
        //this.popupMenuInputProcessor.processMotionInput(motionGestureEvent);
        super.processMotionInput(motionGestureEvent);
    }
}
