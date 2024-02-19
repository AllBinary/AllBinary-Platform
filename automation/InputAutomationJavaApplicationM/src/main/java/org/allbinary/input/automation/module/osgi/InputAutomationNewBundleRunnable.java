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

import org.allbinary.logic.io.file.directory.SubDirectory;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.allbinary.logic.io.file.filter.BasicFileFilterUtil;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.thread.RunnableInterface;

import bundle.input.automation.InputAutomationBundleActivator;
import org.allbinary.logic.io.file.FileWrapperUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.time.TimeDelayHelper;

public class InputAutomationNewBundleRunnable
    implements RunnableInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private static final String FILE = "file:";
    private static final String JAR_DIR_PROP = "org.knopflerfish.gosg.jars";
    private static final String INPUT_AUTMATION_MODULE_BUNDLE_JAR_PATH = "ia/auto";
    private static final int MODULES_START_LEVEL = 8;
        
    private final InputAutomationBundleActivator inputAutomationBundleActivator;
    
    private boolean running;
    
    private Vector fileVector;
    
    public InputAutomationNewBundleRunnable(
        InputAutomationBundleActivator inputAutomationBundleActivator)
    {
        this.inputAutomationBundleActivator = inputAutomationBundleActivator;
        this.fileVector = new Vector();
    }
    
    public void setThread(Thread thread)throws Exception
    {
        
    }

    public synchronized boolean isRunning()
    {
        return running;
    }
    
    public synchronized void setRunning(boolean running)
    {
        this.running = running;
    }
    
    //1. Look in directory for all modules
    //2. Compare Existing modules to those found
    //3. Request user if they would like to add new modules
    //4.
    /*
        this.addNewModules(vector);
        serviceReference.getBundle()
        context.
        context.installBundle()
     
     */
    
    private void updateModules()
    throws Exception
    {
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "updateModules"));
        
        Vector vector = this.findNewModules();
        Iterator iterator = vector.iterator();
        while(iterator.hasNext())
        {
            Bundle bundle = this.install((URL) iterator.next());
            
            if(bundle != null)
            {
                bundle.start(MODULES_START_LEVEL);
            }
        }
    }
    
    private HashMap getAllJarSymbolicNameHashMap()
    throws Exception
    {
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "getAllJarSymbolicNameHashMap"));
        
        HashMap hashMap = new HashMap();
        Vector jarFileVector = this.getJarModuleFileVector();
        
        LogUtil.put(LogFactory.getInstance("Jar Module Files: " + jarFileVector, this, "getAllJarSymbolicNameHashMap"));
        
        Iterator iterator = jarFileVector.iterator();
        while(iterator.hasNext())
        {
            File file = (File) iterator.next();
            if(!file.isDirectory())
            {
                FileInputStream fileInputStream =
                    new FileInputStream(file);
                JarInputStream jarInputStream =
                    new JarInputStream(fileInputStream);
                Manifest manifest = jarInputStream.getManifest();
                if (manifest == null)
                {
                    //throw new IOException("Bundle manifest is missing");
                }
                else
                {
                    String symbolicName = manifest.getMainAttributes().getValue(
                        Constants.BUNDLE_SYMBOLICNAME);
                    if(symbolicName != null)
                        hashMap.put(symbolicName, new URL(FILE + file.getAbsolutePath()));
                }
            }
        }
        return hashMap;
    }
    
    private Vector getJarModuleFileVector()
    {
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "getJarModuleFileVector"));
        
        String baseJarPath = System.getProperty(JAR_DIR_PROP);
        
        if(baseJarPath.startsWith(FILE))
        {
            baseJarPath = baseJarPath.substring(FILE.length());
        }
        
        FileFilter jarFileFilter = BasicFileFilterUtil.getInstance(".jar");
        
        String path = baseJarPath + INPUT_AUTMATION_MODULE_BUNDLE_JAR_PATH;
        
        LogUtil.put(LogFactory.getInstance("Path: " + path, this, "getJarModuleFileVector"));
        
        File file = new File(path);
        
        LogUtil.put(LogFactory.getInstance("File: " + file.getAbsolutePath() +
            " isDirectory: " + file.isDirectory(), this, "getJarModuleFileVector"));
        
        return new SubDirectory().search(jarFileFilter, FileWrapperUtil.wrapFile(file));
    }
    
    private Vector getInstalledJarSymbolicNameVector()
    throws Exception
    {
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "getInstalledJarSymbolicNameVector"));
        
        Vector vector = new Vector();
        
        BundleContext bundleContext =
            InputAutomationBundleActivator.getBundleContext();
        
        Bundle[] bundleArray = bundleContext.getBundles();
        
        if(bundleArray != null)
        {
            LogUtil.put(LogFactory.getInstance("bundleArray: " + bundleArray.length,
                "InputAutomationBundleActivator", "getInputAutomationModuleServices"));
            
            for(int index = 0; index < bundleArray.length; index++)
            {
                Bundle bundle = bundleArray[index];
                vector.add(bundle.getSymbolicName());
            }
        }
        return vector;
    }
    
    private boolean isInstalled(String symbolicName)
    throws Exception
    {
        LogUtil.put(LogFactory.getInstance(CommonLabels.getInstance().START + symbolicName, this, "isInstalled"));
        
        Vector vector = this.getInstalledJarSymbolicNameVector();
        Iterator iterator = vector.iterator();
        while(iterator.hasNext())
        {
            String nextSymbolicName = (String) iterator.next();
            if(nextSymbolicName.compareTo(symbolicName) == 0)
            {
                return true;
            }
        }
        return false;
    }
    
    private Vector findNewModules() throws Exception
    {
        LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "findNewModules"));
        
        Vector vector = new Vector();
        HashMap hashMap = this.getAllJarSymbolicNameHashMap();
        
        LogUtil.put(LogFactory.getInstance("All: " + hashMap, this, "findNewModules"));
        
        Set set = hashMap.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext())
        {
            String symbolicName = (String) iterator.next();
            if(!this.isInstalled(symbolicName))
            {
                vector.add(hashMap.get(symbolicName));
            }
        }
        return vector;
    }
    
    private Bundle install(URL url) throws Exception
    {
        LogUtil.put(LogFactory.getInstance(CommonLabels.getInstance().START + url, this, "install"));
        
        BundleContext bundleContext =
            InputAutomationBundleActivator.getBundleContext();
        
        return bundleContext.installBundle(url.toString());
    }
    
    public void run()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, this.commonStrings.RUN));
            
            this.setRunning(true);
            
            TimeDelayHelper timeHelper = new TimeDelayHelper(1000);
            
            while(this.isRunning())
            {
                timeHelper.setStartTime();
                
                LogUtil.put(LogFactory.getInstance(
                    CommonLabels.getInstance().ELAPSED + timeHelper.getElapsed(), this, this.commonStrings.RUN));
                
                this.updateModules();
                //Thread.sleep(10000);
                break;
            }
            LogUtil.put(LogFactory.getInstance(this.commonStrings.END, this, this.commonStrings.RUN));
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, this.commonStrings.RUN, e));
        }
    }
}
