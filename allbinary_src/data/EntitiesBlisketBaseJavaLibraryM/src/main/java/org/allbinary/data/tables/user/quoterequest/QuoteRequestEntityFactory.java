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
package org.allbinary.data.tables.user.quoterequest;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class QuoteRequestEntityFactory
{
    private static final QuoteRequestEntityFactory instance =
            new QuoteRequestEntityFactory();

    /**
     * @return the instance
     */
    public static QuoteRequestEntityFactory getInstance() {
        return instance;
    }

   //private final String CLASSNAME = "org.allbinary.data.tables.user.quotes.QuoteRequestEntity";
   
   private QuoteRequestEntityFactory()
   {
   }
   
   public QuoteRequestEntity getQuoteRequestEntityInstance() //throws LicensingException
   {
      try
      {
         //Object object = AbeFactory.getInstance().getInstance(CLASSNAME);
         //return (QuoteRequestEntityInterface) InterfaceCastProxy.newInstance(object);
         return new org.allbinary.data.tables.user.quoterequest.QuoteRequestEntity();
      }
      /*
      catch(LicensingException e)
      {
         String error = "Failed to get instance";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(error,"QuoteRequestEntityFactory","getInstance()",e);
         }
         throw e;
      }*/
      catch(Exception e)
      {
         String error = "Failed to get instance";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,"QuoteRequestEntityFactory","getInstance",e));
         }
         return null;
      }
   }
   
}
