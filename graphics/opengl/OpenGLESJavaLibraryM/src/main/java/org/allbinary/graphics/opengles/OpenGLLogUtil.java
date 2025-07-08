package org.allbinary.graphics.opengles;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.lcdui.Image;

import org.allbinary.image.opengles.OpenGLStrings;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;

public class OpenGLLogUtil
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final OpenGLLogUtil instance = new OpenGLLogUtil();

    public static OpenGLLogUtil getInstance()
    {
        return instance;
    }
    
    private final String MAX_TEXTURE = " Max Texture Size: ";
    
    public void logError(final GL10 gl)
    {
        final int error = gl.glGetError();
        if (error != GL10.GL_NO_ERROR)
        {
            final StringMaker stringBuffer = new StringMaker();
            
            stringBuffer.append(OpenGLStrings.getInstance().GL_ERROR_LABEL);
            stringBuffer.append(error);
            if(error == 1281)
            {
                stringBuffer.append(MAX_TEXTURE);
                
                final int[] maxTextureSize = new int[1];
                gl.glGetIntegerv(GL10.GL_MAX_TEXTURE_SIZE, maxTextureSize, 0);
                
                stringBuffer.append(maxTextureSize[0]);
            }

            if(error == 1280) {
                PreLogUtil.put(stringBuffer.toString(), this, OpenGLStrings.getInstance().SET);  
            } else {
                ForcedLogUtil.log(stringBuffer.toString(), this);
            }

        }
    }

    private final String LOG_ERROR = "logError";
    private final String IMAGE = " Image: ";
    
    public void logError(final GL10 gl, final Image image)
    {
        final int error = gl.glGetError();
        if (error != GL10.GL_NO_ERROR)
        {
            final StringMaker stringBuffer = new StringMaker();
            
            stringBuffer.append(OpenGLStrings.getInstance().GL_ERROR_LABEL);
            stringBuffer.append(error);
            if(error == 1281)
            {
                stringBuffer.append(MAX_TEXTURE);
                
                final int[] maxTextureSize = new int[1];
                gl.glGetIntegerv(GL10.GL_MAX_TEXTURE_SIZE, maxTextureSize, 0);
                
                stringBuffer.append(maxTextureSize[0]);
            }

            stringBuffer.append(IMAGE);
            stringBuffer.append(image.toString());
            
            //ForcedLogUtil.log(stringBuffer.toString(), this);
            PreLogUtil.put(stringBuffer.toString(), this, LOG_ERROR);
        }
    }
}
