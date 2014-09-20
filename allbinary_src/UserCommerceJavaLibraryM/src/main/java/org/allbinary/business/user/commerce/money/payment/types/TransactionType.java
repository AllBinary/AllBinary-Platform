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

public class TransactionType
{      
   private String name;
   
   public TransactionType(String name)
   {
      this.name = name;
   }

   public boolean isEqual(TransactionType transactionType)
   {
      if(this.toString().compareTo(transactionType.toString())==0)
         return true;
      else return false;
   }
   
   public String toString()
   {
      return name;
   }
}
