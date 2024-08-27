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
package org.allbinary.graphics.displayable.event;

import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.handler.BasicEventHandler;

/**
 *
 * @author User
 */
public class LastDisplayChangeEventHandler extends BasicEventHandler {
    
    private static final LastDisplayChangeEventHandler SINGLETON = new LastDisplayChangeEventHandler();

    public static LastDisplayChangeEventHandler getInstance() {
        return SINGLETON;
    }
    
    private LastDisplayChangeEventHandler() {
    }
    
    
    protected void process(final AllBinaryEventObject eventObject,
        final EventListenerInterface eventListenerInterface) throws Exception {

        ((DisplayChangeEventListener) eventListenerInterface).onDisplayChangeEvent((DisplayChangeEvent) eventObject);
    }    
    
}
