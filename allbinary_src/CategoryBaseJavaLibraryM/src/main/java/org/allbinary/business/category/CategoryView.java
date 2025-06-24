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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.object.InterfaceUtil;
import org.allbinary.business.category.properties.CategoryPropertiesInterface;
import org.allbinary.business.category.properties.CategoryPropertiesView;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;


import java.util.Vector;

public class CategoryView implements DomNodeInterface
{
   private CategoryInterface categoryInterface;
   
   public CategoryView(CategoryInterface categoryInterface)
   {
      this.categoryInterface = categoryInterface;
   }

   public CategoryInterface getCategoryInterface()
   {
      return this.categoryInterface;
   }
   
   public Node toXmlNode(Document document) throws Exception
   {
      Node node = document.createElement(CategoryData.getInstance().NAME);

      /*
      NamedNodeMap nodeAttributes = node.getAttributes();
      Attr nameAttr = document.createAttribute(DomData.ID);
      nameAttr.setValue(this.getCategoryInterface().getPath());
      CategoryData.ROOTCATEGORY
      nodeAttributes.setNamedItem(nameAttr);
       */

      Vector childCategoryVector = this.categoryInterface.getChildNodes();
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
      {
         LogUtil.put(LogFactory.getInstance("Number Of Children: " + childCategoryVector.size(), this, "toXmlNode"));
      }
      
      int size = childCategoryVector.size();
      for (int i = 0; i < size; i++)
      {
         Object object = childCategoryVector.get(i);
         CategoryPropertiesInterface categoryPropertiesInterface = null;

         if(InterfaceUtil.isImplemented("CategoryPropertiesInterface", object))
         {
            categoryPropertiesInterface = (CategoryPropertiesInterface) object;
         }
         else
         if(InterfaceUtil.isImplemented("CategoryInterface", object))
         {
            CategoryInterface categoryInterface = (CategoryInterface) object;
            categoryPropertiesInterface = categoryInterface.getProperties();
         }
         else
         {
            String isImpl =
               InterfaceUtil.viewAll(object.getClass(),"\n");
            /*
             * classname.class is not obfuscated correctly
               InterfaceUtil.isImplementedView(
                  CategoryInterface.class.getClass(), object) +
               InterfaceUtil.isImplementedView(
                  CategoryPropertiesInterface.class.getClass(), object);
            */
            
            //"CategoryView found unknown object:\n" + 
            throw new Exception("CategoryView found unknown object.\n" + 
               "Show Testing:" + isImpl);
         }

         Node childCategoryNode = 
            new CategoryPropertiesView(
               categoryPropertiesInterface).toXmlNode(document);

         node.appendChild(childCategoryNode);
      }

      return node;
   }
}
