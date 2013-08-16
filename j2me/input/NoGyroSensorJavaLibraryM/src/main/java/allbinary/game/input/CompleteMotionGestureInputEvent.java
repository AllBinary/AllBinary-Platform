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
package allbinary.game.input;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.input.motion.gesture.MotionGestureInput;
import allbinary.logic.basic.util.event.AllBinaryEventObject;

public class CompleteMotionGestureInputEvent extends AllBinaryEventObject 
implements CompleteMotionGestureInputInterface
{
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
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "InputToGameKeyEventAction", e));
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
