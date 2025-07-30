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

import org.allbinary.game.input.CompleteMotionGestureInputEvent;
import org.allbinary.util.BasicArrayList;

public class MotionGestureToMotionGestureActionAssociation
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private BasicArrayList list;
    private CompleteMotionGestureInputEvent motionGestureAction;

    public MotionGestureToMotionGestureActionAssociation(BasicArrayList list,
            CompleteMotionGestureInputEvent commandAction)
    {
        this.list = list;
        this.motionGestureAction = commandAction;
    }

    public BasicArrayList getMotionGesture()
    {
        return list;
    }

    public void setMotionGestures(BasicArrayList list)
    {
        this.list = list;
    }

    public CompleteMotionGestureInputEvent getCommandAction()
    {
        return motionGestureAction;
    }

    public void setMotionGestureAction(CompleteMotionGestureInputEvent commandAction)
    {
        this.motionGestureAction = commandAction;
    }

    public boolean isMotionGestureArrayEquals(BasicArrayList aList)
    {
        int size = aList.size();
        if (this.list.size() != size)
        {
            //logUtil.put("List Is Not The Same Size", this, "isMotionGestureArrayEquals");
            
            return false;
        }

        for (int i = size - 1; i >= 0; i--)
        {
            if (aList.objectArray[i] != this.list.objectArray[i])
            {
                //logUtil.put("List is different: aList: " + aList + " list: " + this.list, this, "isMotionGestureArrayEquals");
                
                return false;
            }
        }
        return true;
    }

}
