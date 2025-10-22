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

import org.allbinary.graphics.color.BasicColor;

public class RaceTrackInfo
{
   private Integer id;
   private String name;

   private RaceTrackFrictionProperties raceTrackFrictionProperties;
   
   private BasicColor foregroundBasicColor;
   private BasicColor backgroundBasicColor;
   
   private int qualifyTime;
   private int worstContinuePosition;
   private int totalLaps;

   public RaceTrackInfo(
      final Integer id, final String name, final RaceTrackFrictionProperties raceTrackFrictionProperties,
      final BasicColor foregroundBasicColor, final BasicColor backgroundBasicColor,
      final int qualifyTime, final int worstContinuePosition, final int totalLaps)
   {
      this.id = id;
      this.name = name;
      
      this.raceTrackFrictionProperties = raceTrackFrictionProperties;

      this.backgroundBasicColor = backgroundBasicColor;
      this.foregroundBasicColor = foregroundBasicColor;
      
      this.qualifyTime = qualifyTime;
      this.worstContinuePosition = worstContinuePosition;
      this.totalLaps = totalLaps;
   }

   public Integer getId()
   {
      return id;
   }

   private void setId(Integer id)
   {
      this.id = id;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public int getQualifyTime()
   {
      return qualifyTime;
   }

   public void setQualifyTime(int qualifyTime)
   {
      this.qualifyTime = qualifyTime;
   }

   public int getWorstContinuePosition()
   {
      return worstContinuePosition;
   }

   public void setWorstContinuePosition(int worstContinuePosition)
   {
      this.worstContinuePosition = worstContinuePosition;
   }

   public int getTotalLaps()
   {
      return totalLaps;
   }

   public void setTotalLaps(int totalLaps)
   {
      this.totalLaps = totalLaps;
   }

   public BasicColor getForegroundBasicColor()
   {
      return foregroundBasicColor;
   }

   public void setForegroundBasicColor(BasicColor foregroundBasicColor)
   {
      this.foregroundBasicColor = foregroundBasicColor;
   }

   public BasicColor getBackgroundBasicColor()
   {
      return backgroundBasicColor;
   }

   public void setBackgroundBasicColor(BasicColor backgroundBasicColor)
   {
      this.backgroundBasicColor = backgroundBasicColor;
   }

   public RaceTrackFrictionProperties getRaceTrackFrictionProperties()
   {
      return raceTrackFrictionProperties;
   }

   public void setRaceTrackFrictionProperties(RaceTrackFrictionProperties raceTrackFrictionProperties)
   {
      this.raceTrackFrictionProperties = raceTrackFrictionProperties;
   }

}
