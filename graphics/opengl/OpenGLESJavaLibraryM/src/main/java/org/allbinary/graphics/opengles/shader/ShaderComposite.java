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

import org.allbinary.graphics.opengles.OpenGLProcessor;

/**
 *
 * @author User
 */
public class ShaderComposite {

    public final String requiresOpenGLVersion;
    
    public final String[] shaderStringArray = new String[2];
    
    public final int[] shaderHandleArray;
    
    public final ShaderInitializer shaderInitializer;
    public final CompositeShaderUpdater compositeShaderUpdater;
    
    public final ModelViewProjection modelViewProjection;
    public final OpenGLProcessor colorOpenGLProcessor;
    public final OpenGLProcessor vertexOpenGLProcessor;

    public int programHandle;    
    
    public ShaderComposite(final String requiresOpenGLVersion, final int[] shaderHandleArray, final CompositeShaderUpdater compositeShaderUpdater, final ShaderInitializer shaderInitializer,
        final ModelViewProjection modelViewProjection, final OpenGLProcessor colorOpenGLProcessor, final OpenGLProcessor vertexOpenGLProcessor) {

        this.requiresOpenGLVersion = requiresOpenGLVersion;
        this.shaderHandleArray = shaderHandleArray;
        this.shaderInitializer = shaderInitializer;
        this.compositeShaderUpdater = compositeShaderUpdater;
        this.modelViewProjection = modelViewProjection;
        this.colorOpenGLProcessor = colorOpenGLProcessor;
        this.vertexOpenGLProcessor = vertexOpenGLProcessor;

    }
    
}
