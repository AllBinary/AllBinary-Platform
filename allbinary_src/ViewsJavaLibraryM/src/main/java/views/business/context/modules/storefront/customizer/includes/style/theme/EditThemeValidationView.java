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

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.communication.log.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.Document;

import org.allbinary.data.tree.category.CategoryLoaderInterface;
import org.allbinary.data.tree.category.CategoryLoaderFactory;

import org.allbinary.business.category.CategoryInterface;
import org.allbinary.business.category.CategoryFactoryInterface;

import org.allbinary.business.category.store.theme.StoreThemeCategoryFactory;
import org.allbinary.business.category.store.theme.StoreThemeCategoryInterface;
import org.allbinary.business.category.store.theme.StoreThemeCategoryPathValidationView;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.control.validate.ValidationComponentInterface;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.DomNodeInterface;

public class EditThemeValidationView extends ThemeCustomizerView implements ValidationComponentInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put("Setting Data: " + documentString, this, this.commonStrings.CONSTRUCTOR);
         }

         this.getTransformInfoInterface().setData(documentString);
         
         return isValid;
      }
      catch(Exception e)
      {
          if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
          {
             logUtil.put("Failed to validate",this,commonStrings.IS_VALID,e);
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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put("Failed to generate validation error info",this,"validationInfo()",e);
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
