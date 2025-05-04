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

/**
 *
 * @author User
 */
public class UniformShaderOpenGLProcessor extends OpenGLTextureProcessor {

    private final ShaderComposite shaderComposite;
    
    public UniformShaderOpenGLProcessor(final ShaderComposite shaderComposite) {
        this.shaderComposite = shaderComposite;
    }

    public void process(final GL10 gl, final Object unused, final int index)
    {
        //GL11 gl11 = (GL11) gl;

    }

}
