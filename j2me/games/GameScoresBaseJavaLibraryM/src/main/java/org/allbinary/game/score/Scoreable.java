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
package org.allbinary.game.score;

import org.allbinary.logic.math.SmallIntegerSingletonFactory;

public class Scoreable implements ScoreableInterface
{

   public static Integer ID = SmallIntegerSingletonFactory.getInstance().getInstance(3);

   private int points;
   
   public Scoreable(int points)
   {
      this.points = points;
   }

   @Override
   public void addPoints(int points)
   {
      this.points += points;
   }

   @Override   
   public void removePoints(int points)
   {
      this.points -= points;
   }

protected void setPoints(int points)
{
    this.points = points;
}

@Override
public int getPoints()
{
    return points;
}
   
}
