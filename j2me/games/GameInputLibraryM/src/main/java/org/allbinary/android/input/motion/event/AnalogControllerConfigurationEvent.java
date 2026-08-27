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
package org.allbinary.android.input.motion.event;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsConstructor;

import org.allbinary.logic.util.event.AllBinaryEventObject;

@JsType
public class AnalogControllerConfigurationEvent extends AllBinaryEventObject {

    @JsConstructor
    public AnalogControllerConfigurationEvent(Object object) {
        super(object);
    }

}
