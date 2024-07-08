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
package org.allbinary.graphics.opengles;

import org.allbinary.logic.string.StringUtil;

public class OpenGLCapabilities
{
    private static final OpenGLCapabilities instance = new OpenGLCapabilities();
    
    public static OpenGLCapabilities getInstance()
    {
        return instance;
    }

    private String glVersion = StringUtil.getInstance().EMPTY_STRING;
    public String glInstanceVersion = StringUtil.getInstance().EMPTY_STRING;

    private OpenGLCapabilities()
    {
    }

    public void initCapabilities(Object gl)
    {
        
    }
    
    public boolean isGlExtensionDrawTexture()
    {
        return false;
    }

    public String getGlVersion()
    {
        return glVersion;
    }
    
    public boolean isVertexBufferObjectSupport()
    {
        return false;
    }
}
