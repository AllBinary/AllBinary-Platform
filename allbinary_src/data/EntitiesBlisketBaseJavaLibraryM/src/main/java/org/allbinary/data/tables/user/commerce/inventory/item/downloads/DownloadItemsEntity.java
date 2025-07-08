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
package org.allbinary.data.tables.user.commerce.inventory.item.downloads;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.business.init.db.InventoryDbInitInfo;
import org.allbinary.business.installer.Portion;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.commerce.inventory.item.BasicItemData;
import org.allbinary.business.user.commerce.inventory.item.download.DownloadItemData;
import org.allbinary.business.user.commerce.inventory.item.download.DownloadableItem;
import org.allbinary.logic.communication.sql.AbSqlBean;

public class DownloadItemsEntity extends AbSqlBean implements DownloadItemsEntityInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    protected final String tableName = "downloaditems";

    public DownloadItemsEntity()
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
            super.deleteWhere(DownloadItemData.ID, value);
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

    public void update(HashMap updatedValues)
    {
        super.updateWhere(DownloadItemData.ID, (String) updatedValues.get(DownloadItemData.ID), updatedValues);
    }

    public Vector getForItem(String id)
    {
        Vector returnVector = new Vector();

        HashMap keysAndValues = new HashMap();
        keysAndValues.put(BasicItemData.ID, id);
        Vector vector = super.getRows(keysAndValues);

        final int size = vector.size();
        for(int index = 0; index < size; index++)
        {
            HashMap hashMap = (HashMap) vector.get(index);
            returnVector.add(new DownloadableItem(hashMap));
        }

        if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
        {
            logUtil.put("Found: " + size, this, "getForItem");
        }

        return returnVector;
    }

    public Vector getForItem(String id, String downloadItemId)
    {
        Vector returnVector = new Vector();

        HashMap keysAndValues = new HashMap();

        keysAndValues.put(BasicItemData.ID, id);
        keysAndValues.put(DownloadItemData.ID, downloadItemId);

        Vector vector = super.getRows(keysAndValues);

        final int size = vector.size();
        for(int index = 0; index < size; index++)
        {
            HashMap hashMap = (HashMap) vector.get(index);
            returnVector.add(new DownloadableItem(hashMap));
        }

        if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
        {
            logUtil.put("Found: " + size, this, "getForItem");
        }

        return returnVector;
    }

    public final String createTableStatement()
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(this.sqlStrings.CREATE_TABLE)
                .append(tableName)
                .append(this.sqlStrings.START)
                .append(DownloadItemData.ID)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(BasicItemData.ID)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(EntryData.getInstance().ENABLE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(DownloadItemData.SPECIAL_NAME)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(DownloadItemData.VERSION)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(DownloadItemData.CHANGES)
                .append(this.sqlTypeStrings.BLOB_NOT_NULL)
                .append(DownloadItemData.SYSTEM)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(DownloadItemData.PLATFORM)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(DownloadItemData.LICENSE_FILE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        //define the file download time limit and number of retries allowed and license
        stringBuffer.append(DownloadItemData.FILE)
                .append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL)
                .append(DownloadItemData.SIZE)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(DownloadItemData.VALID_TIME)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(DownloadItemData.RETRIES)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(EntryData.getInstance().TIMECREATED)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(EntryData.getInstance().LASTMODIFIED)
                .append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL)
                .append(this.sqlStrings.PRIMARY_KEY)
                .append(DownloadItemData.ID)
                .append(this.sqlStrings.END);

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
     public String getForm(String id)
     {
     return super.getInputWhere(BasicItemData.ID,id);
     }
     */
    /*
     public String getTable(String itemId)
     {
     return super.getTableWhere(BasicItemData.ID,itemId);
     }
     */
}
