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
package views.admin.workflow;

import org.allbinary.logic.communication.log.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.Document;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.control.validate.ValidationComponentInterface;

import org.allbinary.logic.communication.log.LogUtil;

public class WorkflowsValidationView extends WorkFlowsView implements ValidationComponentInterface
{
   public WorkflowsValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }
   
   public Boolean isValid()
   {
      try
      {
         return Boolean.TRUE;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate",this,"isValid()",e));
         }
         return Boolean.FALSE;
      }
   }
   
   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();

         stringBuffer.append("Please Contact Administrator");
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate validation error info",this,"validationInfo()",e));
         }
         return "Error Getting Validation Info";
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
