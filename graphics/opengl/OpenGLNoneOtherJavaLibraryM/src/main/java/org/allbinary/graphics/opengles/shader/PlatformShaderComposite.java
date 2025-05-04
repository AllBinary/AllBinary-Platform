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
public class PlatformShaderComposite extends ShaderComposite {

    public PlatformShaderComposite(final String requiresOpenGLVersion, final Shader[] shaderArray, final CompositeShaderUpdater compositeShaderUpdater, final ShaderInitializer shaderInitializer,
        final ModelViewProjection modelViewProjection, final OpenGLProcessor colorOpenGLProcessor, final OpenGLProcessor vertexOpenGLProcessor) {

        super(requiresOpenGLVersion, shaderArray, compositeShaderUpdater, shaderInitializer, modelViewProjection, colorOpenGLProcessor, vertexOpenGLProcessor,
            null
            //ShaderMatrixOpenGLProcessor.getInstance(),
        );
        
    }

}
