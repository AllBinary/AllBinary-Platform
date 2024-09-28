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
public class ModifierOpenGLESImageProcessor extends OpenGLESImageProcessor {
    
    private static final ModifierOpenGLESImageProcessor instance = new ModifierOpenGLESImageProcessor();

    /**
     * @return the instance
     */
    public static ModifierOpenGLESImageProcessor getInstance() {
        return instance;
    }
    
//    public void rotate(GL10 gl, int angle) {
//        gl.glRotatex(angle, 0, 1, 0);
//    }
    
    public void colorMask(GL10 gl, int red, int green, int blue, int alpha) {
        gl.glColor4f(red, green, blue, alpha);
    }
    
}
