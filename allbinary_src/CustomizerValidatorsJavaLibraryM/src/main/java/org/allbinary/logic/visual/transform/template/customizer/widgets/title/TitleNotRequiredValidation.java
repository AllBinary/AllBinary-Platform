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
package org.allbinary.logic.visual.transform.template.customizer.widgets.title;

import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.validate.ValidationInterface;
import org.allbinary.logic.string.StringValidationUtil;

public class TitleNotRequiredValidation extends TitleView implements ValidationInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();
   
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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            logUtil.put(this.commonStrings.START, this, commonStrings.IS_VALID);
         }
         
         Boolean valid = Boolean.TRUE;
         
         if(!StringValidationUtil.getInstance().isValidNotRequired(this.getTitle(), 0, TitleData.getInstance().MAXLEN))
         {
            valid = Boolean.FALSE;
         }

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
             StringBuffer stringBuffer = new StringBuffer();

             stringBuffer.append("Title: ");
             stringBuffer.append(this.getTitle());
             stringBuffer.append("End: ");
             stringBuffer.append(valid);

            logUtil.put(stringBuffer.toString(), this, commonStrings.IS_VALID);
         }
         
         return valid;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put("Failed to validate form", this, commonStrings.IS_VALID, e);
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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put("Failed to generate validation error info", this, "validationInfo()", e);
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