/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: Sep 9, 2007, 1:52:32 AM
 *
 *
 * Modified By         When       ?
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
