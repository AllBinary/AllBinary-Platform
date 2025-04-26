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

import javax.microedition.khronos.opengles.GL10;

import org.allbinary.graphics.opengles.OpenGLProcessor;
import org.allbinary.graphics.opengles.NullOpenGLProcessorFactory;

/**
 *
 * @author User
 */
public class ShaderComposite {

    public final OpenGLProcessor disableProgramShaderOpenGLProcessor;
    
    public final String requiresOpenGLVersion;
    
    public final String[] shaderStringArray = new String[2];
    
    public final int[] shaderHandleArray;
    
    public final ShaderInitializer shaderInitializer;
    public final CompositeShaderUpdater compositeShaderUpdater;
    
    public final ModelViewProjection modelViewProjection;
    public final OpenGLProcessor colorOpenGLProcessor;
    public final OpenGLProcessor vertexOpenGLProcessor;

    public OpenGLProcessor useProgramShaderOpenGLProcessor = null;
    public OpenGLProcessor shaderMatrixOpenGLProcessor = NullOpenGLProcessorFactory.getInstance();
    public OpenGLProcessor colorEnableVertexAttribArrayOpenGLProcessor = NullOpenGLProcessorFactory.getInstance();
    public OpenGLProcessor vertexEnableVertexAttribArrayOpenGLProcessor = NullOpenGLProcessorFactory.getInstance();
    public OpenGLProcessor textureEnableVertexAttribArrayOpenGLProcessor = NullOpenGLProcessorFactory.getInstance();
    public OpenGLProcessor colorDisableVertexAttribArrayOpenGLProcessor = NullOpenGLProcessorFactory.getInstance();
    public OpenGLProcessor vertexDisableVertexAttribArrayOpenGLProcessor = NullOpenGLProcessorFactory.getInstance();
    public OpenGLProcessor textureDisableVertexAttribArrayOpenGLProcessor = NullOpenGLProcessorFactory.getInstance();
    
    public OpenGLTextureProcessor uniformTextureUnitOpenGLProcessor = NullOpenGLTextureProcessor.getInstance();

    public int programHandle;    

    public ShaderComposite(final String requiresOpenGLVersion, final int[] shaderHandleArray, final CompositeShaderUpdater compositeShaderUpdater, final ShaderInitializer shaderInitializer,
        final ModelViewProjection modelViewProjection, final OpenGLProcessor colorOpenGLProcessor, final OpenGLProcessor vertexOpenGLProcessor, 
        final OpenGLProcessor disableProgramShaderOpenGLProcessor) {

        this.requiresOpenGLVersion = requiresOpenGLVersion;
        this.shaderHandleArray = shaderHandleArray;
        this.shaderInitializer = shaderInitializer;
        this.compositeShaderUpdater = compositeShaderUpdater;
        this.modelViewProjection = modelViewProjection;
        this.colorOpenGLProcessor = colorOpenGLProcessor;
        this.vertexOpenGLProcessor = vertexOpenGLProcessor;

        this.disableProgramShaderOpenGLProcessor = disableProgramShaderOpenGLProcessor;
        
    }
    
    public void init(GL10 gl) {
        this.programHandle = this.shaderInitializer.init(gl, this.shaderStringArray, this.shaderHandleArray, null);
    }

}
