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
public class ShaderComposite {
    
    protected final ShaderInitializer shaderInitializer;
    public final CompositeShaderUpdater compositeShaderUpdater;
    
    public ShaderComposite(final CompositeShaderUpdater compositeShaderUpdater, final ShaderInitializer shaderInitializer) {
        this.shaderInitializer = shaderInitializer;
        this.compositeShaderUpdater = compositeShaderUpdater;
    }
    
}
