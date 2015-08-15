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
package org.allbinary.data.tables.user.commerce.inventory.item.customs;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.init.db.InventoryDbInitInfo;
import org.allbinary.business.installer.Portion;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.DynamicObjectData;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.commerce.inventory.item.BasicItemData;
import org.allbinary.business.user.commerce.inventory.item.custom.CustomItemData;
import org.allbinary.logic.communication.sql.AbSqlBean;

public class CustomItemsEntity extends AbSqlBean implements CustomItemsEntityInterface
{

    protected final String tableName = "customitems";

    public CustomItemsEntity()
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
                LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_SUCCESS, this, INSERT));
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_FAILED, this, INSERT, e));
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
                LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_SUCCESS, this, DELETE));
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_FAILED, this, DELETE, e));
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

        stringBuffer.append(this.sqlStrings.CREATE_TABLE)
                .append(tableName)
                .append(this.sqlStrings.START)
                .append(BasicItemData.ID)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(DynamicObjectData.NAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(CustomItemData.PACKAGE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(EntryData.getInstance().TIMECREATED)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(EntryData.getInstance().LASTMODIFIED)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(this.sqlStrings.PRIMARY_KEY)
                .append(BasicItemData.ID)
                .append(this.sqlStrings.END);

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
     }*/
    public void update(HashMap updatedValues)
    {
        super.updateWhere(BasicItemData.ID, (String) updatedValues.get(BasicItemData.ID), updatedValues);
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
