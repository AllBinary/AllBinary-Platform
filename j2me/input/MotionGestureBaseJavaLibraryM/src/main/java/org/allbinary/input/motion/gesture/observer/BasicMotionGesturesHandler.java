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
package org.allbinary.input.motion.gesture.observer;


import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.handler.BasicEventHandler;

public class BasicMotionGesturesHandler extends BasicEventHandler
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final BasicMotionGesturesHandler SINGLETON =
       new BasicMotionGesturesHandler();

    public static final BasicMotionGesturesHandler getInstance()
    {
        return SINGLETON;
    }
    
    //private final BasicArrayList list = new BasicArrayList();
    
    protected BasicMotionGesturesHandler()
    {
    }

    /*
    public void addListener(PlayerGameInput playerGameInput)
    {
        if(!list.contains(playerGameInput))
        {
            list.add(playerGameInput);
        }
    }

    public void removeAllListeners()
    {
        this.list.clear();
        super.removeAllListeners();
    }

    public void removeListener(EventListenerInterface eventListenerInterface)
    {
        this.list.remove(eventListenerInterface);
        super.removeListener(eventListenerInterface);
    }

    public void fireEvent(AllBinaryEventObject eventObject) throws Exception
    {        
        for (int index = this.list.size(); --index >= 0;)
        {
            try
            {
                PlayerGameInput playerGameInput = (PlayerGameInput) this.list.get(index);
                playerGameInput.onMotionGestureEvent((MotionGestureEvent) eventObject);
            }
            catch (Exception e)
            {
                logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
            }
        }

        super.fireEvent(eventObject);
    }
    */

    protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {
        
        ((BaseMotionGestureEventListener) eventListenerInterface).onMotionGestureEvent(
           (MotionGestureEvent) eventObject);
    }
}
