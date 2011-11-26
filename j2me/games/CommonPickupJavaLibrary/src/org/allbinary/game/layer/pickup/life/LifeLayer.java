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

package org.allbinary.game.layer.pickup.life;

import javax.microedition.lcdui.Graphics;

import allbinary.game.life.Life;
import allbinary.game.life.LifeVisitorInterface;
import allbinary.graphics.PointFactory;
import allbinary.graphics.Rectangle;
import allbinary.layer.AllBinaryLayer;
import allbinary.view.ViewPosition;

public class LifeLayer extends AllBinaryLayer
        implements LifeVisitorInterface
{
   public LifeLayer()
      throws Exception
   {
      super(new Rectangle(PointFactory.getInstance().ZERO_ZERO, 0, 0), new ViewPosition());
   }
   
   public void paint(Graphics graphics)
   {
   }
   
   public void visit(Life lifeInterface)
   {
      lifeInterface.add((short) 1);
   }
}
