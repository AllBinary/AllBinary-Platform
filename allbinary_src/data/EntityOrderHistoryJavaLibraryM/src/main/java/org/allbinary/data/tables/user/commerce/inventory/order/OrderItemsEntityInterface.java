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

import java.util.Vector;

import org.allbinary.business.user.commerce.inventory.basket.BasketReview;
import org.allbinary.business.user.commerce.inventory.order.OrderInterface;
import org.allbinary.data.tables.BasicDataTableInterface;

public interface OrderItemsEntityInterface extends BasicDataTableInterface
{
   public void setStatus(String orderId,String groupId,String status);
   
   public boolean isEverythingShipped(String orderId);
   
   public BasketReview getBasketReview(String orderId);
   
   //public void delete(String value);
   
   public void insert(String userName,OrderInterface order);
   
   public void insert(Vector values);

   //public void update(HashMap updatedValues);
}
