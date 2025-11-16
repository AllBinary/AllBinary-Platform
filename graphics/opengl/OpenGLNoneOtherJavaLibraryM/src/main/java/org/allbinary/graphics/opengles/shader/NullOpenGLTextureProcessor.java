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

/**
 *
 * @author User
 */
public class NullOpenGLTextureProcessor {
    
    private static final OpenGLObject3dProcessor instance = new OpenGLObject3dProcessor();

    /**
     * @return the instance
     */
    public static OpenGLObject3dProcessor getInstance() {
        return instance;
    }
    
}
