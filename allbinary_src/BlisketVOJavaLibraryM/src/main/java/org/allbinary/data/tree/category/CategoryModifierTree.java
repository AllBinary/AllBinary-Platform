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

import org.allbinary.logic.io.file.directory.Directory;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.category.CategoryFactoryInterface;
import org.allbinary.business.category.CategoryInterface;
import org.allbinary.string.CommonStrings;

public class CategoryModifierTree extends CategoryPrivateTree implements CategoryModifierTreeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    private final Directory directory = Directory.getInstance();
    
   public CategoryModifierTree(CategoryFactoryInterface categoryFactoryInterface)
   {
      super(categoryFactoryInterface);
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
      {
         logUtil.put(commonStrings.START, this, "CategoryModifierTree(CategoryFactoryInterface categoryFactoryInterface)");
      }
   }

   //Add Category
   public synchronized void insert(
      CategoryInterface parentCategoryInterface,
      CategoryInterface newChildCategoryInterface)
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
         {
            logUtil.put("Inserting", this, "insert()");
         }
      
         if(parentCategoryInterface.isValid().booleanValue())
         {
            //This could cause a problem adding it before saving the data but who cares
            parentCategoryInterface.addChild(newChildCategoryInterface);
            
            AbPath directoryToBeCreatedAbPath = new AbPath(
               newChildCategoryInterface.getRootFilePath().toString() + 
               newChildCategoryInterface.getPath().toString());
            
            this.directory.create(directoryToBeCreatedAbPath);

            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
            {
               logUtil.put("Saving", this, "insert()");
            }

            this.save(parentCategoryInterface);
            this.save(newChildCategoryInterface);
         }
         else
            throw new Exception("Will Not Add Null Category");
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
         {
            logUtil.put(this.commonStrings.FAILURE, this, "insert", e);
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
            
            this.directory.remove(directoryToBeDeletedAbPath);
         }
         else
            throw new Exception("Will Not Add Null Category");
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
         {
            logUtil.put(this.commonStrings.FAILURE, this, commonStrings.delete, e);
         }
      }
   }
}
