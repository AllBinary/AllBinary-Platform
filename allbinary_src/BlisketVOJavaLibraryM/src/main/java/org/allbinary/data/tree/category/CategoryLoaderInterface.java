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

import org.allbinary.business.category.CategoryInterface;
import org.w3c.dom.Document;

public interface CategoryLoaderInterface extends CategoryModifierTreeInterface
{
   //Get the document associated the properties specified
   public Document getDoc(CategoryInterface categoryInterface);
   
   public CategoryInterface get(CategoryInterface categoryInterface);
   //public CategoryInterface get(Document document);
   //public CategoryInterface get(Node node);

   //public CategoryInterface getUpToLevel(CategoryInterface categoryInterface);

   public CategoryInterface getAll(CategoryInterface categoryInterface);
}
