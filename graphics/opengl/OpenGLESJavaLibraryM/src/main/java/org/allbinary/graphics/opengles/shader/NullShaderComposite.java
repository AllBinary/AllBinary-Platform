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

import org.allbinary.graphics.opengles.NullOpenGLProcessorFactory;
import org.allbinary.graphics.opengles.OpenGLCapabilities;
import org.allbinary.graphics.opengles.OpenGLProcessor;

/**
 *
 * @author User
 */
public class NullShaderComposite extends ShaderComposite {

    private static final NullShaderComposite instance = new NullShaderComposite(OpenGLCapabilities.getInstance().VERSION_2_0, 
        new int[2],
        CompositeShaderUpdater.getInstance(),
        ShaderInitializer.getInstance(),
        ModelViewProjection.getInstance(),        
        null,
        null,
        NullOpenGLProcessorFactory.getInstance()) {
            public void init(GL10 gl) {
                
            }
        };

    /**
     * @return the instance
     */
    public static ShaderComposite getInstance() {
        return instance;
    }
    
    public NullShaderComposite(final String requiresOpenGLVersion, final int[] shaderHandleArray, final CompositeShaderUpdater compositeShaderUpdater, final ShaderInitializer shaderInitializer,
        final ModelViewProjection modelViewProjection, final OpenGLProcessor colorOpenGLProcessor, final OpenGLProcessor vertexOpenGLProcessor, 
        final OpenGLProcessor disableProgramShaderOpenGLProcessor) {
        super(requiresOpenGLVersion, shaderHandleArray, compositeShaderUpdater, shaderInitializer, modelViewProjection, colorOpenGLProcessor, vertexOpenGLProcessor, disableProgramShaderOpenGLProcessor);
        
        this.useProgramShaderOpenGLProcessor = NullOpenGLProcessorFactory.getInstance();

    }
    
}
