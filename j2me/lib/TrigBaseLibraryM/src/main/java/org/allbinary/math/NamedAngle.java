/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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
package org.allbinary.math;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsConstructor;

/**
 *
 * @author User
 */
@JsType
public class NamedAngle extends Angle {
    
    @JsProperty
    public final String name;
   
    //protected
    @JsConstructor
    public NamedAngle(final short angle, final String name)
    {
        super(angle);
        
        this.name = name;

    }
    
}
