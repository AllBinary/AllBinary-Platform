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
package org.allbinary.business.user.commerce.money.payment.gateway;

import org.allbinary.data.tables.TableMappingInterface;

import java.util.HashMap;
import java.util.Vector;

public class PaymentGatewayMapping implements TableMappingInterface
{   
   private PaymentGatewayInterface paymentGatewayInterface;
   
   public PaymentGatewayMapping(PaymentGatewayInterface paymentGatewayInterface)
   {
      this.paymentGatewayInterface = paymentGatewayInterface;
   }

   //This is used to update db entries
   public HashMap toHashMap() throws Exception
   {
      return this.paymentGatewayInterface.toHashMap(false);
   }   
   
   //This is used for db updates
   public Vector toVector()
   {
      return this.paymentGatewayInterface.toVector(false);
   }
   
   public Object getKey() throws Exception
   {
      return (Object) this.paymentGatewayInterface.getKey();
   }   
}