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
package views.business.context.modules.storefront.customizer.bodies.generic.title;
        
import abcs.logic.communication.log.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.Document;


import abcs.logic.communication.log.LogUtil;

import allbinary.data.tree.dom.DomNodeInterface;

import allbinary.logic.visual.transform.info.TransformInfoInterface;

import allbinary.logic.control.validate.ValidationComponentInterface;

import views.business.context.modules.storefront.customizer.CustomizerUtil;

import allbinary.logic.visual.transform.template.customizer.bodies.title.TitleBodyValidation;

public class NewTitleBodyValidationView extends TitleBodyCustomizerView implements ValidationComponentInterface
{
   public NewTitleBodyValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);

      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("View Name: " + transformInfoInterface.getName(), this, "NewTitleBodyValidationView()"));
      }

      this.titleBody = new TitleBodyValidation();
   }

    public Document toXmlDoc() throws Exception
    {
        return null;
    }

   public Boolean isValid()
   {
      try
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
         {
            LogUtil.put(LogFactory.getInstance("View Name: " + this.getTransformInfoInterface().getName(), this, "isValid()"));
         }
         //Boolean isValid = this.heading.isValid();
                  
         //if(isValid == Boolean.TRUE)
         {
            //Insert XML into the view specified by the Object Config for this view
            CustomizerUtil.insert(this.getTransformInfoInterface(),(DomNodeInterface) this.titleBody);
         }
         
         //return isValid;
         return Boolean.TRUE;
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
         
         stringBuffer.append(this.titleBody.validationInfo());
         
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
   
   public String view() throws Exception
   {
      return views.ValidationOnlyTempUtil.view(this);
   }
}
