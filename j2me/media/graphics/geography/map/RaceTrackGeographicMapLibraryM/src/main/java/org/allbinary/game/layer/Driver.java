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
package org.allbinary.game.layer;

/**
 *
 * @author user
 */
public class Driver {

   //driver intelligence
   private Integer turnSpeed;
   private Integer trackPathSelectionSkill;

   private Aggression aggression;
   
   public Driver(
      Integer turnSpeed, 
      Integer trackPathSelectionSkill, 
      Aggression aggression)
   {
      this.setTurnSpeed(turnSpeed);
      this.setTrackPathSelectionSkill(trackPathSelectionSkill);
      this.setAggression(aggression);
   }

   public Integer getTurnSpeed()
   {
      return turnSpeed;
   }

   public void setTurnSpeed(Integer turnSpeed)
   {
      this.turnSpeed = turnSpeed;
   }

   public Integer getTrackPathSelectionSkill()
   {
      return trackPathSelectionSkill;
   }

   public void setTrackPathSelectionSkill(Integer trackPathSelectionSkill)
   {
      this.trackPathSelectionSkill = trackPathSelectionSkill;
   }

   public Aggression getAggression()
   {
      return aggression;
   }

   public void setAggression(Aggression aggression)
   {
      this.aggression = aggression;
   }
   
}
