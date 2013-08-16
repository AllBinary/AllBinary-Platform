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
package allbinary.logic.visual.transform.template.customizer.widgets.title;

import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.control.validate.ValidationInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.HashMap;

public class TitleNotRequiredValidation extends TitleView implements ValidationInterface
{   
   public TitleNotRequiredValidation()
   {
      super();
   }

   public TitleNotRequiredValidation(Node node) throws Exception
   {
      super(node);
   }
   
   public TitleNotRequiredValidation(HashMap hashMap)
   {
      super(hashMap);
   }
      
   public Boolean isValid()
   {
      try
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
         {
            LogUtil.put(LogFactory.getInstance("Start", this, "isValid()"));
         }
         
         Boolean valid = Boolean.TRUE;
         
         if(!StringValidationUtil.getInstance().isValidNotRequired(this.getTitle(), 0, TitleData.getInstance().MAXLEN))
         {
            valid = Boolean.FALSE;
         }

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
         {
             StringBuffer stringBuffer = new StringBuffer();

             stringBuffer.append("Title: ");
             stringBuffer.append(this.getTitle());
             stringBuffer.append("End: ");
             stringBuffer.append(valid);

            LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "isValid()"));
         }
         
         return valid;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate form", this, "isValid()", e));
         }
         return Boolean.FALSE;
      }
   }
   
   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();
                  
         stringBuffer.append("Title is not valid.");
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate validation error info", this, "validationInfo()", e));
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
}