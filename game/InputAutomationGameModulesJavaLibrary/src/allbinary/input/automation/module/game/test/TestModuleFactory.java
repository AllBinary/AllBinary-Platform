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
package allbinary.input.automation.module.game.test;

import allbinary.input.automation.module.AbstractInputAutomationFactory;
import javax.swing.JPanel;
import allbinary.input.automation.module.configuration.BlankModuleConfigurationJPanel;
import allbinary.input.automation.module.InputAutomationModuleData;
import allbinary.input.automation.module.InputAutomationModuleFactoryInterface;

import allbinary.thread.RunnableInterface;

public class TestModuleFactory
    extends AbstractInputAutomationFactory
    implements InputAutomationModuleFactoryInterface
{
   private static String NAME = "Test New Game" + InputAutomationModuleData.MODULE_NAME_END;

   private BlankModuleConfigurationJPanel blankModuleConfigurationJPanel;
   
   public TestModuleFactory()
   {
       super(NAME, new BlankModuleConfigurationJPanel());
   }

   public String getName()
   {
       return NAME;
   }
   
    public JPanel getConfigurationJPanel()
    {
        return this.blankModuleConfigurationJPanel;
    }
   
   public synchronized RunnableInterface getInstance() throws Exception
   {
      return null;
   }
}
