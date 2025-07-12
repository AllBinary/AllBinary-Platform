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


import java.util.Vector;

import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.input.automation.module.generic.configuration.profile.actions.GenericProfileActions;
import org.allbinary.logic.communication.log.LogUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GenericProfile
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private String name;
    private Vector vector;
    private GenericProfileActions genericProfileActions;
    
    public GenericProfile(Node node)
    {
        this.setName(DomNodeHelper.getTextNodeValue(node));
        this.vector = new Vector();
        
        NodeList nodeList = node.getChildNodes();
        
        logUtil.put("Name: " + this.getName() + " Child Nodes: " + nodeList.getLength(), this,"Contructor");
        
        for(int index = 0; index < nodeList.getLength(); index++)
        {
            Node profileNode = nodeList.item(index);
            
            if(profileNode.getNodeName().compareTo(GenericProfileDataWorkerData.NAME) == 0)
            {
                GenericProfileDataWorkerType genericProfileDataWorkerType =
                     GenericProfileDataWorkerType.getInstance(profileNode);
                logUtil.put("Adding GenericProfileDataWorkerType: " + 
                    genericProfileDataWorkerType.toString(), this,"Contructor");
                this.vector.add(genericProfileDataWorkerType);
            }
        }
    }

    public GenericProfile(String name)
    {
        this.setName(name);
        
        this.vector = new Vector();
    }
    
    public Vector getGenericProfileDataWorkerTypeVector()
    {
        return this.vector;
    }
    
    public void add(GenericProfileDataWorkerType genericProfileDataWorkerType)
    {
        if(!this.vector.contains(genericProfileDataWorkerType))
        this.vector.add(genericProfileDataWorkerType);
    }

    public void remove(GenericProfileDataWorkerType genericProfileDataWorkerType)
    {
        this.vector.remove(genericProfileDataWorkerType);
    }
    
    public Node toXmlNode(Document document) throws Exception
    {
        Node node = ModDomHelper.createTextNode(
            document, GenericProfileData.NAME, name);
        
        final int size = vector.size();
        for(int index = 0; index < size; index++)
        {
            GenericProfileDataWorkerType genericProfileDataWorkerType =
                (GenericProfileDataWorkerType) vector.get(index);
            node.appendChild(genericProfileDataWorkerType.toXmlNode(document));
        }
        return node;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public GenericProfileActions getGenericProfileActions()
    {
        return genericProfileActions;
    }

    public void setGenericProfileActions(GenericProfileActions genericProfileActions)
    {
        this.genericProfileActions = genericProfileActions;
    }
}
