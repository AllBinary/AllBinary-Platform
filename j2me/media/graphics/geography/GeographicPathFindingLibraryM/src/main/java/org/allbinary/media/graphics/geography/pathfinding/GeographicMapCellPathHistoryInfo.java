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
package org.allbinary.media.graphics.geography.pathfinding;

import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;

/**
 *
 * @author user
 */
public class GeographicMapCellPathHistoryInfo {

   private GeographicMapCellPosition previousGeographicMapCellPosition;
   private GeographicMapCellPosition previousOnPathGeographicMapCellPosition;
   private GeographicMapCellPosition nextUnvisitedOnPathGeographicMapCellPosition;
   private GeographicMapCellPosition nextOnPathGeographicMapCellPosition;
   private GeographicMapCellPosition nextChosenOnPathGeographicMapCellPosition;

   public GeographicMapCellPosition getPreviousGeographicMapCellPosition()
   {
      return previousGeographicMapCellPosition;
   }

   public void setPreviousGeographicMapCellPosition(GeographicMapCellPosition previousGeographicMapCellPosition)
   {
      this.previousGeographicMapCellPosition = previousGeographicMapCellPosition;
   }

   public GeographicMapCellPosition getPreviousOnPathGeographicMapCellPosition()
   {
      return previousOnPathGeographicMapCellPosition;
   }

   public void setPreviousOnPathGeographicMapCellPosition(GeographicMapCellPosition previousOnPathGeographicMapCellPosition)
   {
      this.previousOnPathGeographicMapCellPosition = previousOnPathGeographicMapCellPosition;
   }

   public GeographicMapCellPosition getNextUnvisitedOnPathGeographicMapCellPosition()
   {
      return nextUnvisitedOnPathGeographicMapCellPosition;
   }

   public void setNextUnvisitedOnPathGeographicMapCellPosition(GeographicMapCellPosition nextUnvisitedOnPathGeographicMapCellPosition)
   {
      this.nextUnvisitedOnPathGeographicMapCellPosition = nextUnvisitedOnPathGeographicMapCellPosition;
   }

   public GeographicMapCellPosition getNextOnPathGeographicMapCellPosition()
   {
      return nextOnPathGeographicMapCellPosition;
   }

   public void setNextOnPathGeographicMapCellPosition(GeographicMapCellPosition nextOnPathGeographicMapCellPosition)
   {
      this.nextOnPathGeographicMapCellPosition = nextOnPathGeographicMapCellPosition;
   }

   public GeographicMapCellPosition getNextChosenOnPathGeographicMapCellPosition()
   {
      return nextChosenOnPathGeographicMapCellPosition;
   }

   public void setNextChosenOnPathGeographicMapCellPosition(GeographicMapCellPosition nextChosenOnPathGeographicMapCellPosition)
   {
      this.nextChosenOnPathGeographicMapCellPosition = nextChosenOnPathGeographicMapCellPosition;
   }

}
