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
package abcs.business.context.modules.storefronts;


import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;


public class BasicStoreFrontFactory
{
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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.FACTORYERROR))
         {
            String error = "Failed to get instance";
            LogUtil.put(
                LogFactory.getInstance(error, this, "getInstance()", e));
         }
         return null;
      }
   }
}
