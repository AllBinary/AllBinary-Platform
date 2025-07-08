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
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFront;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
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
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.SUCCESS, this, INSERT));
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, INSERT, e));
         }
      }
   }
   
   public void delete(String value)
   {
      try
      {
         super.deleteWhere(StoreFrontData.getInstance().NAME,value);
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.SUCCESS, this, commonStrings.delete));
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, commonStrings.delete, e));
         }
      }
   }
   
   public StoreFront getStoreFrontInterface(String name) throws Exception
   {
      HashMap keysAndValues = new HashMap();
      keysAndValues.put(StoreFrontData.getInstance().NAME, name);
      HashMap storeHashMap = super.getRow(keysAndValues);
      if(storeHashMap != null)
      {
          return new StoreFront(storeHashMap);
      }
      else
      {
          return new StoreFront();
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

        stringBuffer.append(this.sqlStrings.CREATE_TABLE);
        stringBuffer.append(tableName);
        stringBuffer.append(this.sqlStrings.START);

        stringBuffer.append(storeFrontData.NAME);
        stringBuffer.append(this.sqlTypeStrings.SIXTY_CHAR_COLUMN_NOT_NULL);

      //UserData.USERNAME + this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL +
        stringBuffer.append(storeFrontData.HOMEHOSTNAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(storeFrontData.HOMEHOSTNAMEPATH);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(storeFrontData.HOSTNAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(storeFrontData.HOSTNAMEPATH);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(storeFrontData.TESTHOMEHOSTNAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(storeFrontData.TESTHOMEHOSTNAMEPATH);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(storeFrontData.TESTHOSTNAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);
      
        stringBuffer.append(storeFrontData.TESTHOSTNAMEPATH);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(storeFrontData.IMAGEPATH);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);
      
        stringBuffer.append(storeFrontData.STATICPATH);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(storeFrontData.CATEGORYPATH);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(storeFrontData.INVENTORYCONTROL);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(storeFrontData.CONFIGURATION);
        stringBuffer.append(this.sqlTypeStrings.BLOB_NOT_NULL);

        stringBuffer.append(storeFrontData.SUBSTORES);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(storeFrontData.TAGLOCATION);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(storeFrontData.PACKAGELOCATION);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(storeFrontData.FTP);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);
      
        stringBuffer.append(storeFrontData.FTPPATH);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(storeFrontData.FTPUSERNAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(storeFrontData.FTPPASSWORD);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(storeFrontData.TESTFTP);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(storeFrontData.TESTFTPPATH);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(storeFrontData.TESTFTPUSERNAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);
      
        stringBuffer.append(storeFrontData.TESTFTPPASSWORD);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(EntryData.getInstance().TIMECREATED);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(EntryData.getInstance().getInstance().LASTMODIFIED);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(this.sqlStrings.PRIMARY_KEY);
        stringBuffer.append(storeFrontData.NAME);
        stringBuffer.append(this.sqlStrings.END);

        return stringBuffer.toString();
    }

    public String createTable()
    {
        return super.createTable(this.createTableStatement());
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
