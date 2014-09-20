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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class CssElementView implements DomNodeInterface
{
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
      this.title = "";
      this.description = "";
      this.value = "";
   }
   
   public CssElementView(Node node) throws Exception
   {
      try
      {
         Node valueNode =
            DomSearchHelper.getNode(
               DomData.VALUE,
               node.getChildNodes());

         this.value = DomNodeHelper.getTextNodeValue(valueNode);
         
         Node titleValueNode =
            DomSearchHelper.getNodeNoThrow(TITLE, node.getChildNodes());
         
         if(titleValueNode != null)
         {
            this.title = DomNodeHelper.getTextNodeValue(titleValueNode);
         }
         else
         {
            this.title = "";
         }
         
         Node descriptionValueNode =
            DomSearchHelper.getNodeNoThrow(DESCRIPTION, node.getChildNodes());
         
         if(descriptionValueNode != null)
         {
            this.description = DomNodeHelper.getTextNodeValue(descriptionValueNode);
         }
         else
         {
            this.description = "";
         }

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.VIEW))
         {
            LogUtil.put(LogFactory.getInstance("Value: " + this.value +
                    "\nTitle: " + this.title +
                    "\nDescription: " + this.description
                    ,this,"Constructor()"));
         }
         
         //Nodes with CssElementData.NAME
         Vector cssElementStyleNodeVector =
         DomSearchHelper.getAllNodes(
            CssElementData.getInstance().NAME, node.getChildNodes());

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.VIEW))
         {
            LogUtil.put(LogFactory.getInstance("Child Css Elements: " + cssElementStyleNodeVector.size(),
               this, "CssElementView()"));
         }

         this.cssElementVector =
            CssElementsValidationFactory.getInstance(cssElementStyleNodeVector);

         NodeList nodeList = node.getChildNodes();
         this.cssPropertyVector =
            CssPropertiesValidationFactory.getInstance(nodeList);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Value: " + this.value +
                    "\nTitle: " + this.title +
                    "\nDescription: " + this.description
                    ,this,"Constructor()",e));
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
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("CssView HashMap: " + hashMap.toString(),this,"toXmlNode"));
      }
      
      Node node = ModDomHelper.createNodeWithValueNodes(
      document, CssElementData.getInstance().NAME, hashMap);
      
      Iterator iter = this.cssPropertyVector.iterator();
      while(iter.hasNext())
      {
         DomNodeInterface cssPropertyDomNodeInterface = (DomNodeInterface) iter.next();
         node.appendChild(cssPropertyDomNodeInterface.toXmlNode(document));
      }
      
      Iterator elementIter = this.cssElementVector.iterator();
      while(elementIter.hasNext())
      {
         DomNodeInterface cssElementDomNodeInterface = (DomNodeInterface) elementIter.next();
         node.appendChild(cssElementDomNodeInterface.toXmlNode(document));
      }
      
      return node;
   }
}