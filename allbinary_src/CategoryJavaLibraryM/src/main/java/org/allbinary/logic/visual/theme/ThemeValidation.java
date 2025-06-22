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
package org.allbinary.logic.visual.theme;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.io.path.AbPathUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.category.CategoryData;
import org.allbinary.business.category.CategoryFactoryInterface;
import org.allbinary.business.category.store.theme.StoreThemeCategoryFactory;
import org.allbinary.business.category.store.theme.StoreThemeCategoryInterface;
import org.allbinary.data.tree.category.CategoryLoaderFactory;
import org.allbinary.data.tree.category.CategoryLoaderInterface;
import org.allbinary.data.tree.dom.DomData;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.control.crypt.file.CryptFileReader;
import org.allbinary.logic.control.validate.ValidationInterface;
import org.allbinary.logic.visual.transform.info.CompositeTransformInfoInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.template.customizer.includes.style.css.template.retail.CssStyleValidation;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.communication.sql.AbSqlData;
import org.allbinary.string.CommonStrings;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ThemeValidation 
   implements ThemeInterface, ValidationInterface, DomNodeInterface, CompositeTransformInfoInterface
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();

   private TransformInfoInterface transformInfoInterface;
   private ValidationInterface styleValidationInterface;
   
   private String themeName;
   private String themePreviewImageName;
   private AbPath webAppAbPath;
   
   private AbPath categoryAbPath;
   
   private AbPath fileAbPath;

   public ThemeValidation(
      TransformInfoInterface transformInfoInterface,
      CssStyleValidation cssStyleValidation, 
      String categoryThemePath)
      throws Exception
   {
      this.transformInfoInterface = transformInfoInterface;
      
      this.categoryAbPath = AbPathUtil.getInstance().removeNameFromPath(categoryThemePath);
      this.themeName = AbPathUtil.getInstance().getNameFromPath(categoryThemePath);

      this.styleValidationInterface = cssStyleValidation;
      
      this.init();
   }

   public ThemeValidation(
      TransformInfoInterface transformInfoInterface,
      HashMap hashMap) throws Exception
   {
      this.transformInfoInterface = transformInfoInterface;

      AbPath categoryThemeAbPath = 
         new AbPath((String) hashMap.get(ThemeData.getInstance().PATH));

      this.categoryAbPath = AbPathUtil.getInstance().removeNameFromPath(categoryThemeAbPath.toString());
      this.themeName = AbPathUtil.getInstance().getNameFromPath(categoryThemeAbPath.toString());

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
    	  StringBuffer stringBuffer = new StringBuffer();
    	  
    	  stringBuffer.append("CategoryThemePath: ");
    	  stringBuffer.append(categoryThemeAbPath.toString());
    	  stringBuffer.append(" & CategoryPath: ");
    	  stringBuffer.append(this.categoryAbPath.toString());
    	  stringBuffer.append(" & ThemeName: ");
    	  stringBuffer.append(this.themeName);

    	  LogUtil.put(LogFactory.getInstance("Http Request Constructor", this, stringBuffer.toString()));
      }

      //Load the theme properties from the category file associated with the theme
      this.init();
   }
   
   public ThemeValidation(
      StoreThemeCategoryInterface storeThemeCategoryInterface, 
      Node node) 
      throws Exception
   {
      this.transformInfoInterface = storeThemeCategoryInterface.getTransformInfoInterface();

      this.categoryAbPath = storeThemeCategoryInterface.getPath();

      Node themeNameNode =
         DomSearchHelper.getNode(ThemeData.getInstance().NAME, node.getChildNodes());
      Node valueNode =
         DomSearchHelper.getNode(DomData.VALUE, themeNameNode.getChildNodes());
      this.themeName = DomNodeHelper.getTextNodeValue(valueNode);

      Node previewImageNameNode =
         DomSearchHelper.getNode(ThemeData.getInstance().PREVIEW_IMAGE_NAME, node.getChildNodes());
      Node previewImageNameValueNode =
         DomSearchHelper.getNode(DomData.VALUE, previewImageNameNode.getChildNodes());
      this.themePreviewImageName = 
         DomNodeHelper.getTextNodeValue(previewImageNameValueNode);

      this.webAppAbPath = storeThemeCategoryInterface.getWebAppPath();
      
      this.init(storeThemeCategoryInterface);
   }

   /*
         StoreThemeCategoryFactory storeThemeCategoryFactory = 
            new StoreThemeCategoryFactory(this.getTransformInfoInterface());

         //String categoryName = CategoryUtil.getNameFromPath(this.categoryPath);
         //this.categoryPath, categoryName
         //load the css associated with the categoryPathPath
         CategoryInterface rootStoreThemeCategoryInterface = 
            storeThemeCategoryFactory.getRootInstance(this.categoryPath);
                  
         StoreThemeCategory storeThemeCategory = (StoreThemeCategory)
            CategoryTreeFactory.getInstance(
               storeThemeCategoryFactory).get(rootStoreThemeCategoryInterface);
   */
   
   private void init() throws Exception
   {
      CategoryFactoryInterface categoryFactoryInterface =
         new StoreThemeCategoryFactory(this.getTransformInfoInterface());

      CategoryLoaderInterface categoryLoaderInterface = 
         CategoryLoaderFactory.getInstance(categoryFactoryInterface);
      
      StoreThemeCategoryInterface rootStoreThemeCategoryInterface = 
         (StoreThemeCategoryInterface) 
            categoryFactoryInterface.getRootInstance();
      
      this.init(rootStoreThemeCategoryInterface);
   }
   
   private void init(StoreThemeCategoryInterface storeThemeCategoryInterface) throws Exception
   {
      this.fileAbPath = new AbPath(
	 storeThemeCategoryInterface.getRootFilePath().toString() + 
         this.categoryAbPath.toString());

      this.webAppAbPath = storeThemeCategoryInterface.getWebAppPath();
   }

   public TransformInfoInterface getTransformInfoInterface() throws Exception
   {
      return transformInfoInterface;
   }
   
   public String getName()
   {
      return this.themeName;
   }
   
   public String getPreviewImageName()
   {
      return this.themePreviewImageName;
   }
   
   public String getPreviewImagePath()
   {
      return this.webAppAbPath.toString() + this.categoryAbPath.toString();
   }
      
   public String getPath()
   {
      return this.categoryAbPath.toString();
   }

   public CssStyleValidation getCssStyleValidation() throws Exception
   {
      if(this.styleValidationInterface == null)
      {
         AbPath cssStyleFileAbPath = new AbPath(this.fileAbPath.toString(), 
            this.themeName + AbPathData.getInstance().EXTENSION_SEP +
            CategoryData.getInstance().UNCRYPTED_EXTENSION);
         
         //Load the css style for this theme themes 
         CryptFileReader cryptFileReader = new CryptFileReader(
            CategoryData.getInstance().UNCRYPTED_EXTENSION,
            CategoryData.getInstance().ENCRYPTED_EXTENSION);

         Document document = DomDocumentHelper.create(
            cryptFileReader.get(cssStyleFileAbPath));
      
         this.styleValidationInterface = new CssStyleValidation(document);
      }
      return (CssStyleValidation) this.styleValidationInterface;
   }
   public Boolean isValid()
   {
      try
      {
         Boolean isValid = Boolean.TRUE;

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START,this,"isValid()"));
         }

         if(!StringValidationUtil.getInstance().isValidRequired(
            this.categoryAbPath.toString(), AbSqlData.MINSTRING, AbSqlData.MAXSTRING))
         {
            isValid = Boolean.FALSE;
         }

         if(!StringValidationUtil.getInstance().isValidRequired(
            this.themeName, AbSqlData.MINSTRING, AbSqlData.MAXSTRING))
         {
            isValid = Boolean.FALSE;
         }

         if(!this.getCssStyleValidation().isValid().booleanValue())
         {
            isValid = Boolean.FALSE;
         }

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            LogUtil.put(LogFactory.getInstance("End: " + isValid, this, "isValid()"));
         }
         
         return isValid;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate form",this,"isValid()",e));
         }
         return Boolean.FALSE;
      }
   }

   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();
         
         stringBuffer.append("Theme Validation Error");
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate validation error info",this,"validationInfo()",e));
         }
         return "Error Validating Form";
      }
   }

   public Document toValidationInfoDoc()
   {
      return null;
   }

   public Node toValidationInfoNode(Document document)
   {
      return null;
   }
   
   public Object getKey()
   {
      return (Object) this.getName();
   }
   
   public Vector toVector()
   {
      Vector vector = new Vector();
      
      vector.add(this.getName());
      vector.add(this.getPath());
      vector.add(this.getPreviewImageName());
      vector.add(this.getPreviewImagePath());
      
      return vector;
   }
   
   public HashMap toHashMap()
   {
	   ThemeData themeData = ThemeData.getInstance();
	   
      HashMap hashMap = new HashMap();

      hashMap.put(themeData.NAME, this.getName());
      hashMap.put(themeData.PATH, this.getPath());
      hashMap.put(themeData.PREVIEW_IMAGE_NAME, this.getPreviewImageName());
      hashMap.put(themeData.PREVIEW_IMAGE_PATH, this.getPreviewImagePath());

      return hashMap;
   }
   
   public Node toXmlNode(Document document) throws Exception
   {
      Node node = 
         ModDomHelper.createNameValueNodes(
            document, ThemeData.getInstance().NAME, this.toHashMap());

      DomNodeInterface domNodeInterface = 
         (DomNodeInterface) this.getCssStyleValidation();
      node.appendChild(domNodeInterface.toXmlNode(document));

      return node;
   }
}
