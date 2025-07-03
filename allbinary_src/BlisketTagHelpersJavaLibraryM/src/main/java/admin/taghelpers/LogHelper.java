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

import org.allbinary.business.installer.Portion;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tables.log.LogTableEntity;
import org.allbinary.data.tables.log.LogTableEntityFactory;
import org.allbinary.logic.communication.sql.AbSqlTableUtil;
 
public class LogHelper extends BasicTable
{
   //private HttpServletRequest request;

	private final Portion portion;

   public LogHelper(HashMap hashMap, PageContext pageContext)
   {
	   this.portion = new Portion(hashMap);
   }
   
   public String drop()
   {
      try
      {
         return LogTableEntityFactory.getInstance().getLogTableEntityInstance().dropTable();
      }
      catch(Exception e)
      {
         String error = "Failed to drop log table";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,commonStrings.DROP,e));
         }
         return error;  
      }
   }
   
   public String create()
   {
      try
      {
         return LogTableEntityFactory.getInstance().getLogTableEntityInstance().createTable();
      }
      catch(Exception e)
      {
         String error = "Failed to create new log table";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"create()",e));
         }
         return error;  
      }
   }
  
   public String restore()
   {
      try
      {
         final String success = "Restore Successful";
         final LogTableEntity logTableEntity = LogTableEntityFactory.getInstance().getLogTableEntityInstance();
         final String result = AbSqlTableUtil.getInstance().restoreTable(logTableEntity, this.portion);
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"restore()"));
         }
         return result;
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
         final String success = "Backup Successful";
         final String result = AbSqlTableUtil.getInstance().backupTable(LogTableEntityFactory.getInstance().getLogTableEntityInstance());
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"backup()"));
         }
         return result;
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
