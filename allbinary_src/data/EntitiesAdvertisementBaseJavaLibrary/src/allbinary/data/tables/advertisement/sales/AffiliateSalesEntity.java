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
package allbinary.data.tables.advertisement.sales;

import java.util.HashMap;

import abcs.business.init.db.UserDbInitInfo;
import abcs.business.installer.Portion;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.DynamicObjectData;
import allbinary.business.context.modules.storefront.StoreFrontData;
import allbinary.business.entry.EntryData;
import allbinary.business.user.group.GroupData;
import allbinary.logic.communication.sql.AbSqlBean;

public class AffiliateSalesEntity extends AbSqlBean implements AffiliateSalesEntityInterface
{
   protected final String tableName = "affiliate";
   
   public AffiliateSalesEntity()
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

   /*
   public AffiliatesInterface get(String name)
   {
      HashMap keysAndValues = new HashMap();
      keysAndValues.put(StoreFrontData.NAME, name);
      Vector hashMapVector = super.getRows(keysAndValues);
      
      Vector affiliateVector = new Vector();
      Iterator iter = hashMapVector.iterator();
      while(iter.hasNext())
      {
         HashMap hashMap = (HashMap) iter.next();
         if(hashMap!=null)
            affiliateVector.add((AffiliateInterface) new Affiliate(hashMap));
      }

      return (AffiliatesInterface) new Affiliates(affiliateVector);
   }

   public AffiliateInterface get(String name, String affiliateName)
   {
      HashMap keysAndValues = new HashMap();
      keysAndValues.put(StoreFrontData.NAME, name);
      keysAndValues.put(AffiliateData.NAME, name);
      HashMap hashMap = super.getRow(keysAndValues);
      
      if(hashMap!=null) 
         return (AffiliateInterface) new Affiliate(hashMap);
      else 
      return null;
   }
*/
   
   public final String createTableStatement()
   {
	   EntryData entryData = EntryData.getInstance();
	   	   
       StringBuffer stringBuffer = new StringBuffer();

       stringBuffer.append("CREATE TABLE ");

       stringBuffer.append(this.getTableName());
       stringBuffer.append(" (");

       //Without compound keys I use compound data in the key colum. 
       //Usually StoreName and id/name of entity
       stringBuffer.append(
    		      entryData.ID + " BIGINT(19) UNSIGNED NOT NULL," +
    		      StoreFrontData.getInstance().NAME + " VARCHAR(255) NOT NULL," +
    		      //AffiliateData.NAME + " VARCHAR(255) NOT NULL," +
    		      //AffiliateData.DESCRIPTION + " VARCHAR(255) NOT NULL," +
    		      GroupData.NAME + " VARCHAR(255) NOT NULL," +
    		      DynamicObjectData.NAME + " VARCHAR(255) NOT NULL," +
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

   public void update(HashMap updatedValues)
   {
      super.updateWhere(EntryData.getInstance().getInstance().ID,
    		  (String) updatedValues.get(EntryData.getInstance().getInstance().ID),
    		  updatedValues);
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
