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
package org.allbinary.logic.visual.dhtml.style.css.property;

import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.DomData;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.HashMap;

import java.util.Vector;
import org.allbinary.string.CommonSeps;

public class CssPropertyValues implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private Vector propertyValueVector;
   
   public CssPropertyValues()
   {
      this.propertyValueVector = new Vector();
   }

   public CssPropertyValues(Node node) throws Exception
   {
      HashMap indexPropertyValueHashMap = new HashMap();

      this.propertyValueVector = new Vector();

      //Child Nodes Are CssPropertyData.VALUE
      
      Vector cssPropertyNodeVector = 
      DomSearchHelper.getAllNodes(
         CssPropertyValueData.getInstance().NAME, node.getChildNodes());

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().STYLE))
      {
         logUtil.put("Number Of Properties: " +
            cssPropertyNodeVector.size(),this,"CssProperties()");
      }
      
      final int size = cssPropertyNodeVector.size();
      for (int index = 0; index < size; index++)      
      {
         Node cssPropertyValueNode = (Node) cssPropertyNodeVector.get(index);
         
         Node valueNode = 
            DomSearchHelper.getNode(
               DomData.VALUE,
               cssPropertyValueNode.getChildNodes());

         String propertyValue =
            DomNodeHelper.getTextNodeValue(valueNode);

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().STYLE))
         {
            logUtil.put("Property Value: " + propertyValue,this,"CssProperties()");
         }

         Node indexNode = 
            DomSearchHelper.getNode(
               DomData.INDEX, cssPropertyValueNode.getChildNodes());

         String indexValue =
            DomNodeHelper.getTextNodeValue(indexNode);

         indexPropertyValueHashMap.put(indexValue, propertyValue);
      }

      for(int index = 0; index < indexPropertyValueHashMap.keySet().size(); index++)
      {
         Integer indexInt = new Integer(index);
         String propertyValue = 
            (String) indexPropertyValueHashMap.get(indexInt.toString());
         this.propertyValueVector.add(propertyValue);
      }
   }

   /*
   public CssPropertyValues(HashMap hashMap)
   {
      this.propertyValueVector = new Vector();

      Set set = hashMap.keySet();
      iter = set;
      int index = 0;

      while(iter.hasNext())
      {
         String key = (String) iter.next();
         String value = (String) hashMap.get(key);
         this.propertyValueVector.add(value);
      }
   }
*/
   
   public HashMap toHashMap()
   {
       final CommonSeps commonSeps = CommonSeps.getInstance();
       final CssPropertyValueData cssPropertyValueData = CssPropertyValueData.getInstance();
      final HashMap hashMap = new HashMap();
      final StringBuffer stringBuffer = new StringBuffer();
      
      final int size = propertyValueVector.size();
      for (int index = 0; index < size; index++)      
      {
         String value = (String) propertyValueVector.get(index);

         stringBuffer.delete(0, stringBuffer.length());

         stringBuffer.append(cssPropertyValueData.NAME);
         stringBuffer.append(commonSeps.BRACKET_OPEN);
         stringBuffer.append(index);
         stringBuffer.append(commonSeps.BRACKET_CLOSE);

         hashMap.put(stringBuffer.toString(), value);
         
         index++;
      }

      return hashMap;
   }

   public Node toXmlNode(Document document) throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         HashMap hashMap = this.toHashMap();
         logUtil.put("CssPropertyValues HashMap: " + hashMap.toString(),this,"toXmlNode()");
      }

      return ModDomHelper.createNameValueIndexNodes(
         document, CssPropertyData.getInstance().VALUES, 
         CssPropertyValueData.getInstance().NAME, this.propertyValueVector);
   }
}
