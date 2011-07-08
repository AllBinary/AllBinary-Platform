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
package allbinary.logic.visual.transform.info;

import abcs.data.tree.dom.DomNodeHelper;
import abcs.data.tree.dom.DomSearchHelper;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TransformInfoDomNode
{
    private TransformInfoInterface transformInfoInterface;
    private String mappedName;

    public TransformInfoDomNode(Node node) throws Exception
    {
        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
            LogUtil.put(LogFactory.getInstance(
                "Constructing TransformInfo From TransformInfoDomNode: "
                + node.getNodeName(), this, "TransformInfoDomNode(Node node)"));
        }

    	TransformInfoData transformInfoData = 
    		TransformInfoData.getInstance();
        
        NamedNodeMap attributes = node.getAttributes();
        Attr attrNode = (Attr) attributes.getNamedItem(transformInfoData.NAME);
        String name = attrNode.getValue();

        NodeList nodeList = node.getChildNodes();

        if (nodeList.getLength() > 1)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Constructing Complete TransformInfo Node For: ");
                stringBuffer.append(name);
                stringBuffer.append(" with ");
                stringBuffer.append(nodeList.getLength());
                stringBuffer.append(" children");

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(),
                    this, "TransformInfoObjectConfigComponent(Node node)"));
            }

            Node objectFileNameNode = DomSearchHelper.getNode(
                transformInfoData.OBJECTFILENAME, nodeList);
            String objectFileName = DomNodeHelper.getTextNodeValue(objectFileNameNode);

            Node objectConfigFileNameNode = DomSearchHelper.getNode(
                transformInfoData.OBJECTCONFIGFILENAME, nodeList);
            String objectConfigFileName =
                DomNodeHelper.getTextNodeValue(objectConfigFileNameNode);

            Node templateFileNameNode =
                DomSearchHelper.getNode(transformInfoData.TEMPLATEFILENAME, nodeList);
            String templateFileName =
                DomNodeHelper.getTextNodeValue(templateFileNameNode);

            Node dataFileNameNode =
                DomSearchHelper.getNode(transformInfoData.DATAFILENAME, nodeList);
            String dataFileName =
                DomNodeHelper.getTextNodeValue(dataFileNameNode);

            //Assumes that the transform info has absolute URIs unlike Store/Context requests
            this.transformInfoInterface = (TransformInfoInterface)
                new TransformInfo(name,
                objectFileName, objectConfigFileName,
                templateFileName, dataFileName);
        } else
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
                StringBuffer stringBuffer = new StringBuffer();

                stringBuffer.append("Constructing Partial TransformInfo Node For: ");
                stringBuffer.append(name);
                stringBuffer.append(" with ");
                stringBuffer.append(nodeList.getLength());
                stringBuffer.append(" children");

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(),
                    this, "TransformInfoObjectConfigComponent(Node node)"));
            }

            this.transformInfoInterface = (TransformInfoInterface)
                new TransformInfo(name);
        }

        Attr templateAttrNode = (Attr) attributes.getNamedItem(transformInfoData.MAPPED);

        if (templateAttrNode != null)
        {
            this.mappedName = templateAttrNode.getValue();
        } else
        {
            this.mappedName = this.getTransformInfoInterface().getName();
        }
    }

    public TransformInfoDomNode(TransformInfoInterface transformInfoInterface)
    {
        this.transformInfoInterface = transformInfoInterface;
        this.mappedName = transformInfoInterface.getName();
    }

    public TransformInfoDomNode(
        TransformInfoInterface transformInfoInterface, String mappedName)
    {
        this.transformInfoInterface = transformInfoInterface;
        this.mappedName = mappedName;
    }

    public TransformInfoDomNode(String name, String mappedName) throws Exception
    {
        this.transformInfoInterface = new TransformInfo(name);
        	//TWB - GAE update
            //new TransformInfo(name, null, null, null, null);
        this.mappedName = mappedName;
    }

    public TransformInfoInterface getTransformInfoInterface()
    {
        return this.transformInfoInterface;
    }

    //if the name is overridden with a mapped name like body to reduce
    //the number of parent templates required.
    public String getMappedName()
    {
        return this.mappedName;
    }

    //TWB - seems that mapped name is not used anymore?
    public String getReplaceKey()
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("<transform:component name=\"");
        //stringBuffer.append(this.getMappedName());
        stringBuffer.append("body");
        stringBuffer.append("\"/>");

        String key = stringBuffer.toString();

        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
            LogUtil.put(LogFactory.getInstance("Component Key: " + key, this, "getReplaceKey()"));
        }
        return key;
    }
}
