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
import org.w3c.dom.Node;

public interface CategoryFactoryInterface
{
   CategoryInterface getRootInstance();
   CategoryInterface getRootInstance(AbPath categoryPath);

   CategoryInterface getRootInstanceFromNode(Node node);
   
   //New Loner Category
   //CategoryInterface getInstance(String name, int level);

   //New Category With Parent Child Relationships
   CategoryInterface getInstance(      
      CategoryInterface rootCategoryInterface, 
      CategoryInterface parentCategoryInterface,
      String name);
   
   //CategoryInterface getInstance(Node node, int level);

   CategoryInterface getInstance(      
      CategoryInterface rootCategoryInterface, 
      CategoryInterface parentCategoryInterface,
      Node node);

   //CategoryInterface getInstance(HashMap categoryPropertiesHashMap, int level);

   CategoryInterface getInstance(      
      CategoryInterface rootCategoryInterface, 
      CategoryInterface parentCategoryInterface,
      HashMap categoryPropertiesHashMap);
}