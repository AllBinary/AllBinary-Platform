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
package org.allbinary.logic.visual.transform.info.dom;

import java.util.HashMap;
import java.util.Vector;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;

import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.data.tree.dom.document.DomDocumentFileHelper;

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoData;

import org.allbinary.logic.visual.transform.info.TransformInfoProperties;
import org.allbinary.logic.visual.transform.info.TransformInfoPropertiesFactory;
import org.allbinary.logic.visual.transform.info.TransformInfosData;
import org.allbinary.string.CommonStrings;

public class TransformInfoPropertiesDocument
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private Document document;
   
   public TransformInfoPropertiesDocument(String filePath) throws Exception
   {
      AbFile xmlFile = new AbFile(filePath);
      this.document = DomDocumentFileHelper.create(xmlFile);
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

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            logUtil.put("Size: " + transformInfoNodeVector.size(), this, "toTransformInfoPropertiesHashMap()");
         }

         for(int index = 0; index < transformInfoNodeVector.size(); index++)
         {
            Node node = (Node) transformInfoNodeVector.get(index);

            //Add each transformInfo and its children
            TransformInfoProperties transformInfoProperties = 
               TransformInfoPropertiesFactory.getInstance().getInstance(node);

            transformInfoPropertiesHashMap.put(
               transformInfoProperties.getName(), transformInfoProperties);
         }
         
         return transformInfoPropertiesHashMap;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, "toTransformInfoPropertiesHashMap()", e);
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
