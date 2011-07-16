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
package allbinary.input.automation.module;

import javax.help.HelpSet;

import javax.swing.JPanel;

import allbinary.thread.RunnableInterface;

public interface InputAutomationModuleFactoryInterface
{
    String getName();
    JPanel getConfigurationJPanel();
    HelpSet getHelpSet();
    RunnableInterface getInstance() throws Exception;
}
