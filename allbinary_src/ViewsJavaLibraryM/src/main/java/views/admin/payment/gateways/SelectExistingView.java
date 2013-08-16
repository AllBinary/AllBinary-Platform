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
package views.admin.payment.gateways;

import abcs.logic.communication.log.LogFactory;
import java.util.Vector;

import abcs.logic.communication.log.LogUtil;


import allbinary.logic.visual.transform.info.TransformInfoInterface;

import allbinary.data.tables.user.commerce.money.payment.gateway.PaymentGatewayEntityFactory;
import allbinary.data.tables.user.commerce.money.payment.gateway.PaymentGatewayEntityInterface;

import views.admin.payment.gateway.PaymentGatewayViewAbstract;

public class SelectExistingView extends PaymentGatewayViewAbstract
{
   public SelectExistingView(TransformInfoInterface transformInfoInterface)
   {
      super(transformInfoInterface);
   }

   public String view() throws Exception
   {      
      try
      {
         PaymentGatewayEntityInterface paymentGatewayEntityInterface = 
            PaymentGatewayEntityFactory.getInstance();

         Vector existingGateways =
            paymentGatewayEntityInterface.findPaymentTypeVectorByStore(
               this.getPaymentGatewayPrimaryKey().getStoreName());
         
         return new PaymentGatewaysView(
            this.getTransformInfoInterface(), existingGateways).view();
      }
      catch(Exception e)
      {
         String error = "Failed";

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "view", e));
         }
         throw e;
      }
   }
}
