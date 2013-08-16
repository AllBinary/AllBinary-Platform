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
package allbinary.data.tree.category;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.category.CategoryFactoryInterface;

public class CategoryLoaderFactory
{
   
   private CategoryLoaderFactory()
   {
   }
   
   public static CategoryLoaderInterface getInstance(
      CategoryFactoryInterface categoryFactoryInterface) //throws LicensingException
   {
      try
      {
         return new CategoryLoader(categoryFactoryInterface);
      }
      catch(Exception e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,"CategoryNodeFactory","getInstance()",e));
         }
         return null;
      }
   }
   
}
