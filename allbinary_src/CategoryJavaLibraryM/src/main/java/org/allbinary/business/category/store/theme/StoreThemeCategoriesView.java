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
package org.allbinary.business.category.store.theme;

import org.allbinary.business.category.CategoryInterface;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;



public class StoreThemeCategoriesView implements DomNodeInterface
{
   private StoreThemeCategoryInterface categoryInterface;

   public StoreThemeCategoriesView(StoreThemeCategoryInterface categoryInterface)
   {
      this.categoryInterface = categoryInterface;
   }

   public Node toXmlNode(Document document) throws Exception
   {
      Node node = new StoreThemeCategoryView(categoryInterface).toXmlNode(document);
      
      Object[] childArray = this.categoryInterface.getChildNodes().toArray();
      int size = childArray.length;
      for (int i = 0; i < size; i++)
      {
         CategoryInterface childCategoryInterface = (CategoryInterface) childArray[i];

         Node childCategoryNode = 
            new StoreThemeCategoriesView(
               (StoreThemeCategoryInterface) 
                 childCategoryInterface).toXmlNode(document);

         node.appendChild(childCategoryNode);
      }
      return node;
   }
}
