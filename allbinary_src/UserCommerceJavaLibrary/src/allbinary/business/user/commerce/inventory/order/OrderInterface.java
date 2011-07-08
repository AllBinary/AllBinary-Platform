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
package allbinary.business.user.commerce.inventory.order;

import allbinary.business.user.commerce.inventory.basket.BasketInterface;

public interface OrderInterface
{
   /*
   public OrderInterface getOrder(OrderReview orderReview);
   */
   //public void setBasket(BasketInterface basket);
   
   public void setShippingMethod(String shippingType);
   
   public void setPaymentMethod(String value);
   
   public void setSpecial(String special);
   
   public void setStoreName(String value);

   public void setUserComments(String value);

   public void setUserCancelComments(String value);

   public void setStoreComments(String value);

   public void setStoreCancelComments(String value);
   
   public BasketInterface getBasket();
   
   public String getShippingMethod();
         
   public String getPaymentMethod();
   
   public String getSpecial();   

   public String getId();
   
   public String getStoreName();
      
   public String getUserComments();

   public String getUserCancelComments();

   public String getStoreComments();

   public String getStoreCancelComments();

   //public String process(String userName) throws Exception;

}
