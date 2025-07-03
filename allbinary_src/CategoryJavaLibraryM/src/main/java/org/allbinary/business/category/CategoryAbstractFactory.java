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
package org.allbinary.business.category;

import java.util.HashMap;

import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.category.properties.CategoryPropertiesFactory;
import org.allbinary.business.category.properties.CategoryPropertiesFactoryInterface;
import org.allbinary.string.CommonStrings;

import org.w3c.dom.Node;

public abstract class CategoryAbstractFactory implements CategoryFactoryInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   public CategoryAbstractFactory()
   {
   }

   public abstract CategoryInterface getRootInstance();
   public abstract CategoryInterface getRootInstance(AbPath categoryAbPath);
   public abstract CategoryInterface getRootInstanceFromNode(Node node);
   /*
   {
      try
      {
         CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface = 
            (CategoryPropertiesFactoryInterface) 
               new CategoryPropertiesFactory();
         
         return (CategoryInterface) 
            new Category(categoryPropertiesFactoryInterface, 0);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(commonStrings.EXCEPTION, this, "getInstance(String, int)", e);
         }
         return null;
      }
   }
   */
   /*
   public CategoryInterface getRootInstance(String categoryPath)
   {
      try
      {
         int level = CategoryUtil.getPathLevel(categoryPath);
         
         CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface = 
            (CategoryPropertiesFactoryInterface) 
               new CategoryPropertiesFactory(categoryPath);
         
         return (CategoryInterface) 
            new Category(categoryPropertiesFactoryInterface, level);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(commonStrings.EXCEPTION, this, "getInstance(String, int)", e);
         }
         return null;
      }
   }
   */
   
   //New Loner Category
   public CategoryInterface getInstance(
      String categoryName)
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "getInstance(String)"));
         }
         
         CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface = 
            (CategoryPropertiesFactoryInterface) 
               new CategoryPropertiesFactory(categoryName);
         
         return (CategoryInterface) 
            new Category(categoryPropertiesFactoryInterface);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "getInstance(String, int)", e));
         }
         return null;
      }
   }
   
   /*
   public CategoryInterface getInstance(
      String categoryName,
      int level)
   {
      try
      {
         CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface = 
            (CategoryPropertiesFactoryInterface) 
               new CategoryPropertiesFactory(categoryName);
         
         return (CategoryInterface) 
            new Category(categoryPropertiesFactoryInterface, level);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(commonStrings.EXCEPTION, this, "getInstance(String, int)", e);
         }
         return null;
      }
   }
   */
   
   //New Category With Parent Child Relationships
   public CategoryInterface getInstance(      
      CategoryInterface rootCategoryInterface, 
      CategoryInterface parentCategoryInterface,
      String categoryName)
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "getInstance(CategoryInterface, CategoryInterface, String)"));
         }

         CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface = 
            (CategoryPropertiesFactoryInterface) new CategoryPropertiesFactory(categoryName);

         return (CategoryInterface) new Category(
            rootCategoryInterface, parentCategoryInterface, 
            categoryPropertiesFactoryInterface);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "getInstance(CategoryInterface, CategoryInterface , String, int)", e));
         }
         return null;
      }
   }

   public CategoryInterface getInstance(Node node)
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "getInstance(Node)"));
         }
         
         CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface = 
            (CategoryPropertiesFactoryInterface) new CategoryPropertiesFactory(node);
         
         return (CategoryInterface) new Category(
            categoryPropertiesFactoryInterface);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "getInstance(Node, int)", e));
         }
         return null;
      }
   }

   /*
   public CategoryInterface getInstance(Node node, int level)
   {
      try
      {
         CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface = 
            (CategoryPropertiesFactoryInterface) new CategoryPropertiesFactory(node);
         
         return (CategoryInterface) new Category(
            categoryPropertiesFactoryInterface, level);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(commonStrings.EXCEPTION, this, "getInstance(Node, int)", e);
         }
         return null;
      }
   }
   */

   public CategoryInterface getInstance(      
      CategoryInterface rootCategoryInterface, 
      CategoryInterface parentCategoryInterface,
      Node node)
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "getInstance(CategoryInterface, CategoryInterface, Node)"));
         }
         
         CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface = 
            (CategoryPropertiesFactoryInterface) new CategoryPropertiesFactory(node);
         
         return (CategoryInterface) new Category(
            rootCategoryInterface, parentCategoryInterface, 
            categoryPropertiesFactoryInterface);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this,
              "getInstance(CategoryInterface, CategoryInterface, Node, int)", e));
         }
         return null;
      }
   }

   //Create from request
   /*
   public CategoryInterface getInstance(HashMap categoryPropertiesHashMap, int level)
   {
      try
      {
         CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface = 
            (CategoryPropertiesFactoryInterface) new CategoryPropertiesFactory(
               categoryPropertiesHashMap);
         
         return (CategoryInterface) new Category(categoryPropertiesFactoryInterface, level);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().ENTITYFACTORYERROR))
         {
            LogUtil.put(commonStrings.EXCEPTION, this, "getInstance(HashMap)", e);
         }
         return null;
      }
   }
   */
   
   //Create from request
   public CategoryInterface getInstance(      
      CategoryInterface rootCategoryInterface, 
      CategoryInterface parentCategoryInterface,
      HashMap categoryPropertiesHashMap)
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, "getInstance(CategoryInterface, CategoryInterface, HashMap)"));
         }
         
         CategoryPropertiesFactoryInterface categoryPropertiesFactoryInterface = 
            (CategoryPropertiesFactoryInterface) new CategoryPropertiesFactory(
               categoryPropertiesHashMap);

         return (CategoryInterface) new Category(
            rootCategoryInterface, 
            parentCategoryInterface,
            categoryPropertiesFactoryInterface);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this,
               "getInstance(CategoryInterface, CategoryInterface, HashMap)", e));
         }
         return null;
      }
   }
}