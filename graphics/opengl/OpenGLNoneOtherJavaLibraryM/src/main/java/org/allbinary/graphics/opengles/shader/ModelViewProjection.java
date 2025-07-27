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

import org.allbinary.logic.NullUtil;

/**
 *
 * @author User
 */
public class ModelViewProjection {
    
    private static final ModelViewProjection instance = new ModelViewProjection();

    /**
     * @return the instance
     */
    public static ModelViewProjection getInstance() {
        return instance;
    }
    
    public float[] get() {
        return NullUtil.getInstance().NULL_FLOAT_ARRAY;
    }

}
