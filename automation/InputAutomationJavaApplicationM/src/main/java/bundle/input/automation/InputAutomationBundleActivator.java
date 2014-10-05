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
package bundle.input.automation;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.input.automation.InputAutomationJFrame;
import org.allbinary.input.automation.osgi.InputAutomationServiceFactory;
import org.allbinary.input.automation.module.osgi.InputAutomationModuleServiceConsumer;
import org.allbinary.input.automation.module.osgi.InputAutomationNewBundleRunnable;
import org.allbinary.input.automation.robot.osgi.InputAutomationRobotServiceConsumer;
import org.allbinary.osgi.OSGIActivatorUtil;
import bundle.input.automation.module.configuration.InputAutomationConfigurationModuleChangeListener;
import org.allbinary.logic.communication.log.LogFactory;
import org.bundle.input.automation.robot.InputAutomationRobotChangeListener;

public class InputAutomationBundleActivator
    implements BundleActivator, InputAutomationBundleActivatorListenerInterface
{
    private static Thread moduleManagementThread;
    private static BundleContext bundleContext;
    //private ServiceTracker remoteTracker;
        
    public static BundleContext getBundleContext()
    {
        return bundleContext;
    }
    
    public void start(BundleContext bundleContext) throws Exception
    {
        try
        {
            LogUtil.put(LogFactory.getInstance("Start", this, "start"));
            
            this.bundleContext = bundleContext;
        /*
        remoteTracker = new ServiceTracker(
            context, RemoteFramework.class.getName(), null)
        {
            public Object addingService(ServiceReference serviceReference)
            {
                LogUtil.put(LogFactory.getInstance("Start Name: " + serviceReference.getBundle().getSymbolicName(), this, "addingService"));
                Object obj = super.addingService(serviceReference);
                return obj;
            }
            public void removedService(ServiceReference serviceReference, Object service)
            {
                LogUtil.put(LogFactory.getInstance("Start Name: " + serviceReference.getBundle().getSymbolicName(), this, "removedService"));
                super.removedService(serviceReference, service);
            }
        };
        remoteTracker.open(true);
         */
                /*
                try {
                }
                catch (IOException ioe) {
                        //throw new FactoryConfigurationError(ioe);
                }
                 */
            
            moduleManagementThread = new Thread(
                new InputAutomationNewBundleRunnable(this));
            
            moduleManagementThread.start();
            
            InputAutomationJFrame.create(this);
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, "start", e));
            throw e;
        }
    }
    
    public void registerAsService()
    throws Exception
    {
        OSGIActivatorUtil.registerAsService(
            this.getBundleContext(),
            InputAutomationServiceFactory.getInstance(),
            InputAutomationConfigurationModuleChangeListener.class.getName());

        OSGIActivatorUtil.registerAsService(
            this.getBundleContext(),
            InputAutomationServiceFactory.getInstance(),
            InputAutomationRobotChangeListener.class.getName());
    }
    
    public void useServices() throws Exception
    {
        new InputAutomationRobotServiceConsumer(this.getBundleContext()).process();
        new InputAutomationModuleServiceConsumer(this.getBundleContext()).process();        
    }
    
    public void stop(BundleContext context)
    throws Exception
    {
        LogUtil.put(LogFactory.getInstance("Start", this, "stop"));
        if(InputAutomationJFrame.getInstance() != null)
        {
            InputAutomationJFrame.destroy();
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("Nothing to stop", this, "stop"));
        }
    }
}
