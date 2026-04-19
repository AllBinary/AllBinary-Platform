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

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.UserData;
import org.allbinary.business.user.address.BillingAddressData;
import org.allbinary.business.user.address.ShippingAddressData;
import org.allbinary.business.user.commerce.inventory.order.OrderData;
import org.allbinary.business.user.commerce.inventory.order.OrderHistoryData;
import org.allbinary.business.user.commerce.money.payment.PaymentData;
import org.allbinary.business.user.commerce.shipping.ShippingMethodData;
import org.allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntityFactory;
import org.allbinary.logic.communication.log.LogUtil;

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
      this.entryId = this.request.getParameter(EntryData.getInstance().ID);
      this.orderId = this.request.getParameter(OrderData.ID);
      this.userName = this.request.getParameter(UserData.USERNAME);
      this.storeName = this.request.getParameter(StoreFrontData.getInstance().NAME);
      this.shipDate = this.request.getParameter(OrderHistoryData.SHIPPEDDATE);
      this.orderDate = this.request.getParameter(OrderHistoryData.ORDERDATE);
      this.transDate = this.request.getParameter(OrderHistoryData.TRANSDATE);
      this.cancelDate = this.request.getParameter(OrderHistoryData.CANCELDATE);
      this.status = this.request.getParameter(OrderHistoryData.STATUS);

      this.paymentMethod = this.request.getParameter(PaymentData.METHOD);
      this.name = this.request.getParameter(PaymentData.NAME);
      this.type = this.request.getParameter(PaymentData.TYPE);
      this.expiration = this.request.getParameter(PaymentData.EXPIRATION);
      this.number = this.request.getParameter(PaymentData.NUMBER);
      this.encryption = this.request.getParameter(EntryData.getInstance().ENCRYPTION);
      
      this.billingName = this.request.getParameter(BillingAddressData.NAME);
      this.billingStreet = this.request.getParameter(BillingAddressData.STREET);
      this.billingCity = this.request.getParameter(BillingAddressData.CITY);
      this.billingState = this.request.getParameter(BillingAddressData.STATE);
      this.billingCode = this.request.getParameter(BillingAddressData.CODE);
      this.billingCountry = this.request.getParameter(BillingAddressData.COUNTRY);
          
      this.shippingName = this.request.getParameter(ShippingAddressData.NAME);
      this.shippingStreet = this.request.getParameter(ShippingAddressData.STREET);
      this.shippingCity = this.request.getParameter(ShippingAddressData.CITY);
      this.shippingState = this.request.getParameter(ShippingAddressData.STATE);
      this.shippingCode = this.request.getParameter(ShippingAddressData.CODE);
      this.shippingCountry = this.request.getParameter(ShippingAddressData.COUNTRY);
    
      this.shippingMethodName = this.request.getParameter(ShippingMethodData.NAME);
      this.subTotal = this.request.getParameter(OrderHistoryData.SUBTOTAL);
      this.shippingCost = this.request.getParameter(OrderHistoryData.SHIPPINGCOST);
      this.tax = this.request.getParameter(OrderHistoryData.TAX);
      this.total = this.request.getParameter(OrderHistoryData.TOTAL);
   
      this.special = this.request.getParameter(EntryData.getInstance().SPECIAL);
      
      this.userComment = this.request.getParameter(OrderData.CUSTOMERCOMMENT);
      this.userCancelComment = this.request.getParameter(OrderData.CUSTOMERCANCELCOMMENT);
      this.storeComment = this.request.getParameter(OrderData.STORECOMMENT);
      this.storeCancelComment = this.request.getParameter(OrderData.STORECANCELCOMMENT);   
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
            this.logUtil.putF(success,this,"update()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to update order table";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            this.logUtil.put(commonStrings.EXCEPTION,this,"update()",e);
         }
         return error;
      }
   }
   
}
