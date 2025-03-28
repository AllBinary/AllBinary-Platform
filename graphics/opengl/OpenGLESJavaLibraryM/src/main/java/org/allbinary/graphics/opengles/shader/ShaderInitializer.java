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
package org.allbinary.graphics.opengles.shader;

import javax.microedition.khronos.opengles.GL10;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
public class ShaderInitializer {

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public int programHandle;
    
    public void init(final GL10 gl, final String[] shaderStringArray, final int[] shaderHandleArray, final String[] attributeArray) {
        
    }

}
