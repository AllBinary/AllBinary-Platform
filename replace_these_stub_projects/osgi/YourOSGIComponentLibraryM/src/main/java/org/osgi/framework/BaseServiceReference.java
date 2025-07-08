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

/**
 *
 * @author User
 */
public class BaseServiceReference implements ServiceReference {
 
    public Object getProperty(String key) {
        throw new RuntimeException();
    }

    public String[] getPropertyKeys() {
        throw new RuntimeException();
    }
    
    public int compareTo(Object o) {
        throw new RuntimeException();
    }
    
}
