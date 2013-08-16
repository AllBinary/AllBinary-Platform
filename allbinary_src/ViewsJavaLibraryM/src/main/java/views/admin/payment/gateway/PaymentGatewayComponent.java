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

import views.business.context.modules.storefront.HttpStoreComponentView;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayInterface;
import allbinary.business.user.commerce.money.payment.gateway.modules.gateway.PaymentGatewayDomNodeFactoryInterface;
import allbinary.business.user.commerce.money.payment.types.BasicPaymentType;
import allbinary.business.user.commerce.money.payment.types.BasicPaymentTypeUtil;
import allbinary.data.tree.dom.DomNodeInterface;
import allbinary.logic.visual.transform.info.TransformInfoInterface;

public class PaymentGatewayComponent extends HttpStoreComponentView
{
   private PaymentGatewayInterface paymentGatewayInterface;
   
   public PaymentGatewayComponent(
      TransformInfoInterface transformInfoInterface, 
      PaymentGatewayInterface paymentGatewayInterface) throws Exception
   {
      super(transformInfoInterface);      
      this.paymentGatewayInterface = paymentGatewayInterface;
   }
   
   public void addDomNodeInterfaces() throws Exception
   {
      BasicPaymentType paymentType = 
    	  BasicPaymentTypeUtil.getInstance().get(this.paymentGatewayInterface.getName());

      PaymentGatewayDomNodeFactoryInterface 
         paymentGatewayDomNodeFactoryInterface = 
            paymentType.getPaymentGatewayDomNodeFactoryInterface();

      DomNodeInterface domNodeInterface = (DomNodeInterface)
         paymentGatewayDomNodeFactoryInterface.getInstance(
            this.paymentGatewayInterface);

      this.addDomNodeInterface(domNodeInterface);
      //new PaymentGatewayView()
   }

   public String view()
   {
      try
      {
         this.addDomNodeInterfaces();
         return super.view();
      }
      catch(Exception e)
      {
         String error = "Failed to view payment gateway";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "view()", e));
         }
         return error;
      }
   }   
}
