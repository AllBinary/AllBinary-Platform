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

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.category.CategoryComponent;
import org.allbinary.business.category.CategoryData;
import org.allbinary.business.category.CategoryFactoryInterface;
import org.allbinary.business.category.CategoryInterface;
import org.allbinary.logic.control.crypt.file.CryptFileWriter;
import org.allbinary.string.CommonStrings;
import org.w3c.dom.Document;

public class CategoryPrivateTree
{
   protected CategoryFactoryInterface categoryFactoryInterface;

   public CategoryPrivateTree(CategoryFactoryInterface categoryFactoryInterface)
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CATEGORY))
      {
         final CommonStrings commonStrings = CommonStrings.getInstance();
         LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "CategoryPrivateTree(CategoryFactoryInterface categoryFactoryInterface)"));
      }

      this.categoryFactoryInterface = categoryFactoryInterface;
   }

   protected synchronized void save(CategoryInterface categoryInterface)
      throws Exception
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Saving Category File: " + categoryInterface.getFilePath(), this, "save()"));
      }

      Document document = 
          new CategoryComponent(categoryInterface).toXmlDoc();

       //overwrite old file with new
       CryptFileWriter cryptFileWriter = new CryptFileWriter(
             CategoryData.getInstance().UNCRYPTED_EXTENSION,
             CategoryData.getInstance().ENCRYPTED_EXTENSION);

       cryptFileWriter.write(categoryInterface.getFilePath(), document);

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("end", this, "save()"));
      }
   }

   protected synchronized void delete(CategoryInterface categoryInterface)
      throws Exception
   {
      if(categoryInterface.isLeaf())
      {
         AbFile categoryFile = new AbFile(categoryInterface.getFilePath());

         if(categoryFile.delete())
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
               LogUtil.put(LogFactory.getInstance(
                   "Removed: " + categoryInterface.getFilePath(),
                   this, "delete(CategoryInterface)"));
            }
         }
      }
      else
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Not a leaf unable to remove", this, "delete(CategoryInterface)"));
         }
      }
   }

/*   
   //remove category xml file - if no sub categories removeCategoryXml
   //String categoryFilePath
   protected synchronized void remove(CategoryInterface categoryInterface)
      throws Exception
   //Document document, String categoryFilePath
   {
      Document document = 
         new CategoryComponent(categoryInterface).toXmlDoc();
      
      if(this.isLeaf(document))
      {
         File categoryFile = new File(categoryInterface.getFilePath());

         if(categoryFile.delete())
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
               LogUtil.put(LogFactory.getInstance("Removed: " + categoryInterface.getFilePath(), 
                  this, "remove(CategoryInterface)");
            }
         }
      }
   }
*/
   /*
   //check to see if category has no children
   //, String categoryFile
   protected synchronized boolean isLeaf(Document newDocument) throws Exception
   {
      if(newDocument!=null)
      {
         NodeList categoryNodes = newDocument.getChildNodes();
         
         if(categoryNodes!=null)
         {
            for(int index = 0; index < categoryNodes.getLength(); index++)
            {
               Node categoryNode = categoryNodes.item(index);
               if(categoryNode.getNodeType() == Node.ELEMENT_NODE)
               {
                  NodeList categoriesNodeList = categoryNode.getChildNodes();

                  for(int categoriesIndex = 0;
                  categoriesIndex < categoriesNodeList.getLength();
                  categoriesIndex++)
                  {
                     Node categoriesNode = categoriesNodeList.item(categoriesIndex);
                     if(categoriesNode.hasAttributes())
                     {
                        NamedNodeMap namedNodeMap = categoriesNode.getAttributes();
                        for(int attributeIndex = 0;
                        attributeIndex < namedNodeMap.getLength();
                        attributeIndex++)
                        {
                           Node attributeNode = namedNodeMap.item(attributeIndex);
                           String key = attributeNode.getNodeName();
                           String value = attributeNode.getNodeValue();
                           if(key.compareTo(CategoryData.getInstance().LABEL)==0)
                           {
                              return false;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      return true;
   }
   */
   
   /*
   //remove category from parent category XML removeCategoryFromCategoryXml
   protected synchronized void remove(
      CategoryInterface parentCategoryInterface,
      CategoryInterface newChildCategoryInterface) throws Exception
   {
      Document document = 
         new CategoryComponent(parentCategoryInterface).toXmlDoc();

      if(document == null)
      {
         throw new Exception("XML Category Document Null");
      }

      NodeList categoriesNodeList = 
         document.getElementsByTagName(CategoriesData.NAME);
      
      if(categoriesNodeList == null)
      {
         throw new Exception("Categories Node Missing");
      }

      boolean hasInserted = false;

      //create new category node
      CategoryPropertiesView newCategoryPropertyView =
         new CategoryPropertiesView(newChildCategoryInterface.getProperties());

      Node newCategoryNode = newCategoryPropertyView.toXmlNode(document);

      //if no categories append
      if(categoriesNodeList.getLength() > 0) 
      {
         Node categoriesNode = categoriesNodeList.item(0);
         NodeList categoryNodeList = categoriesNode.getChildNodes();

         for(int index = 0; index < categoryNodeList.getLength(); index++)
         {
            Node categoryNode = categoryNodeList.item(index);

            if(categoryNode.getNodeType() == Node.ELEMENT_NODE &&
               categoryNode.getNodeName().compareTo(CategoryData.getInstance().NAME) == 0)
            {
               CategoryInterface categoryInterface =
                  this.categoryFactoryInterface.getInstance(categoryNode, 
                     parentCategoryInterface.getHierarchy().getLevel() + 1);

               String category = 
                  categoryInterface.getProperties().getValue();

               String newCategory = 
                  newChildCategoryInterface.getProperties().getValue();

               //remove matching node
               if(newCategory.compareTo(category) == 0)
               {
                  categoryNode.removeChild(categoriesNode);
               }
            }
         }
      }

      //overwrite old file with new
      File modifiedCategoryFile = 
         new File(parentCategoryInterface.getFilePath());
      DomHelper.save(modifiedCategoryFile,document);
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("end", this, "remove()");
      }      
   }
*/   
/*
       Document document = 
         new CategoryComponent(parentCategoryInterface).toXmlDoc();

      if(document == null)
      {
         throw new Exception("XML Category Document Null");
      }

      NodeList categoriesNodeList = 
         document.getElementsByTagName(CategoriesData.NAME);
      
      if(categoriesNodeList == null)
      {
         throw new Exception("Categories Node Missing");
      }
      
      boolean hasInserted = false;
      
      //create new category node
      CategoryPropertiesView newCategoryPropertyView =
         new CategoryPropertiesView(newChildCategoryInterface.getProperties());
            
      Node newCategoryNode = newCategoryPropertyView.toXmlNode(document);
            
      //if no categories append
      if(categoriesNodeList.getLength() > 0) 
      {
         Node categoriesNode = categoriesNodeList.item(0);
         NodeList categoryNodeList = categoriesNode.getChildNodes();
         
         for(int index = 0; index < categoryNodeList.getLength(); index++)
         {
            Node categoryNode = categoryNodeList.item(index);

            if(categoryNode.getNodeType() == Node.ELEMENT_NODE &&
               categoryNode.getNodeName().compareTo(CategoryData.getInstance().NAME) == 0)
            {
               CategoryInterface categoryInterface =
                  this.categoryFactoryInterface.getInstance(categoryNode, 
                     parentCategoryInterface.getHierarchy().getLevel() + 1);
                     
               String category = 
                  categoryInterface.getProperties().getValue();

               String newCategory = 
                  newChildCategoryInterface.getProperties().getValue();

               if(newCategory.compareTo(category) == 0)
               {
                  if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                  {
                     LogUtil.put(LogFactory.getInstance("Already Exists" + newCategory,this,"modifyFile");
                  }
                  return;
               }
            }
         }
         
         for(int index = 0; index < categoryNodeList.getLength(); index++)
         {
            Node categoryNode = categoryNodeList.item(index);

            if(categoryNode.getNodeType() == Node.ELEMENT_NODE &&
               categoryNode.getNodeName().compareTo(CategoryData.getInstance().NAME) == 0)
            {
               CategoryInterface categoryInterface =
                  this.categoryFactoryInterface.getInstance(categoryNode, 
                     parentCategoryInterface.getHierarchy().getLevel() + 1);
                     
               String category = 
                  categoryInterface.getProperties().getValue();

               String newCategory = 
                  newChildCategoryInterface.getProperties().getValue();

               //add alphabetically
               if(newCategory.compareToIgnoreCase(category) < 0)
               {
                  if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
                  {
                     LogUtil.put(LogFactory.getInstance("Inserting: " + newCategory,this,"modifyFile");
                  }

                  categoryNode.insertBefore(newCategoryNode,categoriesNode);

                  hasInserted = true;
               }
               
               //add to end
               if(index == categoryNodeList.getLength() - 1 && !hasInserted)
               {
                  categoryNode.appendChild(newCategoryNode);
               }
            }
         }         
      }
*/   
}
