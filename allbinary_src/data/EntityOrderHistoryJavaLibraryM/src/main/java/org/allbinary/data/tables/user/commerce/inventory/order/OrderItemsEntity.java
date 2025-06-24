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
package org.allbinary.data.tables.user.commerce.inventory.order;

import org.allbinary.data.generator.OrderItemIdGenerator;
import java.util.HashMap;

import java.util.ListIterator;
import java.util.Set;
import java.util.Vector;

import org.allbinary.business.init.db.HistoryDbInitInfo;
import org.allbinary.business.installer.Portion;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.user.UserData;
import org.allbinary.business.user.commerce.inventory.basket.BasketInterface;
import org.allbinary.business.user.commerce.inventory.basket.BasketReview;
import org.allbinary.business.user.commerce.inventory.item.Item;
import org.allbinary.business.user.commerce.inventory.item.ItemInterface;
import org.allbinary.business.user.commerce.inventory.order.OrderHistoryData;
import org.allbinary.business.user.commerce.inventory.order.OrderInterface;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.address.ShippingAddressData;
import org.allbinary.business.user.commerce.inventory.item.BasicItemData;
import org.allbinary.business.user.commerce.inventory.order.OrderData;
import org.allbinary.business.user.commerce.shipping.ShippingMethodData;
import org.allbinary.data.tables.TableDataFactory;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntity;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntityFactory;
import org.allbinary.logic.communication.sql.AbSqlBean;

public class OrderItemsEntity extends AbSqlBean implements OrderItemsEntityInterface
{

    protected final String tableName = "orderitems";

    public OrderItemsEntity()
    {
        super(new HistoryDbInitInfo());
        this.setTableName(tableName);
    }

    public void insert(String userName, OrderInterface order)
    {
        Vector vector = new Vector();
        try
        {

            BasketInterface basket = order.getBasket();

            Set items = basket.getIds();
            //HashMap numberOfEachItem = new HashMap();

            final String ZERO_STRING = TableDataFactory.getInstance().ZERO_STRING;
            final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;

            InventoryEntity inventoryEntity
                    = InventoryEntityFactory.getInstance().getInventoryEntityInstance();

            Object[] itemsArray = items.toArray();
            int itemsSize = itemsArray.length;
            for (int i = 0; i < itemsSize; i++)
            {
                String item = (String) itemsArray[i];
                ItemInterface itemInterface = inventoryEntity.getItem(item);

            //Calendar calendar=Calendar.getInstance();
                //String time = new String(new Long(calendar.getTimeInMillis()).toString());
                vector = new Vector();

            //vector.add(StringUtil.getInstance());
                //vector.add("auto_increment");
                vector.add(new OrderItemIdGenerator().getNext());

                vector.add(order.getId());
                vector.add(TableDataFactory.getInstance().INTEGER_MAX_VALUE_STRING);
                vector.add(userName);
                vector.add(order.getStoreName());

                vector.add(itemInterface.getId());

                vector.add(basket.getNumberOf(item).toString());

                vector.add(itemInterface.getNewOrUsed());
                vector.add(itemInterface.getSummary());
                vector.add(itemInterface.getDistributor());
                vector.add(itemInterface.getIdUsedByDistributor());
                vector.add(itemInterface.getProducedBy());
                vector.add(itemInterface.getProductionDate());
                vector.add(itemInterface.getStartProductionDate());
                vector.add(itemInterface.getDescription());
                vector.add(itemInterface.getKeywords());
                vector.add(itemInterface.getCategory());
                vector.add(itemInterface.getType());
                vector.add(itemInterface.getSmallImage());
                vector.add(itemInterface.getMediumImage());
                vector.add(itemInterface.getLargeImage());

                vector.add(itemInterface.getWeight());
                vector.add(itemInterface.getPrice().toString());

                vector.add(itemInterface.getComment());
                vector.add(itemInterface.getCustoms());
                vector.add(itemInterface.getDownloads());

                vector.add(EMPTY_STRING);
                vector.add(EMPTY_STRING);

                vector.add(EMPTY_STRING);
                vector.add(EMPTY_STRING);
                vector.add(EMPTY_STRING);
                vector.add(EMPTY_STRING);
                vector.add(EMPTY_STRING);
                vector.add(EMPTY_STRING);
                vector.add(EMPTY_STRING);
                vector.add(EMPTY_STRING);
                vector.add(EMPTY_STRING);
                vector.add(EMPTY_STRING);
                vector.add(EMPTY_STRING);

                vector.add(ZERO_STRING);
                vector.add(ZERO_STRING);
                vector.add(ZERO_STRING);
                vector.add(ZERO_STRING);

                this.insert(vector);
            }

            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Command Success Added: " + items.size(), this, INSERT));
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Command Failed: " + vector.toString(), this, INSERT, e));
            }
        }
    }

    /*
     public String getOrderTable(String orderId)
     {
     return super.getTableWhere(OrderData.ID,orderId);
     }
     */
    public void setStatus(String orderId, String groupId, String status)
    {
        try
        {
            HashMap whereHashMap = new HashMap();
            HashMap updateHashMap = new HashMap();
            whereHashMap.put(OrderData.ID, orderId);
            whereHashMap.put(ShippingMethodData.GROUP, groupId);
            updateHashMap.put(OrderHistoryData.STATUS, status);

            super.updateWhere(whereHashMap, updateHashMap);
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, "setStatus", e));
            }
        }
    }

    public boolean isEverythingShipped(String orderId)
    {
        try
        {
            Vector itemStatusVector = super.getColumnWhere(OrderHistoryData.STATUS, OrderData.ID, orderId);
            ListIterator itemStatusIter = itemStatusVector.listIterator();

            while(itemStatusIter.hasNext())
            {
                String status = (String) itemStatusIter.next();

                if(status.compareTo(OrderHistoryData.SHIPPED) != 0)
                {
                    return false;
                }
            }
            return true;
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, "isEverythingShipped", e));
            }
            return false;
        }

    }

    public BasketReview getBasketReview(String orderId)
    {
        try
        {
            BasketReview basketReview = new BasketReview();
            HashMap keyValues = new HashMap();
            keyValues.put(OrderData.ID, orderId);
            Vector items = super.getRows(keyValues);

            EntryData entryData = EntryData.getInstance();

            if(items != null && items.size() > 0)
            {
                final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;

                Object[] itemsArray = items.toArray();
                int itemsSize = itemsArray.length;
                for (int i = 0; i < itemsSize; i++)
                {
                    HashMap itemHashMap = (HashMap) itemsArray[i];

                    //add missing entries that exist in basic inventory for item construction
                    itemHashMap.put(BasicItemData.INBASKETS, EMPTY_STRING);
                    itemHashMap.put(entryData.ENABLE, EMPTY_STRING);
                    itemHashMap.put(entryData.TIMECREATED, EMPTY_STRING);
                    itemHashMap.put(entryData.LASTMODIFIED, EMPTY_STRING);

                    itemHashMap.put(BasicItemData.GROUPS, EMPTY_STRING);
                    itemHashMap.put(BasicItemData.OPTIONS, EMPTY_STRING);
                    itemHashMap.put(BasicItemData.PERMISSIONS, EMPTY_STRING);
                    itemHashMap.put(BasicItemData.SPECIALS, EMPTY_STRING);

                    Item item = new Item(itemHashMap);
                    basketReview.addItem(item);
                }

                return basketReview;
            }else
            {
                return null;
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE, this, "getBasketReview", e));
            }
            return null;
        }
    }

    public final String createTableStatement()
    {
        EntryData entryData = EntryData.getInstance();

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("CREATE TABLE ");

        stringBuffer.append(tableName);
        stringBuffer.append(" (");

        stringBuffer.append(entryData.ID);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(OrderData.ID);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(ShippingMethodData.GROUP);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(UserData.USERNAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(StoreFrontData.getInstance().NAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(BasicItemData.ID);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(BasicItemData.NUMBER);
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

        stringBuffer.append(BasicItemData.WEIGHT);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(BasicItemData.PRICE);
        //stringBuffer.append(" DECIMAL (11,2) NOT NULL,");
        stringBuffer.append(" VARCHAR(20) NOT NULL,");

        stringBuffer.append(BasicItemData.COMMENT);
        stringBuffer.append(this.sqlTypeStrings.BLOB_NOT_NULL);

        stringBuffer.append(BasicItemData.CUSTOMS);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(BasicItemData.DOWNLOADS);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(ShippingMethodData.COST);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(OrderHistoryData.TAX);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(ShippingAddressData.NAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(ShippingAddressData.STREET);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(ShippingAddressData.CITY);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(ShippingAddressData.STATE);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(ShippingAddressData.CODE);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(ShippingAddressData.COUNTRY);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(ShippingMethodData.NAME);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(entryData.SPECIAL);
        stringBuffer.append(" VARCHAR(255) ,");

        stringBuffer.append(OrderHistoryData.STATUS);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(OrderHistoryData.CANCELINFO);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(OrderHistoryData.CANCELTYPE);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(OrderHistoryData.SHIPPEDDATE);
        stringBuffer.append(" BIGINT(19) UNSIGNED ,");

        stringBuffer.append(OrderHistoryData.ORDERDATE);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(OrderHistoryData.TRANSDATE);
        stringBuffer.append(" BIGINT(19) UNSIGNED ,");

        stringBuffer.append(OrderHistoryData.CANCELDATE);
        stringBuffer.append(" BIGINT(19) UNSIGNED ,");

        stringBuffer.append("PRIMARY KEY(");
        stringBuffer.append(entryData.ID);
        stringBuffer.append(") )");

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
     */
}
