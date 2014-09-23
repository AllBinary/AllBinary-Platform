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
package org.allbinary.data.tables.advertisement.areas;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.business.installer.Portion;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.advertisement.area.AdvertisementArea;
import org.allbinary.business.advertisement.area.AdvertisementAreaData;
import org.allbinary.business.advertisement.area.AdvertisementAreaInterface;
import org.allbinary.business.advertisement.campaign.AdvertisementCampaignData;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.entry.EntryData;
import org.allbinary.logic.communication.sql.AbSqlBean;

public class AdvertisementAreasEntity extends AbSqlBean implements AdvertisementAreasEntityInterface
{
   protected final String tableName = "advertisements";
   
   public AdvertisementAreasEntity()
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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Success", this, "delete"));
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed", this, "delete", e));
         }
      }
   }

   //AdvertisementAreaInterface
   public Vector get(String storeName) throws Exception
   {
      HashMap keysAndValues = new HashMap();
      keysAndValues.put(StoreFrontData.getInstance().NAME, storeName);
      Vector hashMapVector = super.getRows(keysAndValues);
      
      Vector vector = new Vector();
      Iterator iter = hashMapVector.iterator();
      while(iter.hasNext())
      {
         HashMap hashMap = (HashMap) iter.next();
         if(hashMap!=null)
            vector.add((AdvertisementAreaInterface) new AdvertisementArea(hashMap));
      }

      //(AdvertisementAreaInterface) new AdvertisementArea(vector);
      return vector;
   }

   public AdvertisementAreaInterface get(
      String storeName, String advertisementAreaName) throws Exception
   {
      HashMap keysAndValues = new HashMap();
      keysAndValues.put(StoreFrontData.getInstance().NAME, storeName);
      keysAndValues.put(AdvertisementAreaData.getInstance().NAME, advertisementAreaName);
      HashMap hashMap = super.getRow(keysAndValues);

      if(hashMap!=null) 
         return (AdvertisementAreaInterface) new AdvertisementArea(hashMap);
      else 
      return null;
   }

   public final String createTableStatement()
   {
	   AdvertisementAreaData advertisementAreaData = 
		   AdvertisementAreaData.getInstance();
	   
       StringBuffer stringBuffer = new StringBuffer();

       stringBuffer.append("CREATE TABLE ");

       stringBuffer.append(this.getTableName());
       stringBuffer.append(" (");

       //Without compound keys I use compound data in the key colum. 
       //Usually StoreName and id/name of entity
       stringBuffer.append(
    		   advertisementAreaData.NAME + " VARCHAR(255) NOT NULL," +
       StoreFrontData.getInstance().NAME + " VARCHAR(255) NOT NULL," +
       advertisementAreaData.DESCRIPTION + " VARCHAR(255) NOT NULL," +
       advertisementAreaData.CONSTRAINTS + " BLOB NOT NULL," + 
       AdvertisementCampaignData.getInstance().NAME + " VARCHAR(255) NOT NULL," +
       EntryData.getInstance().TIMECREATED + " BIGINT(19) UNSIGNED NOT NULL, " +
       EntryData.getInstance().LASTMODIFIED + " BIGINT(19) UNSIGNED NOT NULL, ");
       
       stringBuffer.append("PRIMARY KEY(");
       stringBuffer.append(advertisementAreaData.NAME);
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
      super.updateWhere(AdvertisementAreaData.getInstance().NAME, 
    		  (String) updatedValues.get(AdvertisementAreaData.getInstance().NAME),updatedValues);
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
