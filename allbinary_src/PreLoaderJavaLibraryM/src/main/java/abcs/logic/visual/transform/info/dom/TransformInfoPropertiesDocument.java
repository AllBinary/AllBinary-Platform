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
package abcs.logic.visual.transform.info.dom;

import java.util.HashMap;
import java.util.Vector;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;

import abcs.data.tree.dom.document.DomDocumentHelper;
import abcs.data.tree.dom.DomSearchHelper;

import abcs.logic.basic.io.file.AbFile;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

import abcs.logic.visual.transform.info.TransformInfoProperties;
import abcs.logic.visual.transform.info.TransformInfoPropertiesFactory;

import allbinary.logic.visual.transform.info.TransformInfoData;
import allbinary.logic.visual.transform.info.TransformInfosData;

public class TransformInfoPropertiesDocument
{
   private Document document;
   
   public TransformInfoPropertiesDocument(String filePath) throws Exception
   {
      AbFile xmlFile = new AbFile(filePath);
      this.document = DomDocumentHelper.create(xmlFile);
   }
   
   public HashMap toTransformInfoPropertiesHashMap()
   {
      try
      {
         HashMap transformInfoPropertiesHashMap = new HashMap();

         /*
         Node objectConfigNode = this.document.getElementsByTagName(
            TransformInfoObjectConfigData.NAME).item(0);

         NodeList objectConfigChildNodeList = objectConfigNode.getChildNodes();
         */
         
         Node transformInfosNode = this.document.getElementsByTagName(
            TransformInfosData.getInstance().NAME).item(0);
         
         NodeList transformInfosChildNodeList = transformInfosNode.getChildNodes();
         
         Vector transformInfoNodeVector = DomSearchHelper.getAllNodes(
            TransformInfoData.getInstance().NAME, transformInfosChildNodeList);

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
         {
            LogUtil.put(LogFactory.getInstance("Size: " + transformInfoNodeVector.size(), this, "toTransformInfoPropertiesHashMap()"));
         }

         for(int index = 0; index < transformInfoNodeVector.size(); index++)
         {
            Node node = (Node) transformInfoNodeVector.get(index);

            //Add each transformInfo and its children
            TransformInfoProperties transformInfoProperties = 
               TransformInfoPropertiesFactory.getInstance(node);

            transformInfoPropertiesHashMap.put(
               transformInfoProperties.getName(), transformInfoProperties);
         }
         
         return transformInfoPropertiesHashMap;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed", this, "toTransformInfoPropertiesHashMap()", e));
         }
         return null;
      }
   }
   
   /*
   public void setTransformInfoProperties()
   {
   }
    */
}
