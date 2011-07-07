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
package allbinary.graphics.color;

import org.allbinary.util.CircularIndexUtil;

public class BasicColorArrayIndexer {
   
   private BasicColor[] basicColorArray;

   private CircularIndexUtil circularIndexUtil;

   public BasicColorArrayIndexer(BasicColor[] basicColorArray)
   {
      this.basicColorArray = basicColorArray;
      
      this.circularIndexUtil = CircularIndexUtil.getInstance(this.basicColorArray.length); 
   }
   
   public void next()
   {
       this.circularIndexUtil.next();
   }
   
   public BasicColor get()
   {
      return this.basicColorArray[this.circularIndexUtil.getIndex()];
   }
   
   public BasicColor[] getBasicColorArray()
   {
      return basicColorArray;
   }
   
}
