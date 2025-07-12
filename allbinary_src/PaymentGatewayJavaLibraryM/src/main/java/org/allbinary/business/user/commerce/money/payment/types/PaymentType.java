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
package org.allbinary.business.user.commerce.money.payment.types;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayData;
import org.allbinary.business.user.commerce.money.payment.gateway.processor.PaymentProcessorInterfaceFactoryInterface;
import org.allbinary.business.user.commerce.money.payment.gateway.transaction.PaymentTransactionInterfaceFactoryInterface;

public class PaymentType //implements TableMappingInterface
{
   private final BasicPaymentType basicPaymentType;

   private final PaymentTransactionInterfaceFactoryInterface paymentTransactionInterfaceFactoryInterface;
   private final PaymentProcessorInterfaceFactoryInterface paymentProcessorInterfaceFactoryInterface;
   
   protected PaymentType(BasicPaymentType basicPaymentType)
   {
	   this.basicPaymentType = basicPaymentType;
	   
	      this.paymentTransactionInterfaceFactoryInterface =
	          null;
	       this.paymentProcessorInterfaceFactoryInterface =
	          null;
	       
	       PaymentTypeUtil.getInstance().add(this);
   }
   
   protected PaymentType(BasicPaymentType basicPaymentType,
      PaymentTransactionInterfaceFactoryInterface paymentTransactionInterfaceFactoryInterface,
      PaymentProcessorInterfaceFactoryInterface paymentProcessorInterfaceFactoryInterface)
   {
	   this.basicPaymentType = basicPaymentType;

	      this.paymentTransactionInterfaceFactoryInterface =
	          paymentTransactionInterfaceFactoryInterface;
	       this.paymentProcessorInterfaceFactoryInterface =
	          paymentProcessorInterfaceFactoryInterface;
	       
	       PaymentTypeUtil.getInstance().add(this);
   }
   
   public PaymentTransactionInterfaceFactoryInterface
      getPaymentTransactionInterfaceFactoryInterface()
      throws Exception
   {
      if(this.paymentTransactionInterfaceFactoryInterface ==  null)
      {
         throw new Exception("PaymentTransactionInterfaceFactoryInterface is Null");
      }
      return this.paymentTransactionInterfaceFactoryInterface;
   }
   
   public PaymentProcessorInterfaceFactoryInterface
      getPaymentProcessorInterfaceFactoryInterface()
      throws Exception
   {
      if(this.paymentProcessorInterfaceFactoryInterface ==  null)
      {
         throw new Exception("PaymentProcessorInterfaceFactoryInterface is Null");
      }
      return this.paymentProcessorInterfaceFactoryInterface;
   }
      
   public Vector toVector()
   {
      Vector vector = new Vector();
      
      vector.addAll(this.getBasicPaymentType().toVector());
      
      if(this.paymentTransactionInterfaceFactoryInterface != null)
      {
         vector.add(this.paymentTransactionInterfaceFactoryInterface.getClass().getName());
      }
      
      if(this.paymentProcessorInterfaceFactoryInterface != null)
      {
         vector.add(this.paymentProcessorInterfaceFactoryInterface.getClass().getName());
      }
      return vector;
   }
   
   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();
      
      hashMap.putAll(this.getBasicPaymentType().toHashMap());
      
      if(this.paymentTransactionInterfaceFactoryInterface != null)
      {
         hashMap.put(
            PaymentGatewayData.PAYMENTTRANSACTIONINTERFACEFACTORYINTERFACE.toString(),
            this.paymentTransactionInterfaceFactoryInterface.getClass().getName());
      }
      
      if(this.paymentProcessorInterfaceFactoryInterface != null)
      {
         hashMap.put(
            PaymentGatewayData.PAYMENTPROCESSORINTERFACEFACTORYINTERFACE.toString(),
            this.paymentProcessorInterfaceFactoryInterface.getClass().getName());
      }
      return hashMap;
   }

public BasicPaymentType getBasicPaymentType() {
	return basicPaymentType;
}

}
