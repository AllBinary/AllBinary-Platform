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
package org.allbinary.util;

import java.util.Enumeration;

/**
 *
 * @author User
 */
//ArkTs does not have this.  So this is for the ArkTs build.
public class EnumerationUtil {
    
    private static final EnumerationUtil instance = new EnumerationUtil();

    /**
     * @return the instance
     */
    public static EnumerationUtil getInstance() {
        return EnumerationUtil.instance;
    }
    
    public boolean hasMoreElements(final Enumeration enumeration) {
        return enumeration.hasMoreElements();
    }

    public Object nextElement(final Enumeration enumeration) {
        return enumeration.nextElement();
    }
    
    public Object[] getAsArray(final Enumeration enumeration)
    {
        final BasicArrayList basicArrayList = new BasicArrayListD();

        while (this.hasMoreElements(enumeration))
        {
            basicArrayList.add(this.nextElement(enumeration));
        }

        return basicArrayList.toArray();
    }
    
}
