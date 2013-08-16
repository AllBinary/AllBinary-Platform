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
package allbinary.business.category;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.java.object.InterfaceUtil;
import allbinary.business.category.properties.CategoryPropertiesInterface;
import allbinary.business.category.properties.CategoryPropertiesView;
import allbinary.data.tree.dom.DomNodeInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.Iterator;
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
      
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.CATEGORY))
      {
         LogUtil.put(LogFactory.getInstance("Number Of Children: " + childCategoryVector.size(), this, "toXmlNode"));
      }
      
      Iterator iter = childCategoryVector.iterator();
      while(iter.hasNext())
      {
         Object object = iter.next();
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
