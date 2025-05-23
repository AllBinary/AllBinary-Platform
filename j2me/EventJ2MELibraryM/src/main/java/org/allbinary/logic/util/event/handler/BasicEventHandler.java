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
package org.allbinary.logic.util.event.handler;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.util.BasicArrayList;

public class BasicEventHandler implements BasicEventHandlerInterface
{
    private final EventStrings eventStrings = EventStrings.getInstance();
    
    protected BasicArrayList eventListenerInterfaceList;

    // private ReentrantLock reentrantLock = new ReentrantLock();
    // private Condition condition = reentrantLock.newCondition();
    public BasicEventHandler()
    {
        this.eventListenerInterfaceList = new BasicArrayList();
    }

    public void removeAllListeners()
    {
        this.eventListenerInterfaceList = new BasicArrayList();
    }

    public void addListeners(final BasicArrayList vector)
    {
        final int size = vector.size();
        EventListenerInterface eventListenerInterface;
        for (int index = 0; index < size; index++)
        {
            eventListenerInterface = (EventListenerInterface) vector.get(index);
            this.addListener(eventListenerInterface);
        }
    }

    public void removeListeners(final BasicArrayList vector)
    {
        final int size = vector.size();
        EventListenerInterface eventListenerInterface;
        for (int index = 0; index < size; index++)
        {
            eventListenerInterface = (EventListenerInterface) vector.get(index);
            this.removeListener(eventListenerInterface);
        }
    }
    
    public void addListenerSingleThreaded(final EventListenerInterface eventListenerInterface)
    {
        if (!this.eventListenerInterfaceList.contains(eventListenerInterface))
        {
            //LogUtil.put(LogFactory.getInstance(CommonLabels.getInstance().START + eventListenerInterface, this, "addListener"));
            this.eventListenerInterfaceList.add(eventListenerInterface);
        }
    }

    public void addListener(final EventListenerInterface eventListenerInterface)
    {

        /*
         * reentrantLock.lock(); try {
         *
         * while(this.reentrantLock.getHoldCount() > 1) {
         * this.condition.await(); }
         */
        // LogUtil.put(LogFactory.getInstance("Start: Locks Held: " +
        // reentrantLock.getHoldCount() + " Held By Current Thread: " +
        // reentrantLock.isHeldByCurrentThread(), this, "addListener"));
        if (!this.eventListenerInterfaceList.contains(eventListenerInterface))
        {
            //LogUtil.put(LogFactory.getInstance(CommonLabels.getInstance().START + eventListenerInterface, this, "addListener"));
            this.eventListenerInterfaceList.add(eventListenerInterface);
        }
        /*
         * this.condition.signal();
         *  } catch(Exception e) { LogUtil.put(LogFactory.getInstance("Exception", this,
         * "addListener", e)); } finally { reentrantLock.unlock(); }
         */
    }

    public void removeListenerSingleThreaded(final EventListenerInterface eventListenerInterface)
     {

            //LogUtil.put(LogFactory.getInstance(
              //      CommonLabels.getInstance().START + eventListenerInterface, this, "addListener"));

         this.eventListenerInterfaceList.remove(eventListenerInterface);
    }
    
    public void removeListener(final EventListenerInterface eventListenerInterface)
    {
        /*
         * reentrantLock.lock(); try {
         *
         * while(this.reentrantLock.getHoldCount() > 1) {
         * this.condition.await(); }
         */
        // LogUtil.put(LogFactory.getInstance("Start: Locks Held: " +
        // reentrantLock.getHoldCount() + " Held By Current Thread: " +
        // reentrantLock.isHeldByCurrentThread(), this, "removeListener"));

        //LogUtil.put(LogFactory.getInstance(
          //      CommonLabels.getInstance().START + eventListenerInterface, this, "addListener"));

        this.eventListenerInterfaceList.remove(eventListenerInterface);
        /*
         * this.condition.signal(); } catch(Exception e) { LogUtil.put(
         * LogFactory.getInstance("Exception", this, "removeListener", e)); } finally {
         * reentrantLock.unlock(); }
         */
    }

    public void fireEvent(final AllBinaryEventObject eventObject)
        throws Exception
    {
        /*
         * reentrantLock.lock(); try {
         *
         * while(this.reentrantLock.getHoldCount() > 1) {
         * this.condition.await(); }
         *
         * //if(this instance of DestroyedEventHandler) //LogUtil.put(new
         * Log("Start: Locks Held: " + reentrantLock.getHoldCount() + " Held By
         * Current Thread: " + reentrantLock.isHeldByCurrentThread(), this,
         * EventStrings.getInstance().FIRE_EVENT)); //LogUtil.put(LogFactory.getInstance("Start", this, EventStrings.getInstance().FIRE_EVENT));
         *
         * Iterator iter = this.eventListenerInterfaceVector.iterator(); while
         * (iter.hasNext()) { EventListenerInterface eventListenerInterface =
         * (EventListenerInterface) iter .next();
         *
         * this.process(eventObject, eventListenerInterface); }
         *
         * this.condition.signal(); } catch(Exception e) { LogUtil.put(new
         * Log("Exception", this, EventStrings.getInstance().FIRE_EVENT, e)); } finally {
         * reentrantLock.unlock(); }
         */
        // int size = this.eventListenerInterfaceVector.size();
        // Enumeration enumeration =
        // this.eventListenerInterfaceVector.elements();
        // while(enumeration.hasMoreElements())
        EventListenerInterface eventListenerInterface;
        for (int index = 0; index < this.eventListenerInterfaceList.size(); index++)
        {
            try
            {
                eventListenerInterface = (EventListenerInterface) // enumeration.nextElement();
                    this.eventListenerInterfaceList.get(index);
                this.process(eventObject, eventListenerInterface);
            }
            catch (Exception e)
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, eventStrings.FIRE_EVENT, e));
            }
        }

    }

    protected void process(final AllBinaryEventObject eventObject,
        final EventListenerInterface eventListenerInterface) throws Exception
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
        
    public String toString()
    {
        final StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(eventStrings.TOTAL_LISTENERS);
        stringBuffer.append(this.eventListenerInterfaceList.size());

        EventListenerInterface eventListenerInterface;
        for (int index = 0; index < this.eventListenerInterfaceList.size(); index++)
        {
            try
            {
                eventListenerInterface = (EventListenerInterface) // enumeration.nextElement();
                    this.eventListenerInterfaceList.get(index);

                stringBuffer.append(eventStrings.LISTENER_LABEL);
                stringBuffer.append(eventListenerInterface);
            }
            catch (Exception e)
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "toString", e));
            }
        }
        return stringBuffer.toString();
    }    
}
