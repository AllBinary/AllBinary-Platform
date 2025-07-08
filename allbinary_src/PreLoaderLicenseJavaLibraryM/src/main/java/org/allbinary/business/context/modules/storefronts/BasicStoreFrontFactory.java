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
package org.allbinary.business.context.modules.storefronts;


import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;


public class BasicStoreFrontFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private static final BasicStoreFrontFactory instance = new BasicStoreFrontFactory();

    /**
     * @return the instance
     */
    public static BasicStoreFrontFactory getInstance()
    {
        return instance;
    }

   private BasicStoreFrontFactory()
   {
   }

   public BasicStoreFrontInterface getInstance(String storeName) throws Exception
   {
      try
      {
         BasicStoreFrontsEntity storeFronts = new BasicStoreFrontsEntity();
         return storeFronts.getStoreFrontInterface(storeName);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().FACTORYERROR))
         {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE, e);
         }
         return null;
      }
   }
}
