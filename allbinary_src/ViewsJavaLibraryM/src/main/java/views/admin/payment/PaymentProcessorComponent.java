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
package views.admin.payment;




import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.data.tree.dom.DomNodeInterface;

import org.allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.business.user.commerce.money.payment.gateway.processor.PaymentProcessorInterface;
import org.allbinary.business.user.commerce.money.payment.gateway.processor.PaymentProcessorInterfaceFactory;

import views.business.context.modules.storefront.HttpStoreComponentView;

public class PaymentProcessorComponent 
   extends HttpStoreComponentView
{
   public PaymentProcessorComponent(TransformInfoInterface transformInfoInterface) 
      throws Exception
   {
      super(transformInfoInterface);
   }

   public void addDomNodeInterfaces() throws Exception
   {
      PaymentProcessorInterface paymentProcessorInterface =
         PaymentProcessorInterfaceFactory.getInstance().getInstance(
            this.getTransformInfoInterface());
      
      paymentProcessorInterface.process();
      //If Processing works the empty basket
      
      TransformInfoHttpInterface httpTransformInfoInterface = 
         (TransformInfoHttpInterface) this.getTransformInfoInterface();

      httpTransformInfoInterface.getWeblisketSession().removeBasket();

      this.addDomNodeInterface((DomNodeInterface) paymentProcessorInterface);
   }
   
   public String view() throws Exception
   {
      try
      {
         this.addDomNodeInterfaces();
         return super.view();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "view()", e));
         }
         throw e;
      }
   }
}
