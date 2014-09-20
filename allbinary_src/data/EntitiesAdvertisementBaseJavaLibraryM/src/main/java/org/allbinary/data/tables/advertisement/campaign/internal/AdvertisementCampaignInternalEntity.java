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
package org.allbinary.data.tables.advertisement.campaign.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.allbinary.business.init.db.UserDbInitInfo;
import org.allbinary.business.installer.Portion;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.advertisement.campaign.AdvertisementCampaign;
import org.allbinary.business.advertisement.campaign.AdvertisementCampaignData;
import org.allbinary.business.advertisement.campaign.AdvertisementCampaignInterface;
import org.allbinary.business.advertisement.campaign.AdvertisementCampaigns;
import org.allbinary.business.advertisement.campaign.AdvertisementCampaignsInterface;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.EntryData;
import org.TimeData;
import org.allbinary.logic.communication.sql.AbSqlBean;

public class AdvertisementCampaignInternalEntity extends AbSqlBean 
   implements AdvertisementCampaignInternalEntityInterface
{
   protected final String tableName = "adCampaignInternal";
   
   public AdvertisementCampaignInternalEntity()
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
         super.deleteWhere(StoreFrontData.getInstance().NAME, value);

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

   public AdvertisementCampaignsInterface getCampaignsInStore(String storeName)
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
            vector.add((AdvertisementCampaignInterface) new AdvertisementCampaign(hashMap));
      }

      return (AdvertisementCampaignsInterface) new AdvertisementCampaigns(vector);
   }

   public AdvertisementCampaignInterface get(String storeName, String name)
   {
      HashMap keysAndValues = new HashMap();
      keysAndValues.put(StoreFrontData.getInstance().NAME, storeName);
      keysAndValues.put(AdvertisementCampaignData.getInstance().NAME, name);
      HashMap hashMap = super.getRow(keysAndValues);
      
      if(hashMap!=null) 
         return (AdvertisementCampaignInterface) new AdvertisementCampaign(hashMap);
      else 
      return null;
   }
   
   public void update(HashMap updatedValues)
   {
      super.updateWhere(EntryData.getInstance().getInstance().ID,
    		  (String) updatedValues.get(EntryData.getInstance().getInstance().ID),
    		  updatedValues);
   }
   
   public final String createTableStatement()
   {
	   EntryData entryData = EntryData.getInstance().getInstance();
	   
	   AdvertisementCampaignData advertisementCampaignData =
		   AdvertisementCampaignData.getInstance();
	   
       StringBuffer stringBuffer = new StringBuffer();

       stringBuffer.append("CREATE TABLE ");

       stringBuffer.append(this.getTableName());
       stringBuffer.append(" (");

       //Without compound keys I use compound data in the key colum. 
       //Usually StoreName and id/name of entity
       stringBuffer.append(
    		      entryData.ID + " BIGINT(19) UNSIGNED NOT NULL," +
    		      StoreFrontData.getInstance().NAME + " VARCHAR(255) NOT NULL," +
    		      advertisementCampaignData.NAME + " VARCHAR(255) NOT NULL," +
    		      advertisementCampaignData.DESCRIPTION + " VARCHAR(255) NOT NULL," +
    		      advertisementCampaignData.CONFIG + " BLOB NOT NULL," +
    		      TimeData.getInstance().START + " BIGINT(19) UNSIGNED NOT NULL, " +
    		      TimeData.getInstance().END + " BIGINT(19) UNSIGNED NOT NULL, " +
    		      entryData.TIMECREATED + " BIGINT(19) UNSIGNED NOT NULL, " +
    		      entryData.LASTMODIFIED + " BIGINT(19) UNSIGNED NOT NULL, "
    		      );
       
       stringBuffer.append("PRIMARY KEY(");
       stringBuffer.append(entryData.ID);
       stringBuffer.append(") )");

       return stringBuffer.toString();
   }
   
   public String createTable()
   {
      String returnStr = super.createTable(this.createTableStatement());
      return returnStr;
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
