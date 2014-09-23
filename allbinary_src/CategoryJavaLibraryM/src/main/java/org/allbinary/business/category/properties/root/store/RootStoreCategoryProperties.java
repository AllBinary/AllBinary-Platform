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
package org.allbinary.business.category.properties.root.store;

import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.basic.path.AbPath;
import org.allbinary.logic.basic.path.AbPathData;
import org.allbinary.logic.basic.path.AbPathUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.category.CategoryData;
import org.allbinary.business.category.CategoryUtil;
import org.allbinary.business.category.hierarchy.CategoryHierarchyInterface;
import org.allbinary.business.category.properties.CategoryPropertiesInterface;
import org.allbinary.business.category.properties.root.RootCategoryPropertiesInterface;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.logic.basic.string.StringValidationUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

/*
 *Nothing more than a parent category reference to a category on the fs.  
 *i.e. unloaded category.
 */
public class RootStoreCategoryProperties 
   implements RootCategoryPropertiesInterface, CategoryPropertiesInterface
{
   private AbPath fileAbPath;
   private AbPath abPath;
   private String category;
   private boolean isRealRoot;
   
   protected AbPath webAppAbPath;
   
   protected TransformInfoInterface transformInfoInterface;
   
   //This is the real root category
   public RootStoreCategoryProperties(TransformInfoInterface transformInfoInterface) throws Exception
   {
      this.transformInfoInterface = transformInfoInterface;
      this.abPath = new AbPath();
      this.category = CategoryData.getInstance().ROOTCATEGORY;
      this.isRealRoot = true;
      this.initPath();
      this.log();
   }
   
   //categoryPath = category where last token is used as the filename
   //Example were categoryPath = "Category" the file path to the root category
   //will be storeCategoryPath/Category/Category.xml which is a sub category of the root
   public RootStoreCategoryProperties(
      TransformInfoInterface transformInfoInterface, AbPath categoryAbPath) throws Exception
   {
      this.transformInfoInterface = transformInfoInterface;
      this.abPath = categoryAbPath;
      this.category = AbPathUtil.getInstance().getNameFromPath(categoryAbPath.toString());
      
      if(StringValidationUtil.getInstance().isEmpty(this.category))
      {
         this.isRealRoot = true;
         this.category = CategoryData.getInstance().ROOTCATEGORY;
      }
      
      this.initPath();
      this.log();
   }
      
   public RootStoreCategoryProperties(
      TransformInfoInterface transformInfoInterface, Node node) throws Exception
   {
      this.transformInfoInterface = transformInfoInterface;
      String categoryPath = CategoryUtil.getNameFromNode(node);

      this.abPath = new AbPath(categoryPath);
      this.category = AbPathUtil.getInstance().getNameFromPath(categoryPath);
      
      if(StringValidationUtil.getInstance().isEmpty(this.category))
      {
         this.isRealRoot = true;
         this.category = CategoryData.getInstance().ROOTCATEGORY;
      }
      
      this.initPath();
      this.log();
   }

   public RootStoreCategoryProperties(
      TransformInfoInterface transformInfoInterface,
      HashMap categoryPropertiesHashMap) throws Exception
   {
      this.transformInfoInterface = transformInfoInterface;
      String categoryPath = 
         new String((String) categoryPropertiesHashMap.get(
            CategoryData.getInstance().NAME));

      this.abPath = new AbPath(categoryPath);
      this.category = AbPathUtil.getInstance().getNameFromPath(categoryPath);
      
      if(StringValidationUtil.getInstance().isEmpty(this.category))
      {
         this.isRealRoot = true;
         this.category = CategoryData.getInstance().ROOTCATEGORY;
      }
      
      this.initPath();
      this.log();
   }

   protected void initPath() throws Exception
   {
	   TransformInfoHttpInterface transformInfoHttpStoreInterface = 
         (TransformInfoHttpInterface) this.transformInfoInterface;

      StoreFrontInterface storeFrontInterface =
         StoreFrontFactory.getInstance(
            transformInfoHttpStoreInterface.getStoreName());

      String postPath = 
         storeFrontInterface.getCurrentHostNamePath() + 
         storeFrontInterface.getCategoryPath();

      HttpServletRequest httpServletRequest = (HttpServletRequest)
         transformInfoHttpStoreInterface.getPageContext().getRequest();

      this.webAppAbPath = new AbPath(httpServletRequest.getContextPath() + postPath);

      this.setRootFilePath(new AbPath(URLGLOBALS.getMainPath() + postPath));
   }

   public boolean isRealRoot()
   {
      return this.isRealRoot;
   }
   
   public boolean isRoot()
   {
      return true;
   }
   
   public Object getKey()
   {
      return (Object) this.getValue();
   }

   public void setPath(AbPath path)
   {
      this.abPath = path;
   }

   public void setRootFilePath(AbPath value)
   {
      this.fileAbPath = value;
   }

   public AbPath getRootFilePath()
   {
      return this.fileAbPath;
   }

   public String getValue()
   {
      return this.category;
   }

   public AbPath getWebAppPath() throws Exception
   {
      return this.webAppAbPath;
   }
   
   public AbPath getPath(CategoryHierarchyInterface categoryHierarchyInterface)
   {
      return this.abPath;
   }
   
   public String getFileName()
   {
      return this.getValue() + AbPathData.getInstance().EXTENSION_SEP + CategoryData.getInstance().UNCRYPTED_EXTENSION;
   }

   public void setValue(String value)
   {
      this.category = value;
   }
   
   public Boolean isValid()
   {
      //if() return false;
      return Boolean.TRUE;
   }

   public HashMap toHashMap()
   {
      HashMap categoryHashMap = new HashMap();
      categoryHashMap.put(CategoryData.getInstance().NAME, this.getValue());
      //categoryHashMap.put(CategoryData.getInstance().LEVEL,new Integer(this.level).toString());
      return categoryHashMap;
   }

   public Vector toVector()
   {
      Vector categoryVector = new Vector();
      categoryVector.add(this.getValue());
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
   
   public void log()
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.CATEGORY))
      {
         LogUtil.put(LogFactory.getInstance("filePath = " + this.fileAbPath.toString() +
                 "\npath = " + this.abPath.toString() + 
                 "\ncategory = " + this.category, 
                 this, "log()"));
      }
   }
}