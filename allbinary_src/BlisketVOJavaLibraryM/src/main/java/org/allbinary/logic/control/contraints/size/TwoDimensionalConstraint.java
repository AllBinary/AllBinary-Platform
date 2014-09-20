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
package org.allbinary.logic.control.contraints.size;

import org.w3c.dom.Node;

public class TwoDimensionalConstraint implements TwoDimensionalConstraintInterface
{
   private SizeConstraintInterface widthSizeConstraintInterface;
   private SizeConstraintInterface heightSizeConstraintInterface;
   
   public TwoDimensionalConstraint(Node node)
   {
      //this.heightSizeConstraintInterface = 
      //this.widthSizeConstraintInterface = 
   }
   
   public SizeConstraintInterface getHeight()
   {
      return this.heightSizeConstraintInterface;
   }
   
   public SizeConstraintInterface getWidth()
   {
      return this.widthSizeConstraintInterface;
   }

   public void setHeight(SizeConstraintInterface heightSizeConstraintInterface)
   {
      this.heightSizeConstraintInterface = heightSizeConstraintInterface;
   }
   
   public void setWidth(SizeConstraintInterface widthSizeConstraintInterface)
   {
      this.widthSizeConstraintInterface = widthSizeConstraintInterface;
   }
}
