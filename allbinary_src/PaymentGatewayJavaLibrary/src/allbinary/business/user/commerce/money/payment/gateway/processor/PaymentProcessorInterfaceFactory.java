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
package allbinary.business.user.commerce.money.payment.gateway.processor;

import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.user.commerce.money.payment.gateway.transaction.PaymentTransactionInterface;
import allbinary.business.user.commerce.money.payment.gateway.transaction.PaymentTransactionInterfaceRequestFactory;
import allbinary.business.user.commerce.money.payment.types.PaymentType;
import allbinary.business.user.commerce.money.payment.types.PaymentTypeUtil;
import allbinary.logic.visual.transform.info.TransformInfoInterface;

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
            PaymentTransactionInterfaceRequestFactory.getInstance(
               transformInfoInterface);

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
         String error = "Error Getting PaymentProcessorInterface";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PAYMENTERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "getInstance()", e));
         }
         throw e;
      }
   }
}
