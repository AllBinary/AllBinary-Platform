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
package org.allbinary.data.tables.user.quoterequest;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.business.installer.Portion;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.quoterequest.QuoteRequestData;
import org.allbinary.business.user.UserData;
import org.allbinary.business.user.quoterequest.QuoteRequest;
import org.allbinary.logic.communication.sql.AbSqlBean;

public class QuoteRequestEntity extends AbSqlBean 
   implements QuoteRequestEntityInterface
{   
   private final String tableName = "quoterequest";
   
   public QuoteRequestEntity()
   {
      super(new UserDbInitInfo());
      this.setTableName(tableName);
   }
   
   public void insert(Vector values)
   {
      try
      {
         super.insert(values);
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Success",this,"insert"));
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"insert",e));
         }
      }
   }
      
   public QuoteRequest get(String userName, int id) throws Exception
   {      
      HashMap row = new HashMap();
      row.put(UserData.USERNAME, userName);
      row.put(QuoteRequestData.getInstance().ID, Integer.toString(id));

      HashMap quoteRequestHashMap = super.getRow(row);

      if(quoteRequestHashMap!=null)
      {
         return new org.allbinary.business.user.quoterequest.QuoteRequest(quoteRequestHashMap);
      }
      else
      {
    	  return null;
      }
   }   
   
   public Vector getIds(String userName)
   {
	   return super.getColumnWhere(QuoteRequestData.getInstance().ID, UserData.USERNAME, userName);
   }
   
   public void deleteWhere(String key,String value)
   {
      try
      {
         super.deleteWhere(key,value);
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Success",this,"deleteWhere"));
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"deleteWhere",e));
         }
      }
   }
      
   /*
   public String getTable(String userName)
   {            
      return super.getTableWhere(UserData.USERNAME,userName);
   }
*/
   /*
   public String getTable()
   {            
      return super.getTable();
   }
   */
   
    public final String createTableStatement()
    {
    	QuoteRequestData quoteRequestData = QuoteRequestData.getInstance();
    	
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("CREATE TABLE ");

        stringBuffer.append(tableName);
        stringBuffer.append(" (");

        stringBuffer.append(quoteRequestData.ID);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL,");

        stringBuffer.append(UserData.USERNAME);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(quoteRequestData.PROJECT_INFO);
        stringBuffer.append(" BLOB NOT NULL,");

        stringBuffer.append(quoteRequestData.CUSTOMER_COMMENTS);
        stringBuffer.append(" BLOB NOT NULL,");

        stringBuffer.append(quoteRequestData.BUDGET);
        stringBuffer.append(" BLOB NOT NULL,");

        stringBuffer.append(quoteRequestData.TIMEFRAME);
        stringBuffer.append(" BLOB NOT NULL,");

        stringBuffer.append(quoteRequestData.COMMENTS);
        stringBuffer.append(" BLOB NOT NULL,");

        stringBuffer.append(EntryData.getInstance().TIMECREATED);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL, ");

        stringBuffer.append(EntryData.getInstance().LASTMODIFIED);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL, ");

        stringBuffer.append("PRIMARY KEY(");
        stringBuffer.append(quoteRequestData.ID);
        stringBuffer.append(") )");

        return stringBuffer.toString();
    }

    public String createTable()
    {
        return super.createTable(this.createTableStatement());
    }
   
   public String dropTable()
   {
      String result = new String(super.dropTable());
      return result;
   }
   
/*   
   public String getForm(String userName, String id)
   {
      return super.getInputWhere(UserData.USERNAME,userName);
   }
*/
   
   public void update(String userName,HashMap updatedValues)
   {
      super.updateWhere(UserData.USERNAME,userName,updatedValues);      
   }
   
   public String backupTable()
   {
      return super.backupTable();
   }

   public String restoreTable(Portion portion)
   {
      return super.restoreTable(portion);
   }
}
