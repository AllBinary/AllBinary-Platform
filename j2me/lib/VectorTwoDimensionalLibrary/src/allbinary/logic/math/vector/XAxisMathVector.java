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
package allbinary.logic.math.vector;

import org.allbinary.logic.math.BasicDecimal;

public class XAxisMathVector extends MathVector
{
   public XAxisMathVector(
      BasicDecimal magnitudeBasicDecimal, int direction)
   {
      super(magnitudeBasicDecimal, direction);
      
      this.calculate();
   }

   private void calculate()
   {
      long result = 
            YAxisMathVectorUtil.calculate(this.getMagnitude().getUnscaled(), this.getDirection());
      this.resultBasicDecimal = new BasicDecimal(result);
   }
}
