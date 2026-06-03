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
public class AppRendererShaderUpdaterFactory extends ShaderUpdater {
    
    private static final AppRendererShaderUpdaterFactory instanceC = new AppRendererShaderUpdaterFactory();

    /**
     * @return the instance
     */
    public static AppRendererShaderUpdaterFactory getInstance() {
        return AppRendererShaderUpdaterFactory.instanceC;
    }

    private final ShaderComposite[] NULL_SHADER_COMPOSITE_ARRAY = new ShaderComposite[0];

    public final ShaderComposite[] shaderCompositeArray = this.NULL_SHADER_COMPOSITE_ARRAY;

    private final ShaderOpenGLProcessor[] shaderOpenGLProcessorArray = new ShaderOpenGLProcessor[0];
    
    public ShaderOpenGLProcessor[] getTestShaders() {
        return this.shaderOpenGLProcessorArray;
    }
   
    public void setShaderComposite(final Object object3d) {
    }
    
}
