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

import org.allbinary.string.CommonStrings;
//import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.input.PlayerGameInput;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.util.BasicArrayList;

public class DownGameKeyEventHandlerBase extends BasicEventHandler {

    private final BasicArrayList list = new BasicArrayList();

    protected DownGameKeyEventHandlerBase() {
    }

    public void addListener(PlayerGameInput playerGameInput) {
        if (!list.contains(playerGameInput)) {

            list.add(playerGameInput);
            
            //ForcedLogUtil.log(this.toString(), "addListener");
        }
    }

    public void removeAllListeners() {

        //ForcedLogUtil.log(this.toString(), "removeAllListeners");
        
        this.list.clear();
        super.removeAllListeners();
        
        //ForcedLogUtil.log(this.toString(), "removeAllListeners");
    }

    public void removeListenerSingleThreaded(
            EventListenerInterface eventListenerInterface) {
        
        this.list.remove(eventListenerInterface);
        super.removeListenerSingleThreaded(eventListenerInterface);
        
        //ForcedLogUtil.log(this.toString(), "removeListenerSingleThreaded");
    }

    public synchronized void removeListener(EventListenerInterface eventListenerInterface) {

        this.list.remove(eventListenerInterface);
        super.removeListener(eventListenerInterface);
        
        //ForcedLogUtil.log(this.toString(), "removeListener");
    }

    public void fireEvent(AllBinaryEventObject eventObject) throws Exception {
        //ForcedLogUtil.log(this.toString(), EventStrings.getInstance().FIRE_EVENT);

        for (int index = this.list.size(); --index >= 0;) {
            try {
                //Add deviceId
                PlayerGameInput playerGameInput = (PlayerGameInput) this.list.objectArray[index];
                playerGameInput.onDownGameKeyEvent((GameKeyEvent) eventObject);
            } catch (Exception e) {
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e));
            }
        }

        super.fireEvent(eventObject);
    }

    protected void process(AllBinaryEventObject eventObject,
            EventListenerInterface eventListenerInterface) throws Exception {
        ((DownGameKeyEventListenerInterface) eventListenerInterface)
                .onDownGameKeyEvent((GameKeyEvent) eventObject);
    }

    private static final String TOTAL_LISTENERS = " Total PlayerGameInput Listeners: ";
    private static final String LISTENER_LABEL = " PlayerGameInput Listener: ";

    public String toString() {
        final StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(super.toString());
        stringBuffer.append(TOTAL_LISTENERS);
        stringBuffer.append(this.list.size());

        for (int index = 0; index < this.list.size(); index++) {
            try {
                EventListenerInterface eventListenerInterface = (EventListenerInterface) // enumeration.nextElement();
                        this.list.get(index);

                stringBuffer.append(LISTENER_LABEL);
                stringBuffer.append(eventListenerInterface);
            } catch (Exception e) {
                LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "toString", e));
            }
        }
        return stringBuffer.toString();
    }
}
