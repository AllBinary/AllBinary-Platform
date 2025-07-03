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
package admin.taghelpers;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import org.allbinary.logic.visual.transform.info.TransformInfoHttpFactory;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpInterface;

import views.admin.payment.PaymentProcessorComponent;

public class PaymentProcessHelper
    extends TagHelper
{
   private PaymentProcessorComponent paymentProcessorComponent;

   public PaymentProcessHelper(HashMap propertiesHashMap, PageContext pageContext) throws Exception
   {
      TransformInfoHttpInterface transformInfoInterface = 
         (TransformInfoHttpInterface) TransformInfoHttpFactory.getInstance(
            propertiesHashMap, pageContext);

      this.paymentProcessorComponent = 
         new PaymentProcessorComponent(transformInfoInterface);
   }

   public String process() throws Exception
   {
      return this.paymentProcessorComponent.view();
   }   
}
