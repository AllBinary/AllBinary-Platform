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
public class OpenGLESDeviceImageTranslate extends OpenGLESImageTranslate {
        
    public float translateX = 0.0f;
    public float translateY = 0.0f;
    public float translateX2 = 0.0f;
    public float translateY2 = 0.0f;
    
    @Override
    public void translate(final GL10 gl, final OpenGLESImage openGLESImage, final float x, final float y) {
        
        openGLESImage.imageProcessor.translate(gl, translateX + (openGLESImage.openGLESImageProperties.scaleX * x), translateY + (openGLESImage.openGLESImageProperties.scaleY * y));
        
    }

    @Override
    public void translate2(final GL10 gl, final OpenGLESImage openGLESImage) {
        
        openGLESImage.imageProcessor.translate(gl, translateX2, translateY2);
        
    }    
    
}
