/*
 *Copyright (c) 2007 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: Oct 7, 2007, 11:31:14 AM
 *
 *
 *Modified By         When       ?
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
