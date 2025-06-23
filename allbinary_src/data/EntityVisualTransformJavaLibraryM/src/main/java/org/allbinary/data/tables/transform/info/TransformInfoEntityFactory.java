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
package org.allbinary.data.tables.transform.info;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoFactoryInterface;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigAndManipulatorFactoryInterface;
import org.allbinary.logic.visual.transform.info.objectConfig.generator.TransformInfoObjectConfigGeneratorFactoryInterface;

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
         //Object object = AbeFactory.getInstance().getInstance(CLASSNAME);
         //return (StoreFrontsEntityInterface) InterfaceCastProxy.newInstance(object);
         return new TransformInfoEntity(
             transformInfoObjectConfigGeneratorFactoryInterface,
             transformInfoObjectConfigAndManipulatorFactoryInterface,
             transformInfoFactoryInterface);
         //return (TransformInfoEntityInterface) AbeFactory.getInstance().getInstance("org.allbinary.data.tables.transform.info.TransformInfoEntity");
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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, "TransformInfoEntityFactory", "getInstance()", e));
         }
         return null;
      }   
   }
}
