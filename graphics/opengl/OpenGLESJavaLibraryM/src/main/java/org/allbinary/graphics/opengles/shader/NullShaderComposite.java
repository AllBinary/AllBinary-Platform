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

import org.allbinary.graphics.opengles.OpenGLCapabilities;

/**
 *
 * @author User
 */
public class NullShaderComposite {

    private static final ShaderComposite instance = new ShaderComposite(OpenGLCapabilities.getInstance().VERSION_2_0, 
        new int[2],
        CompositeShaderUpdater.getInstance(),
        ShaderInitializer.getInstance(),
        ModelViewProjection.getInstance(),
        null,
        null);
    
    /**
     * @return the instance
     */
    public static ShaderComposite getInstance() {
        return instance;
    }
    
}
