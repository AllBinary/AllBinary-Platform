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
package org.allbinary.layer.relationship;

import jsinterop.annotations.JsType;

import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.RelativeRelationship;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.util.BasicArrayList;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class RelativeLayerRelationship 
   extends RelativeRelationship
{
   private AllBinaryLayer layer = AllBinaryLayer.NULL_ALLBINARY_LAYER;
   
   @JsConstructor
   public RelativeLayerRelationship(final AllBinaryLayer layer, final GPoint point, final BasicArrayList typesAllowedList)
   {
      super(point, typesAllowedList);
      
      this.layer = layer;
   }
   
   @Override
   @JsMethod
   public int getX()
   {
      return this.layer.getXP() + super.getX();
   }
   
   @Override
   @JsMethod
   public int getY()
   {
      return this.layer.getYP() + super.getY();
   }

   @Override
   @JsMethod
   public int getZ()
   {
      return this.layer.getZP() + super.getZ();
   }
   
   /*
   public Angle[] getAngleArray()
   {
      return angleArray;
   } 
    */
      
}
