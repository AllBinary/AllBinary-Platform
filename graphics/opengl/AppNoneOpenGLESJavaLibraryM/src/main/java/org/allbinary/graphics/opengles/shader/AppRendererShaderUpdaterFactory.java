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

import org.allbinary.graphics.threed.min3d.renderer.processor.OpenGLProcessor;

/**
 *
 * @author User
 */
public class AppRendererShaderUpdaterFactory extends ShaderUpdater {
    
    protected static final AppRendererShaderUpdaterFactory instance = new AppRendererShaderUpdaterFactory();

    /**
     * @return the instance
     */
    public static AppRendererShaderUpdaterFactory getInstance() {
        return instance;
    }

    public final ShaderCustomRenderer shaderCustomRenderer = null;
    public final OpenGLProcessor shaderOpenGLProcessor = null;
    public final ModelViewProjection modelViewProjection = null;
    
}
