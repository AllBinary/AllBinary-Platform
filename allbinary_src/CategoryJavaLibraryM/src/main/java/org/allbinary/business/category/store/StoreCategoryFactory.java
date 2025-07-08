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
package org.allbinary.business.category.store;

import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.category.Category;
import org.allbinary.business.category.CategoryAbstractFactory;
import org.allbinary.business.category.CategoryFactoryInterface;
import org.allbinary.business.category.CategoryInterface;
import org.allbinary.business.category.CategoryUtil;
import org.allbinary.business.category.properties.CategoryPropertiesFactoryInterface;
import org.allbinary.business.category.properties.root.store.RootStoreCategoryPropertiesFactory;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Node;

public class StoreCategoryFactory extends CategoryAbstractFactory 
   implements CategoryFactoryInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private TransformInfoInterface transformInfoInterface;
   
   public StoreCategoryFactory(TransformInfoInterface transformInfoInterface)
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
      {
         logUtil.put(commonStrings.START, this, "StoreCategoryFactory(TransformInfoInterface transformInfoInterface)");
      }

      this.transformInfoInterface = transformInfoInterface;
   }

   //Completely New Root Category
   public CategoryInterface getRootInstance()
   {
      try
      {
         CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface = 
            (CategoryPropertiesFactoryInterface) 
               new RootStoreCategoryPropertiesFactory(this.transformInfoInterface);

         return (CategoryInterface) 
            new Category(categoryPropertiesFactoryInterface);
      }
      catch(Exception e)
      {
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, this, "getInstance(String, int)", e);
         }
         return null;
      }
   }
   
   //Get Root Category with possible offset from root
   public CategoryInterface getRootInstance(AbPath categoryPath)
   {
      try
      {
         int level = CategoryUtil.getPathLevel(categoryPath);
         
         CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface = 
            (CategoryPropertiesFactoryInterface) 
               new RootStoreCategoryPropertiesFactory(
                  this.transformInfoInterface, categoryPath);
         
         return (CategoryInterface) 
            new Category(categoryPropertiesFactoryInterface, level);
      }
      catch(Exception e)
      {
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                 org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, this, "getInstance(String, int)", e);
         }
         return null;
      }
   }

   //Get Root Category with possible offset from root
   public CategoryInterface getRootInstanceFromNode(Node node)
   {
      try
      {
         CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface = 
            (CategoryPropertiesFactoryInterface) 
               new RootStoreCategoryPropertiesFactory(
                  this.transformInfoInterface, node);

         AbPath categoryPath = new AbPath(CategoryUtil.getNameFromNode(node));

         int level = CategoryUtil.getPathLevel(categoryPath);

         return (CategoryInterface) 
            new Category(categoryPropertiesFactoryInterface, level);
      }
      catch(Exception e)
      {
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, this, "getInstance(String, int)", e);
         }
         return null;
      }
   }   
}
