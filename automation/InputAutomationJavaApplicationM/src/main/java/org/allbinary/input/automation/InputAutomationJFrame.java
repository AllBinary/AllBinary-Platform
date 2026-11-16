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
package org.allbinary.input.automation;
import org.allbinary.thread.ARunnable;


import java.net.URI;
import java.net.URL;
import java.awt.Desktop;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.help.HelpSet;
import javax.help.event.HelpSetEvent;
import javax.help.event.HelpSetListener;

import bundle.input.automation.InputAutomationBundleActivatorListenerInterface;
import bundle.input.automation.module.configuration.InputAutomationConfigurationModuleChangeListener;
import bundle.input.automation.robot.InputAutomationRobotChangeListener;

import org.allbinary.globals.AppUrlGlobals;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.gui.dialog.BasicTextJDialog;
import org.allbinary.gui.swing.workers.JListSwingWorker;
import org.allbinary.input.automation.configuration.InputAutomationConfiguration;
import org.allbinary.input.automation.configuration.InputAutomationConfigurationFactory;
import org.allbinary.input.automation.configuration.InputAutomationConfigurationModuleChangeEvent;
import org.allbinary.input.automation.module.InputAutomationModuleFactoryFactory;
import org.allbinary.input.automation.module.InputAutomationModuleFactoryInterface;
import org.allbinary.input.automation.module.configuration.InputAutomationModuleConfiguration;
import org.allbinary.input.automation.module.configuration.InputAutomationModuleConfigurations;
import org.allbinary.input.automation.osgi.DesktopBundle;
import org.allbinary.input.automation.robot.InputRobotFactory;
import org.allbinary.input.automation.robot.osgi.InputAutomationRobotChangeEvent;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.java.help.JavaHelpUtil;
import org.allbinary.logic.system.loader.WebappClassLoaderInfo;
import org.allbinary.logic.system.security.licensing.InputAutomationClientInformationFactory;
import org.allbinary.string.CommonStrings;
import org.allbinary.thread.RunnableInterface;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.GroupLayout;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class InputAutomationJFrame extends JFrame implements InputAutomationConfigurationModuleChangeListener, InputAutomationRobotChangeListener, HelpSetListener
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();

   private InputAutomationModuleConfigurations inputAutomationModuleConfigurations = new InputAutomationModuleConfigurations();
   private InputAutomationModuleFactoryFactory inputAutomationModuleFactory;
   private InputAutomationModuleFactoryInterface inputAutomationModuleInterface;
   private RunnableInterface runnableInterface;
   private Thread thread;
   private HelpSet helpSet;
   private final URI uri = new URI("http://geocities.com/allbinary/");

   public InputAutomationJFrame() throws Exception
   {
      this.initComponents();

      //fileJDialog = new FileJDialog();
      //fileJDialog.addFinishedListener(this);
      URL url = this.getClass().getResource("/help/Help.hs");
      this.logUtil.putF("URL: " + url, this, this.commonStrings.CONSTRUCTOR);
      this.helpSet = JavaHelpUtil.getInstance().getHelpSet(url);

      url = this.getClass().getResource("/resources/allbinaryicon8bit.jpg");
      final ImageIcon imageIcon = new ImageIcon(url);
      this.setIconImage(imageIcon.getImage());

      this.init();
   }

   public void helpSetAdded(final HelpSetEvent helpSetEvent)
   {
      this.helpSet.add(helpSetEvent.getHelpSet());
   }

   public void helpSetRemoved(final HelpSetEvent helpSetEvent)
   {
      this.helpSet.remove(helpSetEvent.getHelpSet());
   }
   
   private void init() throws Exception
   {
      final InputAutomationConfigurationFactory inputAutomationConfigurationFactory = InputAutomationConfigurationFactory.getInstance();
      inputAutomationConfigurationFactory.init(InputAutomationClientInformationFactory.getInstance());
      final List<InputAutomationModuleConfiguration> inputAutomationModuleConfigurationList = inputAutomationConfigurationFactory.inputAutomationConfiguration.getInputAutomationModuleConfigurationList();
      if(inputAutomationModuleConfigurationList == null) {
          //No modules from configuration loaded.
      } else {
          this.inputAutomationModuleConfigurations = new InputAutomationModuleConfigurations(inputAutomationModuleConfigurationList);
      }

      this.inputAutomationModuleFactory = new InputAutomationModuleFactoryFactory(inputAutomationModuleConfigurations, this);

      (new JListSwingWorker(this.inputAutomationModuleJList, this.inputAutomationModuleFactory.getListModel())).execute();

      this.gameRobotJTabbedPane.setEnabledAt(1, false);
      this.gameRobotJTabbedPane.setSelectedIndex(0);
   }

   /*
   public void onFiles(File files[])
   {
   try
   {
   this.logUtil.putF(this.commonStrings.START, this, "onFiles");
   for(int index = 0; index < files.length; index++)
   {
   this.logUtil.putF("Reading: " + files[index], this, "onFiles");
   InputAutomationConfiguration inputAutomationConfiguration =
   InputAutomationConfigurationFactory.getInstance();
   InputAutomationModuleConfigurations inputAutomationModuleConfigurations =
   new InputAutomationModuleConfigurations(files[index]);
   Collection collection =
   inputAutomationModuleConfigurations.getHashMap().values();
   iterator = collection;
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
   this.logUtil.put(this.commonStrings.EXCEPTION, this, "onFiles", e);
   }
   }
    */
   /*
   this.logUtil.putF("Nothing", this, "focusGained");
   if(this.thread != null && this.thread.isAlive())
   {
   //this.thread.join();
   this.logUtil.putF("Error Thread Already Running", this, "focusGained");
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
        this.noModuleSelectedJDialog = new JDialog();
        this.noModuleSelectedJLabel = new JLabel();
        this.gameRobotJTabbedPane = new JTabbedPane();
        this.inputAutomationModuleJPanel = new JPanel();
        this.jScrollPane1 = new JScrollPane();
        this.inputAutomationModuleJList = new JList();
        this.automationModuleConfigurationJPanel = new JPanel();
        this.mainJMenuBar = new JMenuBar();
        this.processingJMenu = new JMenu();
        this.startJMenuItem = new JMenuItem();
        this.stopJMenuItem = new JMenuItem();
        this.optionsJMenu = new JMenu();
        this.stopOnFocusJCheckBoxMenuItem = new JCheckBoxMenuItem();
        this.toolsJMenu = new JMenu();
        this.helpJMenuItem = new JMenuItem();
        this.updatesJMenuItem = new JMenuItem();
        this.subscriptionJMenuItem = new JMenuItem();
        this.modulesJMenuItem = new JMenuItem();
        this.aboutJMenuItem = new JMenuItem();

        this.noModuleSelectedJDialog.setMinimumSize(new Dimension(200, 100));
        this.noModuleSelectedJLabel.setText("Please Select A Module");

        GroupLayout noModuleSelectedJDialogLayout = new GroupLayout(this.noModuleSelectedJDialog.getContentPane());
        this.noModuleSelectedJDialog.getContentPane().setLayout(noModuleSelectedJDialogLayout);
        noModuleSelectedJDialogLayout.setHorizontalGroup(
            noModuleSelectedJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(noModuleSelectedJDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noModuleSelectedJLabel)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        noModuleSelectedJDialogLayout.setVerticalGroup(
            noModuleSelectedJDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(noModuleSelectedJLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(325, 215));
        setResizable(false);
        addWindowFocusListener(new WindowFocusListener()
        {
            public void windowGainedFocus(WindowEvent evt)
            {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(WindowEvent evt)
            {
                formWindowLostFocus(evt);
            }
        });
        addFocusListener(new FocusAdapter()
        {
            public void focusGained(FocusEvent evt)
            {
                formFocusGained(evt);
            }
            public void focusLost(FocusEvent evt)
            {
                formFocusLost(evt);
            }
        });

        this.gameRobotJTabbedPane.setMinimumSize(new Dimension(320, 200));
        this.inputAutomationModuleJList.setMinimumSize(new Dimension(200, 0));
        this.inputAutomationModuleJList.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent evt)
            {
                inputAutomationModuleJListValueChanged(evt);
            }
        });

        this.jScrollPane1.setViewportView(this.inputAutomationModuleJList);

        GroupLayout inputAutomationModuleJPanelLayout = new GroupLayout(this.inputAutomationModuleJPanel);
        this.inputAutomationModuleJPanel.setLayout(inputAutomationModuleJPanelLayout);
        inputAutomationModuleJPanelLayout.setHorizontalGroup(
            inputAutomationModuleJPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(inputAutomationModuleJPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(367, Short.MAX_VALUE))
        );
        inputAutomationModuleJPanelLayout.setVerticalGroup(
            inputAutomationModuleJPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(inputAutomationModuleJPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        this.gameRobotJTabbedPane.addTab("Modules", inputAutomationModuleJPanel);

        GroupLayout automationModuleConfigurationJPanelLayout = new GroupLayout(this.automationModuleConfigurationJPanel);
        this.automationModuleConfigurationJPanel.setLayout(automationModuleConfigurationJPanelLayout);
        automationModuleConfigurationJPanelLayout.setHorizontalGroup(
            automationModuleConfigurationJPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
        );
        automationModuleConfigurationJPanelLayout.setVerticalGroup(
            automationModuleConfigurationJPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 334, Short.MAX_VALUE)
        );
        this.gameRobotJTabbedPane.addTab("Configuration", automationModuleConfigurationJPanel);

        this.processingJMenu.setText("Processing");
        this.processingJMenu.setToolTipText("Set module processing state");
        this.startJMenuItem.setText(this.commonStrings.START);
        this.startJMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                startJMenuItemActionPerformed(evt);
            }
        });

        this.processingJMenu.add(this.startJMenuItem);

        this.stopJMenuItem.setText("Stop");
        this.stopJMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                stopJMenuItemActionPerformed(evt);
            }
        });

        this.processingJMenu.add(this.stopJMenuItem);

        this.mainJMenuBar.add(this.processingJMenu);

        this.optionsJMenu.setText("Options");
        this.optionsJMenu.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                optionsJMenuActionPerformed(evt);
            }
        });

        this.stopOnFocusJCheckBoxMenuItem.setSelected(true);
        this.stopOnFocusJCheckBoxMenuItem.setEnabled(false);
        this.stopOnFocusJCheckBoxMenuItem.setLabel("Stop Module On Focus");
        this.stopOnFocusJCheckBoxMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                stopOnFocusJCheckBoxMenuItemActionPerformed(evt);
            }
        });

        this.optionsJMenu.add(this.stopOnFocusJCheckBoxMenuItem);

        this.mainJMenuBar.add(this.optionsJMenu);

        this.toolsJMenu.setText("Help");
        this.helpJMenuItem.setText("Help");
        this.helpJMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                helpJMenuItemActionPerformed(evt);
            }
        });

        this.toolsJMenu.add(this.helpJMenuItem);

        this.updatesJMenuItem.setText("Updates");
        this.updatesJMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                updatesJMenuItemActionPerformed(evt);
            }
        });

        this.toolsJMenu.add(this.updatesJMenuItem);

        this.subscriptionJMenuItem.setText("Subscription");
        this.subscriptionJMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                subscriptionJMenuItemActionPerformed(evt);
            }
        });

        this.toolsJMenu.add(this.subscriptionJMenuItem);

        this.modulesJMenuItem.setText("Module Manager");
        this.modulesJMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                modulesJMenuItemActionPerformed(evt);
            }
        });

        this.toolsJMenu.add(this.modulesJMenuItem);

        this.aboutJMenuItem.setText("About");
        this.aboutJMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                aboutJMenuItemActionPerformed(evt);
            }
        });

        this.toolsJMenu.add(this.aboutJMenuItem);

        this.mainJMenuBar.add(this.toolsJMenu);

        setJMenuBar(this.mainJMenuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(gameRobotJTabbedPane, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(gameRobotJTabbedPane, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subscriptionJMenuItemActionPerformed(ActionEvent evt)//GEN-FIRST:event_subscriptionJMenuItemActionPerformed
    {//GEN-HEADEREND:event_subscriptionJMenuItemActionPerformed
       try
       {
          Desktop.getDesktop().browse(this.uri);
       } catch (Exception e)
       {
          this.logUtil.put(this.commonStrings.EXCEPTION, this, "subscriptionJMenuItemActionPerformed", e);
       }
    }//GEN-LAST:event_subscriptionJMenuItemActionPerformed

    private void updatesJMenuItemActionPerformed(ActionEvent evt)//GEN-FIRST:event_updatesJMenuItemActionPerformed
    {//GEN-HEADEREND:event_updatesJMenuItemActionPerformed
       try
       {
          Desktop.getDesktop().browse(this.uri);
       } catch (Exception e)
       {
          this.logUtil.put(this.commonStrings.EXCEPTION, this, "updatesJMenuItemActionPerformed", e);
       }
    }//GEN-LAST:event_updatesJMenuItemActionPerformed

    private void aboutJMenuItemActionPerformed(ActionEvent evt)//GEN-FIRST:event_aboutJMenuItemActionPerformed
    {//GEN-HEADEREND:event_aboutJMenuItemActionPerformed
       JDialog textJDialog = new BasicTextJDialog("Copyright (c) 2002-2007 AllBinary");
       textJDialog.setVisible(true);
       textJDialog.setLocationRelativeTo(this);
    }//GEN-LAST:event_aboutJMenuItemActionPerformed

    private void modulesJMenuItemActionPerformed(ActionEvent evt)//GEN-FIRST:event_modulesJMenuItemActionPerformed
    {//GEN-HEADEREND:event_modulesJMenuItemActionPerformed
       try
       {
          new DesktopBundle().start();
       } catch (Exception e)
       {
          this.logUtil.put(this.commonStrings.EXCEPTION, this, "modulesJMenuItemActionPerformed", e);
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
   this.logUtil.put(this.commonStrings.EXCEPTION, this, "removeModuleJMenuItemActionPerformed", e);
   }
   }*/
   /*
   private void addModuleJMenuItemActionPerformed(java.awt.event.ActionEvent evt)
   {
   this.fileJDialog.setVisible(true);
   }
    */
    private void helpJMenuItemActionPerformed(ActionEvent evt)//GEN-FIRST:event_helpJMenuItemActionPerformed
    {//GEN-HEADEREND:event_helpJMenuItemActionPerformed
       JavaHelpUtil.getInstance().show(this.helpSet);
    }//GEN-LAST:event_helpJMenuItemActionPerformed

    private void stopOnFocusJCheckBoxMenuItemActionPerformed(ActionEvent evt)//GEN-FIRST:event_stopOnFocusJCheckBoxMenuItemActionPerformed
    {//GEN-HEADEREND:event_stopOnFocusJCheckBoxMenuItemActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_stopOnFocusJCheckBoxMenuItemActionPerformed

    private void optionsJMenuActionPerformed(ActionEvent evt)//GEN-FIRST:event_optionsJMenuActionPerformed
    {//GEN-HEADEREND:event_optionsJMenuActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_optionsJMenuActionPerformed

    private void formWindowLostFocus(WindowEvent evt)//GEN-FIRST:event_formWindowLostFocus
    {//GEN-HEADEREND:event_formWindowLostFocus
// TODO add your handling code here:
       this.logUtil.putF("Nothing", this, "focusLost");
    }//GEN-LAST:event_formWindowLostFocus

    private void formWindowGainedFocus(WindowEvent evt)//GEN-FIRST:event_formWindowGainedFocus
    {//GEN-HEADEREND:event_formWindowGainedFocus
// TODO add your handling code here:
       if (this.stopOnFocusJCheckBoxMenuItem.isSelected())
       {
          this.logUtil.putF("Stopping", this, "focusGained");

          if (this.runnableInterface != null)
          {
             this.runnableInterface.setRunning(false);
          }
       }
    }//GEN-LAST:event_formWindowGainedFocus

    private void formFocusLost(FocusEvent evt)//GEN-FIRST:event_formFocusLost
    {//GEN-HEADEREND:event_formFocusLost
// TODO add your handling code here:
    }//GEN-LAST:event_formFocusLost

    private void formFocusGained(FocusEvent evt)//GEN-FIRST:event_formFocusGained
    {//GEN-HEADEREND:event_formFocusGained
// TODO add your handling code here:
    }//GEN-LAST:event_formFocusGained

    private void inputAutomationModuleJListValueChanged(ListSelectionEvent evt)//GEN-FIRST:event_inputAutomationModuleJListValueChanged
    {//GEN-HEADEREND:event_inputAutomationModuleJListValueChanged
       try
       {
          String gameAutomationRobotModuleNameString = (String) this.inputAutomationModuleJList.getSelectedValue();

          if (gameAutomationRobotModuleNameString != null)
          {
             this.inputAutomationModuleInterface = this.inputAutomationModuleFactory.getInstance(gameAutomationRobotModuleNameString);

             this.getAutomationModuleConfigurationJPanel().removeAll();

             GroupLayout layout = (GroupLayout) this.getAutomationModuleConfigurationJPanel().getLayout();

             layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 395, Short.MAX_VALUE).addComponent(this.inputAutomationModuleInterface.getConfigurationJPanel(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
             layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 256, Short.MAX_VALUE).addComponent(this.inputAutomationModuleInterface.getConfigurationJPanel(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));

             this.gameRobotJTabbedPane.setEnabledAt(1, true);

             this.logUtil.putF("Setting Module: " + gameAutomationRobotModuleNameString + " and Configuration JPanel", this, "gameAutomationRobotModuleJListValueChanged");
          } else
          {
              this.logUtil.putF("Module name was null", this, "gameAutomationRobotModuleJListValueChanged");
             this.gameRobotJTabbedPane.setEnabledAt(1, false);
          }
       } catch (Exception e)
       {
          this.logUtil.put(this.commonStrings.EXCEPTION, this, "gameAutomationRobotModuleJListValueChanged", e);
       }
    }//GEN-LAST:event_inputAutomationModuleJListValueChanged

   private void stopJMenuItemActionPerformed(ActionEvent evt)//GEN-FIRST:event_stopJMenuItemActionPerformed
   {//GEN-HEADEREND:event_stopJMenuItemActionPerformed

      if (this.runnableInterface != null)
      {
         this.runnableInterface.setRunning(false);
      } else
      {
         this.noModuleSelectedJDialog.setVisible(true);
         this.noModuleSelectedJDialog.setLocationRelativeTo(this);
      }
   }//GEN-LAST:event_stopJMenuItemActionPerformed

   private void startJMenuItemActionPerformed(ActionEvent evt)//GEN-FIRST:event_startJMenuItemActionPerformed
   {//GEN-HEADEREND:event_startJMenuItemActionPerformed
      try
      {
         this.logUtil.putF("Starting", this, "startJMenuItemActionPerformed");

         if (this.inputAutomationModuleInterface != null)
         {
            this.runnableInterface = this.inputAutomationModuleInterface.getInstance();

            if (this.runnableInterface != null)
            {
               this.thread = new Thread(this.runnableInterface);
               this.thread.setPriority(Thread.MIN_PRIORITY + 1);
               this.thread.start();
            } else
            {
                this.logUtil.putF("Runnnable was null", this, "startJMenuItemActionPerformed");
                
               this.noModuleSelectedJDialog.setVisible(true);
               this.noModuleSelectedJDialog.setLocationRelativeTo(this);
            }
         } else
         {
             this.logUtil.putF("InputAutomationModuleInterface was null", this, "startJMenuItemActionPerformed");

            this.noModuleSelectedJDialog.setVisible(true);
            this.noModuleSelectedJDialog.setLocationRelativeTo(this);
         }
      } catch (Exception e)
      {
         this.logUtil.put(this.commonStrings.EXCEPTION, this, "startJMenuItemActionPerformed", e);
      }
   }//GEN-LAST:event_startJMenuItemActionPerformed
   private static InputAutomationJFrame INPUTAUTOMATION_JFRAME;

   public static InputAutomationJFrame getInstance()
   {
      return InputAutomationJFrame.INPUTAUTOMATION_JFRAME;
   }

   public static void destroy()
   {
      InputAutomationJFrame.INPUTAUTOMATION_JFRAME.setVisible(false);
   }

   public static void create(final InputAutomationBundleActivatorListenerInterface inputAutomationBundleActivatorListenerInterface)
   {
       LogConfigTypeFactory.getInstance();
       final AppUrlGlobals appUrlGlobals = new AppUrlGlobals();
       appUrlGlobals.setWebappPath("G:/mnt/bc/mydev/work_automation/automation/run/");
       URLGLOBALS.init(appUrlGlobals);
       WebappClassLoaderInfo.setLoader(appUrlGlobals.getClass().getClassLoader());

      EventQueue.invokeLater(new ARunnable()
      {

         public void run()
         {
             final LogUtil logUtil = LogUtil.getInstance();
             final CommonStrings commonStrings = CommonStrings.getInstance();

            try
            {
               logUtil.putF("Running", this, commonStrings.RUN);
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
               logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
            }
         }
      });
   }

   public static void main(final String[] args) throws Exception
   {
      InputAutomationJFrame.create(null);
   }

   public JPanel getAutomationModuleConfigurationJPanel()
   {
      return this.automationModuleConfigurationJPanel;
   }

   public void setAutomationModuleConfigurationJPanel(final JPanel automationModuleConfigurationJPanel)
   {
      this.automationModuleConfigurationJPanel = automationModuleConfigurationJPanel;
   }

   public void onAdd(final InputAutomationRobotChangeEvent inputAutomationRobotChangeEvent)
   {
      try
      {
         this.logUtil.putF(this.commonStrings.START, this, "onAdd");

         InputRobotFactory.getInstance().add(inputAutomationRobotChangeEvent.getInputAutomationRobotInterfaceWrapper().getInputRobotInterface());
      } catch (Exception e)
      {
         this.logUtil.put(this.commonStrings.EXCEPTION, this, "onAdd", e);
      }
   }

   public void onRemove(final InputAutomationRobotChangeEvent inputAutomationRobotChangeEvent)
   {
      try
      {
         this.logUtil.putF(this.commonStrings.START, this, "onRemove");

         InputRobotFactory.getInstance().add(inputAutomationRobotChangeEvent.getInputAutomationRobotInterfaceWrapper().getInputRobotInterface());
      } catch (Exception e)
      {
         this.logUtil.put(this.commonStrings.EXCEPTION, this, "onRemove", e);
      }
   }

   public void onAdd(final InputAutomationConfigurationModuleChangeEvent inputAutomationConfigurationChangeEvent)
   {
      try
      {
         this.logUtil.putF(this.commonStrings.START, this, "onAdd");

         this.inputAutomationModuleConfigurations.add(inputAutomationConfigurationChangeEvent.getInputAutomationModuleConfiguration());

         //inputAutomationConfiguration.save();
         this.init();
      } catch (Exception e)
      {
         this.logUtil.put(this.commonStrings.EXCEPTION, this, "onAdd", e);
      }
   }

   public void onRemove(final InputAutomationConfigurationModuleChangeEvent inputAutomationConfigurationChangeEvent)
   {
      try
      {
         this.logUtil.putF(this.commonStrings.START, this, "onRemove");
         
         final InputAutomationConfiguration inputAutomationConfiguration = InputAutomationConfigurationFactory.getInstance().inputAutomationConfiguration;
         
            final List<InputAutomationModuleConfiguration> inputAutomationModuleConfigurationList = inputAutomationConfiguration.getInputAutomationModuleConfigurationList();
            if(inputAutomationModuleConfigurationList == null) {
                final LogUtil logUtil = LogUtil.getInstance();
                final CommonStrings commonStrings = CommonStrings.getInstance();
                logUtil.putF("inputAutomationModuleConfigurationList: " + inputAutomationModuleConfigurationList, this, commonStrings.INIT);
                throw new Exception();
            }
         
         final InputAutomationModuleConfigurations inputAutomationModuleConfigurations = new InputAutomationModuleConfigurations(inputAutomationConfiguration.getInputAutomationModuleConfigurationList());

         inputAutomationModuleConfigurations.remove(inputAutomationConfigurationChangeEvent.getInputAutomationModuleConfiguration());

         //inputAutomationConfiguration.save();
         this.init();
      } catch (Exception e)
      {
         this.logUtil.put(this.commonStrings.EXCEPTION, this, "onRemove", e);
      }
   }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JMenuItem aboutJMenuItem;
    private JPanel automationModuleConfigurationJPanel;
    private JTabbedPane gameRobotJTabbedPane;
    private JMenuItem helpJMenuItem;
    private JList inputAutomationModuleJList;
    private JPanel inputAutomationModuleJPanel;
    private JScrollPane jScrollPane1;
    private JMenuBar mainJMenuBar;
    private JMenuItem modulesJMenuItem;
    private JDialog noModuleSelectedJDialog;
    private JLabel noModuleSelectedJLabel;
    private JMenu optionsJMenu;
    private JMenu processingJMenu;
    private JMenuItem startJMenuItem;
    private JMenuItem stopJMenuItem;
    private JCheckBoxMenuItem stopOnFocusJCheckBoxMenuItem;
    private JMenuItem subscriptionJMenuItem;
    private JMenu toolsJMenu;
    private JMenuItem updatesJMenuItem;
    // End of variables declaration//GEN-END:variables
}
