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

import org.allbinary.business.category.CategoryFactoryInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class CategoryLoaderFactory
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   
   private CategoryLoaderFactory()
   {
   }
   
   public static CategoryLoaderInterface getInstance(
      CategoryFactoryInterface categoryFactoryInterface) //throws LicensingException
   {
       final LogUtil logUtil = LogUtil.getInstance();
      try
      {
         return new CategoryLoader(categoryFactoryInterface);
      }
      catch(Exception e)
      {
         final CommonStrings commonStrings = CommonStrings.getInstance();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, "CategoryNodeFactory",commonStrings.GET_INSTANCE,e);
         }
         return null;
      }
   }
   
}
