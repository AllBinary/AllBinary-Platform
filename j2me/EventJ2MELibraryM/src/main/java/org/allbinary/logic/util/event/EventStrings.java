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
package org.allbinary.logic.util.event;

/**
 *
 * @author User
 */
public class EventStrings {
    
    protected static final EventStrings instance = new EventStrings();

    /**
     * @return the instance
     */
    public static EventStrings getInstance() {
        return instance;
    }

    public final String PERFORMANCE_MESSAGE = "Use Custom onEvent Methods for needed optimization";
    public final String TOTAL_LISTENERS = "Total Listeners: ";
    public final String LISTENER_LABEL = " Listener: ";
    
    public final String FIRE_EVENT = EventStrings.getInstance().FIRE_EVENT;

}
