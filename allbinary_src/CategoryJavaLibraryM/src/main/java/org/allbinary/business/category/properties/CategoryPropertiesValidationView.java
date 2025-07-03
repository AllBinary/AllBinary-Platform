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
package org.allbinary.business.category.properties;



import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.validate.ValidationInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class CategoryPropertiesValidationView extends CategoryPropertiesView 
    implements ValidationInterface
{
   public CategoryPropertiesValidationView(CategoryPropertiesInterface categoryPropertiesInterface)
   {
      super(categoryPropertiesInterface);
   }
   
   public Boolean isValid() throws Exception
   {
      return this.categoryPropertiesInterface.isValid();
   }
   
   public String validationInfo() throws Exception
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();

         stringBuffer.append(this.categoryPropertiesInterface.validationInfo());
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                 org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate validation info error", this, "validationInfo()", e));
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
