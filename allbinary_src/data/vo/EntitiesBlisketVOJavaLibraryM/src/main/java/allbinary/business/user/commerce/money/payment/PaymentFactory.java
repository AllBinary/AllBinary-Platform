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
package allbinary.business.user.commerce.money.payment;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

import javax.servlet.ServletRequest;

//import allbinary.logic.java.proxy.InterfaceCastProxy;

//import abcs.logic.system.loader.AbeFactory;
//import allbinary.business.user.commerce.money.payment.*;

public class PaymentFactory
{
   //private static final String CLASSNAME = 
     //      "allbinary.business.user.commerce.money.payment.Payment";

   private PaymentFactory()
   {
   }

   public static PaymentInterface getInstance(ServletRequest request) //throws LicensingException
   {
      try
      {
         return (PaymentInterface) new allbinary.business.user.commerce.money.payment.Payment(request);
      }
      /*
      catch(LicensingException e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
         {
            LogUtil.put(error,"PaymentFactory","getInstance()",e);
         }
         throw e;
      }
       */
      catch(Exception e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, "PaymentFactory", "getInstance()", e));
         }
         return null;
      }
   }
}
