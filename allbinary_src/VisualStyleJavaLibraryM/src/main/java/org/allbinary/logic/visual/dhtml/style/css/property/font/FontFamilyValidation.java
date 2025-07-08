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
package org.allbinary.logic.visual.dhtml.style.css.property.font;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.validate.ValidationInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class FontFamilyValidation extends FontFamilyView implements ValidationInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   public FontFamilyValidation()
   {
      super();
   }
   
   public FontFamilyValidation(Node node) throws Exception
   {
      super(node);
   }

   /*
   public FontFamilyValidation(HashMap hashMap)
   {
      super(hashMap);
   }
   */
   
   public Boolean isValid() throws Exception
   {
      try
      {
         Boolean valid = Boolean.TRUE;

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            logUtil.put("FontFamilyValidation",this,commonStrings.IS_VALID);
         }

         /*
         if(!StringValidationUtil.isValidRequired(,1,StyleData.NAMEMAXLEN))
         {
            valid = Boolean.FALSE;
         }
          */

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            logUtil.put("CssValidation: " + valid,this,commonStrings.IS_VALID);
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

   public String validationInfo() throws Exception
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();

         stringBuffer.append("Css Font Family Property Validation Error");
         
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

   public Document toValidationInfoDoc() throws Exception
   {
      return null;
   }
   
   public Node toValidationInfoNode(Document document) throws Exception
   {
      return null;
   }
}
