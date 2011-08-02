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
package allbinary.osgi.service;

import java.util.Vector;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;

import allbinary.osgi.OSGIServiceInterface;

public class OSGIServiceUtil
{
    private OSGIServiceUtil()
    {
    }
    
    public static Vector getServicesObjectVector(
        BundleContext bundleContext, ServiceReference serviceReferences[])
        throws Exception
    {
        Vector vector = new Vector();
        
        if(serviceReferences != null)
        {
            LogUtil.put(new Log("Service References: " + serviceReferences.length, 
                "OSGIServiceUtil", "getServicesObjectVector"));
            
            for(int index = 0; index < serviceReferences.length; index++)
            {
                ServiceReference serviceReference = serviceReferences[index];

                //LogUtil.put(new Log("Service Reference Properties: " + stringBuffer.toString(), this, "getInputAutomationModuleServices"));
                
                if(serviceReference != null)
                {
                    OSGIServiceInterface osgiServiceInterface = 
                        (OSGIServiceInterface)
                        bundleContext.getService(serviceReference);
                    
                    if(osgiServiceInterface == null)
                    {
                        throw new Exception("No Service For Reference");
                    }
                    
                    vector.add(osgiServiceInterface);
                }
            }
        }
        return vector;
    }
}
