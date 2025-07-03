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
package org.allbinary.business.user.commerce.money.payment;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

import javax.servlet.ServletRequest;
import org.allbinary.string.CommonStrings;

//import org.allbinary.logic.java.proxy.InterfaceCastProxy;

//import org.allbinary.logic.system.loader.AbeFactory;
//import org.allbinary.business.user.commerce.money.payment.*;

public class PaymentFactory
{
   //private static final String CLASSNAME = 
     //      "org.allbinary.business.user.commerce.money.payment.Payment";

   private PaymentFactory()
   {
   }

   public static PaymentInterface getInstance(ServletRequest request) //throws LicensingException
   {
      try
      {
         return (PaymentInterface) new org.allbinary.business.user.commerce.money.payment.Payment(request);
      }
      /*
      catch(LicensingException e)
      {
         final CommonStrings commonStrings = CommonStrings.getInstance();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FACTORYERROR))
         {
            LogUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE,e);
         }
         throw e;
      }
       */
      catch(Exception e)
      {
         final CommonStrings commonStrings = CommonStrings.getInstance();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, "PaymentFactory", commonStrings.GET_INSTANCE, e));
         }
         return null;
      }
   }
}
