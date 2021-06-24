/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
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
package org.allbinary.opengles;

import org.allbinary.graphics.opengles.OpenGLCapabilities;

/**
 *
 * @author User
 */
public class GLUtil
{

    public static org.allbinary.opengles.GL10 get(final javax.microedition.khronos.opengles.GL10 gl, final String glInstanceVersion)
    {
        final OpenGLCapabilities openGLCapabilities = OpenGLCapabilities.getInstance();
        
        if (glInstanceVersion == openGLCapabilities.VERSION_1_1)
        {
            return new org.allbinary.opengles.GL11((javax.microedition.khronos.opengles.GL11) gl);
        } else if (glInstanceVersion == openGLCapabilities.VERSION_1_0)
        {
            return new org.allbinary.opengles.GL10(gl);
        } else
        {
            return new org.allbinary.opengles.GL11((javax.microedition.khronos.opengles.GL11) gl);
        }
    }
}
