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

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.allbinary.logic.NullUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonSeps;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author User
 */

@JsType
public class CompositeShaderUpdater extends ShaderUpdater {

    @JsProperty
    protected final CommonSeps commonSeps = CommonSeps.getInstance();
    @JsProperty
    protected final ShaderStrings shaderStrings = ShaderStrings.getInstance();
    
    private static final CompositeShaderUpdater instanceC = new CompositeShaderUpdater(StringUtil.getInstance().getArrayInstance(), StringUtil.getInstance().getArrayInstance(), StringUtil.getInstance().getArrayInstance(), NullUtil.getInstance().NULL_INT_ARRAY);
    
    /**
     * @return the instance
     */
    @JsMethod
    public static CompositeShaderUpdater getInstance() {
        return CompositeShaderUpdater.instanceC;
    }
    
    @JsProperty
    public final int[] uniformBlockHandleArray;
    @JsProperty
    public final String[] uniformBlockArray;
    
    @JsProperty
    public final int[] uniformHandleArray;
    @JsProperty
    public final String[] uniformArray;

    @JsProperty
    public final int[] attributeHandleArray;
    @JsProperty
    public final String[] attributeArray;
    
//    public CompositeShaderUpdater(final String[] uniformBlockArray, final String[] uniformArray, final String[] attributeArray) {
//        this(uniformBlockArray, uniformArray, attributeArray, new int[attributeArray.length]);
//    }
    
    @JsConstructor
    public CompositeShaderUpdater(final String[] uniformBlockArray, final String[] uniformArray, final String[] attributeArray, final int[] attributeHandleArray) {

        this.uniformBlockArray = uniformBlockArray;
        this.uniformBlockHandleArray = new int[this.uniformBlockArray.length];
        this.uniformArray = uniformArray;
        this.uniformHandleArray = new int[this.uniformArray.length];
        this.attributeArray = attributeArray;
        this.attributeHandleArray = attributeHandleArray;

    }
    
    @JsMethod
    public void onSurfaceCreated(final GL10 gl, final EGLConfig eglConfig, final int programHandle) {
            
    }
 
    @JsMethod
    public void onSurfaceDestroyed(final GL10 gl) {
        
    }

}
