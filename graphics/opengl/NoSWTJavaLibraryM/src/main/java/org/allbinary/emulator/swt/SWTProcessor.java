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
package org.allbinary.emulator.swt;

import jsinterop.annotations.JsType;

import org.eclipse.swt.widgets.Display;

import org.allbinary.thread.NullRunnable;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author User
 */

@JsType
public class SWTProcessor {
    
    private static final SWTProcessor instance = new SWTProcessor();

    /**
     * @return the instance
     */
    @JsMethod
    public static SWTProcessor getInstance() {
        return SWTProcessor.instance;
    }
    
    @JsProperty
    public Runnable runnable = NullRunnable.getInstance();
    
    @JsMethod
    public void process(final Display display) {
        display.sleep();
    }
    
}
