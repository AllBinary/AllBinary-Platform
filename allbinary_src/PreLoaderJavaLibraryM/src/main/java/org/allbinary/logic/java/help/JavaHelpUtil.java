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
package org.allbinary.logic.java.help;

import org.allbinary.logic.communication.log.LogFactory;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URI;
import java.net.URL;
import javax.help.CSH;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JLabel;

import org.allbinary.logic.communication.log.LogUtil;

public class JavaHelpUtil
{
	private static final JavaHelpUtil instance = new JavaHelpUtil();
    private static final Point point = new Point(0,0);
    private static final Dimension dimension = new Dimension(640, 480);
    
    private static ActionEvent contextSensitiveHelpActionEvent =
        new ActionEvent(new JLabel(), ActionEvent.ACTION_FIRST, null);
    
    /*
     *NavigatorView navigatorView = null;
    private static String navigatorViewName = null;
                if (helpBroker.getHelpSet().getNavigatorView(navigatorViewName) != null)
                {
                    helpBroker.setCurrentView(navigatorViewName);
                    LogUtil.put(LogFactory.getInstance("SetCurrentView", "JavaHelpUtil", "show"));
                }
     */
    
    private JavaHelpUtil()
    {
    }
    
    public static HelpSet getHelpSet(String filePath)
    {
        try
        {
            URI helpSetURI = new File(filePath).toURI();
            URL helpSetURL = helpSetURI.toURL();
            return getHelpSet(helpSetURL);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", instance, "set", e));
            return null;
        }
    }

    public static HelpSet getHelpSet(URL url)
    {
        try
        {
            return new HelpSet(null, url);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", instance, "set", e));
            return null;
        }
    }
    
    public static void show(HelpSet helpSet)
    {
        try
        {
            HelpBroker helpBroker = helpSet.createHelpBroker();
            
            helpBroker.setLocation(point);
            helpBroker.setSize(dimension);
            
            new CSH.DisplayHelpFromSource(helpBroker).actionPerformed(contextSensitiveHelpActionEvent);
            LogUtil.put(LogFactory.getInstance("CSH Action", instance, "show"));
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", instance, "show", e));
        }
    }
    
    public static void main(String args[])
    {
        try
        {
            JavaHelpUtil.show(JavaHelpUtil.getHelpSet(
                "G:/mnt/bc/mydev/working/automation/InputAutomationJavaApplication/AllBinaryInputAutomationHelp/AllBinaryInputAutomation.hs"));
        }
        catch(Exception e)
        {
            //System.out.println("Error: " + e);
        }
    }
}
