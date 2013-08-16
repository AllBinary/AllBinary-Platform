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

import java.util.Vector;

import views.admin.payment.gateway.PaymentGatewayViewAbstract;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.user.commerce.money.payment.types.BasicPaymentTypeUtil;
import allbinary.data.tables.user.commerce.money.payment.gateway.PaymentGatewayEntityFactory;
import allbinary.data.tables.user.commerce.money.payment.gateway.PaymentGatewayEntityInterface;
import allbinary.logic.visual.transform.info.TransformInfoInterface;

public class SelectNewView extends PaymentGatewayViewAbstract
{
   public SelectNewView(TransformInfoInterface transformInfoInterface) throws Exception
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

         Vector allGatewaysLessExisting = BasicPaymentTypeUtil.getInstance().difference(existingGateways);

         return new PaymentGatewaysView(
            this.getTransformInfoInterface(), allGatewaysLessExisting).view();
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
             String error = "Failed edit payment gateway";
             LogUtil.put(LogFactory.getInstance(error, this, "view", e));
         }
         throw e;
      }
   }
}
