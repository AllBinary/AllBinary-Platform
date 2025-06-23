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
package views.business.context.modules.storefront.customizer.hedges.header;
        
import java.util.HashMap;

import org.w3c.dom.Node;
import org.w3c.dom.Document;

import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.communication.log.LogFactory;


import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.logic.communication.http.request.MultipartRequestParams;

import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;

import org.allbinary.logic.visual.transform.template.customizer.hedges.heading.HeadingValidation;
import org.allbinary.logic.visual.transform.template.customizer.widgets.logo.LogoData;

import org.allbinary.data.tree.dom.DomNodeInterface;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.control.validate.ValidationComponentInterface;

import views.business.context.modules.storefront.customizer.CustomizerUtil;

public class InsertHeaderValidationView extends TopBarView implements ValidationComponentInterface
{
   private final String LOGOPATH = "logo/";
   
   public InsertHeaderValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      
      HashMap multipartRequestHashMap = 
         new MultipartRequestParams(
         this.getPageContext()).toHashMap();
            //pageContext).toHashMap();

/*      
      HashMap multipartRequestHashMap = (HashMap)
         this.getTransformInfoInterface().getPropertiesHashMap().get(
            MultipartRequestParamsData.HASHMAP);
*/
      StoreFrontInterface storeFrontInterface =
         StoreFrontFactory.getInstance(
            this.getWeblisketSession().getStoreName());
            
      String fullPath =
        URLGLOBALS.getWebappPath() +
        storeFrontInterface.getCurrentHostNamePath() + LOGOPATH;
      
      multipartRequestHashMap.put(LogoData.getInstance().IMAGEPATH, fullPath);
      this.heading = new HeadingValidation(multipartRequestHashMap);
   }

    public Document toXmlDoc() throws Exception
    {
        return null;
    }

   public Boolean isValid()
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            LogUtil.put(LogFactory.getInstance("topbarValidation",this,"isValid()"));
         }
         
         Boolean isValid = this.heading.isValid();
                  
         if(isValid == Boolean.TRUE)
         {
            //Insert XML into the view specified by the Object Config for this view
            CustomizerUtil.getInstance().insert(
               this.getTransformInfoInterface(), (DomNodeInterface) this);
         }
         
         return isValid;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
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
         
         stringBuffer.append(this.heading.validationInfo());
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
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
