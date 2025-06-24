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

import java.util.Date;
import java.util.HashMap;

import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.UserData;
import org.allbinary.business.user.address.BillingAddressData;
import org.allbinary.business.user.address.ShippingAddressData;
import org.allbinary.business.user.address.StreetAddress;
import org.allbinary.business.user.commerce.inventory.basket.BasketInterface;
import org.allbinary.business.user.commerce.money.Money;
import org.allbinary.business.user.commerce.money.MoneyException;
import org.allbinary.business.user.commerce.money.payment.Payment;
import org.allbinary.business.user.commerce.money.payment.PaymentData;
import org.allbinary.business.user.commerce.shipping.ShippingMethodData;
import org.allbinary.data.tree.dom.ModDomHelper;

public class OrderHistory extends Order
        implements OrderHistoryInterface
{
   private String userName;
   
   private StreetAddress shippingAddress;
   private StreetAddress billingAddress;
   
   private Payment payment;   
   
   private String shipDate;
   private String orderDate;
   private String transDate;
   private String cancelDate;
   private String status;
   
   private Money subTotal;
   private Money shippingCost;
   private Money taxes;
   private Money total;   
   
   public OrderHistory(BasketInterface basketInterface)
   {
      super(basketInterface);
   }
      
   public OrderHistory(BasketInterface basketInterface, OrderHistory orderHistory)
   {
      super(orderHistory.getStoreName(), basketInterface, orderHistory.getId(),orderHistory.getShippingMethod(),orderHistory.getSpecial(),orderHistory.getPaymentMethod());
      this.userName = orderHistory.getUserName();
      this.shippingAddress = orderHistory.getShippingAddress();
      this.billingAddress = orderHistory.getBillingAddress();
      this.payment = orderHistory.getPaymentInfo();       
      this.shipDate = orderHistory.getShipDate();
      this.orderDate = orderHistory.getOrderDate();
      this.transDate = orderHistory.getTransDate();
      this.cancelDate = orderHistory.getCancelDate();
      this.status = orderHistory.getStatus();
      this.subTotal = orderHistory.getSubTotal();
      this.shippingCost =orderHistory.getShippingCost();
      this.taxes =orderHistory.getTaxes();
      this.total =orderHistory.getTotal();
      
      super.setUserComments( (String) orderHistory.getUserComments());
      super.setUserCancelComments( (String) orderHistory.getUserCancelComments());
      
      super.setStoreComments( (String) orderHistory.getStoreComments());
      super.setStoreCancelComments( (String) orderHistory.getStoreCancelComments());
   }   
   
   public OrderHistory(BasketInterface basketInterface, HashMap orderHistoryHashMap) throws MoneyException
   {
      super((String) orderHistoryHashMap.get(StoreFrontData.getInstance().NAME),
          basketInterface,
      (String) orderHistoryHashMap.get(OrderData.ID),
      (String) orderHistoryHashMap.get(ShippingMethodData.NAME),
      (String) orderHistoryHashMap.get(EntryData.getInstance().SPECIAL),
      (String) orderHistoryHashMap.get(PaymentData.METHOD));
      
      this.shippingAddress = new StreetAddress(
      (String) orderHistoryHashMap.get(ShippingAddressData.NAME),
      (String) orderHistoryHashMap.get(ShippingAddressData.STREET),
      (String) orderHistoryHashMap.get(ShippingAddressData.CITY),
      (String) orderHistoryHashMap.get(ShippingAddressData.STATE),
      (String) orderHistoryHashMap.get(ShippingAddressData.CODE),
      (String) orderHistoryHashMap.get(ShippingAddressData.COUNTRY));
      
      this.billingAddress = new StreetAddress(
      (String) orderHistoryHashMap.get(BillingAddressData.NAME),
      (String) orderHistoryHashMap.get(BillingAddressData.STREET),
      (String) orderHistoryHashMap.get(BillingAddressData.CITY),
      (String) orderHistoryHashMap.get(BillingAddressData.STATE),
      (String) orderHistoryHashMap.get(BillingAddressData.CODE),
      (String) orderHistoryHashMap.get(BillingAddressData.COUNTRY));
      
      this.payment = new Payment(orderHistoryHashMap);        
      
      this.userName = (String) orderHistoryHashMap.get(UserData.USERNAME);            
      
      this.orderDate = (String) orderHistoryHashMap.get(OrderHistoryData.ORDERDATE);
      this.shipDate = (String) orderHistoryHashMap.get(OrderHistoryData.SHIPPEDDATE);
      this.transDate = (String) orderHistoryHashMap.get(OrderHistoryData.TRANSDATE);
      this.cancelDate = (String) orderHistoryHashMap.get(OrderHistoryData.CANCELDATE);
      this.status = (String) orderHistoryHashMap.get(OrderHistoryData.STATUS);      
      this.subTotal = new Money((String) orderHistoryHashMap.get(OrderHistoryData.SUBTOTAL));
      this.total = new Money((String) orderHistoryHashMap.get(OrderHistoryData.TOTAL));
      this.shippingCost = new Money((String) orderHistoryHashMap.get(OrderHistoryData.SHIPPINGCOST));
      this.taxes = new Money((String) orderHistoryHashMap.get(OrderHistoryData.TAX));      
      
      super.setUserComments( (String) orderHistoryHashMap.get(OrderData.CUSTOMERCOMMENT));
      super.setUserCancelComments( (String) orderHistoryHashMap.get(OrderData.CUSTOMERCANCELCOMMENT));
      
      super.setStoreComments( (String) orderHistoryHashMap.get(OrderData.STORECOMMENT));
      super.setStoreCancelComments( (String) orderHistoryHashMap.get(OrderData.STORECANCELCOMMENT));
   }
      
   public void setUserName(String value)
   {
      this.userName = value;
   }
 
   
   public void setDefaultShippingAddress(StreetAddress shippingAddress)
   {
      this.shippingAddress= shippingAddress;
   }
   
   public void setBillingAddress(StreetAddress billingAddress)
   {
      this.billingAddress= billingAddress;
   }
   
   public void setPaymentInfo(Payment payment)
   {
      this.payment = payment;
   }
   
   public void setShipDate(String value)
   {
      this.shipDate = value;
   }
   
   public void setOrderDate(String value)
   {
      this.orderDate = value;
   }
   
   public void setTransDate(String value)
   {
      this.transDate = value;
   }
   
   public void setCancelDate(String value)
   {
      this.cancelDate = value;
   }
   
   public void setStatus(String value)
   {
      this.status = value;
   }
   
   public void setSubTotal(Money value)
   {
      this.subTotal = value;
   }
   
   public void setShippingCost(Money value)
   {
      this.shippingCost = value;
   }
   
   public void setTaxes(Money value)
   {
      this.taxes = value;
   }
   
   public void setTotal(Money value)
   {
      this.total = value;
   }
   
   public String getUserName()
   {
      return this.userName;
   }
   
   public StreetAddress getShippingAddress()
   {
      return this.shippingAddress;
   }
   
   public StreetAddress getBillingAddress()
   {
      return this.billingAddress;
   }
   
   public Payment getPaymentInfo()
   {
      return this.payment;
   }
      
   public String getShipDate()
   {
      return this.shipDate;
   }
   
   public String getOrderDate()
   {
      return this.orderDate;
   }
   
   public String getTransDate()
   {
      return this.transDate;
   }
   
   public String getCancelDate()
   {
      return this.cancelDate;
   }
   
   public String getStatus()
   {
      return this.status;
   }
   
   public Money getSubTotal()
   {
      return this.subTotal;
   }
   
   public Money getShippingCost()
   {
      return this.shippingCost;
   }
   
   public Money getTaxes()
   {
      return this.taxes;
   }
   
   public Money getTotal()
   {
      return this.total;
   }

   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();
      
      hashMap.put(UserData.USERNAME,this.userName);
      
      hashMap.put(OrderHistoryData.ORDERDATE,this.orderDate);
      hashMap.put(OrderHistoryData.SHIPPEDDATE,this.shipDate);
      hashMap.put(OrderHistoryData.TRANSDATE,this.transDate);
      hashMap.put(OrderHistoryData.CANCELDATE,this.cancelDate);

      //Long.parseLong(
      hashMap.put(OrderHistoryData.ORDERDATEFORMATTED,new Date(new Long(this.orderDate).longValue()).toString());
      hashMap.put(OrderHistoryData.SHIPPEDDATEFORMATTED,new Date(new Long(this.shipDate).longValue()).toString());
      hashMap.put(OrderHistoryData.TRANSDATEFORMATTED,new Date(new Long(this.transDate).longValue()).toString());
      hashMap.put(OrderHistoryData.CANCELDATEFORMATTED,new Date(new Long(this.cancelDate).longValue()).toString());
      
      hashMap.put(OrderHistoryData.STATUS,this.status);
      hashMap.put(OrderHistoryData.SUBTOTAL,this.subTotal.toString());
      hashMap.put(OrderHistoryData.TOTAL,this.total.toString());
      hashMap.put(OrderHistoryData.SHIPPINGCOST,this.shippingCost.toString());
      hashMap.put(OrderHistoryData.TAX,this.taxes.toString());
  
      return hashMap;
   }
   
   /*
   public Vector toVector()
   {
      return new Vector();
   }
   */
   
   public Node toXmlNode(Document document) throws Exception
   {      
      HashMap hashMap = this.toHashMap();
      Set keySet = hashMap.keySet();
      Object[] keyArray = keySet.toArray();
      int size = keyArray.length;
      
      Node node = document.createElement(OrderHistoryData.ORDERHISTORY);
      
      node.appendChild(super.toXmlNode(document));
      
      Node billingAddressNode = document.createElement(BillingAddressData.BILLINGADDRESS);
      billingAddressNode.appendChild(this.billingAddress.toXmlNode(document));

      Node shippingAddressNode = document.createElement(ShippingAddressData.SHIPPINGADDRESS);
      shippingAddressNode.appendChild(this.shippingAddress.toXmlNode(document));

      node.appendChild(billingAddressNode);

      node.appendChild(shippingAddressNode);
      
      node.appendChild(this.payment.toXmlNode(document));

      for (int i = 0; i < size; i++)
      {
         String name = (String) keyArray[i];
         String value = (String) hashMap.get(name);
         
         value = StringUtil.getInstance().getInstance(value);
         
         node.appendChild(ModDomHelper.createNameValueNodes(document, name, value));
      }
      
      return node;
   }

}
