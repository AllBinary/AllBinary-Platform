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
package allbinary.game.physics.velocity;



public interface VelocityInterface extends BasicVelocityInterface
{    
	//these four should be removed or moved to another interface
   //void setMaxVelocity(Direction direction);
   //void setMaxVelocity(Angle angle);
   //void setMaxXVelocity(int multiply);
   //void setMaxYVelocity(int multiply);
   
   int getMaxForwardVelocity();

   int getMaxReverseVelocity();

   void limitMaxXYForwardVelocity();

   void limitMaxXYReverseVelocity();

   void limitXYToForwardAndReverseMaxVelocity();

   void limitMaxXYVelocity(int maxVelocity);
   
   void setMaxForwardVelocity(int maxForwardVelocity);

   void setMaxReverseVelocity(int maxReverseVelocity);
}
