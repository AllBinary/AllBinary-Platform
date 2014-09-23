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
package org.allbinary.data.tables.context.module.storefronts;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.business.installer.Portion;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFront;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.entry.EntryData;
import org.allbinary.logic.communication.sql.AbSqlBean;

public class StoreFrontsEntity extends AbSqlBean implements StoreFrontsEntityInterface
{
   protected final String tableName = "storefronts";
   
   public StoreFrontsEntity()
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
   
   public void delete(String value)
   {
      try
      {
         super.deleteWhere(StoreFrontData.getInstance().NAME,value);
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Success",this,"delete"));
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"delete",e));
         }
      }
   }
   
   public StoreFrontInterface getStoreFrontInterface(String name) throws Exception
   {
      HashMap keysAndValues = new HashMap();
      keysAndValues.put(StoreFrontData.getInstance().NAME, name);
      HashMap storeHashMap = super.getRow(keysAndValues);
      if(storeHashMap!=null)
      {
         return (StoreFrontInterface) new StoreFront(storeHashMap);
      }
      else
      {
          return null;
      }
   }
      
   public Vector getStoreFrontNames()
   {
      Vector storeFrontNames = super.getColumn(
    		  StoreFrontData.getInstance().NAME);
      return storeFrontNames;
   }

   public void update(HashMap updatedValues)
   {     
      super.updateWhere(StoreFrontData.getInstance().NAME,
    		  (String) updatedValues.get(StoreFrontData.getInstance().NAME),
    		  updatedValues);      
   }

    public final String createTableStatement()
    {
    	StoreFrontData storeFrontData = StoreFrontData.getInstance();
    	
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("CREATE TABLE ");
        stringBuffer.append(tableName);
        stringBuffer.append(" (");

        stringBuffer.append(storeFrontData.NAME);
        stringBuffer.append(" VARCHAR(60) NOT NULL,");

      //UserData.USERNAME + " VARCHAR(255) NOT NULL," +
        stringBuffer.append(storeFrontData.HOMEHOSTNAME);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(storeFrontData.HOMEHOSTNAMEPATH);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(storeFrontData.HOSTNAME);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(storeFrontData.HOSTNAMEPATH);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(storeFrontData.TESTHOMEHOSTNAME);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(storeFrontData.TESTHOMEHOSTNAMEPATH);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(storeFrontData.TESTHOSTNAME);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");
      
        stringBuffer.append(storeFrontData.TESTHOSTNAMEPATH);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(storeFrontData.IMAGEPATH);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");
      
        stringBuffer.append(storeFrontData.STATICPATH);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(storeFrontData.CATEGORYPATH);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(storeFrontData.INVENTORYCONTROL);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(storeFrontData.CONFIGURATION);
        stringBuffer.append(" BLOB NOT NULL,");

        stringBuffer.append(storeFrontData.SUBSTORES);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(storeFrontData.TAGLOCATION);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(storeFrontData.PACKAGELOCATION);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(storeFrontData.FTP);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");
      
        stringBuffer.append(storeFrontData.FTPPATH);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(storeFrontData.FTPUSERNAME);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(storeFrontData.FTPPASSWORD);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(storeFrontData.TESTFTP);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(storeFrontData.TESTFTPPATH);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(storeFrontData.TESTFTPUSERNAME);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");
      
        stringBuffer.append(storeFrontData.TESTFTPPASSWORD);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(EntryData.getInstance().TIMECREATED);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL, ");

        stringBuffer.append(EntryData.getInstance().getInstance().LASTMODIFIED);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL, ");

        stringBuffer.append("PRIMARY KEY(");
        stringBuffer.append(storeFrontData.NAME);
        stringBuffer.append(") )");

        return stringBuffer.toString();
    }

    public String createTable()
    {
        return super.createTable(this.createTableStatement());
    }
   
   public String backupTable()
   {
      return super.backupTable();
   }
   
   public String restoreTable(Portion portion)
   {
      return super.restoreTable(portion);
   }

   public String dropTable()
   {
      return super.dropTable();
   }
   
   /*
   public String getTable()
   {
      return super.getTable();
   } 
    **/  
   /*
   public String getTable(String name)
   {
      return super.getTableWhere(storeFrontData.NAME, name);
   }
   */
   
   /*
   public String getStoreForm(String name)
   {
      return super.getInputWhere(storeFrontData.NAME, name);
   }
     */
}
