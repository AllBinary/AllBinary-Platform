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
import java.util.Iterator;
import java.util.Vector;

public class CssPropertyValues implements DomNodeInterface
{
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

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.STYLE))
      {
         LogUtil.put(LogFactory.getInstance("Number Of Properties: " +
            cssPropertyNodeVector.size(),this,"CssProperties()"));
      }
      
      Iterator iter = cssPropertyNodeVector.iterator();
      while(iter.hasNext())
      {
         Node cssPropertyValueNode = (Node) iter.next();
         
         Node valueNode = 
            DomSearchHelper.getNode(
               DomData.VALUE,
               cssPropertyValueNode.getChildNodes());

         String propertyValue =
            DomNodeHelper.getTextNodeValue(valueNode);

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.STYLE))
         {
            LogUtil.put(LogFactory.getInstance("Property Value: " + propertyValue,this,"CssProperties()"));
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
      Iterator iter = set.iterator();
      int index = 0;

      while(iter.hasNext())
      {
         String key = (String) iter.next();
         String value = (String) hashMap.get(key);
         this.propertyValueVector.add(value);
      }
   }
*/
   private final String OPEN_BRACKET = "[";
   private final String CLOSE_BRACKET = "]";
   
   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();
      Iterator iter = this.propertyValueVector.iterator();
      int index = 0;

      StringBuffer stringBuffer = new StringBuffer();
      
      while(iter.hasNext())
      {
         String value = (String) iter.next();

         stringBuffer.delete(0, stringBuffer.length());

         stringBuffer.append(CssPropertyValueData.getInstance().NAME);
         stringBuffer.append(OPEN_BRACKET);
         stringBuffer.append(index);
         stringBuffer.append(CLOSE_BRACKET);

         hashMap.put(stringBuffer.toString(), value);
         
         index++;
      }

      return hashMap;
   }

   public Node toXmlNode(Document document) throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         HashMap hashMap = this.toHashMap();
         LogUtil.put(LogFactory.getInstance("CssPropertyValues HashMap: " + hashMap.toString(),this,"toXmlNode()"));
      }

      return ModDomHelper.createNameValueIndexNodes(
         document, CssPropertyData.getInstance().VALUES, 
         CssPropertyValueData.getInstance().NAME, this.propertyValueVector);
   }
}
