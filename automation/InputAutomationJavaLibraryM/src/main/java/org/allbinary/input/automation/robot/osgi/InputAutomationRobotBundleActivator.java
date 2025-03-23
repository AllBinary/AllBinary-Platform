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
package org.allbinary.input.automation.robot.osgi;

import bundle.input.automation.robot.InputAutomationRobotChangeListener;
import bundle.input.automation.robot.InputAutomationRobotServiceInterface;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.input.automation.robot.InputRobotInterface;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.string.CommonStrings;
import org.allbinary.osgi.OSGIActivatorUtil;
import org.osgi.framework.ServiceReference;

public class InputAutomationRobotBundleActivator
    implements BundleActivator
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private InputRobotInterface[] inputRobotInterface;
    private ServiceFactory serviceFactory;
    
    public InputAutomationRobotBundleActivator()
    throws Exception
    {
        this.init();
    }
    
    public void init() throws Exception {
        throw new RuntimeException();
    }
    
    public void registerAsService(BundleContext bundleContext)
    throws Exception
    {
        OSGIActivatorUtil.registerAsService(
            bundleContext, getServiceFactory(),
            InputAutomationRobotServiceInterface.class.getName());
    }
    
    private InputAutomationRobotChangeListener
        getInputAutomationRobotChangeListener(
        BundleContext context)
        throws Exception
    {
        ServiceReference serviceReference =
            context.getServiceReference(
            InputAutomationRobotChangeListener.class.getName());
        
        if(serviceReference != null)
        {
            InputAutomationRobotChangeListener
                inputAutomationRobotChangeListener =
                (InputAutomationRobotChangeListener)
                context.getService(serviceReference);
            
            if(inputAutomationRobotChangeListener == null)
                throw new Exception("No Service For ServiceReference");
            
            return inputAutomationRobotChangeListener;
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("No ServiceReference: " +
                InputAutomationRobotChangeListener.class.getName(), this, "getInputAutomationRobotChangeListener"));
            return null;
        }
    }
    
    private void addRobots(BundleContext context)
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "addRobots"));
            
            InputAutomationRobotChangeListener
                inputAutomationRobotChangeListener =
                this.getInputAutomationRobotChangeListener(context);
            
            if(inputAutomationRobotChangeListener != null)
            {
                for(int index = 0; index < this.getInputRobotInterface().length; index++)
                {                    
                    InputAutomationRobotChangeEvent inputAutomationRobotChangeEvent =
                        InputAutomationRobotUtil.getChangeEvent(
                        this.getInputRobotInterface()[index]);
                    
                    inputAutomationRobotChangeListener.onAdd(
                        inputAutomationRobotChangeEvent);
                }
            }
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, "addModules"));
        }
    }
    
    private void removeRobots(BundleContext context)
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "removeRobots"));
            InputAutomationRobotChangeListener
                inputAutomationRobotChangeListener =
                this.getInputAutomationRobotChangeListener(context);
            
            if(inputAutomationRobotChangeListener != null)
            {
                for(int index = 0; index < this.getInputRobotInterface().length; index++)
                {
                    InputAutomationRobotChangeEvent inputAutomationRobotChangeEvent =
                        InputAutomationRobotUtil.getChangeEvent(
                        this.getInputRobotInterface()[index]);
                    
                    inputAutomationRobotChangeListener.onRemove(
                        inputAutomationRobotChangeEvent);
                }
            }
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, "removeRobots"));
        }
    }
    
    public void start(BundleContext context)
    throws Exception
    {
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, this.commonStrings.START));
        
        //TWB - It is possible that the injectors get 
        //added twice but that will not cause failure.
        this.addRobots(context);
        
        this.registerAsService(context);
    }
    
    public void stop(BundleContext context)
    throws Exception
    {
        LogUtil.put(LogFactory.getInstance("Stop", this, this.commonStrings.START));
        
        this.removeRobots(context);
        //TWB - add imp for removal InputRobotFactory.getInstance().remove
    }
    
    protected Object getServiceFactory()
    {
        return serviceFactory;
    }
    
    protected void setServiceFactory(ServiceFactory serviceObject)
    {
        this.serviceFactory = serviceObject;
    }
    
    public InputRobotInterface[] getInputRobotInterface()
    {
        return inputRobotInterface;
    }
    
    public void setInputRobotInterface(InputRobotInterface[] inputRobotInterface)
    {
        this.inputRobotInterface = inputRobotInterface;
    }
}
