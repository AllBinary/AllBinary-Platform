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
package views.admin.storefront;

import org.allbinary.logic.communication.log.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.Document;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.business.context.modules.storefront.StoreFrontFactory;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.control.validate.ValidationComponentInterface;

public class EditValidationStoreFrontView extends StoreFrontComponent implements ValidationComponentInterface
{
   public EditValidationStoreFrontView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }
      
   public Boolean isValid()
   {
      try
      {
         Boolean valid = Boolean.TRUE;
         
         if(this.newStoreFrontInterface.isNameValid() == Boolean.FALSE)
         {
            valid = Boolean.FALSE;
         }
         
         if(StoreFrontFactory.getInstance(this.newStoreFrontInterface.getName())==null)
         {
            valid = Boolean.FALSE;
         }
         
         this.newStoreFrontInterface = StoreFrontFactory.getInstance(this.newStoreFrontInterface.getName());
         
         return valid;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate form",this,commonStrings.IS_VALID,e));
         }
         return Boolean.FALSE;
      }
   }

   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();
         
         if(this.newStoreFrontInterface.isNameValid() == Boolean.FALSE)
         {
            stringBuffer.append(this.newStoreFrontInterface.nameValidationInfo());
         }

         if(StoreFrontFactory.getInstance(this.newStoreFrontInterface.getName())==null)
         {
            stringBuffer.append(this.newStoreFrontInterface + " does not exist.<br>");
         }
         
         return "Unkown Error";
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate validation error info",this,"validationInfo()",e));
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
