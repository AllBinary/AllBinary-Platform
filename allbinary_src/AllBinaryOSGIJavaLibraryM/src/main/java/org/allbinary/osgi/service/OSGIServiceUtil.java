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
package org.allbinary.osgi.service;

import java.util.Vector;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.osgi.OSGIServiceInterface;

public class OSGIServiceUtil
{
    private static final OSGIServiceUtil instance = new OSGIServiceUtil();

    /**
     * @return the instance
     */
    public static OSGIServiceUtil getInstance() {
        return instance;
    }
    
    protected final LogUtil logUtil = LogUtil.getInstance();

    private OSGIServiceUtil()
    {
    }
    
    public Vector getServicesObjectVector(
        final BundleContext bundleContext, final ServiceReference[] serviceReferences)
        throws Exception
    {
        final Vector vector = new Vector();
        
        if(serviceReferences != null)
        {
            final int size = serviceReferences.length;
            logUtil.put("Service References: " + size, this, "getServicesObjectVector");
            
            for(int index = 0; index < size; index++)
            {
                ServiceReference serviceReference = serviceReferences[index];

                //logUtil.put("Service Reference Properties: " + stringBuffer.toString(), this, "getInputAutomationModuleServices");
                
                if(serviceReference != null)
                {
                    final OSGIServiceInterface osgiServiceInterface = 
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
