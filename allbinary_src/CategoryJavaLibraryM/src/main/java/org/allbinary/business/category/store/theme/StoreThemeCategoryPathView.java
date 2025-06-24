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

import org.allbinary.business.category.CategoryView;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.logic.visual.theme.ThemeInterface;
import org.allbinary.logic.visual.theme.ThemePropertiesView;
import org.allbinary.logic.visual.theme.ThemeValidation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;



public class StoreThemeCategoryPathView extends CategoryView implements DomNodeInterface
{
   public StoreThemeCategoryPathView(StoreThemeCategoryInterface categoryInterface)
   {
      super((StoreThemeCategoryInterface) categoryInterface);
   }

   public Node toXmlNode(Document document) throws Exception
   {
      Node node = super.toXmlNode(document);

      StoreThemeCategoryInterface storeThemeCategoryInterface = 
         (StoreThemeCategoryInterface) this.getCategoryInterface();
      
      Object[] themeArray = storeThemeCategoryInterface.getThemes().toArray();
      int size = themeArray.length;
      for (int i = 0; i < size; i++)
      {
         ThemeValidation themeValidation = (ThemeValidation) themeArray[i];

         DomNodeInterface domNodeInterface = (DomNodeInterface)
            new ThemePropertiesView((ThemeInterface) themeValidation);

         node.appendChild(domNodeInterface.toXmlNode(document));
      }

      return node;
   }
}
