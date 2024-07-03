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
package org.microemu.app;

import org.eclipse.swt.widgets.Display;

/**
 *
 * @author User
 */
public class SWTProcessor {
    
    private static final SWTProcessor instance = new SWTProcessor();

    /**
     * @return the instance
     */
    public static SWTProcessor getInstance() {
        return instance;
    }
    
    public Runnable runnable = null;
    
    public void process(final Display display) {
        display.sleep();
    }
    
}
