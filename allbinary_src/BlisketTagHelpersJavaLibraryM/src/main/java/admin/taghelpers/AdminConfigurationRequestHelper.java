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

import javax.servlet.jsp.PageContext;

import javax.servlet.http.HttpServletRequest;

import org.allbinary.business.user.modules.admin.configuration.AdminConfiguration;
import org.allbinary.business.user.modules.admin.configuration.AdminConfigurationInterface;

import org.allbinary.logic.communication.log.LogUtil;
import java.util.HashMap;
import org.allbinary.string.CommonStrings;

public class AdminConfigurationRequestHelper extends ModifyTable
{
    
   private HttpServletRequest request;

   public AdminConfigurationRequestHelper(HashMap hashMap, PageContext pageContext)
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
      {
         LogUtil.put(LogFactory.getInstance(this.commonStrings.CONSTRUCTOR,this,this.commonStrings.CONSTRUCTOR));
      }
      
      this.request = (HttpServletRequest) pageContext.getRequest();
   }

   public String insert()
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.START,this,"insert()"));
         }
         
         String success = "Successfully inserted AdminConfiguration";
         
         AdminConfigurationInterface adminConfigurationInterface = 
            (AdminConfigurationInterface) new AdminConfiguration(this.request);
         
         adminConfigurationInterface.write();
            
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"insert()"));
         }
         
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to created AdminConfiguration";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "insert()", e));
         }
         return error;
      }
   }    
   
   public String delete()
   {
      try
      {
         String success = "Successfully deleted AdminConfiguration";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"delete()"));
         }
         
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to delete";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"delete()",e));
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
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"update()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to update";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"update()",e));
         }
         return error;
      }
   }
}
