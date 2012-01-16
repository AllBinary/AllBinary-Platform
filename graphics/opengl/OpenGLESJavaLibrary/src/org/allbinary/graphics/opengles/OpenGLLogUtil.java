package org.allbinary.graphics.opengles;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.lcdui.Image;

import org.allbinary.image.opengles.OpenGLStrings;

import abcs.logic.communication.log.ForcedLogUtil;
import abcs.logic.communication.log.PreLogUtil;

public class OpenGLLogUtil
{
    private static final OpenGLLogUtil instance = new OpenGLLogUtil();

    public static OpenGLLogUtil getInstance()
    {
        return instance;
    }
    
    private final String MAX_TEXTURE = " Max Texture Size: ";
    
    public void logError(GL10 gl)
    {
        int error = gl.glGetError();
        if (error != GL10.GL_NO_ERROR)
        {
            StringBuilder stringBuffer = new StringBuilder();
            
            stringBuffer.append(OpenGLStrings.getInstance().GL_ERROR_LABEL);
            stringBuffer.append(error);
            if(error == 1281)
            {
                stringBuffer.append(MAX_TEXTURE);
                
                int[] maxTextureSize = new int[1];
                gl.glGetIntegerv(GL10.GL_MAX_TEXTURE_SIZE, maxTextureSize, 0);
                
                stringBuffer.append(maxTextureSize[0]);
            }

            ForcedLogUtil.log(stringBuffer.toString(), this);
            //PreLogUtil.put(stringBuffer.toString(), this, OpenGLStrings.getInstance().SET);
        }
    }

    private final String LOG_ERROR = "logError";
    private final String IMAGE = " Image: ";
    
    public void logError(GL10 gl, Image image)
    {
        int error = gl.glGetError();
        if (error != GL10.GL_NO_ERROR)
        {
            StringBuilder stringBuffer = new StringBuilder();
            
            stringBuffer.append(OpenGLStrings.getInstance().GL_ERROR_LABEL);
            stringBuffer.append(error);
            if(error == 1281)
            {
                stringBuffer.append(MAX_TEXTURE);
                
                int[] maxTextureSize = new int[1];
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
