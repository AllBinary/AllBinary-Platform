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
import org.allbinary.logic.visual.theme.ThemeValidation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.Arrays;

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

      for(int index = 0; index < themeValidationArray.length; index++)
      {
         ThemeValidation themeValidation = 
            (ThemeValidation) themeValidationArray[index];

         node.appendChild(themeValidation.toXmlNode(document));
      }
      
      /*
      Iterator iter = storeThemeCategoryInterface.getThemes().iterator();
      while(iter.hasNext())
      {
         ThemeValidation themeValidation = (ThemeValidation) iter.next();

         node.appendChild(themeValidation.toXmlNode(document));
      }
      */

      return node;
   }
}
