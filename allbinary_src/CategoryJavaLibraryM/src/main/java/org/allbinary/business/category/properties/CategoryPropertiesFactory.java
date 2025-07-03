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
package org.allbinary.business.category.properties;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.w3c.dom.Node;

import java.util.HashMap;
import org.allbinary.string.CommonStrings;

public class CategoryPropertiesFactory implements CategoryPropertiesFactoryInterface
{
   private CategoryPropertiesInterface categoryPropertiesInterface;
   
   public CategoryPropertiesFactory(String name)
   {
      this.categoryPropertiesInterface = 
         (CategoryPropertiesInterface) new CategoryProperties(name);
   }

   public CategoryPropertiesFactory(Node node)
   {
      this.categoryPropertiesInterface = 
         (CategoryPropertiesInterface) new CategoryProperties(node);
   }

   public CategoryPropertiesFactory(HashMap categoryPropertiesHashMap)
   {
      this.categoryPropertiesInterface = 
         (CategoryPropertiesInterface) 
            new CategoryProperties(categoryPropertiesHashMap);
   }
   
   //New Loner Category
   public CategoryPropertiesInterface getInstance()
   {
      try
      {
         return (CategoryPropertiesInterface) this.categoryPropertiesInterface;
      }
      catch(Exception e)
      {
         final CommonStrings commonStrings = CommonStrings.getInstance();
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE, e));
         }
         return null;
      }
   }
}
