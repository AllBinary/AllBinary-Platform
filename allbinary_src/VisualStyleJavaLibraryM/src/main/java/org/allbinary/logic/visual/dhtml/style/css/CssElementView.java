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
package org.allbinary.logic.visual.dhtml.style.css;

import java.util.HashMap;

import java.util.Vector;

import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.DomData;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.visual.dhtml.style.css.property.CssPropertiesValidationFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;

public class CssElementView implements DomNodeInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private final String TITLE = "title";
   private final String DESCRIPTION = "description";
   
   private String value;
   private String title;
   private String description;
   
   protected Vector cssElementVector;
   protected Vector cssPropertyVector;
   
   public CssElementView()
   {
      this.cssPropertyVector = new Vector();
      final StringUtil stringUtil = StringUtil.getInstance();
      this.title = stringUtil.EMPTY_STRING;
      this.description = stringUtil.EMPTY_STRING;
      this.value = stringUtil.EMPTY_STRING;
   }
   
   public CssElementView(Node node) throws Exception
   {
      try
      {
         final Node valueNode =
            DomSearchHelper.getNode(
               DomData.VALUE,
               node.getChildNodes());

         this.value = DomNodeHelper.getTextNodeValue(valueNode);
         
         final Node titleValueNode =
            DomSearchHelper.getNodeNoThrow(TITLE, node.getChildNodes());
         
         if(titleValueNode != null)
         {
            this.title = DomNodeHelper.getTextNodeValue(titleValueNode);
         }
         else
         {
             final StringUtil stringUtil = StringUtil.getInstance();
            this.title = stringUtil.EMPTY_STRING;
         }
         
         final Node descriptionValueNode =
            DomSearchHelper.getNodeNoThrow(DESCRIPTION, node.getChildNodes());
         
         if(descriptionValueNode != null)
         {
            this.description = DomNodeHelper.getTextNodeValue(descriptionValueNode);
         }
         else
         {
            this.description = "";
         }

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
             final CommonStrings commonStrings = CommonStrings.getInstance();

            LogUtil.put(LogFactory.getInstance(new StringMaker().append("Value: ").append(this.value).append("\nTitle: ").append(this.title).append("\nDescription: ").append(this.description).toString(), this, commonStrings.CONSTRUCTOR));
         }
         
         //Nodes with CssElementData.NAME
         Vector cssElementStyleNodeVector =
         DomSearchHelper.getAllNodes(
            CssElementData.getInstance().NAME, node.getChildNodes());

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            LogUtil.put(LogFactory.getInstance("Child Css Elements: " + cssElementStyleNodeVector.size(),
               this, "CssElementView()"));
         }

         this.cssElementVector =
            CssElementsValidationFactory.getInstance(cssElementStyleNodeVector);

         final NodeList nodeList = node.getChildNodes();
         this.cssPropertyVector =
            CssPropertiesValidationFactory.getInstance(nodeList);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance(new StringMaker().append("Value: ").append(this.value).append("\nTitle: ").append(this.title).append("\nDescription: ").append(this.description).toString(), this, this.commonStrings.CONSTRUCTOR,e));
         }
         throw new Exception("CssElementView");
      }
   }
   
   public String getValue()
   {
      return this.value;
   }
   
   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();
      //hashMap.put(CssElementData.NAME, this.name);
      hashMap.put(DomData.VALUE, this.value);
      hashMap.put(this.TITLE, this.title);
      hashMap.put(this.DESCRIPTION, this.description);
      return hashMap;
   }
   
   public Node toXmlNode(Document document) throws Exception
   {
      HashMap hashMap = this.toHashMap();
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         LogUtil.put(LogFactory.getInstance("CssView HashMap: " + hashMap.toString(),this,"toXmlNode"));
      }
      
      Node node = ModDomHelper.createNodeWithValueNodes(
      document, CssElementData.getInstance().NAME, hashMap);
      
      final int size = cssPropertyVector.size();
      for (int index = 0; index < size; index++)
      {
         DomNodeInterface cssPropertyDomNodeInterface = (DomNodeInterface) cssPropertyVector.get(index);
         node.appendChild(cssPropertyDomNodeInterface.toXmlNode(document));
      }
      
      final int size2 = cssElementVector.size();
      for (int index = 0; index < size2; index++)
      {
         DomNodeInterface cssElementDomNodeInterface = (DomNodeInterface) cssElementVector.get(index);
         node.appendChild(cssElementDomNodeInterface.toXmlNode(document));
      }
      
      return node;
   }
}