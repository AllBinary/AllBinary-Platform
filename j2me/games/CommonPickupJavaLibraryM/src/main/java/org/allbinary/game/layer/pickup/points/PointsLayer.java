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
package org.allbinary.game.layer.pickup.points;

import javax.microedition.lcdui.Graphics;

import allbinary.game.score.ScoreableInterface;
import allbinary.graphics.PointFactory;
import allbinary.graphics.Rectangle;
import allbinary.layer.AllBinaryLayer;
import allbinary.view.ViewPosition;

public class PointsLayer extends AllBinaryLayer
{
	private final int points;
	
   public PointsLayer(int points)
      throws Exception
   {
      super(new Rectangle(PointFactory.getInstance().ZERO_ZERO, 0, 0), new ViewPosition());
      
      this.points = points;
   }
   
   public void paint(Graphics graphics)
   {
   }
   
   public void visit(ScoreableInterface scoreableInterface)
   {
	   //PreLogUtil.put("Add Points: " + points, this, "visit");
	   
	   scoreableInterface.addPoints(this.points);
   }
}
