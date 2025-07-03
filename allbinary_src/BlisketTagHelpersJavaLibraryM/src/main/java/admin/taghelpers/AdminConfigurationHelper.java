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

import org.allbinary.logic.communication.log.LogUtil;
import java.util.HashMap;

public class AdminConfigurationHelper extends BasicTable 
{
    
   public AdminConfigurationHelper(HashMap hashMap, PageContext pageContext)
   {
   }
   
   public String create()
   {
      try
      {
         String success = "No Imp";
                  
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"create()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to create table";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"create()",e));
         }
         return error;
      }
   }
   
   public String  drop()
   {
      try
      {
         String success = "No Imp";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,commonStrings.DROP));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to drop table";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,commonStrings.DROP,e));
         }
         return error;
      }
   }
        
   public String restore()
   {
      try
      {
         String success = "No Imp";
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to restore backup";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"restore()",e));
         }
         return error;
      }
   }
   
   public String backup()
   {
      try
      {
         String success = "Backup Successful";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"backup()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to make backup";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"backup()",e));
         }
         return error;
      }
   }   
}
