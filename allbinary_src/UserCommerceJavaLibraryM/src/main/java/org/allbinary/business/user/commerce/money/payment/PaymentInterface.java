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

public interface PaymentInterface
{
   void setName(String value);

   void setType(String value);

   void setExpiration(String value);

   void setNumber(String value);

   String getId();

   String getName();

   String getType();

   String getExpiration();

   String getEncryption();

   String getNumber();

   String getLastFour();

   boolean isDefault();
}
