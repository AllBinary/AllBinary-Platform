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
package org.allbinary.business.user.commerce.shipping.modules;

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.user.commerce.shipping.ShippingMethodData;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.java.exception.ExceptionUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class BasicWeightShippingModuleView extends BasicWeightShippingModule implements DomNodeInterface
{
   public BasicWeightShippingModuleView()
   {
   }
   
   public Boolean isValid()
   {
      return null;
   }
   
   public org.w3c.dom.Document toValidationInfoDoc()
   {
      return null;
   }
   
   public org.w3c.dom.Node toValidationInfoNode(org.w3c.dom.Document document)
   {
      return null;
   }
   
   public String validationInfo()
   {
      return null;
   }
   
   public org.w3c.dom.Node toXmlNode(org.w3c.dom.Document document)
   {
      try
      {
         Node shippingMethodNode =
         document.createElement(
         ShippingMethodData.NAME);
         
         shippingMethodNode.appendChild(
         ModDomHelper.createNameValueNodes(
         document, ShippingMethodData.NAME, this.getName()));
         
         shippingMethodNode.appendChild(
         ModDomHelper.createNameValueNodes(
         document, ShippingMethodData.DESCRIPTION, this.getDescription()));
         
         return shippingMethodNode;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Exception", this, "toXmlNode", e));
         }
         //throw e;
         return null;
      }
   }
   
   public org.w3c.dom.Document toXmlDoc()
   {
      try
      {
         Document document = DomDocumentHelper.create();
         /*
         Node contentTypeNode =
         document.createElement(this.contentType);
         document.appendChild(contentTypeNode);
          
         contentTypeNode.appendChild(this.storeFrontInterface.toXmlNode(document));
         contentTypeNode.appendChild(this.toXmlNode(document));
          */
         return document;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Exception", this, "toXmlDoc", e));
         }
         //throw e;
         return null;
      }
   }
   
   public String view()
   {
      try
      {
         return "Not Implemented";
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Exception", this, "view", e));
         }
         //throw e;
         return null;
      }
   }
   
}
