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
package org.allbinary.logic.io.path;

/**
 *
 * @author User
 */
public class AbPathUtil {

    private static final AbPathUtil instance = new AbPathUtil();

    /**
     * @return the instance
     */
    public static AbPathUtil getInstance() {
        return AbPathUtil.instance;
    }
    
    public final AbPath NO_ABPATH = new AbPath();
}
