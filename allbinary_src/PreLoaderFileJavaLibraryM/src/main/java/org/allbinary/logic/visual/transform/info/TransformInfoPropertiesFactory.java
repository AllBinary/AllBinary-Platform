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
	private static final TransformInfoPropertiesFactory instance = new TransformInfoPropertiesFactory();
	
   private TransformInfoPropertiesFactory()
   {
   }
   
   public static TransformInfoProperties getInstance(Node node) throws Exception
   {
      NodeList transformInfoChildNodeList = node.getChildNodes();
      
      TransformInfoData transformInfoData = TransformInfoData.getInstance();
      
      NamedNodeMap attributes = node.getAttributes();
      Attr attrNode = (Attr) attributes.getNamedItem(transformInfoData.NAME);
      String name = attrNode.getValue();
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         LogUtil.put(LogFactory.getInstance(
             "Next View Name: " + name, instance, "toTransformInfoPropertiesHashMap()"));
      }
      
      Node labelNode = DomSearchHelper.getNodeNoThrow(
            transformInfoData.LABEL, transformInfoChildNodeList);
      
      String label = DomNodeHelper.getTextNodesValue(labelNode);
      
      if(label == null)
      {
         label = name;
      }
      
      Node descriptionNode =
            DomSearchHelper.getNode(
            transformInfoData.DESCRIPTION, transformInfoChildNodeList);
      
      String description =
            DomNodeHelper.getTextNodesValue(descriptionNode);
      
      Node objectFileNode =
            DomSearchHelper.getNode(
            transformInfoData.OBJECTFILENAME, transformInfoChildNodeList);
      
      String objectFileName =
            DomNodeHelper.getTextNodeValue(objectFileNode);
      
      Node objectConfigFileNode =
            DomSearchHelper.getNode(
            transformInfoData.OBJECTCONFIGFILENAME, transformInfoChildNodeList);
      
      String objectConfigFileName =
            DomNodeHelper.getTextNodeValue(objectConfigFileNode);
      
      Node templateFileNode =
            DomSearchHelper.getNode(
            transformInfoData.TEMPLATEFILENAME, transformInfoChildNodeList);
      
      String templateFileName =
            DomNodeHelper.getTextNodeValue(templateFileNode);
      
      TransformInfoProperties transformInfoProperties =
            new TransformInfoProperties(
            name, label, description,
            objectFileName, objectConfigFileName, templateFileName);
      
      //TWB - Why was I returning null here for all cases?
      return transformInfoProperties;
      //return null;
   }
   
}
