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
package org.allbinary.business.user.commerce.money.payment.gateway.transaction;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.tokens.Tokenizer;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class TransactionResult implements TransactionResultInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private HashMap keyValuePairs;
   private String result;
   
   public TransactionResult(String result) throws Exception
   {
      try
      {
         this.result = result;
         Tokenizer tokenizer = new Tokenizer(CommonSeps.getInstance().AMP);
         BasicArrayList tokens = tokenizer.getTokens(result, new BasicArrayList());
         Tokenizer tokenizer2 = new Tokenizer(CommonSeps.getInstance().EQUALS);
         
         Hashtable hashtable = tokenizer2.getTokens(tokens);
         //TWB - does this actually work?
         keyValuePairs.putAll(hashtable);
      }
      catch(Exception e)
      {
         String exceptionMessage = "TransactionResult Constructor Failed";

         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().PAYMENT))
         //{
            LogUtil.put(LogFactory.getInstance(exceptionMessage, this, this.commonStrings.CONSTRUCTOR));
         //}
         //throw new PaymentException("TransactionResult Constructor Failed");
      }
   }
   
   public TransactionResult(HashMap keyValuePairs)
   {
      keyValuePairs = keyValuePairs;
      this.result ="Needs to have the hashmap converted to string for other constructor";
   }
   
   public String toString()
   {
      return result;
   }
   
   public HashMap toHashMap()
   {
      return keyValuePairs;
   }
   
   private Vector getAllPossibleKeys()
   {
	   PaymentTransactionKeysFactory paymentTransactionKeysFactory = 
		   PaymentTransactionKeysFactory.getInstance();
	   
      Vector verisignResponseKeys = new Vector();
      verisignResponseKeys.add(paymentTransactionKeysFactory.RESULT.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.PNREF.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.RESPMSG.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.AUTHCODE.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.AVSADDR.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.AVSZIP.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.ORIGRESULT.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.STATUS.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.FRAUDCODE.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.FRAUDMSG.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.ERRCODE.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.SCORE.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.REASON1.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.REASON2.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.REASON3.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.EXCEPTION1.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.EXCEPTION2.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.EXCEPTION3.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.EXCEPTION4.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.EXCEPTION5.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.EXCEPTION6.toString());
      verisignResponseKeys.add(paymentTransactionKeysFactory.EXCEPTION7.toString());      
      return verisignResponseKeys;
   }
   
   public Vector getValues()
   {
      Vector keyVector = this.getAllPossibleKeys();
      Vector valueVector = new Vector();      
      Iterator keyIter = keyVector.iterator();
      while(keyIter.hasNext())
      {
         String key = (String) keyIter.next();
         if(keyValuePairs.containsKey(key))
         {
            valueVector.add(keyValuePairs.get(key));
         }
         else
         {
            valueVector.add("");
         }
      }
      
      return valueVector;
   }      
   
   public String getPnRef()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().PNREF.toString());
   }     
         
   public String getRespMsg()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().RESPMSG.toString());
   }
   
   public String getResult()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().RESULT.toString());
   }
   
   public String getScore()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().SCORE.toString());
   }
   
   public String getStatus()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().STATUS.toString());
   }

   public String getFraudCode()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().FRAUDCODE.toString());
   }

   public String getFraudMsg()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().FRAUDMSG.toString());
   }
   
   public String getOrigResult()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().ORIGRESULT.toString());
   }
   
   public String getAvsAddr()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().AVSADDR.toString());
   }
   
   public String getAvsZip()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().AVSZIP.toString());
   }
   
   public String getErrCode()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().ERRCODE.toString());
   }
   
   public String getException1()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().EXCEPTION1.toString());
   }
   
   public String getException2()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().EXCEPTION2.toString());
   }
   
   public String getException3()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().EXCEPTION3.toString());
   }
   
   public String getException4()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().EXCEPTION4.toString());
   }
   
   public String getException5()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().EXCEPTION5.toString());
   }
   
   public String getException6()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().EXCEPTION6.toString());
   }
   
   public String getException7()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().EXCEPTION7.toString());
   }
      
   public String getReason1()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().REASON1.toString());
   }
   
   public String getReason2()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().REASON2.toString());
   }
   
   public String getReason3()
   {
      return (String) keyValuePairs.get(PaymentTransactionKeysFactory.getInstance().REASON3.toString());
   }         
}
