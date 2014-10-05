/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
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
package org.allbinary.bundle.logic.system.loader;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

import org.allbinary.logic.communication.log.Log;
import org.allbinary.logic.communication.log.LogUtil;

public class CryptServiceFactory
    implements ServiceFactory
{
    private CryptService cryptService = new CryptService();
    
    public CryptServiceFactory()
    {
    }
        
    public Object getService(
        Bundle bundle, ServiceRegistration registration)
    {
        LogUtil.put(LogFactory.getInstance("Start", this, "getService"));

        return this.cryptService;
    }
    
    public void ungetService(
        Bundle bundle,
        ServiceRegistration registration,
        Object service)
    {
    }    
}
