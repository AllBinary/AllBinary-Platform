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
package allbinary.business.user.commerce.inventory.basket;

public class BasketData
{
   private BasketData()
   {
   }
   
   public static final String BASKET = "BASKET";
   
   public static final String NAME = "NAME";
   
   public static final String BASKETREVIEW = "BASKET_REVIEW";
   
   public static final String TOTALWEIGHT = "BASKET_TOTALWEIGTH";
   public static final String ITEMTOTAL = "BASKET_ITEMTOTAL";
   public static final String ITEMTOTALINBASKET = "BASKET_ITEMTOTALINBASKET";
   public static final String SUBTOTAL = "BASKET_SUBTOTAL";
   
   //Basket Commands
   public static final String ISEMPTY = "BASKET_ISEMPTY";
   public static final String INSERT = "BASKET_INSERT";
   public static final String DELETE = "BASKET_DELETE";
   public static final String ADJUST = "BASKET_ADJUST";
}
