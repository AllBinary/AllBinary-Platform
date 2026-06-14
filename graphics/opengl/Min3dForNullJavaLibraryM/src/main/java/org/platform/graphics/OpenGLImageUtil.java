package org.platform.graphics;

import org.allbinary.image.opengles.OpenGLImageUtilBase;

/**
 *
 * @author User
 */
public class OpenGLImageUtil extends OpenGLImageUtilBase {
    
    private static final OpenGLImageUtil instance = new OpenGLImageUtil();
    
    /**
     * @return the instance
     */
    public static OpenGLImageUtil getInstance() {
        return OpenGLImageUtil.instance;
    }

}
