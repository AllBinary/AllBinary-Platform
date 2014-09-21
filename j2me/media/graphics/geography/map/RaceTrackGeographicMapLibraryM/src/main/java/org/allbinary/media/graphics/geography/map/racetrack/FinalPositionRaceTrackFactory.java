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
package org.allbinary.media.graphics.geography.map.racetrack;

public class FinalPositionRaceTrackFactory
{
   public static final int FIRST_POSITION = 1;

   private static int index = FIRST_POSITION;
      
   private FinalPositionRaceTrackFactory()
   {
   }
   
   public synchronized static int getInstance()
   {
      return index++;
   }

   public synchronized static void reset()
   {
      index = FIRST_POSITION;
   }
}
