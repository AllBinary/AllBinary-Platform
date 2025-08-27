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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class BasicEventHandler implements BasicEventHandlerInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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
        final StringMaker stringBuffer = new StringMaker();

        final String LISTENER = " Listener: ";
        
        stringBuffer.append("Total Listeners: ");
        stringBuffer.append(this.eventListenerInterfaceList.size());

        EventListenerInterface eventListenerInterface;
        int index = 0;
        while (index < this.eventListenerInterfaceList.size())
        {
            try
            {
                eventListenerInterface = (EventListenerInterface) this.eventListenerInterfaceList.objectArray[index];

                stringBuffer.append(LISTENER);
                stringBuffer.append(StringUtil.getInstance().toString(eventListenerInterface));

            }
            catch (Exception e)
            {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.TOSTRING, e);
            }
            index++;
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
            //logUtil.put(
              //      CommonLabels.getInstance().START + eventListenerInterface, this, commonStrings.ADD_LISTENER);
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
        // logUtil.put("Start: Locks Held: " +
        // reentrantLock.getHoldCount() + " Held By Current Thread: " +
        // reentrantLock.isHeldByCurrentThread(), this, commonStrings.ADD_LISTENER);
        if (!this.eventListenerInterfaceList.contains(eventListenerInterface))
        {
            //logUtil.put(
              //      CommonLabels.getInstance().START + eventListenerInterface, this, commonStrings.ADD_LISTENER);
            this.eventListenerInterfaceList.add(eventListenerInterface);
        }
        /*
         * this.condition.signal();
         *  } catch(Exception e) { logUtil.put(this.commonStrings.EXCEPTION, this,
         * commonStrings.ADD_LISTENER, e); } finally { reentrantLock.unlock(); }
         */
    }

    public void removeListenerSingleThreaded(
            EventListenerInterface eventListenerInterface)
     {

            //logUtil.put(
              //      CommonLabels.getInstance().START + eventListenerInterface, this, commonStrings.ADD_LISTENER);

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
            // logUtil.put("Start: Locks Held: " +
            // reentrantLock.getHoldCount() + " Held By Current Thread: " +
            // reentrantLock.isHeldByCurrentThread(), this, commonStrings.REMOVE_LISTENER);

            //logUtil.put(
              //      CommonLabels.getInstance().START + eventListenerInterface, this, commonStrings.ADD_LISTENER);

            this.eventListenerInterfaceList.remove(eventListenerInterface);
            /*
             * this.condition.signal(); } catch(Exception e) { LogUtil.put(
             * LogFactory.getInstance(this.commonStrings.EXCEPTION, this, commonStrings.REMOVE_LISTENER, e)); } finally {
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
         * EventStrings.getInstance().FIRE_EVENT)); //logUtil.put(this.commonStrings.START, this, EventStrings.getInstance().FIRE_EVENT);
         *
         * iter = this.eventListenerInterfaceVector; while
         * (iter.hasNext()) { EventListenerInterface eventListenerInterface =
         * (EventListenerInterface) iter .next();
         *
         * this.process(eventObject, eventListenerInterface); }
         *
         * this.condition.signal(); } catch(Exception e) { LogUtil.put(new
         * Log(this.commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e)); } finally {
         * reentrantLock.unlock(); }
         */
        int index = 0;
        EventListenerInterface eventListenerInterface;
        while (index < this.eventListenerInterfaceList.size())
        {
            try
            {
                eventListenerInterface = (EventListenerInterface) this.eventListenerInterfaceList.objectArray[index];
                this.process(eventObject, eventListenerInterface);
            }
            catch (Exception e)
            {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
            }
            index++;
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

    public BasicArrayList getEventListenerInterfaceListP()
    {
        return eventListenerInterfaceList;
    }
}
