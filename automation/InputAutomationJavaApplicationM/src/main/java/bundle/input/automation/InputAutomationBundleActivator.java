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

import bundle.input.automation.module.configuration.InputAutomationConfigurationModuleChangeListener;
import bundle.input.automation.robot.InputAutomationRobotChangeListener;
import org.allbinary.input.automation.InputAutomationJFrame;
import org.allbinary.input.automation.module.osgi.InputAutomationModuleServiceConsumer;
import org.allbinary.input.automation.module.osgi.InputAutomationNewBundleRunnable;
import org.allbinary.input.automation.osgi.InputAutomationServiceFactory;
import org.allbinary.input.automation.robot.osgi.InputAutomationRobotServiceConsumer;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.osgi.OSGIActivatorUtil;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class InputAutomationBundleActivator
    implements BundleActivator, InputAutomationBundleActivatorListenerInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private static Thread moduleManagementThread;
    private static BundleContext bundleContext;
    //private ServiceTracker remoteTracker;
        
    public static BundleContext getBundleContext()
    {
        return bundleContext;
    }
    
    public void start(final BundleContext bundleContext) throws Exception
    {
        try
        {
            logUtil.put(this.commonStrings.START, this, this.commonStrings.START);
            
            this.bundleContext = bundleContext;
        /*
        remoteTracker = new ServiceTracker(
            context, RemoteFramework.class.getName(), null)
        {
            public Object addingService(ServiceReference serviceReference)
            {
                logUtil.put("Start Name: " + serviceReference.getBundle().getSymbolicName(), this, "addingService");
                Object obj = super.addingService(serviceReference);
                return obj;
            }
            public void removedService(ServiceReference serviceReference, Object service)
            {
                logUtil.put("Start Name: " + serviceReference.getBundle().getSymbolicName(), this, "removedService");
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
            logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.START, e);
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
    
    public void stop(final BundleContext context)
    throws Exception
    {
        logUtil.put(this.commonStrings.START, this, "stop");
        if(InputAutomationJFrame.getInstance() != null)
        {
            InputAutomationJFrame.destroy();
        }
        else
        {
            logUtil.put("Nothing to stop", this, "stop");
        }
    }
}
