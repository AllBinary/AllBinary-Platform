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
package org.allbinary.logic.visual.dhtml.style.css;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.validate.ValidationInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class CssElementValidation extends CssElementView implements ValidationInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   public CssElementValidation()
   {
      super();
   }
      
   public CssElementValidation(Node node) throws Exception
   {
      super(node);
   }
   
   /*
   public CssElementValidation(HashMap hashMap)
   {
      super(hashMap);
   }
     */
   
   public Boolean isValid()
   {
      try
      {
         Boolean valid = Boolean.TRUE;

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            logUtil.put("CssElementValidation",this,commonStrings.IS_VALID);
         }

         /*
         if(!StringValidationUtil.isValidRequired(,1,StyleData.NAMEMAXLEN))
         {
            valid = Boolean.FALSE;
         }

         iter = this.getStyleAttributes();
         while(iter.next())
         {
            ValidationInterface styleAttributeValidationInterface = 
               (ValidationInterface) iter.next();

            if(styleAttributeValidationInterface.isValid().booleanValue())
            {
               valid = Boolean.FALSE;
            }
         }
         */

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            logUtil.put("CssElementValidation: " + valid,this,commonStrings.IS_VALID);
         }
         
         return valid;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put("Failed to validate data",this,commonStrings.IS_VALID,e);
         }
         return Boolean.FALSE;
      }
   }
   
   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();

         stringBuffer.append(this.getValue());
         stringBuffer.append(" Css Style is not valid.");
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put("Failed to generate validation info error",this,"validationInfo()",e);
         }
         return "Error Validating Data";
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