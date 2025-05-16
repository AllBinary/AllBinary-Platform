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
package org.allbinary.image.opengles;

import javax.microedition.khronos.opengles.GL10;

/**
 *
 * @author User
 */
public class OpenGLESImageTranslate {
    
    protected static final OpenGLESImageTranslate instance = new OpenGLESImageTranslate();

    /**
     * @return the instance
     */
    public static OpenGLESImageTranslate getInstance() {
        return instance;
    }
    
    public void translate(final GL10 gl, final OpenGLESImage openGLESImage, final float x, final float y) {
        
        openGLESImage.imageProcessor.translate(gl, x, y);
        
    }    
    
    public void translate2(final GL10 gl, final OpenGLESImage openGLESImage) {
        
    }    
    
}
