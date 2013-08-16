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
package allbinary.data.tables.context.module.storefronts;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class StoreFrontsEntityFactory
{
    private static final StoreFrontsEntityFactory instance =
            new StoreFrontsEntityFactory();

    /**
     * @return the instance
     */
    public static StoreFrontsEntityFactory getInstance() {
        return instance;
    }

   //private final String CLASSNAME = "allbinary.data.tables.StoreFrontsEntity";
   
   private StoreFrontsEntityFactory()
   {
   }
   
   public StoreFrontsEntity getStoreFrontsEntityInstance() //throws LicensingException
   {
      try
      {
         //Object object = AbeFactory.getInstance(CLASSNAME);
         //return (StoreFrontsEntityInterface) InterfaceCastProxy.newInstance(object);
         return new allbinary.data.tables.context.module.storefronts.StoreFrontsEntity();
      }
      /*
      catch(LicensingException e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(error,"StoreFrontsEntityFactory","getInstance()",e);
         }
         throw e;
      } 
       */  
      catch(Exception e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, "StoreFrontsEntityFactory", "getInstance", e));
         }
         return null;
      }   
   }
}
