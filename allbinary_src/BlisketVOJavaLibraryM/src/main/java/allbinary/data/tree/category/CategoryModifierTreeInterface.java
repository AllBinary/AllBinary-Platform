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

import allbinary.business.category.CategoryInterface;

public interface CategoryModifierTreeInterface
{
   public void insert(
      CategoryInterface parentCategoryInterface,
      CategoryInterface newChildCategoryInterface);
   /*
      Document document,
      String parentCategoryXmlOfNewCategory, 
      String newCategoryPath, 
      String modifiedCategory, 
      String newCategory   
   */
   
   public void delete(
      CategoryInterface parentCategoryInterface,
      CategoryInterface newChildCategoryInterface);
   /*
      Document document,
      String parentCategoryFilePath, 
      String categoryPath,            
      String categoryFilePath,
      String category   
    */
}
