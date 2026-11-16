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

import org.allbinary.business.user.commerce.shipping.ShippingMethodData;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.communication.log.LogUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;

public class BasicWeightShippingModuleView extends BasicWeightShippingModule implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   public BasicWeightShippingModuleView()
   {
   }
   
   public Boolean isValid()
   {
      return null;
   }
   
   public Document toValidationInfoDoc()
   {
      return null;
   }
   
   public Node toValidationInfoNode(Document document)
   {
      return null;
   }
   
   public String validationInfo()
   {
      return null;
   }
   
   public Node toXmlNode(Document document)
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
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().SQLLOGGING))
         {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, "toXmlNode", e);
         }
         //throw e;
         return null;
      }
   }
   
   public Document toXmlDoc()
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
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().SQLLOGGING))
         {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, "toXmlDoc", e);
         }
         //throw e;
         return null;
      }
   }
   
   public String view()
   {
      try
      {
         return commonStrings.NOT_IMPLEMENTED;
      }
      catch(Exception e)
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().SQLLOGGING))
         {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, "view", e);
         }
         //throw e;
         return null;
      }
   }
   
}
