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
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.init.db.InventoryDbInitInfo;
import org.allbinary.business.user.commerce.inventory.item.BasicItem;
import org.allbinary.business.user.commerce.inventory.item.BasicItemData;
import org.allbinary.business.user.commerce.inventory.item.ItemInterface;
import org.allbinary.business.user.commerce.money.MoneyException;
import org.allbinary.logic.StdUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.sql.AbSqlBean;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringValidationUtil;

public class InventoryEntity extends AbSqlBean implements InventoryEntityInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final BasicItemData basicItemData = BasicItemData.getInstance();
    
    private final String tableName = "basicinventory";

    public InventoryEntity()
    {
        super(new InventoryDbInitInfo());
        this.setTableName(this.tableName);
    }

    public void insert(BasicArrayList values)
    {
        try
        {
            super.insert(values);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                this.logUtil.putF(this.commonStrings.SUCCESS, this, INSERT);
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                this.logUtil.put(this.commonStrings.FAILURE, this, INSERT, e);
            }
        }
    }

    public void delete(String value)
    {
        try
        {
            super.deleteWhere(basicItemData.ID, value);
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                this.logUtil.putF(this.commonStrings.SUCCESS, this, this.commonStrings.delete);
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                this.logUtil.put(this.commonStrings.FAILURE, this, this.commonStrings.delete, e);
            }
        }
    }

    //I could create a factory to output the results that excepts a productId and xslt file
    public BasicArrayList getItems(StoreFrontInterface storeFrontInterface) throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
        {
            this.logUtil.putF("Getting Items For: " + storeFrontInterface.getName(), this, "getItems");
        }

        BasicArrayList itemVector = new BasicArrayListD();
        HashMap keysAndValues = StdUtil.getInstance().createHashMap();

        //AbSqlData.ANYSINGLECHARACTERMATCH +
        
///////////
      //TWB - GAE upgrade uses JIQL and it doesn't like LIKE in the SQL
        
        //HashMap likeKeysAndValues = StdUtil.getInstance().createHashMap();        
        //likeKeysAndValues.put(basicItemData.CATEGORY,
        //  storeFrontInterface.getCategoryPath() + AbSqlData.ANYMULTICHARACTERMATCH);

        //BasicArrayList itemHashMapVector = super.getRowsWhereLike(keysAndValues, likeKeysAndValues);
///////////

        BasicArrayList itemHashMapVector = super.getRows(keysAndValues);

        int size = itemHashMapVector.size();
        for (int i = 0; i < size; i++)
        {
            HashMap itemHashMap = (HashMap) itemHashMapVector.get(i);
            if (itemHashMap != null)
            {

      ///////////
            	//TWB - GAE upgrade uses JIQL and it doesn't like LIKE in the SQL so I fixed it
            	String category = (String) itemHashMap.get(basicItemData.CATEGORY);
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
        HashMap keysAndValues = StdUtil.getInstance().createHashMap();
        keysAndValues.put(basicItemData.ID, id);
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
        return super.getField(basicItemData.ID, id, basicItemData.WEIGHT);
    }

    /*
    public String getTable(String itemId)
    {
    return super.getTableWhere(basicItemData.ID,itemId);
    }
     */
    public final String createTableStatement()
    {
        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append(this.sqlStrings.CREATE_TABLE);
        stringBuffer.append(this.tableName);
        stringBuffer.append(this.sqlStrings.START);

        stringBuffer.append(basicItemData.ID);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(basicItemData.NUMBER);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(basicItemData.INBASKETS);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(basicItemData.WEIGHT);
        //stringBuffer.append(" DECIMAL (11,2) NOT NULL,");
        stringBuffer.append(" VARCHAR(20) NOT NULL,");

        stringBuffer.append(EntryData.getInstance().ENABLE);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(basicItemData.NEWORUSED);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(basicItemData.SUMMARY);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(basicItemData.DISTRIBUTOR);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(basicItemData.IDUSEDBYDISTRIBUTOR);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(basicItemData.PRODUCEDBY);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(basicItemData.PRODUCTIONDATE);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(basicItemData.STARTPRODUCTIONDATE);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(basicItemData.DESCRIPTION);
        stringBuffer.append(this.sqlTypeStrings.BLOB_NOT_NULL);

        stringBuffer.append(basicItemData.KEYWORDS);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(basicItemData.CATEGORY);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(basicItemData.TYPE);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(basicItemData.SMALLIMAGE);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(basicItemData.MEDIUMIMAGE);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(basicItemData.LARGEIMAGE);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(EntryData.getInstance().LASTMODIFIED);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(EntryData.getInstance().TIMECREATED);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(basicItemData.PRICE);
        //stringBuffer.append(" DECIMAL (11,2) NOT NULL,");
        stringBuffer.append(" VARCHAR(20) NOT NULL,");

        stringBuffer.append(basicItemData.COMMENT);
        stringBuffer.append(this.sqlTypeStrings.BLOB_NOT_NULL);

        //Special Inventory Types True or False
        stringBuffer.append(basicItemData.CUSTOMS);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(basicItemData.DOWNLOADS);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(basicItemData.GROUPS);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(basicItemData.OPTIONS);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(basicItemData.PERMISSIONS);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(basicItemData.SPECIALS);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(this.sqlStrings.PRIMARY_KEY);
        stringBuffer.append(basicItemData.ID);
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
    return super.getInputWhere(basicItemData.ID,id);
    }
     */
    public void update(HashMap updatedValues)
    {
        super.updateWhere(basicItemData.ID, (String) updatedValues.get(basicItemData.ID), updatedValues);
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
