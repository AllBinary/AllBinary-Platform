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

import org.allbinary.logic.basic.io.file.directory.Directory;
import org.allbinary.logic.basic.path.AbPath;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.category.CategoryFactoryInterface;
import org.allbinary.business.category.CategoryInterface;
import org.allbinary.logic.basic.string.CommonStrings;

public class CategoryModifierTree extends CategoryPrivateTree implements CategoryModifierTreeInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   public CategoryModifierTree(CategoryFactoryInterface categoryFactoryInterface)
   {
      super(categoryFactoryInterface);
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.CATEGORY))
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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.CATEGORY))
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

            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.CATEGORY))
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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, "insert", e));
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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, "delete", e));
         }
      }
   }
}
