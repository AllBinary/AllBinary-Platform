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
package allbinary.media.graphics.geography.map;

public class GeographicMapCellType
{   
   private Integer typeInteger;

   protected GeographicMapCellType(Integer typeInteger)
   {
      this.typeInteger = typeInteger;
      GeographicMapCellTypeFactory.getInstance().getGeographicMapCellTypeArray()[typeInteger.intValue()] = this;
   }

   public Integer getType()
   {
      return this.typeInteger;
   }
   
   public String toString()
   {
      return this.typeInteger.toString();
   }
}
