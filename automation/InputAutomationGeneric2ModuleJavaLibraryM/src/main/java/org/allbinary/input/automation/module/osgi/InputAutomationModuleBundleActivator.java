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
package org.allbinary.input.automation.module.osgi;

import bundle.input.automation.module.InputAutomationModuleServiceInterface;
import bundle.input.automation.module.configuration.InputAutomationConfigurationModuleChangeListener;
import org.allbinary.input.automation.configuration.InputAutomationConfigurationModuleChangeEvent;
import org.allbinary.input.automation.configuration.InputAutomationConfigurationUtil;
import org.allbinary.input.automation.module.InputAutomationModuleFactoryInterface;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.osgi.OSGIActivatorUtil;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceReference;

public class InputAutomationModuleBundleActivator
    implements BundleActivator
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private InputAutomationModuleFactoryInterface[] inputAutomationModuleInterface;
    private ServiceFactory serviceFactory;
    
    public InputAutomationModuleBundleActivator()
    throws Exception
    {
        this.init();
    }
    
    public void init() throws Exception {
        throw new RuntimeException();
    }
    
    private InputAutomationConfigurationModuleChangeListener
        getInputAutomationConfigurationModuleChangeListener(
        BundleContext context)
        throws Exception
    {
        ServiceReference serviceReference =
            context.getServiceReference(
            InputAutomationConfigurationModuleChangeListener.class.getName());
        
        if(serviceReference != null)
        {
            InputAutomationConfigurationModuleChangeListener
                inputAutomationConfigurationModuleChangeListener =
                (InputAutomationConfigurationModuleChangeListener)
                context.getService(serviceReference);
            
            if(inputAutomationConfigurationModuleChangeListener == null)
                throw new Exception("No Service For ServiceReference");
            
            return inputAutomationConfigurationModuleChangeListener;
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("No ServiceReference: " +
                InputAutomationConfigurationModuleChangeListener.class.getName(), this, "addModules"));
            return null;
        }
    }
    
    private void addModules(BundleContext context)
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "addModules"));
            
            InputAutomationConfigurationModuleChangeListener
                inputAutomationConfigurationModuleChangeListener =
                this.getInputAutomationConfigurationModuleChangeListener(context);
            
            if(inputAutomationConfigurationModuleChangeListener != null)
            {
                for(int index = 0; index < this.getInputAutomationModuleInterface().length; index++)
                {
                    InputAutomationConfigurationModuleChangeEvent
                        inputAutomationConfigurationModuleChangeEvent =
                        InputAutomationConfigurationUtil.getChangeEvent(
                        this.getInputAutomationModuleInterface()[index]);
                    
                    inputAutomationConfigurationModuleChangeListener.onAdd(
                        inputAutomationConfigurationModuleChangeEvent);
                }
            }
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.EXCEPTION, this, "addModules"));
        }
    }
    
    public void registerAsService(BundleContext bundleContext)
    throws Exception
    {
        OSGIActivatorUtil.registerAsService(
            bundleContext,
            getServiceFactory(),
            InputAutomationModuleServiceInterface.class.getName());
    }
    
    private void removeModules(BundleContext context)
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "removeModules"));
            InputAutomationConfigurationModuleChangeListener
                inputAutomationConfigurationModuleChangeListener =
                this.getInputAutomationConfigurationModuleChangeListener(context);
            
            if(inputAutomationConfigurationModuleChangeListener != null)
            {
                for(int index = 0; index < this.getInputAutomationModuleInterface().length; index++)
                {
                    InputAutomationConfigurationModuleChangeEvent
                        inputAutomationConfigurationModuleChangeEvent =
                        InputAutomationConfigurationUtil.getChangeEvent(
                        this.getInputAutomationModuleInterface()[index]);
                    
                    inputAutomationConfigurationModuleChangeListener.onRemove(
                        inputAutomationConfigurationModuleChangeEvent);
                }
            }
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.EXCEPTION, this, "removeModules"));
        }
    }
    
    public void start(BundleContext context)
    throws Exception
    {
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, this.commonStrings.START));
        
        this.addModules(context);
        
        this.registerAsService(context);
    }
    
    public void stop(BundleContext context)
    throws Exception
    {
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "stop"));
        
        this.removeModules(context);
    }
    
    protected InputAutomationModuleFactoryInterface[] getInputAutomationModuleInterface()
    {
        return inputAutomationModuleInterface;
    }
    
    protected void setInputAutomationModuleInterface(InputAutomationModuleFactoryInterface inputAutomationModuleInterface[])
    {
        this.inputAutomationModuleInterface = inputAutomationModuleInterface;
    }
    
    protected Object getServiceFactory()
    {
        return serviceFactory;
    }
    
    protected void setServiceFactory(ServiceFactory serviceObject)
    {
        this.serviceFactory = serviceObject;
    }
}
