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
package org.allbinary.time;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author User
 */

@JsType
public class GameTickTimeDelayHelper {
    
    @JsProperty
    public long timeDelta;
    @JsProperty
    public long startTime;
    @JsProperty
    public long lastStartTime;

//    public GameTickTimeDelayHelper() {
//        this.startTime = System.currentTimeMillis();
//        this.lastStartTime = this.startTime;
//    }

    @JsConstructor
    public GameTickTimeDelayHelper(final long startTime) {
        this.startTime = startTime;
        this.lastStartTime = 0;
    }
    
    @JsMethod
    public long setStartTime()
    {
        this.startTime = System.currentTimeMillis();
        return this.startTime;
    }

    @JsMethod
    public void loop() {
        if (this.lastStartTime == Long.MIN_VALUE) {
            this.timeDelta = 0;
        } else {
            this.timeDelta = System.currentTimeMillis() - this.lastStartTime;
        }
    }
    @JsMethod
    public long getTimeFromStart() {
        return this.startTime - this.lastStartTime;
    }
}
