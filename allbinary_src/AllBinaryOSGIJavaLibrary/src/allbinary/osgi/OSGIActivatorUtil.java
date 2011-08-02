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
package allbinary.osgi;

import java.util.Hashtable;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class OSGIActivatorUtil
{
    private OSGIActivatorUtil()
    {
    }
 
    public static void registerAsService(
        BundleContext bundleContext, 
        Object object,
        String serviceName)
    throws Exception
    {
        OSGIActivatorUtil.registerAsService(
            bundleContext, object, serviceName, new Hashtable());
    }
    
    public static void registerAsService(
        BundleContext bundleContext,
        Object object,
        String serviceName,
        Hashtable properties)
    throws Exception
    {
        ServiceRegistration serviceRegistration =
            bundleContext.registerService(
                serviceName, object, properties);
        
        ServiceReference serviceReference =
            serviceRegistration.getReference();
        
        serviceReference = bundleContext.getServiceReference(
            serviceName);
        
        if(serviceReference == null)
        {
            throw new Exception("No Such Service Reference");
        }
    }
}
