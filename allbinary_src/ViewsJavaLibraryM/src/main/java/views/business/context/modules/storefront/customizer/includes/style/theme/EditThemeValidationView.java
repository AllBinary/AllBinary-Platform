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
package views.business.context.modules.storefront.customizer.includes.style.theme;

import abcs.data.tree.dom.document.DomDocumentHelper;
import abcs.logic.communication.log.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.Document;

import allbinary.data.tree.category.CategoryLoaderInterface;
import allbinary.data.tree.category.CategoryLoaderFactory;

import allbinary.business.category.CategoryInterface;
import allbinary.business.category.CategoryFactoryInterface;

import allbinary.business.category.store.theme.StoreThemeCategoryFactory;
import allbinary.business.category.store.theme.StoreThemeCategoryInterface;
import allbinary.business.category.store.theme.StoreThemeCategoryPathValidationView;

import allbinary.logic.visual.transform.info.TransformInfoInterface;

import allbinary.logic.control.validate.ValidationComponentInterface;

import abcs.logic.communication.log.LogUtil;
import allbinary.data.tree.dom.DomNodeInterface;

public class EditThemeValidationView extends ThemeCustomizerView implements ValidationComponentInterface
{
   public EditThemeValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }
   
   public Boolean isValid()
   {
      try
      {
         Boolean isValid = Boolean.TRUE;

         CategoryFactoryInterface categoryFactoryInterface =
            new StoreThemeCategoryFactory(this.getTransformInfoInterface());

         CategoryLoaderInterface categoryLoaderInterface = 
            CategoryLoaderFactory.getInstance(categoryFactoryInterface);

         StoreThemeCategoryInterface rootStoreThemeCategoryInterface = 
            (StoreThemeCategoryInterface) categoryFactoryInterface.getRootInstance();
         
         StoreThemeCategoryInterface storeThemeCategoryInterface =
            (StoreThemeCategoryInterface)
               //categoryTreeInterface.get((CategoryInterface) rootStoreThemeCategoryInterface);
               categoryLoaderInterface.getAll((CategoryInterface) rootStoreThemeCategoryInterface);
         
         this.validationInterface = 
            new StoreThemeCategoryPathValidationView(
               (StoreThemeCategoryInterface) storeThemeCategoryInterface);

         isValid = this.validationInterface.isValid();

         //TWB - Set data from category - could be made to not read data file 2 times

         //categoryLoaderInterface.getDoc((CategoryInterface) rootStoreThemeCategoryInterface)

         DomNodeInterface domNodeInterface = 
        	 (DomNodeInterface) this.validationInterface;
         
         Document document = DomDocumentHelper.create();
         document.appendChild(domNodeInterface.toXmlNode(document));
         String documentString = DomDocumentHelper.toString(document);

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Setting Data: " + documentString, this, "Constructor()"));
         }

         this.getTransformInfoInterface().setData(documentString);
         
         return isValid;
      }
      catch(Exception e)
      {
          if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
          {
             LogUtil.put(LogFactory.getInstance("Failed to validate",this,"isValid()",e));
          }
         return Boolean.FALSE;
      }
   }
   
   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();
         
         stringBuffer.append(this.validationInterface.validationInfo());
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate validation error info",this,"validationInfo()",e));
         }
         return "Error Getting Validation Info";
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
}
