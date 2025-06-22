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

import java.util.Iterator;
import java.util.Vector;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.osgi.OSGIServiceInterface;
import org.allbinary.osgi.OSGIServiceVisitorInterface;
import org.allbinary.string.CommonStrings;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

abstract public class InputAutomationServiceConsumer
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    private String registryName;
    private BundleContext bundleContext;
    private OSGIServiceVisitorInterface osgiServiceVisitorInterface;
        
    public InputAutomationServiceConsumer(
        String registryName, BundleContext bundleContext,
        OSGIServiceVisitorInterface osgiServiceVisitorInterface)
    {
        this.setRegistryName(registryName);
        this.setBundleContext(bundleContext);
        this.setOsgiServiceVisitorInterface(osgiServiceVisitorInterface);
    }
    
    public void process()
    throws Exception
    {
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "process"));
        
        Vector vector = OSGIServiceUtil.getServicesObjectVector(
            this.getBundleContext(),
            this.getServiceReferences());
        
        LogUtil.put(LogFactory.getInstance("Processing " + vector.size() + " Services", this, "process"));
        Iterator iterator = vector.iterator();
        while(iterator.hasNext())
        {
            OSGIServiceInterface osgiServiceInterface = 
                (OSGIServiceInterface) iterator.next();
            if(!getOsgiServiceVisitorInterface().visit(osgiServiceInterface))
            {
                throw new Exception("Unable to process service: " + osgiServiceInterface);
            }
        }
    }

    private ServiceReference[] getServiceReferences()
        throws Exception
    {
         return this.getBundleContext().getServiceReferences(
             this.getRegistryName(), null);
    }
    
    public BundleContext getBundleContext()
    {
        return bundleContext;
    }

    public void setBundleContext(BundleContext aBundleContext)
    {
        bundleContext = aBundleContext;
    }

    public String getRegistryName()
    {
        return registryName;
    }

    public void setRegistryName(String registryName)
    {
        this.registryName = registryName;
    }

    public OSGIServiceVisitorInterface getOsgiServiceVisitorInterface()
    {
        return osgiServiceVisitorInterface;
    }

    public void setOsgiServiceVisitorInterface(OSGIServiceVisitorInterface osgiServiceVisitorInterface)
    {
        this.osgiServiceVisitorInterface = osgiServiceVisitorInterface;
    }
}
