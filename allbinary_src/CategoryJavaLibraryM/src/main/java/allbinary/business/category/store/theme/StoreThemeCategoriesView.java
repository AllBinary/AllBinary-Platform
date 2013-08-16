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
package allbinary.business.category.store.theme;

import allbinary.business.category.CategoryInterface;
import allbinary.data.tree.dom.DomNodeInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.Iterator;

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
      
      Iterator iter = this.categoryInterface.getChildNodes().iterator();
      while(iter.hasNext())
      {
         CategoryInterface childCategoryInterface = (CategoryInterface) iter.next();

         Node childCategoryNode = 
            new StoreThemeCategoriesView(
               (StoreThemeCategoryInterface) 
                 childCategoryInterface).toXmlNode(document);

         node.appendChild(childCategoryNode);
      }
      return node;
   }
}
