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
package views.business.context.modules.storefront.customizer.includes.style.css;
        
import org.allbinary.logic.communication.log.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.Document;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.data.tree.dom.DomNodeInterface;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.control.validate.ValidationComponentInterface;

import org.allbinary.logic.visual.transform.template.customizer.includes.style.css.GenericCssStyleValidation;

import views.business.context.modules.storefront.customizer.CustomizerUtil;

public class NewCssValidationView extends CssCustomizerView implements ValidationComponentInterface
{
   public NewCssValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
            
      this.styleValidationInterface = new GenericCssStyleValidation();
   }

    public Document toXmlDoc() throws Exception
    {
        return null;
    }

   public Boolean isValid()
   {
      try
      {
         //Boolean isValid = this.heading.isValid();
                  
         //if(isValid == Boolean.TRUE)
         {
            //Insert XML into the view specified by the Object Config for this view
            CustomizerUtil.insert(this.getTransformInfoInterface(),(DomNodeInterface) this.styleValidationInterface);
         }
         
         //return isValid;
         return Boolean.TRUE;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.VIEWERROR))
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
         
         stringBuffer.append(this.styleValidationInterface.validationInfo());
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.VIEWERROR))
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
   
   public String view() throws Exception
   {
      return views.ValidationOnlyTempUtil.view(this);
   }
}