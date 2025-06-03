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

    public final String VERSION_1_0 = "1.0";
    public final String VERSION_1_1 = "1.1";
    public final String VERSION_2_0 = "2.0";
    public final String VERSION_3_0 = "3.0";
    public final String VERSION_3_1 = "3.1";
    public final String VERSION_3_2 = "3.2";
    public final String VERSION_HIGHER_THAN_EXISTS = "999.999";
    
    private String glVersion = StringUtil.getInstance().EMPTY_STRING;
    public int shaderVersion = 0;
    public String glInstanceVersion = StringUtil.getInstance().EMPTY_STRING;
    public int maxTextureSize = 64;
    
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

    public boolean isTextureSizeValid(final int widthAndHeight)
    {
        return true;
    }

}
