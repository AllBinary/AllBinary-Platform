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

import javax.microedition.lcdui.Graphics;

import allbinary.graphics.color.BasicColor;

/**
 *
 * @author user
 */
public class AdjustedVectorAnimation extends VectorAnimation
{
   private int dx;
   private int dy;
   
   public AdjustedVectorAnimation(int currentPoints[][][], BasicColor basicColor, int dx, int dy) {
      super(currentPoints, basicColor);

      this.dx = dx;
      this.dy = dy;
	}

	public AdjustedVectorAnimation(int currentPoints[][], BasicColor basicColor, int dx, int dy) {
      super(currentPoints, basicColor);
      
      this.dx = dx;
      this.dy = dy;
	}
   
	public void paint(Graphics graphics, int x, int y) {
	
      super.paint(graphics, x + this.dx, y + this.dy);
	}
}
