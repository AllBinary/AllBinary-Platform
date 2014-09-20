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

import org.allbinary.logic.communication.log.LogFactory;
import java.util.*;


import javax.servlet.jsp.PageContext;

import org.allbinary.logic.communication.log.LogUtil;


import org.allbinary.logic.control.workflow.WorkFlowData;
import org.allbinary.logic.control.workflow.WorkFlowInterface;
import org.allbinary.logic.control.workflow.NewWorkFlowFactory;

import org.allbinary.data.tables.workflow.WorkFlowEntityFactory;

public class WorkFlowRequestHelper implements ModifyTableInterface
{
   private WorkFlowInterface workFlowInterface;
   
   public WorkFlowRequestHelper(HashMap hashMap, PageContext pageContext) throws Exception
   {
      this.workFlowInterface = NewWorkFlowFactory.getInstance(hashMap, pageContext);
   }
   
   public String delete() throws Exception
   {
      try
      {
         WorkFlowEntityFactory.getInstance().delete(
         this.workFlowInterface.getName(), "");
         //this.workFlowInterface.getStoreName());
         
         String success = "Successfully Removed the workflow with " +
         WorkFlowData.getInstance().NAME + "= " + this.workFlowInterface.getName() + " from ";
         //+ StoreFrontData.NAME + "= " +
         //this.workFlowInterface.getStoreName());
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"delete()"));
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
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
            {
               LogUtil.put(LogFactory.getInstance(error,this,"delete()",e));
            }
            throw new Exception(e);
         }
         catch(Exception ex)
         {
            String error = "Failed to get data from workflowinterface to set error string";
            
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
            {
               LogUtil.put(LogFactory.getInstance(error,this,"delete()",ex));
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
         //WorkFlowEntityFactory.getInstance().insert(values);

         String success
         = "New User Successfully added to the Users Table";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"add()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to add User";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"add()",e));
         }
         return error;
      }
   }
   
   public String update()
   {
      try
      {
         //HashMap values = this.workFlowInterface.toHashMap();
         
         //WorkFlowEntityFactory.getInstance().update(values);
         
         String success = "Updated WorkFlow Successfully";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"update()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to update WorkFlow";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"update()",e));
         }
         return error;
      }
   }
}
