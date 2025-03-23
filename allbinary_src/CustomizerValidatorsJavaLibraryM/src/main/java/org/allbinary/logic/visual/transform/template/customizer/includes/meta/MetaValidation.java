/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* III
* Created By: Travis Berthelot
* 
*/
package org.allbinary.logic.visual.transform.template.customizer.includes.meta;

import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.control.validate.ValidationInterface;
import org.allbinary.logic.visual.dhtml.html.head.meta.HtmlMetaAttributeData;
import org.allbinary.logic.visual.dhtml.html.head.meta.HtmlMetaAttributeDataFactory;
import org.allbinary.logic.visual.dhtml.html.head.meta.HtmlMetaData;

public class MetaValidation implements ValidationInterface, DomNodeInterface
{
   private String label;
   private String name;
   private String httpEquiv;
   private String content;

   //new
   public MetaValidation(HtmlMetaAttributeData htmlMetaAttributeData, 
      String label, String attributeValue, String contentValue)
   {
      this.label = label;

      if(htmlMetaAttributeData.equals(HtmlMetaAttributeDataFactory.getInstance().HTTP_EQUIV))
      {
         this.httpEquiv = attributeValue;
      }
      else
      if(htmlMetaAttributeData.equals(HtmlMetaAttributeDataFactory.getInstance().NAME))
      {
         this.name = attributeValue;
      }
      this.content = contentValue;
   }
   
   //from db
   public MetaValidation(Document document) throws Exception
   {
      throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
      //this.storeFrontInterface = new StoreFront(document);
   }
   
   //with request data
   /*
   public GlobalsValidation(HashMap hashMap)
   {
      
   }
     */
   
   public Boolean isValid()
   {
      try
      {
         Boolean valid = Boolean.TRUE;

         /*
         if(!this.isValid().booleanValue() && !this.logo.isValid().booleanValue())
         {
            valid = Boolean.FALSE;
         }
         */

         return valid;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate form", this, "isValid()", e));
         }
         return Boolean.FALSE;
      }
   }
   
   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();

         /*
         if(!this.isValid().booleanValue() && !this.isValid().booleanValue())
         {
            stringBuffer.append("Include a title or logo for the header.");
         }
         */

         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate validation error info", this, "validationInfo()", e));
         }
         return "Error Validating Form";
      }
   }
   
   public Document toValidationInfoDoc()
   {
      return null;
   }
   
   public Node toValidationInfoNode(Document document)
   {
      return null;
   }
 
   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();

      HtmlMetaAttributeDataFactory htmlMetaAttributeDataFactory = 
    	  HtmlMetaAttributeDataFactory.getInstance();
      
      hashMap.put(HtmlMetaData.getInstance().LABEL.toString(), this.label);
      hashMap.put(htmlMetaAttributeDataFactory.NAME.toString(), this.name);
      hashMap.put(htmlMetaAttributeDataFactory.HTTP_EQUIV.toString(), this.httpEquiv);
      hashMap.put(htmlMetaAttributeDataFactory.CONTENT.toString(), this.content);

      return hashMap;
   }

   public Node toXmlNode(Document document) throws Exception
   {
      Node node = 
         ModDomHelper.createNameValueNodes(
            document, HtmlMetaData.getInstance().NAME.toString(), this.toHashMap());
      return node;
   }
}