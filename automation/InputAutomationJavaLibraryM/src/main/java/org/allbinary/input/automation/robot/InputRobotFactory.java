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
import java.util.Iterator;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.string.CommonStrings;

public class InputRobotFactory
{

    private static final InputRobotFactory inputRobotFactory = new InputRobotFactory();
    
    public static InputRobotFactory getInstance() throws Exception
    {
        return inputRobotFactory;
    }
 
    protected final LogUtil logUtil = LogUtil.getInstance();
    
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
                logUtil.put("Adding Robot: " + inputRobotInterface.getName(), this, "getRobots");
                this.get().put(inputRobotInterface.getName(), inputRobotInterface);
            }
            
            logUtil.put("Number Of Robots: " + this.hashtable.size(), this, "getRobots");
        }
        catch(Exception e)
        {
            logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.CONSTRUCTOR);
        }
    }
    
    public void addListener(final HelpSetListener helpSetListenerInterface)
    {
        this.helpSetListenerInterface = helpSetListenerInterface;
    }
    
    public void add(final InputRobotInterface inputRobotInterface)
    throws Exception
    {
        logUtil.put("Adding InputRobotInterface: " + inputRobotInterface.getName(), this, "add");
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
                logUtil.put("Null HelpSet For: " +
                    inputRobotInterface.getName(), this, "add");
            }
        }
        else
        {
            logUtil.put("No HelpSet Listener", this, "add");
        }
    }
    
    public void loadLibraries()
    throws Exception
    {
        final Set set = this.get().keySet();
        logUtil.put("Loading Libraries", this, "loadLibraries");

        final Object[] nameArray = set.toArray();
        final int size = nameArray.length;
        for(int index = 0; index < size; index++)
        {
            InputRobotInterface inputRobotInterface =
                InputRobotFactory.getInstance().get((String) nameArray[index]);
            loadLibrary(inputRobotInterface);
        }
    }
    
    public static void loadLibraries(Collection collection)
    throws Exception
    {
        final LogUtil logUtil = LogUtil.getInstance();
        logUtil.put("Loading Libraries", "InputRobotFactory", "loadLibraries");
        final Iterator iterator = collection.iterator();
        while(iterator.hasNext())
        {
            loadLibrary((InputRobotInterface) iterator.next());
        }
    }
    
    public static void loadLibrary(final InputRobotInterface inputRobotInterface)
        throws Exception
    {
        final LogUtil logUtil = LogUtil.getInstance();
        if(InterfaceUtil.isImplemented(SecuredNativeLibraryInterface.class, inputRobotInterface))
        {
            logUtil.put("Loading Library: " + 
                inputRobotInterface.getName(), 
                "InputRobotFactory", "loadLibraries");
            
            final SecuredNativeLibraryInterface securedNativeLibraryInterface =
                (SecuredNativeLibraryInterface) inputRobotInterface;
            securedNativeLibraryInterface.load();
        }
    }
    
    public void unloadLibraries() 
        throws Exception
    {
        logUtil.put("Unloading Libraries", this, "unloadLibraries");
        final Set set = this.get().keySet();

        InputRobotInterface inputRobotInterface;
        
        final Object[] inputRobotArray = set.toArray();
        final int size = inputRobotArray.length;
        for(int index = 0; index < size; index++)
        {
            inputRobotInterface = this.get((String) inputRobotArray[index]);
            
            if(InterfaceUtil.isImplemented(
                SecuredNativeLibraryInterface.class, inputRobotInterface))
            {
                logUtil.put("Unloading Library: " + inputRobotInterface.getName(),
                    this, "unloadLibraries");
                
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
        logUtil.put("Getting Robot: " + name, this, "getRobots");
        return (InputRobotInterface) this.hashtable.get(name);
    }
}
