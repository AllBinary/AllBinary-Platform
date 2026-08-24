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

package org.allbinary.game.layer;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class BasicLayerPlacement {

   private LayerPlacementType layerType; 
   private int width; 
   private int height;
   
   @JsConstructor
   public BasicLayerPlacement(LayerPlacementType layerType, int width, int height)
   {
      this.layerType = layerType;
      this.width = width;
      this.height = height;
   }
   
   @JsMethod
   public int getWidth()
   {
      return this.width;
   }

   @JsMethod
   public int getHeight()
   {
      return this.height;
   }

   @JsMethod
   public LayerPlacementType getLayerType()
   {
      return this.layerType;
   }

   @JsMethod
   protected void setLayerType(LayerPlacementType layerType)
   {
      this.layerType = layerType;
   }
}
