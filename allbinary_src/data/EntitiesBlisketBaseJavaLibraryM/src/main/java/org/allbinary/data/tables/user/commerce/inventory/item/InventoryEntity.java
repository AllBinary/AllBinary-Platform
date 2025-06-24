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
package org.allbinary.data.tables.user.commerce.inventory.item;

import java.util.HashMap;

import java.util.Vector;

import org.allbinary.business.init.db.InventoryDbInitInfo;
import org.allbinary.business.installer.Portion;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.commerce.inventory.item.BasicItem;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.commerce.inventory.item.BasicItemData;
import org.allbinary.business.user.commerce.inventory.item.ItemInterface;
import org.allbinary.business.user.commerce.money.MoneyException;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.communication.sql.AbSqlBean;

public class InventoryEntity extends AbSqlBean implements InventoryEntityInterface
{
    private final String tableName = "basicinventory";

    public InventoryEntity()
    {
        super(new InventoryDbInitInfo());
        this.setTableName(tableName);
    }

    public void insert(Vector values)
    {
        try
        {
            super.insert(values);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.SUCCESS, this, INSERT));
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, INSERT, e));
            }
        }
    }

    public void delete(String value)
    {
        try
        {
            super.deleteWhere(BasicItemData.ID, value);
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.SUCCESS, this, DELETE));
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, DELETE, e));
            }
        }
    }

    //I could create a factory to output the results that excepts a productId and xslt file
    public Vector getItems(StoreFrontInterface storeFrontInterface) throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
        {
            LogUtil.put(LogFactory.getInstance("Getting Items For: " + storeFrontInterface.getName(), this, "getItems"));
        }

        Vector itemVector = new Vector();
        HashMap keysAndValues = new HashMap();

        //AbSqlData.ANYSINGLECHARACTERMATCH +
        
///////////
      //TWB - GAE upgrade uses JIQL and it doesn't like LIKE in the SQL
        
        //HashMap likeKeysAndValues = new HashMap();        
        //likeKeysAndValues.put(BasicItemData.CATEGORY,
        //  storeFrontInterface.getCategoryPath() + AbSqlData.ANYMULTICHARACTERMATCH);

        //Vector itemHashMapVector = super.getRowsWhereLike(keysAndValues, likeKeysAndValues);
///////////

        Vector itemHashMapVector = super.getRows(keysAndValues);

        int size = itemHashMapVector.size();
        for (int i = 0; i < size; i++)
        {
            HashMap itemHashMap = (HashMap) itemHashMapVector.get(i);
            if (itemHashMap != null)
            {

      ///////////
            	//TWB - GAE upgrade uses JIQL and it doesn't like LIKE in the SQL so I fixed it
            	String category = (String) itemHashMap.get(BasicItemData.CATEGORY);
            	if(!StringValidationUtil.getInstance().isEmpty(category) && 
         			category.startsWith(storeFrontInterface.getCategoryPath()))
            	{
            		itemVector.add(new BasicItem(itemHashMap));
            	}
///////////
            	//itemVector.add(new BasicItem(itemHashMap));
            }
        }

        return itemVector;
    }

    //I could create a factory to output the results that excepts a productId and xslt file
    public ItemInterface getItem(String id) throws MoneyException
    {
        HashMap keysAndValues = new HashMap();
        keysAndValues.put(BasicItemData.ID, id);
        HashMap itemHashMap = super.getRow(keysAndValues);
        if (itemHashMap != null)
        {
            return new BasicItem(itemHashMap);
        } else
        {
            return null;
        }
    }

    public String getWeight(String id)
    {
        return super.getField(BasicItemData.ID, id, BasicItemData.WEIGHT);
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

        stringBuffer.append(this.sqlStrings.CREATE_TABLE);
        stringBuffer.append(tableName);
        stringBuffer.append(this.sqlStrings.START);

        stringBuffer.append(BasicItemData.ID);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(BasicItemData.NUMBER);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(BasicItemData.INBASKETS);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(BasicItemData.WEIGHT);
        //stringBuffer.append(" DECIMAL (11,2) NOT NULL,");
        stringBuffer.append(" VARCHAR(20) NOT NULL,");

        stringBuffer.append(EntryData.getInstance().ENABLE);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(BasicItemData.NEWORUSED);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(BasicItemData.SUMMARY);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(BasicItemData.DISTRIBUTOR);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(BasicItemData.IDUSEDBYDISTRIBUTOR);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(BasicItemData.PRODUCEDBY);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(BasicItemData.PRODUCTIONDATE);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(BasicItemData.STARTPRODUCTIONDATE);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(BasicItemData.DESCRIPTION);
        stringBuffer.append(this.sqlTypeStrings.BLOB_NOT_NULL);

        stringBuffer.append(BasicItemData.KEYWORDS);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(BasicItemData.CATEGORY);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(BasicItemData.TYPE);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(BasicItemData.SMALLIMAGE);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(BasicItemData.MEDIUMIMAGE);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(BasicItemData.LARGEIMAGE);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(EntryData.getInstance().LASTMODIFIED);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(EntryData.getInstance().TIMECREATED);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(BasicItemData.PRICE);
        //stringBuffer.append(" DECIMAL (11,2) NOT NULL,");
        stringBuffer.append(" VARCHAR(20) NOT NULL,");

        stringBuffer.append(BasicItemData.COMMENT);
        stringBuffer.append(this.sqlTypeStrings.BLOB_NOT_NULL);

        //Special Inventory Types True or False
        stringBuffer.append(BasicItemData.CUSTOMS);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(BasicItemData.DOWNLOADS);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(BasicItemData.GROUPS);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(BasicItemData.OPTIONS);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(BasicItemData.PERMISSIONS);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(BasicItemData.SPECIALS);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(this.sqlStrings.PRIMARY_KEY);
        stringBuffer.append(BasicItemData.ID);
        stringBuffer.append(this.sqlStrings.END);

        return stringBuffer.toString();
    }

    public String createTable()
    {
        return super.createTable(this.createTableStatement());
    }

    /*
    public String getItemForm(String id)
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
    }
     */
}
