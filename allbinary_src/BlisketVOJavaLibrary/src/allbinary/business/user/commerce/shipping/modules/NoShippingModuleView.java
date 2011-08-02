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
package allbinary.business.user.commerce.shipping.modules;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.user.commerce.shipping.ShippingMethodData;
import allbinary.data.tree.dom.DomNodeInterface;
import allbinary.data.tree.dom.ModDomHelper;
import org.w3c.dom.Node;

public class NoShippingModuleView extends NoShippingModule implements DomNodeInterface
{
   public NoShippingModuleView()
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
   
   public org.w3c.dom.Document toXmlDoc()
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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"toXmlNode",e));
         }
         //throw e;
         return null;
      }
   }
   
   public String validationInfo()
   {
      return null;
   }
   
   public String view()
   {
      return null;
   }
}
