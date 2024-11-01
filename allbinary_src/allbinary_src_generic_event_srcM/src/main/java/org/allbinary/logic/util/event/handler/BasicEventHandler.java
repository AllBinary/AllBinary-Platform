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

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.util.event.EventStrings;

public class BasicEventHandler implements BasicEventHandlerInterface
{
    public static final String PERFORMANCE_MESSAGE = "Use Custom onEvent Methods for needed optimization";
    private BasicArrayList eventListenerInterfaceList;

    // private ReentrantLock reentrantLock = new ReentrantLock();
    // private Condition condition = reentrantLock.newCondition();
    public BasicEventHandler()
    {
        this.eventListenerInterfaceList = new BasicArrayList();
    }

    public String toString()
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("Total Listeners: ");
        stringBuffer.append(this.eventListenerInterfaceList.size());

        for (int index = 0; index < this.eventListenerInterfaceList.size(); index++)
        {
            try
            {
                EventListenerInterface eventListenerInterface = (EventListenerInterface) // enumeration.nextElement();
                    this.eventListenerInterfaceList.objectArray[index];

                stringBuffer.append(" Listener: ");
                stringBuffer.append(eventListenerInterface);

            }
            catch (Exception e)
            {
                LogUtil.put(LogFactory.getInstance("Exception", this, "toString", e));
            }
        }
        return stringBuffer.toString();
    }

    public synchronized void removeAllListeners()
    {
        this.eventListenerInterfaceList = new BasicArrayList();
    }

    /*
    public synchronized void addListeners(BasicArrayList vector)
    {
        int size = vector.size();
        for (int index = 0; index < size; index++)
        {
            EventListenerInterface eventListenerInterface =
                (EventListenerInterface) vector.get(index);
            this.addListener(eventListenerInterface);
        }
    }
    */

    public void addListenerSingleThreaded(
            EventListenerInterface eventListenerInterface)
    {
        if (!this.eventListenerInterfaceList.contains(eventListenerInterface))
        {
            //LogUtil.put(LogFactory.getInstance(
              //      CommonLabels.getInstance().START + eventListenerInterface, this, "addListener"));
            this.eventListenerInterfaceList.add(eventListenerInterface);
        }
    }
    
    public synchronized void addListener(
            EventListenerInterface eventListenerInterface)
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
            //LogUtil.put(LogFactory.getInstance(
              //      CommonLabels.getInstance().START + eventListenerInterface, this, "addListener"));
            this.eventListenerInterfaceList.add(eventListenerInterface);
        }
        /*
         * this.condition.signal();
         *  } catch(Exception e) { LogUtil.put(LogFactory.getInstance("Exception", this,
         * "addListener", e)); } finally { reentrantLock.unlock(); }
         */
    }

    public void removeListenerSingleThreaded(
            EventListenerInterface eventListenerInterface)
     {

            //LogUtil.put(LogFactory.getInstance(
              //      CommonLabels.getInstance().START + eventListenerInterface, this, "addListener"));

         this.eventListenerInterfaceList.remove(eventListenerInterface);
    }
    
    public synchronized void removeListener(
            EventListenerInterface eventListenerInterface)
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
        for (int index = 0; index < this.eventListenerInterfaceList.size(); index++)
        {
            try
            {
                EventListenerInterface eventListenerInterface = (EventListenerInterface) // enumeration.nextElement();
                    this.eventListenerInterfaceList.objectArray[index];
                this.process(eventObject, eventListenerInterface);
            }
            catch (Exception e)
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e));
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
