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
package org.allbinary.business.user.commerce.money.payment.gateway;

import java.util.HashMap;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.string.CommonStrings;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class PaymentGatewayView implements DomNodeInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private PaymentGatewayInterface paymentGatewayInterface;
   
   public PaymentGatewayView(final PaymentGatewayInterface paymentGatewayInterface)  //throws Exception
   {
      this.paymentGatewayInterface = paymentGatewayInterface;
   }

   public PaymentGatewayInterface getPaymentGatewayInterface()
   {
      return this.paymentGatewayInterface;
   }

   public HashMap toHashMap() throws Exception
   {
      final HashMap hashMap = new HashMap();
      return hashMap;
   }

   public Node toXmlNode(final Document document) throws Exception
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "toXmlNode"));
         }

         final HashMap hashMap = new PaymentGatewayMapping(
            this.paymentGatewayInterface).toHashMap();

         hashMap.putAll(this.toHashMap());

         final Node paymentGatewayNode = 
            ModDomHelper.createNodeWithValueNodes(
               document, PaymentGatewayData.NAME.toString(), hashMap);
         
         return paymentGatewayNode;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().XSLLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "toXmlNode", e));
         }
         throw e;
      }
   }
}
