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
package org.allbinary.graphics.opengles.vbo;

import javax.microedition.khronos.opengles.GL10;

import org.allbinary.graphics.threed.min3d.renderer.processor.ObjectThreedOpenGLProcessor;

/**
 *
 * @author User
 */
public class ColorAttributePointerVBOOpenGLProcessor extends ObjectThreedOpenGLProcessor {
    //protected final LogUtil logUtil = LogUtil.getInstance();

    
    private static final ColorAttributePointerVBOOpenGLProcessor instance = new ColorAttributePointerVBOOpenGLProcessor();

    /**
     * @return the instance
     */
    public static ColorAttributePointerVBOOpenGLProcessor getInstance() {
        return instance;
    }

    public ColorAttributePointerVBOOpenGLProcessor()
    {
        //PreLogUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);
        
    }

    @Override
    public void process(final GL10 gl, final Object object)
    {
        throw new RuntimeException();
    }
    
}
