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

import org.allbinary.graphics.opengles.OpenGLProcessor;
import org.allbinary.graphics.opengles.NullOpenGLProcessorFactory;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author User
 */

@JsType
public class ShaderComposite {

    @JsProperty
    public final OpenGLProcessor disableProgramShaderOpenGLProcessor;
    
    @JsProperty
    public final String requiresOpenGLVersion;
    
    @JsProperty
    public final Shader[] shaderArray;
    
    @JsProperty
    public final ShaderInitializer shaderInitializer;
    @JsProperty
    public final CompositeShaderUpdater compositeShaderUpdater;
    
    @JsProperty
    public final ModelViewProjection modelViewProjection;
    @JsProperty
    public final OpenGLProcessor colorOpenGLProcessor;
    @JsProperty
    public final OpenGLProcessor vertexOpenGLProcessor;

    @JsProperty
    public OpenGLProcessor useProgramShaderOpenGLProcessor = null;
    @JsProperty
    public OpenGLProcessor shaderMatrixOpenGLProcessor = NullOpenGLProcessorFactory.getInstance();
    @JsProperty
    public OpenGLProcessor colorEnableVertexAttribArrayOpenGLProcessor = NullOpenGLProcessorFactory.getInstance();
    @JsProperty
    public OpenGLProcessor normalEnableVertexAttribArrayOpenGLProcessor = NullOpenGLProcessorFactory.getInstance();
    @JsProperty
    public OpenGLProcessor vertexEnableVertexAttribArrayOpenGLProcessor = NullOpenGLProcessorFactory.getInstance();
    @JsProperty
    public OpenGLProcessor textureEnableVertexAttribArrayOpenGLProcessor = NullOpenGLProcessorFactory.getInstance();
    @JsProperty
    public OpenGLProcessor colorDisableVertexAttribArrayOpenGLProcessor = NullOpenGLProcessorFactory.getInstance();
    @JsProperty
    public OpenGLProcessor normalDisableVertexAttribArrayOpenGLProcessor = NullOpenGLProcessorFactory.getInstance();
    @JsProperty
    public OpenGLProcessor vertexDisableVertexAttribArrayOpenGLProcessor = NullOpenGLProcessorFactory.getInstance();
    @JsProperty
    public OpenGLProcessor textureDisableVertexAttribArrayOpenGLProcessor = NullOpenGLProcessorFactory.getInstance();
    
    @JsProperty
    public OpenGLObject3dProcessor uniformLightPositionOpenGLProcessor = NullOpenGLTextureProcessor.getInstance();
    @JsProperty
    public OpenGLObject3dProcessor uniformLightColorOpenGLProcessor = NullOpenGLTextureProcessor.getInstance();
    @JsProperty
    public OpenGLObject3dProcessor uniformCameraPositionOpenGLProcessor = NullOpenGLTextureProcessor.getInstance();
    
    @JsProperty
    public OpenGLObject3dProcessor uniformTextureUnitOpenGLProcessor = NullOpenGLTextureProcessor.getInstance();

    @JsProperty
    public int programHandle;    

    @JsConstructor
    public ShaderComposite(final String requiresOpenGLVersion, final Shader[] shaderArray, final CompositeShaderUpdater compositeShaderUpdater, final ShaderInitializer shaderInitializer,
        final ModelViewProjection modelViewProjection, final OpenGLProcessor colorOpenGLProcessor, final OpenGLProcessor vertexOpenGLProcessor, 
        final OpenGLProcessor disableProgramShaderOpenGLProcessor) {

        this.requiresOpenGLVersion = requiresOpenGLVersion;
        this.shaderArray = shaderArray;
        this.shaderInitializer = shaderInitializer;
        this.compositeShaderUpdater = compositeShaderUpdater;
        this.modelViewProjection = modelViewProjection;
        this.colorOpenGLProcessor = colorOpenGLProcessor;
        this.vertexOpenGLProcessor = vertexOpenGLProcessor;

        this.disableProgramShaderOpenGLProcessor = disableProgramShaderOpenGLProcessor;
        
    }
    
    @JsMethod
    public void init(GL10 gl) {
        this.programHandle = this.shaderInitializer.init(gl, this.shaderArray, this.compositeShaderUpdater.attributeArray);
    }

}
