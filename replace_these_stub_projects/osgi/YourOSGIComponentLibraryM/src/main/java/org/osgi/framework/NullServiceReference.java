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
package org.osgi.framework;

import org.allbinary.logic.NullUtil;
import org.allbinary.logic.string.StringUtil;

/**
 *
 * @author User
 */
public class NullServiceReference {
    
    public Object getProperty(String key) {
        return NullUtil.getInstance().NULL_OBJECT;
    }

    public String[] getPropertyKeys() {
        return StringUtil.getInstance().getArrayInstance();
    }
    
    public int compareTo(Object o) {
        throw new RuntimeException();
    }
    
    
}
