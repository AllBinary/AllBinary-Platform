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
import org.allbinary.layer.Layer;
import org.allbinary.layer.NullLayer;
import org.allbinary.view.ViewPosition;

/**
 *
 * @author user
 */
public class StaticTileLayerIntoPositionViewPosition extends ViewPosition
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   private static Layer tiledLayer = NullLayer.getInstance();
   public static AllBinaryLayer layer = AllBinaryLayer.NULL_ALLBINARY_LAYER;
   
   public StaticTileLayerIntoPositionViewPosition()
   {
      //logUtil.put("getRelativePositionX: " + this.getRelativePositionX(), this, "getViewPositionX");
      //logUtil.put("tiledLayer.getXP(): " + tiledLayer.getXP(), this, "getViewPositionX");
      //logUtil.put("X: " + x, this, "getViewPositionX");
   }

   @Override
   public int getX()
   {
      return super.getX() - StaticTileLayerIntoPositionViewPosition.tiledLayer.getXP();
   }

   @Override
   public int getY()
   {
      return super.getY() - StaticTileLayerIntoPositionViewPosition.tiledLayer.getYP();
   }

   @Override
   public int getZ()
   {
      return super.getZ() - StaticTileLayerIntoPositionViewPosition.tiledLayer.getZP();
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
