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

public class NoScoreable extends Scoreable
{
   private static final NoScoreable SINGLETON = new NoScoreable();
   
   public static NoScoreable getInstance()
   {
       return SINGLETON;
   }
   
   private NoScoreable()
   {
       super(0);
   }

   public void addPoints(int points)
   {
   }
   
   public void removePoints(int points)
   {
   }
}
