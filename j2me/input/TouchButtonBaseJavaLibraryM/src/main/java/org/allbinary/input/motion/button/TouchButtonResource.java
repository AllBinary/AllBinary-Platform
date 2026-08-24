/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allbinary.input.motion.button;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author user
 */

@JsType
public class TouchButtonResource {
    
    @JsProperty
    public final String RESOURCE;
    @JsProperty
    public final String HINT;
    
    @JsConstructor
    protected TouchButtonResource(String resource, String hint)
    {
        this.RESOURCE = resource;
        this.HINT = hint;
    }
}
