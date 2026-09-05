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

import jsinterop.annotations.JsType;

/**
 *
 * @author User
 */
@JsType
public class ABSystemWrapper {

    private static final ABSystemWrapper instance = new ABSystemWrapper();

    /**
     * @return the instance
     */
    public static ABSystemWrapper getInstance() {
        return instance;
    }
    
    public void arraycopy(final Object src, final int srcPos, final Object dest, final int destPos, final int length) {
        System.arraycopy(src, srcPos, dest, destPos, length);
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
    
    public void gc() {
        System.gc();
    }

}
