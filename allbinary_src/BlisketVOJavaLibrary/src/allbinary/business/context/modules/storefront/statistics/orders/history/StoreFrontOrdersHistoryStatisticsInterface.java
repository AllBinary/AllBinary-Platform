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
package allbinary.business.context.modules.storefront.statistics.orders.history;

import allbinary.business.user.commerce.money.Money;
import allbinary.data.tables.TableMappingInterface;

public interface StoreFrontOrdersHistoryStatisticsInterface extends TableMappingInterface
{
   public Long getNumberOfOrders();
   
   public Money getSubTotal();
   public Money getShippingCost();
   public Money getTaxes();
   public Money getTotal();   
}
