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
package org.allbinary.graphics.displayable.event;

import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.handler.BasicEventHandler;

public class DisplayChangeEventHandler extends BasicEventHandler
{
    private static final DisplayChangeEventHandler SINGLETON = new DisplayChangeEventHandler();

    public static DisplayChangeEventHandler getInstance() {
        return SINGLETON;
    }
    
    private DisplayChangeEventHandler() {
    }
    
    @Override
    protected void process(final AllBinaryEventObject eventObject,
        final EventListenerInterface eventListenerInterface) throws Exception {

        final DisplayChangeEventListener displayChangeEventListener = ((DisplayChangeEventListener) eventListenerInterface);
        displayChangeEventListener.onDisplayChangeEvent((DisplayChangeEvent) eventObject);
    }

}
