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

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 *
 * @author User
 */
public class CompositeShaderUpdater extends ShaderUpdater {
 
    public final int[] uniformHandleArray;
    public final String[] uniformArray;

    public final int[] attributeHandleArray;
    public final String[] attributeArray;
    
    public CompositeShaderUpdater(final String[] uniformArray, final String[] attributeArray) {

        this.uniformArray = uniformArray;
        this.uniformHandleArray = new int[this.uniformArray.length];
        this.attributeArray = attributeArray;
        this.attributeHandleArray = new int[this.attributeArray.length];

    }
    
    public void onSurfaceCreated(final GL10 gl, final EGLConfig eglConfig, final int programHandle) {
            
    }
 
    public void onSurfaceDestroyed(final GL10 gl) {
        
    }

}
