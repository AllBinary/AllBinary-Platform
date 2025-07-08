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
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.view.ViewPosition;

/**
 *
 * @author user
 */
public class StaticTileLayerIntoPositionViewPosition extends ViewPosition
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   private static AllBinaryTiledLayer tiledLayer;
   public static AllBinaryLayer layer;
   
   public StaticTileLayerIntoPositionViewPosition()
   {
      //logUtil.put("getRelativePositionX: " + this.getRelativePositionX(), this, "getViewPositionX");
      //logUtil.put("tiledLayer.getX(): " + tiledLayer.getX(), this, "getViewPositionX");
      //logUtil.put("X: " + x, this, "getViewPositionX");
   }
      
   public int getX()
   {
      return super.getX() - StaticTileLayerIntoPositionViewPosition.tiledLayer.getX();
   }

   public int getY()
   {
      return super.getY() - StaticTileLayerIntoPositionViewPosition.tiledLayer.getY();
   }

   public int getZ()
   {
      return super.getZ() - StaticTileLayerIntoPositionViewPosition.tiledLayer.getZ();
   }
   
//   public AllBinaryTiledLayer getTiledLayer()
//   {
//      return tiledLayer;
//   }

   public static void setTiledLayer(AllBinaryTiledLayer tiledLayer)
   {
      StaticTileLayerIntoPositionViewPosition.tiledLayer = tiledLayer;
   }
}
