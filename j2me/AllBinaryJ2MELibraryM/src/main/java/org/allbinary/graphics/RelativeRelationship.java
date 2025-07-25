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

import org.allbinary.util.BasicArrayList;

/**
 *
 * @author user
 */
public class RelativeRelationship
{
   private BasicArrayList typesAllowedList;
   
   private int x;
   private int y;
   private int z;

   //private Angle[] angleArray;

   public RelativeRelationship(GPoint point, BasicArrayList typesAllowedList)
   {
      this.x = point.getX();
      this.y = point.getY();
      this.z = point.getZ();
      
      this.typesAllowedList = typesAllowedList;
   }
   
   public int getX()
   {
      return this.x;
   }
   
   public int getY()
   {
      return this.y;
   }

   public int getZ()
   {
      return this.z;
   }
   
   public BasicArrayList getTypesAllowedList()
   {
      return typesAllowedList;
   }

   public void setTypesAllowedList(BasicArrayList typesAllowedList)
   {
      this.typesAllowedList = typesAllowedList;
   }
   
}
