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

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.help.CSH;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.*;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class JavaHelpUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final JavaHelpUtil instance = new JavaHelpUtil();

    /**
     * @return the instance
     */
    public static JavaHelpUtil getInstance() {
        return instance;
    }

    private final Point point = new Point(0,0);
    private final Dimension dimension = new Dimension(640, 480);
    
    private ActionEvent contextSensitiveHelpActionEvent =
        new ActionEvent(new JLabel(), ActionEvent.ACTION_FIRST, null);

    protected final CommonStrings commonStrings = CommonStrings.getInstance();    

    /*
     *NavigatorView navigatorView = null;
    private String navigatorViewName = null;
                if (helpBroker.getHelpSet().getNavigatorView(navigatorViewName) != null)
                {
                    helpBroker.setCurrentView(navigatorViewName);
                    logUtil.put("SetCurrentView", this, "show");
                }
     */
    
    private JavaHelpUtil()
    {
    }
    
    public HelpSet getHelpSet(String filePath)
    {
        try
        {
            URI helpSetURI = new File(filePath).toURI();
            URL helpSetURL = helpSetURI.toURL();
            return getHelpSet(helpSetURL);
        }
        catch (Exception e)
        {
            logUtil.put(this.commonStrings.EXCEPTION, getInstance(), "set", e);
            return null;
        }
    }

    public HelpSet getHelpSet(URL url)
    {
        try
        {
            return new HelpSet(null, url);
        }
        catch (Exception e)
        {
            logUtil.put(this.commonStrings.EXCEPTION, getInstance(), "set", e);
            return null;
        }
    }
    
    public void show(HelpSet helpSet)
    {
        try
        {
            HelpBroker helpBroker = helpSet.createHelpBroker();
            
            helpBroker.setLocation(point);
            helpBroker.setSize(dimension);
            
            new CSH.DisplayHelpFromSource(helpBroker).actionPerformed(contextSensitiveHelpActionEvent);
            logUtil.put("CSH Action", getInstance(), "show");
        }
        catch (Exception e)
        {
            logUtil.put(this.commonStrings.EXCEPTION, getInstance(), "show", e);
        }
    }
    
    public static void main(String args[])
    {
        try
        {
            JavaHelpUtil javaHelpUtil = JavaHelpUtil.getInstance();
            javaHelpUtil.show(javaHelpUtil.getHelpSet(
                "G:/mnt/bc/mydev/working/automation/InputAutomationJavaApplication/AllBinaryInputAutomationHelp/AllBinaryInputAutomation.hs"));
        }
        catch(Exception e)
        {
            //System.out.println("Error: " + e);
        }
    }
}
