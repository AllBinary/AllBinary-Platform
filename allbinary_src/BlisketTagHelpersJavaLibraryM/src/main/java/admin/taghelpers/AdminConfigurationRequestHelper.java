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

import abcs.logic.communication.log.LogFactory;

import javax.servlet.jsp.PageContext;

import javax.servlet.http.HttpServletRequest;

import allbinary.business.user.modules.admin.configuration.AdminConfiguration;
import allbinary.business.user.modules.admin.configuration.AdminConfigurationInterface;

import abcs.logic.communication.log.LogUtil;
import java.util.HashMap;

public class AdminConfigurationRequestHelper implements ModifyTableInterface
{
   private HttpServletRequest request;

   public AdminConfigurationRequestHelper(HashMap hashMap, PageContext pageContext)
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
      {
         LogUtil.put(LogFactory.getInstance("Constructor",this,"Constructor"));
      }
      
      this.request = (HttpServletRequest) pageContext.getRequest();
   }

   public String insert()
   {
      try
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance("Start",this,"insert()"));
         }
         
         String success = "Successfully inserted AdminConfiguration";
         
         AdminConfigurationInterface adminConfigurationInterface = 
            (AdminConfigurationInterface) new AdminConfiguration(this.request);
         
         adminConfigurationInterface.write();
            
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"insert()"));
         }
         
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to created AdminConfiguration";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "insert()", e));
         }
         return error;
      }
   }    
   
   public String delete()
   {
      try
      {
         String success = "Successfully deleted AdminConfiguration";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"delete()"));
         }
         
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to delete";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"delete()",e));
         }
         return error;
      }
   }
   
   public String update()
   {
      try
      {
         String success = "Updated AdminConfiguration Successful";

         AdminConfigurationInterface adminConfigurationInterface = 
            (AdminConfigurationInterface) new AdminConfiguration(this.request);
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"update()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to update";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"update()",e));
         }
         return error;
      }
   }
}
