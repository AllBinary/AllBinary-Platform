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
package views.business.context.modules.storefront.customizer.template.objectConfig;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Document;

import allbinary.logic.visual.transform.info.TransformInfosData;

import allbinary.logic.visual.transform.info.TransformInfoInterface;

import allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigData;

import allbinary.logic.visual.transform.info.TransformInfoDomNodeView;

public class InsertTemplateCustomizerTransformInfoObjectConfig extends NoTemplateTransformInfoObjectConfig
{

    public InsertTemplateCustomizerTransformInfoObjectConfig(
        TransformInfoInterface transformInfoInterface) throws Exception
    {
        super(transformInfoInterface);
        //this.setDocument(this.generate(this.getDocument()));
    }

    public InsertTemplateCustomizerTransformInfoObjectConfig(
        TransformInfoInterface transformInfoInterface, Document document) throws Exception
    {
        super(transformInfoInterface, document);
        this.setDocument(this.generate(this.toXmlDoc()));
    }

    public InsertTemplateCustomizerTransformInfoObjectConfig(
        TransformInfoInterface transformInfoInterface, String name, String type) throws Exception
    {
        super(transformInfoInterface, name, type);
        this.setDocument(this.generate(this.toXmlDoc()));
    }

    //set root component that is the template
    public void set(String aParentViewName) throws Exception
    {

        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
        {
            LogUtil.put(LogFactory.getInstance("Setting: " + aParentViewName, this, "set()"));
        }

        Document document = this.toXmlDoc();
        
        NodeList parentComponentsNodeList =
            //Parent
        	document.getElementsByTagName(TransformInfosData.getInstance().NAME);

        if (parentComponentsNodeList != null
            && parentComponentsNodeList.getLength() > 0)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
                LogUtil.put(LogFactory.getInstance("Replacing", this, "set()"));
            }
        	
            Node componentNode =
                parentComponentsNodeList.item(0).getChildNodes().item(0);
            if (componentNode != null)
            {
            	document.removeChild(componentNode);
            }

            parentComponentsNodeList.item(0).appendChild(
                new TransformInfoDomNodeView(
                aParentViewName).toXmlNode(document));
        } else
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
                LogUtil.put(LogFactory.getInstance("Setting", this, "set()"));
            }
        	
            Node objectConfigNode = document.getElementsByTagName(
            		TransformInfoObjectConfigData.getInstance().NAME).item(0);

            Node parentComponentsNode =
                document.createElement(TransformInfosData.getInstance().NAME);

            objectConfigNode.appendChild(parentComponentsNode);

            TransformInfoDomNodeView transformInfoDomNodeView = 
            	new TransformInfoDomNodeView(aParentViewName);
            
            parentComponentsNode.appendChild(
            		transformInfoDomNodeView.toXmlNode(document));
        }
    }
}
