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

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.allbinary.graphics.opengles.renderer.RendererStrings;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
public class ShaderUpdater {
    
    private static final ShaderUpdater instance = new ShaderUpdater();
    
    /**
     * @return the instance
     */
    public static ShaderUpdater getInstance() {
        return instance;
    }
    
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final RendererStrings rendererStrings = RendererStrings.getInstance();
    
    public void onSurfaceCreated(final GL10 gl, final EGLConfig eglConfig) {
        
    }
        
}
