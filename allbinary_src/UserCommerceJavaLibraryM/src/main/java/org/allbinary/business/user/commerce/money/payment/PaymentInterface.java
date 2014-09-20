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
   public void setName(String value);

   public void setType(String value);

   public void setExpiration(String value);

   public void setNumber(String value);

   public String getId();

   public String getName();

   public String getType();

   public String getExpiration();

   public String getEncryption();

   public String getNumber();

   public String getLastFour();

   public boolean isDefault();
}
