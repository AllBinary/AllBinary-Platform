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
package allbinary.business.category.properties;

import allbinary.business.category.CategoryData;
import allbinary.data.tree.dom.DomNodeInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class CategoryPropertiesView implements DomNodeInterface
{
   protected CategoryPropertiesInterface categoryPropertiesInterface;
   
   public CategoryPropertiesView(
      CategoryPropertiesInterface categoryPropertiesInterface)
   {
      this.categoryPropertiesInterface = categoryPropertiesInterface;
   }

   public Node toXmlNode(Document document) throws Exception
   {
      Node categoryNode = document.createElement(CategoryData.getInstance().NAME);

      Element newCategoryNode = (Element) categoryNode;
      newCategoryNode.setAttribute(
         CategoryData.getInstance().LABEL, 
         this.categoryPropertiesInterface.getValue());

      return categoryNode;
   }
}
