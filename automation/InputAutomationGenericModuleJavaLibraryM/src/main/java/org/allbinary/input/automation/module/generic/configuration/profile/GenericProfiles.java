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
package org.allbinary.input.automation.module.generic.configuration.profile;



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
import org.allbinary.input.automation.module.generic.configuration.profile.actions.GenericProfileActionData;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class GenericProfiles
    implements DomNodeInterface, DomDocumentMappingInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    public static final String DEFAULT_PROFILES_PATH = 
        "./modules/configs/profiles/";
    public static final String DEFAULT_PROFILE_ACTIONS_PATH = 
        DEFAULT_PROFILES_PATH + "actions/";    
    
    public static final String DEFAULT_FILE = 
        DEFAULT_PROFILES_PATH + "profiles.xml";
    
    private HashMap hashMap;
    
    private DefaultListModelHelper profilesDefaultListModelHelper;
    
    private final String fileName;
    
    public GenericProfiles(String fileName) throws Exception
    {
        this.fileName = fileName;

        this.profilesDefaultListModelHelper = new DefaultListModelHelper();
        
        this.hashMap = new HashMap();
        
        if(new File(fileName).isFile())
        {
            byte bytes[]= new byte[100000];
            
            FileInputStream idFile = new FileInputStream(fileName);
            int length = idFile.read(bytes);
            String data = new String(bytes);
            int endIndex = data.lastIndexOf('>');
            
            Document document = DomDocumentHelper.create(
                data.substring(0, endIndex + 1));
            
            NodeList nodeList = document.getElementsByTagName(
                GenericProfilesData.NAME);
            
            logUtil.put("Number Of Profiles Specified: " + nodeList.getLength(), this,"Contructor");
            
            if(nodeList != null)
            {
                this.initProfiles(nodeList.item(0));
            }
            else
            {
                throw new Exception(GenericProfileActionData.NAME + " Name Node Node Children");
            }
            
            logUtil.put("Loaded: " + this.hashMap.size() + " Configuration Profile Actions", this,"Contructor");
        }
        else
        {
            logUtil.put("No Generic Profile: " + fileName, this,"Contructor");
        }
        this.getDefaultListModelHelper().initDefaultModelList();
    }
    
    private void initProfiles(Node node)
    throws Exception
    {
        NodeList nodeList = node.getChildNodes();
        
        for(int index = 0; index < nodeList.getLength(); index++)
        {
            Node profileNameNode = nodeList.item(index);
            
            if(profileNameNode.getNodeName().compareTo(GenericProfileData.NAME) == 0)
            {
                GenericProfile genericProfile =
                    new GenericProfile(profileNameNode);
                this.hashMap.put(genericProfile.getName(), genericProfile);
                this.getDefaultListModelHelper().add(
                    genericProfile.getName());
            }
        }
    }
    
    public DefaultListModelHelper getDefaultListModelHelper()
    {
        return this.profilesDefaultListModelHelper;
    }

    public GenericProfile get(String name) throws Exception
    {
        GenericProfile genericProfile = (GenericProfile) this.hashMap.get(name);
        if(genericProfile == null)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put("No Generic Profile Named: " + name + 
                " availability was: " + this.hashMap, this, commonStrings.GET);
        }
        return genericProfile;
    }
    
    public void add(String name) throws Exception
    {
        this.hashMap.put(name, new GenericProfile(name));
        
        this.getDefaultListModelHelper().add(name);
        this.getDefaultListModelHelper().initDefaultModelList();
        
        this.save();
    }
    
    public void remove(String name) throws Exception
    {
        this.hashMap.remove(name);
        
        this.getDefaultListModelHelper().remove(name);
        this.getDefaultListModelHelper().initDefaultModelList();

        this.save();
    }
    
    public Node toXmlNode(Document document) throws Exception
    {
        Node node = document.createElement(GenericProfilesData.NAME);
        
        Set set = this.hashMap.keySet();

        final Object[] nameArray = set.toArray();
        final int size = nameArray.length;
        for(int index = 0; index < size; index++)
        {
            GenericProfile genericProfile = (GenericProfile)
            this.hashMap.get((String) nameArray[index]);
            
            node.appendChild(genericProfile.toXmlNode(document));
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
    
    public void save() throws Exception
    {
        FileOutputStream idFile = new FileOutputStream(this.fileName);
        DataOutputStream idOutData = new DataOutputStream(idFile);
        String documentString = DomDocumentHelper.toString(this.toXmlDoc());
        //logUtil.put("Do: " + nodeList.getLength(), this,"Contructor");
        idOutData.writeBytes(documentString);
    }
}
