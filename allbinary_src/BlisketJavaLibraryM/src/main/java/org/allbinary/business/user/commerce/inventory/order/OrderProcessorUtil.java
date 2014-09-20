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

import org.allbinary.logic.basic.io.file.generators.OrderIdGenerator;
import org.allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntity;
import org.allbinary.data.tables.user.commerce.inventory.order.OrderItemsEntity;

/**
 *
 * @author user
 */
public class OrderProcessorUtil {

	private static final OrderProcessorUtil instance = new OrderProcessorUtil();
	
	public static OrderProcessorUtil getInstance() {
		return instance;
	} 
	
   private final String ORDER_PROCESSED = "Order Processed";
	
   public String process(String userName, Order order) throws Exception
   {
      OrderItemsEntity orderItems = new OrderItemsEntity();
      OrderHistoryEntity orderHistory = new OrderHistoryEntity();
      order.setId(new OrderIdGenerator().getNext());
      orderItems.insert(userName, order);
      orderHistory.insert(userName, order);

      return ORDER_PROCESSED;
   }
}
