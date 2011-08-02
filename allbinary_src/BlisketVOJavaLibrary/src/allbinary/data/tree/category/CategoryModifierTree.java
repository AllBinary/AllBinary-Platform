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

import abcs.logic.basic.io.file.directory.Directory;
import abcs.logic.basic.path.AbPath;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.category.CategoryFactoryInterface;
import allbinary.business.category.CategoryInterface;

public class CategoryModifierTree extends CategoryPrivateTree implements CategoryModifierTreeInterface
{
   public CategoryModifierTree(CategoryFactoryInterface categoryFactoryInterface)
   {
      super(categoryFactoryInterface);
      
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
      {
         LogUtil.put(LogFactory.getInstance("Constructing", this, "CategoryModifierTree(CategoryFactoryInterface categoryFactoryInterface)"));
      }
   }

   //Add Category
   public synchronized void insert(
      CategoryInterface parentCategoryInterface,
      CategoryInterface newChildCategoryInterface)
   {
      try
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
         {
            LogUtil.put(LogFactory.getInstance("Inserting", this, "insert()"));
         }
      
         if(parentCategoryInterface.isValid().booleanValue())
         {
            //This could cause a problem adding it before saving the data but who cares
            parentCategoryInterface.addChild(newChildCategoryInterface);
            
            AbPath directoryToBeCreatedAbPath = new AbPath(
               newChildCategoryInterface.getRootFilePath().toString() + 
               newChildCategoryInterface.getPath().toString());
            
            Directory.create(directoryToBeCreatedAbPath);

            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
            {
               LogUtil.put(LogFactory.getInstance("Saving", this, "insert()"));
            }

            this.save(parentCategoryInterface);
            this.save(newChildCategoryInterface);
         }
         else
            throw new Exception("Will Not Add Null Category");
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed", this, "insert", e));
         }
      }
   }

   public synchronized void delete(
      CategoryInterface parentCategoryInterface,
      CategoryInterface existingChildCategoryInterface)
   {
      try
      {
         if(parentCategoryInterface.isValid().booleanValue())
         {
            parentCategoryInterface.addChild(existingChildCategoryInterface);

            AbPath directoryToBeDeletedAbPath = new AbPath(
               existingChildCategoryInterface.getRootFilePath().toString() + 
               existingChildCategoryInterface.getPath().toString());

            this.delete(existingChildCategoryInterface);
            parentCategoryInterface.removeChild(existingChildCategoryInterface);
            
            this.save(parentCategoryInterface);
            
            Directory.remove(directoryToBeDeletedAbPath);
         }
         else
            throw new Exception("Will Not Add Null Category");
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed", this, "delete", e));
         }
      }
   }
}
