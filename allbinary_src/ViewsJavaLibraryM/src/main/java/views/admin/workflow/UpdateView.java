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

public class UpdateView extends WorkFlowView implements ValidationComponentInterface
{
   public UpdateView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      
      //this.workFlowInterface = NewWorkFlowFactory.getInstance(this.getPropertiesHashMap(), this.getPageContext());
   }
   
   public Boolean isValid()
   {
      try
      {
         /*
         if(this.workFlowInterface.isValid() == Boolean.FALSE) return Boolean.FALSE;
            
         if(WorkFlowEntityFactory.getInstance().create2().get(
            this.workFlowInterface.getName(), 
            this.getWeblisketSession().getStoreName())==null)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               LogUtil.put(LogFactory.getInstance("WorkFlow does not exist.",this,commonStrings.IS_VALID));
            }
            return Boolean.FALSE;
         }         
           */       
         return Boolean.TRUE;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate",this,commonStrings.IS_VALID,e));
         }
         return Boolean.FALSE;
      }
   }
   
   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();
/*
         stringBuffer.append(this.workFlowInterface.validationInfo());

         if(WorkFlowEntityFactory.getInstance().create2().get(
            this.workFlowInterface.getName(),
            this.getWeblisketSession().getStoreName())==null)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               LogUtil.put(LogFactory.getInstance("WorkFlow does not exist.",this,"validationInfo()"));
            }
            stringBuffer.append("WorkFlow does not exist<br />");
         }
*/
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
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
