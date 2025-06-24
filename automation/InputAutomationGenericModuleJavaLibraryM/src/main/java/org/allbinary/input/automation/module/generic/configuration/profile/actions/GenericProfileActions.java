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
package org.allbinary.input.automation.module.generic.configuration.profile.actions;

import java.io.DataOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.HashMap;

import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.data.tree.dom.document.mapping.DomDocumentMappingInterface;
import org.allbinary.input.automation.module.DefaultListModelHelper;
import org.allbinary.input.automation.module.generic.configuration.profile.GenericProfiles;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class GenericProfileActions
    implements DomNodeInterface, DomDocumentMappingInterface
{
    public static final String DEFAULT_PROFILE_ACTIONS_PATH = 
        GenericProfiles.DEFAULT_PROFILES_PATH + "actions/";
        
    private String name;
    
    private GenericProfileActionsJPanel genericProfileActionsJPanel;
    
    private DefaultListModelHelper actionsDefaultListModelHelper;
    
    private HashMap hashMap;
    
    public GenericProfileActions(
        GenericProfileActionsJPanel genericProfileActionsJPanel,
        String name) throws Exception
    {
        this.setName(name);
        this.init(genericProfileActionsJPanel);
        this.load();
    }

    public GenericProfileActions(
        GenericProfileActionsJPanel genericProfileActionsJPanel,
        AbPath abPath, String name) throws Exception
    {
        this.setName(name);
        this.init(genericProfileActionsJPanel);
        this.load();
    }
    
    public GenericProfileActions(
        GenericProfileActionsJPanel genericProfileActionsJPanel,
        FileInputStream fileInputStream, String name) throws Exception
    {
        this.setName(name);
        this.init(genericProfileActionsJPanel);
        this.fileInit(fileInputStream);
    }
    
    private void init(GenericProfileActionsJPanel genericProfileActionsJPanel)
    throws Exception
    {
        this.setGenericProfileActionsJPanel(genericProfileActionsJPanel);
        this.actionsDefaultListModelHelper = new DefaultListModelHelper();
        this.setHashMap(new HashMap());
    }

    public void save() throws Exception
    {
        FileOutputStream idFile = new FileOutputStream(
            DEFAULT_PROFILE_ACTIONS_PATH + getName() + ".xml");
        DataOutputStream idOutData = new DataOutputStream(idFile);
        idOutData.writeBytes(
            DomDocumentHelper.toString(this.toXmlDoc()));
    }

    public static File getFile(String name)
    {
        String fileName = DEFAULT_PROFILE_ACTIONS_PATH + name + ".xml";
        return new File(fileName);
    }
    
    private void load()
    throws Exception
    {
        File file = getFile(getName());

        if(file.isFile())
        {
            FileInputStream idFile = new FileInputStream(file);
            this.fileInit(idFile);
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("No Profile: " + file.getAbsolutePath(), this, "Contructor"));
        }
    }
    
    private void fileInit(FileInputStream fileInputStream)
        throws Exception
    {
        byte bytes[]= new byte[100000];
        
        int length = fileInputStream.read(bytes);
        String data = new String(bytes);
        int endIndex = data.lastIndexOf('>');
        
        Document document = DomDocumentHelper.create(
            data.substring(0, endIndex + 1));
        
        NodeList nameNodeList = document.getElementsByTagName(
            GenericProfileActionsData.NAME);
        
        LogUtil.put(LogFactory.getInstance("Number Of Profiles Specified: " + nameNodeList.getLength(), this, "Contructor"));
        
        for(int index = 0; index < nameNodeList.getLength(); index++)
        {
            Node node = nameNodeList.item(index);
            NodeList nodeList = node.getChildNodes();
            
            if(nodeList != null)
            {
                this.initActions(nodeList);
            }
            else
            {
                throw new Exception(GenericProfileActionData.NAME + " Name Node Node Children");
            }
            
        }
        LogUtil.put(LogFactory.getInstance("Loaded: " + this.getHashMap().size() + " Configuration Profile Action(s)", this,"Contructor"));
        
        this.getDefaultListModelHelper().initDefaultModelList();
    }
    
    private void initActions(NodeList nodeList)
    throws Exception
    {
        for(int index = 0; index < nodeList.getLength(); index++)
        {
            Node actionNode = nodeList.item(index);
            //GenericProfileActionData.NAME
            
            if(actionNode != null)
            {
                GenericProfileAction genericConfigurationProfileAction =
                    new GenericProfileAction(
                    this.getGenericProfileActionsJPanel().getGenericProfileActionJPanel(),
                    actionNode);
                
                this.getHashMap().put(
                    genericConfigurationProfileAction.getName(),
                    genericConfigurationProfileAction);
                
                this.getDefaultListModelHelper().add(
                    genericConfigurationProfileAction.getName());
            }
            else
            {
                throw new Exception(GenericProfileActionData.NAME + " Node Null");
            }
        }
    }
    
    public DefaultListModelHelper getDefaultListModelHelper()
    {
        return this.actionsDefaultListModelHelper;
    }
    
    public GenericProfileAction getAction(
        String string)
    {
        return (GenericProfileAction) this.getHashMap().get(string);
    }
    
    public void add(String name) throws Exception
    {
        GenericProfileAction genericProfileAction =
            new GenericProfileAction(name);
        
        this.getHashMap().put(
            genericProfileAction.getName(),
            genericProfileAction);
        
        this.getDefaultListModelHelper().add(genericProfileAction.getName());
        this.getDefaultListModelHelper().initDefaultModelList();
        
        this.save();
    }
    
    public void remove(String name) throws Exception
    {
        this.getHashMap().remove(name);
        this.getDefaultListModelHelper().remove(name);
        this.getDefaultListModelHelper().initDefaultModelList();
        this.save();
    }
    
    public HashMap toHashMap()
    {
        HashMap hashMap = new HashMap();
        
        LogUtil.put(LogFactory.getInstance("HashMap: " + hashMap.toString(), this, "toHashMap()"));
        
        return hashMap;
    }
    
    public Node toXmlNode(Document document) throws Exception
    {
        Node node = document.createElement(GenericProfileActionsData.NAME);

        Set set = this.getHashMap().keySet();
                
        final Object[] actionNameArray = set.toArray();
        final int size = actionNameArray.length;
        for(int index = 0; index < size; index++)
        {
            String nextActionName = (String) actionNameArray[index];
            
            GenericProfileAction nextGenericProfileAction =
                (GenericProfileAction) this.getAction(nextActionName);
            
            node.appendChild(nextGenericProfileAction.toXmlNode(document));
        }
        
        return node;
    }
    
    public Document toXmlDoc() throws Exception
    {
        Document document = DomDocumentHelper.create();
        Node node = this.toXmlNode(document);
        document.appendChild(node);
        return document;
    }
    
    public GenericProfileActionsJPanel getGenericProfileActionsJPanel()
    {
        return genericProfileActionsJPanel;
    }
    
    public void setGenericProfileActionsJPanel(GenericProfileActionsJPanel genericProfileActionsJPanel)
    {
        this.genericProfileActionsJPanel = genericProfileActionsJPanel;
    }
    
    public HashMap getHashMap()
    {
        return hashMap;
    }
    
    public void setHashMap(HashMap hashMap)
    {
        this.hashMap = hashMap;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
