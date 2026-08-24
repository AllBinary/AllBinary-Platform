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
public class LayerPlacementType
{    
   private int type;

   @JsConstructor
   LayerPlacementType(int type)
   {
      this.type = type;
   }

   @JsMethod
   public int getType()
   {
      return this.type;
   }

   @JsMethod
   protected void setType(int type)
   {
      this.type = type;
   }
}