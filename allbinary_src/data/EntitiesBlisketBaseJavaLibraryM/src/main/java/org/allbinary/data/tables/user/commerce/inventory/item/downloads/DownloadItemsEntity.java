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
import org.EntryData;
import org.BasicItemData;
import org.DownloadItemData;
import org.allbinary.business.user.commerce.inventory.item.download.DownloadableItem;
import org.allbinary.logic.communication.sql.AbSqlBean;

public class DownloadItemsEntity extends AbSqlBean implements DownloadItemsEntityInterface
{

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

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Command Success", this, "insert"));
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Command Failed", this, "insert", e));
            }
        }
    }

    public void delete(String value)
    {
        try
        {
            super.deleteWhere(DownloadItemData.ID, value);
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Command Success", this, "delete"));
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Command Failed", this, "delete", e));
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
        for (int index = 0; index < size; index++)
        {
            HashMap hashMap = (HashMap) vector.get(index);
            returnVector.add(new DownloadableItem(hashMap));
        }

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
        {
            LogUtil.put(LogFactory.getInstance("Found: " + size, this, "getForItem"));
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
        for (int index = 0; index < size; index++)
        {
            HashMap hashMap = (HashMap) vector.get(index);
            returnVector.add(new DownloadableItem(hashMap));
        }

        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
        {
            LogUtil.put(LogFactory.getInstance("Found: " + size, this, "getForItem"));
        }

        return returnVector;
    }

    public final String createTableStatement()
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("CREATE TABLE ");

        stringBuffer.append(tableName);
        stringBuffer.append(" (");

        stringBuffer.append(DownloadItemData.ID);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL,");

        stringBuffer.append(BasicItemData.ID);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL,");

        stringBuffer.append(EntryData.getInstance().ENABLE);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(DownloadItemData.SPECIAL_NAME);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(DownloadItemData.VERSION);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(DownloadItemData.CHANGES);
        stringBuffer.append(" BLOB NOT NULL,");

        stringBuffer.append(DownloadItemData.SYSTEM);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(DownloadItemData.PLATFORM);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(DownloadItemData.LICENSE_FILE);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");
        //define the file download time limit and number of retries allowed and license

        stringBuffer.append(DownloadItemData.FILE);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(DownloadItemData.SIZE);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL, ");

        stringBuffer.append(DownloadItemData.VALID_TIME);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL, ");

        stringBuffer.append(DownloadItemData.RETRIES);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL, ");

        stringBuffer.append(EntryData.getInstance().TIMECREATED);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL, ");

        stringBuffer.append(EntryData.getInstance().LASTMODIFIED);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL, ");

        stringBuffer.append("PRIMARY KEY(");
        stringBuffer.append(DownloadItemData.ID);
        stringBuffer.append(") )");

        return stringBuffer.toString();
    }

    public String createTable()
    {
        return super.createTable(this.createTableStatement());
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
