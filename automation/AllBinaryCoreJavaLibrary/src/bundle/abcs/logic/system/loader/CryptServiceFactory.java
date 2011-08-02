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
package bundle.abcs.logic.system.loader;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;

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
        LogUtil.put(new Log("Start", this, "getService"));

        return this.cryptService;
    }
    
    public void ungetService(
        Bundle bundle,
        ServiceRegistration registration,
        Object service)
    {
    }    
}
