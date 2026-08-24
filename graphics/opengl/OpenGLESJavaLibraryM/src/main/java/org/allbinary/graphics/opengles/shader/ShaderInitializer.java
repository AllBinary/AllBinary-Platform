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
package org.allbinary.graphics.opengles.shader;

import jsinterop.annotations.JsType;

import javax.microedition.khronos.opengles.GL10;
import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author User
 */

@JsType
public class ShaderInitializer {

    private static final ShaderInitializer instance = new ShaderInitializer();
    
    /**
     * @return the instance
     */
    @JsMethod
    public static ShaderInitializer getInstance() {
        return ShaderInitializer.instance;
    }
    
    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    @JsMethod
    public int init(final GL10 gl, final Shader[] shader, final String[] attributeArray) {
        return -1;
    }

}
