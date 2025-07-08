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
import org.allbinary.data.tables.user.commerce.inventory.item.downloads.DownloadItemsEntityFactory;
import org.allbinary.logic.communication.sql.AbSqlTableUtil;

public class DownloadItemsHelper extends BasicTable
{
    protected final LogUtil logUtil = LogUtil.getInstance();

     
	private final Portion portion;

   public DownloadItemsHelper(HashMap hashMap, PageContext pageContext)
   {
	   this.portion = new Portion(hashMap);
   }
   
   public String create()
   {
      try
      {
         String success = DownloadItemsEntityFactory.getInstance().getDownloadItemsEntityInstance().createTable();
                  
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"create()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to create table";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"create()",e);
         }
         return error;
      }
   }
   
   public String  drop()
   {
      try
      {
         String success = DownloadItemsEntityFactory.getInstance().getDownloadItemsEntityInstance().dropTable();
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,commonStrings.DROP);
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to drop pricing tables";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,commonStrings.DROP,e);
         }
         return error;
      }
   }
      
   public String restore()
   {
      try
      {
         final String success = "Restore Successful";
         final String result = AbSqlTableUtil.getInstance().restoreTable(DownloadItemsEntityFactory.getInstance().getDownloadItemsEntityInstance(), this.portion);
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"restore()");
         }
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to restore backup";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"restore()",e);
         }
         return error;
      }
   }
   
   public String backup()
   {
      try
      {
         String success = "Backup Successful";
         final String result = AbSqlTableUtil.getInstance().backupTable(DownloadItemsEntityFactory.getInstance().getDownloadItemsEntityInstance());
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"backup()");
         }
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to make backup";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"backup()",e);
         }
         return error;
      }
   }
      
}
