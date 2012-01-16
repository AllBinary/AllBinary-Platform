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

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.EventListenerInterface;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class CompleteMotionGestureInputEventHandler extends BasicEventHandler
{    
    private static final CompleteMotionGestureInputEventHandler SINGLETON = 
        new CompleteMotionGestureInputEventHandler();
    
    public static CompleteMotionGestureInputEventHandler getInstance()
    {
        return SINGLETON;
    }
    
    private final BasicArrayList list = new BasicArrayList();
    
    private CompleteMotionGestureInputEventHandler()
    {
    }

    public void addListener(CompleteMotionGestureInputEventListener completeMotionGestureInputEventListener)
    {
        if(!list.contains(completeMotionGestureInputEventListener))
        {
            list.add(completeMotionGestureInputEventListener);
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
                CompleteMotionGestureInputEventListener completeMotionGestureInputEventListener = 
                        (CompleteMotionGestureInputEventListener) this.list.get(index);
                completeMotionGestureInputEventListener.onCompleteMotionGestureInputEvent(
                        (CompleteMotionGestureInputEvent) eventObject);
            }
            catch (Exception e)
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "fireEvent", e));
            }
        }

        super.fireEvent(eventObject);
    }
        
    protected void process(AllBinaryEventObject eventObject,
            EventListenerInterface eventListenerInterface) throws Exception {

       ((CompleteMotionGestureInputEventListenerInterface) eventListenerInterface).onCompleteMotionGestureInputEvent(
               (CompleteMotionGestureInputEvent) eventObject);
    }
}
