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
package org.allbinary.data.tree.category;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.category.CategoryFactoryInterface;

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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,"CategoryNodeFactory","getInstance()",e));
         }
         return null;
      }
   }
   
}
