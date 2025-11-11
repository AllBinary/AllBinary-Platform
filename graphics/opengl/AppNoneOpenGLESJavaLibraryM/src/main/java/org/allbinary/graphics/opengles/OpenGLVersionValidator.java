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
package org.allbinary.graphics.opengles;

import org.allbinary.graphics.opengles.shader.ShaderComposite;

/**
 *
 * @author User
 */
public class OpenGLVersionValidator {

    private static final OpenGLVersionValidator instance = new OpenGLVersionValidator();

    /**
     * @return the instance
     */
    public static OpenGLVersionValidator getInstance() {
        return instance;
    }

    public boolean isGL31OrHigher() {
        return false;
    }

    public boolean isAvailable(final String glVersionRequired) {
        return false;
    }
    
    public void setShaderComposite(final ShaderComposite shaderComposite, final Object object) {
    }
    
    public int EGL_CONTEXT_CLIENT_VERSION_VALUE = -1;
}
