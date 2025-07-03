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
package org.allbinary.business.category.properties.root;

import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.business.category.CategoryData;
import org.allbinary.business.category.hierarchy.CategoryHierarchyInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Vector;

/*
 *Nothing more than a parent category reference to a category on the fs.  
 *i.e. unloaded category.
 */
public class RootCategoryPropertiesAbstract 
   implements RootCategoryPropertiesInterface
{
   private final String category = CategoryData.getInstance().ROOTCATEGORY;
   private final String fileName = 
	   category + AbPathData.getInstance().EXTENSION_SEP + 
       CategoryData.getInstance().UNCRYPTED_EXTENSION;

   public RootCategoryPropertiesAbstract()
   {
   }

   public Object getKey()
   {
      return (Object) this.getValue();
   }

   public String getValue()
   {
      return this.category;
   }

   public void setValue(String value) throws Exception
   {
      throw new Exception("No Value Allowed");
   }

   public void setRootFilePath(AbPath value) throws Exception {
       throw new RuntimeException();
   }
   
   public void setRootFilePath(String value) throws Exception
   {
      throw new Exception("No Value Allowed");
   }
   
   public AbPath getRootFilePath() throws Exception
   {
      throw new Exception("No Value Allowed");
   }

   public boolean isRealRoot() throws Exception
   {
      throw new Exception("No A Real Root");
   }
   
   public boolean isRoot() throws Exception
   {
      throw new Exception("Root but not implemented");
   }
   
   public String getWebAppPath(
      CategoryHierarchyInterface categoryHierarchyInterface) throws Exception
   {
      throw new Exception("Not Root");
   }

   public AbPath getPath(CategoryHierarchyInterface categoryHierarchyInterface)
   {
      return new AbPath();
   }

   public String getFileName()
   {
      return fileName;
   }

   /*
   public String getFilePath(CategoryHierarchyInterface categoryHierarchyInterface)
   {
      return this.getPath(categoryHierarchyInterface) + this.getFileName();
   }
    */
   
   public Boolean isValid()
   {
      //if() return false;
      return Boolean.TRUE;
   }

   public HashMap toHashMap()
   {
      HashMap categoryHashMap = new HashMap();
      categoryHashMap.put(CategoryData.getInstance().NAME, this.category);
      //categoryHashMap.put(CategoryData.LEVEL,new Integer(this.level).toString());
      return categoryHashMap;
   }

   public Vector toVector()
   {
      Vector categoryVector = new Vector();
      categoryVector.add(category);
      //categoryVector.add(new Integer(this.level).toString());
      return categoryVector;
   }

   public Document toValidationInfoDoc() throws Exception
   {
      return null;
   }

   public Node toValidationInfoNode(Document document) throws Exception
   {
      return null;
   }

   public String validationInfo() throws Exception
   {
      return null;
   }
}