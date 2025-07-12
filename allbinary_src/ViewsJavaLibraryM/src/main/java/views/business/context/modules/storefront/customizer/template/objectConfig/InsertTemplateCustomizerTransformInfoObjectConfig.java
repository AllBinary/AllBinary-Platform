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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoDomNodeView;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.TransformInfosData;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigData;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class InsertTemplateCustomizerTransformInfoObjectConfig extends NoTemplateTransformInfoObjectConfig
{
    protected final LogUtil logUtil = LogUtil.getInstance();


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

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
        {
            logUtil.put("Setting: " + aParentViewName, this, "set()");
        }

        Document document = this.toXmlDoc();
        
        NodeList parentComponentsNodeList =
            //Parent
        	document.getElementsByTagName(TransformInfosData.getInstance().NAME);

        if (parentComponentsNodeList != null
            && parentComponentsNodeList.getLength() > 0)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                logUtil.put("Replacing", this, "set()");
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
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
                logUtil.put("Setting", this, "set()");
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
