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
package org.allbinary.game.layer.pickup;

import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.game.view.TileLayerPositionIntoViewPosition;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.view.event.ViewPositionEventHandler;

public class RaceTrackPickupLayer extends PickupLayer
{
    private static final String NAME = "RaceTrackPickupLayer";

   public RaceTrackPickupLayer()
      throws Exception
   {
      super(NAME, new TileLayerPositionIntoViewPosition());

      //this.setVisible(false);
   }

   private final ViewPositionEventHandler viewPositionEventHandler = 
       ViewPositionEventHandler.getInstance();
   
   public void setTiledLayer(AllBinaryTiledLayer tiledLayer)
   {
      TileLayerPositionIntoViewPosition viewPosition =
         (TileLayerPositionIntoViewPosition) this.getViewPosition();

      viewPosition.setTiledLayer(tiledLayer);
      
      viewPositionEventHandler.addListener((AllBinaryLayer) this);
   }
   
   public void setDestroyed(boolean destroyed)
   {
       super.setDestroyed(destroyed);
       
       if(this.isDestroyed())
       {
           viewPositionEventHandler.removeListener(this);
       }
   }
   
   /*
   public void paint(Graphics graphics)
   {
   super.paint(graphics);
   
   graphics.setColor(BasicColor.AQUA.intValue());
   graphics.drawArc(x - 3, y - 3, width + 6, height + 6, 0, Angle.THREE_SIXTY);
   }
    */
}
