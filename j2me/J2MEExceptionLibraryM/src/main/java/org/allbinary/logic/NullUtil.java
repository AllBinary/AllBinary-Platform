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
package org.allbinary.logic;

/**
 *
 * @author User
 */
public class NullUtil {
    
    protected static final NullUtil instance = new NullUtil();

    /**
     * @return the instance
     */
    public static NullUtil getInstance() {
        return instance;
    }
    
    public final Object NULL_OBJECT = new Object();
    public final Object[] NULL_OBJECT_ARRAY = new Object[0];
    
}
