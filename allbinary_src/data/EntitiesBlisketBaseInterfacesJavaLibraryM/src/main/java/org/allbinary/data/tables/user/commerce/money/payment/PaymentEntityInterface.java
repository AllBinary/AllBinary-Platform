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
package org.allbinary.data.tables.user.commerce.money.payment;

import java.util.Vector;

import org.allbinary.business.user.commerce.money.payment.PaymentInterface;
import org.allbinary.data.tables.BasicDataTableInterface;

public interface PaymentEntityInterface extends BasicDataTableInterface
{         
   public String getLastId(String userName);
   
   public void setDefault(String userName, Integer index);
      
   public PaymentInterface getDefault(String userName);

   public Vector get(String userName);
   
   public void add(String userName, PaymentInterface paymentInterface);

   public void remove(String userName, Integer index);
   
   public void insert(Vector values);

   //public void update(HashMap updatedValues);
}
