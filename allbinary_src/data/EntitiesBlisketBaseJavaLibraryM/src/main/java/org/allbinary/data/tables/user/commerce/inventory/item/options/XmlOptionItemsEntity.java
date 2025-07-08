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
import org.allbinary.business.user.commerce.inventory.item.option.XmlOptionItemData;
import org.allbinary.logic.communication.sql.AbSqlBean;

public class XmlOptionItemsEntity extends AbSqlBean implements XmlOptionItemsEntityInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    protected final String tableName = "xmloptionitems";

    public XmlOptionItemsEntity()
    {
        super(new InventoryDbInitInfo());
        this.setTableName(tableName);
    }

    public void insert(Vector values)
    {
        try
        {
            super.insert(values);

            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.SUCCESS, this, INSERT);
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.FAILURE, this, INSERT, e);
            }
        }
    }

    public void delete(String value)
    {
        try
        {
            super.deleteWhere(BasicItemData.ID, value);
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.SUCCESS, this, commonStrings.delete);
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                logUtil.put(this.commonStrings.FAILURE, this, commonStrings.delete, e);
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
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        //Option title like color or size
        stringBuffer.append(XmlOptionItemData.XML_FILE_STATUS)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(XmlOptionItemData.OPTION_XML_FILE)
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
     }
     */
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
     } */
}
