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

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.allbinary.graphics.opengles.renderer.RendererStrings;
import org.allbinary.string.CommonStrings;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author User
 */

@JsType
public class ShaderUpdater {
    
    private static final ShaderUpdater instance = new ShaderUpdater();
    
    /**
     * @return the instance
     */
    @JsMethod
    public static ShaderUpdater getInstance() {
        return ShaderUpdater.instance;
    }
    
    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    @JsProperty
    protected final RendererStrings rendererStrings = RendererStrings.getInstance();
    
    @JsMethod
    public void onSurfaceCreated(final GL10 gl, final EGLConfig eglConfig) {
        
    }
        
}
