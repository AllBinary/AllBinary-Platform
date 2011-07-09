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
package allbinary.data.tables.user;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class UserEntityFactory
{
   //private static final String CLASSNAME = "allbinary.data.tables.user.UserEntity";
   
   private UserEntityFactory()
   {
   }
   
   public static UserEntityInterface getInstance() //throws LicensingException
   {
      try
      {
         //Object object = AbeFactory.getInstance(CLASSNAME);
         //return (UserEntityInterface) InterfaceCastProxy.newInstance(object);
         return (UserEntityInterface) new allbinary.data.tables.user.UserEntity();
      }
      /*
      catch(LicensingException e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(error,"UserEntityFactory","getInstance()",e);
         }
         throw e;
      }*/
      catch(Exception e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,"UserEntityFactory","getInstance()",e));
         }
         return null;
      }
   }
   
}
