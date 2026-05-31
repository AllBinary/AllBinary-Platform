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
package org.allbinary;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author User
 */
//ArkTs does not have this.  So this is for the ArkTs build.
public class J2SEUtil {
    
    private static final J2SEUtil instance = new J2SEUtil();

    /**
     * @return the instance
     */
    public static J2SEUtil getInstance() {
        return J2SEUtil.instance;
    }
    
    public Object[] getHashMapAsArray(final HashMap<Object, Object> hashMap)
    {
        final Object[] objectArray = hashMap.keySet().toArray();
        return objectArray;
    }
    
    public Object[] getMapAsArray(final Map<Object, Object> map)
    {
        final Object[] objectArray = map.keySet().toArray();
        return objectArray;
    }

    public Object[] getSetAsArray(final Set<String> set)
    {
        final Object[] objectArray = set.toArray();
        return objectArray;
    }
        
}
