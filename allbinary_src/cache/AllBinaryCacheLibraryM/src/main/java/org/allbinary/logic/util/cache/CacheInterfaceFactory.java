/*
 *Copyright (c) 2007 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *@author Travis Berthelot
 *Date: April 20, 2007, 9:14 PM
 *
 *Modified By         When       ?
 *
 */

package org.allbinary.logic.util.cache;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class CacheInterfaceFactory
{
    
    private CacheInterfaceFactory()
    {
    }
    
    public static CacheInterface getInstance(
        CacheType cacheType, CachePolicy cachePolicy)
    throws Exception
    {
        throw new Exception("No such " + cacheType.toString());
    }
}
