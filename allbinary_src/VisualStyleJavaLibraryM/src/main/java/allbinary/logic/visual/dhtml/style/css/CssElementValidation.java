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
package allbinary.logic.visual.dhtml.style.css;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.control.validate.ValidationInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class CssElementValidation extends CssElementView implements ValidationInterface
{
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

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
         {
            LogUtil.put(LogFactory.getInstance("CssElementValidation",this,"isValid()"));
         }

         /*
         if(!StringValidationUtil.isValidRequired(,1,StyleData.NAMEMAXLEN))
         {
            valid = Boolean.FALSE;
         }

         Iterator iter = this.getStyleAttributes().iterator();
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

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
         {
            LogUtil.put(LogFactory.getInstance("CssElementValidation: " + valid,this,"isValid()"));
         }
         
         return valid;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate data",this,"isValid()",e));
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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate validation info error",this,"validationInfo()",e));
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