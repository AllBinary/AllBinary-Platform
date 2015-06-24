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
package org.allbinary.data.tables.advertisement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.business.installer.Portion;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.DynamicObjectData;
import org.allbinary.business.advertisement.AdvertisementData;
import org.allbinary.business.advertisement.AdvertisementInterface;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.entry.EntryData;
import org.allbinary.logic.communication.sql.AbSqlBean;

public class AdvertisementsEntity extends AbSqlBean implements AdvertisementsEntityInterface
{
   protected final String tableName = "advertisements";
   
   private final String _INDEX = "_index";
   
   public AdvertisementsEntity()
   {
      super(new UserDbInitInfo());
      this.setTableName(tableName);
   }
   
   /*
   public void insert(Vector values)
   {
      try
      {
         super.insert(values);
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_SUCCESS,this,INSERT);
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_FAILED,this,INSERT,e);
         }
      }
   }
   */
   
   public void delete(String value)
   {
      try
      {
         super.deleteWhere(EntryData.getInstance().ID, value);
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_SUCCESS, this, DELETE));
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_FAILED, this, DELETE, e));
         }
      }
   }

   //AdvertisementsInterface
   public Vector get(String storeName)
   {
      HashMap keysAndValues = new HashMap();
      keysAndValues.put(StoreFrontData.getInstance().NAME, storeName);
      Vector hashMapVector = super.getRows(keysAndValues);
      
      Vector vector = new Vector();
      Iterator iter = hashMapVector.iterator();
      while(iter.hasNext())
      {
         HashMap hashMap = (HashMap) iter.next();
        // if(hashMap!=null)
           // vector.add((AdvertisementInterface) new Advertisement(hashMap));
      }
      return (Vector) vector;
   }

   public AdvertisementInterface get(String storeName, String advertismentName)
   {
      HashMap keysAndValues = new HashMap();
      keysAndValues.put(StoreFrontData.getInstance().NAME, storeName);
      keysAndValues.put(AdvertisementData.getInstance().NAME, advertismentName);
      HashMap hashMap = super.getRow(keysAndValues);

     // if(hashMap!=null) 
    //     return (AdvertisementInterface) new Advertisement(hashMap);
     // else 
      return null;
   }
   
   public final String createTableStatement()
   {
       AdvertisementData advertisementData = AdvertisementData.getInstance();
	   
       final StringBuffer stringBuffer = new StringBuffer();

       stringBuffer.append(this.sqlStrings.CREATE_TABLE);

       stringBuffer.append(this.getTableName())
               .append(this.sqlStrings.START)
               .append(advertisementData.NAME)
               .append(_INDEX)
               .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
               .append(advertisementData.NAME)
               .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
               .append(StoreFrontData.getInstance().NAME)
               .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
               .append(advertisementData.DESCRIPTION)
               .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);
       
       //Holds the ad component usually uses the default
       //AdComponent that uses the AdViewComponent
       stringBuffer.append(DynamicObjectData.NAME)
               .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
               .append(EntryData.getInstance().TIMECREATED)
               .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
               .append(EntryData.getInstance().LASTMODIFIED)
               .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
               .append(this.sqlStrings.PRIMARY_KEY)
               .append(advertisementData.NAME)
               .append(this.sqlStrings.END);

       return stringBuffer.toString();
   }
   
   public String createTable()
   {
      String returnStr = super.createTable(this.createTableStatement());
      return returnStr;
   }

   public void update(HashMap updatedValues)
   {     
      //super.updateWhere(StoreFrontData.NAME,(String) updatedValues.get(StoreFrontData.NAME),updatedValues);      
      super.updateWhere(EntryData.getInstance().ID,(String) updatedValues.get(EntryData.getInstance().ID),updatedValues);
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
}
