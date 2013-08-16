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
package allbinary.animation;

import allbinary.graphics.color.BasicColor;

/**
 *
 * @author user
 */
public class TimedVectorAnimationFactory implements VectorAnimationFactoryInterface {

   public VectorAnimation getInstance(int[][][] points, BasicColor basicColor)
   {
      return new TimedVectorAnimation(points, basicColor);
   }
}
