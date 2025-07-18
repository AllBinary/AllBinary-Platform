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

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;

import bundle.input.automation.InputAutomationBundleActivator;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.io.file.FileWrapperUtil;
import org.allbinary.logic.io.file.directory.SubDirectory;
import org.allbinary.logic.io.file.filter.BasicFileFilterUtil;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonStrings;
import org.allbinary.thread.RunnableInterface;
import org.allbinary.time.TimeDelayHelper;
import org.allbinary.util.BasicArrayList;

public class InputAutomationNewBundleRunnable
    implements RunnableInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private static final String FILE = "file:";
    private static final String JAR_DIR_PROP = "org.knopflerfish.gosg.jars";
    private static final String INPUT_AUTMATION_MODULE_BUNDLE_JAR_PATH = "ia/auto";
    private static final int MODULES_START_LEVEL = 8;
        
    private final InputAutomationBundleActivator inputAutomationBundleActivator;
    
    private boolean running;
    
    private BasicArrayList fileBasicArrayList;
    
    public InputAutomationNewBundleRunnable(
        InputAutomationBundleActivator inputAutomationBundleActivator)
    {
        this.inputAutomationBundleActivator = inputAutomationBundleActivator;
        this.fileBasicArrayList = new BasicArrayList();
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
        logUtil.put(this.commonStrings.START, this, "updateModules");
        
        final BasicArrayList list = this.findNewModules();
        final int size = list.size();
        
        Bundle bundle;
        for(int index = 0; index < size; index++)
        {
            bundle = this.install((URL) list.get(index));
            
            if(bundle != null)
            {
                bundle.start(MODULES_START_LEVEL);
            }
        }
    }
    
    private HashMap getAllJarSymbolicNameHashMap()
    throws Exception
    {
        logUtil.put(this.commonStrings.START, this, "getAllJarSymbolicNameHashMap");
        
        final HashMap hashMap = new HashMap();
        final BasicArrayList jarFileBasicArrayList = this.getJarModuleFileBasicArrayList();
        
        logUtil.put("Jar Module Files: " + jarFileBasicArrayList, this, "getAllJarSymbolicNameHashMap");
        
        final int size = jarFileBasicArrayList.size();
        File file;
        for(int index = 0; index < size; index++)
        {
            file = (File) jarFileBasicArrayList.get(index);
            if(!file.isDirectory())
            {
                final FileInputStream fileInputStream =
                    new FileInputStream(file);
                final JarInputStream jarInputStream =
                    new JarInputStream(fileInputStream);
                final Manifest manifest = jarInputStream.getManifest();
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
    
    private BasicArrayList getJarModuleFileBasicArrayList()
    {
        logUtil.put(this.commonStrings.START, this, "getJarModuleFileBasicArrayList");
        
        String baseJarPath = System.getProperty(JAR_DIR_PROP);
        
        if(baseJarPath.startsWith(FILE))
        {
            baseJarPath = baseJarPath.substring(FILE.length());
        }
        
        FileFilter jarFileFilter = BasicFileFilterUtil.getInstance(".jar");
        
        String path = baseJarPath + INPUT_AUTMATION_MODULE_BUNDLE_JAR_PATH;
        
        logUtil.put("Path: " + path, this, "getJarModuleFileBasicArrayList");
        
        File file = new File(path);
        
        logUtil.put("File: " + file.getAbsolutePath() +
            " isDirectory: " + file.isDirectory(), this, "getJarModuleFileBasicArrayList");
        
        return SubDirectory.getInstance().search(jarFileFilter, FileWrapperUtil.wrapFile(file));
    }
    
    private BasicArrayList getInstalledJarSymbolicNameBasicArrayList()
    throws Exception
    {
        logUtil.put(this.commonStrings.START, this, "getInstalledJarSymbolicNameBasicArrayList");
        
        BasicArrayList vector = new BasicArrayList();
        
        BundleContext bundleContext =
            InputAutomationBundleActivator.getBundleContext();
        
        Bundle[] bundleArray = bundleContext.getBundles();
        
        if(bundleArray != null)
        {
            logUtil.put("bundleArray: " + bundleArray.length,this, "getInputAutomationModuleServices");
            
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
        logUtil.put(CommonLabels.getInstance().START + symbolicName, this, "isInstalled");
        
        final BasicArrayList list = this.getInstalledJarSymbolicNameBasicArrayList();
        final int size = list.size();
        String nextSymbolicName;
        for(int index = 0; index < size; index++)
        {
            nextSymbolicName = (String) list.get(index);
            if(nextSymbolicName.compareTo(symbolicName) == 0)
            {
                return true;
            }
        }
        return false;
    }
    
    private BasicArrayList findNewModules() throws Exception
    {
        logUtil.put(this.commonStrings.START, this, "findNewModules");
        
        BasicArrayList vector = new BasicArrayList();
        HashMap hashMap = this.getAllJarSymbolicNameHashMap();
        
        logUtil.put("All: " + hashMap, this, "findNewModules");
        
        Set set = hashMap.keySet();
        
        final Object[] symbolicNameArray = set.toArray();
        final int size = symbolicNameArray.length;
        for(int index = 0; index < size; index++)
        {
            String symbolicName = (String) symbolicNameArray[index];
            if(!this.isInstalled(symbolicName))
            {
                vector.add(hashMap.get(symbolicName));
            }
        }
        return vector;
    }
    
    private Bundle install(URL url) throws Exception
    {
        logUtil.put(CommonLabels.getInstance().START + url, this, "install");
        
        BundleContext bundleContext =
            InputAutomationBundleActivator.getBundleContext();
        
        return bundleContext.installBundle(url.toString());
    }
    
    public void run()
    {
        try
        {
            logUtil.put(this.commonStrings.START, this, this.commonStrings.RUN);
            
            this.setRunning(true);
            
            TimeDelayHelper timeHelper = new TimeDelayHelper(1000);
            
            while(this.isRunning())
            {
                timeHelper.setStartTime();
                
                logUtil.put(
                    CommonLabels.getInstance().ELAPSED + timeHelper.getElapsed(), this, this.commonStrings.RUN);
                
                this.updateModules();
                //Thread.sleep(10000);
                break;
            }
            logUtil.put(this.commonStrings.END, this, this.commonStrings.RUN);
        }
        catch (Exception e)
        {
            logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.RUN, e);
        }
    }
}
