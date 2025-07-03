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
package org.allbinary.business.user.commerce.money.payment.gateway.processor;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.user.commerce.money.payment.gateway.transaction.PaymentTransactionInterface;
import org.allbinary.business.user.commerce.money.payment.gateway.transaction.PaymentTransactionInterfaceRequestFactory;
import org.allbinary.business.user.commerce.money.payment.types.PaymentType;
import org.allbinary.business.user.commerce.money.payment.types.PaymentTypeUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.string.CommonStrings;

public class PaymentProcessorInterfaceFactory
{
	private static final PaymentProcessorInterfaceFactory instance = new PaymentProcessorInterfaceFactory();
	
	public static PaymentProcessorInterfaceFactory getInstance() {
		return instance;
	}

   private PaymentProcessorInterfaceFactory()
   {
   }

   public synchronized PaymentProcessorInterface getInstance(
      TransformInfoInterface transformInfoInterface)
      throws Exception
   {
      try
      {
         PaymentTransactionInterface paymentTransactionInterface =
            PaymentTransactionInterfaceRequestFactory.getInstance().getInstance(transformInfoInterface);

         //HashMap propertiesHashMap = transformInfoInterface.getPropertiesHashMap();
         //PageContext pageContext = transformInfoInterface.getPageContext();

         String gatewayName = 
            paymentTransactionInterface.getOrderHistory().getPaymentMethod();

         if(!StringValidationUtil.getInstance().isEmpty(gatewayName))
         {
            /*
            propertiesHashMap.put(TransformInfoData.NAME, "Verisign Processor View");
            propertiesHashMap.put(StoreFrontData.NAME,this.storeName);
            propertiesHashMap.put(TransformInfoData.OBJECTFILENAME, this.viewFile);
            propertiesHashMap.put(TransformInfoData.TEMPLATEFILENAME, this.templateFile);
            propertiesHashMap.put(TransformInfoData.DATAFILENAME, this.dataFile);
            propertiesHashMap.put(TransformInfoData.OBJECTCONFIGFILENAME, this.objectConfigFile);
             */
            //TransformInfoInterface storeTransformInfoInterface = (TransformInfoInterface)
            //new StoreTransformInfo(propertiesHashMap, pageContext);
            //storeTransformInfoInterface

            PaymentType paymentType = PaymentTypeUtil.getInstance().get(gatewayName);
            
            PaymentProcessorInterfaceFactoryInterface 
               paymentProcessorInterfaceFactoryInterface = 
                  paymentType.getPaymentProcessorInterfaceFactoryInterface();

            return (PaymentProcessorInterface) 
               paymentProcessorInterfaceFactoryInterface.getInstance(
                  transformInfoInterface, paymentTransactionInterface);
         }

         throw new Exception("Error Getting PaymentProcessorInterface");
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PAYMENTERROR))
         {
             final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE, e));
         }
         throw e;
      }
   }
}
