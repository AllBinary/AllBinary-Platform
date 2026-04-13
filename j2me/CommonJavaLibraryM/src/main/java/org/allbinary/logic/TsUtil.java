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
public class TsUtil {
    
    private static final TsUtil instance = new TsUtil();

    /**
     * @return the instance
     */
    public static TsUtil getInstance() {
        return instance;
    }

    public int hashCode(Object object) {
        return object.hashCode();
    }
}
