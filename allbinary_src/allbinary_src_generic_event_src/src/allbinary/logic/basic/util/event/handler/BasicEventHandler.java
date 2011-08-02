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
package allbinary.logic.basic.util.event.handler;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.EventListenerInterface;

public class BasicEventHandler implements BasicEventHandlerInterface
{
    public static final String PERFORMANCE_MESSAGE = "Use Custom onEvent Methods for needed optimization";

    private BasicArrayList tempList;
    private BasicArrayList swapList = new BasicArrayList();
    private BasicArrayList eventListenerInterfaceList = new BasicArrayList();

    // private ReentrantLock reentrantLock = new ReentrantLock();
    // private Condition condition = reentrantLock.newCondition();
    public BasicEventHandler()
    {
    }

    public synchronized void removeAllListeners()
    {
        this.tempList = this.eventListenerInterfaceList;
        this.eventListenerInterfaceList = this.swapList;
        this.swapList = this.tempList;
        this.swapList.clear();
    }

    public void addListeners(BasicArrayList vector)
    {
        int size = vector.size();
        for (int index = 0; index < size; index++)
        {
            EventListenerInterface eventListenerInterface = (EventListenerInterface) vector
                    .get(index);
            this.addListener(eventListenerInterface);
        }
    }

    public void addListener(
            EventListenerInterface eventListenerInterface)
    {

        /*
         * reentrantLock.lock(); try {
         * 
         * while(this.reentrantLock.getHoldCount() > 1) {
         * this.condition.await(); }
         */
        // LogUtil.put(LogFactory.getInstance("Locks Held: " +
        // reentrantLock.getHoldCount() + " Held By Current Thread: " +
        // reentrantLock.isHeldByCurrentThread(), this, "addListener"));
        if (!this.eventListenerInterfaceList.contains(eventListenerInterface))
        {
            //LogUtil.put(LogFactory.getInstance(eventListenerInterface.toString(), this, "addListener"));
            this.eventListenerInterfaceList.add(eventListenerInterface);
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("Already Contains Listener: "
                    + eventListenerInterface, this, "addListener"));
        }

        /*
         * this.condition.signal(); } catch(Exception e) {
         * LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "addListener",
         * e)); } finally { reentrantLock.unlock(); }
         */
    }

    public void removeListener(
            EventListenerInterface eventListenerInterface)
    {
        /*
         * reentrantLock.lock(); try {
         * 
         * while(this.reentrantLock.getHoldCount() > 1) {
         * this.condition.await(); }
         */
        // LogUtil.put(LogFactory.getInstance("Locks Held: " +
        // reentrantLock.getHoldCount() + " Held By Current Thread: " +
        // reentrantLock.isHeldByCurrentThread(), this, "removeListener"));

        //LogUtil.put(LogFactory.getInstance(eventListenerInterface.toString(), this, "removeListener"));
        this.eventListenerInterfaceList.remove(eventListenerInterface);

        /*
         * this.condition.signal(); } catch(Exception e) { LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "removeListener", e)); } finally {
         * reentrantLock.unlock(); }
         */
    }

    public synchronized void fireEvent(AllBinaryEventObject eventObject)
            throws Exception
    {
        /*
         * reentrantLock.lock(); try {
         * 
         * while(this.reentrantLock.getHoldCount() > 1) {
         * this.condition.await(); }
         * 
         * //if(this instance of DestroyedEventHandler) //LogUtil.put(new
         * Log("Locks Held: " + reentrantLock.getHoldCount() + " Held By
         * Current Thread: " + reentrantLock.isHeldByCurrentThread(), this,
         * "fireEvent")); //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this,
         * "fireEvent"));
         * 
         * Iterator iter = this.eventListenerInterfaceVector.iterator(); while
         * (iter.hasNext()) { EventListenerInterface eventListenerInterface =
         * (EventListenerInterface) iter .next();
         * 
         * this.process(eventObject, eventListenerInterface); }
         * 
         * this.condition.signal(); } catch(Exception e) { LogUtil.put(new
         * Log(CommonStrings.getInstance().EXCEPTION, this, "fireEvent", e)); } finally {
         * reentrantLock.unlock(); }
         */
        // int size = this.eventListenerInterfaceVector.size();

        for (int index = 0; index < this.eventListenerInterfaceList.size(); index++)
        {
            try
            {
                EventListenerInterface eventListenerInterface = 
                    (EventListenerInterface) // enumeration.nextElement();
                    this.eventListenerInterfaceList.get(index);
                this.process(eventObject, eventListenerInterface);
            }
            catch (Exception e)
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "fireEvent", e));
            }
        }

    }

    protected void process(AllBinaryEventObject eventObject,
            EventListenerInterface eventListenerInterface) throws Exception
    {
        // BasicEventListenerInterface basicEventListenerInterface =
        // (BasicEventListenerInterface) eventListenerInterface;

        eventListenerInterface.onEvent(eventObject);
        // basicEventListenerInterface.onEvent(eventObject);
    }

    public BasicArrayList getEventListenerInterfaceList()
    {
        return eventListenerInterfaceList;
    }
}
