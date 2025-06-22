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
package org.allbinary.bundle.logic.preloader;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.bundle.logic.system.loader.CryptServiceFactory;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.string.CommonStrings;

public class AllBinaryPreloaderActivator
    implements BundleActivator
{
    private static BundleContext context;

    public AllBinaryPreloaderActivator()
    {
    }

    private static final String CRYPT_REGISTRY_NAME =
        "bundle.abcs.logic.system.loader.CryptService";
    
    public static BundleContext getBundleContext()
    {
        return context;
    }
 
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public void start(BundleContext context) throws Exception
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "start"));
            AllBinaryPreloaderActivator.context = context;
            this.registerAsService();
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.EXCEPTION, this, "start", e));
            throw e;
        }
    }
    
    public void registerAsService()
    throws Exception
    {
        Hashtable properties = new Hashtable();
        
        ServiceRegistration serviceRegistration =
            context.registerService(CRYPT_REGISTRY_NAME,
            new CryptServiceFactory(), properties);
        
        ServiceReference serviceReference =
            serviceRegistration.getReference();
        
        serviceReference =
            context.getServiceReference(CRYPT_REGISTRY_NAME);
        if(serviceReference == null)
            throw new Exception("No Such Service Reference");
    }
            
    public void stop(BundleContext context)
    throws Exception
    {
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "stop"));
    }    
}
