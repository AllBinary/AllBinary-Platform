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
package org.allbinary.data.tables.user.commerce.money.payment.transaction;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class TransactionEntityFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final TransactionEntityFactory instance =
            new TransactionEntityFactory();

    /**
     * @return the instance
     */
    public static TransactionEntityFactory getInstance() {
        return instance;
    }

   //privatec final String CLASSNAME = "org.allbinary.data.tables.transaction.TransactionEntity";
   
   private TransactionEntityFactory()
   {
   }
   
   public TransactionEntity getTransactionEntityInstance() //throws LicensingException
   {
      try
      {
         //Object object = AbeFactory.getInstance().getInstance(CLASSNAME);
         //return (TransactionEntityInterface) InterfaceCastProxy.newInstance(object);
         return new org.allbinary.data.tables.user.commerce.money.payment.transaction.TransactionEntity();
      }
      /*
      catch(LicensingException e)
      {
         final CommonStrings commonStrings = CommonStrings.getInstance();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE,e);
         }
         throw e;
      }
       */
      catch(Exception e)
      {
         final CommonStrings commonStrings = CommonStrings.getInstance();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE,e);
         }
         return null;
      }
   }
}
