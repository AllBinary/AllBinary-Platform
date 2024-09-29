/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
public class OpenGLESImageProcessor {

    private static final OpenGLESImageProcessor instance = new OpenGLESImageProcessor();

    /**
     * @return the instance
     */
    public static OpenGLESImageProcessor getInstance() {
        return instance;
    }
    
    public void translate(GL10 gl, float translateX, float translateY) {
        gl.glTranslatef(translateX, translateY, 0);
    }
    
    public void rotate(GL10 gl, int angle) {

    }
    
    public void scale(GL10 gl, float scale) {
        gl.glScalef(scale, scale, 1);
    }
    
    public void colorMask(GL10 gl, int red, int green, int blue, int alpha) {

    }
    
}
