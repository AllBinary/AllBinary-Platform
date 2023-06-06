/*
 *Copyright (c) 2007 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: Sep 9, 2007, 10:48:03 PM
 *
 *
 *Modified By         When       ?
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
