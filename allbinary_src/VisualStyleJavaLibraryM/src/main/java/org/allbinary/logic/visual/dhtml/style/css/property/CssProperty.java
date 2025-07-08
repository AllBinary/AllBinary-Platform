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

public class CssProperty implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private String propertyName;
   private CssPropertyValues cssPropertyValues;
   
   public CssProperty(String name)
   {
      this.propertyName = name;
      this.cssPropertyValues = new CssPropertyValues();
   }

   /*
    *<CSS_PROPERTY_NAME>
    *   <name>CSS_PROPERTY_NAME</name>
    *   <value>propertyName</value>
    *   <CSS_PROPERTY_VALUES_NAME>
    *      <CSS_PROPERTY_VALUE_NAME>value</CSS_PROPERTY_VALUE_NAME>
    *   <CSS_PROPERTY_VALUES_NAME>
    *</CSS_PROPERTY_NAME>
    *CssPropertyData.NAME
    */
   public CssProperty(Node node) throws Exception
   {      
      Node propertyNode = 
         DomSearchHelper.getNode(
            DomData.VALUE,
            node.getChildNodes());
      
      this.propertyName = DomNodeHelper.getTextNodeValue(propertyNode);

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().STYLE))
      {
         logUtil.put("Name: " + this.getName(),this,"CssProperty()");
      }

      Node propertyValuesNode =
         DomSearchHelper.getNode(
            CssPropertyData.getInstance().VALUES,
            node.getChildNodes());
      
      this.cssPropertyValues = new CssPropertyValues(propertyValuesNode);
   }

   /*
   public CssProperty(HashMap hashMap)
   {
      this.propertyName = (String) hashMap.get(CssPropertyData.NAME);

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().STYLE))
      {
         logUtil.put("Name: " + this.getName(),this,"CssProperty()");
      }

      HashMap propertyValuesHashMap = 
         (HashMap) hashMap.get(CssPropertyData.VALUES);
      
      this.cssPropertyValues = new CssPropertyValues(propertyValuesHashMap);
   }
*/
   
   public String getName()
   {
      return this.propertyName;
   }
   
   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();
      hashMap.put(CssPropertyData.getInstance().NAME, this.propertyName);
      return hashMap;      
   }

   public Node toXmlNode(Document document) throws Exception
   {
      HashMap hashMap = this.toHashMap();

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         logUtil.put("CssView HashMap: " + hashMap.toString(),this,"toXmlNode()");
      }

      Node node = ModDomHelper.createNameValueNodes(
         document, CssPropertyData.getInstance().NAME, this.propertyName);

      node.appendChild(this.cssPropertyValues.toXmlNode(document));

      return node;
   }
}
