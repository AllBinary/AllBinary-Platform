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
package org.allbinary.game.input;

import org.allbinary.input.motion.gesture.MotionGestureInput;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.string.CommonStrings;

public class CompleteMotionGestureInputEvent extends AllBinaryEventObject 
implements CompleteMotionGestureInputInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private String name;
    
    private MotionGestureInput motionGestureInput;
    
    public CompleteMotionGestureInputEvent(String name, MotionGestureInput motionGestureInput)
    {
        super(motionGestureInput);
        this.setName(name);
        try
        {
            this.setMotionGestureInput(motionGestureInput);
        }
        catch(Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, "InputToGameKeyEventAction", e);
        }        
    }
    
    protected void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    
    public void setMotionGestureInput(MotionGestureInput motionGestureInput)
    {
        this.motionGestureInput = motionGestureInput;
    }

    public MotionGestureInput getMotionGestureInput()
    {
        return motionGestureInput;
    }
    
}
