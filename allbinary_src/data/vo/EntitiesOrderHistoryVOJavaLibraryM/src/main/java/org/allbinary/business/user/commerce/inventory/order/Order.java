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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.entry.EntryData;
import org.allbinary.business.user.commerce.inventory.basket.BasketInterface;
import org.allbinary.business.user.commerce.money.payment.PaymentData;
import org.allbinary.business.user.commerce.shipping.ShippingMethodData;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class Order implements OrderInterface, Serializable
{   
   private final BasketInterface basket;
   private String paymentMethod;   
   private String shippingType;
   private String storeName;
   private String special;
   private String id;
     
   private String userComment;
   private String userCancelComment;
   private String storeComment;
   private String storeCancelComment;

   public Order(BasketInterface basket)
   {
       this.basket = basket;
       id = null;
       shippingType = StringUtil.getInstance().EMPTY_STRING;
   }

   public Order(String storeName, BasketInterface basket, String id, String shippingType, String special, String paymentMethod)
   {
      this.storeName = storeName;
      this.basket = basket;
      this.id = id;
      this.shippingType = shippingType;
      this.special = special;
      this.paymentMethod = paymentMethod;
   }
  /*
   public OrderInterface getOrder(OrderReview orderReview)
   {
      return null;
   }
   */
   
   public Boolean isIdValid()
   {
       if(this.getId() == null || this.getId().compareTo(StringUtil.getInstance().EMPTY_STRING)==0 ||
         this.getId().length() > OrderHistoryData.MAXIDLEN || 
         !StringValidationUtil.getInstance().isNumber(this.getId()))
      {
         return Boolean.FALSE;
      }
      return Boolean.TRUE;
   }

   public Boolean isValid()
   {
      return Boolean.TRUE;
   }

   public String getIdValidationInfo()
   {
	   final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;

	   if(this.getId() == null || this.getId().compareTo(EMPTY_STRING)==0 ||
         this.getId().length() > OrderHistoryData.MAXIDLEN || 
         !StringValidationUtil.getInstance().isNumber(this.getId()))
	   {
		   return "Please enter a valid order Id < " + OrderHistoryData.MAXIDLEN + " digits in length.<br>";
       }

	   return EMPTY_STRING;
   }
   
   /*
   public void setBasket(BasketInterface basket)
   {
      this.basket = basket;
   }
   */
   
   public void setShippingMethod(String shippingType)
   {
      this.shippingType = shippingType;      
   }
   
   public void setPaymentMethod(String value)
   {
      this.paymentMethod = value;
   }

   public void setSpecial(String special)
   {
      this.special = special;
   }

   public void setId(String value)
   {
      this.id = value;
   }

   public void setStoreName(String value)
   {
      this.storeName = value;
   }
   
   public void setUserComments(String value)
   {
      this.userComment = value;
   }

   public void setUserCancelComments(String value)
   {
      this.userCancelComment = value;
   }

   public void setStoreComments(String value)
   {
      this.storeComment = value;
   }

   public void setStoreCancelComments(String value)
   {
      this.storeCancelComment = value;
   }

   public BasketInterface getBasket()
   {
      return this.basket;
   }
   
   public String getShippingMethod()
   {
      return this.shippingType;
   }
   
   public String getPaymentMethod()
   {
      return this.paymentMethod;
   }

   public String getSpecial()
   {
      return this.special;
   }

   public String getId()
   {
      return this.id;
   }

   public String getStoreName()
   {
      return this.storeName;
   }
   
   public String getUserComments()
   {
      return this.userComment;
   }

   public String getUserCancelComments()
   {
      return this.userCancelComment;
   }

   public String getStoreComments()
   {
      return this.storeComment;
   }

   public String getStoreCancelComments()
   {
      return this.storeCancelComment;
   }     
   
   public String reviewOrder(String paymentMethod)
   {
      return "Create your own Order.reviewOrder";
   }

   public String viewOrder(String paymentMethod)
   {
      return "Create your own Order.viewOrder";
   }

   public String emailOrder(String userName, String paymentMethod, StoreFrontInterface storeFrontInterface)
   {
      return "Create your own Order.emailOrder";
   }

   public HashMap toHashMapOrder()
   {
      HashMap hashMap = new HashMap();
      hashMap.put(PaymentData.METHOD,this.paymentMethod);
      hashMap.put(ShippingMethodData.NAME,this.shippingType);
      hashMap.put(StoreFrontData.getInstance().NAME,this.storeName);
      hashMap.put(EntryData.getInstance().SPECIAL,this.special);
      hashMap.put(OrderData.ID,this.id);
      hashMap.put(OrderData.CUSTOMERCOMMENT,this.userComment);
      hashMap.put(OrderData.CUSTOMERCANCELCOMMENT,this.userCancelComment);
      hashMap.put(OrderData.STORECOMMENT,this.storeComment);
      hashMap.put(OrderData.STORECANCELCOMMENT,this.storeCancelComment);
      return hashMap;
   }
   
   public Node toXmlNode(Document document) throws Exception
   {            
      HashMap hashMap = this.toHashMapOrder();
      Set keySet = hashMap.keySet();
      Object[] keyArray = keySet.toArray();
      int size = keyArray.length;
      
      Node node = document.createElement(OrderData.ORDER);
      
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
