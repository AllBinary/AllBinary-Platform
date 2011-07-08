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

import abcs.logic.communication.log.LogFactory;

import org.w3c.dom.Node;
import org.w3c.dom.Document;

import abcs.logic.communication.log.LogUtil;

//import abcs.logic.system.security.crypt.*;

//import allbinary.business.user.commerce.inventory.order.*;
//import allbinary.business.user.commerce.money.payment.*;

import allbinary.logic.visual.transform.info.TransformInfoInterface;

import allbinary.logic.control.validate.ValidationComponentInterface;

import allbinary.logic.control.workflow.NewWorkFlowFactory;

import allbinary.data.tables.workflow.WorkFlowEntityFactory;


public class AddValidationView extends WorkFlowView implements ValidationComponentInterface
{
   public AddValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
            
      this.workFlowInterface = NewWorkFlowFactory.getInstance(this.getPropertiesHashMap(), this.getPageContext());
   }
   
   public Boolean isValid()
   {
      try
      {
         /*
         if(WorkFlowEntityFactory.getInstance().get(
            this.workFlowInterface.getName(), 
            this.getTransformInfoInterface().getStoreName())!=null)
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
               LogUtil.put(LogFactory.getInstance("WorkFlow already in existance.",this,"isValid()");
            }
            return Boolean.FALSE;
         }
         
         if(this.workFlowInterface.isValid() == Boolean.FALSE) return Boolean.FALSE;
          */         
         return Boolean.TRUE;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
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

         if(WorkFlowEntityFactory.getInstance().get(
            this.workFlowInterface.getName(), 
            this.getTransformInfoInterface().getStoreName())!=null)
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
               LogUtil.put(LogFactory.getInstance("Workflow already exist",this,"validationInfo()"));
            }
            stringBuffer.append("The WorkFlow name you selected is already in use.<br/>  Please select another Name.<br />");            
         }

         /*
         if(this.workFlowInterface.isValid() == Boolean.FALSE)
         {
            stringBuffer.append(this.workFlowInterface.validationInfo());
         }
         */
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
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
