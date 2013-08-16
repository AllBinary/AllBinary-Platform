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
package allbinary.data.tables.transform.info;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.visual.transform.info.TransformInfoFactoryInterface;
import allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigAndManipulatorFactoryInterface;
import allbinary.logic.visual.transform.info.objectConfig.generator.TransformInfoObjectConfigGeneratorFactoryInterface;

public class TransformInfoEntityFactory
{
   private TransformInfoEntityFactory()
   {
   }
   
   public static TransformInfoEntity getInstance(
       TransformInfoObjectConfigGeneratorFactoryInterface transformInfoObjectConfigGeneratorFactoryInterface,
       TransformInfoObjectConfigAndManipulatorFactoryInterface transformInfoObjectConfigAndManipulatorFactoryInterface,
       TransformInfoFactoryInterface transformInfoFactoryInterface
        ) //throws LicensingException
   {
      try
      {
         //Object object = AbeFactory.getInstance(CLASSNAME);
         //return (StoreFrontsEntityInterface) InterfaceCastProxy.newInstance(object);
         return new TransformInfoEntity(
             transformInfoObjectConfigGeneratorFactoryInterface,
             transformInfoObjectConfigAndManipulatorFactoryInterface,
             transformInfoFactoryInterface);
         //return (TransformInfoEntityInterface) AbeFactory.getInstance("allbinary.data.tables.transform.info.TransformInfoEntity");
      }
      /*
      catch(LicensingException e)
      {
         String error = "Failed to get instance";
         if(allbinary.globals.LOG.LOGGING.contains(allbinary.globals.LOG.ENTITYFACTORYERROR))
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
            LogUtil.put(LogFactory.getInstance(error, "TransformInfoEntityFactory", "getInstance()", e));
         }
         return null;
      }   
   }
}
