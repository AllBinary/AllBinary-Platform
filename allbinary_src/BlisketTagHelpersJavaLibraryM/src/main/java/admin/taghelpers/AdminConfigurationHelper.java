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
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;

public class AdminConfigurationHelper extends BasicTable 
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    
   public AdminConfigurationHelper(HashMap hashMap, PageContext pageContext)
   {
   }
   
   public String create()
   {
      try
      {
         String success = "No Imp";
                  
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            this.logUtil.putF(success,this,"create()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to create table";
         
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            this.logUtil.put(this.commonStrings.EXCEPTION,this,"create()",e);
         }
         return error;
      }
   }
   
   public String  drop()
   {
      try
      {
         String success = "No Imp";
         
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            this.logUtil.putF(success,this,this.commonStrings.DROP);
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to drop table";
         
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            this.logUtil.put(this.commonStrings.EXCEPTION,this,this.commonStrings.DROP,e);
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
         
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            this.logUtil.put(this.commonStrings.EXCEPTION,this,"restore()",e);
         }
         return error;
      }
   }
   
   public String backup()
   {
      try
      {
         String success = "Backup Successful";
         
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            this.logUtil.putF(success,this,"backup()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to make backup";
         
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            this.logUtil.put(this.commonStrings.EXCEPTION,this,"backup()",e);
         }
         return error;
      }
   }   
}
