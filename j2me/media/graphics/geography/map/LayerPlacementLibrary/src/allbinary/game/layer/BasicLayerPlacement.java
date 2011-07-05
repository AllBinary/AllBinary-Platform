/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: Sep 29, 2007, 7:09:17 AM
 *
 *
 * Modified By         When       ?
 *
 */

package allbinary.game.layer;

public class BasicLayerPlacement {

   private LayerPlacementType layerType; 
   private int width; 
   private int height;
   
   public BasicLayerPlacement(LayerPlacementType layerType, int width, int height)
   {
      this.layerType = layerType;
      this.width = width;
      this.height = height;
   }
   
   public int getWidth()
   {
      return this.width;
   }

   public int getHeight()
   {
      return this.height;
   }

   public LayerPlacementType getLayerType()
   {
      return layerType;
   }

   protected void setLayerType(LayerPlacementType layerType)
   {
      this.layerType = layerType;
   }
}
