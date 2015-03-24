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
package org.allbinary.logic.math.vector;

import org.allbinary.logic.math.BasicDecimal;

public class MathVector implements MathVectorInterface
{
   private BasicDecimal magnitudeBasicDecimal;
   private int direction;
   
   protected BasicDecimal resultBasicDecimal;
   
   public MathVector(
         BasicDecimal magnitudeBasicDecimal, int direction)
   {
      this.magnitudeBasicDecimal = magnitudeBasicDecimal;
      this.direction = direction;
   }
   
   public BasicDecimal getMagnitude()
   {
      return this.magnitudeBasicDecimal;
   }

   public int getDirection()
   {
      return this.direction;
   }

   /*
   public void setMagnitude(BasicDecimal magnitudeBasicDecimal)
   {
      this.magnitudeBasicDecimal = magnitudeBasicDecimal;
   }

   public void setDirection(int direction)
   {
      this.direction = direction;
   }
   */

   public BasicDecimal getResult()
   {
      return resultBasicDecimal;
   }
}
