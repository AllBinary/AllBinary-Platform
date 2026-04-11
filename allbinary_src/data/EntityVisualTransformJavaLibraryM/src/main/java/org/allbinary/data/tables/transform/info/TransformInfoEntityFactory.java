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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoFactoryInterface;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigAndManipulatorFactoryBase;
import org.allbinary.logic.visual.transform.info.objectConfig.generator.TransformInfoObjectConfigGeneratorFactoryInterface;
import org.allbinary.string.CommonStrings;

public class TransformInfoEntityFactory
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   private TransformInfoEntityFactory()
   {
   }
   
   public static TransformInfoEntity getInstance(
       TransformInfoObjectConfigGeneratorFactoryInterface transformInfoObjectConfigGeneratorFactoryInterface,
       TransformInfoObjectConfigAndManipulatorFactoryBase transformInfoObjectConfigAndManipulatorFactoryInterface,
       TransformInfoFactoryInterface transformInfoFactoryInterface
        ) //throws LicensingException
   {
       final LogUtil logUtil = LogUtil.getInstance();
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
         final CommonStrings commonStrings = CommonStrings.getInstance();
         if(allbinary.globals.LOG.LOGGING.contains(allbinary.globals.LOG.ENTITYFACTORYERROR))
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
            logUtil.put(commonStrings.EXCEPTION, "TransformInfoEntityFactory", commonStrings.GET_INSTANCE, e);
         }
         return null;
      }   
   }
}
