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
package org.allbinary.input.automation.osgi;

import javax.swing.*;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import bundle.input.automation.InputAutomationBundleActivator;
import org.allbinary.gui.dialog.BasicTextJDialog;

public class DesktopBundle
{
    private static final String DESKTOP_SYMBOLIC_NAME = 
        "org.knopflerfish.bundle.desktop";
    
    private Bundle bundle;
    
    public DesktopBundle()
    {
        BundleContext bundleContext = 
            InputAutomationBundleActivator.getBundleContext();
        
        if(bundleContext != null)
        {
            Bundle[] bundleArray = bundleContext.getBundles();
            
            for (int index = 0; index < bundleArray.length; index++) {
                Bundle bundle = bundleArray[index];
                String symbolicName = bundle.getSymbolicName();
                if (symbolicName.compareTo(DESKTOP_SYMBOLIC_NAME) == 0) {
                    this.bundle = bundle;
                }
            }
        }
        else
        {
            JDialog textJDialog = new BasicTextJDialog("BundlContext is Null");
            textJDialog.setVisible(true);
            //textJDialog.setLocationRelativeTo(this);
        }
    }
    
    public void start()
        throws Exception
    {
        this.bundle.start();
    }
}
