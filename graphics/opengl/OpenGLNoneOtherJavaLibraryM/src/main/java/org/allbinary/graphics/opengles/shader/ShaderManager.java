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
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class ShaderManager {
 
    private static final ShaderManager instance = new ShaderManager(-1, -1);

    /**
     * @return the instance
     */
    public static ShaderManager getInstance() {
        return instance;
    }
    
    protected final String LOAD_SHADER = "loadShader";
    
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public final int GL_VERTEX_SHADER;
    public final int GL_FRAGMENT_SHADER;
    
    public ShaderManager(final int GL_VERTEX_SHADER, final int GL_FRAGMENT_SHADER) {
        this.GL_VERTEX_SHADER = GL_VERTEX_SHADER;
        this.GL_FRAGMENT_SHADER = GL_FRAGMENT_SHADER;
    }
    
    public int load(final GL10 gl, final String resource, final String[] shaderAsStringArray, final int shaderType) {
        return -1;
    }
    
    public int loadShader(final GL10 gl, final String resource, final BasicArrayList stringList, final int shaderType) {
        return -1;
    }

}
