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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.health.HealthInterface;
import org.allbinary.game.health.HealthVisitorInterface;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.view.ViewPosition;

public class HealLayer extends AllBinaryLayer
        implements HealthVisitorInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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
          logUtil.put("Heal attempt on dead: Does not currently occur", this, "visit");
      }
   }
}
