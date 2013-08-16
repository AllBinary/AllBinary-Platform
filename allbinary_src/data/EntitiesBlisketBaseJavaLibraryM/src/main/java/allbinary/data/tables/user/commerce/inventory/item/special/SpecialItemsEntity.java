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
package allbinary.data.tables.user.commerce.inventory.item.special;

import java.util.HashMap;
import java.util.Vector;

import abcs.business.init.db.InventoryDbInitInfo;
import abcs.business.installer.Portion;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.entry.EntryData;
import allbinary.business.user.commerce.inventory.item.BasicItemData;
import allbinary.business.user.commerce.inventory.item.special.SpecialItemData;
import allbinary.logic.communication.sql.AbSqlBean;

public class SpecialItemsEntity extends AbSqlBean implements SpecialItemsEntityInterface
{   
   protected final String tableName = "specialitems";

   public SpecialItemsEntity()
   {
      super(new InventoryDbInitInfo());
      this.setTableName(tableName);
   }
   
   public void insert(Vector values)
   {
      try
      {
         super.insert(values);
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Success",this,"insert"));
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
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
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Success",this,"delete"));
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
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
   BasicItemData.NUMBER + " BIGINT(19) UNSIGNED NOT NULL," + 

   EntryData.getInstance().ENABLE + " VARCHAR(255) NOT NULL," + 

   SpecialItemData.START_TIME + " BIGINT(19) UNSIGNED NOT NULL, " +
   SpecialItemData.END_TIME + " BIGINT(19) UNSIGNED NOT NULL, " +      
   BasicItemData.PRICE + " DECIMAL (11,2) NOT NULL," +
   
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
      super.updateWhere(BasicItemData.ID,
         (String) updatedValues.get(BasicItemData.ID),updatedValues);
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
   } */  
}
