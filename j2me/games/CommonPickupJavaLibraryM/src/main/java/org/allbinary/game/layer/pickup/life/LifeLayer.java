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
package org.allbinary.game.layer.pickup.life;

import javax.microedition.lcdui.Graphics;

import org.allbinary.game.life.Life;
import org.allbinary.game.life.LifeVisitorInterface;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.view.ViewPosition;

public class LifeLayer extends AllBinaryLayer
        implements LifeVisitorInterface
{
   public LifeLayer()
      throws Exception
   {
      super(new Rectangle(PointFactory.getInstance().ZERO_ZERO, 0, 0), new ViewPosition());
   }
   
   @Override
   public void paint(Graphics graphics)
   {
   }
   
   @Override
   public void visit(Life lifeInterface)
   {
      lifeInterface.add((short) 1);
   }
}
