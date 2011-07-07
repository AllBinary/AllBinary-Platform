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
package allbinary.graphics;

import org.allbinary.util.BasicArrayList;

/**
 *
 * @author user
 */
public class Relative2DRelationship extends RelativeRelationship
{
   private BasicArrayList typesAllowedList;
   
   protected int dx;
   protected int dy;
   //private Angle[] angleArray;

   public Relative2DRelationship(GPoint point, BasicArrayList typesAllowedList)
   {
      this.dx = point.getX();
      this.dy = point.getY();
      
      this.typesAllowedList = typesAllowedList;
   }
   
   public int getX()
   {
      return this.dx;
   }
   
   public int getY()
   {
      return this.dy;
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
