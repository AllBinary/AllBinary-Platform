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
package org.allbinary.business.category.hierarchy;

import java.util.HashMap;

import org.allbinary.business.category.CategoryData;
import org.allbinary.business.category.CategoryInterface;
import org.allbinary.logic.string.StringUtil;

/*
 *Nothing more than a parent category reference to a category on the fs.  
 *i.e. unloaded category.
 */
public class CategoryHierarchy implements CategoryHierarchyInterface
{
   private CategoryInterface rootCategoryInterface;
   private CategoryInterface parentCategoryInterface;

   private int level;

   public CategoryHierarchy(
      CategoryInterface rootCategoryInterface, CategoryInterface parentCategoryInterface)
   {
      this.level = 0;

      this.setRoot(rootCategoryInterface);
      this.setParent(parentCategoryInterface);
   }

   public CategoryHierarchy(
      CategoryInterface rootCategoryInterface, CategoryInterface parentCategoryInterface, int level)
   {
      this.level = level;

      this.setRoot(rootCategoryInterface);
      this.setParent(parentCategoryInterface);
   }
      
   public CategoryHierarchy(CategoryInterface rootCategoryInterface, CategoryInterface parentCategoryInterface, HashMap categoryPropertiesHashMap)
   {
      String levelString = 
         StringUtil.getInstance().getInstance((String) categoryPropertiesHashMap.get(CategoryData.getInstance().LEVEL));

      this.level = Integer.valueOf(levelString).intValue();

      this.setRoot(rootCategoryInterface);
      this.setParent(parentCategoryInterface);
   }

   public int getLevel()
   {
      return this.level;
   }
   
   public String getLevelString()
   {
      return Integer.toString(this.level);
   }
   
   public void setLevel(int level)
   {
      this.level = level;
   }

   public void setParent(CategoryInterface categoryInterface)
   {
      this.parentCategoryInterface = categoryInterface;
   }
   
   public void setRoot(CategoryInterface categoryInterface)
   {
      this.rootCategoryInterface = categoryInterface;
   }

   public CategoryInterface getRoot()
   {
      return this.rootCategoryInterface;
   }
   
   public CategoryInterface getParent()
   {
      return this.parentCategoryInterface;
   }
}