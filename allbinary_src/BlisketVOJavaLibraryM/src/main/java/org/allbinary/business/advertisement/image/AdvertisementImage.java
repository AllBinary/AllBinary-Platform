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
package org.allbinary.business.advertisement.image;

import org.allbinary.logic.control.contraints.size.TwoDimensionalConstraintInterface;

public class AdvertisementImage implements AdvertisementImageInterface
{
   private String imageFileName;
   private String imageUrl;
   private TwoDimensionalConstraintInterface constraint;

   public AdvertisementImage()
   {
   }
   
   public String getFileName()
   {
      return this.imageFileName;
   }
   
   public String getUrl()
   {
      return this.imageUrl;
   }
   
   public TwoDimensionalConstraintInterface getTwoDimensionalConstraint()
   {
      return this.constraint;
   }
}
