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
