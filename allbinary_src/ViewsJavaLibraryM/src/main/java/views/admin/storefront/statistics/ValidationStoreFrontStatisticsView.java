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
package views.admin.storefront.statistics;


import org.allbinary.logic.communication.log.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.Document;


import org.allbinary.logic.communication.log.LogUtil;


import org.allbinary.business.context.modules.storefront.StoreFrontInterface;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.control.validate.ValidationComponentInterface;

//extends StoreFrontComponent

public class ValidationStoreFrontStatisticsView 
   extends StoreFrontStatisticsComponent 
      implements ValidationComponentInterface
{
   protected StoreFrontInterface newStoreFrontInterface;
   
   public ValidationStoreFrontStatisticsView(TransformInfoInterface transformInfoInterface)throws Exception
   {
      super(transformInfoInterface);      
   }
      
   public Boolean isValid()
   {
      try
      {
         Boolean valid = Boolean.TRUE;

         return valid;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate form",this,"isValid()",e));
         }
         return Boolean.FALSE;
      }
   }

   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();
         
	 stringBuffer.append("Never");
	 
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
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
