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

import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonSeps;

/**
 *
 * @author User
 */
public class CompositeShaderUpdater extends ShaderUpdater {

    protected final CommonSeps commonSeps = CommonSeps.getInstance();
    protected final ShaderStrings shaderStrings = ShaderStrings.getInstance();
    
    private static final CompositeShaderUpdater instance = new CompositeShaderUpdater(StringUtil.getInstance().getArrayInstance(), StringUtil.getInstance().getArrayInstance(), StringUtil.getInstance().getArrayInstance());
    
    /**
     * @return the instance
     */
    public static CompositeShaderUpdater getInstance() {
        return instance;
    }
    
    public final int[] uniformBlockHandleArray;
    public final String[] uniformBlockArray;
    
    public final int[] uniformHandleArray;
    public final String[] uniformArray;

    public final int[] attributeHandleArray;
    public final String[] attributeArray;
    
    public CompositeShaderUpdater(final String[] uniformBlockArray, final String[] uniformArray, final String[] attributeArray) {
        this(uniformBlockArray, uniformArray, attributeArray, new int[attributeArray.length]);
    }
    
    public CompositeShaderUpdater(final String[] uniformBlockArray, final String[] uniformArray, final String[] attributeArray, final int[] attributeHandleArray) {

        this.uniformBlockArray = uniformBlockArray;
        this.uniformBlockHandleArray = new int[this.uniformBlockArray.length];
        this.uniformArray = uniformArray;
        this.uniformHandleArray = new int[this.uniformArray.length];
        this.attributeArray = attributeArray;
        this.attributeHandleArray = attributeHandleArray;

    }
    
    public void onSurfaceCreated(final GL10 gl, final EGLConfig eglConfig, final int programHandle) {
            
    }
 
    public void onSurfaceDestroyed(final GL10 gl) {
        
    }

}
