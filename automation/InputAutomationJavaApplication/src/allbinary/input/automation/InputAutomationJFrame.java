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
package allbinary.input.automation;

import java.awt.Desktop;
import java.net.URL;
import java.net.URI;
import javax.help.HelpSet;
import javax.help.event.HelpSetEvent;
import javax.help.event.HelpSetListener;
import javax.swing.ImageIcon;
import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.java.help.JavaHelpUtil;
import abcs.logic.system.security.licensing.AbeLicenseInterface;
import abcs.logic.system.security.licensing.AbeLicenseInterfaceFactory;
import abcs.logic.system.security.licensing.AbeNoLicense;
import abcs.logic.system.security.licensing.LicensingException;
import allbinary.gui.dialog.BasicTextJDialog;
import allbinary.gui.dialog.ExitCloseListener;
import allbinary.gui.swing.workers.JListSwingWorker;
import allbinary.input.automation.osgi.DesktopBundle;
import allbinary.input.automation.robot.InputRobotFactory;
import allbinary.input.automation.configuration.InputAutomationConfiguration;
import allbinary.input.automation.configuration.InputAutomationConfigurationFactory;
import allbinary.input.automation.configuration.InputAutomationConfigurationModuleChangeEvent;
import allbinary.input.automation.module.configuration.InputAutomationModuleConfigurations;
import allbinary.input.automation.module.InputAutomationModuleFactoryFactory;
import allbinary.input.automation.module.InputAutomationModuleFactoryInterface;
import allbinary.input.automation.module.configuration.InputAutomationModuleConfigurationsSingletonFactory;
import allbinary.input.automation.robot.osgi.InputAutomationRobotChangeEvent;
import allbinary.thread.RunnableInterface;
import bundle.input.automation.InputAutomationBundleActivatorListenerInterface;
import bundle.input.automation.module.configuration.InputAutomationConfigurationModuleChangeListener;
import bundle.input.automation.robot.InputAutomationRobotChangeListener;

public class InputAutomationJFrame extends javax.swing.JFrame implements InputAutomationConfigurationModuleChangeListener, InputAutomationRobotChangeListener, HelpSetListener
{

   private InputAutomationModuleFactoryFactory inputAutomationModuleFactory;
   private InputAutomationModuleFactoryInterface inputAutomationModuleInterface;
   private RunnableInterface runnableInterface;
   private Thread thread;
   private HelpSet helpSet;
   private final URI uri = new URI("http://geocities.com/allbinary/");

   public InputAutomationJFrame() throws Exception
   {
      initComponents();

      //fileJDialog = new FileJDialog();
      //fileJDialog.addFinishedListener(this);
      URL url = this.getClass().getResource("/help/Help.hs");
      LogUtil.put(new Log("URL: " + url, this, "Constructor"));
      helpSet = JavaHelpUtil.getHelpSet(url);

      url = this.getClass().getResource("/resources/allbinaryicon8bit.jpg");
      ImageIcon imageIcon = new ImageIcon(url);
      this.setIconImage(imageIcon.getImage());

      this.init();
   }

   public void helpSetAdded(HelpSetEvent helpSetEvent)
   {
      this.helpSet.add(helpSetEvent.getHelpSet());
   }

   public void helpSetRemoved(HelpSetEvent helpSetEvent)
   {
      this.helpSet.remove(helpSetEvent.getHelpSet());
   }

   private void init() throws Exception
   {
      InputAutomationConfigurationFactory.init();

      this.inputAutomationModuleFactory = new InputAutomationModuleFactoryFactory(this);

      (new JListSwingWorker(this.inputAutomationModuleJList, inputAutomationModuleFactory.getListModel())).execute();

      this.gameRobotJTabbedPane.setEnabledAt(1, false);
      this.gameRobotJTabbedPane.setSelectedIndex(0);
   }

   /*
   public void onFiles(File files[])
   {
   try
   {
   LogUtil.put(new Log("Start", this, "onFiles"));
   for(int index = 0; index < files.length; index++)
   {
   LogUtil.put(new Log("Reading: " + files[index], this, "onFiles"));
   InputAutomationConfiguration inputAutomationConfiguration =
   InputAutomationConfigurationFactory.getInstance();
   InputAutomationModuleConfigurations inputAutomationModuleConfigurations =
   new InputAutomationModuleConfigurations(files[index]);
   Collection collection =
   inputAutomationModuleConfigurations.getHashMap().values();
   Iterator iterator = collection.iterator();
   while(iterator.hasNext())
   {
   inputAutomationConfiguration.add(
   (InputAutomationModuleConfiguration) iterator.next());
   }
   inputAutomationConfiguration.save();
   }
   this.init();
   this.fileJDialog.setVisible(false);
   }
   catch (Exception e)
   {
   LogUtil.put(new Log("Exception", this, "onFiles", e));
   }
   }
    */
   /*
   LogUtil.put(new Log("Nothing", this, "focusGained"));
   if(this.thread != null && this.thread.isAlive())
   {
   //this.thread.join();
   LogUtil.put(new Log("Error Thread Already Running", this, "focusGained"));
   }
   else
   {
   }
    */
   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        noModuleSelectedJDialog = new javax.swing.JDialog();
        noModuleSelectedJLabel = new javax.swing.JLabel();
        gameRobotJTabbedPane = new javax.swing.JTabbedPane();
        inputAutomationModuleJPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputAutomationModuleJList = new javax.swing.JList();
        automationModuleConfigurationJPanel = new javax.swing.JPanel();
        mainJMenuBar = new javax.swing.JMenuBar();
        processingJMenu = new javax.swing.JMenu();
        startJMenuItem = new javax.swing.JMenuItem();
        stopJMenuItem = new javax.swing.JMenuItem();
        optionsJMenu = new javax.swing.JMenu();
        stopOnFocusJCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        toolsJMenu = new javax.swing.JMenu();
        helpJMenuItem = new javax.swing.JMenuItem();
        updatesJMenuItem = new javax.swing.JMenuItem();
        subscriptionJMenuItem = new javax.swing.JMenuItem();
        modulesJMenuItem = new javax.swing.JMenuItem();
        aboutJMenuItem = new javax.swing.JMenuItem();

        noModuleSelectedJDialog.setMinimumSize(new java.awt.Dimension(200, 100));
        noModuleSelectedJLabel.setText("Please Select A Game Module");

        javax.swing.GroupLayout noModuleSelectedJDialogLayout = new javax.swing.GroupLayout(noModuleSelectedJDialog.getContentPane());
        noModuleSelectedJDialog.getContentPane().setLayout(noModuleSelectedJDialogLayout);
        noModuleSelectedJDialogLayout.setHorizontalGroup(
            noModuleSelectedJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noModuleSelectedJDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noModuleSelectedJLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        noModuleSelectedJDialogLayout.setVerticalGroup(
            noModuleSelectedJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(noModuleSelectedJLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(325, 215));
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener()
        {
            public void windowGainedFocus(java.awt.event.WindowEvent evt)
            {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt)
            {
                formWindowLostFocus(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                formFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                formFocusLost(evt);
            }
        });

        gameRobotJTabbedPane.setMinimumSize(new java.awt.Dimension(320, 200));
        inputAutomationModuleJList.setMinimumSize(new java.awt.Dimension(200, 0));
        inputAutomationModuleJList.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                inputAutomationModuleJListValueChanged(evt);
            }
        });

        jScrollPane1.setViewportView(inputAutomationModuleJList);

        javax.swing.GroupLayout inputAutomationModuleJPanelLayout = new javax.swing.GroupLayout(inputAutomationModuleJPanel);
        inputAutomationModuleJPanel.setLayout(inputAutomationModuleJPanelLayout);
        inputAutomationModuleJPanelLayout.setHorizontalGroup(
            inputAutomationModuleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputAutomationModuleJPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(367, Short.MAX_VALUE))
        );
        inputAutomationModuleJPanelLayout.setVerticalGroup(
            inputAutomationModuleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inputAutomationModuleJPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gameRobotJTabbedPane.addTab("Modules", inputAutomationModuleJPanel);

        javax.swing.GroupLayout automationModuleConfigurationJPanelLayout = new javax.swing.GroupLayout(automationModuleConfigurationJPanel);
        automationModuleConfigurationJPanel.setLayout(automationModuleConfigurationJPanelLayout);
        automationModuleConfigurationJPanelLayout.setHorizontalGroup(
            automationModuleConfigurationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
        );
        automationModuleConfigurationJPanelLayout.setVerticalGroup(
            automationModuleConfigurationJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 334, Short.MAX_VALUE)
        );
        gameRobotJTabbedPane.addTab("Configuration", automationModuleConfigurationJPanel);

        processingJMenu.setText("Processing");
        processingJMenu.setToolTipText("Set module processing state");
        startJMenuItem.setText("Start");
        startJMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                startJMenuItemActionPerformed(evt);
            }
        });

        processingJMenu.add(startJMenuItem);

        stopJMenuItem.setText("Stop");
        stopJMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                stopJMenuItemActionPerformed(evt);
            }
        });

        processingJMenu.add(stopJMenuItem);

        mainJMenuBar.add(processingJMenu);

        optionsJMenu.setText("Options");
        optionsJMenu.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                optionsJMenuActionPerformed(evt);
            }
        });

        stopOnFocusJCheckBoxMenuItem.setSelected(true);
        stopOnFocusJCheckBoxMenuItem.setEnabled(false);
        stopOnFocusJCheckBoxMenuItem.setLabel("Stop Module On Focus");
        stopOnFocusJCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                stopOnFocusJCheckBoxMenuItemActionPerformed(evt);
            }
        });

        optionsJMenu.add(stopOnFocusJCheckBoxMenuItem);

        mainJMenuBar.add(optionsJMenu);

        toolsJMenu.setText("Help");
        helpJMenuItem.setText("Help");
        helpJMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                helpJMenuItemActionPerformed(evt);
            }
        });

        toolsJMenu.add(helpJMenuItem);

        updatesJMenuItem.setText("Updates");
        updatesJMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                updatesJMenuItemActionPerformed(evt);
            }
        });

        toolsJMenu.add(updatesJMenuItem);

        subscriptionJMenuItem.setText("Subscription");
        subscriptionJMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                subscriptionJMenuItemActionPerformed(evt);
            }
        });

        toolsJMenu.add(subscriptionJMenuItem);

        modulesJMenuItem.setText("Module Manager");
        modulesJMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                modulesJMenuItemActionPerformed(evt);
            }
        });

        toolsJMenu.add(modulesJMenuItem);

        aboutJMenuItem.setText("About");
        aboutJMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                aboutJMenuItemActionPerformed(evt);
            }
        });

        toolsJMenu.add(aboutJMenuItem);

        mainJMenuBar.add(toolsJMenu);

        setJMenuBar(mainJMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gameRobotJTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gameRobotJTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subscriptionJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_subscriptionJMenuItemActionPerformed
    {//GEN-HEADEREND:event_subscriptionJMenuItemActionPerformed
       try
       {
          Desktop.getDesktop().browse(uri);
       } catch (Exception e)
       {
          LogUtil.put(new Log("Exception", this, "subscriptionJMenuItemActionPerformed", e));
       }
    }//GEN-LAST:event_subscriptionJMenuItemActionPerformed

    private void updatesJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_updatesJMenuItemActionPerformed
    {//GEN-HEADEREND:event_updatesJMenuItemActionPerformed
       try
       {
          Desktop.getDesktop().browse(uri);
       } catch (Exception e)
       {
          LogUtil.put(new Log("Exception", this, "updatesJMenuItemActionPerformed", e));
       }
    }//GEN-LAST:event_updatesJMenuItemActionPerformed

    private void aboutJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_aboutJMenuItemActionPerformed
    {//GEN-HEADEREND:event_aboutJMenuItemActionPerformed
       new BasicTextJDialog("Copyright (c) 2002-2007 AllBinary").setVisible(true);
    }//GEN-LAST:event_aboutJMenuItemActionPerformed

    private void modulesJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_modulesJMenuItemActionPerformed
    {//GEN-HEADEREND:event_modulesJMenuItemActionPerformed
       try
       {
          new DesktopBundle().start();
       } catch (Exception e)
       {
          LogUtil.put(new Log("Exception", this, "modulesJMenuItemActionPerformed", e));
       }
    }//GEN-LAST:event_modulesJMenuItemActionPerformed

   /*
   private void removeModuleJMenuItemActionPerformed(java.awt.event.ActionEvent evt)
   {
   try
   {
   String gameAutomationRobotModuleNameString =
   (String) this.inputAutomationModuleJList.getSelectedValue();
   if(gameAutomationRobotModuleNameString != null)
   {
   InputAutomationModuleInterface inputAutomationModuleInterface =
   this.inputAutomationModuleFactory.getInstance(
   gameAutomationRobotModuleNameString);
   InputAutomationConfiguration inputAutomationConfiguration =
   InputAutomationConfigurationFactory.getInstance();
   inputAutomationConfiguration.remove(inputAutomationModuleInterface);
   this.getAutomationModuleConfigurationJPanel().removeAll();
   this.init();
   }
   }
   catch (Exception e)
   {
   LogUtil.put(new Log("Exception", this, "removeModuleJMenuItemActionPerformed", e));
   }
   }*/
   /*
   private void addModuleJMenuItemActionPerformed(java.awt.event.ActionEvent evt)
   {
   this.fileJDialog.setVisible(true);
   }
    */
    private void helpJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_helpJMenuItemActionPerformed
    {//GEN-HEADEREND:event_helpJMenuItemActionPerformed
       JavaHelpUtil.show(helpSet);
    }//GEN-LAST:event_helpJMenuItemActionPerformed

    private void stopOnFocusJCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_stopOnFocusJCheckBoxMenuItemActionPerformed
    {//GEN-HEADEREND:event_stopOnFocusJCheckBoxMenuItemActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_stopOnFocusJCheckBoxMenuItemActionPerformed

    private void optionsJMenuActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_optionsJMenuActionPerformed
    {//GEN-HEADEREND:event_optionsJMenuActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_optionsJMenuActionPerformed

    private void formWindowLostFocus(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowLostFocus
    {//GEN-HEADEREND:event_formWindowLostFocus
// TODO add your handling code here:
       LogUtil.put(new Log("Nothing", this, "focusLost"));
    }//GEN-LAST:event_formWindowLostFocus

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowGainedFocus
    {//GEN-HEADEREND:event_formWindowGainedFocus
// TODO add your handling code here:
       if (this.stopOnFocusJCheckBoxMenuItem.isSelected())
       {
          LogUtil.put(new Log("Stopping", this, "focusGained"));

          if (this.runnableInterface != null)
          {
             this.runnableInterface.setRunning(false);
          }
       }
    }//GEN-LAST:event_formWindowGainedFocus

    private void formFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_formFocusLost
    {//GEN-HEADEREND:event_formFocusLost
// TODO add your handling code here:
    }//GEN-LAST:event_formFocusLost

    private void formFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_formFocusGained
    {//GEN-HEADEREND:event_formFocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_formFocusGained

    private void inputAutomationModuleJListValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_inputAutomationModuleJListValueChanged
    {//GEN-HEADEREND:event_inputAutomationModuleJListValueChanged
       try
       {
          String gameAutomationRobotModuleNameString = (String) this.inputAutomationModuleJList.getSelectedValue();

          if (gameAutomationRobotModuleNameString != null)
          {
             this.inputAutomationModuleInterface = this.inputAutomationModuleFactory.getInstance(gameAutomationRobotModuleNameString);

             this.getAutomationModuleConfigurationJPanel().removeAll();

             javax.swing.GroupLayout layout = (javax.swing.GroupLayout) this.getAutomationModuleConfigurationJPanel().getLayout();

             layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 395, Short.MAX_VALUE).addComponent(inputAutomationModuleInterface.getConfigurationJPanel(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));
             layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 256, Short.MAX_VALUE).addComponent(inputAutomationModuleInterface.getConfigurationJPanel(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));

             this.gameRobotJTabbedPane.setEnabledAt(1, true);

             LogUtil.put(new Log("Setting Configuration JPanel", this, "gameAutomationRobotModuleJListValueChanged"));
          } else
          {
             this.gameRobotJTabbedPane.setEnabledAt(1, false);
          }
       } catch (Exception e)
       {
          LogUtil.put(new Log("Exception", this, "gameAutomationRobotModuleJListValueChanged", e));
       }
    }//GEN-LAST:event_inputAutomationModuleJListValueChanged

   private void stopJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_stopJMenuItemActionPerformed
   {//GEN-HEADEREND:event_stopJMenuItemActionPerformed

      if (this.runnableInterface != null)
      {
         this.runnableInterface.setRunning(false);
      } else
      {
         this.noModuleSelectedJDialog.setVisible(true);
      }
   }//GEN-LAST:event_stopJMenuItemActionPerformed

   private void startJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_startJMenuItemActionPerformed
   {//GEN-HEADEREND:event_startJMenuItemActionPerformed
      try
      {
         LogUtil.put(new Log("Starting", this, "startJMenuItemActionPerformed"));

         if (inputAutomationModuleInterface != null)
         {
            this.runnableInterface = inputAutomationModuleInterface.getInstance();

            if (this.runnableInterface != null)
            {
               this.thread = new Thread(this.runnableInterface);
               thread.setPriority(Thread.MIN_PRIORITY + 1);
               thread.start();
            } else
            {
               this.noModuleSelectedJDialog.setVisible(true);
            }
         } else
         {
            this.noModuleSelectedJDialog.setVisible(true);
         }
      } catch (Exception e)
      {
         LogUtil.put(new Log("Exception", this, "startJMenuItemActionPerformed", e));
      }
   }//GEN-LAST:event_startJMenuItemActionPerformed
   private static InputAutomationJFrame INPUTAUTOMATION_JFRAME;

   public static InputAutomationJFrame getInstance()
   {
      return INPUTAUTOMATION_JFRAME;
   }

   public static void destroy()
   {
      INPUTAUTOMATION_JFRAME.setVisible(false);
   }

   public static void create(final InputAutomationBundleActivatorListenerInterface inputAutomationBundleActivatorListenerInterface)
   {
      java.awt.EventQueue.invokeLater(new Runnable()
      {

         public void run()
         {
            try
            {
               LogUtil.put(new Log("Running", "Main", "run"));
               INPUTAUTOMATION_JFRAME = new InputAutomationJFrame();
               InputRobotFactory.getInstance().addListener(InputAutomationJFrame.getInstance());

               if (inputAutomationBundleActivatorListenerInterface != null)
               {
                  inputAutomationBundleActivatorListenerInterface.registerAsService();
                  inputAutomationBundleActivatorListenerInterface.useServices();
               }
               INPUTAUTOMATION_JFRAME.setVisible(true);
            } catch (Exception e)
            {
               String error = "Error";
               LogUtil.put(new Log(error, this, "run", e));
            }
         }
      });
   }

   public static void main(String[] args) throws Exception
   {
      InputAutomationJFrame.create(null);
   }

   public javax.swing.JPanel getAutomationModuleConfigurationJPanel()
   {
      return automationModuleConfigurationJPanel;
   }

   public void setAutomationModuleConfigurationJPanel(javax.swing.JPanel automationModuleConfigurationJPanel)
   {
      this.automationModuleConfigurationJPanel = automationModuleConfigurationJPanel;
   }

   public void onAdd(InputAutomationRobotChangeEvent inputAutomationRobotChangeEvent)
   {
      try
      {
         LogUtil.put(new Log("Start", this, "onAdd"));

         InputRobotFactory.getInstance().add(inputAutomationRobotChangeEvent.getInputAutomationRobotInterfaceWrapper().getInputRobotInterface());
      } catch (Exception e)
      {
         LogUtil.put(new Log("Exception", this, "onAdd", e));
      }
   }

   public void onRemove(InputAutomationRobotChangeEvent inputAutomationRobotChangeEvent)
   {
      try
      {
         LogUtil.put(new Log("Start", this, "onRemove"));

         InputRobotFactory.getInstance().add(inputAutomationRobotChangeEvent.getInputAutomationRobotInterfaceWrapper().getInputRobotInterface());
      } catch (Exception e)
      {
         LogUtil.put(new Log("Exception", this, "onRemove", e));
      }
   }

   public void onAdd(InputAutomationConfigurationModuleChangeEvent inputAutomationConfigurationChangeEvent)
   {
      try
      {
         LogUtil.put(new Log("Start", this, "onAdd"));

         InputAutomationConfiguration inputAutomationConfiguration = InputAutomationConfigurationFactory.getInstance();

         InputAutomationModuleConfigurations inputAutomationModuleConfigurations = InputAutomationModuleConfigurationsSingletonFactory.getInstance();

         inputAutomationModuleConfigurations.add(inputAutomationConfigurationChangeEvent.getInputAutomationModuleConfiguration());

         //inputAutomationConfiguration.save();
         this.init();
      } catch (Exception e)
      {
         LogUtil.put(new Log("Exception", this, "onAdd", e));
      }
   }

   public void onRemove(InputAutomationConfigurationModuleChangeEvent inputAutomationConfigurationChangeEvent)
   {
      try
      {
         LogUtil.put(new Log("Start", this, "onRemove"));

         InputAutomationConfiguration inputAutomationConfiguration = InputAutomationConfigurationFactory.getInstance();

         InputAutomationModuleConfigurations inputAutomationModuleConfigurations = InputAutomationModuleConfigurationsSingletonFactory.getInstance();

         inputAutomationModuleConfigurations.remove(inputAutomationConfigurationChangeEvent.getInputAutomationModuleConfiguration());

         //inputAutomationConfiguration.save();
         this.init();
      } catch (Exception e)
      {
         LogUtil.put(new Log("Exception", this, "onRemove", e));
      }
   }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutJMenuItem;
    private javax.swing.JPanel automationModuleConfigurationJPanel;
    private javax.swing.JTabbedPane gameRobotJTabbedPane;
    private javax.swing.JMenuItem helpJMenuItem;
    private javax.swing.JList inputAutomationModuleJList;
    private javax.swing.JPanel inputAutomationModuleJPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar mainJMenuBar;
    private javax.swing.JMenuItem modulesJMenuItem;
    private javax.swing.JDialog noModuleSelectedJDialog;
    private javax.swing.JLabel noModuleSelectedJLabel;
    private javax.swing.JMenu optionsJMenu;
    private javax.swing.JMenu processingJMenu;
    private javax.swing.JMenuItem startJMenuItem;
    private javax.swing.JMenuItem stopJMenuItem;
    private javax.swing.JCheckBoxMenuItem stopOnFocusJCheckBoxMenuItem;
    private javax.swing.JMenuItem subscriptionJMenuItem;
    private javax.swing.JMenu toolsJMenu;
    private javax.swing.JMenuItem updatesJMenuItem;
    // End of variables declaration//GEN-END:variables
}