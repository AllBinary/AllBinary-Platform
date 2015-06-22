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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import org.allbinary.business.init.db.HistoryDbInitInfo;
import org.allbinary.business.installer.Portion;
import org.allbinary.logic.basic.io.file.generators.OrderHistoryIdGenerator;
import org.allbinary.logic.basic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.UserData;
import org.allbinary.business.user.address.StreetAddress;
import org.allbinary.business.user.commerce.inventory.basket.Basket;
import org.allbinary.business.user.commerce.inventory.basket.BasketInterface;
import org.allbinary.business.user.commerce.inventory.order.Order;
import org.allbinary.business.user.commerce.inventory.order.OrderHistory;
import org.allbinary.business.user.commerce.inventory.order.OrderHistoryData;
import org.allbinary.business.user.commerce.money.Money;
import org.allbinary.business.user.commerce.money.payment.PaymentInterface;
import org.allbinary.business.user.commerce.money.tax.TaxFactory;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.address.BillingAddressData;
import org.allbinary.business.user.address.ShippingAddressData;
import org.allbinary.business.user.commerce.inventory.order.OrderData;
import org.allbinary.business.user.commerce.money.payment.PaymentData;
import org.allbinary.business.user.commerce.shipping.ShippingMethodData;
import org.allbinary.business.user.commerce.shipping.ShippingMethods;
import org.allbinary.business.user.commerce.shipping.modules.ShippingInterface;
import org.allbinary.data.tables.TableDataFactory;
import org.allbinary.data.tables.user.address.billing.BillingAddressesEntity;
import org.allbinary.data.tables.user.address.shipping.ShippingAddressesEntity;
import org.allbinary.data.tables.user.commerce.money.payment.PaymentEntity;
import org.allbinary.logic.communication.sql.AbSqlBean;
import org.allbinary.logic.control.crypt.SuperCrypt;

public class OrderHistoryEntity extends AbSqlBean implements OrderHistoryEntityInterface
{
    protected final String tableName = "orderhistory";

    public OrderHistoryEntity()
    {
        super(new HistoryDbInitInfo());
        this.setTableName(tableName);
    }

    public void insert(String userName, Order order)
    {
        Vector vector = new Vector();
        try
        {
            PaymentInterface paymentInterface = new PaymentEntity().getDefault(userName);
            StreetAddress billingAddress = new BillingAddressesEntity(userName).getDefault();
            StreetAddress shippingAddress = new ShippingAddressesEntity(userName).getDefault();

            StoreFrontInterface storeFrontInterface =
                StoreFrontFactory.getInstance(order.getStoreName());

            ShippingInterface shippingInterface = new ShippingMethods(storeFrontInterface).getShippingInterface(order.getShippingMethod());

            Money shippingCost = shippingInterface.getCost(order);

            BasketInterface basketInterface = order.getBasket();

            Money subTotal = basketInterface.getSubTotal();

            Float taxRate = TaxFactory.getInstance(storeFrontInterface).getTaxRate(shippingAddress, storeFrontInterface);
            Money tax = new Money();
            Money total = new Money();

            total.add(shippingCost.toString());
            total.add(subTotal.toString());

            tax.add(total.toString());
            tax.multiply(taxRate);

            total.add(tax.toString());

            final String empty = StringUtil.getInstance().EMPTY_STRING;
            
            //vector.add("auto_increment");
            //vector.add(empty);
            vector.add(new OrderHistoryIdGenerator().getNext());   

            final String ZERO = TableDataFactory.getInstance().ZERO_STRING;

            vector.add(order.getId());
            vector.add(userName);
            vector.add(order.getStoreName());
            
            Calendar calendar = Calendar.getInstance();
            String time = new String(new Long(calendar.getTimeInMillis()).toString());
            
            vector.add(ZERO);
            vector.add(time);
            vector.add(ZERO);
            vector.add(ZERO);
            vector.add(OrderHistoryData.PREPROCESSING);
            vector.add(order.getPaymentMethod());

            if (paymentInterface != null)
            {
                vector.add(paymentInterface.getName());
                vector.add(paymentInterface.getType());
                vector.add(paymentInterface.getExpiration());
                int random = new Random().nextInt(SuperCrypt.KEYMAX);
                vector.add(new SuperCrypt(random).encrypt(paymentInterface.getNumber()));
                vector.add(new Integer(random).toString());
            } else
            {
                vector.add(empty);
                vector.add(empty);
                vector.add(empty);
                vector.add(empty);
                vector.add(ZERO);
            }

            if (billingAddress != null)
            {
                vector.add(billingAddress.getName());
                vector.add(billingAddress.getStreet());
                vector.add(billingAddress.getCity());
                vector.add(billingAddress.getState());
                vector.add(billingAddress.getCode());
                vector.add(billingAddress.getCountry());
            } else
            {
                vector.add(empty);
                vector.add(empty);
                vector.add(empty);
                vector.add(empty);
                vector.add(empty);
                vector.add(empty);
            }

            if (shippingAddress != null)
            {
                vector.add(shippingAddress.getName());
                vector.add(shippingAddress.getStreet());
                vector.add(shippingAddress.getCity());
                vector.add(shippingAddress.getState());
                vector.add(shippingAddress.getCode());
                vector.add(shippingAddress.getCountry());
            } else
            {
                vector.add(empty);
                vector.add(empty);
                vector.add(empty);
                vector.add(empty);
                vector.add(empty);
                vector.add(empty);
            }

            vector.add(order.getShippingMethod());
            vector.add(subTotal.toString());
            vector.add(shippingCost.toString());
            vector.add(tax.toString());
            vector.add(total.toString());
            vector.add(order.getSpecial());

            vector.add(order.getUserComments());
            vector.add(order.getUserCancelComments());

            vector.add(order.getStoreComments());
            vector.add(order.getStoreCancelComments());

            this.insert(vector);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_SUCCESS, this, INSERT));
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance("Command Failed: " + vector, this, INSERT, e));
            }
        }
    }

    public void insert(Vector values)
    {
        try
        {
            super.insert(values);

            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_SUCCESS, this, INSERT));
            }
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_FAILED, this, INSERT, e));
            }
        }
    }

    public void setStatus(String orderId, String status)
    {
        try
        {
            Calendar calendar = Calendar.getInstance();
            String time = new String(new Long(calendar.getTimeInMillis()).toString());
            HashMap updateHashMap = new HashMap();
            
            updateHashMap.put(OrderHistoryData.STATUS, status);
            
            if(status.compareTo(OrderHistoryData.CANCELLED) == 0)
            {
            	updateHashMap.put(OrderHistoryData.CANCELDATE, time);
            }
            else
            	if(status.compareTo(OrderHistoryData.SHIPPED) == 0)
            {
            		updateHashMap.put(OrderHistoryData.SHIPPEDDATE, time);
            }

            super.updateWhere(OrderData.ID, orderId, updateHashMap);

        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_FAILED, this, "setStatus", e));
            }
        }
    }

    public void setPaymentMethod(String orderId, String paymentMethod)
    {
        try
        {
            Calendar calendar = Calendar.getInstance();
            String time = new String(new Long(calendar.getTimeInMillis()).toString());
            
            HashMap updateHashMap = new HashMap();
            updateHashMap.put(PaymentData.METHOD, paymentMethod);

            updateHashMap.put(OrderHistoryData.TRANSDATE, time);
            
            super.updateWhere(OrderData.ID, orderId, updateHashMap);
        } catch (Exception e)
        {
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLLOGGING))
            {
                LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_FAILED, this, "setPaymentMethod", e));
            }
        }
    }

    /*
    public String getUserOrdersTable(String userName,String fromDate,String toDate)
    {
    HashMap whereHashMap = new HashMap();
    whereHashMap.put(UserData.USERNAME,userName);
    
    return super.getTableWhereBetween(whereHashMap,OrderHistoryData.ORDERDATE,fromDate,toDate);
    }
    
    public String getCancelledTable()
    {
    return super.getTableWhere(OrderHistoryData.STATUS,OrderHistoryData.CANCELLED);
    }
    
    public String getShippedTable()
    {
    return super.getTableWhere(OrderHistoryData.STATUS,OrderHistoryData.SHIPPED);
    }
    
    public String getPreprocessingTable()
    {
    return super.getTableWhere(OrderHistoryData.STATUS,OrderHistoryData.PREPROCESSING);
    }
    
    public String getProcessingTable()
    {
    return super.getTableWhere(OrderHistoryData.STATUS,OrderHistoryData.PROCESSING);
    }
    
    public String getPartiallyTable()
    {
    return super.getTableWhere(OrderHistoryData.STATUS,OrderHistoryData.PARTIALLYSHIPPED);
    }
    
     */
    public Vector getStoreOrders(StoreFrontInterface storeFrontInterface) throws Exception
    {
        Vector orderReviewVector = new Vector();
        HashMap whereHashMap = new HashMap();
        
        whereHashMap.put(StoreFrontData.getInstance().NAME, storeFrontInterface.getName());
        Vector orderHashMapVector = super.getRows(whereHashMap);
        Iterator orderIter = orderHashMapVector.iterator();

        while (orderIter.hasNext())
        {
            HashMap orderReviewHashMap = (HashMap) orderIter.next();
            OrderHistory orderReview = new OrderHistory(new Basket(), orderReviewHashMap);
            orderReviewVector.add(orderReview);
        }
        return orderReviewVector;
    }

    public Vector getOrders(String userName) throws Exception
    {
        Vector orderReviewVector = new Vector();
        HashMap whereHashMap = new HashMap();
        whereHashMap.put(UserData.USERNAME, userName);
        Vector orderHashMapVector = super.getRows(whereHashMap);
        Iterator orderIter = orderHashMapVector.iterator();

        while (orderIter.hasNext())
        {
            HashMap orderReviewHashMap = (HashMap) orderIter.next();
            OrderHistory orderReview = new OrderHistory(new Basket(), orderReviewHashMap);
            orderReviewVector.add(orderReview);
        }
        return orderReviewVector;
    }

    public Vector getOrders(String status, String fromDate, String toDate) throws Exception
    {
        Vector orderReviewVector = new Vector();
        HashMap whereHashMap = new HashMap();
        whereHashMap.put(OrderHistoryData.STATUS, status);
        Vector orderHashMapVector = super.getRowsWhereBetween(
            whereHashMap, OrderHistoryData.ORDERDATE, fromDate, toDate);
        Iterator orderIter = orderHashMapVector.iterator();

        while (orderIter.hasNext())
        {
            HashMap orderReviewHashMap = (HashMap) orderIter.next();
            OrderHistory orderReview = new OrderHistory(new Basket(), orderReviewHashMap);
            orderReviewVector.add(orderReview);
        }
        return orderReviewVector;
    }

    public Vector getOrders(String fromDate, String toDate) throws Exception
    {
        Vector orderReviewVector = new Vector();
        Vector orderHashMapVector = super.getRowsWhereBetween(OrderHistoryData.ORDERDATE, fromDate, toDate);
        Iterator orderIter = orderHashMapVector.iterator();

        while (orderIter.hasNext())
        {
            HashMap orderReviewHashMap = (HashMap) orderIter.next();
            OrderHistory orderReview = new OrderHistory(new Basket(), orderReviewHashMap);
            orderReviewVector.add(orderReview);
        }
        return orderReviewVector;
    }

    public OrderHistory getOrder(String id) throws Exception
    {
        HashMap whereHashMap = new HashMap();
        whereHashMap.put(OrderData.ID, id);

        HashMap orderReviewHashMap = super.getRow(whereHashMap);

        if (orderReviewHashMap != null)
        {
            OrderHistory orderHistory = new OrderHistory(new Basket(), orderReviewHashMap);
            return orderHistory;
        } else
        {
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
        stringBuffer.append(" BIGINT(19) UNSIGNED AUTO_INCREMENT NOT NULL,");
        
        stringBuffer.append(OrderData.ID);
        stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL,");
 
            stringBuffer.append(UserData.USERNAME);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");
            
            stringBuffer.append(StoreFrontData.getInstance().NAME);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");

            stringBuffer.append(OrderHistoryData.SHIPPEDDATE);
            stringBuffer.append(" BIGINT(19) UNSIGNED ,");

            stringBuffer.append(OrderHistoryData.ORDERDATE);
            stringBuffer.append(" BIGINT(19) UNSIGNED NOT NULL,");
            
            stringBuffer.append(OrderHistoryData.TRANSDATE);
            stringBuffer.append(" BIGINT(19) UNSIGNED ,");

            stringBuffer.append(OrderHistoryData.CANCELDATE);
            stringBuffer.append(" BIGINT(19) UNSIGNED ,");

            stringBuffer.append(OrderHistoryData.STATUS);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");

            stringBuffer.append(PaymentData.METHOD);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");

            stringBuffer.append(PaymentData.NAME);
            stringBuffer.append(" VARCHAR(255) ,");

            stringBuffer.append(PaymentData.TYPE);
            stringBuffer.append(" VARCHAR(255) ,");

            stringBuffer.append(PaymentData.EXPIRATION);
            stringBuffer.append(" VARCHAR(255) ,");

            stringBuffer.append(PaymentData.NUMBER);
            stringBuffer.append(" VARCHAR(255) ,");

            stringBuffer.append(entryData.ENCRYPTION);
            stringBuffer.append(" BIGINT(19) UNSIGNED ,");
            
            stringBuffer.append(BillingAddressData.NAME);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");

            stringBuffer.append(BillingAddressData.STREET);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");

            stringBuffer.append(BillingAddressData.CITY);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");

            stringBuffer.append(BillingAddressData.STATE);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");

            stringBuffer.append(BillingAddressData.CODE);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");
            
            stringBuffer.append(BillingAddressData.COUNTRY);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");
            
            stringBuffer.append(ShippingAddressData.NAME);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");

            stringBuffer.append(ShippingAddressData.STREET);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");
            
            stringBuffer.append(ShippingAddressData.CITY);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");
            
            stringBuffer.append(ShippingAddressData.STATE);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");
            
            stringBuffer.append(ShippingAddressData.CODE);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");
            
            stringBuffer.append(ShippingAddressData.COUNTRY);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");

            stringBuffer.append(ShippingMethodData.NAME);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");
            
            stringBuffer.append(OrderHistoryData.SUBTOTAL);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");
            
            stringBuffer.append(OrderHistoryData.SHIPPINGCOST);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");

            stringBuffer.append(OrderHistoryData.TAX);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");
            
            stringBuffer.append(OrderHistoryData.TOTAL);
            stringBuffer.append(" VARCHAR(255) NOT NULL,");
            
            stringBuffer.append(entryData.SPECIAL);
            stringBuffer.append(" VARCHAR(255) ,");

            stringBuffer.append(OrderData.CUSTOMERCOMMENT);
            stringBuffer.append(" BLOB NOT NULL,");
            
            stringBuffer.append(OrderData.CUSTOMERCANCELCOMMENT);
            stringBuffer.append(" BLOB NOT NULL,");
            
            stringBuffer.append(OrderData.STORECOMMENT);
            stringBuffer.append(" BLOB NOT NULL,");

            stringBuffer.append(OrderData.STORECANCELCOMMENT);
            stringBuffer.append(" BLOB NOT NULL,");

        stringBuffer.append("PRIMARY KEY(");
        stringBuffer.append(entryData.ID);
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
     */
    public void update(HashMap whereHashMap, HashMap orderHashMap) throws Exception
    {
        try
        {
            updateWhere(whereHashMap, orderHashMap);
        } catch (Exception e)
        {
            throw e;
        }
    }
}
