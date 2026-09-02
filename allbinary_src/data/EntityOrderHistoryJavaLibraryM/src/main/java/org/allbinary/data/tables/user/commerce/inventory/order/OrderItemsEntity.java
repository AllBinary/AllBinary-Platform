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

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Set;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.init.db.HistoryDbInitInfo;
import org.allbinary.logic.StdUtil;
import org.allbinary.business.user.UserData;
import org.allbinary.business.user.address.ShippingAddressData;
import org.allbinary.business.user.commerce.inventory.basket.BasketInterface;
import org.allbinary.business.user.commerce.inventory.basket.BasketReview;
import org.allbinary.business.user.commerce.inventory.item.BasicItemData;
import org.allbinary.business.user.commerce.inventory.item.Item;
import org.allbinary.business.user.commerce.inventory.item.ItemInterface;
import org.allbinary.business.user.commerce.inventory.order.OrderData;
import org.allbinary.business.user.commerce.inventory.order.OrderHistoryData;
import org.allbinary.business.user.commerce.inventory.order.OrderInterface;
import org.allbinary.business.user.commerce.shipping.ShippingMethodData;
import org.allbinary.data.generator.OrderItemIdGenerator;
import org.allbinary.data.tables.TableDataFactory;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntity;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntityFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.sql.AbSqlBean;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;

public class OrderItemsEntity extends AbSqlBean implements OrderItemsEntityInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final BasicItemData basicItemData = BasicItemData.getInstance();

    protected final String tableName = "orderitems";

    public OrderItemsEntity()
    {
        super(new HistoryDbInitInfo());
        this.setTableName(this.tableName);
    }

    public void insert(String userName, OrderInterface order)
    {
        BasicArrayList vector = new BasicArrayListD();
        try
        {

            BasketInterface basket = order.getBasket();

            Set items = basket.getIds();
            //HashMap numberOfEachItem = StdUtil.getInstance().createHashMap();

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
                vector = new BasicArrayListD();

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
                this.logUtil.putF("Command Success Added: " + items.size(), this, INSERT);
            }
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                this.logUtil.put("Command Failed: " + vector.toString(), this, INSERT, e);
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
            HashMap whereHashMap = StdUtil.getInstance().createHashMap();
            HashMap updateHashMap = StdUtil.getInstance().createHashMap();
            whereHashMap.put(OrderData.ID, orderId);
            whereHashMap.put(ShippingMethodData.GROUP, groupId);
            updateHashMap.put(OrderHistoryData.STATUS, status);

            super.updateWhere(whereHashMap, updateHashMap);
        }catch(Exception e)
        {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLLOGGING))
            {
                this.logUtil.put(this.commonStrings.FAILURE, this, "setStatus", e);
            }
        }
    }

    public boolean isEverythingShipped(String orderId)
    {
        try
        {
            final BasicArrayList itemStatusVector = super.getColumnWhere(OrderHistoryData.STATUS, OrderData.ID, orderId);
            final int size = itemStatusVector.size();

            for(int index = 0; index < size; index++)
            {
                String status = (String) itemStatusVector.get(index);

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
                this.logUtil.put(this.commonStrings.FAILURE, this, "isEverythingShipped", e);
            }
            return false;
        }

    }

    public BasketReview getBasketReview(String orderId)
    {
        try
        {
            BasketReview basketReview = new BasketReview();
            HashMap keyValues = StdUtil.getInstance().createHashMap();
            keyValues.put(OrderData.ID, orderId);
            BasicArrayList items = super.getRows(keyValues);

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
                    itemHashMap.put(basicItemData.INBASKETS, EMPTY_STRING);
                    itemHashMap.put(entryData.ENABLE, EMPTY_STRING);
                    itemHashMap.put(entryData.TIMECREATED, EMPTY_STRING);
                    itemHashMap.put(entryData.LASTMODIFIED, EMPTY_STRING);

                    itemHashMap.put(basicItemData.GROUPS, EMPTY_STRING);
                    itemHashMap.put(basicItemData.OPTIONS, EMPTY_STRING);
                    itemHashMap.put(basicItemData.PERMISSIONS, EMPTY_STRING);
                    itemHashMap.put(basicItemData.SPECIALS, EMPTY_STRING);

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
                this.logUtil.put(this.commonStrings.FAILURE, this, "getBasketReview", e);
            }
            return null;
        }
    }

    public final String createTableStatement()
    {
        EntryData entryData = EntryData.getInstance();

        StringMaker stringBuffer = new StringMaker();

        stringBuffer.append("CREATE TABLE ");

        stringBuffer.append(this.tableName);
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

        stringBuffer.append(basicItemData.ID);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(basicItemData.NUMBER);
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

        stringBuffer.append(basicItemData.WEIGHT);
        stringBuffer.append(this.sqlTypeStrings.MAX_CHAR_COLUMN_NOT_NULL);

        stringBuffer.append(basicItemData.PRICE);
        //stringBuffer.append(" DECIMAL (11,2) NOT NULL,");
        stringBuffer.append(" VARCHAR(20) NOT NULL,");

        stringBuffer.append(basicItemData.COMMENT);
        stringBuffer.append(this.sqlTypeStrings.BLOB_NOT_NULL);

        stringBuffer.append(basicItemData.CUSTOMS);
        stringBuffer.append(this.sqlTypeStrings.MAX_BIG_INT_UNSIGNED_NOT_NULL);

        stringBuffer.append(basicItemData.DOWNLOADS);
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
