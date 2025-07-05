/*
 * AllBinary Open License Version 1
 * Copyright (c) 2007 AllBinary
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
package org.allbinary.logic.util.cache;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class AutomaticCacheInterfaceFactory {

    private AutomaticCacheInterfaceFactory() {
    }

    public static AutomaticCacheInterface getInstance(
        CacheableInterfaceFactoryInterface cacheableInterfaceFactoryInterface,
        CacheType cacheType, CachePolicy cachePolicy)
    throws Exception
    {
        throw new Exception("No such " + cacheType.toString());
    }
    
}
