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
package allbinary.data.tables.advertisement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import abcs.business.init.db.UserDbInitInfo;
import abcs.business.installer.Portion;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.DynamicObjectData;
import allbinary.business.advertisement.AdvertisementData;
import allbinary.business.advertisement.AdvertisementInterface;
import allbinary.business.context.modules.storefront.StoreFrontData;
import allbinary.business.entry.EntryData;
import allbinary.logic.communication.sql.AbSqlBean;

public class AdvertisementsEntity extends AbSqlBean implements AdvertisementsEntityInterface
{
   protected final String tableName = "advertisements";
   
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
            LogUtil.put("Command Success",this,"insert");
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put("Command Failed",this,"insert",e);
         }
      }
   }
   */
   
   public void delete(String value)
   {
      try
      {
         super.deleteWhere(EntryData.getInstance().ID, value);
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Success", this, "delete"));
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed", this, "delete", e));
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
	   AdvertisementData advertisementData = 
	   AdvertisementData.getInstance();
	   
       StringBuffer stringBuffer = new StringBuffer();

       stringBuffer.append("CREATE TABLE ");

       stringBuffer.append(this.getTableName());
       stringBuffer.append(" (");

       stringBuffer.append(
    		   advertisementData.NAME + "_index" + " BIGINT(19) UNSIGNED NOT NULL," +
    		      advertisementData.NAME + " VARCHAR(255) NOT NULL," +
    		      StoreFrontData.getInstance().NAME + " VARCHAR(255) NOT NULL," + 
    		      advertisementData.DESCRIPTION + " VARCHAR(255) NOT NULL," +
    		      //Holds the ad component usually uses the default 
    		      //AdComponent that uses the AdViewComponent
    		      DynamicObjectData.NAME + " VARCHAR(255) NOT NULL," +
    		      EntryData.getInstance().TIMECREATED + " BIGINT(19) UNSIGNED NOT NULL, " +
    		      EntryData.getInstance().LASTMODIFIED + " BIGINT(19) UNSIGNED NOT NULL, "
    		      );
       
       stringBuffer.append("PRIMARY KEY(");
       stringBuffer.append(advertisementData.NAME);
       stringBuffer.append(") )");

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
