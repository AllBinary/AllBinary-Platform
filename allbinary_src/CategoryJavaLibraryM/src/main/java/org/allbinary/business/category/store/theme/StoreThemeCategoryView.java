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

import java.util.Arrays;

import org.allbinary.business.category.CategoryView;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.logic.visual.theme.ThemeValidation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class StoreThemeCategoryView extends CategoryView
   implements DomNodeInterface
{
   public StoreThemeCategoryView(final StoreThemeCategoryInterface categoryInterface)
   {
      super((StoreThemeCategoryInterface) categoryInterface);
   }

   public Node toXmlNode(final Document document) throws Exception
   {
      final Node node = super.toXmlNode(document);

      final StoreThemeCategoryInterface storeThemeCategoryInterface = 
         (StoreThemeCategoryInterface) this.getCategoryInterface();

      final ThemeValidation[] themeValidationArray = (ThemeValidation[]) 
         storeThemeCategoryInterface.getThemes().toArray();
      Arrays.sort(themeValidationArray, new ThemeComparator());

      int size = themeValidationArray.length;
      for(int index = 0; index < size; index++)
      {
         ThemeValidation themeValidation = 
            (ThemeValidation) themeValidationArray[index];

         node.appendChild(themeValidation.toXmlNode(document));
      }
      
      /*
      Object[] themeArray = storeThemeCategoryInterface.getThemes().toArray();
      int size = themeArray.length;
      for (int i = 0; i < size; i++)
      {
         ThemeValidation themeValidation = (ThemeValidation) themeArray[i];

         node.appendChild(themeValidation.toXmlNode(document));
      }
      */

      return node;
   }
}
