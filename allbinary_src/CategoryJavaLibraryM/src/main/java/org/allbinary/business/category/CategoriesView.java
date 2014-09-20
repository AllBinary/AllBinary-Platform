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

import org.allbinary.business.category.CategoryView;
import org.allbinary.business.category.CategoryInterface;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.Iterator;

public class CategoriesView implements DomNodeInterface
{
   private CategoryInterface categoryInterface;

   public CategoriesView(CategoryInterface categoryInterface)
   {
      this.categoryInterface = categoryInterface;
   }

   public Node toXmlNode(Document document) throws Exception
   {
      Node node = new CategoryView(categoryInterface).toXmlNode(document);
      
      Iterator iter = this.categoryInterface.getChildNodes().iterator();
      while(iter.hasNext())
      {
         CategoryInterface childCategoryInterface = (CategoryInterface) iter.next();
         Node childCategoryNode = 
            new CategoriesView(childCategoryInterface).toXmlNode(document);
         node.appendChild(childCategoryNode);
      }
      return node;
   }
}
