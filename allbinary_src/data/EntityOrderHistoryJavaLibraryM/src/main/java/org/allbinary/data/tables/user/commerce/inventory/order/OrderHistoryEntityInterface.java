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
import java.util.Vector;

import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.commerce.inventory.order.Order;
import org.allbinary.business.user.commerce.inventory.order.OrderHistory;
import org.allbinary.data.tables.BasicDataTableInterface;

public interface OrderHistoryEntityInterface extends BasicDataTableInterface
{
      
   public void insert(String userName, Order order);
      
   public void setStatus(String orderId,String status);
   
   public void setPaymentMethod(String orderId,String status);
   
   //public String getCancelledTable(String fromDate,String toDate);
   
   //public String getShippedTable(String fromDate,String toDate);
   
  // public String getPreprocessingTable(String fromDate,String toDate);

   //public String getProcessingTable(String fromDate,String toDate);

   //public String getPartiallyShippedTable(String fromDate,String toDate);
   
   //public String getUserOrdersTable(String userName,String fromDate,String toDate);
   
   //public String getCancelledTable();
   
   //public String getShippedTable();
   
   //public String getPreprocessingTable();

   //public String getProcessingTable();
   
   //public String getPartiallyTable();
   
   //public String getUserOrdersTable(String userName);
   
  // public String getOrderTable(String orderId);
   
  // public String getOrderForm(String orderId);

   public Vector getStoreOrders(StoreFrontInterface storeFrontInterface) throws Exception;
   
   public Vector getOrders(String userName) throws Exception;
   
   public Vector getOrders(String status, String fromDate, String toDate) throws Exception;
   
   public OrderHistory getOrder(String id) throws Exception;

   public void update(HashMap whereHashMap, HashMap orderHashMap) throws Exception;
   
   //public void delete(String value);
}
