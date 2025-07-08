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

import java.util.HashMap;

import org.w3c.dom.Node;
import org.w3c.dom.Document;

import org.allbinary.logic.communication.http.request.NameSpaceRequestParams;
import org.allbinary.logic.visual.theme.ThemeValidation;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.control.validate.ValidationComponentInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import views.business.context.modules.storefront.customizer.CustomizerUtil;

import views.business.context.modules.storefront.customizer.StoreCustomizerComponentUtil;

public class InsertThemeValidationView extends ThemeCustomizerView implements ValidationComponentInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private HashMap requestHashMap;
   
   public InsertThemeValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         logUtil.put(this.commonStrings.CONSTRUCTOR,this,"started");
      }
      
      this.requestHashMap =
         new NameSpaceRequestParams(this.getPageContext()).toHashMap();

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         logUtil.put(this.commonStrings.CONSTRUCTOR,this,"begin loading theme");
      }
   }

    public Document toXmlDoc() throws Exception
    {
        return null;
    }

   public Boolean isValid()
   {
      try
      {
         Boolean isValid = Boolean.TRUE;

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            logUtil.put("Started Validation",this,commonStrings.IS_VALID);
         }

         ThemeValidation themeValidation = 
            new ThemeValidation(this.getTransformInfoInterface(), requestHashMap);
         
         if(themeValidation.isValid() == Boolean.FALSE)
         {
            isValid = Boolean.FALSE;
         }
                  
         this.validationInterface = themeValidation.getCssStyleValidation();

         isValid = this.validationInterface.isValid();

         if(isValid == Boolean.TRUE)
         {
            //Insert XML into the view specified by the Object Config for this view
            CustomizerUtil.getInstance().insert(
               this.getTransformInfoInterface(),
               (DomNodeInterface) this.validationInterface);
         }
         
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
         
         //stringBuffer.append(this.body.validationInfo());
         
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
   
   public String view() throws Exception
   {
      try
      {
         return StoreCustomizerComponentUtil.getInstance().generate(this.abeClientInformation, this.getTransformInfoInterface());
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"view()",e);
         }
         throw e;
      }
   }
}