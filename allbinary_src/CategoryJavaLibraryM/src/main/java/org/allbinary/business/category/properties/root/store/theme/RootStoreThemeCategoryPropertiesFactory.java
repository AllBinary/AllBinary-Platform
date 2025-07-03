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
package org.allbinary.business.category.properties.root.store.theme;

import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.category.properties.CategoryPropertiesFactoryInterface;
import org.allbinary.business.category.properties.CategoryPropertiesInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Node;

import java.util.HashMap;
import org.allbinary.string.CommonStrings;

public class RootStoreThemeCategoryPropertiesFactory 
   implements CategoryPropertiesFactoryInterface
{
   private CategoryPropertiesInterface categoryPropertiesInterface;
   
   public RootStoreThemeCategoryPropertiesFactory(
      TransformInfoInterface transformInfoInterface) throws Exception
   {
      this.categoryPropertiesInterface = 
         (CategoryPropertiesInterface) 
            new RootStoreThemeCategoryProperties(transformInfoInterface);
   }

   public RootStoreThemeCategoryPropertiesFactory(
      TransformInfoInterface transformInfoInterface, AbPath abPath) throws Exception
   {
      this.categoryPropertiesInterface = 
         (CategoryPropertiesInterface) 
            new RootStoreThemeCategoryProperties(transformInfoInterface, abPath);
   }

   public RootStoreThemeCategoryPropertiesFactory(
      TransformInfoInterface transformInfoInterface, Node node) throws Exception
   {
      this.categoryPropertiesInterface = 
         (CategoryPropertiesInterface) 
            new RootStoreThemeCategoryProperties(transformInfoInterface, node);
   }

   public RootStoreThemeCategoryPropertiesFactory(
     TransformInfoInterface transformInfoInterface, HashMap categoryPropertiesHashMap) throws Exception
   {
      this.categoryPropertiesInterface = 
         (CategoryPropertiesInterface) 
            new RootStoreThemeCategoryProperties(
               transformInfoInterface, categoryPropertiesHashMap);
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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE, e));
         }
         return null;
      }
   }
}
