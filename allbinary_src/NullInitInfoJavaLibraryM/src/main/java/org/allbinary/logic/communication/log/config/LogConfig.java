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
package org.allbinary.logic.communication.log.config;

import org.allbinary.business.entry.EntryData;
import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.logic.java.bool.BooleanUtil;
import org.allbinary.util.BasicArrayList;
import org.w3c.dom.Node;


public class LogConfig
{

    private boolean isEnabled;
    private String name;
    private String description;
    private String path;
    private String fileName;

    public LogConfig(
        String aName, String aDescription, String aPath, String aFileName)
    {
        this.isEnabled = false;
        this.name = aName;
        this.description = aDescription;
        this.path = aPath;
        this.fileName = aFileName;
    }

    public LogConfig(Node node) throws Exception
    {
        Node enabledValueNode =
            DomSearchHelper.getNode(EntryData.getInstance().ENABLE, node.getChildNodes());
        String isEnabledString =
            DomNodeHelper.getTextNodeValue(enabledValueNode);

        this.isEnabled = BooleanUtil.getInstance().getFromString(isEnabledString);

        LogConfigData logConfigData = LogConfigData.getInstance();
        
        Node nameValueNode =
            DomSearchHelper.getNode(logConfigData.NAME, node.getChildNodes());
        this.name =
            DomNodeHelper.getTextNodeValue(nameValueNode);

        Node descriptionValueNode =
            DomSearchHelper.getNode(logConfigData.DESCRIPTION, node.getChildNodes());
        this.description =
            DomNodeHelper.getTextNodeValue(descriptionValueNode);

        /*
        Node pathValueNode =
        DomSearchHelper.getNode(logConfigData.PATH, node.getChildNodes());
        this.path =
        DomNodeHelper.getTextNodeValue(pathValueNode);
         */

        Node fileValueNode =
            DomSearchHelper.getNode(logConfigData.FILE, node.getChildNodes());
        this.fileName =
            DomNodeHelper.getTextNodeValue(fileValueNode);
    }

    public String getName()
    {
        return this.name;
    }

    public boolean isEnabled()
    {
        return this.isEnabled;
    }

    public void enable()
    {
        this.isEnabled = true;
    }

    public void disable()
    {
        this.isEnabled = false;
    }

    public String getDescription()
    {
        return this.description;
    }

    /*
    public String getPath()
    {
    return this.path;
    }
     */
    public String getFileName()
    {
        return this.fileName;
    }

    public void setName(String value)
    {
        this.name = value;
    }

    public void setDescription(String value)
    {
        this.description = value;
    }

    public void setPath(String value)
    {
        this.path = value;
    }

    public void setFileName(String value)
    {
        this.fileName = value;
    }
/*
    private Document getDoc() throws Exception
    {
    }
*/
    public BasicArrayList getTypeVector() throws Exception
    {
        BasicArrayList logKeyVector = new BasicArrayList();
        /*
        Document document = this.getDoc();

        NodeList logConfigTypesNodeList =
            document.getElementsByTagName(LogConfigTypesData.getInstance().NAME);

        Node logConfigTypesNode = logConfigTypesNodeList.item(0);

        Vector logConfigTypeNodeVector =
            DomSearchHelper.getAllNodes(
            LogConfigTypeData.getInstance().NAME, logConfigTypesNode.getChildNodes());

        iter = logConfigTypeNodeVector;
        while (iter.hasNext())
        {
            Node node = (Node) iter.next();
            LogConfigType logType = LogConfigTypes.getInstance(node);
            logKeyVector.add(logType);
        }
        */
        return logKeyVector;
    }

    /*
    public Vector getTypeNameVector() throws Exception
    {
    Vector logKeyVector = new Vector();
    Document document = this.getDoc();

    NodeList logConfigTypesNodeList =
    document.getElementsByTagName(LogConfigTypesData.NAME);

    Node logConfigTypesNode = logConfigTypesNodeList.item(0);

    Vector logConfigTypeNodeVector =
    DomSearchHelper.getAllNodes(
    LogConfigTypeData.NAME, logConfigTypesNode.getChildNodes());

    iter = logConfigTypeNodeVector;
    while(iter.hasNext())
    {
    Node node = (Node) iter.next();
    LogConfigType logType = LogConfigTypes.getInstance(node);
    logKeyVector.add(logType.getName());
    }

    return logKeyVector;
    }
     **/
}
