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

import org.allbinary.string.CommonStrings;
import org.eclipse.swt.widgets.Display;

/**
 *
 * @author User
 */
public class SWTRunnableProcessor extends SWTProcessor {
    
    private static final SWTRunnableProcessor instance = new SWTRunnableProcessor();

    /**
     * @return the instance
     */
    public static SWTRunnableProcessor getInstance() {
        return instance;
    }
    
    private SWTRunnableProcessor() {
        //System.out.println("SWTRunnableProcessor" + this.hashCode());
    }
    
    public void process(final Display display) {
        //System.out.println("SWTRunnableProcessor:process - Processing on SWT Thread" + this.hashCode());
        this.runnable.run();
        //super.process(display);
    }

}
