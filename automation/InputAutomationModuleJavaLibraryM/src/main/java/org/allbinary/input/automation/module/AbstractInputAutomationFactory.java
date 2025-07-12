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
package org.allbinary.input.automation.module;

import java.net.URL;

import javax.help.HelpSet;
import javax.swing.*;

import org.allbinary.logic.java.help.JavaHelpUtil;

public class AbstractInputAutomationFactory extends InputAutomationModuleFactoryInterface
{
    private String name;
    private JPanel jPanel;

    private HelpSet helpSet;
    
    public AbstractInputAutomationFactory(
        String name, JPanel jPanel, HelpSet helpSet)
    {
        this.name = name;
        this.jPanel = jPanel;
        this.helpSet = helpSet;
    }
    
    public AbstractInputAutomationFactory(
        String name, JPanel jPanel)
    {        
        this.name = name;
        this.jPanel = jPanel;
        
        URL url = this.getClass().getResource("/help/Help.hs");
        this.helpSet = JavaHelpUtil.getInstance().getHelpSet(url);
    }
   
    public String getName()
    {
        return name;
    }
    
    public JPanel getConfigurationJPanel()
    {
        return this.jPanel;
    }
    
    public HelpSet getHelpSet()
    {
        return helpSet;
    }
    
}
