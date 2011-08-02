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
package allbinary.business.advertisement.product;

import allbinary.logic.control.contraints.size.TwoDimensionalConstraintInterface;

public class AdvertisementProduct implements AdvertisementProductInterface
{
   private String type;
   private String link;
   private int number;
   private TwoDimensionalConstraintInterface constraint;

   public AdvertisementProduct()
   {
   }
   
   public String getType()
   {
      return this.type;
   }
   
   public String getLink()
   {
      return this.link;
   }
   
   public int getNumber()
   {
      return this.number;
   }

   public TwoDimensionalConstraintInterface getTwoDimensionalConstraint()
   {
      return this.constraint;
   }
}
