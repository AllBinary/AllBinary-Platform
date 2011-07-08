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
package views.admin.payment.gateway;

import javax.servlet.http.HttpServletRequest;

import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayInterface;
import allbinary.business.user.commerce.money.payment.types.BasicPaymentTypeUtil;
import allbinary.data.tables.user.commerce.money.payment.gateway.PaymentGatewayEntityFactory;
import allbinary.logic.visual.transform.info.TransformInfoInterface;


public class GatewayView extends PaymentGatewayViewAbstract
{
   public GatewayView(TransformInfoInterface transformInfoInterface) //throws Exception
   {
      super(transformInfoInterface);
   }

   public void getFormData(HttpServletRequest request)
   {
       super.getFormData(request);

      if(StringValidationUtil.getInstance().isEmpty(this.getPaymentGatewayPrimaryKey().getName()))
      {
         this.getPaymentGatewayPrimaryKey().setName(
            this.getWeblisketSession().getPaymentMethod());
      }
   }

   public String view() throws Exception
   {
      try
      {
         PaymentGatewayInterface paymentGatewayInterface =
            PaymentGatewayEntityFactory.getInstance().getPaymentGatewayInterface(
               this.getPaymentGatewayPrimaryKey().getStoreName(), 
               BasicPaymentTypeUtil.getInstance().get(this.getPaymentGatewayPrimaryKey().getName()));

         return new PaymentGatewayComponent(
            this.getTransformInfoInterface(), paymentGatewayInterface).view();
      }
      catch(Exception e)
      {
         String error = "Failed edit PaymentGateway";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "edit()", e));
         }
         throw e;
      }
   }
}
