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
import java.util.Iterator;
import java.util.Vector;

import org.allbinary.business.init.db.InventoryDbInitInfo;
import org.allbinary.business.installer.Portion;
import org.allbinary.StringValidationUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.EntryData;
import org.allbinary.business.user.commerce.inventory.item.BasicItem;
import org.BasicItemData;
import org.allbinary.business.user.commerce.inventory.item.ItemInterface;
import org.allbinary.business.user.commerce.money.MoneyException;
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
            super.deleteWhere(BasicItemData.ID, value);
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

    //I could create a factory to output the results that excepts a productId and xslt file
    public Vector getItems(StoreFrontInterface storeFrontInterface) throws Exception
    {
        if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
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

        Iterator iter = itemHashMapVector.iterator();
        while (iter.hasNext())
        {
            HashMap itemHashMap = (HashMap) iter.next();
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

        stringBuffer.append("CREATE TABLE ");
        stringBuffer.append(tableName);
        stringBuffer.append(" (");

        stringBuffer.append(BasicItemData.ID);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL,");

        stringBuffer.append(BasicItemData.NUMBER);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL,");

        stringBuffer.append(BasicItemData.INBASKETS);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL,");

        stringBuffer.append(BasicItemData.WEIGHT);
        //stringBuffer.append(" DECIMAL (11,2) NOT NULL,");
        stringBuffer.append(" VARCHAR(20) NOT NULL,");

        stringBuffer.append(EntryData.getInstance().ENABLE);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(BasicItemData.NEWORUSED);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(BasicItemData.SUMMARY);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(BasicItemData.DISTRIBUTOR);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(BasicItemData.IDUSEDBYDISTRIBUTOR);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(BasicItemData.PRODUCEDBY);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(BasicItemData.PRODUCTIONDATE);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(BasicItemData.STARTPRODUCTIONDATE);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(BasicItemData.DESCRIPTION);
        stringBuffer.append(" BLOB NOT NULL,");

        stringBuffer.append(BasicItemData.KEYWORDS);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(BasicItemData.CATEGORY);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(BasicItemData.TYPE);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(BasicItemData.SMALLIMAGE);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(BasicItemData.MEDIUMIMAGE);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(BasicItemData.LARGEIMAGE);
        stringBuffer.append(" VARCHAR(255) NOT NULL,");

        stringBuffer.append(EntryData.getInstance().LASTMODIFIED);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL, ");

        stringBuffer.append(EntryData.getInstance().TIMECREATED);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL, ");

        stringBuffer.append(BasicItemData.PRICE);
        //stringBuffer.append(" DECIMAL (11,2) NOT NULL,");
        stringBuffer.append(" VARCHAR(20) NOT NULL,");

        stringBuffer.append(BasicItemData.COMMENT);
        stringBuffer.append(" BLOB NOT NULL,");

        //Special Inventory Types True or False
        stringBuffer.append(BasicItemData.CUSTOMS);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL,");

        stringBuffer.append(BasicItemData.DOWNLOADS);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL,");

        stringBuffer.append(BasicItemData.GROUPS);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL,");

        stringBuffer.append(BasicItemData.OPTIONS);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL,");

        stringBuffer.append(BasicItemData.PERMISSIONS);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL,");

        stringBuffer.append(BasicItemData.SPECIALS);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL,");

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
    public String getItemForm(String id)
    {
    return super.getInputWhere(BasicItemData.ID,id);
    }
     */
    public void update(HashMap updatedValues)
    {
        super.updateWhere(BasicItemData.ID, (String) updatedValues.get(BasicItemData.ID), updatedValues);
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
