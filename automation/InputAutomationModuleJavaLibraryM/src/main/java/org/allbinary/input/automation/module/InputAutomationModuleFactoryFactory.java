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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Collection;
import javax.help.HelpSet;
import javax.help.event.HelpSetEvent;
import javax.help.event.HelpSetListener;
import javax.swing.ListModel;

import org.allbinary.input.automation.module.configuration.InputAutomationModuleConfiguration;
import org.allbinary.input.automation.module.configuration.InputAutomationModuleConfigurations;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.help.JavaHelpSetNotifier;
import org.allbinary.input.automation.module.configuration.InputAutomationModuleConfigurationsSingletonFactory;
import org.allbinary.logic.communication.log.LogFactory;

public class InputAutomationModuleFactoryFactory
{
    private HashMap hashMap;
    private DefaultListModelHelper defaultListModelHelper;
    private HelpSetListener helpSetListenerInterface;
    
    public InputAutomationModuleFactoryFactory(final HelpSetListener helpSetListenerInterface)
    throws Exception
    {
        this.helpSetListenerInterface = helpSetListenerInterface;
        this.defaultListModelHelper = new DefaultListModelHelper();
        
        this.hashMap = new HashMap();
        
        final InputAutomationModuleConfigurations inputAutomationModuleConfigurations = InputAutomationModuleConfigurationsSingletonFactory.getInstance();

        final Collection collection = inputAutomationModuleConfigurations.getHashMap().values();
        final Iterator iterator = collection.iterator();
        InputAutomationModuleConfiguration inputAutomationModuleConfiguration;
        while(iterator.hasNext())
        {
            inputAutomationModuleConfiguration = (InputAutomationModuleConfiguration) iterator.next();
            
            this.add(inputAutomationModuleConfiguration);
        }
        
        this.defaultListModelHelper.initDefaultModelList();
        LogUtil.put(LogFactory.getInstance("Loaded " + this.hashMap.size() + "/" + this.defaultListModelHelper.getListModel().getSize() + " Input Automation Modules", this,"Contructor"));
    }
    
    private void add(final InputAutomationModuleConfiguration inputAutomationModuleConfiguration)
    {
        final InputAutomationModuleFactoryInterface inputAutomationModuleInterface =
            inputAutomationModuleConfiguration.getInputAutomationModuleInterface();

        this.hashMap.put(inputAutomationModuleConfiguration.getName(),inputAutomationModuleInterface);
        this.defaultListModelHelper.add(inputAutomationModuleConfiguration.getName());

        final HelpSet helpSet = inputAutomationModuleInterface.getHelpSet();
        if(!JavaHelpSetNotifier.isNotified(helpSet))
        {
            final HelpSetEvent helpSetEvent = new HelpSetEvent(
                this, helpSet, HelpSetEvent.HELPSET_ADDED);
            this.helpSetListenerInterface.helpSetAdded(helpSetEvent);
        }
    }
    
    public ListModel getListModel()
    {
        return (ListModel) this.defaultListModelHelper.getListModel();
    }
    
    public InputAutomationModuleFactoryInterface getInstance(final String moduleName)
    {
        LogUtil.put(LogFactory.getInstance("Getting Module: " + moduleName, this, "getInstance"));
        final InputAutomationModuleFactoryInterface inputAutomationModuleFactoryInterface = (InputAutomationModuleFactoryInterface) this.hashMap.get(moduleName);
        if(inputAutomationModuleFactoryInterface == null) {
            LogUtil.put(LogFactory.getInstance("Module: " + moduleName + " was null", this, "getInstance"));
        }

        return inputAutomationModuleFactoryInterface;
    }
}
