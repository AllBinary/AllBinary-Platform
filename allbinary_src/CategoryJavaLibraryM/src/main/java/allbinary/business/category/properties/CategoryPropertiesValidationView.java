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
package allbinary.business.category.properties;



import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.control.validate.ValidationInterface;
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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                 abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
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
