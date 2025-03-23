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

import org.allbinary.string.CommonSeps;
import org.allbinary.business.context.modules.storefront.StoreFront;

public class PaymentGatewayPrimaryKey implements java.io.Serializable
{
   private String storeName;
   private String name;

   public PaymentGatewayPrimaryKey(String storeName, String gatewayName)
   {
      this.setStoreName(storeName);
      this.setName(gatewayName);
   }

   public String getStoreName()
   {
      return storeName;
   }

   public void setStoreName(String storeName)
   {
      this.storeName = storeName;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }
   
   public String toString()
   {
       StringBuffer stringBuffer = new StringBuffer();

       stringBuffer.append(PaymentGatewayPrimaryKey.class.getName());
       stringBuffer.append(StoreFront.class.getName());
       stringBuffer.append(" =");
       stringBuffer.append(this.getStoreName());
       stringBuffer.append(CommonSeps.getInstance().SPACE);
       stringBuffer.append(PaymentGateway.class.getName());
       stringBuffer.append("=");
       stringBuffer.append(this.getName());

      return stringBuffer.toString();
   }
}
