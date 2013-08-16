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
package allbinary.business.category.store;




import abcs.logic.basic.path.AbPath;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.category.Category;
import allbinary.business.category.CategoryAbstractFactory;
import allbinary.business.category.CategoryFactoryInterface;
import allbinary.business.category.CategoryInterface;
import allbinary.business.category.CategoryUtil;
import allbinary.business.category.properties.CategoryPropertiesFactoryInterface;
import allbinary.business.category.properties.root.store.RootStoreCategoryPropertiesFactory;
import allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Node;

public class StoreCategoryFactory extends CategoryAbstractFactory 
   implements CategoryFactoryInterface
{
   private TransformInfoInterface transformInfoInterface;
   
   public StoreCategoryFactory(TransformInfoInterface transformInfoInterface)
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
      {
         LogUtil.put(LogFactory.getInstance("Constructing", this, "StoreCategoryFactory(TransformInfoInterface transformInfoInterface)"));
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
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "getInstance(String, int)", e));
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
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                 abcs.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "getInstance(String, int)", e));
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
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "getInstance(String, int)", e));
         }
         return null;
      }
   }   
}
