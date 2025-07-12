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
package org.allbinary.game.ai;

import javax.microedition.lcdui.Graphics;

import org.allbinary.bounds.BoundsVisitorInterface;
import org.allbinary.bounds.LayerBounds;
import org.allbinary.game.input.GameInput;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;

public class BoundBounceAI extends BasicAI
{
   protected int currentRelativeAngle = 0;
   //private int currentSpeed;
   
   private LayerBounds layerBounds;

   private BoundsVisitorInterface boundsVisitorInterface;

    public BoundBounceAI(AllBinaryLayer ownerLayerInterface, GameInput gameInput,
       LayerBounds layerBounds, BoundsVisitorInterface boundsVisitorInterface)
            throws Exception
    {
      super(ownerLayerInterface, gameInput);

      this.layerBounds = layerBounds;

      this.boundsVisitorInterface = boundsVisitorInterface;
    }

   public void paint(Graphics graphics)
   {
      graphics.drawRect(this.layerBounds.getRectangle().getPoint().getX(),
              this.layerBounds.getRectangle().getPoint().getY(),
              this.layerBounds.getRectangle().getWidth(),
              this.layerBounds.getRectangle().getHeight());
   }
   
   public void processAI(AllBinaryLayerManager allBinaryLayerManager) 
           throws Exception
   {      
      this.layerBounds.visit(this.boundsVisitorInterface);
   }
   
}
