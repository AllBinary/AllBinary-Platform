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

package org.allbinary.game.layer.pickup.health;

import javax.microedition.lcdui.Graphics;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.health.HealthInterface;
import allbinary.game.health.HealthVisitorInterface;
import allbinary.graphics.PointFactory;
import allbinary.graphics.Rectangle;
import allbinary.layer.AllBinaryLayer;
import allbinary.view.ViewPosition;

public class HealLayer extends AllBinaryLayer
        implements HealthVisitorInterface
{
   public HealLayer()
      throws Exception
   {
      super(new Rectangle(PointFactory.getInstance().ZERO_ZERO, 0, 0), new ViewPosition());
   }
   
   public void paint(Graphics graphics)
   {
   }
   
   public void visit(HealthInterface healthInterface)
   {
       //If not alive then heal should fail
      if(healthInterface.isAlive())
      {
          healthInterface.heal();
      }
      else
      {
          LogUtil.put(LogFactory.getInstance("Heal attempt on dead: Does not currently occur", this, "visit"));
      }
   }
}
