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
package org.allbinary.data.tables.user.commerce.inventory.item.options;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.init.db.InventoryDbInitInfo;
import org.allbinary.business.installer.Portion;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.commerce.inventory.item.BasicItemData;
import org.allbinary.business.user.commerce.inventory.item.option.BasicOptionItemData;
import org.allbinary.logic.communication.sql.AbSqlBean;

public class BasicOptionItemsEntity extends AbSqlBean implements BasicOptionItemsEntityInterface
{   
   protected final String tableName = "basicoptionitems";   
   
   public BasicOptionItemsEntity()
   {
      super(new InventoryDbInitInfo());
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
         super.deleteWhere(BasicItemData.ID, value);
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

   /*
   public String getTable(String itemId)
   {
      return super.getTableWhere(BasicItemData.ID,itemId);
   }
*/
   
    public final String createTableStatement()
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("CREATE TABLE ");

        stringBuffer.append(tableName);
        stringBuffer.append(" (");

        stringBuffer.append(BasicItemData.ID + " BIGINT(19) UNSIGNED NOT NULL," +
   
   //Option title like color or size
   BasicOptionItemData.OPTION_ONE_TITLE + " VARCHAR(255) NOT NULL," +
   
   BasicOptionItemData.DEFAULT_OPTION_ITEM + " BIGINT(19) UNSIGNED NOT NULL," +  
   BasicOptionItemData.DEFAULT_OPTION_VALUE + " VARCHAR(255) NOT NULL," +
   
   //Available options
   BasicOptionItemData.OPTION_ONE_ONE_ITEM + " BIGINT(19) UNSIGNED NOT NULL," +  
   BasicOptionItemData.OPTION_ONE_ONE_VALUE + " VARCHAR(255) NOT NULL," +
   BasicOptionItemData.OPTION_ONE_TWO_ITEM + " BIGINT(19) UNSIGNED NOT NULL," +  
   BasicOptionItemData.OPTION_ONE_TWO_VALUE + " VARCHAR(255) NOT NULL," +
   BasicOptionItemData.OPTION_ONE_THREE_ITEM + " BIGINT(19) UNSIGNED NOT NULL," +  
   BasicOptionItemData.OPTION_ONE_THREE_VALUE + " VARCHAR(255) NOT NULL," +
   BasicOptionItemData.OPTION_ONE_FOUR_ITEM + " BIGINT(19) UNSIGNED NOT NULL," +  
   BasicOptionItemData.OPTION_ONE_FOUR_VALUE + " VARCHAR(255) NOT NULL," +
   BasicOptionItemData.OPTION_ONE_FIVE_ITEM + " BIGINT(19) UNSIGNED NOT NULL," +  
   BasicOptionItemData.OPTION_ONE_FIVE_VALUE + " VARCHAR(255) NOT NULL," +
   BasicOptionItemData.OPTION_ONE_SIX_ITEM + " BIGINT(19) UNSIGNED NOT NULL," +  
   BasicOptionItemData.OPTION_ONE_SIX_VALUE + " VARCHAR(255) NOT NULL," +
   BasicOptionItemData.OPTION_ONE_SEVEN_ITEM + " BIGINT(19) UNSIGNED NOT NULL," +  
   BasicOptionItemData.OPTION_ONE_SEVEN_VALUE + " VARCHAR(255) NOT NULL," +
   BasicOptionItemData.OPTION_ONE_EIGHT_ITEM + " BIGINT(19) UNSIGNED NOT NULL," +  
   BasicOptionItemData.OPTION_ONE_EIGHT_VALUE + " VARCHAR(255) NOT NULL," +
   BasicOptionItemData.OPTION_ONE_NINE_ITEM + " BIGINT(19) UNSIGNED NOT NULL," +  
   BasicOptionItemData.OPTION_ONE_NINE_VALUE + " VARCHAR(255) NOT NULL," +
   
   EntryData.getInstance().TIMECREATED + " BIGINT(19) UNSIGNED NOT NULL, " +
   EntryData.getInstance().LASTMODIFIED + " BIGINT(19) UNSIGNED NOT NULL, ");

        stringBuffer.append("PRIMARY KEY(");
        stringBuffer.append(BasicItemData.ID);
        stringBuffer.append(") )");

        return stringBuffer.toString();
    }

    public String createTable()
    {
        return super.createTable(this.createTableStatement());
    }

   /*
   public String getForm(String id)
   {
      return super.getInputWhere(BasicItemData.ID,id);
   }
*/
   
   public void update(HashMap updatedValues)
   {
      super.updateWhere(BasicItemData.ID,(String) updatedValues.get(BasicItemData.ID),updatedValues);
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
    */  
}
