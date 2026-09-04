/*
 * AllBinary Open License Version 1
 * Copyright (c) 2026 AllBinary
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
package org.allbinary.logic;

import java.util.Vector;

/**
 *
 * @author User
 */
public class VectorUtil {
    
    private static final VectorUtil instance = new VectorUtil();

    /**
     * @return the instance
     */
    public static VectorUtil getInstance() {
        return instance;
    }
    
    public int getSize(final Object vector) {
        return ((Vector) vector).size();
    }

    public Object elementAt(final Object vector, final int index) {
        return ((Vector) vector).elementAt(index);
    }
}
