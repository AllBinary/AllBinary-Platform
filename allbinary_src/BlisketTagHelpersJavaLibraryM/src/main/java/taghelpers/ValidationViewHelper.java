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
package taghelpers;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.validate.ValidationComponentInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ValidationViewHelper extends ViewHelper implements ValidationComponentInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private ValidationComponentInterface viewInterface;
   
   public ValidationViewHelper(HashMap hashMap, PageContext pageContext) throws Exception
   {
      super(hashMap, pageContext);      
      
      viewInterface = (ValidationComponentInterface) this.getViewObject();
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPER))
      {
         logUtil.put(this.commonStrings.START, this,this.commonStrings.CONSTRUCTOR);
      }      
   }
         
   public Boolean isValid()
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPER))
         {
            logUtil.put("Started",this,commonStrings.IS_VALID);
         }      
         
         return viewInterface.isValid();
      }
      catch(Exception e)
      {
         //String error = "Failed to check valid value: ";
         //if(this.getTransformInfoFactoryInterface()!=null) error += this.getTransformInfoFactoryInterface().getName();
         //else 
            //error += "Unknown";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,commonStrings.IS_VALID,e);
         }
         return Boolean.FALSE;
      }
   }   

   public String validationInfo()
   {
      try
      {
         return viewInterface.validationInfo();
      }
      catch(Exception e)
      {
         //String error = "Failed to retrieve validation info for: ";
         //if(this.getTransformInfoFactoryInterface()!=null) 
           // error += this.getTransformInfoFactoryInterface().getName();
         //else 
            //error += "Unknown";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"validationInfo()",e);
         }
         return "Unknown Validation Error";
      }
   }
   
   public Document toValidationInfoDoc()
   {
      try
      {
         return viewInterface.toValidationInfoDoc();
      }
      catch(Exception e)
      {
         //String error = "Failed to get validation info doc for: ";
         //if(this.getTransformInfoFactoryInterface()!=null) 
           // error += this.getTransformInfoFactoryInterface().getName();
         //else 
         //error += "Unknown";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"toValidationInfoDoc()",e);
         }
         return null;
      }
   }
   
   public Node toValidationInfoNode(Document document)
   {
      try
      {
         return viewInterface.toValidationInfoNode(document);
      }
      catch(Exception e)
      {
         //String error = "Failed to get validation node for: ";
         //if(this.getTransformInfoFactoryInterface()!=null) 
           // error += this.getTransformInfoFactoryInterface().getName();
         //else 
            //error += "Unknown";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"toValidationInfoNode()",e);
         }
         return null;
      }
   }
}
