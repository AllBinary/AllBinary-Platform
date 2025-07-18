/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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

package org.allbinary.game.view;

import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.layer.Layer;
import org.allbinary.layer.NullLayer;
import org.allbinary.view.ViewPosition;

/**
 *
 * @author user
 */
public class TileLayerPositionIntoViewPosition extends ViewPosition
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   private Layer tiledLayer = null; // = NullLayer.getInstance();
   
   public TileLayerPositionIntoViewPosition()
   {
      //logUtil.put("getRelativePositionX: " + this.getRelativePositionX(), this, "getViewPositionX");
      //logUtil.put("tiledLayer.getX(): " + tiledLayer.getX(), this, "getViewPositionX");
      //logUtil.put("X: " + x, this, "getViewPositionX");
   }

   public int getX()
   {
      return super.getX() - this.tiledLayer.getX();
   }

   public int getY()
   {
      return super.getY() - this.tiledLayer.getY();
   }

   public int getZ()
   {
      return super.getZ() - this.tiledLayer.getZ();
   }

   public void setTiledLayer(AllBinaryTiledLayer tiledLayer)
   {
      this.tiledLayer = tiledLayer;
   }
}
