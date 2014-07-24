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
