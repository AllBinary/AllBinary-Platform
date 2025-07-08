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
package org.allbinary.logic.visual.transform.template.customizer.includes.globals;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.context.modules.storefront.StoreFrontView;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.logic.control.validate.Validation;
import org.allbinary.string.CommonStrings;

public class GlobalsValidation extends Validation implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();
   
    
   private StoreFrontInterface storeFrontInterface;
   
   //new
   public GlobalsValidation(String storeName)
   {
      this.storeFrontInterface = StoreFrontFactory.getInstance(storeName);
   }
   
   //from db
   public GlobalsValidation(Document document) throws Exception
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
             
            logUtil.put("Failed to validate form", this, commonStrings.IS_VALID, e);
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
            logUtil.put("Failed to generate validation error info", this, "validationInfo()", e);
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
 
   public Node toXmlNode(Document document) throws Exception
   {
      Node node = document.createElement(GlobalsData.NAME);
      node.appendChild(new StoreFrontView(
         this.storeFrontInterface).toXmlNode(document));
      return node;
   }
}