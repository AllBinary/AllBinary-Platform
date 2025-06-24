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
package org.allbinary.dom;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.allbinary.util.BasicArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomHelper
{
    private static final DomHelper instance = new DomHelper();

    /**
     * @return the instance
     */
    public static DomHelper getInstance()
    {
        return instance;
    }

    private DomHelper()
    {
    }

    public Document createDocument()
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.newDocument();

            return document;
        } catch (Exception e)
        {
            return null;
        }
    }

    public Document createDocument(File xmlFile) throws Exception
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(xmlFile);

            return document;
        } catch (Exception e)
        {
            throw e;
        }
    }

    public String toString(Document document) throws Exception
    {
        try
        {
            DOMSource domSource = new DOMSource(document);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            StreamResult streamResult = new StreamResult(byteArrayOutputStream);

            TransformerFactory copyTransformerFactory = TransformerFactory.newInstance();

            Transformer copyTransformer = copyTransformerFactory.newTransformer();

            copyTransformer.transform(domSource, streamResult);

            return byteArrayOutputStream.toString();
        } catch (Exception e)
        {
            throw e;
        }
    }

    public void save(File file, Document document) throws Exception
    {
        try
        {
            TransformerFactory copyTransformerFactory = TransformerFactory.newInstance();

            Transformer copyTransformer = copyTransformerFactory.newTransformer();

            DOMSource domSource = new DOMSource(document);

            file.createNewFile();

            StreamResult streamResult = new StreamResult(file);
            copyTransformer.transform(domSource, streamResult);

        } catch (Exception e)
        {
            throw e;
        }
    }

    public NodeList getChildNodeList(String nodeName, NodeList nodeList) throws Exception
    {
        int numberOfNodes = nodeList.getLength();
        for (int index = 0; index < numberOfNodes; index++)
        {
            Node node = nodeList.item(index);
            if (node.getNodeName().compareTo(nodeName) == 0)
            {
                return node.getChildNodes();
            }
        }
        throw new Exception(nodeName + " Node Not Found");
    }

    public BasicArrayList getChildrenWithoutTextNodes(String nodeName, NodeList nodeList) throws Exception
    {
        BasicArrayList list = new BasicArrayList();

        NodeList childNodeList = getChildNodeList(nodeName, nodeList);

        int numberOfChildren = childNodeList.getLength();

        for (int index = 0; index < numberOfChildren; index++)
        {
            Node node = childNodeList.item(index);

            if(node.getNodeType() != Node.TEXT_NODE)
            {
                list.add(node);
            }
        }
        return list;
    }

    public BasicArrayList getWithoutTextNodes(NodeList nodeList) throws Exception
    {
        BasicArrayList list = new BasicArrayList();

        int numberOfChildren = nodeList.getLength();

        for (int index = 0; index < numberOfChildren; index++)
        {
            Node node = nodeList.item(index);

            if(node.getNodeType() != Node.TEXT_NODE)
            {
                list.add(node);
            }
        }
        return list;
    }

    public Node searchNodeList(String nodeName, NodeList nodeList) throws Exception
    {
        int numberOfNodes = nodeList.getLength();
        for (int index = 0; index < numberOfNodes; index++)
        {
            Node node = nodeList.item(index);
            LogUtil.put(LogFactory.getInstance("NodeName: " + node.getNodeName(), this, "searchNodeList"));
            if (node.getNodeName().compareTo(nodeName) == 0)
            {
                return node;
            }
        }
        throw new Exception(nodeName + " Node Not Found in search");
    }

    public Node searchNodeList(String nodeName, BasicArrayList nodeList) throws Exception
    {
        int numberOfNodes = nodeList.size();
        for (int index = 0; index < numberOfNodes; index++)
        {
            Node node = (Node) nodeList.get(index);
            LogUtil.put(LogFactory.getInstance("NodeName: " + node.getNodeName(), this, "searchNodeList"));
            if (node.getNodeName().compareTo(nodeName) == 0)
            {
                return node;
            }
        }
        throw new Exception(nodeName + " Node Not Found in search");
    }
}
