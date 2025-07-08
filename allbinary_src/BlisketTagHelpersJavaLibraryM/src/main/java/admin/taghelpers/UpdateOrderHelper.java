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
package admin.taghelpers;

import org.allbinary.logic.communication.log.LogFactory;
import java.util.*;

import javax.servlet.jsp.PageContext;

import javax.servlet.http.HttpServletRequest;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.business.user.address.ShippingAddressData;
import org.allbinary.business.user.address.BillingAddressData;
import org.allbinary.business.user.UserData;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.commerce.money.payment.PaymentData;
import org.allbinary.business.user.commerce.inventory.order.OrderHistoryData;
import org.allbinary.business.user.commerce.inventory.order.OrderData;
import org.allbinary.business.user.commerce.shipping.ShippingMethodData;
import org.allbinary.business.context.modules.storefront.StoreFrontData;

import org.allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntityFactory;

public class UpdateOrderHelper
    extends TagHelper
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private HttpServletRequest request;
   
   private String entryId;
   private String orderId;
   private String userName;
   private String storeName;
   private String shipDate;
   private String orderDate;
   private String transDate;
   private String cancelDate;
   private String status;
   
   private String paymentMethod;
   private String name;
   private String type;
   private String expiration;
   private String number;
   private String encryption;
   private String billingName;
   private String billingStreet;
   private String billingCity;
   private String billingState;
   private String billingCode;
   private String billingCountry;
   private String shippingName;
   private String shippingStreet;
   private String shippingCity;
   private String shippingState;
   private String shippingCode;
   private String shippingCountry;
   
   private String shippingMethodName;
   private String subTotal;
   private String shippingCost;
   private String tax;
   private String total;
   
   private String special;
   
   private String userComment;
   private String userCancelComment;
   private String storeComment;
   private String storeCancelComment;
   
   public UpdateOrderHelper(HashMap hashMap, PageContext pageContext)
   {
      this.request = (HttpServletRequest) pageContext.getRequest();
      this.getFormData();
   }
   
   private void getFormData()
   {   
      this.entryId = request.getParameter(EntryData.getInstance().ID);
      this.orderId = request.getParameter(OrderData.ID);
      this.userName = request.getParameter(UserData.USERNAME);
      this.storeName = request.getParameter(StoreFrontData.getInstance().NAME);
      this.shipDate = request.getParameter(OrderHistoryData.SHIPPEDDATE);
      this.orderDate = request.getParameter(OrderHistoryData.ORDERDATE);
      this.transDate = request.getParameter(OrderHistoryData.TRANSDATE);
      this.cancelDate = request.getParameter(OrderHistoryData.CANCELDATE);
      this.status = request.getParameter(OrderHistoryData.STATUS);

      this.paymentMethod = request.getParameter(PaymentData.METHOD);
      this.name = request.getParameter(PaymentData.NAME);
      this.type = request.getParameter(PaymentData.TYPE);
      this.expiration = request.getParameter(PaymentData.EXPIRATION);
      this.number = request.getParameter(PaymentData.NUMBER);
      this.encryption = request.getParameter(EntryData.getInstance().ENCRYPTION);
      
      this.billingName = request.getParameter(BillingAddressData.NAME);
      this.billingStreet = request.getParameter(BillingAddressData.STREET);
      this.billingCity = request.getParameter(BillingAddressData.CITY);
      this.billingState = request.getParameter(BillingAddressData.STATE);
      this.billingCode = request.getParameter(BillingAddressData.CODE);
      this.billingCountry = request.getParameter(BillingAddressData.COUNTRY);
          
      this.shippingName = request.getParameter(ShippingAddressData.NAME);
      this.shippingStreet = request.getParameter(ShippingAddressData.STREET);
      this.shippingCity = request.getParameter(ShippingAddressData.CITY);
      this.shippingState = request.getParameter(ShippingAddressData.STATE);
      this.shippingCode = request.getParameter(ShippingAddressData.CODE);
      this.shippingCountry = request.getParameter(ShippingAddressData.COUNTRY);
    
      this.shippingMethodName = request.getParameter(ShippingMethodData.NAME);
      this.subTotal = request.getParameter(OrderHistoryData.SUBTOTAL);
      this.shippingCost = request.getParameter(OrderHistoryData.SHIPPINGCOST);
      this.tax = request.getParameter(OrderHistoryData.TAX);
      this.total = request.getParameter(OrderHistoryData.TOTAL);
   
      this.special = request.getParameter(EntryData.getInstance().SPECIAL);
      
      this.userComment = request.getParameter(OrderData.CUSTOMERCOMMENT);
      this.userCancelComment = request.getParameter(OrderData.CUSTOMERCANCELCOMMENT);
      this.storeComment = request.getParameter(OrderData.STORECOMMENT);
      this.storeCancelComment = request.getParameter(OrderData.STORECANCELCOMMENT);   
   }
   
   public String update()
   {
      try
      {
         String success = "Successful update";
         HashMap orderHashMap = new HashMap();
         HashMap whereHashMap = new HashMap();

         orderHashMap.put(EntryData.getInstance().ID,entryId);
         orderHashMap.put(OrderData.ID,this.orderId);         
         orderHashMap.put(UserData.USERNAME,userName);
         orderHashMap.put(StoreFrontData.getInstance().NAME,this.storeName);

         orderHashMap.put(OrderHistoryData.SHIPPEDDATE,shipDate);
         orderHashMap.put(OrderHistoryData.ORDERDATE,orderDate);
         orderHashMap.put(OrderHistoryData.TRANSDATE,transDate);
         orderHashMap.put(OrderHistoryData.CANCELDATE,cancelDate);
         orderHashMap.put(OrderHistoryData.STATUS,status);
         
         orderHashMap.put(PaymentData.METHOD,paymentMethod);
         orderHashMap.put(PaymentData.NAME,name);
         orderHashMap.put(PaymentData.TYPE,type);
         orderHashMap.put(PaymentData.EXPIRATION,expiration);
         //orderHashMap.put(PaymentData.NUMBER,number);
         //orderHashMap.put(EntryData.getInstance().ENCRYPTION,encryption);
         
         orderHashMap.put(BillingAddressData.NAME,billingName);
         orderHashMap.put(BillingAddressData.STREET,billingStreet);
         orderHashMap.put(BillingAddressData.CITY,billingCity);
         orderHashMap.put(BillingAddressData.STATE,billingState);
         orderHashMap.put(BillingAddressData.CODE,billingCode);
         orderHashMap.put(BillingAddressData.COUNTRY,billingCountry);
         
         orderHashMap.put(ShippingAddressData.NAME,shippingName);
         orderHashMap.put(ShippingAddressData.STREET,shippingStreet);
         orderHashMap.put(ShippingAddressData.CITY,shippingCity);
         orderHashMap.put(ShippingAddressData.STATE,shippingState);
         orderHashMap.put(ShippingAddressData.CODE,shippingCode);
         orderHashMap.put(ShippingAddressData.COUNTRY,shippingCountry);

         orderHashMap.put(ShippingMethodData.NAME, this.shippingMethodName);
         orderHashMap.put(OrderHistoryData.SUBTOTAL, this.subTotal);
         orderHashMap.put(OrderHistoryData.SHIPPINGCOST, this.shippingCost);
         orderHashMap.put(OrderHistoryData.TAX, this.tax);
         orderHashMap.put(OrderHistoryData.TOTAL,this.total);         

         orderHashMap.put(EntryData.getInstance().SPECIAL,special);

         orderHashMap.put(OrderData.CUSTOMERCOMMENT, this.userComment);
         orderHashMap.put(OrderData.CUSTOMERCANCELCOMMENT, this.userCancelComment);
         orderHashMap.put(OrderData.STORECOMMENT, this.storeComment);
         orderHashMap.put(OrderData.STORECANCELCOMMENT, this.storeCancelComment);         

         whereHashMap.put(OrderData.ID,orderId);
         
         OrderHistoryEntityFactory.getInstance().update(whereHashMap,orderHashMap);         
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"update()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to update order table";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"update()",e);
         }
         return error;
      }
   }
   
}
