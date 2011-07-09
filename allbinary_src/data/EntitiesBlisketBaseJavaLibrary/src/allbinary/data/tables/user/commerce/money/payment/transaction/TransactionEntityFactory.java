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
package allbinary.data.tables.user.commerce.money.payment.transaction;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class TransactionEntityFactory
{
    private static final TransactionEntityFactory instance =
            new TransactionEntityFactory();

    /**
     * @return the instance
     */
    public static TransactionEntityFactory getInstance() {
        return instance;
    }

   //privatec final String CLASSNAME = "allbinary.data.tables.transaction.TransactionEntity";
   
   private TransactionEntityFactory()
   {
   }
   
   public TransactionEntity getTransactionEntityInstance() //throws LicensingException
   {
      try
      {
         //Object object = AbeFactory.getInstance(CLASSNAME);
         //return (TransactionEntityInterface) InterfaceCastProxy.newInstance(object);
         return new allbinary.data.tables.user.commerce.money.payment.transaction.TransactionEntity();
      }
      /*
      catch(LicensingException e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(error,"TransactionEntityFactory","getInstance()",e);
         }
         throw e;
      }
       */
      catch(Exception e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,"TransactionEntityFactory","getInstance()",e));
         }
         return null;
      }
   }
}
