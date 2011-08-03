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
package allbinary.business.user.commerce.money.payment.gateway;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.data.tree.dom.DomNodeInterface;
import allbinary.data.tree.dom.ModDomHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.HashMap;

public class PaymentGatewayView implements DomNodeInterface
{
   private PaymentGatewayInterface paymentGatewayInterface;
   
   public PaymentGatewayView(PaymentGatewayInterface paymentGatewayInterface)  //throws Exception
   {
      this.paymentGatewayInterface = paymentGatewayInterface;
   }

   public PaymentGatewayInterface getPaymentGatewayInterface()
   {
      return this.paymentGatewayInterface;
   }

   public HashMap toHashMap() throws Exception
   {
      HashMap hashMap = new HashMap();
      return hashMap;
   }

   public Node toXmlNode(Document document) throws Exception
   {
      try
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
         {
            LogUtil.put(LogFactory.getInstance("Start", this, "toXmlNode"));
         }

         HashMap hashMap = new PaymentGatewayMapping(
            this.paymentGatewayInterface).toHashMap();

         hashMap.putAll(this.toHashMap());

         Node paymentGatewayNode = 
            ModDomHelper.createNodeWithValueNodes(
               document, PaymentGatewayData.NAME.toString(), hashMap);
         
         return paymentGatewayNode;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.XSLLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed", this, "toXmlNode", e));
         }
         throw e;
      }
   }
}
