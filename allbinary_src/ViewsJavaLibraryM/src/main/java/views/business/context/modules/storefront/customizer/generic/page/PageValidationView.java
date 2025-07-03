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
package views.business.context.modules.storefront.customizer.generic.page;

import java.util.HashMap;

import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import views.business.context.modules.storefront.customizer.CustomizerUtil;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tables.transform.info.TransformInfoEntity;
import org.allbinary.data.tables.transform.info.TransformInfoEntityBuilder;
import org.allbinary.logic.control.validate.ValidationComponentInterface;
import org.allbinary.logic.visual.transform.info.TransformInfo;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigInterface;
import org.allbinary.logic.visual.transform.template.customizer.page.PageValidation;
import org.allbinary.logic.visual.transform.template.customizer.widgets.title.TitleData;

public class PageValidationView extends PageView implements ValidationComponentInterface
{
   public PageValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         LogUtil.put(LogFactory.getInstance("View Name: " + transformInfoInterface.getName(), this, "PageViewValidation()"));
      }
   }
   
   public Boolean isValid()
   {
      try
      {
         Boolean isValid = Boolean.TRUE;
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START, this, commonStrings.IS_VALID));
         }

         //Insert XML into the view specified by the Object Config for this view
         //CustomizerUtils.insert(this.getTransformInfoInterface(),(DomNodeInterface) pageValidation);

         TransformInfoEntity transformInfoEntityInterface =
        	 TransformInfoEntityBuilder.getInstance();

         //This Object Config Contains the Components that are Generators that
         //need page titles
         TransformInfoObjectConfigInterface pageObjectConfigInterface =
               this.getTransformInfoInterface().getObjectConfigInterface();
            
         //Vector generatorsToBeModified = objectConfig.getComponents();
         
         //this.insertIntoTransformInfos(pageObjectConfig);

         //Vector allViewsToBeModified = objectConfig.getComponents();
         Vector allViewsToBeModifiedVector = pageObjectConfigInterface.getGroupTransforms();

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            LogUtil.put(LogFactory.getInstance("Views To Be Modified: " + allViewsToBeModifiedVector.size(), this, "get(transformInfoInterface)"));
         }
         
         final int size = allViewsToBeModifiedVector.size();
         for(int index = 0; index < size; index++)
         {
             TransformInfo transformInfoInterface =
               (TransformInfo) allViewsToBeModifiedVector.get(index);

            String viewNameOfViewToBeModified = 
            	transformInfoInterface.getName();

            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
            	StringBuffer stringBuffer = new StringBuffer();
            	
            	stringBuffer.append(this.getTransformInfoInterface().getName());
            	stringBuffer.append(" is modifying view: ");
            	stringBuffer.append(viewNameOfViewToBeModified);

            	LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "insert()"));
            }

            TransformInfoHttpInterface httpTransformInfoInterface = 
               (TransformInfoHttpInterface) this.getTransformInfoInterface();

            TransformInfoInterface specifiedTransformInfoInterface =
               transformInfoEntityInterface.get(
                  viewNameOfViewToBeModified,
                  httpTransformInfoInterface.getPropertiesHashMap(),
                  httpTransformInfoInterface.getPageContext());

            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
            	StringBuffer stringBuffer = new StringBuffer();
            	
            	stringBuffer.append(this.getTransformInfoInterface().getName());
            	stringBuffer.append(" is adding data to view: ");
            	stringBuffer.append(viewNameOfViewToBeModified);
            	
               LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "insert()"));
            }

            HashMap hashMap =  new HashMap();            
            String title = specifiedTransformInfoInterface.getName().substring(
               this.getTransformInfoInterface().getStoreName().length());

            if(title.compareTo("index")==0)  
            {
               hashMap.put(TitleData.getInstance().TEXT, this.getTransformInfoInterface().getStoreName() + " - Home Page");
            }
            else
            {
               hashMap.put(TitleData.getInstance().TEXT, this.getTransformInfoInterface().getStoreName() + " -" + title);
            }

            PageValidation pageValidation = new PageValidation(hashMap);

            if(pageValidation.isValid() == Boolean.FALSE)
            {
               isValid = Boolean.FALSE;
            }

            if(isValid == Boolean.TRUE)
            {
               //get the view xml/data that will replace the old xml/data
               Document document = DomDocumentHelper.create();
               document.appendChild(pageValidation.toXmlNode(document));
               String documentString = DomDocumentHelper.toString(document);

               if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
               {
                   StringBuffer stringBuffer = new StringBuffer();

                    stringBuffer.append(viewNameOfViewToBeModified);
                    stringBuffer.append(" is changing data in ");
                    stringBuffer.append(specifiedTransformInfoInterface.getDataFilePath());
                    stringBuffer.append(" to the following data:\n");
                    stringBuffer.append(documentString);

                    LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, commonStrings.IS_VALID));
               }

               //save xml data to specified view
               CustomizerUtil.getInstance().write(specifiedTransformInfoInterface, documentString);
            }
         }
         return isValid;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate",this,commonStrings.IS_VALID,e));
         }
         return Boolean.FALSE;
      }
   }
   
   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();

         TransformInfoEntity transformInfoEntityInterface =
        	 TransformInfoEntityBuilder.getInstance();

         TransformInfoObjectConfigInterface objectConfig =
            this.getTransformInfoInterface().getObjectConfigInterface();
         
         TransformInfoHttpInterface httpTransformInfoInterface = 
            (TransformInfoHttpInterface) this.getTransformInfoInterface();
         
         Vector allViewsToBeModifiedVector = objectConfig.getGroupTransforms();

         final int size = allViewsToBeModifiedVector.size();
         for(int index = 0; index < size; index++)
         {
             TransformInfo transformInfoInterface =
                 (TransformInfo) allViewsToBeModifiedVector.get(index);

            String viewNameOfViewToBeModified = transformInfoInterface.getName();

            TransformInfoInterface specifiedTransformInfoInterface =
               transformInfoEntityInterface.get(viewNameOfViewToBeModified,
                  httpTransformInfoInterface.getPropertiesHashMap(),
                  httpTransformInfoInterface.getPageContext());

            HashMap hashMap =  new HashMap();            
            String title = specifiedTransformInfoInterface.getName().substring(
               this.getTransformInfoInterface().getStoreName().length());

            if(title.compareTo("index")==0)
            {
               hashMap.put(TitleData.getInstance().TEXT, this.getTransformInfoInterface().getStoreName() + " - Home Page");
            }
            else
            {
               hashMap.put(TitleData.getInstance().TEXT, this.getTransformInfoInterface().getStoreName() + " -" + title);
            }

            PageValidation pageValidation = new PageValidation(hashMap);

            if(pageValidation.isValid() == Boolean.FALSE)
            {
               stringBuffer.append("TransformInfo Name for PageValidation:" + specifiedTransformInfoInterface.getName());
               stringBuffer.append("PageValidation:" + hashMap);
               stringBuffer.append("PageValidation Info:" + pageValidation.validationInfo());
            }
         }

         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate validation error info",this,"validationInfo()",e));
         }
         return "An auto generated page name was invalid.";
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
