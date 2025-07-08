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

import org.allbinary.data.tables.workflow.WorkFlowEntityFactory;



import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.control.validate.ValidationComponentInterface;

import org.allbinary.logic.control.workflow.WorkFlowData;

import org.allbinary.logic.communication.log.LogUtil;

public class DeleteValidationView extends WorkFlowView implements ValidationComponentInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private String workFlowName;
   
   public DeleteValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      
      this.workFlowName = this.getPageContext().getRequest().getParameter(WorkFlowData.getInstance().NAME);
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         logUtil.put("Name: " + this.workFlowName,this,this.commonStrings.CONSTRUCTOR);
      }
   }

   public Boolean isValid()
   {
      try
      {
         if(this.workFlowName==null)
         {
            return Boolean.FALSE;
         }
         
         if(WorkFlowEntityFactory.getInstance().create2().get(
            this.workFlowName, 
            this.getWeblisketSession().getStoreName())==null)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("WorkFlow does not exist.",this,commonStrings.IS_VALID);
            }
            return Boolean.FALSE;
         }
                  
         return Boolean.TRUE;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put("Failed to validate form",this,commonStrings.IS_VALID,e);
         }
         return Boolean.FALSE;
      }
   }
   
   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();
         
         if(this.workFlowName == null)
         {
            stringBuffer.append("WorkFlow name is invalid<br />");
         }
         
         if(WorkFlowEntityFactory.getInstance().create2().get(this.workFlowName, this.getWeblisketSession().getStoreName()) == null)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               logUtil.put("WorkFlow does not exist.",this,"validationInfo()");
            }
            stringBuffer.append("WorkFlow does not exist<br />");
         }
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put("Failed to generate validation error info",this,"validationInfo()",e);
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
