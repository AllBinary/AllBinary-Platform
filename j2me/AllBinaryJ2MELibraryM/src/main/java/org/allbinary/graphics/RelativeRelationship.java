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
package org.allbinary.graphics;

import jsinterop.annotations.JsType;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

/**
 *
 * @author user
 */

@JsType
public class RelativeRelationship
{
    @JsProperty
    public static final RelativeRelationship NULL_RELATIVE_RELATIONSHIP = new RelativeRelationship(
        PointFactory.getInstance().ZERO_ZERO, BasicArrayListUtil.getInstance().getImmutableInstance());

   private BasicArrayList typesAllowedList;
   
   private int x;
   private int y;
   private int z;

   //private Angle[] angleArray;

   @JsConstructor
   public RelativeRelationship(GPoint point, BasicArrayList typesAllowedList)
   {
      this.x = point.getX();
      this.y = point.getY();
      this.z = point.getZ();
      
      this.typesAllowedList = typesAllowedList;
   }
   
   @JsMethod
   public int getX()
   {
      return this.x;
   }
   
   @JsMethod
   public int getY()
   {
      return this.y;
   }

   @JsMethod
   public int getZ()
   {
      return this.z;
   }
   
   @JsMethod
   public BasicArrayList getTypesAllowedList()
   {
      return this.typesAllowedList;
   }

   @JsMethod
   public void setTypesAllowedList(BasicArrayList typesAllowedList)
   {
      this.typesAllowedList = typesAllowedList;
   }
   
}
