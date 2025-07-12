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
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class BasicEventHandler implements BasicEventHandlerInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
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
        EventListenerInterface eventListenerInterface;
        final int size = vector.size();
        for (int index = 0; index < size; index++)
        {
            eventListenerInterface = (EventListenerInterface) vector.get(index);
            this.addListener(eventListenerInterface);
        }
    }

    public void removeListeners(final BasicArrayList vector)
    {
        EventListenerInterface eventListenerInterface;
        final int size = vector.size();
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
            //logUtil.put(CommonLabels.getInstance().START + eventListenerInterface, this, commonStrings.ADD_LISTENER);
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
        // logUtil.put("Start: Locks Held: " +
        // reentrantLock.getHoldCount() + " Held By Current Thread: " +
        // reentrantLock.isHeldByCurrentThread(), this, commonStrings.ADD_LISTENER);
        if (!this.eventListenerInterfaceList.contains(eventListenerInterface))
        {
            //logUtil.put(CommonLabels.getInstance().START + eventListenerInterface, this, commonStrings.ADD_LISTENER);
            this.eventListenerInterfaceList.add(eventListenerInterface);
        }
        /*
         * this.condition.signal();
         *  } catch(Exception e) { logUtil.put(this.commonStrings.EXCEPTION, this,
         * commonStrings.ADD_LISTENER, e); } finally { reentrantLock.unlock(); }
         */
    }

    public void removeListenerSingleThreaded(final EventListenerInterface eventListenerInterface)
     {

            //logUtil.put(
              //      CommonLabels.getInstance().START + eventListenerInterface, this, commonStrings.ADD_LISTENER);

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
                logUtil.put(commonStrings.EXCEPTION, this, eventStrings.FIRE_EVENT, e);
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
        final StringMaker stringBuffer = new StringMaker();

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
                stringBuffer.append(eventListenerInterface.toString());
            }
            catch (Exception e)
            {
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.TOSTRING, e);
            }
        }
        return stringBuffer.toString();
    }    
}
