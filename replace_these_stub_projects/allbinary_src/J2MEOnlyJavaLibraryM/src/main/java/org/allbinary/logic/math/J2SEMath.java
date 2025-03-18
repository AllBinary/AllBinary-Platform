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
package org.allbinary.logic.math;

/**
 *
 * @author User
 */
public class J2SEMath {
    
    protected static final J2SEMath instance = new J2SEMath();
    
    /**
     * @return the instance
     */
    public static J2SEMath getInstance() {
        return instance;
    }
    
    public int round(float value) {
        return (int) value;
    }
    
    public float abs(float value) {
        return (value < 0) ? -value : value;
    }
    
}
