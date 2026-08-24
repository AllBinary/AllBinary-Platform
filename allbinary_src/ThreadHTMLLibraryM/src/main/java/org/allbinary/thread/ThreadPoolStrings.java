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
package org.allbinary.thread;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author User
 */

@JsType
public class ThreadPoolStrings {

    private static final ThreadPoolStrings instance = new ThreadPoolStrings();
    
    /**
     * @return the instance
     */
    @JsMethod
    public static ThreadPoolStrings getInstance() {
        return ThreadPoolStrings.instance;
    }
    
    @JsProperty
    public final String ADD_TASK = "addTask";
    @JsProperty
    public final String START_TASK = "Started Task: ";
    @JsProperty
    public final String COMPLETE_TASK = "Completed Task: ";

}
