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
package org.allbinary.game.input.event;

import org.allbinary.game.input.PlayerGameInput;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.util.BasicArrayList;

public class DownKeyEventHandlerBase extends BasicEventHandler {

    private final BasicArrayList list = new BasicArrayList();

    DownKeyEventHandlerBase() {
    }

    public void addListener(final PlayerGameInput playerGameInput) {
        if (!list.contains(playerGameInput)) {

            list.add(playerGameInput);
            
            //ForcedLogUtil.log(this.toString(), commonStrings.ADD_LISTENER);
        }
    }

    @Override
    public void removeAllListeners() {

        //ForcedLogUtil.log(this.toString(), "removeAllListeners");
        
        this.list.clear();
        super.removeAllListeners();
        
        //ForcedLogUtil.log(this.toString(), "removeAllListeners");
    }

    @Override
    public void removeListenerSingleThreaded(final EventListenerInterface eventListenerInterface) {
        
        this.list.remove(eventListenerInterface);
        super.removeListenerSingleThreaded(eventListenerInterface);
        
        //ForcedLogUtil.log(this.toString(), "removeListenerSingleThreaded");
    }

    @Override
    public synchronized void removeListener(EventListenerInterface eventListenerInterface) {

        this.list.remove(eventListenerInterface);
        super.removeListener(eventListenerInterface);
        
        //ForcedLogUtil.log(this.toString(), commonStrings.REMOVE_LISTENER);
    }

    public void fireEvent(final Integer eventObject) throws Exception {
        //ForcedLogUtil.log(this.toString(), EventStrings.getInstance().FIRE_EVENT);

        for (int index = this.list.size(); --index >= 0;) {
            try {
                //Add deviceId
                PlayerGameInput playerGameInput = (PlayerGameInput) this.list.objectArray[index];
                playerGameInput.onDownKeyEvent(eventObject);
            } catch (Exception e) {
                logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
            }
        }

        int index = 0;
        EventListenerInterface eventListenerInterface;
        while (index < this.eventListenerInterfaceList.size())
        {
            try
            {
                eventListenerInterface = (EventListenerInterface) this.eventListenerInterfaceList.get(index);
                this.process(eventObject, eventListenerInterface);
            }
            catch (Exception e)
            {
                logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
            }
            index++;
        }

    }

    public void fireEvent(final GameKeyEvent eventObject) throws Exception {
        //ForcedLogUtil.log(this.toString(), EventStrings.getInstance().FIRE_EVENT);

        final int size = this.list.size();
        for (int index = size; --index >= 0;) {
            try {
                //Add deviceId
                PlayerGameInput playerGameInput = (PlayerGameInput) this.list.objectArray[index];
                playerGameInput.onDownKeyEvent(eventObject);
            } catch (Exception e) {
                logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
            }
        }

        int index = 0;
        EventListenerInterface eventListenerInterface;
        while (index < this.eventListenerInterfaceList.size())
        {
            try
            {
                eventListenerInterface = (EventListenerInterface) this.eventListenerInterfaceList.get(index);
                this.process(eventObject, eventListenerInterface);
            }
            catch (Exception e)
            {
                logUtil.put(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
            }
            index++;
        }

    }
    
    protected void process(final Integer eventObject, final EventListenerInterface eventListenerInterface) throws Exception {
        final DownKeyEventListenerInterface downKeyEventListenerInterface = (DownKeyEventListenerInterface) eventListenerInterface;
        downKeyEventListenerInterface.onDownKeyEvent(eventObject);
    }

    protected void process(final GameKeyEvent eventObject, final EventListenerInterface eventListenerInterface) throws Exception {
        final DownKeyEventListenerInterface downKeyEventListenerInterface = (DownKeyEventListenerInterface) eventListenerInterface;
        downKeyEventListenerInterface.onDownKeyEvent(eventObject);
    }
    
    private static final String TOTAL_LISTENERS = " Total PlayerGameInput Listeners: ";
    private static final String LISTENER_LABEL = " PlayerGameInput Listener: ";

    public String toString() {
        final StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(super.toString());
        stringBuffer.append(TOTAL_LISTENERS);
        stringBuffer.append(this.list.size());

        EventListenerInterface eventListenerInterface;
        for (int index = 0; index < this.list.size(); index++) {
            try {
                eventListenerInterface = (EventListenerInterface) // enumeration.nextElement();
                        this.list.get(index);

                stringBuffer.append(LISTENER_LABEL);
                stringBuffer.append(eventListenerInterface.toString());
            } catch (Exception e) {
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.TOSTRING, e);
            }
        }
        return stringBuffer.toString();
    }
}
