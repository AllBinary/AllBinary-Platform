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
package org.allbinary.business.user.commerce.inventory.order;

public class OrderHistoryData
{   
   public static int MAXIDLEN = 19;
   
   public static final String DATEDELTA = "DATE_DELTA";
   
   public static final String DATETYPE = "DATE_TYPE";
   public static final String TYPELONG = "TYPE_LONG";
   public static final String TYPECAESAR = "TYPE_CAESAR";
   
   public static final String TODATE = "TO_DATE";

   public static final String FROMHOUR = "FROM_HOUR";
   public static final String FROMDAY = "FROM_DAY";
   public static final String FROMMONTH = "FROM_MONTH";
   public static final String FROMYEAR = "FROM_YEAR";

   public static final String TOHOUR = "TO_HOUR";
   public static final String TODAY = "TO_DAY";
   public static final String TOMONTH = "TO_MONTH";
   public static final String TOYEAR = "TO_YEAR";
      
   public static final String ORDERHISTORY = "ORDERHISTORY";   

   //Transaction dates
   public static final String SHIPPEDDATE = "ORDERHISTORY_SHIPPED_DATE";
   public static final String ORDERDATE = "ORDERHISTORY_ORDER_DATE";
   public static final String TRANSDATE = "ORDERHISTORY_TRANS_DATE";
   public static final String CANCELDATE = "ORDERHISTORY_CANCEL_DATE";

   public static final String SHIPPEDDATEFORMATTED = "ORDERHISTORY_SHIPPED_DATE_FORMATTED";
   public static final String ORDERDATEFORMATTED = "ORDERHISTORY_ORDER_DATE_FORMATTED";
   public static final String TRANSDATEFORMATTED = "ORDERHISTORY_TRANS_DATE_FORMATTED";
   public static final String CANCELDATEFORMATTED = "ORDERHISTORY_CANCEL_DATE_FORMATTED";
   
   public static final String SUBTOTAL = "ORDERHISTORY_SUB_TOTAL";
   public static final String SHIPPINGCOST = "ORDERHISTORY_SHIPPING_COST";
   public static final String TAX = "ORDERHISTORY_TAX";
   public static final String TOTAL = "ORDERHISTORY_TOTAL";

   public static final String CANCELINFO = "ORDERHISTORY_CANCEL_INFO";
   public static final String CANCELTYPE = "ORDERHISTORY_CANCEL_TYPE"; 
   
   public static final String STATUS = "ORDERHISTORY_STATUS";
     
   //different order status information
   public static final String PREPROCESSINGNAME = "PREPROCESSING";
   public static final String PROCESSINGNAME = "PROCESSING";
   public static final String CANCELLEDNAME = "CANCELLED";
   public static final String PARTIALLYSHIPPEDNAME = "PARTIALLYSHIPPED";   
   public static final String SHIPPEDNAME = "SHIPPED";

   public static final String PREPROCESSING = "Order Preprocessing";
   public static final String PROCESSING = "Order Processing";
   public static final String CANCELLED = "Order Cancelled";
   public static final String PARTIALLYSHIPPED = "Order Partially Shipped";   
   public static final String SHIPPED = "Order Shipped";      
   
   //Status Command
   public static final String SETSTATUS = "Set Status";
   
   //public static final String ITEMID = "ITEM_ID";
   //public static final String NAME = "NAME";
   
}
