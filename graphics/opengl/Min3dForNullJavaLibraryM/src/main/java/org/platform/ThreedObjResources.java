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
package org.platform;

import java.util.Hashtable;

/**
 *
 * @author User
 */
public class ThreedObjResources {
    
    private static final ThreedObjResources instance = new ThreedObjResources();
    
    /**
     * @return the instance
     */
    public static ThreedObjResources getInstance() {
        return instance;
    }
    
    public final Hashtable hashtable = new Hashtable();

    public String get(final String resourceName) {
        final String resourceName2 = (String) this.hashtable.get(resourceName);
        if(resourceName2 != null) {
            return resourceName2;
        }
        return resourceName;
    }

}
