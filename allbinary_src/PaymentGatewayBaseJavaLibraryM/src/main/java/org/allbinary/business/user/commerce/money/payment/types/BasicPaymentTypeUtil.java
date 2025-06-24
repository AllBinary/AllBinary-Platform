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


import java.util.Vector;

/**
 *
 * @author user
 */
public class BasicPaymentTypeUtil {

    private static final BasicPaymentTypeUtil instance = new BasicPaymentTypeUtil();

    static
    {
    	//BasicPaymentTypeFactory.getInstance().A1MPLATFORM.hashCode();
    }
    
    /**
     * @return the instance
     */
    public static BasicPaymentTypeUtil getInstance()
    {
        return instance;
    }

    private BasicPaymentTypeUtil()
    {

    }

   private Vector paymentTypeVector = new Vector();

   public void add(BasicPaymentType paymentType)
   {
       this.paymentTypeVector.add(paymentType);
   }

   public BasicPaymentType get(String paymentTypeString)
   throws Exception
   {
      int size = paymentTypeVector.size();
      for (int i = 0; i < size; i++)
      {
         BasicPaymentType paymentType = (BasicPaymentType) paymentTypeVector.get(i);
         if(paymentType.getName().compareTo(paymentTypeString) == 0)
         {
            return paymentType;
         }
      }
      throw new Exception("Unknown PaymentType: " + paymentTypeString);
   }


   public boolean isContain(BasicPaymentType paymentType)
   {
      return this.paymentTypeVector.contains(paymentType);
   }

   public Vector difference(Vector a_PaymentTypeVector)
   {
      Vector diff = new Vector();
      int size = paymentTypeVector.size();
      for (int i = 0; i < size; i++)
      {
         BasicPaymentType paymentType = (BasicPaymentType) paymentTypeVector.get(i);
         if(!a_PaymentTypeVector.contains(paymentType))
            diff.add(paymentType);
      }
      return diff;
   }

}
