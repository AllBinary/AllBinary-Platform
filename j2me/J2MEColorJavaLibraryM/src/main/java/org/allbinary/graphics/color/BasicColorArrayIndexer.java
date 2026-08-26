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
package org.allbinary.graphics.color;

import jsinterop.annotations.JsType;
import org.allbinary.util.CircularIndexUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;

@JsType
public class BasicColorArrayIndexer {
   
   private BasicColor[] basicColorArray;

   private CircularIndexUtil circularIndexUtil;

   @JsConstructor
   public BasicColorArrayIndexer(BasicColor[] basicColorArray)
   {
      this.basicColorArray = basicColorArray;
      
      this.circularIndexUtil = CircularIndexUtil.createInstance(this.basicColorArray.length);
   }
   
   @JsMethod
   public void next()
   {
       this.circularIndexUtil.next();
   }
   
   @JsMethod
   public BasicColor get()
   {
      return this.basicColorArray[this.circularIndexUtil.getIndex()];
   }
   
   @JsMethod
   public BasicColor[] getBasicColorArray()
   {
      return this.basicColorArray;
   }
   
}
