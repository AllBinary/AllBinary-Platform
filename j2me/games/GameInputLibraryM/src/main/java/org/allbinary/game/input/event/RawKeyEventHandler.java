/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.game.input.event;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class RawKeyEventHandler {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final RawKeyEventHandler instance = new RawKeyEventHandler();

    /**
     * @return the instance
     */
    public static RawKeyEventHandler getInstance() {
        return instance;
    }

    protected BasicArrayList eventListenerInterfaceList;

    public RawKeyEventHandler()
    {
        this.eventListenerInterfaceList = new BasicArrayList();
    }

    public  void removeAllListeners()
    {
        this.eventListenerInterfaceList = new BasicArrayList();
    }

    public  void addListeners(final BasicArrayList vector)
    {
        RawKeyEventListener eventListenerInterface;
        final int size = vector.size();
        for (int index = 0; index < size; index++)
        {
            eventListenerInterface =
                (RawKeyEventListener) vector.get(index);
            this.addListener(eventListenerInterface);
        }
    }

    public void addListenerSingleThreaded(final RawKeyEventListener eventListenerInterface)
    {
        if (!this.eventListenerInterfaceList.contains(eventListenerInterface))
        {
            //logUtil.put(CommonLabels.getInstance().START + eventListenerInterface, this, commonStrings.ADD_LISTENER);
            this.eventListenerInterfaceList.add(eventListenerInterface);
        }
    }

    public  void addListener(final RawKeyEventListener eventListenerInterface)
    {
        if (!this.eventListenerInterfaceList.contains(eventListenerInterface))
        {
            //logUtil.put(CommonLabels.getInstance().START + eventListenerInterface, this, commonStrings.ADD_LISTENER);
            this.eventListenerInterfaceList.add(eventListenerInterface);
        }
    }

    public void removeListenerSingleThreaded(final RawKeyEventListener eventListenerInterface)
     {
            //logUtil.put(
              //      CommonLabels.getInstance().START + eventListenerInterface, this, commonStrings.ADD_LISTENER);

        this.eventListenerInterfaceList.remove(eventListenerInterface);
    }
    
    public void removeListener(final RawKeyEventListener eventListenerInterface)
    {
        this.eventListenerInterfaceList.remove(eventListenerInterface);
    }

    public void fireEvent(final int keyCode, final int deviceId, final boolean repeated)
        throws Exception
    {
        RawKeyEventListener eventListenerInterface;
        for (int index = 0; index < this.eventListenerInterfaceList.size(); index++)
        {
            try
            {
                eventListenerInterface = (RawKeyEventListener) // enumeration.nextElement();
                    this.eventListenerInterfaceList.get(index);
                this.process(keyCode, deviceId, repeated, eventListenerInterface);
            }
            catch (Exception e)
            {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
            }
        }

    }

    protected void process(final int keyCode, final int deviceId, final boolean repeated,
        final RawKeyEventListener eventListenerInterface) throws Exception
    {
        eventListenerInterface.onEvent(keyCode, deviceId, repeated);
    }

    public BasicArrayList getEventListenerInterfaceListP()
    {
        return eventListenerInterfaceList;
    }
    
    private static final String TOTAL_LISTENERS = "Total Listeners: ";
    private static final String LISTENER_LABEL = " Listener: ";
    
    public String toString()
    {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(TOTAL_LISTENERS);
        stringBuffer.append(this.eventListenerInterfaceList.size());

        RawKeyEventListener eventListenerInterface;
        for (int index = 0; index < this.eventListenerInterfaceList.size(); index++)
        {
            try
            {
                eventListenerInterface = (RawKeyEventListener) // enumeration.nextElement();
                    this.eventListenerInterfaceList.get(index);

                stringBuffer.append(LISTENER_LABEL);
                stringBuffer.append(eventListenerInterface.toString());
            }
            catch (Exception e)
            {
                final CommonStrings commonStrings = CommonStrings.getInstance();
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.TOSTRING, e);
            }
        }
        return stringBuffer.toString();
    }    
    
}
