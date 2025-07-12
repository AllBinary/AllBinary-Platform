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

import javax.help.HelpSet;
import javax.swing.*;

import org.allbinary.thread.RunnableInterface;

public class InputAutomationModuleFactoryInterface
{
    public String getName() {
        return null;
    }

    public JPanel getConfigurationJPanel() {
        return null;
    }
    
    public HelpSet getHelpSet() {
        return null;
    }
    
    public RunnableInterface getInstance() throws Exception {
        return null;
    }

}
