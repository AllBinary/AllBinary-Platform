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
package allbinary.business.category;

import abcs.logic.basic.path.AbPath;
import org.w3c.dom.Node;

import java.util.HashMap;

public interface CategoryFactoryInterface
{
   public abstract CategoryInterface getRootInstance();
   public abstract CategoryInterface getRootInstance(AbPath categoryPath);

   public abstract CategoryInterface getRootInstanceFromNode(Node node);
   
   //New Loner Category
   //public CategoryInterface getInstance(String name, int level);

   //New Category With Parent Child Relationships
   public CategoryInterface getInstance(      
      CategoryInterface rootCategoryInterface, 
      CategoryInterface parentCategoryInterface,
      String name);
   
   //public CategoryInterface getInstance(Node node, int level);

   public CategoryInterface getInstance(      
      CategoryInterface rootCategoryInterface, 
      CategoryInterface parentCategoryInterface,
      Node node);

   //public CategoryInterface getInstance(HashMap categoryPropertiesHashMap, int level);

   public CategoryInterface getInstance(      
      CategoryInterface rootCategoryInterface, 
      CategoryInterface parentCategoryInterface,
      HashMap categoryPropertiesHashMap);
}