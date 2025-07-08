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

import org.allbinary.logic.communication.log.LogUtil;

//import org.allbinary.logic.system.security.crypt.*;

//import org.allbinary.business.user.commerce.inventory.order.*;
//import org.allbinary.business.user.commerce.money.payment.*;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.control.validate.ValidationComponentInterface;

import org.allbinary.logic.control.workflow.NewWorkFlowFactory;

import org.allbinary.data.tables.workflow.WorkFlowEntityFactory;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.ServiceClientInformationInterfaceFactory;


public class AddValidationView extends WorkFlowView implements ValidationComponentInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final AbeClientInformationInterface abeClientInformation = 
        ServiceClientInformationInterfaceFactory.getInstance();
    
   public AddValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
            
      this.workFlowInterface = NewWorkFlowFactory.getInstance().getInstance(abeClientInformation, this.getPropertiesHashMap(), this.getPageContext());
   }
   
   public Boolean isValid()
   {
      try
      {
         /*
         if(WorkFlowEntityFactory.getInstance().create2().get(
            this.workFlowInterface.getName(), 
            this.getTransformInfoInterface().getStoreName())!=null)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("WorkFlow already in existance.",this,commonStrings.IS_VALID);
            }
            return Boolean.FALSE;
         }
         
         if(this.workFlowInterface.isValid() == Boolean.FALSE) return Boolean.FALSE;
          */         
         return Boolean.TRUE;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put("Failed to validate",this,commonStrings.IS_VALID,e);
         }
         return Boolean.FALSE;
      }
   }
   
   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();

         if(WorkFlowEntityFactory.getInstance().create2().get(this.workFlowInterface.getName(), this.getTransformInfoInterface().getStoreName())!=null)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("Workflow already exist",this,"validationInfo()");
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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put("Failed to generate validation error info",this,"validationInfo()",e);
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
