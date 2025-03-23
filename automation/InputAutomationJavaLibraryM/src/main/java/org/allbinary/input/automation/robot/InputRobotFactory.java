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
package org.allbinary.input.automation.robot;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.help.HelpSet;
import javax.help.event.HelpSetEvent;
import javax.help.event.HelpSetListener;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.help.JavaHelpSetNotifier;
import org.allbinary.logic.java.object.InterfaceUtil;
import org.allbinary.logic.system.loader.SecuredNativeLibraryInterface;
import java.util.Collection;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.string.CommonStrings;

public class InputRobotFactory
{
    private static final InputRobotFactory inputRobotFactory = new InputRobotFactory();
    
    public static InputRobotFactory getInstance() throws Exception
    {
        return inputRobotFactory;
    }
    
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final Hashtable hashtable = new Hashtable();
    private HelpSetListener helpSetListenerInterface;
    
    private InputRobotFactory()
    {
        try
        {
            final GraphicsEnvironment graphenv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            final GraphicsDevice [] screens = graphenv.getScreenDevices();
            
            InputRobotInterface inputRobotInterface;
            for (int i = 0; i < screens.length; i++)
            {
                inputRobotInterface = (InputRobotInterface) new InputRobot(screens [i]);
                LogUtil.put(LogFactory.getInstance("Adding Robot: " + inputRobotInterface.getName(), this, "getRobots"));
                this.get().put(inputRobotInterface.getName(), inputRobotInterface);
            }
            
            LogUtil.put(LogFactory.getInstance("Number Of Robots: " + this.hashtable.size(), this, "getRobots"));
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, this.commonStrings.CONSTRUCTOR));
        }
    }
    
    public void addListener(final HelpSetListener helpSetListenerInterface)
    {
        this.helpSetListenerInterface = helpSetListenerInterface;
    }
    
    public void add(final InputRobotInterface inputRobotInterface)
    throws Exception
    {
        LogUtil.put(LogFactory.getInstance("Adding InputRobotInterface: " + inputRobotInterface.getName(), this, "add"));
        this.get().put(inputRobotInterface.getName(), inputRobotInterface);
        
        final HelpSet helpSet = inputRobotInterface.getHelpSet();
        if(this.helpSetListenerInterface != null)
        {
            if(helpSet != null)
            {
                if(!JavaHelpSetNotifier.isNotified(helpSet))
                {
                    final HelpSetEvent helpSetEvent = new HelpSetEvent(
                        this, helpSet, HelpSetEvent.HELPSET_ADDED);
                    this.helpSetListenerInterface.helpSetAdded(helpSetEvent);
                }
            }
            else
            {
                LogUtil.put(LogFactory.getInstance("Null HelpSet For: " +
                    inputRobotInterface.getName(), this, "add"));
            }
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("No HelpSet Listener", this, "add"));
        }
    }
    
    public void loadLibraries()
    throws Exception
    {
        final Set set = this.get().keySet();
        LogUtil.put(LogFactory.getInstance("Loading Libraries", "InputRobotFactory", "loadLibraries"));
        final Iterator iterator = set.iterator();
        while(iterator.hasNext())
        {
            InputRobotInterface inputRobotInterface =
                InputRobotFactory.getInstance().get((String) iterator.next());
            loadLibrary(inputRobotInterface);
        }
    }
    
    public static void loadLibraries(Collection collection)
    throws Exception
    {
        LogUtil.put(LogFactory.getInstance("Loading Libraries", "InputRobotFactory", "loadLibraries"));
        final Iterator iterator = collection.iterator();
        while(iterator.hasNext())
        {
            loadLibrary((InputRobotInterface) iterator.next());
        }
    }
    
    public static void loadLibrary(final InputRobotInterface inputRobotInterface)
        throws Exception
    {
        if(InterfaceUtil.isImplemented(SecuredNativeLibraryInterface.class, inputRobotInterface))
        {
            LogUtil.put(LogFactory.getInstance("Loading Library: " + 
                inputRobotInterface.getName(), 
                "InputRobotFactory", "loadLibraries"));
            
            final SecuredNativeLibraryInterface securedNativeLibraryInterface =
                (SecuredNativeLibraryInterface) inputRobotInterface;
            securedNativeLibraryInterface.load();
        }
    }
    
    public void unloadLibraries() 
        throws Exception
    {
        LogUtil.put(LogFactory.getInstance("Unloading Libraries", this, "unloadLibraries"));
        final Set set = this.get().keySet();
        final Iterator iterator = set.iterator();
        InputRobotInterface inputRobotInterface;
        while(iterator.hasNext())
        {
            inputRobotInterface = this.get((String) iterator.next());
            
            if(InterfaceUtil.isImplemented(
                SecuredNativeLibraryInterface.class, inputRobotInterface))
            {
                LogUtil.put(LogFactory.getInstance("Unloading Library: " + inputRobotInterface.getName(),
                    this, "unloadLibraries"));
                
                SecuredNativeLibraryInterface securedNativeLibraryInterface =
                    (SecuredNativeLibraryInterface) inputRobotInterface;
                securedNativeLibraryInterface.unload();
            }
        }
    }
    
    public Hashtable get() throws Exception
    {
        return hashtable;
    }
    
    public InputRobotInterface get(final String name)
    {
        LogUtil.put(LogFactory.getInstance("Getting Robot: " + name, this, "getRobots"));
        return (InputRobotInterface) this.hashtable.get(name);
    }
}
