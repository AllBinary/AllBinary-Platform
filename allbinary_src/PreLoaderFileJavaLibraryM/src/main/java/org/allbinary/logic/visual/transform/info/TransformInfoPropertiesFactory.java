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
package org.allbinary.logic.visual.transform.info;

import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class TransformInfoPropertiesFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final TransformInfoPropertiesFactory instance = new TransformInfoPropertiesFactory();

    public static TransformInfoPropertiesFactory getInstance() {
        return instance;
    }
    
   private TransformInfoPropertiesFactory()
   {
   }
   
   public TransformInfoProperties getInstance(Node node) throws Exception
   {
      final NodeList transformInfoChildNodeList = node.getChildNodes();
      
      final TransformInfoData transformInfoData = TransformInfoData.getInstance();
      
      final NamedNodeMap attributes = node.getAttributes();
      final Attr attrNode = (Attr) attributes.getNamedItem(transformInfoData.NAME);
      final String name = attrNode.getValue();
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         logUtil.put("Next View Name: " + name, this, "toTransformInfoPropertiesHashMap()");
      }
      
      final Node labelNode = DomSearchHelper.getNodeNoThrow(
            transformInfoData.LABEL, transformInfoChildNodeList);
      
      String label = DomNodeHelper.getTextNodesValue(labelNode);
      
      if(label == null)
      {
         label = name;
      }
      
      final Node descriptionNode =
            DomSearchHelper.getNode(
            transformInfoData.DESCRIPTION, transformInfoChildNodeList);
      
      final String description =
            DomNodeHelper.getTextNodesValue(descriptionNode);
      
      final Node objectFileNode =
            DomSearchHelper.getNode(
            transformInfoData.OBJECTFILENAME, transformInfoChildNodeList);
      
      final String objectFileName =
            DomNodeHelper.getTextNodeValue(objectFileNode);
      
      final Node objectConfigFileNode =
            DomSearchHelper.getNode(
            transformInfoData.OBJECTCONFIGFILENAME, transformInfoChildNodeList);
      
      final String objectConfigFileName =
            DomNodeHelper.getTextNodeValue(objectConfigFileNode);
      
      final Node templateFileNode =
            DomSearchHelper.getNode(
            transformInfoData.TEMPLATEFILENAME, transformInfoChildNodeList);
      
      final String templateFileName =
            DomNodeHelper.getTextNodeValue(templateFileNode);
      
      final TransformInfoProperties transformInfoProperties =
            new TransformInfoProperties(
            name, label, description,
            objectFileName, objectConfigFileName, templateFileName);
      
      //TWB - Why was I returning null here for all cases?
      return transformInfoProperties;
      //return null;
   }
   
}
