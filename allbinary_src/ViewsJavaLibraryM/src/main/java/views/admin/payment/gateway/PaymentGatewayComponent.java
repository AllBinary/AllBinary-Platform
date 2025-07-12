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

import org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayInterface;
import org.allbinary.business.user.commerce.money.payment.gateway.modules.gateway.PaymentGatewayDomNodeFactoryInterface;
import org.allbinary.business.user.commerce.money.payment.types.BasicPaymentType;
import org.allbinary.business.user.commerce.money.payment.types.BasicPaymentTypeUtil;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import views.business.context.modules.storefront.HttpStoreComponentView;

public class PaymentGatewayComponent extends HttpStoreComponentView
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, this, "view()", e);
         }
         return error;
      }
   }   
}
