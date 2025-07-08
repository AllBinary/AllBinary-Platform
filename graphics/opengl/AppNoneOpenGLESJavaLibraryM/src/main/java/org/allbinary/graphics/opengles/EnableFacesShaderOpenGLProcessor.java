package org.allbinary.graphics.opengles;

import javax.microedition.khronos.opengles.GL10;
import org.allbinary.graphics.threed.min3d.renderer.processor.ObjectThreedOpenGLProcessor;

public class EnableFacesShaderOpenGLProcessor extends ObjectThreedOpenGLProcessor
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final EnableFacesShaderOpenGLProcessor instance = new EnableFacesShaderOpenGLProcessor();

    /**
     * @return the instance
     */
    public static EnableFacesShaderOpenGLProcessor getInstance() {
        return instance;
    }
    
    public EnableFacesShaderOpenGLProcessor()
    {
        //PreLogUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);

    }

    public void process(final GL10 gl, final Object object)
    {

    }
}
