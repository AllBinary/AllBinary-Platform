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
package admin.taghelpers;

import java.util.HashMap;
import javax.servlet.jsp.PageContext;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.control.workflow.WorkFlowData;
import org.allbinary.logic.control.workflow.WorkFlowInterface;
import org.allbinary.logic.control.workflow.NewWorkFlowFactory;
import org.allbinary.data.tables.workflow.WorkFlowEntityFactory;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.ServiceClientInformationInterfaceFactory;

public class WorkFlowRequestHelper extends ModifyTable
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final AbeClientInformationInterface abeClientInformation = 
        ServiceClientInformationInterfaceFactory.getInstance();
    
   private WorkFlowInterface workFlowInterface;
   
   public WorkFlowRequestHelper(HashMap hashMap, PageContext pageContext) throws Exception
   {
      this.workFlowInterface = NewWorkFlowFactory.getInstance().getInstance(this.abeClientInformation, hashMap, pageContext);
   }
   
   public String delete() throws Exception
   {
      try
      {
         final StringUtil stringUtil = StringUtil.getInstance();
         WorkFlowEntityFactory.getInstance().create2().delete(
         this.workFlowInterface.getName(), stringUtil.EMPTY_STRING);
         //this.workFlowInterface.getStoreName());
         
         String success = "Successfully Removed the workflow with " +
         WorkFlowData.getInstance().NAME + "= " + this.workFlowInterface.getName() + " from ";
         //+ StoreFrontData.NAME + "= " +
         //this.workFlowInterface.getStoreName());
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"delete()");
         }
         return success;
      }
      catch(Exception e)
      {
         try
         {
            String error = "Failed to remove workflow with " +
            WorkFlowData.getInstance().NAME + "= " + this.workFlowInterface.getName() +
            " from ";
            //+ StoreFrontData.NAME + "= " +
            //this.workFlowInterface.getStoreName();
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
               logUtil.put(commonStrings.EXCEPTION,this,"delete()",e);
            }
            throw new Exception(e);
         }
         catch(Exception ex)
         {
            String error = "Failed to get data from workflowinterface to set error string";
            
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
            {
               logUtil.put(commonStrings.EXCEPTION,this,"delete()",ex);
            }
            return error;
         }         
      }
   }
   
   public String insert()
   {
      try
      {
         //Vector values = this.workFlowInterface.toVector();
         //WorkFlowEntityFactory.getInstance().create2().insert(values);

         String success
         = "New User Successfully added to the Users Table";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"add()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to add User";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"add()",e);
         }
         return error;
      }
   }
   
   public String update()
   {
      try
      {
         //HashMap values = this.workFlowInterface.toHashMap();
         
         //WorkFlowEntityFactory.getInstance().create2().update(values);
         
         String success = "Updated WorkFlow Successfully";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"update()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to update WorkFlow";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"update()",e);
         }
         return error;
      }
   }
}
