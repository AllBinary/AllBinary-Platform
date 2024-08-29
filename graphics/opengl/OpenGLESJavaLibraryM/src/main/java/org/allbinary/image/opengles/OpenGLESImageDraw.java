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
public class OpenGLESImageDraw {
    
    private static final OpenGLESImageDraw instance = new OpenGLESImageDraw();

    /**
     * @return the instance
     */
    public static OpenGLESImageDraw getInstance() {
        return instance;
    }
    
    public void drawRegion(final GL10 gl, final int viewHeight, 
            final float x_src, final float y_src, 
            final float width, final float height, 
            final int x, final int y, final int z)
    {
    }
    
    public void draw(final GL10 gl, final int x, final int y, final int z) {
        
    }

}
